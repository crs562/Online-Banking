import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import com.shah.*;
import java.util.Vector;
import java.util.Arrays;

public class BankingControl {

	public Vector NumberBalanceInfo(String UName) {
		Vector NumberBalanceVector = new Vector();
		CheckingAccount CA = new CheckingAccount(UName);
        Vector CheckingAccountNumber = CA.getAccountNo();
        Vector CheckingBalance = new Vector();
        Vector SavingsBalance = new Vector();
        float CBalance, SBalance;
        for(int i = 0; i < CheckingAccountNumber.size(); i++) {
			String AccountNumber = (String) CheckingAccountNumber.get(i);
			CBalance = CA.getBalance(AccountNumber);
			CheckingBalance.addElement(CBalance);
		}
		System.out.println(CheckingAccountNumber);
		System.out.println(CheckingBalance);
        SavingAccount SA = new SavingAccount(UName);
        Vector SavingsAccountNumber = SA.getAccountNo();
        for(int j = 0; j < SavingsAccountNumber.size(); j++) {
			String AccountNumber = (String) SavingsAccountNumber.get(j);
			SBalance = SA.getBalance(AccountNumber);
			SavingsBalance.addElement(SBalance);
		}
		System.out.println(SavingsAccountNumber);
		System.out.println(SavingsBalance);
		NumberBalanceVector.add(0, CheckingAccountNumber);
		NumberBalanceVector.add(1, CheckingBalance);
		NumberBalanceVector.add(2, SavingsAccountNumber);
		NumberBalanceVector.add(3, SavingsBalance);
        return NumberBalanceVector;
	}
}