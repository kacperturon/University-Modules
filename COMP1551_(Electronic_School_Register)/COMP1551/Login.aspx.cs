using System;
using System.Configuration;
using System.IO;
using System.Web.UI;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Parents;

namespace COMP1551
{
    public partial class Login : Page
    {
        private ChildDAO childDAO = new ChildDAOImpl();
        private ParentDAO parentDAO = new ParentDAOImpl();
        private AdministratorDAO administratorDAO = new AdministratorDAOImpl();

        protected void Page_Load(object sender, EventArgs e)
        {
            /* If user is already logged in redirect to user panel */
            if (Session["Child"] != null || Session["Parent"] != null || Session["Administrator"] != null)
            {
                Response.Redirect("~/Default.aspx");
            }

            if (!IsPostBack)
            {
                Title = "Login screen";
            }
        }

        protected void loginButton_Click(object sender, EventArgs e)
        {
            /* Check if user passed correct username and password if so redirect to user panel */
            Child child = childDAO.getChild(usernameTxt.Text, passwordTxt.Text);
            Parent parent = parentDAO.getParent(usernameTxt.Text, passwordTxt.Text);
            Administrator administrator = administratorDAO.getAdministrator(usernameTxt.Text, passwordTxt.Text);
            if (child != null)
            {
                incorrectCredentialsLabel.Text = ""; //Clear the label if was incorrect previously
                Session["Child"] = child;
                Response.Redirect("~/Quizzes.aspx");
            }
            if (parent != null) //Successfully logged in
            {
                incorrectCredentialsLabel.Text = ""; //Clear the label if was incorrect previously
                Session["Parent"] = parent;
                Response.Redirect("~/ParentPanel.aspx");
            }
            if (administrator != null)
            {
                incorrectCredentialsLabel.Text = ""; //Clear the label if was incorrect previously
                Session["Administrator"] = administrator;
                Response.Redirect("~/AdministratorPanel.aspx");
            }

            incorrectCredentialsLabel.Text = "Incorrect Username or Password.";
            
        }
    }
}