<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="RegisterChildren.aspx.cs" Inherits="COMP1551.RegisterChildren" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p> <strong>Register Children </strong> </p>
        <br />

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
                <asp:Label ID="birthDateLabel" runat="server" Text="Birth date:"></asp:Label>
            </td>
            <td >
                <asp:TextBox ID="birthDateTxt" runat="server" TextMode="Date" CausesValidation="True"></asp:TextBox>
            </td>
            <td >
                <asp:CustomValidator ID="birthDateTxtValidator" runat="server" ControlToValidate="birthDateTxt" ErrorMessage="Children between 5 and 16 years old." OnServerValidate="birthDateTxtValidator_ServerValidate" ValidateEmptyText="True" ForeColor="#CC0000"></asp:CustomValidator>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="genderLabel" runat="server" Text="Gender:"></asp:Label>
            </td>
            <td>
                <asp:RadioButtonList ID="genderRadioBtnList" runat="server" CellPadding="0" CellSpacing="0" Height="15px" RepeatDirection="Horizontal" Width="156px" style="margin: auto;">
                    <asp:ListItem>Male</asp:ListItem>
                    <asp:ListItem>Female</asp:ListItem>
                </asp:RadioButtonList>
            </td>
            <td>
                <asp:RequiredFieldValidator ID="genderRadioBtnListValidator" runat="server" ControlToValidate="genderRadioBtnList" ErrorMessage="Selecting gender is required." ForeColor="#CC0000" style="text-align: left"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
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
            <asp:Label ID="registerChildStatus" runat="server"></asp:Label>
    <br />
    <br />
    
    <script>
        $(document)
            .ready(function() {
                setTimeout(function() {
                    $("[id*=registerChildStatus]").fadeOut();
                    },
                    3000);
            });
    </script>
</asp:Content>
