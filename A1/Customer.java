package A1;

import java.lang.Exception;

class BalanceExceedException extends Exception {
	public BalanceExceedException(String s) {
		super(s);
	}
}

public class Customer {
	String bankAccountNumber, password;
	int balance;

	public Customer(String accNo, String pass, int balance) {
		this.bankAccountNumber = accNo;
		this.password = pass;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return this.bankAccountNumber;
	}

	public boolean verifyPass(String pass) {
		return this.password.equals(pass);
	}

	public boolean deposit(int amt) {
		try {
			this.balance += amt;
			System.out.printf("Amount %d deposited success\n", amt);
			return true;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean withdraw(int amt) {
		try {
			if (amt > this.balance) {
				throw new BalanceExceedException("Balance less than amount to withdraw");
			}
			this.balance -= amt;
			return true;
		} catch (BalanceExceedException e) {
			System.out.println("Please enter valid amount");
			return false;
		}
	}
}