<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ViewChildren.aspx.cs" Inherits="COMP1551.AdministratorTools.ViewChildren" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p class="text-center" style="font-size: large">&nbsp;</p>
    <p class="text-center" style="font-size: large; text-align: center;"><strong>View Children</strong></p>
    <p class="text-center" style="font-size: large">&nbsp;</p>
    <table class="nav-justified">
     <tr>
            <td style="text-align: right; width: 47%; margin-right: 3%;">
                <asp:Label ID="sortByLabel" runat="server" Text="Sort by: "></asp:Label>
            </td>
            <td>
                <asp:RadioButtonList ID="sortByRadioButtonList" runat="server" AutoPostBack="True" RepeatDirection="Horizontal" OnSelectedIndexChanged="sortByRadioButtonList_SelectedIndexChanged">
                    <asp:ListItem Selected="True">Family</asp:ListItem>
                    <asp:ListItem>Age Group</asp:ListItem>
                </asp:RadioButtonList>    
            </td>
        </tr>
    </table>
    <br />
    <table class="nav-justified">
       
    </table>
    <asp:GridView ID="childrenGridView" runat="server" HorizontalAlign="Center" Width="100%" AutoGenerateColumns="False" DataKeyNames="userID" OnSelectedIndexChanged="childrenGridView_SelectedIndexChanged">
        <Columns>
            <asp:BoundField DataField="#" HeaderText="#" SortExpression="#" />
            <asp:BoundField DataField="firstName" HeaderText="First Name" SortExpression="firstName" />
            <asp:BoundField DataField="surname" HeaderText="Surname" SortExpression="surname" />
            <asp:BoundField DataField="age" HeaderText="Age" SortExpression="age" />
            <asp:CommandField HeaderText="View profile " ShowHeader="True" ShowSelectButton="True" />
        </Columns>
    </asp:GridView>
    <br />
    </asp:Content>
