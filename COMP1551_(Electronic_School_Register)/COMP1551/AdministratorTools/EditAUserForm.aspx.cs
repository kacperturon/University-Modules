using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.IO;
using System.Web.UI.WebControls;
using COMP1551.DAO;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Parents;

namespace COMP1551.AdministratorTools
{
    public partial class EditAUserForm : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        private Child childEdit;
        private Parent parentEdit;
        private Administrator administratorEdit;

        protected void Page_Load(object sender, EventArgs e)
        {
            Administrator administrator;
            if (Session["Administrator"] != null) //Check if admin is logged in
            {
                administrator = (Administrator) Session["Administrator"]; 
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            /* Obtain the passed user for editing */
            if (Session["childEdit"] != null)
            {
                childEdit = (Child)Session["childEdit"];
            }
            else if (Session["parentEdit"] != null)
            {
                parentEdit = (Parent)Session["parentEdit"];
            }
            else if (Session["administratorEdit"] != null)
            {
                administratorEdit = (Administrator) Session["administratorEdit"];
            }
            else //If there isn't a user to edit redirect back
            {
                Response.Redirect("~/Default.aspx");
            }

            if (!IsPostBack)
            {
                this.Title = "Edit a user";

                List<Parent> parents = parentDAO.getAllParents();
                DataTable parentsFullNames = new DataTable();
                parentsFullNames.Columns.Add("ID");
                parentsFullNames.Columns.Add("FullName");
                foreach (Parent parent in parents)  //Loop through all parents
                {
                    parentsFullNames.Rows.Add(parent.ID, parent.Surname + " " + parent.FirstName); //Create a custom datatable to display surname with firstname for parents
                }
                parentsDropdownList.DataSource = parentsFullNames; //Assign the datatable as the datasource for the gridview
                parentsDropdownList.DataValueField = "ID"; //Use ID as the value field to later on identify selected parent
                parentsDropdownList.DataTextField = "FullName"; //Use fullname as textfield to display in dropdown
                parentsDropdownList.DataBind();
                parentsDropdownList.Items.Insert(0, new ListItem("Please Select", "0"));

                setInputFields(); //Set the user data from passed user in the input fields
            }
            
            setValidators(userTypeDD.SelectedValue); //Update enabled/disabled validators when user type is changed

        }

        protected void editBtn_Click(object sender, EventArgs e)
        {
            if (Page.IsValid) //Check if form is valid
            {
                try
                {
                    if (childEdit != null)
                    {

                        if (userTypeDD.SelectedValue == "Child")
                        {
                            childEdit.Username = usernameTxt.Text;
                            childEdit.Password = passwordTxt.Text;
                            childEdit.FirstName = firstNameTxt.Text;
                            childEdit.Surname = surnameTxt.Text;
                            childEdit.BirthDate = birthDateTxt.Text;
                            childEdit.Gender = genderRadioBtnList.SelectedValue;
                            childDAO.updateChild(childEdit);
                        }
                        if (userTypeDD.SelectedValue == "Parent")
                        {
                            childDAO.deleteChild(childEdit.ID);

                            parentDAO = new ParentDAOImpl();
                            parentDAO.addParent(new Parent(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                                surnameTxt.Text, postcodeTxt.Text, telephoneTxt.Text, emailTxt.Text, "",
                                new List<Child>()));
                        }
                        if (userTypeDD.SelectedValue == "Administrator")
                        {

                            childDAO.deleteChild(childEdit.ID);

                            administratorDAO = new AdministratorDAOImpl();
                            administratorDAO.addAdministrator(new Administrator(usernameTxt.Text, passwordTxt.Text));
                        }
                    }

                    if (parentEdit != null)
                    {

                        if (userTypeDD.SelectedValue == "Child")
                        {
                            parentDAO.deleteParent(parentEdit.ID);
                            childDAO = new ChildDAOImpl();
                            childDAO.addChild(new Child(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                                surnameTxt.Text,
                                birthDateTxt.Text, genderRadioBtnList.SelectedValue, "", new List<Grade>(),
                                int.Parse(parentsDropdownList.SelectedValue)));
                        }
                        if (userTypeDD.SelectedValue == "Parent")
                        {
                            parentEdit.Username = usernameTxt.Text;
                            parentEdit.Password = passwordTxt.Text;
                            parentEdit.FirstName = firstNameTxt.Text;
                            parentEdit.Surname = surnameTxt.Text;
                            parentEdit.Postcode = postcodeTxt.Text;
                            parentEdit.Telephone = telephoneTxt.Text;
                            parentEdit.Email = emailTxt.Text;
                            parentDAO.updateParent(parentEdit);
                        }
                        if (userTypeDD.SelectedValue == "Administrator")
                        {
                            parentDAO.deleteParent(parentEdit.ID);
                            administratorDAO = new AdministratorDAOImpl();
                            administratorDAO.addAdministrator(new Administrator(usernameTxt.Text, passwordTxt.Text));
                        }
                    }
                    if (administratorEdit != null)
                    {

                        if (userTypeDD.SelectedValue == "Child")
                        {
                            administratorDAO.deleteAdministrator(administratorEdit.ID);

                            childDAO = new ChildDAOImpl();
                            childDAO.addChild(new Child(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                                surnameTxt.Text,
                                birthDateTxt.Text, genderRadioBtnList.SelectedValue, "", new List<Grade>(),
                                int.Parse(parentsDropdownList.SelectedValue)));
                        }
                        if (userTypeDD.SelectedValue == "Parent")
                        {
                            administratorDAO.deleteAdministrator(administratorEdit.ID);

                            parentDAO = new ParentDAOImpl();
                            parentDAO.addParent(new Parent(usernameTxt.Text, passwordTxt.Text, firstNameTxt.Text,
                                surnameTxt.Text, postcodeTxt.Text, telephoneTxt.Text, emailTxt.Text, "",
                                new List<Child>()));
                        }
                        if (userTypeDD.SelectedValue == "Administrator")
                        {
                            administratorEdit.Username = usernameTxt.Text;
                            administratorEdit.Password = passwordTxt.Text;
                            administratorDAO.updateAdministrator(administratorEdit);
                        }
                    }
                    Session["SuccessEdit"] = true;
                }
                catch (Exception ex)
                {
                    Session["SuccessEdit"] = false;
                }
                Response.Redirect("~/AdministratorTools/EditAUser", true);


            }
        }

        private void setInputFields() //Set values in input fields from passed user 
        {
            User u = null;
            if (childEdit != null)
            {
                userTypeDD.SelectedValue = "Child";
                u = (User)childEdit;
                firstNameTxt.Text = childEdit.FirstName;
                surnameTxt.Text = childEdit.Surname;
                birthDateTxt.Text = childEdit.BirthDate;
                genderRadioBtnList.SelectedValue = childEdit.Gender;
                parentsDropdownList.Items.FindByValue(childEdit.ParentId.ToString()).Selected = true;
            }
            if (parentEdit != null)
            {
                userTypeDD.SelectedValue = "Parent";
                u = (User)parentEdit;
                firstNameTxt.Text = parentEdit.FirstName;
                surnameTxt.Text = parentEdit.Surname;
                postcodeTxt.Text = parentEdit.Postcode;
                telephoneTxt.Text = parentEdit.Telephone;
                emailTxt.Text = parentEdit.Email;
            }
            if (administratorEdit != null)
            {
                userTypeDD.SelectedValue = "Administrator";
                u = (User)administratorEdit;
            }
            usernameTxt.Text = u.Username;
            passwordTxt.Text = u.Password;
        }

        private void setValidators(string user) //Simply enable and disable necessary validation fields for each type of user
        {
            bool[] validators = new bool[8];

            if (user == "Child")
            {
                validators = new bool[] { true, true, false, false, false, true, true, true };
            }
            if (user == "Parent")
            {
                validators = new bool[] { true, true, true, true, true, false, false, false };
            }
            if (user == "Administrator")
            {
                validators = new bool[] { false, false, false, false, false, false, false, false };
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
            if (childEdit != null && args.Value == childEdit.Username ||
                parentEdit != null && args.Value == parentEdit.Username ||
                administratorEdit != null && args.Value == administratorEdit.Username) //Check if user entered exactly the same username he already had
            {
                args.IsValid = true;
            }
            else if (childDAO.childExists(args.Value) || parentDAO.parentExists(args.Value) ||
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