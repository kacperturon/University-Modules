<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="EditAUserForm.aspx.cs" Inherits="COMP1551.AdministratorTools.EditAUserForm" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p>
        &nbsp;</p>
    <p style="text-align: center">
        <strong>Edit a User</strong></p>
    <p>
        <br />
    </p>
      <table class="nav-justified" id="addTable">
          <tr>
              <td>
        <asp:Label ID="selectUserTypeLabel" runat="server" Text="Select user type:"></asp:Label>
              </td>
              <td>
   
        <asp:DropDownList ID="userTypeDD" runat="server">
            <asp:ListItem>Child</asp:ListItem>
            <asp:ListItem>Parent</asp:ListItem>
            <asp:ListItem>Administrator</asp:ListItem>
        </asp:DropDownList>
              </td>
              <td>&nbsp;</td>
          </tr>
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
              <td>
        <asp:Label ID="surnameLabel" runat="server" Text="Surname: "></asp:Label>
              </td>
              <td>
        <asp:TextBox ID="surnameTxt" runat="server"></asp:TextBox>
              </td>
              <td>
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
              <td style="height: 18px">
        <asp:Label ID="telephoneLabel" runat="server" Text="Telephone number: "></asp:Label>
              </td>
              <td style="height: 18px">
        <asp:TextBox ID="telephoneTxt" runat="server" TextMode="Phone"></asp:TextBox>
              </td>
              <td style="height: 18px">
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
              <td>
                <asp:Label ID="birthDateLabel" runat="server" Text="Birth date:"></asp:Label>
              </td>
              <td>
                <asp:TextBox ID="birthDateTxt" runat="server" TextMode="Date"></asp:TextBox>
              </td>
              <td>
                <asp:CustomValidator ID="birthDateTxtValidator" runat="server" ControlToValidate="birthDateTxt" ErrorMessage="Children between 5 and 16 years old." OnServerValidate="birthDateTxtValidator_ServerValidate" ValidateEmptyText="True" ForeColor="#CC0000"></asp:CustomValidator>
              </td>
          </tr>
          <tr>
              <td>
                <asp:Label ID="genderLabel" runat="server" Text="Gender:"></asp:Label>
              </td>
              <td>
                <asp:RadioButtonList ID="genderRadioBtnList" runat="server" CellPadding="0" CellSpacing="0" Height="15px" RepeatDirection="Horizontal" Width="156px">
                    <asp:ListItem>Male</asp:ListItem>
                    <asp:ListItem>Female</asp:ListItem>
                </asp:RadioButtonList>
              </td>
              <td>
                <asp:RequiredFieldValidator ID="genderRadioBtnListValidator" runat="server" ControlToValidate="genderRadioBtnList" ErrorMessage="Selecting gender is required." ForeColor="#CC0000" style="text-align: left"></asp:RequiredFieldValidator>
              </td>
          </tr>
          <tr>
              <td>
                <asp:Label ID="parentLabel" runat="server" Text="Parent:"></asp:Label>
              </td>
              <td>
                <asp:DropDownList ID="parentsDropdownList" runat="server">
                </asp:DropDownList>

              </td>
              <td>
                <asp:RequiredFieldValidator ID="parentDropDownListValidator" runat="server" ControlToValidate="parentsDropdownList" ErrorMessage="Selecting a parent is required." InitialValue="0" ForeColor="#CC0000"></asp:RequiredFieldValidator>
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
        <asp:Button ID="editBtn" runat="server" OnClick="editBtn_Click" Text="Submit" />

              </td>
              <td>&nbsp;</td>
          </tr>
    </table>
    <br />
    <br />

    <script>
        $(document)
            .ready(function() {
                setTimeout(function() {
                        $("[id*=addedStatus]").fadeOut();
                    },
                    3000);
            });
    </script>
    
    <script src="<%=ResolveUrl("~/Scripts/AdministratorPanel/addAUser.js") %>"></script>

</asp:Content>
