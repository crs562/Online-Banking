<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>
<%@ page import="java.util.Vector;" %>
<%@ page import="java.awt.*;" %>
<%@ page import="java.awt.event.*;" %>
<%@ page import="javax.swing.*;" %>
<%@ page import="javax.swing.JPanel;" %>

<% String CustomerName = request.getParameter("CustName"); %>
<% String Username = request.getParameter("UserID"); %>
<% String ToAccount = request.getParameter("CheckingOrSavings"); %>
<% String AcountNumber = request.getParameter("AccountNumberField"); %>
<% String Balance = request.getParameter("MoneyField"); %>

<html>
	<head> </head>
	
	<body>
	
	<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Account Overview </A>
	<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Open Bank Account </A>
	<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Transfer </A>
	<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Deposit </A>
	<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Withdraw </A>
	<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Search Transection </A>
		
		
<%
		
	String FromAccount = "";
			
	if (ToAccount.equals("Checking")) {
		CheckingAccount CA = new CheckingAccount(AcountNumber, CustomerName, Username, Balance);
		if (CA.openAcct()) {
			out.println("Opening a Checking Account is Successful!");
			Transaction TZ = new Transaction(ToAccount, FromAccount, CustomerName, Username, Balance);
			if (TZ.recordTransaction())
				out.println("Transaction Recorded Successful!");
			else
				out.println("Transaction Fail!");
		} else
			out.println("Opening a Checking Account failed.");
	} else {
		SavingAccount SA = new SavingAccount(AcountNumber, CustomerName, Username, Balance);
		if (SA.openAcct()) {
			out.println("Opening a Saving Account is Successful!");
			Transaction TZ = new Transaction(ToAccount, FromAccount, CustomerName, Username, Balance);
			if (TZ.recordTransaction())
				out.println("Transaction Recorded Successful!");
			else
				out.println("Transaction Fail!");
		} else
			out.println("Opening a Saving Account failed.");
	}
			
%>

	</body>
</html>