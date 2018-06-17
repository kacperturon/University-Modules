<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Quizzes.aspx.cs" Inherits="COMP1551.Quizzes" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h1><u>Quizzes</u></h1>
    <h3>Ages 5-10</h3>
    <asp:ImageButton ID="ImageButton1" runat="server" Height="200px" ImageUrl="~/Images/Quiz1.png" OnClick="ImageButton1_Click" Width="300px" BorderColor="Black" BorderStyle="Solid" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:ImageButton ID="ImageButton2" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz2.png" Width="300px" OnClick="ImageButton2_Click" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:ImageButton ID="ImageButton3" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz3.png" Width="300px" OnClick="ImageButton3_Click" />
    <br />
    <h3>Ages 11-13</h3>
    <asp:ImageButton ID="ImageButton4" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz1.png" Width="300px" OnClick="ImageButton4_Click" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:ImageButton ID="ImageButton5" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz2.png" Width="300px" OnClick="ImageButton5_Click" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:ImageButton ID="ImageButton6" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz3.png" Width="300px" OnClick="ImageButton6_Click" />
    <br />
    <h3>Ages 14-16</h3>
    <p>
        <asp:ImageButton ID="ImageButton7" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz1.png" Width="300px" OnClick="ImageButton7_Click" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:ImageButton ID="ImageButton8" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz2.png" Width="300px" OnClick="ImageButton8_Click" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <asp:ImageButton ID="ImageButton9" runat="server" BorderColor="Black" BorderStyle="Solid" Height="200px" ImageUrl="~/Images/Quiz3.png" Width="300px" OnClick="ImageButton9_Click" />
    </p>
    </asp:Content>
