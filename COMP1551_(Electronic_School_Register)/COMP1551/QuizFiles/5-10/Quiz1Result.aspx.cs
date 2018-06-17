using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Children;

namespace COMP1551.QuizFiles._5_10
{
    public partial class Quiz1Result : System.Web.UI.Page
    {
        GradeDAO gradeDAO = new GradeDAOImpl();
        ChildDAO childDAO = new ChildDAOImpl();
        List<Grade> grades;
        private Child child;
        private Grade grade;

        protected void Page_Load(object sender, EventArgs e)
        {

            //If Child is Logged in then continue with Session Child
            if (Session["Child"] != null)
            {
                child = (Child)Session["Child"];

                if (Session["grade"] != null)
                {
                    grade = (Grade)Session["grade"];
                }
            }
            //Else Redirect to Login
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            //Change text in label (Score) to grade gained from quiz
            Score.Text = grade.GradeValue;
            //Call hallOfFame
            hallOfFame();
        }

        private void hallOfFame()
        {
            //get all grades with quizID = 1
            grades = gradeDAO.getAllGrades("1");
            int i = 0;
            HoF.Text = ("Top 10 Graded Students for Quiz 1" + "<br/>");

            //For each grade print the following into Label (HoF)
            foreach (Grade grade in grades)
            {
                //If 10 entries have been displayed then break out of loop
                if (i == 10)
                {
                    break;
                }

                HoF.Text += ("User ID: " + grade.UserId + "     Quiz Name: " + grade.QuizName + "     Grade Achieved: " + grade.GradeValue + "     Obtained : " + grade.GradeDate + "<br/>");
                i++;

            }
        }

        //Button back to Quizzes Page
        protected void back_Click(object sender, EventArgs e)
        {
            Response.Redirect("~/Quizzes.aspx");
        }
    }
}
