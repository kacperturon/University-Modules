<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="childProfile.aspx.cs" Inherits="COMP1551.childProfile" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p>
        &nbsp;
    </p>
    <div style="text-align: center">
        <asp:Label ID="childProfileLabel" runat="server" style="font-size: x-large; font-weight: 700; text-align: center"></asp:Label>
    </div>
    <p>
        <br/>

    </p>

    <table style="margin: auto; margin-bottom: 50px;">
        <tr>
            <td style="padding-right: 100px;">
                <asp:Image ID="childImage" runat="server" Width="200px" Height="200px"/>
            </td>
            <td>
                <table>
                    <tr>
                        <td style="text-align: right; padding-right: 50px;">
                            <asp:Label ID="firstNameLabel" runat="server" style="font-weight: 700; font-size: large;" Text="First Name:"></asp:Label>
                        </td>
                        <td>
                            <asp:Label ID="firstNameTxt" runat="server" style="font-size: large"></asp:Label>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; padding-right: 50px;">
                            <asp:Label ID="surnameLabel" runat="server" style="font-weight: 700; font-size: large;" Text="Surname:"></asp:Label>
                        </td>
                        <td>
                            <asp:Label ID="surnameTxt" runat="server" style="font-size: large"></asp:Label>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; padding-right: 50px;">
                            <asp:Label ID="usernameLabel" runat="server" style="font-weight: 700; font-size: large;" Text="Username:"></asp:Label>
                        </td>
                        <td>
                            <asp:Label ID="usernameTxt" runat="server" style="font-size: large"></asp:Label>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; padding-right: 50px;">
                            <asp:Label ID="birthDateLabel" runat="server" style="font-weight: 700; font-size: large;" Text="Birth Date:"></asp:Label>
                        </td>
                        <td>
                            <asp:Label ID="birthDateTxt" runat="server" style="font-size: large"></asp:Label>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right; padding-right: 50px;">
                            <asp:Label ID="genderLabel" runat="server" style="font-weight: 700; font-size: large;" Text="Gender:"></asp:Label>
                        </td>
                        <td>
                            <asp:Label ID="genderTxt" runat="server" style="font-size: large"></asp:Label>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>

    <p style="font-size: 16px; text-align: center">
        <strong>
            <span style="font-size: x-large">Grades</span></strong>

    </p>
    <p style="font-size: 16px; text-align: center">
        &nbsp;
    </p>
    <asp:GridView ID="gradesGridView" Width="40%" HorizontalAlign="Center" runat="server" AutoGenerateColumns="False" style="margin-left: auto;">
        <Columns>
            <asp:BoundField DataField="gradeID" HeaderText="#" SortExpression="gradeID"/>
            <asp:BoundField DataField="quizName" HeaderText="Quiz" SortExpression="quizName"/>
            <asp:BoundField DataField="gradeValue" HeaderText="Grade" SortExpression="gradeValue"/>
            <asp:BoundField DataField="gradeDate" HeaderText="Date" SortExpression="gradeDate"/>
        </Columns>
    </asp:GridView>
    <br/>
    <p></p>
</asp:Content>
