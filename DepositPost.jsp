<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>
<%@ page import="java.util.Vector;" %>
<%@ page import="java.awt.*;" %>
<%@ page import="java.awt.event.*;" %>
<%@ page import="javax.swing.*;" %>
<%@ page import="javax.swing.JPanel;" %>

<%! String Name, UName, ToAccount, FromAccount, Balance, CheckingNumber, SavingsNumber, AccountNumber; %>

<% Name = request.getParameter("CustName"); %>
<% UName = request.getParameter("UserID"); %>
<% ToAccount = request.getParameter("SourceAccount"); %>
<% FromAccount = ""; %>
<% Balance = request.getParameter("MoneyField"); %>

<% 	Vector CA_Number = new Vector();
	Vector SA_Number = new Vector();
	Banking BK = new Banking();
	Vector NumberBalance = BK.NumberBalanceInfo(UName);
	Vector CheckingAccountNumber = (Vector) NumberBalance.get(0);
	Vector CheckingBalance = (Vector) NumberBalance.get(1);
	Vector SavingsAccountNumber = (Vector) NumberBalance.get(2);
	Vector SavingsBalance = (Vector) NumberBalance.get(3);
	int c = CheckingAccountNumber.size();
	int s = SavingsAccountNumber.size();
	int k = CheckingAccountNumber.size() + SavingsAccountNumber.size();
	Vector data = new Vector();
%>
	<html>
		<head> </head>
		<body>
			<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Account Overview </A>
			<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Open Bank Account </A>
			<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Transfer </A>
			<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Deposit </A>
			<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Withdraw </A>
			<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= UName %>&CustName=<%= Name %>'> Search Transection </A>
			</br>
		</body>
	</html>
<%
	for(int i = 0; i < CheckingAccountNumber.size(); i++) {
		String AccountNumber = (String) CheckingAccountNumber.get(i);
		CA_Number.addElement(new String("Checking..." + AccountNumber.substring(4, 8)));
	}
	for(int j = 0; j < SavingsAccountNumber.size(); j++) {
		String AccountNumber = (String) SavingsAccountNumber.get(j);
		SA_Number.addElement(new String("Savings..." + AccountNumber.substring(4, 8)));
	}
	
	if(ToAccount.length() == 15) {
		int CA = CA_Number.indexOf(ToAccount);
		AccountNumber = (String) CheckingAccountNumber.get(CA);
	} else {
		int SA = SA_Number.indexOf(ToAccount);
		AccountNumber = (String) SavingsAccountNumber.get(SA);
	}
	if (ToAccount.equals("Choose Account Type"))
		out.println("Please Choose an Account Type!");
	else {
		if (ToAccount.equals("Checking..."+ AccountNumber.substring(4, 8))){
			CheckingAccount CA = new CheckingAccount(AccountNumber, UName, Balance);
			if(CA.deposit()) {
				Transaction TZ = new Transaction(ToAccount, FromAccount, Name, UName, Balance);
				if(TZ.recordTransaction())
					out.println("Deposit Transaction successful in Checking!");
				else
					out.println("Deposit Transaction Failed in Checking!");
			} else
				out.println("Deposit failed in Checking.");
		}else if (ToAccount.equals("Savings..."+ AccountNumber.substring(4, 8))){
			SavingAccount SA = new SavingAccount(AccountNumber, UName, Balance);
			if(SA.deposit()) {
				Transaction TZ = new Transaction(ToAccount, FromAccount, Name, UName, Balance);
				if(TZ.recordTransaction())
					out.println("Deposit Transaction successful in Saving!");
				else
					out.println("Deposit Transaction Failed in Saving!");
			} else
				out.println("Deposit failed in Saving.");
		} else
			out.println("Please Choose an Account Type!");
	}
%>