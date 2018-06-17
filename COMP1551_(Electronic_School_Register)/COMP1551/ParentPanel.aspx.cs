using System;
using System.Data;
using System.Drawing;
using System.IO;
using System.Web.UI;
using System.Web.UI.WebControls;
using COMP1551.DAO.Children;
using COMP1551.DAO.Parents;

namespace COMP1551
{
    public partial class ParentPanel : Page
    {
        private Parent parent;
        private bool isDue;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Parent"] != null) //Check if parent is logged in
            {
                parent = (Parent) Session["Parent"];
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            if (!IsPostBack)
            {
                Title = "Parent Panel";
                isDue = isPaymentDue(); //Check if parent has to pay the fees
                if (isDue || (parent.Children.Count < 1)) //If parent has no children display that he needs to add children
                {
                    makeAPaymentBtn.Enabled = parent.Children.Count == 0 ? false : true;
                    isDueLabel.Text = parent.Children.Count < 1 ? "add children" : "due";
                    isDueLabel.ForeColor = Color.DarkRed;
                }
                else
                {
                    makeAPaymentBtn.Enabled = false;
                    isDueLabel.Text = "Valid till " +
                                      DateTime.Parse(parent.PaymentDate).AddDays(365).ToShortDateString();
                    isDueLabel.ForeColor = Color.LimeGreen;
                }
                bindGrid(); //Add children data to gridview
            }
        }

        private void bindGrid() //Adds data to datatable and binds to gridview
        {
            DataTable childrenData = new DataTable();
            childrenData.Columns.Add("userID", typeof(int));
            childrenData.Columns.Add("ID", typeof(int));
            childrenData.Columns.Add("ImageURL", typeof(string));
            childrenData.Columns.Add("FirstName", typeof(string));
            childrenData.Columns.Add("Surname", typeof(string));

            for (int i = 0; i < parent.Children.Count; i++) //Loop through parents
            {
                childrenData.Rows.Add(parent.Children[i].ID, i + 1,
                    parent.Children[i].ImageUrl == ""
                        ? "~/Content/childrenImages/noImage_small.png"
                        : parent.Children[i].ImageUrl, parent.Children[i].FirstName, parent.Children[i].Surname); 
            }

            childrenGridView.DataSource = childrenData; //Bind data to gridview
            childrenGridView.DataBind();
        }

        private bool isPaymentDue()
        {
            bool isDue = false;
            DateTime currentDate = DateTime.Today;
            if (parent.PaymentDate != "") 
            {
                DateTime payment = DateTime.Parse(parent.PaymentDate);
                TimeSpan dayDifference = currentDate - payment;
                if (dayDifference.TotalDays > 364) //If last time parent paid was over a year ago the fees are due
                {
                    isDue = true;
                }

            }
            else //If nothing is set for paymentDate then fees need to be paid
            {
                isDue = true;
            }
            return isDue;
        }

        protected void makeAPaymentBtn_Click(object sender, EventArgs e) //Redirect to make a payment form
        {
            Response.Redirect("~/ParentTools/MakeAPayment.aspx");
        }

        protected void childrenGridView_RowCommand(object sender, GridViewCommandEventArgs e) // Upload images
        {
            if (e.CommandName.ToLower() != "upload") //Check if user pressed upload link
            {
                return;
            }
            Button bts = e.CommandSource as Button;

            GridViewRow row = (GridViewRow) ((Button) e.CommandSource).NamingContainer; //Get the selected row
            int childID = (int) childrenGridView.DataKeys[row.RowIndex]["userID"]; //Get child ID for selected row
            Child child = null;
            foreach (Child c in parent.Children) //Get child object from childID
            {
                if (c.ID == childID)
                {
                    child = c;
                }
            }

            FileUpload fu = bts.FindControl("imageUpload") as FileUpload; //Get FileUpload control from html

            if (fu.HasFile) //Check if file was inserted
            {
                string extension = Path.GetExtension(fu.FileName); //Get file extension
                if ((extension.Trim().ToLower() == ".jpg") || (extension.Trim().ToLower() == ".png") ||
                    (extension.Trim().ToLower() == ".jpeg")) //Check if the image is in .png .jpg or .jpeg
                {
                    if (child.ImageUrl != "")
                        File.Delete(Server.MapPath(child.ImageUrl)); //Delete the previous image of that child

                    var filePath = "~/Content/childrenImages/" + "profilePicture_userID_" + child.ID + extension;
                    fu.SaveAs(Server.MapPath(filePath)); //Add new image

                    child.ImageUrl = filePath;
                    ChildDAO childDAO = new ChildDAOImpl();
                    childDAO.updateChild(child); //Update image directory string in DB
                    bindGrid(); //Update the gridview to display new image
                }
            }
        }

        protected void childrenGridView_SelectedIndexChanged(object sender, EventArgs e) //Redirect to child's profile
        {
            int childID = (int) childrenGridView.DataKeys[childrenGridView.SelectedRow.RowIndex]["userID"]; //Get id of selected row
            Child child = null;
            foreach (Child c in parent.Children) //Get object from childs id
            {
                if (c.ID == childID)
                {
                    child = c;
                }
            }
            //Redirect to child's profile
            Session["childProfile"] = new ChildDAOImpl().getChild(child.Username, child.Password);//Get refreshed data for child
            Response.Redirect("~/childProfile.aspx");
        }

        protected void childrenGridView_RowDataBound(object sender, GridViewRowEventArgs e) //Disable profile viewing if parent has fees to pay
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
                if (isDue)
                {
                    e.Row.Cells[5].Enabled = false;
                    e.Row.Cells[5].ToolTip = "Payment is due for further usage.";
                }
        }
    }
}