using System;
using System.Web.UI;

namespace COMP1551
{
    public partial class _Default : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {


            /* Redirect based on what and if user is logged in */
            if (Session["Child"] != null)
            {
                Response.Redirect("~/Quizzes.aspx");
            }
            if (Session["Parent"] != null)
            {
                Response.Redirect("~/ParentPanel.aspx");
            }
            if (Session["Administrator"] != null)
            {
                Response.Redirect("~/AdministratorPanel.aspx");
            }

            Response.Redirect("~/Login.aspx");
        }
    }
}