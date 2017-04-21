import java.io.*;
import java.util.*;

public class BankDatabase {

	private static ArrayList<User> users = new ArrayList<User>();

	// this method is used by loginPage (first window of this application)
	public static String login(String number, int password) {
		String ret = "ok";
		User currentUser = null;
		for (User user : users) {
			if (user.getUserNumber().equals(number)) {
				if (user.getPassword() == password) {
					currentUser = user;
					break;
				} else {
					ret = "incorrect password!";
					break;
				}
			}
		}
		if (currentUser == null && ret.equals("ok")) {
			ret = "incorrect user number!";
		}
		return ret;
	}

	// return value
	// 0 cheque
	// 1 saving
	// 2 not found
	// For example, during transfer money, the receiipt account number must be
	// provided
	// this method is to check if the receipt account number is correct
	public static int checkAccountByNumber(String number) {
		int ret = 2;
		for (User user : users) {
			System.out.println("user.getChque().getAccountNumber()=" + user.getChque().getAccountNumber() + ",number=" + number);
			System.out.println("user.getSaving().getAccountNumber()=" + user.getSaving().getAccountNumber() + ",number=" + number);

			if (user.getChque().getAccountNumber().equals(number)) {
				ret = 0;
				break;
			} else if (user.getSaving().getAccountNumber().equals(number)) {
				ret = 1;
				break;
			}
		}
		return ret;
	}

	// according to the userNumber, get the user object
	public static User getUserByUserNumber(String number) {
		User user = null;
		for (User u : users) {
			if (u.getUserNumber().equals(number)) {
				user = u;
				break;
			}
		}
		return user;
	}

	// according to the accountNumber (cheque or saving account number) to get
	// User object
	public static User getUserByAccountNumber(String number) {
		User user = null;
		for (User u : users) {
			if (u.getChque().getAccountNumber().equals(number) || u.getSaving().getAccountNumber().equals(number)) {
				user = u;
				break;
			}
		}
		return user;
	}

	// when this applicaton starts, this method will be used for load data from
	// local file
	public static String loadData() {
		String ret = "ok";
		try {
			File file = new File("accounts.txt");
			// if the data file could not be found, provide a default file
			if (!file.exists()) {
				intiDataFile();
			}
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				String number = sc.next();
				int password = sc.nextInt();
				String chequeNumber = sc.next();
				double chequeBalance = sc.nextDouble();
				String savingNumber = sc.next();
				double savingBalance = sc.nextDouble();

				ChequeAccount c = new ChequeAccount(chequeNumber, chequeBalance);
				SavingAccount s = new SavingAccount(savingNumber, savingBalance);

				User u = new User(number, password, c, s);
				users.add(u);
			}
			System.out.println("users=" + users);
		} catch (Exception e) {
			e.printStackTrace();
			ret = "error during load data from local file!";
		}
		return ret;
	}

	// in order to make the show smoothly, if the data file could be found,
	// create a new file
	private static void intiDataFile() {
		try {
			File file = new File("accounts.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);

			pw.println("1234567891 1234 001 1000 002 2000");
			pw.println("1234567892 1234 003 3000 004 4005");
			pw.println("1234567893 1234 005 5000 006 6000");
			pw.close();
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// when the user logout, save the data to local file
	public static String saveDate() {
		System.out.println("Begin to save data");
		String ret = "ok";
		try {
			File file = new File("accounts.txt");
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			System.out.println("users=" + users);
			for (User u : users) {
				pw.println(u.getUserNumber() + " " + u.getPassword() + " " + u.getChque().getAccountNumber() + " " + u.getChque().getBalance() + " "
						+ u.getSaving().getAccountNumber() + " " + u.getSaving().getBalance());
			}
			pw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
			ret = "error during load data from local file!";
		}
		return ret;
	}
}
