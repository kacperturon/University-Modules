using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Providers.Entities;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Parents;
using Microsoft.ApplicationInsights.Web;

namespace COMP1551
{
    public partial class RegisterChildren : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();

        private Parent parent;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Title = "Register children";
                if (Session["SuccessRegister"] == null && Session["SuccessRegisterChild"] == null)
                {
                    Response.Redirect("~/Default.aspx");
                }
            }

            if (Session["Administrator"] != null || Session["Child"] != null || Session["Parent"] == null) //Check if user is logged in
            {
                Response.Redirect("~/Default.aspx");
            }

            if (Session["Parent"] != null)
            {
                parent = (Parent)Session["Parent"];
            }

            registerChildStatus.Visible = false;
            if (Session["SuccessRegister"] == null)
            {
                if (Session["SuccessRegisterChild"] != null)
                {
                    registerChildStatus.Visible = true;
                    if ((bool) Session["SuccessRegisterChild"])
                    {
                        registerChildStatus.Text = "Successfully registered.";
                        registerChildStatus.CssClass = "alert alert-success";
                        Session["Parent"] = parentDAO.getParent(parent.Username, parent.Password);

                    }
                    else
                    {
                        registerChildStatus.Text = "There was an error while registering. Please try again later.";
                        registerChildStatus.CssClass = "alert alert-danger";
                    }
                    Session["SuccessRegisterChild"] = null; //Reset the message
                }
            }
            else
            {
                Session["SuccessRegister"] = null;
            }
            
        }

        protected void registerBtn_Click(object sender, EventArgs e) //Add child to database
        {
            if(Page.IsValid)
            {
                try
                {
                    childDAO.addChild(new Child(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                        surnameTxt.Text,
                        birthDateTxt.Text, genderRadioBtnList.SelectedValue, "", new List<Grade>(),
                        parent.ID));

                    Session["Parent"] = parentDAO.getParent(parent.Username, parent.Password);
                    Session["SuccessRegisterChild"] = true;
                }
                catch (Exception ex)
                {
                    Session["SuccessRegisterChild"] = false;
                }
                Response.Redirect("~/RegisterChildren.aspx", true);


            }
        }

        protected void usernameExistsValidator_ServerValidate(object source, ServerValidateEventArgs args) //Check if username is already used in database
        {
            if (childDAO.childExists(args.Value) || parentDAO.parentExists(args.Value) ||
                administratorDAO.administratorExists(args.Value))
            {
                args.IsValid = false;
            }
            else
            {
                args.IsValid = true;
            }

        }

        protected void birthDateTxtValidator_ServerValidate(object source, ServerValidateEventArgs args)//Check if child is between 5 and 16 years old
        {
            if (args.Value == "")
            {
                args.IsValid = false;
            }
            else
            {
                DateTime birthDate = DateTime.Parse(args.Value);
                DateTime currentDate = DateTime.Today;
                int age = currentDate.Year - birthDate.Year; //Subtract today's year with user's year of birth to get age

                if (age > 4 && age < 17)
                {
                    args.IsValid = true;
                }
                else
                {
                    args.IsValid = false;
                }
            }

        }
    }
}

       