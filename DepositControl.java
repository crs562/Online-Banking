import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import com.shah.*;

public class DepositControl{

    public DepositControl(String FromAccount, String ToAccount, String CustName, String  UName, String  Balance, String AccountNumber){

    	if (ToAccount.equals("Checking......."+ AccountNumber.substring(4, 8))){
				CheckingAccount CA = new CheckingAccount(AccountNumber, UName, Balance);
				if(CA.withdraw()) {
					Transaction TZ = new Transaction(ToAccount, FromAccount, CustName, UName, Balance);
					if(TZ.recordTransaction())
						JOptionPane.showMessageDialog(null, " Deposit Transaction successful in Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, " Deposit Transaction Failed in Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Deposit failed in Checking.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

		}else if (ToAccount.equals("Savings........"+ AccountNumber.substring(4, 8))){
				SavingAccount SA = new SavingAccount(AccountNumber, UName, Balance);
				if(SA.withdraw()) {
					Transaction TZ = new Transaction(ToAccount, FromAccount, CustName, UName, Balance);
					if(TZ.recordTransaction())
						JOptionPane.showMessageDialog(null, "Deposit Transaction successful in Saving!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Deposit Transaction Failed in Saving!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Deposit failed in Saving.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}