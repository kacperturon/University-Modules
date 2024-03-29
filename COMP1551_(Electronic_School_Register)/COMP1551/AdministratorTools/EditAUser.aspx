﻿<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="EditAUser.aspx.cs" Inherits="COMP1551.AdministratorTools.EditAUser" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <p style="text-align: center">
        &nbsp;</p>
    <p style="text-align: center">
        <span style="font-size: large"><strong>Update users</strong></span></p>
    <p style="text-align: center">
        &nbsp;</p>
    
    <table style="margin: auto;" >
        <tr>
            <td>
                <asp:Label ID="userTypeLabel" runat="server" Text="User type:"></asp:Label>
            </td>
            <td style="width: 6px">&nbsp;</td>
            <td style="text-align: center">
    <asp:DropDownList ID="userDropDownList" runat="server" AutoPostBack="True" OnSelectedIndexChanged="userDropDownList_SelectedIndexChanged">
        <asp:ListItem>Child</asp:ListItem>
        <asp:ListItem>Parent</asp:ListItem>
        <asp:ListItem>Administrator</asp:ListItem>
    </asp:DropDownList>
            </td>
        </tr>
    </table>
    <p></p>
    <asp:GridView ID="userGridView" runat="server" HorizontalAlign="Center" AllowSorting="True" AutoGenerateColumns="False" AutoGenerateDeleteButton="True" OnRowDeleting="userGridView_RowDeleting" AutoGenerateEditButton="True" OnRowEditing="userGridView_RowEditing" style="margin-left: auto;" DataKeyNames="ID">
    </asp:GridView>

    <p>
        
    </p>
   
    <div style="text-align: center; margin-top: 20px;">
        <asp:Label ID="editedStatus" runat="server"></asp:Label>
    </div>
        
    
    <p>
        
        &nbsp;</p>
   <script>
        $(document)
            .ready(function() {
                setTimeout(function() {
                        $("[id*=editedStatus]").fadeOut();
                    },
                    3000);
            });
    </script>
</asp:Content>
