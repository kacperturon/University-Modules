<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Register.aspx.cs" Inherits="COMP1551.Register" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <br />
    <p>
        <strong>Parent registration
    </strong>
    </p>
    <table class="nav-justified" id="addTable">
        <tr>
            <td>
                <asp:Label ID="usernameLabel" runat="server" Text="Username: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="usernameTxt" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="usernameRequiredValidator" runat="server" ControlToValidate="usernameTxt" ErrorMessage="Username is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
                <br />
                <asp:CustomValidator ID="usernameExistsValidator" runat="server" ControlToValidate="usernameTxt" ErrorMessage="This username already exists." OnServerValidate="usernameExistsValidator_ServerValidate" ForeColor="#CC0000"></asp:CustomValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="passwordLabel" runat="server" Text="Password: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="passwordTxt" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="passwordTxtValidator" runat="server" ControlToValidate="passwordTxt" ErrorMessage="Password is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="firstNameLabel" runat="server" Text="First name: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="firstNameTxt" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="firstNameValidator" runat="server" ControlToValidate="firstNameTxt" ErrorMessage="First name is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td style="height: 22px">
                <asp:Label ID="surnameLabel" runat="server" Text="Surname: "></asp:Label>
            </td>
            <td style="height: 22px">
                <asp:TextBox ID="surnameTxt" runat="server"></asp:TextBox>
            </td>
            <td style="height: 22px">
                <asp:RequiredFieldValidator ID="surnameTxtValidator" runat="server" ControlToValidate="surnameTxt" ErrorMessage="Surname is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="postcodeLabel" runat="server" Text="Postcode: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="postcodeTxt" runat="server"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="postcodeTxtValidator" runat="server" ControlToValidate="postcodeTxt" ErrorMessage="Postcode is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="telephoneLabel" runat="server" Text="Telephone number: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="telephoneTxt" runat="server" TextMode="Phone"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="telephoneTxtValidator" runat="server" ControlToValidate="telephoneTxt" ErrorMessage="Telephone number is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="emailLabel" runat="server" Text="Email: "></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="emailTxt" runat="server" TextMode="Email"></asp:TextBox>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="emailTxtValidator" runat="server" ControlToValidate="emailTxt" ErrorMessage="Email is required." ForeColor="#CC0000"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td style="height: 18px"></td>
            <td style="height: 18px"></td>
            <td style="height: 18px"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <asp:Button ID="registerBtn" runat="server" OnClick="registerBtn_Click" Text="Register" />
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
    <br />
    <br />
    <div style="text-align: center; margin-top: 20px;">
            <asp:Label ID="registerStatus" runat="server"></asp:Label>
    </div>
    <br />
    <br />
    <script>
        $(document)
            .ready(function() {
                setTimeout(function() {
                    $("[id*=registerStatus]").fadeOut();
                    },
                    3000);
            });
    </script>
</asp:Content>
