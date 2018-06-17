<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Quiz1Result.aspx.cs" Inherits="COMP1551.QuizFiles._5_10.Quiz1Result" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <asp:Label ID="Label1" runat="server" Font-Size="Larger" Text="Congratulations you have completed the Quiz"></asp:Label>
    <br />
    <asp:Label ID="Label2" runat="server" Font-Size="Larger" Text="Your Score is:"></asp:Label>
    <br />
    <br />
    <asp:Label ID="Score" runat="server" Font-Bold="True" Font-Overline="False" Font-Size="XX-Large" Font-Strikeout="False"></asp:Label>
    <br />
    <br />
    <br />
    <br />
    <asp:Label ID="HoF" runat="server" Height="100px" style="margin-left: 0px" Width="520px"></asp:Label>
    <br />
    <br />
    <br />
    <asp:Button ID="back" runat="server" OnClick="back_Click" Text="Quizzes" />
</asp:Content>
