using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Quizzes;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Children;
using System.Configuration;

namespace COMP1551.QuizFiles._5_10
{
    public partial class Quiz3 : System.Web.UI.Page
    {
        QuizDAO quizDAO = new QuizDAOImpl();
        private Child child;
        private Quiz quiz3;
        private int score;
        private Grade grade;
        int i = 1;
        int total = 0;

        protected void Page_Load(object sender, EventArgs e)
        {

            //If Child is Logged in then continue with Session Child
            if (Session["Child"] != null) // Check if admin is logged in
            {
                child = (Child)Session["Child"];
            }
            //Else Redirect to Login
            else
            {
                Response.Redirect("~/Login.aspx");
            }


            quiz3 = quizDAO.getQuiz(3);
            //Cycle through Questions in Quiz1
            foreach (Question question in quiz3.Questions)
            {
                //If Statements to Split question into correct Lables
                if (i == 1)
                {
                    q1q1.Text = question.QuestionValue;
                    q1o1.Text = question.Option1;
                    q1o2.Text = question.Option3;
                    q1o3.Text = question.Option2;
                    q1a1.Text = question.Answer;
                }
                else if (i == 2)
                {
                    q2q1.Text = question.QuestionValue;
                    q2o1.Text = question.Option1;
                    q2o2.Text = question.Option3;
                    q2o3.Text = question.Option2;
                    q2a1.Text = question.Answer;
                }
                else if (i == 3)
                {
                    q3q1.Text = question.QuestionValue;
                    q3o1.Text = question.Option1;
                    q3o2.Text = question.Option3;
                    q3o3.Text = question.Option2;
                    q3a1.Text = question.Answer;
                }
                else if (i == 4)
                {
                    q4q1.Text = question.QuestionValue;
                    q4o1.Text = question.Option1;
                    q4o2.Text = question.Option3;
                    q4o3.Text = question.Option2;
                    q4a1.Text = question.Answer;
                }
                else if (i == 5)
                {
                    q5q1.Text = question.QuestionValue;
                    q5o1.Text = question.Option1;
                    q5o2.Text = question.Option3;
                    q5o3.Text = question.Option2;
                    q5a1.Text = question.Answer;
                }
                else if (i == 6)
                {
                    q6q1.Text = question.QuestionValue;
                    q6o1.Text = question.Option1;
                    q6o2.Text = question.Option3;
                    q6o3.Text = question.Option2;
                    q6a1.Text = question.Answer;
                }
                else if (i == 7)
                {
                    q7q1.Text = question.QuestionValue;
                    q7o1.Text = question.Option1;
                    q7o2.Text = question.Option3;
                    q7o3.Text = question.Option2;
                    q7a1.Text = question.Answer;
                }
                else if (i == 8)
                {
                    q8q1.Text = question.QuestionValue;
                    q8o1.Text = question.Option1;
                    q8o2.Text = question.Option3;
                    q8o3.Text = question.Option2;
                    q8a1.Text = question.Answer;
                }
                else if (i == 9)
                {
                    q9q1.Text = question.QuestionValue;
                    q9o1.Text = question.Option1;
                    q9o2.Text = question.Option3;
                    q9o3.Text = question.Option2;
                    q9a1.Text = question.Answer;
                }
                else
                {
                    q10q1.Text = question.QuestionValue;
                    q10o1.Text = question.Option1;
                    q10o2.Text = question.Option3;
                    q10o3.Text = question.Option2;
                    q10a1.Text = question.Answer;
                }

                i++;
            }
        }

        //Finish button Event Handler
        protected void Finish_Click(object sender, EventArgs e)
        {
            //If Statements to check if correct answer has been selected
            if (q1a1.Checked)
            { total += 1; }
            if (q2a1.Checked)
            { total += 1; }
            if (q3a1.Checked)
            { total += 1; }
            if (q4a1.Checked)
            { total += 1; }
            if (q5a1.Checked)
            { total += 1; }
            if (q6a1.Checked)
            { total += 1; }
            if (q7a1.Checked)
            { total += 1; }
            if (q8a1.Checked)
            { total += 1; }
            if (q9a1.Checked)
            { total += 1; }
            if (q10a1.Checked)
            { total += 1; }

            try
            {
                GradeDAO gradeDAO = new GradeDAOImpl();
                grade = new Grade((total * 10) + "%", "3", DateTime.Now.ToString("d/M/yyyy"), child.ID);
                Session["grade"] = grade;
                gradeDAO.addGrade(grade);
                Session["Success"] = true;
            }
            catch (Exception ex)
            {
                Session["Success"] = false;
            }

            Response.Redirect("~/QuizFiles/5-10/Quiz3Result.aspx");
        }

        public int Score
        {
            get { return score; }
            set { score = value; }
        }

