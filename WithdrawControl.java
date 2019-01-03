/******************************************************************************
*	Program Author: Dharaben Gandhi for CSCI 6810 Java and the Internet	  *
*	Date: October, 2018													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import com.shah.*;

public class WithdrawControl{

    public WithdrawControl(String FromAccount, String ToAccount, String CustName, String  UName, String  Balance, String AccountNumber){

    	if (FromAccount.equals("Checking......."+ AccountNumber.substring(4, 8))){
				CheckingAccount CA = new CheckingAccount(AccountNumber, UName, Balance);
				if(CA.withdraw()) {
					Transaction TZ = new Transaction(ToAccount, FromAccount, CustName, UName, Balance);
					if(TZ.recordTransaction())
						JOptionPane.showMessageDialog(null, " Withdraw Transaction successful in Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, " Withdraw Transaction Failed in Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Withdraw failed in Checking.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

		}else if (FromAccount.equals("Savings........"+ AccountNumber.substring(4, 8))){
				SavingAccount SA = new SavingAccount(AccountNumber, UName, Balance);
				if(SA.withdraw()) {
					Transaction TZ = new Transaction(ToAccount, FromAccount, CustName, UName, Balance);
					if(TZ.recordTransaction())
						JOptionPane.showMessageDialog(null, " Withdraw Transaction successful in Saving!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, " Withdraw Transaction Failed in Saving!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Withdraw failed in Saving.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}