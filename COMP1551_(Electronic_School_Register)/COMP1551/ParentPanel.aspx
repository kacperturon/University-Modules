<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ParentPanel.aspx.cs" Inherits="COMP1551.ParentPanel" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p class="text-center" style="font-size: large; text-align: center">
        &nbsp;</p>
    <p class="text-center" style="font-size: large; text-align: center">
        <strong>Parent Panel</strong></p>
    <p class="text-center" style="font-size: large; text-align: center">
        &nbsp;</p>

    <table style="margin: auto;">
        <tr>
            <td style="padding-right: 70px;" >
        <asp:Button ID="makeAPaymentBtn" runat="server" OnClick="makeAPaymentBtn_Click" Text="Make a Payment" />
            </td>
            <td>
                <asp:Label ID="feesPaymentLabel" runat="server" Text="Fees payment:"></asp:Label>
            </td>
            <td>
                <asp:Label ID="isDueLabel" runat="server" style="font-weight: 700; margin-left: 15px;"></asp:Label>
            </td>
        </tr>
    </table>
    <p>
        &nbsp;</p>
    
        <asp:GridView ID="childrenGridView" runat="server" AutoGenerateColumns="False" BackColor="White" BorderColor="#CCCCCC" BorderStyle="None" BorderWidth="1px" CellPadding="3" Width="100%" OnRowCommand="childrenGridView_RowCommand" DataKeyNames="userID" OnRowDataBound="childrenGridView_RowDataBound" OnSelectedIndexChanged="childrenGridView_SelectedIndexChanged">
            <Columns>
                <asp:BoundField DataField="ID" HeaderText="#" />
                <asp:ImageField DataImageUrlField="ImageURL" HeaderText="Image">
                    <ControlStyle Height="50px" Width="50px" />
                </asp:ImageField>
                <asp:BoundField DataField="FirstName" HeaderText="First Name" />
                <asp:BoundField DataField="Surname" HeaderText="Surname" />
                <asp:TemplateField HeaderText="Update image" ItemStyle-width="28%" >
                    <ItemTemplate >
                        <asp:FileUpload ID="imageUpload" runat="server" style="display: inline;" />
                        <asp:Button ID="uploadImage" runat="server" EnableViewState="False" Text="Upload" CommandName="Upload" />
                    </ItemTemplate>
                </asp:TemplateField>
                <asp:CommandField HeaderText="View profile" ShowSelectButton="True" />
            </Columns>
            <FooterStyle BackColor="White" ForeColor="#000066" />
            <HeaderStyle BackColor="#006699" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="White" ForeColor="#000066" HorizontalAlign="Left" />
            <RowStyle ForeColor="#000066" />
            <SelectedRowStyle BackColor="#669999" Font-Bold="True" ForeColor="White" />
            <SortedAscendingCellStyle BackColor="#F1F1F1" />
            <SortedAscendingHeaderStyle BackColor="#007DBB" />
            <SortedDescendingCellStyle BackColor="#CAC9C9" />
            <SortedDescendingHeaderStyle BackColor="#00547E" />
        </asp:GridView>

    <p>
    <br />
</p>
    
    <style>
        th {
            color: white;
        }
    </style>
</asp:Content>
