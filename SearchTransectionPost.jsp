<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.shah.*;" %>

<%!	String StartDate, EndDate;
	String UName;
	Vector TransInfo = new Vector();
	Vector column = new Vector();
%>

<% UName = request.getParameter("UserID"); %>
<% String CustomerName = request.getParameter("CustName"); %>
	<HTML>
		<HEAD>
			<title> Search Transection | MoneyExchange </title>
		</HEAD>
		
		
		<script type = "text/javascript">
			document.SearchTransectionForm.UserID.focus();
			function checkInputs() {
				var Prompts = "";
				UserID = window.document.SearchTransectionForm.UserID.value;
				StartDate = window.document.SearchTransectionForm.StartDate.value;
				EndDate = window.document.SearchTransectionForm.EndDate.value;
				if (UserID == "" || StartDate == "" || EndDate == "") {
					if (UserID == "")
				             	Prompts +="Please enter your userID!\n";
					if (StartDate == "")
				             	Prompts +="Please enter your start date in yyyy-mm-dd format!\n";
				        if (EndDate == "")
				             	Prompts +="Please enter your end date in yyyy-mm-dd format!\n;
				        if (Prompts != "")
				             	window.alert(Prompts);
				} else
				        document.SearchTransectionForm.submit();
   			}
		</script>   				
	
		<BODY>
			<A HREF='/CSCI6810/AccountOverview.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Account Overview </A>
			<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Open Bank Account </A>
			<A HREF='/CSCI6810/Transfer.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Transfer </A>
			<A HREF='/CSCI6810/Deposit.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Deposit </A>
			<A HREF='/CSCI6810/Withdraw.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Withdraw </A>
			<A HREF='/CSCI6810/SearchTransection.jsp?UserID=<%= UName %>&CustName=<%= CustomerName %>'> Search Transection </A>
			<FORM NAME="SearchTransectionForm" action = "SearchTransection.jsp" method = "GET">
				<INPUT TYPE='hidden' NAME='UserID' VALUE='<%=request.getParameter("UserID")%>'>
				<INPUT TYPE='hidden' NAME='CustName' VALUE='<%=request.getParameter("CustName")%>'>
				<TABLE cellPadding=3 ALIGN='center'>
					<TR> <td> <h4> Please enter start date and end date in yyyy-mm-dd format. </h4> </td> </tr>
					<TR bgcolor='#ECFAEB'>
						<TD>Start Date:</TD>
        		        		<TD>
        		        			<INPUT TYPE='text' NAME='StartDate' VALUE='1993-01-01'>
        		        		</TD>
        		    		</TR>
        		    		<TR bgcolor='#F1F1FD'>
        		        		<TD>End Date:</TD>
        		        		<TD>
        		        	   		<INPUT TYPE='text' NAME='EndDate' Value='2050-12-31'>
						</TD>
        		    		</TR>
        		  	</TABLE><BR>
				<CENTER><INPUT TYPE="SUBMIT" NAME='submitBNTN' VALUE='Search Transection' onClick="checkInputs()"></CENTER><BR>
			</FORM>
			
			<% StartDate = request.getParameter("StartDate"); %>
			<% EndDate = request.getParameter("EndDate"); %>
			
			<% if (!StartDate.equals("") && !EndDate.equals("")) {
            			Transaction TZ = new Transaction(StartDate, EndDate);
            			TransInfo = TZ.SearchTransection(UName);
            		}
            		%>
			
			<table>
				<tr>
					<td> <h4> Transaction Number </h4> </td>
					<td> <h4> Transaction Amount </h4> </td>
					<td> <h4> Transaction Type </h4> </td>
					<td> <h4> Transaction Time </h4> <td>
					<td> <h4> Transaction Date </h4> <td>
					<td> <h4> From Account </h4> <td>
					<td> <h4> To Account </h4> <td>
				<tr>
			<% for(int i = 0; i < TransInfo.size(); i++) { 
				Vector v = (Vector) TransInfo.get(i);%>
				<tr>
					<td> <% out.println(v.get(0)); %> </td>
					<td> <% out.println(v.get(1)); %> </td>
					<td> <% out.println(v.get(2)); %> </td>
					<td> <% out.println(v.get(3)); %> </td>
					<td> <% out.println(v.get(4)); %> </td>
					<td> <% out.println(v.get(5)); %> </td>
					<td> <% out.println(v.get(6)); %> </td>
				<tr>
			<% } %>
			</table>
				
				
		</BODY>
	</HTML>