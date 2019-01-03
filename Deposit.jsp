<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>

<% String Username = request.getParameter("UserID"); %>
<% String CustomerName = request.getParameter("CustName"); %>
	
<HTML>
	<HEAD>
		<title> Deposit Money | MoneyExchange </title>
	</HEAD>
			
	<script type = "text/javascript">
		document.DepositForm.UserID.focus();
		function checkInputs() {
			var Prompts = "";
			UserID = window.document.DepositForm.UserID.value;
			ToAccount = window.document.DepositForm.ToAccount.value;
			DepositAmount = window.document.DepositForm.MoneyField.value;
			if (UserID == "" || ToAccount == "" || DepositAmount == "") {
				if (UserID == "")
				     	Prompts +="Please enter your userID!\n";
				if (ToAccount == "")
				     	Prompts +="Please select your Account Type!\n";
				if (DepositAmount == "")
				        Prompts +="Please enter your Deposit Amount!\n";
				if (Prompts != "")
				        window.alert(Prompts);
			} else
				document.DepositForm.submit();
   		}
	</script>   				
	
	<BODY>
		<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Account Overview </A>
		<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Open Bank Account </A>
		<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Transfer </A>
		<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Deposit </A>
		<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Withdraw </A>
		<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Search Transection </A>
		
		<%
			String UName, TransferAmount, Name, SourceAccountType, DestinationAccountType;
			Vector CA_Number = new Vector();
			Vector SA_Number = new Vector();
			String CheckingNumber, SavingsNumber;
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
		
		<FORM NAME="DepositForm" action = "DepositPost.jsp" method = "GET">
			<INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
			<INPUT TYPE='hidden' NAME='CustName' VALUE='<%=request.getParameter("CustName")%>'>
				<TABLE cellPadding=3 ALIGN='center'>
					<tr> <td> <h2> <% out.println("Deposit Money | MoneyExchange"); %> </h2> </td> </tr>
					<TR bgcolor='#ECFAEB'>
						<TD>To Account:</TD>
        		        		<TD>
        		        	    		<select size="1" name="SourceAccount">
        		        	    			<option value="SelectAccountType"> Select Source Account </option>
        		        	    			<%
								for(int i = 0; i < CheckingAccountNumber.size(); i++) {
									String AccountNumber = (String) CheckingAccountNumber.get(i);
									String SourceCheck = "Checking..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= SourceCheck %>><%= SourceCheck %></option>
								<%
									CA_Number.addElement(new String("Checking..." + AccountNumber.substring(4, 8)));
								}
								for(int j = 0; j < SavingsAccountNumber.size(); j++) {
									String AccountNumber = (String) SavingsAccountNumber.get(j);
									String SourceSave = "Savings..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= SourceSave %>><%= SourceSave %></option>
								<%
									SA_Number.addElement(new String("Savings..." + AccountNumber.substring(4, 8)));
								}
								%>
							</select>
						</td>
					</tr>
        		    		<TR bgcolor = '#F1F1FD'>
        		    			<TD> Deposit Money: </td>
        		    			<td>
        		    				<input type='text' name='MoneyField' value='' size='15'>
        		    			<td>
        		    		</tr>
        		  	</TABLE><BR>
        		  	<INPUT TYPE='hidden' NAME='CA_Number' VALUE=<%= CA_Number %>>
				<INPUT TYPE='hidden' NAME='SA_Number' VALUE='<%= SA_Number %>'>
				<CENTER><INPUT TYPE="SUBMIT" NAME='submitBNTN' VALUE='Deposit' onClick="checkInputs()"></CENTER><BR>
			</FORM>
		</BODY>
	</HTML>