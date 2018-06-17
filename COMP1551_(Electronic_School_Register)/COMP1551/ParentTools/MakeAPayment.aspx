<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="MakeAPayment.aspx.cs" Inherits="COMP1551.ParentTools.MakeAPayment" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p class="text-center" style="font-size: large">
        &nbsp;</p>
    <p class="text-center" style="font-size: large">
        <strong>Make a Payment</strong></p>
    <p class="text-center">
        &nbsp;</p>

    <asp:GridView ID="childrenGridView" runat="server" HorizontalAlign="Center" Width="100%">
    </asp:GridView>

    <table class="nav-justified">
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="width: 297px">
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="width: 297px">
                <asp:Label ID="discountLabel" runat="server" Text="Discount:"></asp:Label>
            </td>
            <td style="text-align: right">
                <asp:TextBox ID="discountTxt" runat="server" ReadOnly="True" Width="150px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="height: 24px; width: 4335px"></td>
            <td style="height: 24px; width: 297px">
                <asp:Label ID="subtotalLabel" runat="server" Text="Subtotal:"></asp:Label>
            </td>
            <td style="height: 24px; text-align: right;">
                <asp:TextBox ID="subtotalTxt" runat="server" ReadOnly="True" Width="150px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="height: 24px; width: 4335px"></td>
            <td style="height: 24px; width: 297px">
                <asp:Label ID="vatLabel" runat="server" Text="VAT:"></asp:Label>
            </td>
            <td style="height: 24px; text-align: right;">
                <asp:TextBox ID="vatTxt" runat="server" ReadOnly="True" Width="150px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="width: 297px">
                <asp:Label ID="totalLabel" runat="server" Text="Total:"></asp:Label>
            </td>
            <td style="text-align: right">
                <asp:TextBox ID="totalTxt" runat="server" ReadOnly="True" BackColor="Silver" Width="150px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="width: 297px">
                <asp:Label ID="currencyLabel" runat="server" Text="Currency:"></asp:Label>
            </td>
            <td>
                <asp:DropDownList ID="currencyDropDownList" runat="server" AutoPostBack="True" OnSelectedIndexChanged="currencyDropDownList_SelectedIndexChanged">
                    <asp:ListItem>GBP</asp:ListItem>
                    <asp:ListItem>EUR</asp:ListItem>
                    <asp:ListItem>USD</asp:ListItem>
                    <asp:ListItem>AUD</asp:ListItem>
                </asp:DropDownList>
            </td>
        </tr>
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="width: 297px">
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td style="width: 4335px">&nbsp;</td>
            <td style="text-align: center;" colspan="2">
                <asp:Button ID="payNowBtn" runat="server" OnClick="payNowBtn_Click" Text="Pay Now" />
            </td>
        </tr>
    </table>
    <p class="text-center">
        &nbsp;</p>
</asp:Content>
