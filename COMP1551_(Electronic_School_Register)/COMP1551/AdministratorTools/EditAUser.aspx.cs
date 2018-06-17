using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Parents;

namespace COMP1551.AdministratorTools
{
    public partial class EditAUser : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        AdministratorDAO administratorDAO = new AdministratorDAOImpl();
        List<Child> children; 
        List<Parent> parents;
        List<Administrator> administrators;

        protected void Page_Load(object sender, EventArgs e)
        {
            Administrator administrator;
            if (Session["Administrator"] != null) //Check if admin is logged in
            {
                administrator = (Administrator)Session["Administrator"];
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            editedStatus.Visible = false; //If it is a postback don't display the message again

            if (!IsPostBack)
            {
                Page.Title = "Edit users";
                setColumns(userDropDownList.SelectedValue); //Create boundfields/columns
                bindGrid(userDropDownList.SelectedValue); //Get data for specified user and bind it to gridview
                if (Session["SuccessEdit"] != null) //After admin has edited a user display a message
                {
                    editedStatus.Visible = true;
                    if ((bool)Session["SuccessEdit"])
                    {
                        editedStatus.Text = "Successfully updated user.";
                        editedStatus.CssClass = "alert alert-success";
                    }
                    else
                    {
                        editedStatus.Text = "There was an error while updating user. Please try again later.";
                        editedStatus.CssClass = "alert alert-danger";
                    }
                    Session["SuccessEdit"] = null;
                }
            }
        }

        private void bindGrid(string user) //Get data for specified user and bind to gridview
        {
            
            if (user == "Child")
            {
                children = childDAO.getAllChildren();
                userGridView.DataSource = children;
            }
            if (user == "Parent")
            {
                parents = parentDAO.getAllParents();
                userGridView.DataSource = parents;
            }
            if (user == "Administrator")
            {
                administrators = administratorDAO.getAllAdministrators();
                userGridView.DataSource = administrators;
            }

            userGridView.DataBind();
        }

        private void setColumns(string user) //Create boundfields (columns) for gridview for specified user
        {
            userGridView.Columns.Clear();
            BoundField IDField = new BoundField(); //Create a new column
            IDField.HeaderText = "ID";
            IDField.DataField = "ID";
            userGridView.Columns.Add(IDField);

            BoundField usernameField = new BoundField();
            usernameField.HeaderText = "Username";
            usernameField.DataField = "Username";

            if (user == "Child" || user == "Parent")
            {
                BoundField firstNameField = new BoundField();
                firstNameField.HeaderText = "First name";
                firstNameField.DataField = "FirstName";
                BoundField surnameField = new BoundField();
                surnameField.HeaderText = "Surname";
                surnameField.DataField = "Surname";
                userGridView.Columns.Add(firstNameField); //Add column to gridview
                userGridView.Columns.Add(surnameField);
            }
            userGridView.Columns.Add(usernameField);

        }

        protected void userDropDownList_SelectedIndexChanged(object sender, EventArgs e) //After switching dropdown list update both columns and data in gridview
        {
            setColumns(userDropDownList.SelectedValue);
            bindGrid(userDropDownList.SelectedValue);

        }

        protected void userGridView_RowDeleting(object sender, GridViewDeleteEventArgs e) //Delete user from row that 'delete' button was pressed from
        {
            int userID = int.Parse(userGridView.DataKeys[e.RowIndex].Values["ID"].ToString()); //Get the userID value from the specified row
            if (userDropDownList.SelectedValue == "Child")
            {
                childDAO.deleteChild(userID);
            }
            if (userDropDownList.SelectedValue == "Parent")
            {
                parentDAO.deleteParent(userID);
            }
            if (userDropDownList.SelectedValue == "Administrator")
            {
                administratorDAO.deleteAdministrator(userID);
            }
            bindGrid(userDropDownList.SelectedValue);

        }

        protected void userGridView_RowEditing(object sender, GridViewEditEventArgs e) //Redirect to edit form with proper user
        {
            int userID = int.Parse(userGridView.DataKeys[e.NewEditIndex].Values["ID"].ToString()); //Get userID from row selected

            /* Clear sessions to avoid any issues */
            Session["childEdit"] = null;
            Session["parentEdit"] = null;
            Session["administratorEdit"] = null;

            parents = parentDAO.getAllParents();
            administrators = administratorDAO.getAllAdministrators();

            /* Get the user session to user object by obtained userID and redirect to edit user form*/
            foreach (Parent p in parents)
            {
                if (p.ID == userID)
                {
                    Session["parentEdit"] = p;
                    Response.Redirect("~/AdministratorTools/EditAUserForm.aspx");
                }
                if (p.Children != null)
                {
                    foreach (Child c in p.Children)
                    {
                        if (c.ID == userID)
                        {
                            Session["childEdit"] = c;
                            Response.Redirect("~/AdministratorTools/EditAUserForm.aspx");
                        }
                    }
                }
            }
            foreach (Administrator a in administrators)
            {
                if (a.ID == userID)
                {
                    Session["administratorEdit"] = a;
                    Response.Redirect("~/AdministratorTools/EditAUserForm.aspx");
                }
            }

        }

    }
}