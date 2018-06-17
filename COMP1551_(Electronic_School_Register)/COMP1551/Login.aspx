<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="COMP1551.Login" %>
<asp:Content ID="Content1" runat="server" contentplaceholderid="MainContent">
    <p class="text-center" style="text-align: center; font-size: large">
        &nbsp;</p>
    <p class="text-center" style="text-align: center; font-size: large;">
        <strong>LOGIN</strong></p>
    <p class="text-center" style="text-align: center; font-size: large">
        &nbsp;</p>
    <br />
    <table class="nav-justified" id="addTable">
        <tr>
            <td >Username:</td>
            <td class="modal-sm" style="width: 11%;">
                <asp:TextBox ID="usernameTxt" runat="server" Width="175px"></asp:TextBox>
            </td>
            <td >
                <asp:RequiredFieldValidator ID="usernameTxtValidator" runat="server" ControlToValidate="usernameTxt" ErrorMessage="Username is required!" ForeColor="#CC0000" style="text-align: left"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td >Password:</td>
            <td class="modal-sm" >
                <asp:TextBox ID="passwordTxt" runat="server" TextMode="Password" Width="175px"></asp:TextBox>
            </td>
            <td >
                <asp:RequiredFieldValidator ID="passwordTxtValidator" runat="server" ControlToValidate="passwordTxt" ErrorMessage="Password is required!" ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td class="modal-sm">
                <br />
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td class="modal-sm">
                <asp:Button ID="loginButton" runat="server" OnClick="loginButton_Click" Text="Login" />
            </td>
            <td>
                <asp:Label ID="incorrectCredentialsLabel" runat="server" ForeColor="#CC0000"></asp:Label>
            </td>
        </tr>
    </table>
    <br />
    <br />
</asp:Content>

