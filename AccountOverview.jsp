<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>
<%@ page import="java.util.Vector;" %>
<%@ page import="java.awt.*;" %>
<%@ page import="java.awt.event.*;" %>
<%@ page import="javax.swing.*;" %>
<%@ page import="javax.swing.JPanel;" %>

<% String Username = request.getParameter("UserID"); %>
<% String CustomerName = request.getParameter("CustName"); %>

<%
	Banking BK = new Banking();
	Vector NumberBalance = BK.NumberBalanceInfo(Username);
	Vector CheckingAccountNumber = (Vector) NumberBalance.get(0);
	Vector CheckingBalance = (Vector) NumberBalance.get(1);
	Vector SavingsAccountNumber = (Vector) NumberBalance.get(2);
	Vector SavingsBalance = (Vector) NumberBalance.get(3);
	int c = CheckingAccountNumber.size();
	int s = SavingsAccountNumber.size();
	int k = CheckingAccountNumber.size() + SavingsAccountNumber.size();
	Vector data = new Vector();
%>

<HTML>
	<HEAD>
	</head>
	
	<body>
		<h1> <% out.println("Account Overview for " + Username); %> </h1>
		<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Account Overview </A>
		<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Open Bank Account </A>
		<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Transfer </A>
		<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Deposit </A>
		<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Withdraw </A>
		<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Search Transection </A>
		
		<% 
		Vector column = new Vector();
		column.add(0, "Account Type");
		column.add(1, "Account Number");
		column.add(2, "Balance");

		for(int i = 0; i < (CheckingAccountNumber.size() + SavingsAccountNumber.size()); i++) {
			Vector info = new Vector();
				if(i < CheckingAccountNumber.size()) {
					info.add(0, "Checking");
					info.add(1, CheckingAccountNumber.get(i));
					info.add(2, CheckingBalance.get(i));
				} else {
					info.add(0, "Savings");
					info.add(1, SavingsAccountNumber.get(i - CheckingAccountNumber.size()));
					info.add(2, SavingsBalance.get(i - CheckingAccountNumber.size()));
				}
			data.add(i, info);
		}
		
		%>
		
		</br>
		<table>
			<tr>
				<td> <h4> Account Type </h4> </td>
				<td> <h4> Account Number </h4> </td>
				<td> <h4> Balance </h4> </td>
			<tr>
		
		<% for(int i = 0; i < k; i++) { 
			Vector v = (Vector) data.get(i); %>
			
			<tr>
				<td> <% out.println(v.get(0)); %> </td>
				<td> <% out.println(v.get(1)); %> </td>
				<td> <% out.println(v.get(2)); %> </td>
			</tr>
			
		<% } %>
				
	</body>
</html>