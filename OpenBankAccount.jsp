<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>

<% String Username = request.getParameter("UserID"); %>
<% String CustomerName = request.getParameter("CustName"); %>
	<HTML>
		<HEAD>
			<title> Open Bank Account | MoneyExchange </title>
		</HEAD>
			
		<script type = "text/javascript">
			document.OpenBankAccountForm.UserID.focus();
			function checkInputs() {
				var Prompts = "";
				UserID = window.document.OpenBankAccountForm.UserID.value;
				ToAccount = window.document.OpenBankAccountForm.CheckingOrSavings.value;
				AccountNumber = window.document.OpenBankAccountForm.AccountNumberField.value;
				AccountAmount = window.document.OpenBankAccountForm.MoneyField.value;
				if (UserID == "" || ToAccount == "" || AccountNumber == "" || AccountAmount == "") {
					if (UserID == "")
				             	Prompts +="Please enter your userID!\n";
					if (ToAccount == "")
				             	Prompts +="Please select your Account Type!\n";
				        if (AccountNumber == "")
				             	Prompts +="Please enter your Account Number!\n";
				        if (AccountAmount == "")
				             	Prompts +="Please enter your Account Amount!\n";
				        if (Prompts != "")
				             	window.alert(Prompts);
				} else
				        document.OpenBankAccountForm.submit();
   			}
		</script>   				
	
		<BODY>
			<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Account Overview </A>
			<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Open Bank Account </A>
			<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Transfer </A>
			<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Deposit </A>
			<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Withdraw </A>
			<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= Username %>&CustName=<%= CustomerName %>'> Search Transection </A>
			<FORM NAME="OpenBankAccountForm" action = "OpenBankAccountPost.jsp" method = "GET">
				<INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
				<INPUT TYPE='hidden' NAME='CustName' VALUE='<%=request.getParameter("CustName")%>'>
				<TABLE cellPadding=3 ALIGN='center'>
					<tr> <td> <h3> <% out.println("Create new account for " + CustomerName); %> </h3> </td> </tr>
					<TR> <td> <h4> Select the Checking or Saving Account. </h4> </td> </tr>
					<TR bgcolor='#ECFAEB'>
						<TD>Account Type:</TD>
        		        		<TD>
        		        	    		<select size="1" name="CheckingOrSavings">
					    			<option selected value="Checking">Checking</option>
					    			<option value="Savings">Savings</option>    
  			        	    		</select>
        		        		</TD>
        		    		</TR>
        		    		<TR bgcolor='#F1F1FD'>
        		        		<TD>Type Account Number:</TD>
        		        		<TD>
        		        	   		<INPUT TYPE='text' NAME='AccountNumberField' Value='' SIZE='15'>
						</TD>
        		    		</TR>
        		    		<TR bgcolor = '#F1F1FD'>
        		    			<TD> Please provide initial money to deposit: </td>
        		    			<td>
        		    				<input type='text' name='MoneyField' value='' size='15'>
        		    			<td>
        		    		</tr>
        		  	</TABLE><BR>
				<CENTER><INPUT TYPE="SUBMIT" NAME='submitBNTN' VALUE='Open Account' onClick="checkInputs()"></CENTER><BR>
			</FORM>
		</BODY>
	</HTML>

	