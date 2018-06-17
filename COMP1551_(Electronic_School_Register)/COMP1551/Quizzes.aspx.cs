using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;

namespace COMP1551
{
    public partial class Quizzes : System.Web.UI.Page
    {
        private Child child;

        protected void Page_Load(object sender, EventArgs e)
        {
            //If Child is Logged in then continue with Session Child
            if (Session["Child"] != null)
            {
                child = (Child)Session["Child"];
            }
            //Else Redirect to Login
            else
            {
                Response.Redirect("~/Login.aspx");
            }
        }

        //Image Buttons Redirecting to Quizzes
        protected void ImageButton1_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz1.aspx");
        }

        protected void ImageButton2_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz2.aspx");
        }

        protected void ImageButton3_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz3.aspx");
        }

        protected void ImageButton4_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz1.aspx");
        }

        protected void ImageButton5_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz2.aspx");
        }

        protected void ImageButton6_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz3.aspx");
        }

        protected void ImageButton7_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz1.aspx");
        }

        protected void ImageButton8_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz2.aspx");
        }

        protected void ImageButton9_Click(object sender, ImageClickEventArgs e)
        {
            Response.Redirect("~/QuizFiles/5-10/Quiz3.aspx");
        }
    }
}