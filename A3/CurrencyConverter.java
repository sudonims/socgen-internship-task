import java.util.Scanner;

interface CurrencyConverterInterface {
	public double convertToINR(double amount);
}

class GBPConverter implements CurrencyConverterInterface {
	public double convertToINR(double amount) {
		return amount * 101.05;
	}
}

class USDConverter implements CurrencyConverterInterface {
	public double convertToINR(double amount) {
		return amount * 74.64;
	}
}

public class CurrencyConverter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("Select Currency to convert from\n1. GBP\n2. USD\n");

		int choice = sc.nextInt();
		System.out.printf("Enter the amount\n");
		double amt = sc.nextDouble();

		if (choice == 1) {
			GBPConverter con = new GBPConverter();
			System.out.println("INR=" + con.convertToINR(amt));
		} else if (choice == 2) {
			USDConverter con = new USDConverter();
			System.out.println("INR=" + con.convertToINR(amt));
		}

		sc.close();
	}
}
