import java.io.*;
import javax.servlet.*;  //package for GenericServlet
import javax.servlet.http.*;  //package for HttpServlet
import java.util.*;
import com.shah.*;

public class LoginServlet extends HttpServlet {
	private String Username, Password;
   	private PrintWriter output;
   	private String CustomerName;
   	private Vector NumberBalance;
	private Vector CheckingAccountNumber;
	private Vector CheckingBalance;
	private Vector SavingsAccountNumber;
	private Vector SavingsBalance;
	private int c, s, k;
   	public void init(ServletConfig config) throws ServletException {
    	super.init( config );
      	Username = new String("");
      	Password = new String("");
	}

	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
   	}

	public void doPost (HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
      	output = res.getWriter();
      	res.setContentType("text/html");
      	Username = req.getParameter( "UsernameField" );
      	Password = req.getParameter( "PasswordField" );
      	Account Acct = new Account(Username, Password);
      	CustomerName = Acct.signIn();
      	Banking BK = new Banking();
	  	NumberBalance = BK.NumberBalanceInfo(Username);
	  	CheckingAccountNumber = (Vector) NumberBalance.get(0);
	  	CheckingBalance = (Vector) NumberBalance.get(1);
	  	SavingsAccountNumber = (Vector) NumberBalance.get(2);
		SavingsBalance = (Vector) NumberBalance.get(3);
		c = CheckingAccountNumber.size();
		s = SavingsAccountNumber.size();
		k = CheckingAccountNumber.size() + SavingsAccountNumber.size();
      	if (CustomerName != "")
        	showSuccess();
      	else
           	output.println("Account creation failed because of invalid username or Password. Please try again!");
   	}

   	public void showSuccess() {
    	StringBuffer Buf = new StringBuffer();
    	Buf.append("<HTML>\n");
			Buf.append("<HEAD>\n");
				Buf.append("<Title> MoneyExchange </Title>\n");
			Buf.append("</head>\n");
			Buf.append("<body>\n");
				Buf.append("<h1> "+CustomerName+" is successfully Login. Please select the following Option.</h1>\n");
				Buf.append("<A HREF='/CSCI6810/AccountOverview.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Account Overview </A>");
				Buf.append("<A HREF='/CSCI6810/OpenBankAccount.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Open Bank Account </A>");
				Buf.append("<A HREF='/CSCI6810/Transfer.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Transfer </A>");
				Buf.append("<A HREF='/CSCI6810/Deposit.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Deposit </A>");
				Buf.append("<A HREF='/CSCI6810/Withdraw.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Withdraw </A>");
				Buf.append("<A HREF='/CSCI6810/SearchTransection.jsp?UserID="+Username+"&CustName="+CustomerName+"'> Search Transection </A>");
			Buf.append("</body>\n");
		Buf.append("</html>\n");
		output.println(Buf.toString());
   }

   //this "cleanup" method is called when a servlet is terminated by the server
   public void destroy() {
       output.close();
   }
}