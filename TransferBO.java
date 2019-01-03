import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

class TransferPanel extends JPanel implements ActionListener {

	private JButton TransferButton;
	private JTextField UsernameField, NameField, TransferAmountField;
	private JComboBox SourceCheckingOrSavings, DestinationCheckingOrSavings;
	private String UName, TransferAmount, Name, SourceAccountType, DestinationAccountType;
	private Vector CA_Number = new Vector();
	private Vector SA_Number = new Vector();
	private String CheckingNumber, SavingsNumber;
	private Vector CheckingAccountNumber = new Vector();
	private Vector SavingsAccountNumber = new Vector();
	private Vector CheckingBalance = new Vector();
	private Vector SavingsBalance = new Vector();


	public TransferPanel(String UName, String CustomerName) {

		BankingControl BC = new BankingControl();
		Vector NumberBalance = BC.NumberBalanceInfo(UName);
		CheckingAccountNumber = (Vector) NumberBalance.get(0);
		CheckingBalance = (Vector) NumberBalance.get(1);
		SavingsAccountNumber = (Vector) NumberBalance.get(2);
		SavingsBalance = (Vector) NumberBalance.get(3);
		System.out.println("Checking Account at Transfer BO: " + CheckingAccountNumber);
		System.out.println("Checking Balance at Transfer BO: " + CheckingBalance);
		System.out.println("Savings Account at Transfer BO: " + SavingsAccountNumber);
		System.out.println("Savings Balance at Transfer Bo: " + SavingsBalance);
		System.out.println(CheckingAccountNumber.size());
		System.out.println(CheckingBalance.size());
		System.out.println(SavingsAccountNumber.size());
		System.out.println(SavingsBalance.size());

		TransferButton = new JButton("Transfer");

		SourceCheckingOrSavings = new JComboBox();
		SourceCheckingOrSavings.addItem("Choose Source Account Type");
		for(int i = 0; i < CheckingAccountNumber.size(); i++) {
			String AccountNumber = (String) CheckingAccountNumber.get(i);
			SourceCheckingOrSavings.addItem("Checking..." + AccountNumber.substring(4, 8));
			CA_Number.addElement(new String("Checking..." + AccountNumber.substring(4, 8)));
		}
		for(int j = 0; j < SavingsAccountNumber.size(); j++) {
			String AccountNumber = (String) SavingsAccountNumber.get(j);
			SourceCheckingOrSavings.addItem("Savings..." + AccountNumber.substring(4, 8));
			SA_Number.addElement(new String("Savings..." + AccountNumber.substring(4, 8)));
		}

		DestinationCheckingOrSavings = new JComboBox();
		DestinationCheckingOrSavings.addItem("Choose Destination Account Type");
		for(int i = 0; i < CheckingAccountNumber.size(); i++) {
			String AccountNumber = (String) CheckingAccountNumber.get(i);
			DestinationCheckingOrSavings.addItem("Checking..." + AccountNumber.substring(4, 8));
		}
		for(int j = 0; j < SavingsAccountNumber.size(); j++) {
			String AccountNumber = (String) SavingsAccountNumber.get(j);
			DestinationCheckingOrSavings.addItem("Savings..." + AccountNumber.substring(4, 8));
		}

		UsernameField = new JTextField(15);
		UsernameField.setText(UName);
		NameField = new JTextField(CustomerName);
		TransferAmountField = new JTextField(15);
		TransferAmountField.setText("0.0");

		JLabel NameLabel = new JLabel("Customer Name: ");
		JLabel UsernameLabel = new JLabel("Username: ");
		JLabel TransferAmountLabel = new JLabel("Transfer Amount: ");

		JPanel SourceTypePanel = new JPanel();
		JPanel DestinationTypePanel = new JPanel();
		JPanel NamePanel = new JPanel();
		JPanel UsernamePanel = new JPanel();
		JPanel TransferAmountPanel = new JPanel();

		SourceTypePanel.add(SourceCheckingOrSavings);
		DestinationTypePanel.add(DestinationCheckingOrSavings);
		UsernamePanel.add(UsernameLabel);
		UsernamePanel.add(UsernameField);
		NamePanel.add(NameLabel);
		NamePanel.add(NameField);
		TransferAmountPanel.add(TransferAmountLabel);
		TransferAmountPanel.add(TransferAmountField);

		add(SourceTypePanel);
		add(DestinationTypePanel);
		add(UsernamePanel);
		add(NamePanel);
		add(TransferAmountPanel);
		add(TransferButton);

		TransferButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {

		String arg = evt.getActionCommand();
		if(arg.equals("Transfer")) {
			UName = UsernameField.getText();
			Name = NameField.getText();
			TransferAmount = TransferAmountField.getText();
			SourceAccountType = (String) SourceCheckingOrSavings.getSelectedItem();
			if(SourceAccountType.length() == 15) {
				int CA = CA_Number.indexOf(SourceAccountType);
				System.out.println(CA);
				CheckingNumber = (String) CheckingAccountNumber.get(CA);
			} else {
				int SA = SA_Number.indexOf(SourceAccountType);
				SavingsNumber = (String) SavingsAccountNumber.get(SA);
			}
			DestinationAccountType = (String) DestinationCheckingOrSavings.getSelectedItem();
			if(DestinationAccountType.length() == 15) {
				int CA = CA_Number.indexOf(DestinationAccountType);
				CheckingNumber = (String) CheckingAccountNumber.get(CA);
			} else {
				int SA = SA_Number.indexOf(DestinationAccountType);
				SavingsNumber = (String) SavingsAccountNumber.get(SA);
			}
			if(SourceAccountType.equals("Choose Account Type") || DestinationAccountType.equals("Choose Account Type"))
				JOptionPane.showMessageDialog(null, "Please Choose Source or Destination Account Type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			else if (TransferAmount.equals("0.0"))
				JOptionPane.showMessageDialog(null, "Please Provide Amount greater then Zero!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			else {
				TransferControl TC = new TransferControl(SourceAccountType, DestinationAccountType, Name, UName, TransferAmount, SavingsNumber, CheckingNumber);
			}
		}
	}
}

public class TransferBO extends JFrame {

	private TransferPanel TPanel;

	public TransferBO(String UName, String CustomerName) {

		setTitle("Transfer Money");
		setSize(350, 260);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int screenHeight = d.height;
		int screenWidth = d.width;
		setLocation(screenWidth / 3, screenHeight / 4);

		addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();
		TPanel = new TransferPanel(UName, CustomerName);
		contentPane.add(TPanel);
		show();
	}

}
