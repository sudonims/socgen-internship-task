package A1;

import java.util.*;
import java.io.*;

class Bank {
	Customer cust[];

	public Bank() {
		this.cust = new Customer[3];
		this.cust[0] = new Customer("01234567", "pass", 10000);
		this.cust[1] = new Customer("12345678", "password", 20000);
		this.cust[2] = new Customer("23456789", "pass1", 1000);
	}

	public int checkAccountPass(String acc, String pass) {
		for (int i = 0; i < this.cust.length; i++) {
			if (this.cust[i].getAccountNumber().equals(acc)) {
				if (this.cust[i].verifyPass(pass))
					return i;
			}
		}
		return -1;
	}

	public Customer getCust(int num) {
		return this.cust[num];
	}

	public Customer getCust(String accNo) {
		for (int i = 0; i < this.cust.length; i++) {
			if (this.cust[i].getAccountNumber().equals(accNo)) {
				return this.cust[i];
			}
		}
		return null;
	}

	public boolean updateCust(Customer c) {
		for (int i = 0; i < this.cust.length; i++) {
			if (c.getAccountNumber().equals(this.cust[i].getAccountNumber())) {
				this.cust[i] = c;
				return true;
			}
		}
		return false;
	}
}

public class BankInterface {

	public static String generateOTP() {
		Random r = new Random();
		String otp = "";
		for (int i = 0; i < 4; i++) {
			int a = r.nextInt(10);
			otp += Integer.toString(a);
		}
		return otp;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bank b = new Bank();
		File file = new File("transactions.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			System.out.printf("Welcome to login page\n\n");

			System.out.printf("enter Bank Account no\n");
			String acc = sc.nextLine();

			System.out.printf("enter password\n");
			String pass = sc.nextLine();

			int num = b.checkAccountPass(acc, pass);
			if (num != -1) {
				int option = -1, amt;
				Customer c = b.getCust(num);
				while (option != 0) {
					System.out
							.printf(
									"!! WELCOME TO INDIAN BANK !!\nEnter operation:\n1. Deopsit\n2. Withdraw\n3. Transfer\n0. Logout\n");
					option = sc.nextInt();

					switch (option) {
						case 1:
							System.out.printf("enter the amount to deposit\n");
							amt = sc.nextInt();
							c.deposit(amt);
							b.updateCust(c);
							writer.append("amount deposit transaction\n");
							break;

						case 2:
							System.out.printf("enter the amount to withdraw\n");
							amt = sc.nextInt();
							c.withdraw(amt);
							b.updateCust(c);
							writer.append("amount withdraw transaction\n");

							break;

						case 3:
							System.out.printf("Enter the otp\n");
							String otp = generateOTP();
							System.out.println(otp);

							String otpEnter = sc.next();
							if (otpEnter.equals(otp)) {
								System.out.printf("OTP verified!!\n Enter amount and bank account to transfer money\n");
								amt = sc.nextInt();
								String receiverAcc = sc.next();
								Customer receiver = b.getCust(receiverAcc);
								if (receiver != null) {
									c.withdraw(amt);
									receiver.deposit(amt);
									b.updateCust(c);
									b.updateCust(receiver);
									System.out.printf("amount %d transferred to bank account %s\n", amt, receiver.getAccountNumber());
									writer.append("amount transfer transaction\n");
								} else {
									System.out.println("Receiver acc doesn't exist");
									writer.append("Failed transfer transaction\n");
								}
							} else {
								System.out.println("OTP Invalid");
								writer.append("Failed transfer transaction\n");
							}
							break;

						case 0:
							System.out.println("Exited Successfully");
							break;

						case default:
							System.out.println("Invalid Option. Choose Again");
							break;
					}
				}
				writer.close();
			} else {
				System.out.printf("Invalid credentials\n");
			}

			sc.close();
		} catch (IOException e) {
			System.out.println("File couldn't be created/read");
			System.out.println(e);
		}
	}
}
