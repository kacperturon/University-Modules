using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.Web.UI.WebControls;
using COMP1551.DAO.Administrators;
using COMP1551.DAO.Children;
using COMP1551.DAO.Parents;

namespace COMP1551.AdministratorTools
{
    public partial class ViewChildren : System.Web.UI.Page
    {
        ChildDAO childDAO = new ChildDAOImpl();
        ParentDAO parentDAO = new ParentDAOImpl();
        private List<Parent> parents;
        List<Child> childrenByAgeAsc;
        private DataTable childrenData;

        protected void Page_Load(object sender, EventArgs e)
        {
            Administrator administrator; 
            if (Session["Administrator"] != null)
            {
                administrator = (Administrator) Session["Administrator"]; //Check if admin is logged in
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            if (!IsPostBack)
            {
                this.Title = "View Children";

                setSortByHeadersData(sortByRadioButtonList.SelectedValue); //Add data to gridview with header rows depening on radiobutton selected
            }

        }

        private void setSortByHeadersData(string sortType)
        {
            childrenData = new DataTable();
            childrenData.Columns.Add("#", typeof(int));
            childrenData.Columns.Add("userID", typeof(int));
            childrenData.Columns.Add("firstName", typeof(string));
            childrenData.Columns.Add("surname", typeof(string));
            childrenData.Columns.Add("age", typeof(int));

            List<GridViewRow> Headers = new List<GridViewRow>(); //List of headers that display sections e.g. (Family 1, Family 2, Age Group 5-10 etc.)
            List<int> HeadersIndex = new List<int>(); //Indexes for headers 
            if (sortType == "Family") //Add data and create headers list divided by each family
            {
                parents = parentDAO.getOnlyParentsWithChildren();
                int index = 1;
                for (int i = 0; i < parents.Count; i++) //Loop through all parents
                {
                    Headers.Add(new GridViewRow(1, 0, DataControlRowType.Header, DataControlRowState.Insert));
                    TableCell HeaderCell = new TableCell();
                    HeaderCell.Text = parents[i].FirstName + " " + parents[i].Surname + "'s Family:";
                    HeaderCell.ColumnSpan = 5;
                    HeaderCell.ForeColor = Color.Aqua;
                    HeaderCell.BackColor = Color.Teal;
                    HeaderCell.Font.Bold = true;
                    HeaderCell.HorizontalAlign = HorizontalAlign.Center;
                    
                    Headers[i].Cells.Add(HeaderCell);
                    if (i != 0)
                    {
                        index += parents[i - 1].Children.Count + 1;
                    }
                    HeadersIndex.Add(index); //Every outer loop (i) is a new family

                    for (int j = 0; j < parents[i].Children.Count; j++) //Loop through all children
                    {
                        childrenData.Rows.Add(j + 1, parents[i].Children[j].ID, parents[i].Children[j].FirstName,
                            parents[i].Children[j].Surname, DateTime.Today.Year - DateTime.Parse(parents[i].Children[j].BirthDate).Year);
                    }
                }
            }

            if (sortType == "Age Group") //Add data and create headers list divided by age groups
            {
                childrenByAgeAsc = childDAO.getAllChildrenByAgeAscending();

                int age, index = 1, headerCounter = 0, ageGroup=-1, ageGroupPrev=-1, childCounter =0;
                for(int i=0; i < childrenByAgeAsc.Count; i++) //Loop through all the children
                {
                    DateTime birthDate = DateTime.Parse(childrenByAgeAsc[i].BirthDate);
                    DateTime currentDate = DateTime.Today;
                    age = currentDate.Year - birthDate.Year;

                    index = 1;
                    if (age > 4 && age < 11)
                    {
                        ageGroup = 0;
                    }
                    if (age > 10 && age < 14)
                    {
                        ageGroup = 1;
                    }
                    if (age > 13 && age < 17)
                    {
                        ageGroup = 2;
                    }

                    if (ageGroup!=ageGroupPrev) //Compare previous age group with current and if it is different insert new header
                    {
                        Headers.Add(new GridViewRow(1, 0, DataControlRowType.Header, DataControlRowState.Insert));
                        TableCell HeaderCell = new TableCell();
                        HeaderCell.ColumnSpan = 5;
                        HeaderCell.Font.Bold = true;
                        HeaderCell.HorizontalAlign = HorizontalAlign.Center;
                        if (age > 4 && age < 11)
                        {
                            HeaderCell.Text = "Age group: 5-10";
                            HeaderCell.ForeColor = Color.LightSlateGray;
                            HeaderCell.BackColor = System.Drawing.ColorTranslator.FromHtml("#CCFF33");
                                                }
                        if (age > 10 && age < 14)
                        {
                            HeaderCell.Text = "Age group: 11-13";
                            HeaderCell.ForeColor = Color.LightSlateGray;
                            HeaderCell.BackColor = System.Drawing.ColorTranslator.FromHtml("#66FF33");
                        }
                        if (age > 13 && age < 17)
                        {
                            HeaderCell.Text = "Age group: 14-16";
                            HeaderCell.ForeColor = Color.LightSlateGray;
                            HeaderCell.BackColor = System.Drawing.ColorTranslator.FromHtml("#33FF66");
                        }
                        if (i != 0)
                        {
                            index = i + headerCounter + 1;
                        }
                        Headers[headerCounter].Cells.Add(HeaderCell);
                        HeadersIndex.Add(index);
                        headerCounter++;      
                        childCounter = 0;
                    }
                    ageGroupPrev = ageGroup;
                    childCounter++;
                    childrenData.Rows.Add(childCounter, childrenByAgeAsc[i].ID, childrenByAgeAsc[i].FirstName,
                           childrenByAgeAsc[i].Surname, age);
                }


            }
            childrenGridView.DataSource = childrenData; //Bind the data
            childrenGridView.DataBind();
            for (int i = 0; i < Headers.Count; i++) //Add header rows to correct rows in the table
            {
                childrenGridView.Controls[0].Controls.AddAt(HeadersIndex[i], Headers[i]);

            }
        }

        protected void sortByRadioButtonList_SelectedIndexChanged(object sender, EventArgs e)
        {
            setSortByHeadersData(sortByRadioButtonList.SelectedValue); //Add data to gridview with header rows depening on radiobutton selected
        }

        protected void childrenGridView_SelectedIndexChanged(object sender, EventArgs e) //Redirects to childs profile
        {
            int childID = (int)childrenGridView.DataKeys[childrenGridView.SelectedRow.RowIndex]["userID"]; //Get id for selected child
            Child child = null;
            parents = parentDAO.getOnlyParentsWithChildren();
            foreach (Parent p in parents) //Find and object of that child by id
            {
                foreach (Child c in p.Children)
                {
                    if (c.ID == childID)
                    {
                        child = c;
                    }
                }
            }

            Session["childProfile"] = child; 
            Response.Redirect("~/childProfile.aspx"); //Redirect to profile page
        }
        
    }
}