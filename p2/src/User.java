/*
 * each user has a userNumber and password in order to  login
 * each user has two type of accounts: cheque and saving account 
 */
public class User {

	public User(String userNumber, int password, ChequeAccount chque, SavingAccount saving) {
		super();
		this.userNumber = userNumber;
		this.password = password;
		this.chque = chque;
		this.saving = saving;
	}
	private String userNumber;
	private int password;
	private ChequeAccount chque;
	private SavingAccount saving;
	
	public ChequeAccount getChque() {
		return chque;
	}
	public void setChque(ChequeAccount chque) {
		this.chque = chque;
	}
	public SavingAccount getSaving() {
		return saving;
	}
	public void setSaving(SavingAccount saving) {
		this.saving = saving;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userNumber=" + userNumber + ", password=" + password + ", chque=" + chque + ", saving=" + saving + "]";
	}
	
}
