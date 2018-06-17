using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Parents;

namespace COMP1551
{
    public partial class Register : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Child"] != null || Session["Administrator"] != null || Session["Parent"] != null && Session["SuccesRegister"] ==null) //Check if someone is already logged in
            {
                Response.Redirect("~/Default.aspx");
            }

            if (Session["SuccessRegister"] != null)
            {
                if (!(bool)Session["SuccessRegister"]) //Check if user has registered if not he would already have been redirected
                {
                   registerStatus.Text = "There was an error while registering. Please try again later.";
                    registerStatus.CssClass = "alert alert-danger";
                }
                Session["SuccessRegister"] = null; //After displaying message reset the session so after refreshing it will not repeat
            }

            if (!IsPostBack)
            {
                Title = "Registration";
            }
        }

        protected void registerBtn_Click(object sender, EventArgs e)
        {
            if (Page.IsValid)
            {
                try
                {
                    Parent parent = new Parent(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                        surnameTxt.Text, postcodeTxt.Text, telephoneTxt.Text, emailTxt.Text, "", new List<Child>());
                    parentDAO.addParent(parent);

                    Session["SuccessRegister"] = true; 
                    Session["Parent"] = parentDAO.getParent(parent.Username, parent.Password); //Pass newly created parent 
                }
                catch (Exception ex)
                {
                    Session["SuccessRegister"] = false;
                    Response.Redirect("~/Register.aspx", true);

                }
                Response.Redirect("~/RegisterChildren.aspx");
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
    }
}