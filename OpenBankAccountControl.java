/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: February, 2014													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import javax.swing.*;
//import Course.Java.ProSample.*;
import com.shah.*;

public class OpenBankAccountControl
{
	private String FromAccount = "";

    public OpenBankAccountControl(String ToAccount, String  AcountNumber, String  Name, String  UName, String  Balance) {
		//Use CheckingAccount object to invoke method openAcct()
		if (ToAccount.equals("Checking")) {
			CheckingAccount CA = new CheckingAccount(AcountNumber, Name, UName, Balance);
            if (CA.openAcct()) {
            	//System.out.println("successful!");
                JOptionPane.showMessageDialog(null, "Opening a Checking Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                Transaction TZ = new Transaction(ToAccount, FromAccount, Name, UName, Balance);
                if (TZ.recordTransaction()) {
					//system.out.println("successful!");
					JOptionPane.showMessageDialog(null, "Transaction Recorded Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else {
					//system.out.println("fail!");
					JOptionPane.showMessageDialog(null, "Transaction Fail!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}
            } else {
            	//System.out.println("fail!");
            	JOptionPane.showMessageDialog(null, "Opening a Checking Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			SavingAccount SA = new SavingAccount(AcountNumber, Name, UName, Balance);
			if (SA.openAcct()) {
				//system.out.println("successful!");
				JOptionPane.showMessageDialog(null, "Opening a Saving Account is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				Transaction TZ = new Transaction(ToAccount, FromAccount, Name, UName, Balance);
				if (TZ.recordTransaction()) {
					//system.out.println("successful!");
					JOptionPane.showMessageDialog(null, "Transaction Recorded Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else {
					//system.out.println("fail!");
					JOptionPane.showMessageDialog(null, "Transaction Fail!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				//System.out.println("fail!");
            	JOptionPane.showMessageDialog(null, "Opening a Saving Account failed.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}