import java.lang.*;
import javax.swing.*;
import com.shah.*;

public class TransferControl
{

    public TransferControl(String SourceAccountType, String  DestinationAccountType, String  Name, String  UName, String  TransferAmount, String SavingsAccountNumber, String CheckingAccountNumber) {
		if (SourceAccountType.equals("Checking..." + CheckingAccountNumber.substring(4, 8)) && DestinationAccountType.equals("Savings..." + SavingsAccountNumber.substring(4, 8))) {
			CheckingAccount CA = new CheckingAccount(CheckingAccountNumber, UName, TransferAmount);
            if(CA.withdraw()) {
				SavingAccount SA = new SavingAccount(SavingsAccountNumber, UName, TransferAmount);
				if(SA.deposit()) {
					Transaction TA = new Transaction(SourceAccountType, DestinationAccountType, UName, TransferAmount);
					if(TA.recordTransaction())
						JOptionPane.showMessageDialog(null, "Transfer is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Transfer is Unsuccessful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Deposit is Unsuccessful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Withdraw is Unsuccessful due to less Account Balance!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		} else if (SourceAccountType.equals("Savings..." + SavingsAccountNumber.substring(4, 8)) && DestinationAccountType.equals("Checking..." + CheckingAccountNumber.substring(4, 8))) {
			SavingAccount SA = new SavingAccount(SavingsAccountNumber, UName, TransferAmount);
			if(SA.withdraw()) {
				CheckingAccount CA = new CheckingAccount(CheckingAccountNumber, UName, TransferAmount);
				if(CA.deposit()) {
					Transaction TA = new Transaction(SourceAccountType, DestinationAccountType, UName, TransferAmount);
					if(TA.recordTransaction())
						JOptionPane.showMessageDialog(null, "Transfer is Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Transfer is Unsuccessful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Deposit is Unsuccessful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Withdraw is Unsuccessful due to less Account Balance!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Please select Source Account as Checking/Saving and select Destination Account as Saving/Checking!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
}