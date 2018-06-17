<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="PaymentConfirmation.aspx.cs" Inherits="COMP1551.ParentTools.PaymentConfirmation" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p class="text-center" style="font-size: x-large">
        <strong>Confirmation Page
        </strong>
    </p>
    <br />
    <div id="confirmation">
        <table style="margin: auto;">
            <tr >
                <td style="text-align: left;">
                        <asp:Label ID="invoiceNumberLabel0" runat="server" Text="Recipient:" style="font-weight: 700"></asp:Label>
                    </td>
                <td style="text-align: right;">
                        <asp:Label ID="recipientValueLabel" runat="server"></asp:Label>
                    </td>
            </tr>
            <tr >
                <td style="text-align: left;">
                        <asp:Label ID="invoiceNumberLabel" runat="server" Text="Invoice Number:" style="font-weight: 700"></asp:Label>
                    </td>
                <td style="text-align: right;">
                        <asp:Label ID="invoiceNumberValueLabel" runat="server"></asp:Label>
                    </td>
            </tr>
            <tr>
                <td style="text-align: left;">
                        <asp:Label ID="confirmationNumberLabel" runat="server" Text="Confirmation Number:" style="font-weight: 700"></asp:Label>
                    </td>
                <td style="text-align: right;">
                        <asp:Label ID="confirmationNumberValueLabel" runat="server"></asp:Label>
                    </td>
            </tr>
            <tr>
                <td style="text-align: left;">
                        <asp:Label ID="totalAmountPaidLabel" runat="server" Text="Total Amount Paid:" style="font-weight: 700"></asp:Label>
                    </td>
                <td style="text-align: right;">
                        <asp:Label ID="totalAmountPaidValueLabel" runat="server"></asp:Label>
                    </td>
            </tr>
            <tr>
                <td style="text-align: left;">
                        <asp:Label ID="transactionDateLabel" runat="server" Text="Transaction Date:" style="font-weight: 700"></asp:Label>
                    </td>
                <td style="text-align: right;">
                        <asp:Label ID="transactionDateValueLabel" runat="server"></asp:Label>
                    </td>
            </tr>
        </table>
    </div>
    <br />
    <br />
    <asp:Button ID="printConfirmationBtn" runat="server" Text="Print confirmation"  onclientclick="javascript:CallPrint();" />
    <br />
    <script>
    function CallPrint() {
        var WinPrint = window.open('', '', 'letf=100,top=100,width=800,height=600,toolbar=0,scrollbars=0,status=0,dir=ltr');
        WinPrint.document.write($("[id*=confirmation]")[0].innerHTML);
        WinPrint.document.close();
        WinPrint.focus();
        WinPrint.print();
        WinPrint.close();
    }
    </script>
</asp:Content>
