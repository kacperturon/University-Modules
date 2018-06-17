﻿using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.IO;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Parents;

namespace COMP1551.AdministratorTools
{
    
    public partial class AddAUser : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();

        List<Parent> parents;
        Administrator administrator;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Administrator"] != null) // Check if admin is logged in
            {
                administrator = (Administrator)Session["Administrator"];
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }
            addedStatus.Visible = false;
            if (Session["SuccessAdd"] != null) //If admin has added a new user display a message
            {
                addedStatus.Visible = true;
                if ((bool) Session["SuccessAdd"])
                {
                    addedStatus.Text = "Successfully added a new user.";
                    addedStatus.CssClass = "alert alert-success";
                }
                else
                {
                    addedStatus.Text = "There was an error while adding a new user. Please try again later.";
                    addedStatus.CssClass = "alert alert-danger";
                }
                Session["SuccessAdd"] = null; //Clear the session so after refreshing the page, the message won't be displayed again
            }

            if (!IsPostBack) {
                this.Title = "Add a user";
                setParentsDropdownList(); //Add parents to the dropdownlist
            }
            setValidators(userTypeDD.SelectedValue); //Update enabled/disabled validators when user type is changed

        }

        protected void addBtn_Click(object sender, EventArgs e)
        {
            if (Page.IsValid) //Check if form is valid
            {
                try
                {
                    if (userTypeDD.SelectedValue == "Child")
                    {
                        childDAO.addChild(new Child(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                            surnameTxt.Text,
                            birthDateTxt.Text, genderRadioBtnList.SelectedValue, "", new List<Grade>(),
                            int.Parse(parentsDropdownList.SelectedValue))); //Add child to DB
                    }
                    if (userTypeDD.SelectedValue == "Parent")
                    {
                        parentDAO.addParent(new Parent(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                            surnameTxt.Text, postcodeTxt.Text, telephoneTxt.Text, emailTxt.Text, "", new List<Child>())); //Add parent to DB
                    }
                    if (userTypeDD.SelectedValue == "Administrator")
                    {
                        administratorDAO.addAdministrator(new Administrator(usernameTxt.Text, passwordTxt.Text)); //Add admin to DB

                    }
                    Session["SuccessAdd"] = true; //If no exceptions set the session to successful - true

                }
                catch (Exception ex)
                {
                    Session["SuccessAdd"] = false;

                }
                Session["Administrator"] = administrator; //Refresh the session for user
                Response.Redirect("~/AdministratorTools/AddAUser", true); //Refresh the form (clears the input fields and does !postback again)
            }

        }

        private void setValidators(string user) //Simply enable and disable necessary validation fields for each type of user
        {
            bool[] validators = new bool[8];

            if (user == "Child")
            {
                validators = new bool[]{true, true, false, false, false, true, true, true};
            }
            if (user == "Parent")
            {
                validators = new bool[] { true, true, true, true, true, false, false, false };
            }
            if (user == "Administrator")
            {
                validators = new bool[] { false, false, false, false, false, false, false, false};
            }
            
            firstNameValidator.Enabled = validators[0];
            surnameTxtValidator.Enabled = validators[1];
            postcodeTxtValidator.Enabled = validators[2];
            telephoneTxtValidator.Enabled = validators[3];
            emailTxtValidator.Enabled = validators[4];
            birthDateTxtValidator.Enabled = validators[5];
            genderRadioBtnListValidator.Enabled = validators[6];
            parentDropDownListValidator.Enabled = validators[7];
        }

        private void setParentsDropdownList()
        {
            parents = parentDAO.getAllParents();
            DataTable parentsFullNames = new DataTable();
            parentsFullNames.Columns.Add("ID");
            parentsFullNames.Columns.Add("FullName");
            foreach (Parent parent in parents) //Loop through all parents
            {
                parentsFullNames.Rows.Add(parent.ID, parent.Surname + " " + parent.FirstName); //Create a custom datatable to display surname with firstname for parents
            }
            parentsDropdownList.DataSource = parentsFullNames; //Assign the datatable as the datasource for the gridview
            parentsDropdownList.DataValueField = "ID"; //Use ID as the value field to later on identify selected parent
            parentsDropdownList.DataTextField = "FullName"; //Use fullname as textfield to display in dropdown
            parentsDropdownList.DataBind(); 
            parentsDropdownList.Items.Insert(0, new ListItem("Please Select", "0")); //Add 'Please Select' to dropdown field as the first selection
        }

        protected void birthDateTxtValidator_ServerValidate(object source, ServerValidateEventArgs args) //Check if child is between 5-16 years old
        {
            if (args.Value == "") //If birthday field is empty the field is not valid
            {
                args.IsValid = false;
            }
            else
            {
                DateTime birthDate = DateTime.Parse(args.Value); //Get child's birthdate
                DateTime currentDate = DateTime.Today; //Get current date
                int age = currentDate.Year - birthDate.Year; //Get child's age

                if (age > 4 && age < 17) //Only accept kids between 5 and 16 years old
                {
                    args.IsValid = true;
                }
                else
                {
                    args.IsValid = false;
                }
            }
        }

        protected void usernameExistsValidator_ServerValidate(object source, ServerValidateEventArgs args) //Check if username already exists in database
        {
            if (childDAO.childExists(args.Value) || parentDAO.parentExists(args.Value) ||
                administratorDAO.administratorExists(args.Value)) //Check if username already exists in DB and if so the field is not valid
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