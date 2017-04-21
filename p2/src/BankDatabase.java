import java.io.*;
import java.util.*;

public class BankDatabase {

	private static ArrayList<User> users=new ArrayList<User>();

	public static String login(String number, int password){
		String ret="ok";
		User currentUser=null;
		for(User user:users){
			if(user.getUserNumber().equals(number)){
				if(user.getPassword()==password){
					currentUser=user;
					break;
				}else{
					ret="incorrect password!";
					break;
				}
			}
		}
		if(currentUser==null){
			ret="incorrect user number!";
		}
		return ret;
	}
	
	// return value
	// 0 cheque
	// 1 saving
	// 2 not found
	public static int checkAccountByNumber(String number){
		int ret=2;
		for(User user:users){
			if(user.getChque().getAccountNumber().equals(number)){
				ret=0;
				break;
			}else if(user.getSaving().getAccountNumber().equals(number)){
				ret=1;
				break;
			}
		}
		return ret;
	}
	

	
	
	
	public static User getUserByAccountNumber(String number){
		User user=null;
		for(User u:users){
			if(u.getChque().getAccountNumber().equals(number)|| u.getSaving().getAccountNumber().equals(number)){
				user=u;
				break;
			}
		}
		return user;
	}
	
	public static String loadData(){
		String ret="ok";
		try{
			File file=new File("accounts.txt");
			Scanner sc=new Scanner(file);
			while(sc.hasNext()){
				String number=sc.next();
				int password=sc.nextInt();
				String chequeNumber=sc.next();
				double chequeBalance=sc.nextDouble();
				String savingNumber=sc.next();
				double savingBalance=sc.nextDouble();
				
				ChequeAccount c=new ChequeAccount(chequeNumber,chequeBalance);
				SavingAccount s=new SavingAccount(savingNumber,savingBalance);
				
				User u=new User(number,password,c,s);
				users.add(u);
			}
		}catch(Exception e){
			e.printStackTrace();
			ret="error during load data from local file!";
		}
		return ret;
	}
	
	public static String saveDate(){
		String ret="ok";
		try{
			File file=new File("accounts.txt");
			FileWriter fw=new FileWriter(file);
			PrintWriter pw=new PrintWriter(fw);
			for(User u:users){
				pw.println(u.getUserNumber()+" "+u.getPassword()+" "+u.getChque().getAccountNumber()+" "+u.getChque().getBalance()+" "+u.getSaving().getAccountNumber()+" "+u.getSaving().getBalance());
			}
		}catch(Exception e){
			e.printStackTrace();
			ret="error during load data from local file!";
		}
		return ret;
	}
}
