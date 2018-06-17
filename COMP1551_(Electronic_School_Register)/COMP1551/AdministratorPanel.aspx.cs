using System;
using System.Web.UI;
using COMP1551.DAO.Administrators;

namespace COMP1551
{
    public partial class AdministratorPanel : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Administrator administrator;
            if (Session["Administrator"] != null) //Check if administrator is logged in
            {
                administrator = (Administrator) Session["Administrator"];
            }
            else
            {
                Response.Redirect("~/Login.aspx");

            }
            if (!IsPostBack)
            {
                Title = "Administrator Panel";
            }
        }

        protected void addAUserBtn_Click(object sender, EventArgs e) //Redirect to add a user form
        {
            Response.Redirect("~/AdministratorTools/AddAUser.aspx");
        }

        protected void viewChildrenBtn_Click(object sender, EventArgs e) //Redirect to view children form
        {
            Response.Redirect("~/AdministratorTools/ViewChildren.aspx");
        }

        protected void editAUserBtn_Click(object sender, EventArgs e) //Redirect to edit users form
        {
            Response.Redirect("~/AdministratorTools/EditAUser.aspx");
        }
    }
}