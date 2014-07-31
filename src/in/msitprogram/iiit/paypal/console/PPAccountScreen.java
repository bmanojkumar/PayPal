package in.msitprogram.iiit.paypal.console;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Transaction;
import in.msitprogram.iiit.paypal.persistance.DataStore;

public class PPAccountScreen {
	PPAccount account;
	PPRestrictedAccount Raccount;
	PPBusinessAccount BAaccount;
	Scanner scan;
	private ArrayList<PPAccount> accounts = new ArrayList<PPAccount>();
	private ArrayList<PPRestrictedAccount> RAaccounts = new ArrayList<PPRestrictedAccount>();
	private ArrayList<PPBusinessAccount> BAaccounts = new ArrayList<PPBusinessAccount>();

	private static ObjectOutputStream oos;
//	private static final long serialVersionUID = -4066794688590758816L;
//	private static final long serialVersionUID = 6336241787099656735L;
//	private static final long serialVersionUID = 4344632058782201932L;
	
	public static void writeAccount(ArrayList<PPAccount> accs) throws IOException
	{
		File fp1 = new File("PPAccounts.paypal");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int i=0;i<accs.size();i++)
		{
			oos.writeObject(accs.get(i));
		}
		
		oos.close();
	}
	
	
	public static void writeAccount1(ArrayList<PPRestrictedAccount> accs) throws IOException
	{
		File fp1 = new File("PPRestrictedAccounts.paypal");
		//if( fp1  == null)  System.out.println("cnt create file..");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int i=0;i<accs.size();i++)
		{
			oos.writeObject(accs.get(i));
			//System.out.println(accs.get(i));
		}
		
		oos.close();
	}
	
	
	public static void writeAccount2(ArrayList<PPBusinessAccount> accs) throws IOException
	{
		File fp1 = new File("PPBusinessAccounts.paypal");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int i=0;i<accs.size();i++)
		{
			oos.writeObject(accs.get(i));
			//System.out.println(accs.get(i));
		}
		
		oos.close();
	}
	
	public PPAccountScreen(String email) throws ClassNotFoundException, IOException {
		scan = new Scanner(System.in);
		
		System.gc();
		PPAccount account1 = null; 		
		File fp = new File("PPAccounts.paypal");
		ObjectInputStream ois = null;
			try
			{
				
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account1 = (PPAccount)ois.readObject()) != null)
				{
					//System.out.println(account1);
					accounts.add(account1);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
		if(ois !=null)	ois.close();
			
		account = DataStore.lookupAccount(email);
		if(account == null) //System.out.println("Personal Account is null!!");
		
		
		System.gc();	
		
	}
	
	public void PPRestrictedAccount(String email) throws IOException, ClassNotFoundException
	{
		PPRestrictedAccount raaccount1 = null; 		
		File fp1 = new File("PPRestrictedAccounts.paypal");
		ObjectInputStream ois1 = null;
			try
			{
				
				FileInputStream fis1 = new FileInputStream(fp1);			//file input stream
				ois1 = new ObjectInputStream(fis1);	
				while((raaccount1 = (PPRestrictedAccount)ois1.readObject()) != null)
				{
					//System.out.println("Restricted" + raaccount1);
					RAaccounts.add(raaccount1);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
		if(ois1 !=null)	ois1.close();
			
		Raccount = DataStore.lookupAccount1(email);
		if(Raccount == null) //System.out.println("Restricted Account is null!!");
		
		System.gc();

	}
	
	
	public void PPBusinessAccount(String email) throws IOException, ClassNotFoundException
	{
		PPBusinessAccount raaccount1 = null; 		
		File fp1 = new File("PPBusinessAccounts.paypal");
		ObjectInputStream ois1 = null;
			try
			{
				
				FileInputStream fis1 = new FileInputStream(fp1);			//file input stream
				ois1 = new ObjectInputStream(fis1);	
				while((raaccount1 = (PPBusinessAccount)ois1.readObject()) != null)
				{
					//System.out.println("Business" + raaccount1);
					BAaccounts.add(raaccount1);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
		if(ois1 !=null)	ois1.close();
			
		Raccount = DataStore.lookupAccount1(email);
		if(Raccount == null) //System.out.println("Restricted Account is null!!");
		
		System.gc();

	}
	

	public void show() throws IOException {
		
		//check if account is active
		
		//if active
		
			// print the account summary
			//System.out.println("Inside Show");
			int tries = 3;
			boolean check = account.isActivated();
			//System.out.println(check);
			if(check == false) System.out.println("Your Account is not Activated..Use Option 2 to activate your account");
			if(check) 
			{
					Scanner st = new Scanner(System.in);
					System.out.println("Enter activation code:");
					String code = st.nextLine();
					tries--;
					boolean checkk = account.checkactivationcode(code);
					
					if(!checkk)
					{
						System.out.println("Wrong code!!");
					}
					if(checkk == false)
					{
						System.out.println("You still have 2 tries");
						System.out.println("Enter activation code:");
						code = st.nextLine();
						tries--;
						checkk = account.checkactivationcode(code);
					}
					if(checkk == false)
					{
						System.out.println("You still have 1 tries");
						System.out.println("Enter activation code:");
						code = st.nextLine();
						tries--;
						checkk = account.checkactivationcode(code);
					}
					if(checkk == false)
					{
						System.out.println("You maximum no of attempts exceeded,Your account has been suspended");
						account.setActivated(false);
						
						
						for(int i=0;i<accounts.size();i++)
						{
							if(accounts.get(i).geetEmail().equals(account.geetEmail()))
							{
								accounts.get(i).setActivated(false);
									
							}
						}
						
						writeAccount(accounts);
					}
					
					if(checkk)
					{
					
						//System.out.println(account);
						tries = 3;
						System.out.println("");
						System.out.println("1.Add Funds");
						System.out.println("2.Withdraw Funds");
						System.out.println("3.Send Money");
						System.out.println("4.Request Money");
						
						Scanner tc = new Scanner(System.in);
						int ch = tc.nextInt();
						
						if(ch == 1)
						{
							addFunds();
						}
						if(ch == 2)
						{
							withdrawFunds();
						}
						if(ch == 3)
						{
							sendMoney();
						}
						if(ch == 4)
						{
							requestMoney();
						}
					}
			}
			
			// print menu and accept menu options
			// for all the paypal account operations
			
		
	}
	
	
	
	public void show1() throws IOException {
		
		int tries = 3;	
		boolean check = Raccount.isActivated();
		//System.out.println(check);
		if(check == false) System.out.println("Your Account is not Activated..Use Option 2 to activate your account");
		if(check) 
		{
					Scanner st = new Scanner(System.in);
					System.out.println("Enter activation code:");
					String code = st.nextLine();
					tries--;
					boolean checkk = Raccount.checkactivationcode(code);
					
					if(!checkk)
					{
						System.out.println("Wrong code!!");
					}
					if(checkk == false)
					{
						System.out.println("You still have 2 tries");
						System.out.println("Enter activation code:");
						code = st.nextLine();
						tries--;
						checkk = Raccount.checkactivationcode(code);
					}
					if(checkk == false)
					{
						System.out.println("You still have 1 tries");
						System.out.println("Enter activation code:");
						code = st.nextLine();
						tries--;
						checkk = Raccount.checkactivationcode(code);
					}
					if(checkk == false)
					{
						System.out.println("You maximum no of attempts exceeded,Your account has been suspended");
						Raccount.setActivated(false);
						for(int i=0;i<RAaccounts.size();i++)
						{
							if(RAaccounts.get(i).geetEmail().equals(Raccount.geetEmail()))
							{
								RAaccounts.get(i).setActivated(false);
							}
						}
						
						writeAccount1(RAaccounts);
					}
					if(checkk)
					{
					//System.out.println(Raccount);	
						tries = 3;
						System.out.println("");
						System.out.println("1.Withdraw Funds......");				
						Scanner tc = new Scanner(System.in);
						int ch = tc.nextInt();
						
						if(ch == 1)
						{
							int k = withdrawFunds1();
							withdrawFunds11(k);
							
						}
					}
		}
			
	}
	
	
	public void show2() throws IOException {
		
		//check if account is active
		
		//if active
		
			// print the account summary
			//System.out.println("Inside Show");
			//boolean check = account.isActivated();
			//System.out.println(check);
			//if(check) 
			//{
		
		//System.out.println(check);
			int tries = 3;
			boolean check = Raccount.isActivatedB();
				if(check == false) System.out.println("Your Account is not Activated..Use Option 2 to activate your account");
				
				if(check) 
				{
							Scanner st = new Scanner(System.in);
							System.out.println("Enter activation code:");
							String code = st.nextLine();
							tries--;
							boolean checkk = Raccount.checkactivationcodeB(code);
							
							if(!checkk)
							{
								System.out.println("Wrong code!!");
							}
							if(checkk == false)
							{
								System.out.println("You still have 2 tries");
								System.out.println("Enter activation code:");
								code = st.nextLine();
								tries--;
								checkk = Raccount.checkactivationcodeB(code);
							}
							if(checkk == false)
							{
								System.out.println("You still have 1 tries");
								System.out.println("Enter activation code:");
								code = st.nextLine();
								tries--;
								checkk = Raccount.checkactivationcodeB(code);
							}
							if(checkk == false)
							{
								System.out.println("You maximum no of attempts exceeded,Your account has been suspended");
								Raccount.setActivatedB(false);
								for(int i=0;i<RAaccounts.size();i++)
								{
									if(RAaccounts.get(i).geetEmail().equals(Raccount.geetEmail()))
									{
										RAaccounts.get(i).setActivatedB(false);
									}
								}
								
								writeAccount1(RAaccounts);
							}
							if(checkk)
							{
									tries = 3;
									System.out.println(account);
									
									System.out.println("");
									//System.out.println("1.Add Funds");
									System.out.println("1.Withdraw Funds");
									//System.out.println("3.Send Money");
									//System.out.println("4.Request Money");
									
									Scanner tc = new Scanner(System.in);
									int ch = tc.nextInt();
									
									if(ch == 1)
									{
										int g = withdrawFunds22();
										withdrawFunds2(g);
									}
							}
							else
							{
								
							}
				}
					/*if(ch == 2)
					{
						withdrawFunds();
					}
					if(ch == 3)
					{
						sendMoney();
					}
					if(ch == 4)
					{
						requestMoney();
					}*/
			//}
			
			// print menu and accept menu options
			// for all the paypal account operations
			
		
	}


	private void withdrawFunds() throws IOException {
		// implement the withdraw funds user interface here
		//use the account object as needed for withdraw funds
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		Scanner intsc = new Scanner(System.in);
		System.out.println("Enter the amount of funds you want to transfer from bank");
		int funds = intsc.nextInt();
		t.setDebit(funds);
		
		for(int i=0;i<accounts.size();i++)
		{
			if(accounts.get(i).geetEmail().equals(account.geetEmail()))
			{
				if(accounts.get(i).getAccountBal() - funds >= 0)
				{
				accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() - funds);
				transactions = accounts.get(i).getTransactions();
				transactions.add(t);
				accounts.get(i).setTransactions(transactions);
				}
				else
				{
					System.out.println("Not enough balance");
				}
				
			}
		}
		
		writeAccount(accounts);
		
	}

	private void requestMoney() throws IOException {
		//implement the request money user interface here		
		//use the account object as needed for request money funds
		
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		Scanner intsc = new Scanner(System.in);
		Scanner strsc = new Scanner(System.in);
		
		System.out.println("Enter the name of email you want to request:");
		String semail = strsc.nextLine();		
		
		System.out.println("Enter the amount of funds you want to request:");
		int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		
		for(int i=0;i<accounts.size();i++)
		{
			if(accounts.get(i).geetEmail().equals(semail))
			{
				
				accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() - funds);
				transactions = accounts.get(i).getTransactions();
				transactions.add(t);
				accounts.get(i).setTransactions(transactions);
			}
			
			if(accounts.get(i).geetEmail().equals(account.geetEmail()))
			{
				accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() + funds);
				transactions = accounts.get(i).getTransactions();
				transactions.add(t);
				accounts.get(i).setTransactions(transactions);
			}
		}
		
		writeAccount(accounts);
	}

	private void sendMoney() throws IOException {
		// implement the send moeny user interface here	
		//use the account object as needed for send money funds
		
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		
		Scanner intsc = new Scanner(System.in);
		Scanner strsc = new Scanner(System.in);
		
		System.out.println("Enter the name of email you want to send:");
		String semail = strsc.nextLine();		
		
		System.out.println("Enter the amount of funds you want to send:");
		int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		boolean h = false;
		
		for(int i=0;i<accounts.size();i++)
		{
			
			if(accounts.get(i).geetEmail().equals(account.geetEmail()))
			{
				if(accounts.get(i).getAccountBal() - funds >= 0)
				{
					accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() - funds);
					transactions = accounts.get(i).getTransactions();
					transactions.add(t);
					accounts.get(i).setTransactions(transactions);
					h = true;
				}
				else
				{
					System.out.println("Not enough money!!");
					h =false;
				}
			}
			
			if(h == true)
			{
				if(accounts.get(i).geetEmail().equals(semail))
				{
					accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() + funds);
					transactions = accounts.get(i).getTransactions();
					transactions.add(t);
					accounts.get(i).setTransactions(transactions);
				}
			}
			
			
		}
		
		writeAccount(accounts);
		
	}

	private void addFunds() throws IOException {
		// implement the add funds user interface here
		
		//use the account object as needed for add funds
		
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		Scanner intsc = new Scanner(System.in);
		System.out.println("Enter the amount of funds you want to transfer from bank");
		int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		for(int i=0;i<accounts.size();i++)
		{
			if(accounts.get(i).geetEmail().equals(account.geetEmail()))
			{
				accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() + funds);
				transactions = accounts.get(i).getTransactions();
				//System.out.println("Transactions: "+ transactions);
				//System.out.println("t is: "+ t);
				transactions.add(t);
				accounts.get(i).setTransactions(transactions);
				break;
			}
		}
		
		//System.out.println(accounts);
		writeAccount(accounts);
		
		
	}
	
	
	private int withdrawFunds1() throws IOException {
		// implement the withdraw funds user interface here
		//use the account object as needed for withdraw funds
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		
		Scanner intsc = new Scanner(System.in);
		System.out.println("Enter the amount of funds you want to transfer from bank");
		int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		System.out.println("debit");
		
		for(int i=0;i<RAaccounts.size();i++)
		{
			if(RAaccounts.get(i).geetEmail().equals(Raccount.geetEmail()))
			{
				//if(RAaccounts.get(i).getWithdrawLimit() - funds > 0)
				//RAaccounts.get(i).setWithdrawLimit(RAaccounts.get(i).getWithdrawLimit() - funds);
				
				if(RAaccounts.get(i).getWithdrawLimit() - funds >= 0)
				{
					RAaccounts.get(i).setWithdrawLimit(RAaccounts.get(i).getWithdrawLimit() - funds);
					transactions = RAaccounts.get(i).getTransactions();
					transactions.add(t);
					RAaccounts.get(i).setTransactions(transactions);
					float rem  = RAaccounts.get(i).getWithdrawLimit();
					System.out.println("Your remaining withdraw limit is: " + rem);
					writeAccount1(RAaccounts);
					
				}
				else
				{
					//float rem  = RAaccounts.get(i).getWithdrawLimit() - funds;
					//System.out.println("Your remaining withdraw limit is: " + rem);
					System.out.println("Exceeded Withdraw Limit...(You can enter less amount)");
					funds = 0;
				}
			}
		}
		//System.out.println(transactions);
		
		
		return funds;
		
	}
	
	private void withdrawFunds11(int funds) throws IOException {
		// implement the withdraw funds user interface here
		//use the account object as needed for withdraw funds
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		
		Scanner intsc = new Scanner(System.in);
		//System.out.println("Enter the amount of funds you want to transfer from bank");
		//int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		for(int i=0;i<accounts.size();i++)
		{
			if(accounts.get(i).geetEmail().equals(Raccount.getParentaccount()))
			{
				if(accounts.get(i).getAccountBal() - funds >= 0)
				{	
					accounts.get(i).setAccountBal(accounts.get(i).getAccountBal() - funds);
					
					transactions = accounts.get(i).getTransactions();
					transactions.add(t);
					accounts.get(i).setTransactions(transactions);
					writeAccount(accounts);
				}
				else
				{
					System.out.println("Parent Account Does not enough balance");
				}
			}
		}
		//System.out.println(transactions);
		
		
	}
	
	private void withdrawFunds2(int funds) throws IOException {
		// implement the withdraw funds user interface here
		//use the account object as needed for withdraw funds
		
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		
		Scanner intsc = new Scanner(System.in);
		//System.out.println("Enter the amount of funds you want to withdraw");
		//int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		for(int i=0;i<BAaccounts.size();i++)
		{
			System.out.println("hi....");
			if(Raccount == null) System.out.println("Restrcitd account is null.....");
			System.out.println(BAaccounts);
			System.out.println(Raccount.getBusinessaccount());
			if(BAaccounts.get(i).geetEmail().equals(Raccount.getBusinessaccount()))
			{
				System.out.println("Found...Inside Business Account...withdraw");
				
				if(BAaccounts.get(i).getAccountBal() - funds >= 0 && funds < Raccount.getBusinesswithdrawlimit())
				{	
					BAaccounts.get(i).setAccBal(BAaccounts.get(i).getAccountBal() - funds);
					System.out.println(BAaccounts.get(i).getAccountBal());
				}
				else
				{
					System.out.println("Some thing went wrong...You might have entered amount that has exceeded withdraw limit");
					funds = 0;
				}
				
				
				
				//if(RAaccounts.get(i).getBusinesswithdrawlimit() - funds > 0)
				//RAaccounts.get(i).setBusinesswithdrawlimit(RAaccounts.get(i).getBusinesswithdrawlimit() - funds);
				//System.out.println(RAaccounts.get(i));
							
						
				transactions = BAaccounts.get(i).getTransactions();
				transactions.add(t);
				BAaccounts.get(i).setTransactions(transactions);
			}
		}
		
		writeAccount2(BAaccounts);
		
	}
	
	private int withdrawFunds22() throws IOException {
		// implement the withdraw funds user interface here
		//use the account object as needed for withdraw funds
		Scanner st = new Scanner(System.in);
		Transaction t = new Transaction();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		 Date dNow = new Date(0 );
	     SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd");
	     SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
	     t.settDate(ft.format(dNow));
	     t.settTime(ft1.format(dNow));
		
		System.out.println("Enter reference:");
		String ref = st.nextLine();
		
		t.setReference(ref);
		
		System.out.println("Enter narration:");
		String nar = st.nextLine();
		
		t.setNarration(nar);
		
		
		Scanner intsc = new Scanner(System.in);
		System.out.println("Enter the amount of funds you want to transfer from bank");
		int funds = intsc.nextInt();
		
		t.setDebit(funds);
		
		for(int i=0;i<RAaccounts.size();i++)
		{
			if(RAaccounts.get(i).geetEmail().equals(Raccount.geetEmail()))
			{
				//if(RAaccounts.get(i).getWithdrawLimit() - funds > 0)
				//RAaccounts.get(i).setWithdrawLimit(RAaccounts.get(i).getWithdrawLimit() - funds);
				if(RAaccounts.get(i).getBusinesswithdrawlimit() - funds >= 0)
				{
					float rem  = RAaccounts.get(i).getBusinesswithdrawlimit() - funds;
					RAaccounts.get(i).setBusinesswithdrawlimit(RAaccounts.get(i).getBusinesswithdrawlimit() - funds);
					transactions = RAaccounts.get(i).getTransactions();
					transactions.add(t);
					RAaccounts.get(i).setTransactions(transactions);
					
					System.out.println("Your remaining withdraw limit is: " + rem);
					writeAccount1(RAaccounts);
					
				}
				else
				{
					//float rem  = RAaccounts.get(i).getWithdrawLimit() - funds;
					//System.out.println("Your remaining withdraw limit is: " + rem);
					System.out.println("Exceeded Withdraw Limit...(You can enter less amount)");
					funds = 0;
				}
			}
		}
		//System.out.println(transactions);
		
		
		return funds;
		
	}
	
	public void logintomainbusiness()
	{
		System.out.println("");
	}

}