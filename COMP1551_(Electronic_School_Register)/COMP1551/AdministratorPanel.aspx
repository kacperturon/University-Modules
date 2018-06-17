<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="AdministratorPanel.aspx.cs" Inherits="COMP1551.AdministratorPanel" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p>
        &nbsp;</p>
    <p class="text-center">
        <strong>Administrator Panel</strong></p>
    <p class="text-center">
        &nbsp;</p>
    <p class="text-center">
        &nbsp;</p>
    
    <table style="width: 100%; padding-bottom: 100px;">
            <tr>
                <td style="width:33%; text-align: right;">
        <asp:Button ID="addAUserBtn" runat="server" OnClick="addAUserBtn_Click" Text="Add a new User" />
                </td>
                <td style="width:33%; text-align: center;">
        <asp:Button ID="editAUserBtn" runat="server" OnClick="editAUserBtn_Click" Text="Edit a User" />
                </td>
                <td style="width: 33%; text-align: left;">
        <asp:Button ID="viewChildrenBtn" runat="server" OnClick="viewChildrenBtn_Click" Text="View Children" />
                </td>
            </tr>
    </table>
    <p>&nbsp

    </p>
</asp:Content>
