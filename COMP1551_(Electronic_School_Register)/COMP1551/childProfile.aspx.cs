using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Children;

namespace COMP1551
{
    public partial class childProfile : Page
    {
        private Child child;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["childProfile"] != null) //Check if profile was passed to this form
            {
                child = (Child) Session["childProfile"];
            }
            else
            {
                Response.Redirect("~/Default.aspx");
            }

            if (!IsPostBack)
            {
                Title = child.FirstName + " " + child.Surname + "'s Profile";
                /* Set children profile data from child object */
                childProfileLabel.Text = child.FirstName + " " + child.Surname + "'s Profile";
                childImage.ImageUrl = child.ImageUrl == ""
                    ? "~/Content/childrenImages/noImage_Large.png"
                    : child.ImageUrl;
                firstNameTxt.Text = child.FirstName;
                surnameTxt.Text = child.Surname;
                usernameTxt.Text = child.Username;
                birthDateTxt.Text = child.BirthDate;
                genderTxt.Text = child.Gender;

                /* Set datatable for child gridview */
                DataTable gradesData = new DataTable();
                gradesData.Columns.Add("gradeID", typeof(int));
                gradesData.Columns.Add("quizName", typeof(string));
                gradesData.Columns.Add("gradeValue", typeof(string));
                gradesData.Columns.Add("gradeDate", typeof(string));

                List<GridViewRow> Headers = new List<GridViewRow>(); //Headers containing months with years of grades
                List<int> HeadersIndex = new List<int>();
                DateTime gradeDate, gradeDatePrevious = new DateTime();
                
                int headerCounter = 0;
                for (int i = 0; i < child.Grades.Count; i++) //Loop through all grades of a child
                {
                    gradesData.Rows.Add(i + 1, child.Grades[i].QuizName, child.Grades[i].GradeValue,
                        child.Grades[i].GradeDate);

                    gradeDate = DateTime.Parse(child.Grades[i].GradeDate);

                    if (gradeDate.Month != gradeDatePrevious.Month) //Check if new grades month is different than previous grades if so add a new header
                    {
                        Headers.Add(new GridViewRow(1, 0, DataControlRowType.Header, DataControlRowState.Insert));
                        TableCell HeaderCell = new TableCell();
                        HeaderCell.ColumnSpan = 4;
                        HeaderCell.ForeColor = Color.OrangeRed;
                        HeaderCell.BackColor = Color.LightGray;
                        HeaderCell.Font.Bold = true;
                        HeaderCell.HorizontalAlign = HorizontalAlign.Center;
                        HeaderCell.Text = gradeDate.ToString("MMMM")+" "+gradeDate.Year;
                        HeadersIndex.Add(i + headerCounter + 1);
                        Headers[headerCounter].Cells.Add(HeaderCell);
                        headerCounter++;

                    }
                    gradeDatePrevious = gradeDate;
                }

                gradesGridView.DataSource = gradesData;
                gradesGridView.DataBind();

                for (int i = 0; i < Headers.Count; i++) //Add headers to gridview
                {
                    gradesGridView.Controls[0].Controls.AddAt(HeadersIndex[i], Headers[i]);

                }
            }
        }
    }
}