package A3.B;

public class BankBuilder {
	public static void main(String[] args) {
		Bank bank = new Bank.Builder("1234567890").withAccType("Savings").withBranch("Bangalore").withBalance(10000)
				.build();

		System.out.printf("AccNo: %s\n", bank.bankAccountNumber);
	}
}

class Bank {
	public String bankAccountNumber;
	public String accType;
	public String branch;
	public int balance;

	public static class Builder {
		private String bankAccountNumber;
		private String accType;
		private String branch;
		private int balance;

		public Builder(String accNo) {
			this.bankAccountNumber = accNo;
		}

		public Builder withAccType(String accType) {
			this.accType = accType;
			return this;
		}

		public Builder withBranch(String branch) {
			this.branch = branch;
			return this;
		}

		public Builder withBalance(int balance) {
			this.balance = balance;
			return this;
		}

		public Bank build() {
			Bank bank = new Bank();
			bank.bankAccountNumber = this.bankAccountNumber;
			bank.accType = this.accType;
			bank.branch = this.branch;
			bank.balance = this.balance;

			return bank;
		}
	}

	private Bank() {
	}
}
