<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>

<% String Username = request.getParameter("UserID"); %>
<% String CustomerName = request.getParameter("CustName"); %>
	
<HTML>
	<HEAD>
		<title> Transfer Money | MoneyExchange </title>
	</HEAD>
			
	<script type = "text/javascript">
		document.TransferForm.UserID.focus();
		function checkInputs() {
			var Prompts = "";
			UserID = window.document.TransferForm.UserID.value;
			SourceAccount = window.document.TransferForm.SourceAccount.value;
			DestinationAccount = window.document.TransferForm.DestinationAccount.value;
			TransferAmount = window.document.TransferForm.MoneyField.value;
			if (UserID == "" || SourceAccount == "" || DestinationAccount == "" || TransferAmount == "") {
				if (UserID == "")
				     	Prompts +="Please enter your userID!\n";
				if (SourceAccount == "")
				     	Prompts +="Please select your Source Account Type!\n";
				if (DestinationAccount == "")
					Prompts +="Please select your Destination Account Type!\n";
				if (TransferAmount == "")
				        Prompts +="Please enter your Transfer Amount!\n";
				if (Prompts != "")
				        window.alert(Prompts);
			} else
				document.TransferForm.submit();
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
			Banking BK = new Banking();
			Vector NumberBalance = BK.NumberBalanceInfo(Username);
			Vector CheckingAccountNumber = (Vector) NumberBalance.get(0);
			Vector CheckingBalance = (Vector) NumberBalance.get(1);
			Vector SavingsAccountNumber = (Vector) NumberBalance.get(2);
			Vector SavingsBalance = (Vector) NumberBalance.get(3);
		%>
		
		<FORM NAME="TransferForm" action = "TransferPost.jsp" method = "GET">
			<INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
			<INPUT TYPE='hidden' NAME='CustName' VALUE='<%=request.getParameter("CustName")%>'>
				<TABLE cellPadding=3 ALIGN='center'>
					<tr> <td> <h2> <% out.println("Transfer Money | MoneyExchange"); %> </h2> </td> </tr>
					<TR bgcolor='#ECFAEB'>
						<TD>Source Account:</TD>
        		        		<TD>
        		        	    		<select size="1" name="SourceAccount">
        		        	    			<option value="SelectSourceAccount"> Select Source Account </option>
        		        	    			<%
								for(int i = 0; i < CheckingAccountNumber.size(); i++) {
									String AccountNumber = (String) CheckingAccountNumber.get(i);
									String SourceCheck = "Checking..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= SourceCheck %>><%= SourceCheck %></option>
								<%
								}
								for(int j = 0; j < SavingsAccountNumber.size(); j++) {
									String AccountNumber = (String) SavingsAccountNumber.get(j);
									String SourceSave = "Savings..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= SourceSave %>><%= SourceSave %></option>
								<%
								}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td>Destination Account:</td>
						<td>
							<select size="1" name="DestinationAccount">
								<option value="SelectDestinationAccount"> Select Destination Account </option>
								<%
								for(int i = 0; i < CheckingAccountNumber.size(); i++) {
									String AccountNumber = (String) CheckingAccountNumber.get(i);
									String DestiCheck = "Checking..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= DestiCheck %>><%= DestiCheck %></option>
								<%
								}
								for(int j = 0; j < SavingsAccountNumber.size(); j++) {
									String AccountNumber = (String) SavingsAccountNumber.get(j);
									String DS = "Savings..." + AccountNumber.substring(4, 8);
								%>
									<option value=<%= DS %>><%= DS %></option>
								<% } %>   
  			        	    		</select>
        		        		</TD>
        		    		</TR>
        		    		<TR bgcolor = '#F1F1FD'>
        		    			<TD> Transfer Money: </td>
        		    			<td>
        		    				<input type='text' name='MoneyField' value='' size='15'>
        		    			<td>
        		    		</tr>
        		  	</TABLE><BR>
				<CENTER><INPUT TYPE="SUBMIT" NAME='submitBNTN' VALUE='Transfer' onClick="checkInputs()"></CENTER><BR>
			</FORM>
		</BODY>
	</HTML>

	