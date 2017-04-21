
public class SavingAccount extends Account {

	public SavingAccount(String accountNumber, double balance) {
		super(accountNumber, balance);
	}
	private static double fee=5;
	@Override
	public String withdraw(double amount) {
		String ret = "ok";
		if (amount < 0.0) {
			ret = "amount shoule be more than zeor!";
		} else {
			if (getBalance() < (amount+fee)) {
				ret = "no sufficient money!";
			} else {
				setBalance(getBalance() - amount-fee);
			}
		}
		return ret;
	}

}
