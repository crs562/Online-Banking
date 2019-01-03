/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: February, 2014													  *
*******************************************************************************/

//package com.shah;

import java.lang.*; //including Java packages used by this program
import javax.swing.*;
import com.shah.*;
import java.util.Vector;
import java.util.Arrays;
//import Course.Java.ProSample.*;

public class LoginControl
{
    private Account Acct;
    private Vector CheckingBalance = new Vector();
    private Vector SavingsBalance = new Vector();
    private Vector CheckingAccountNumber = new Vector();
    private Vector SavingsAccountNumber = new Vector();

    public LoginControl(String UName, String PWord) {
		Acct = new Account(UName, PWord);
		String CustomerName = Acct.signIn();
        if (!CustomerName.equals("")) {
        	System.out.println("successful!");
            JOptionPane.showMessageDialog(null, "Login is successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            //OpenBankAccountBO OpenAcctBO = new OpenBankAccountBO(UName, CustomerName);
            //OverviewBO OBO = new OverviewBO(UName, CustomerName);
            //TransferBO TBO = new TransferBO(UName, CustomerName, CheckingAccountNumber, SavingsAccountNumber);
            BankingBO BBO = new BankingBO(UName, CustomerName);
        } else {
            System.out.println("fail!");
            JOptionPane.showMessageDialog(null, "Login failed because of invalid username or password.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
