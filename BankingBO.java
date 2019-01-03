import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

class BankingPanel extends JPanel {
	public BankingPanel(String UName, String CustName) {

		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(600, 400));
		tabPanel_1 = new TransferPanel(UName, CustName);
		tabPanel_2 = new OpenBankAccountPanel(UName, CustName);
		tabPanel_3 = new OverviewPanel(UName, CustName);
		tabPanel_4 = new TransactionSearchPanel(UName, CustName);
		tabPanel_5 = new WithdrawPanel(UName, CustName);
		tabPanel_6 = new DepositPanel(UName, CustName);
		tabbedPane.addTab("Account Overview", tabPanel_3);
		tabbedPane.setSelectedIndex(0);
		tabbedPane.addTab("Open Bank Account", tabPanel_2);
		tabbedPane.addTab("Withdraw", tabPanel_5);
		tabbedPane.addTab("Deposit", tabPanel_6);
		tabbedPane.addTab("Transfer Money", tabPanel_1);
		tabbedPane.addTab("Search Transaction", tabPanel_4);
		add(tabbedPane);
	}
	private JTabbedPane tabbedPane;
	private JPanel tabPanel_1, tabPanel_2, tabPanel_3, tabPanel_4, tabPanel_5, tabPanel_6;
}

public class BankingBO extends JFrame {
	JMenu Banking, Help, Payment;
	JMenuItem AccountOverview, PayBills, Transfer, Deposit, Withdraw, OpenAccount, CustomerService, TransectionHistory;
	public BankingBO(String UName, String CustName) {
		setTitle("Banking System");
		setSize(700, 500);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setLocation(screenWidth/3, screenHeight/4);

		addWindowListener (new WindowAdapter() { //handle window event
			public void windowClosing (WindowEvent e) {
				System.exit(0);
		    }
		});

		JMenuBar mb = new JMenuBar();
		Banking = new JMenu("Banking");
		Help = new JMenu("Help");
		Payment = new JMenu("Payment");
		AccountOverview = new JMenuItem("Account Overview");
		PayBills = new JMenuItem("Pay Bills");
		Transfer = new JMenuItem("Transfer");
		Deposit = new JMenuItem("Deposit");
		Withdraw = new JMenuItem("Withdraw");
		OpenAccount = new JMenuItem("Open Account");
		CustomerService = new JMenuItem("Customer Service");
		TransectionHistory = new JMenuItem("Transection History");
		Banking.add(AccountOverview);
		Banking.add(TransectionHistory);
		Banking.addSeparator();
		Banking.add(OpenAccount);
		Banking.addSeparator();
		Payment.add(PayBills);
		Payment.addSeparator();
		Payment.add(Transfer);
		Payment.add(Deposit);
		Payment.add(Withdraw);
		Banking.add(Payment);
		Help.add(CustomerService);
		mb.add(Banking);
		mb.add(Help);
		setJMenuBar(mb);

		//setLayout(null);
		setVisible(true);

		JPanel tabbedPanel = new BankingPanel(UName, CustName);
		Container contentPane = getContentPane(); //add a panel to a frame
		//BankingPanel BP = new BankingPanel(UName, CustName, CheckingAccountNo, SavingsAccountNo);
		contentPane.add(tabbedPanel);
        show();
	}
}