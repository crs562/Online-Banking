import java.awt.*;     //including Java packages used by this program
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.util.Vector;
import com.shah.*;

class OverviewPanel extends JPanel implements ActionListener {

	private JButton RefreshButton, ShowInterestButton;
	private String UName, CustName;

	public OverviewPanel(String UName, String CustName) {
		this.UName = UName;
		this.CustName = CustName;

		Vector data = new Vector();

		BankingControl BC = new BankingControl();
		Vector NumberBalance = BC.NumberBalanceInfo(UName);
		Vector CheckingAccountNumber = (Vector) NumberBalance.get(0);
		Vector CheckingBalance = (Vector) NumberBalance.get(1);
		Vector SavingsAccountNumber = (Vector) NumberBalance.get(2);
		Vector SavingsBalance = (Vector) NumberBalance.get(3);
		System.out.println(CheckingAccountNumber);
		System.out.println(CheckingBalance);
		System.out.println(SavingsAccountNumber);
		System.out.println(SavingsBalance);

		int len = CheckingAccountNumber.size() + SavingsAccountNumber.size();
		//String data[][] = new data[len][3];

		Vector column = new Vector();
		column.add(0, "Account Type");
		column.add(1, "Account Number");
		column.add(2, "Balance");

		for(int i = 0; i < (CheckingAccountNumber.size() + SavingsAccountNumber.size()); i++) {
			Vector info = new Vector();
				if(i < CheckingAccountNumber.size()) {
					info.add(0, "Checking");
					info.add(1, CheckingAccountNumber.get(i));
					info.add(2, CheckingBalance.get(i));
				} else {
					info.add(0, "Savings");
					info.add(1, SavingsAccountNumber.get(i - CheckingAccountNumber.size()));
					info.add(2, SavingsBalance.get(i - CheckingAccountNumber.size()));
				}
			data.add(i, info);
		}
		System.out.println(data);
		JTable jt = new JTable(data, column);
		JScrollPane sp = new JScrollPane(jt);
		add(sp);

		RefreshButton = new JButton("Refresh");
		ShowInterestButton = new JButton("Show Me Interest");
		add(RefreshButton);
		add(ShowInterestButton);

		RefreshButton.addActionListener(this);
		ShowInterestButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		String arg = evt.getActionCommand();
		if(arg.equals("Refresh")) {
			removeAll();
			invalidate();
			repaint();
			revalidate();
			repaint();
			//requestFocus();
		}
		if(arg.equals("Show Me Interest")) {
			SavingAccount SA = new SavingAccount(UName);
			if(SA.calculateInterests())
				JOptionPane.showMessageDialog(null, "Please Click the Refresh Button!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(null, "You Don't have any Savings Account!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}


public class OverviewBO extends JFrame {
private OverviewPanel OV_Panel;

	    public OverviewBO(String UName, String CustName)
	    {

	        setTitle("Account Overview");
	        setSize(500, 500);

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
	         OV_Panel = new OverviewPanel(UName, CustName);
	         contentPane.add(OV_Panel);
	         show();
    }
}