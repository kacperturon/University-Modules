<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Quiz3.aspx.cs" Inherits="COMP1551.QuizFiles._5_10.Quiz3" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <h1><u>Science Quiz</u></h1>
    <br />
    <h3>Question 1</h3>
    <asp:TextBox ID="q1q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q1o1" runat="server" OnCheckedChanged="q1o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q1o2" runat="server" OnCheckedChanged="q1o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q1a1" runat="server" OnCheckedChanged="q1o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q1o3" runat="server" OnCheckedChanged="q1a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 2</h3>
    <asp:TextBox ID="q2q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q2o1" runat="server" OnCheckedChanged="q2o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q2o2" runat="server" OnCheckedChanged="q2o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q2a1" runat="server" OnCheckedChanged="q2o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q2o3" runat="server" OnCheckedChanged="q2a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 3</h3>
    <asp:TextBox ID="q3q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q3o1" runat="server" OnCheckedChanged="q3o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q3o2" runat="server" OnCheckedChanged="q3o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q3a1" runat="server" OnCheckedChanged="q3o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q3o3" runat="server" OnCheckedChanged="q3a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 4</h3>
    <asp:TextBox ID="q4q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q4a1" runat="server" OnCheckedChanged="q4o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q4o2" runat="server" OnCheckedChanged="q4o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q4o3" runat="server" OnCheckedChanged="q4o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q4o1" runat="server" OnCheckedChanged="q4a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 5</h3>
    <asp:TextBox ID="q5q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q5a1" runat="server" OnCheckedChanged="q5o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q5o2" runat="server" OnCheckedChanged="q5o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q5o3" runat="server" OnCheckedChanged="q5o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q5o1" runat="server" OnCheckedChanged="q5a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 6</h3>
    <asp:TextBox ID="q6q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q6o1" runat="server" OnCheckedChanged="q6o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q6a1" runat="server" OnCheckedChanged="q6o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q6o3" runat="server" OnCheckedChanged="q6o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q6o2" runat="server" OnCheckedChanged="q6a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 7</h3>
    <asp:TextBox ID="q7q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q7o1" runat="server" OnCheckedChanged="q7o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q7o2" runat="server" OnCheckedChanged="q7o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q7o3" runat="server" OnCheckedChanged="q7o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q7a1" runat="server" OnCheckedChanged="q7a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 8</h3>
    <asp:TextBox ID="q8q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q8o1" runat="server" OnCheckedChanged="q8o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q8o2" runat="server" OnCheckedChanged="q8o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q8o3" runat="server" OnCheckedChanged="q8o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q8a1" runat="server" OnCheckedChanged="q8a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 9</h3>
    <asp:TextBox ID="q9q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q9a1" runat="server" OnCheckedChanged="q9o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q9o2" runat="server" OnCheckedChanged="q9o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q9o3" runat="server" OnCheckedChanged="q9o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q9o1" runat="server" OnCheckedChanged="q9a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <h3>Question 10</h3>
    <asp:TextBox ID="q10q1" runat="server" Height="60px" ReadOnly="True" Width="500px" CssClass="text-center" TextMode="MultiLine"></asp:TextBox>
    <br />
    <br />
    <asp:CheckBox ID="q10o1" runat="server" OnCheckedChanged="q10o1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q10o2" runat="server" OnCheckedChanged="q10o2_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q10o3" runat="server" OnCheckedChanged="q10o3_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:CheckBox ID="q10a1" runat="server" OnCheckedChanged="q10a1_CheckedChanged" AutoPostBack="true"/>
    <br />
    <br />
    <asp:Button ID="Finish" runat="server" Text="Finish" OnClick="Finish_Click" />
    <br />

</asp:Content>
