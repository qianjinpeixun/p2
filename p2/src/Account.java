/*
 * This is the base class, cheque and saving account should extend this class
 * 
 */
public class Account {

	//each account should have a accountNumber
	private String accountNumber;
	//this is the current balance of this account
	private double balance;

	public Account(String accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	//deposit is the same logic with cheque and saving account
	public String deposit(double amount) {
		String ret = "ok";
		if (amount > 0.0)
			balance = balance + amount;
		else
			ret = "amount shoule be more than zeor!";
		return ret;
	}

	//default method withdraw can be used by cheque account
	//but for the saving account, the logic may be different
	//there will have a fee for saving account during withdraw money
	public String withdraw(double amount) {
		String ret = "ok";
		if (amount < 0.0) {
			ret = "amount shoule be more than zeor!";
		} else {
			if (balance < amount) {
				ret = "no sufficient money!";
			} else {
				balance = balance - amount;
			}
		}
		return ret;
	}

	//During transfer to other account, first step is to check if the receipt number is correct
	//then check if the balance is suciffient
	public String transfer(String accountNumber, double amount) {
		String ret = "ok";
		if (amount < 0.0) {
			ret = "amount shoule be more than zeor!";
		} else if (balance < amount) {
			ret = "no sufficient money!";
		} else {
			int result = BankDatabase.checkAccountByNumber(accountNumber);
			if (result == 2) {
				ret = "receipt account number is not correct!";

			} else {
				User uu = BankDatabase.getUserByAccountNumber(accountNumber);
				if (result == 0) {
					uu.getChque().deposit(amount);
				} else if (result == 1) {
					uu.getSaving().deposit(amount);
				}
				balance = balance - amount;
			}
		}

		return ret;
	}
}
