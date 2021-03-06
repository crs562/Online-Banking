
import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import com.shah.*;
import java.util.Vector;

class DepositPanel extends JPanel implements ActionListener
{
	private JButton DepositButton;
	private JTextField UsernameField, NameField, BalanceField;
	private JComboBox CheckingOrSavingsBox;
	private String UName, Name, ToAccount, Balance;
	private String FromAccount = "";
	private String CheckingNumber, SavingsNumber;
	private String AccountNumber;
	private Vector CA_Number = new Vector();
	private Vector SA_Number = new Vector();
	Vector CheckingAccountNumber = new Vector();
	Vector CheckingBalance = new Vector();
	Vector SavingsAccountNumber = new Vector();
	Vector SavingsBalance = new Vector();

	public DepositPanel(String UName, String CustomerName) {

		BankingControl BC = new BankingControl();
		Vector NumberBalance = BC.NumberBalanceInfo(UName);
		CheckingAccountNumber = (Vector) NumberBalance.get(0);
		CheckingBalance = (Vector) NumberBalance.get(1);
		SavingsAccountNumber = (Vector) NumberBalance.get(2);
		SavingsBalance = (Vector) NumberBalance.get(3);
		System.out.println(CheckingAccountNumber);
		System.out.println(CheckingBalance);
		System.out.println(SavingsAccountNumber);
		System.out.println(SavingsBalance);

		DepositButton = new JButton("Deposit");

		CheckingOrSavingsBox = new JComboBox();
		CheckingOrSavingsBox.addItem("Select");
		for(int i = 0; i < CheckingAccountNumber.size(); i++) {
			String AccountNumber = (String) CheckingAccountNumber.get(i);
			CheckingOrSavingsBox.addItem("Checking..." + AccountNumber.substring(4, 8));
			CA_Number.addElement(new String("Checking..." + AccountNumber.substring(4, 8)));
		}
		for(int j = 0; j < SavingsAccountNumber.size(); j++) {
			String AccountNumber = (String) SavingsAccountNumber.get(j);
			CheckingOrSavingsBox.addItem("Savings..." + AccountNumber.substring(4, 8));
			SA_Number.addElement(new String("Savings..." + AccountNumber.substring(4, 8)));
		}

		UsernameField = new JTextField(15);
		UsernameField.setText(UName);
		NameField = new JTextField(CustomerName);
		BalanceField = new JTextField(15);
		BalanceField.setText("0.0");

		JLabel TypeLabel= new JLabel("Choose Account Type: ");
		JLabel NameLabel = new JLabel("Customer Name:");
		JLabel UsernameLabel = new JLabel("Username: ");
		JLabel BalanceLabel = new JLabel("Deposit Amount:");


		JPanel TypePanel = new JPanel();
		JPanel UsernamePanel = new JPanel();
		JPanel NamePanel = new JPanel();
		JPanel BalancePanel = new JPanel();

		TypePanel.add(TypeLabel);
		TypePanel.add(CheckingOrSavingsBox);
		UsernamePanel.add(UsernameLabel);
		UsernamePanel.add(UsernameField);
		NamePanel.add(NameLabel);
		NamePanel.add(NameField);
		BalancePanel.add(BalanceLabel);
		BalancePanel.add(BalanceField);

		add(TypePanel);
		add(UsernamePanel);
		add(NamePanel);
		add(BalancePanel);
		add(DepositButton);

		DepositButton.addActionListener(this); //event listener registration
    }
    public void actionPerformed(ActionEvent evt)  //event handling
	    {
	        //Object source = evt.getSource(); //get who generates this event
	        String arg = evt.getActionCommand();
	        if (arg.equals("Deposit")) { //determine which button is clicked
	            UName = UsernameField.getText(); //take actions
	            Name = NameField.getText();
	            Balance = BalanceField.getText();
	            ToAccount = (String)CheckingOrSavingsBox.getSelectedItem();
				if(ToAccount.length() == 15) {
					int CA = CA_Number.indexOf(ToAccount);
					AccountNumber = (String) CheckingAccountNumber.get(CA);
				} else {
					int SA = SA_Number.indexOf(ToAccount);
					AccountNumber = (String) SavingsAccountNumber.get(SA);
				}
	            if (FromAccount.equals("Choose Account Type"))
	                JOptionPane.showMessageDialog(null, "Please Choose an Account Type!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	            else {
						DepositControl DPS_Ctrl = new DepositControl(FromAccount, ToAccount, Name, UName, Balance, AccountNumber);
				}
			}
	}

}

public class DepositBO extends JFrame
{
    private DepositPanel D_Panel;

    public DepositBO(String UName, String CustomerName)
    {
        setTitle("Withdraw");
        setSize(370, 260);

         //get screen size and set the location of the frame
         Toolkit tk = Toolkit.getDefaultToolkit();
         Dimension d = tk.getScreenSize();
         int screenHeight = d.height;
         int screenWidth = d.width;
         setLocation( screenWidth / 3, screenHeight / 4);

         addWindowListener (new WindowAdapter()  //handle window event
            {
		       public void windowClosing (WindowEvent e)
			                  { System.exit(0);
               }
            });

         Container contentPane = getContentPane(); //add a panel to a frame
         D_Panel = new DepositPanel(UName, CustomerName);
         contentPane.add(D_Panel);
         show();
    }

    /*public static void main(String [] args)
    { JFrame frame = new SignUpBO(); //initialize a JFrame object
      frame.show(); //display the frame
    }*/
}
