using System;
using System.Data;
using System.Globalization;
using System.Web.UI;
using COMP1551.DAO.Parents;

namespace COMP1551.ParentTools
{
    public partial class MakeAPayment : Page
    {
        private Parent parent;
        private Payment payment;

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
                Title = "Payment";
                payment = new Payment(); //Create a new payment
                payment.PeopleCount = parent.Children.Count;
                Session["Payment"] = payment;
                setValues(currencyDropDownList.SelectedValue); //Update the values of inputs depending on the currency
                setChildrenData(); //Display all the children that require fees to be paid
            }
            else
            {
                payment = (Payment) Session["Payment"];
            }
        }

        private void setValues(string currency) // Sets all values for fees in correct currency
        {
            payment.Currency = currency;
            Session["Payment"] = payment;
            discountTxt.Text = payment.Discount.ToString("C", CultureInfo.CreateSpecificCulture(payment.CultureInfo));
            string discountTooltip = "";
            /* Set the discount tooltip depending on the amount of children */
            if (payment.PeopleCount > 1)
            {
                discountTooltip = "Above 1 child discount.";
            }
            if (payment.PeopleCount > 4)
            {
                discountTooltip = "Above 4 children discount.";
            }
            if (payment.PeopleCount > 9)
            {
                discountTooltip = "Above 9 children discount.";
            }
            discountTxt.ToolTip = discountTooltip;
            subtotalTxt.Text = (payment.FlatRate - payment.Discount).ToString("C",
                CultureInfo.CreateSpecificCulture(payment.CultureInfo));
            vatTxt.Text = payment.VAT.ToString("P");
            totalTxt.Text = payment.Fee.ToString("C", CultureInfo.CreateSpecificCulture(payment.CultureInfo));
        }

        private void setChildrenData() //Sets the gridview and binds the data
        {
            DataTable childrenData = new DataTable();
            childrenData.Columns.Add("#", typeof(int));
            childrenData.Columns.Add("First Name", typeof(string));
            childrenData.Columns.Add("Surname", typeof(string));
            childrenData.Columns.Add("Price", typeof(string));
            for (int i = 0; i < parent.Children.Count; i++)
            {
                childrenData.Rows.Add(i + 1, parent.Children[i].FirstName, parent.Children[i].Surname,
                    payment.FlatRatePerPerson.ToString("C", CultureInfo.CreateSpecificCulture(payment.CultureInfo)));
            }
                
            childrenGridView.DataSource = childrenData;
            childrenGridView.DataBind();
        }

        protected void currencyDropDownList_SelectedIndexChanged(object sender, EventArgs e) //Update all values
        {
            setValues(currencyDropDownList.SelectedValue); //Update values in total
            setChildrenData(); //Update child by child values
        }

        protected void payNowBtn_Click(object sender, EventArgs e) //Redirect to confirmation page after successful payment
        {
            Response.Redirect("~/ParentTools/PaymentConfirmation.aspx");
        }

    }
}