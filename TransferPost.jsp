<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>
<%@ page import="java.util.Vector;" %>
<%@ page import="java.awt.*;" %>
<%@ page import="java.awt.event.*;" %>
<%@ page import="javax.swing.*;" %>
<%@ page import="javax.swing.JPanel;" %>

<%! String Name, UName, SourceAccountType, DestinationAccountType, TransferAmount, CheckingNumber, SavingsNumber; %>

<% Name = request.getParameter("CustName"); %>
<% UName = request.getParameter("UserID"); %>
<% SourceAccountType = request.getParameter("SourceAccount"); %>
<% DestinationAccountType = request.getParameter("DestinationAccount"); %>
<% TransferAmount = request.getParameter("MoneyField"); %>

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
	
	SourceAccountType = request.getParameter("SourceAccount");
	if(SourceAccountType.length() == 15) {
		int CA = CA_Number.indexOf(SourceAccountType);
		System.out.println(CA);
		CheckingNumber = (String) CheckingAccountNumber.get(CA);
	} else {
		int SA = SA_Number.indexOf(SourceAccountType);
		SavingsNumber = (String) SavingsAccountNumber.get(SA);
	}
	DestinationAccountType = request.getParameter("DestinationAccount");
	if(DestinationAccountType.length() == 15) {
		int CA = CA_Number.indexOf(DestinationAccountType);
		CheckingNumber = (String) CheckingAccountNumber.get(CA);
	} else {
		int SA = SA_Number.indexOf(DestinationAccountType);
		SavingsNumber = (String) SavingsAccountNumber.get(SA);
	}
	if(SourceAccountType.equals("Choose Account Type") || DestinationAccountType.equals("Choose Account Type"))
		out.println("Please Choose Source or Destination Account Type!");
	else if (TransferAmount.equals("0.0"))
		out.println("Please Provide Amount greater then Zero!");
	else {
		if (SourceAccountType.equals("Checking..." + CheckingNumber.substring(4, 8)) && DestinationAccountType.equals("Savings..." + SavingsNumber.substring(4, 8))) {
			CheckingAccount CA = new CheckingAccount(CheckingNumber, UName, TransferAmount);
		        if(CA.withdraw()) {
				SavingAccount SA = new SavingAccount(SavingsNumber, UName, TransferAmount);
				if(SA.deposit()) {
					Transaction TA = new Transaction(SourceAccountType, DestinationAccountType, UName, TransferAmount);
					if(TA.recordTransaction())
						out.println("Transfer is Successful!");
					else
						out.println("Transfer is Unsuccessful!");
				} else
					out.println("Deposit is Unsuccessful!");
			} else
				out.println("Withdraw is Unsuccessful due to less Account Balance!");
		} else if (SourceAccountType.equals("Savings..." + SavingsNumber.substring(4, 8)) && DestinationAccountType.equals("Checking..." + CheckingNumber.substring(4, 8))) {
			SavingAccount SA = new SavingAccount(SavingsNumber, UName, TransferAmount);
			if(SA.withdraw()) {
				CheckingAccount CA = new CheckingAccount(CheckingNumber, UName, TransferAmount);
				if(CA.deposit()) {
					Transaction TA = new Transaction(SourceAccountType, DestinationAccountType, UName, TransferAmount);
					if(TA.recordTransaction())
						out.println("Transfer is Successful!");
					else
						out.println("Transfer is Unsuccessful!");
				} else
					out.println("Deposit is Unsuccessful!");
			} else
				out.println("Withdraw is Unsuccessful due to less Account Balance!");
		} else
			out.println("Please select Source Account as Checking/Saving and select Destination Account as Saving/Checking!");
	}
%>