        protected void q1o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q1o1.Checked == true)
            { q1o2.Checked = false; q1o3.Checked = false; q1a1.Checked = false; }
        }

        protected void q1o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q1o2.Checked == true)
            { q1o1.Checked = false; q1o3.Checked = false; q1a1.Checked = false; }
        }

        protected void q1o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q1o3.Checked == true)
            { q1o1.Checked = false; q1o2.Checked = false; q1a1.Checked = false; }
        }

        protected void q1a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q1a1.Checked == true)
            { q1o1.Checked = false; q1o2.Checked = false; q1o3.Checked = false; }
        }

        protected void q2o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q2o1.Checked == true)
            { q2o2.Checked = false; q2o3.Checked = false; q2a1.Checked = false; }
        }

        protected void q2o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q2o2.Checked == true)
            { q2o1.Checked = false; q2o3.Checked = false; q2a1.Checked = false; }
        }

        protected void q2o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q2o3.Checked == true)
            { q2o1.Checked = false; q2o2.Checked = false; q2a1.Checked = false; }
        }

        protected void q2a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q2a1.Checked == true)
            { q2o1.Checked = false; q2o2.Checked = false; q2o3.Checked = false; }
        }

        protected void q3o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q3o1.Checked == true)
            { q3o2.Checked = false; q3o3.Checked = false; q3a1.Checked = false; }
        }

        protected void q3o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q3o2.Checked == true)
            { q3o1.Checked = false; q3o3.Checked = false; q3a1.Checked = false; }
        }

        protected void q3o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q3o3.Checked == true)
            { q3o1.Checked = false; q3o2.Checked = false; q3a1.Checked = false; }
        }

        protected void q3a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q3a1.Checked == true)
            { q3o1.Checked = false; q3o2.Checked = false; q3o3.Checked = false; }
        }

        protected void q4o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q4o1.Checked == true)
            { q4o2.Checked = false; q4o3.Checked = false; q4a1.Checked = false; }
        }

        protected void q4o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q4o2.Checked == true)
            { q4o1.Checked = false; q4o3.Checked = false; q4a1.Checked = false; }
        }

        protected void q4o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q4o3.Checked == true)
            { q4o1.Checked = false; q4o2.Checked = false; q4a1.Checked = false; }
        }

        protected void q4a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q4a1.Checked == true)
            { q4o1.Checked = false; q4o2.Checked = false; q4o3.Checked = false; }
        }

        protected void q5o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q5o1.Checked == true)
            { q5o2.Checked = false; q5o3.Checked = false; q5a1.Checked = false; }
        }

        protected void q5o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q5o2.Checked == true)
            { q5o1.Checked = false; q5o3.Checked = false; q5a1.Checked = false; }
        }

        protected void q5o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q5o3.Checked == true)
            { q5o1.Checked = false; q5o2.Checked = false; q5a1.Checked = false; }
        }

        protected void q5a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q5a1.Checked == true)
            { q5o1.Checked = false; q5o2.Checked = false; q5o3.Checked = false; }
        }

        protected void q6o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q6o1.Checked == true)
            { q6o2.Checked = false; q6o3.Checked = false; q6a1.Checked = false; }
        }

        protected void q6o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q6o2.Checked == true)
            { q6o1.Checked = false; q6o3.Checked = false; q6a1.Checked = false; }
        }

        protected void q6o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q6o3.Checked == true)
            { q6o1.Checked = false; q6o2.Checked = false; q6a1.Checked = false; }
        }

        protected void q6a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q6a1.Checked == true)
            { q6o1.Checked = false; q6o2.Checked = false; q6o3.Checked = false; }
        }

        protected void q7o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q7o1.Checked == true)
            { q7o2.Checked = false; q7o3.Checked = false; q7a1.Checked = false; }
        }

        protected void q7o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q7o2.Checked == true)
            { q7o1.Checked = false; q7o3.Checked = false; q7a1.Checked = false; }
        }

        protected void q7o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q7o3.Checked == true)
            { q7o1.Checked = false; q7o2.Checked = false; q7a1.Checked = false; }
        }

        protected void q7a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q7a1.Checked == true)
            { q7o1.Checked = false; q7o2.Checked = false; q7o3.Checked = false; }
        }

        protected void q8o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q8o1.Checked == true)
            { q8o2.Checked = false; q8o3.Checked = false; q8a1.Checked = false; }
        }

        protected void q8o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q8o2.Checked == true)
            { q8o1.Checked = false; q8o3.Checked = false; q8a1.Checked = false; }
        }

        protected void q8o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q8o3.Checked == true)
            { q8o1.Checked = false; q8o2.Checked = false; q8a1.Checked = false; }
        }

        protected void q8a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q8a1.Checked == true)
            { q8o1.Checked = false; q8o2.Checked = false; q8o3.Checked = false; }
        }

        protected void q9o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q9o1.Checked == true)
            { q9o2.Checked = false; q9o3.Checked = false; q9a1.Checked = false; }
        }

        protected void q9o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q9o2.Checked == true)
            { q9o1.Checked = false; q9o3.Checked = false; q9a1.Checked = false; }
        }

        protected void q9o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q9o3.Checked == true)
            { q9o1.Checked = false; q9o2.Checked = false; q9a1.Checked = false; }
        }

        protected void q9a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q9a1.Checked == true)
            { q9o1.Checked = false; q9o2.Checked = false; q9o3.Checked = false; }
        }

        protected void q10o1_CheckedChanged(object sender, EventArgs e)
        {
            if (q10o1.Checked == true)
            { q10o2.Checked = false; q10o3.Checked = false; q10a1.Checked = false; }
        }

        protected void q10o2_CheckedChanged(object sender, EventArgs e)
        {
            if (q10o2.Checked == true)
            { q10o1.Checked = false; q10o3.Checked = false; q10a1.Checked = false; }
        }

        protected void q10o3_CheckedChanged(object sender, EventArgs e)
        {
            if (q10o3.Checked == true)
            { q10o1.Checked = false; q10o2.Checked = false; q10a1.Checked = false; }
        }

        protected void q10a1_CheckedChanged(object sender, EventArgs e)
        {
            if (q10a1.Checked == true)
            { q10o1.Checked = false; q10o2.Checked = false; q10o3.Checked = false; }
        }
    }
}