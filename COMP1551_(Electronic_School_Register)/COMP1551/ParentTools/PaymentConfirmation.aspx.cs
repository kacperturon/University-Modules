using System;
using System.Globalization;
using System.Web.UI;
using COMP1551.DAO.Parents;

namespace COMP1551.ParentTools
{
    public partial class PaymentConfirmation : Page
    {
        private Parent parent;
        private Payment payment;
        private readonly ParentDAO parentDAO = new ParentDAOImpl();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Session["Parent"] != null) //Check if parent is logged in
            {
                parent = (Parent) Session["Parent"];
                if (Session["Payment"] != null) //Check if user has paid succesfully
                {
                    payment = (Payment) Session["Payment"];
                }
                else
                {
                    Response.Redirect("~/ParentTools/MakeAPayment.aspx");
                }
            }
            else
            {
                Response.Redirect("~/Login.aspx");
            }

            if (!IsPostBack)
            {
                Title = "Payment Confirmation";

                /* Set all labels to payment data */
                recipientValueLabel.Text = "Learn-On-Line PLC";
                invoiceNumberValueLabel.Text = Payment.InvoiceNumber.ToString();
                confirmationNumberValueLabel.Text = payment.ConfirmationNumber;
                totalAmountPaidValueLabel.Text = payment.Fee.ToString("C",
                    CultureInfo.CreateSpecificCulture(payment.CultureInfo));
                transactionDateValueLabel.Text = DateTime.Now.ToString("d/M/yyyy");

                parent.PaymentDate = DateTime.Now.ToString("d/M/yyyy");
                parentDAO.updateParent(parent);
            }
        }
    }
}