package in.msitprogram.iiit.paypal.console;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.persistance.DataStore;
import in.msitprogram.iiit.paypal.utils.PPToolkit;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class PPAccountActivationScreen {
	
	private static ObjectOutputStream oos;
	public static float total;
	
	PPAccount account;
	Scanner scan;
	private static ArrayList<PPAccount> accounts = new ArrayList<PPAccount>();
	private static ArrayList<PPRestrictedAccount> RAaccounts = new ArrayList<PPRestrictedAccount>();
	private static ArrayList<PPBusinessAccount> BAaccounts = new ArrayList<PPBusinessAccount>();

	public static int getnoofPPA()
	{
		return accounts.size();
	}
	
	public static int getnoofPPR()
	{
		return RAaccounts.size();
	}
	
	public static int getnoofPPB()
	{
		return BAaccounts.size();
	}
	
	public static void count() throws IOException, ClassNotFoundException
	{
		PPAccount account1 = null;
		PPRestrictedAccount account2 = null;
		PPBusinessAccount account3 = null;
		accounts.clear();
		RAaccounts.clear();
		BAaccounts.clear();
		File fp = new File("PPAccounts.paypal");
		
//		if(!fp.exists()) System.out.println("Cannot locate file......");
		
		ObjectInputStream ois = null;
		try
		{
			//System.out.println("Inside TRY.....!");
			FileInputStream fis = new FileInputStream(fp);
			//System.out.println("Inside TRY.....!!");//file input stream
			ois = new ObjectInputStream(fis);	
			//System.out.println("Inside TRY.....!!!");
			while((account1 = (PPAccount)ois.readObject()) != null)
			{
				//System.out.println(account1);
				total += account1.getAccountBal();
				accounts.add(account1);
			}
		}
		catch(EOFException e)
		{
			
			//System.out.println("EOF reached when reading Personal Accounts file!!");
			//e.printStackTrace();
		}
		ois.close();
		
		
		/*--------------------------------------------------------------------*/
		
		File fp1 = new File("PPRestrictedAccounts.paypal");
		
		if(!fp1.exists()) 
			{
				//System.out.println("Cannot locate file......");
			}
		else
		{
			ObjectInputStream ois1 = null;
			try
			{
				//System.out.println("Inside TRY.....!");
				FileInputStream fis1 = new FileInputStream(fp1);
				//System.out.println("Inside TRY.....!!");//file input stream
				ois1 = new ObjectInputStream(fis1);	
				//System.out.println("Inside TRY.....!!!");
				while((account2 = (PPRestrictedAccount)ois1.readObject()) != null)
				{
					//System.out.println(account2);
					total += account2.getAccountBal();
					RAaccounts.add(account2);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached when reading Restricted Accounts file...!!");
				//e.printStackTrace();
			}
			ois1.close();
		}
		
		
		/*--------------------------------------------------------------------*/
		
		
		File fp2 = new File("PPBusinessAccounts.paypal");
		
		if(!fp2.exists()) 
			{
				//System.out.println("Cannot locate file......");
			}
		else
		{
				ObjectInputStream ois2 = null;
				try
				{
					FileInputStream fis2 = new FileInputStream(fp2);
					ois2 = new ObjectInputStream(fis2);	
					while((account3 = (PPBusinessAccount)ois2.readObject()) != null)
					{
						//System.out.println(account3);
						total += account3.getAccountBal();
						BAaccounts.add(account3);
					}
				}
				catch(EOFException e)
				{
					
					//System.out.println("EOF reached when reading Business Accounts file...!!");
					//e.printStackTrace();
				}
				ois2.close();
		}
	}
	
	PPAccountActivationScreen() throws IOException, ClassNotFoundException
	{
		PPAccount account1 = null;
		PPRestrictedAccount account2 = null;
		PPBusinessAccount account3 = null;
		//System.out.println("Inside Constructor!!");
		accounts.clear();
		RAaccounts.clear();
		BAaccounts.clear();
		File fp = new File("PPAccounts.paypal");
		
		if(!fp.exists()) 
			{
				//System.out.println("Cannot locate file......");
			}
		
		ObjectInputStream ois = null;
		try
		{
			//System.out.println("Inside TRY.....!");
			FileInputStream fis = new FileInputStream(fp);
			//System.out.println("Inside TRY.....!!");//file input stream
			ois = new ObjectInputStream(fis);	
			//System.out.println("Inside TRY.....!!!");
			while((account1 = (PPAccount)ois.readObject()) != null)
			{
				//System.out.println(account1);
				accounts.add(account1);
			}
		}
		catch(EOFException e)
		{
			
			//System.out.println("EOF reached when reading Personal Accounts file!!");
			//e.printStackTrace();
		}
		ois.close();
		
		
		/*--------------------------------------------------------------------*/
		
		File fp1 = new File("PPRestrictedAccounts.paypal");
		
		if(!fp1.exists()) 
			{
				//System.out.println("Cannot locate file......");
			}
		else
		{
			ObjectInputStream ois1 = null;
			try
			{
				//System.out.println("Inside TRY.....!");
				FileInputStream fis1 = new FileInputStream(fp1);
				//System.out.println("Inside TRY.....!!");//file input stream
				ois1 = new ObjectInputStream(fis1);	
				//System.out.println("Inside TRY.....!!!");
				while((account2 = (PPRestrictedAccount)ois1.readObject()) != null)
				{
					//System.out.println(account2);
					RAaccounts.add(account2);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached when reading Restricted Accounts file...!!");
				//e.printStackTrace();
			}
			ois1.close();
		}
		
		
		/*--------------------------------------------------------------------*/
		
		
		File fp2 = new File("PPBusinessAccounts.paypal");
		
		if(!fp2.exists()) 
			{
				//System.out.println("Cannot locate file......");
			}
		else
		{
				ObjectInputStream ois2 = null;
				try
				{
					FileInputStream fis2 = new FileInputStream(fp2);
					ois2 = new ObjectInputStream(fis2);	
					while((account3 = (PPBusinessAccount)ois2.readObject()) != null)
					{
						//System.out.println(account3);
						BAaccounts.add(account3);
					}
				}
				catch(EOFException e)
				{
					
					//System.out.println("EOF reached when reading Business Accounts file...!!");
					//e.printStackTrace();
				}
				ois2.close();
		}
		
		/*------------------------------------------------------------------------------------*/
		
		Scanner inttsc = new Scanner(System.in);
		System.out.println("Which type of account do u want to activate:");
		System.out.println("1.Personal Account");
		System.out.println("2.Restricted Account");
		System.out.println("3.Business Account");
		System.out.println("4.Main Business Account");
		
		int ch = inttsc.nextInt();
		
		if(ch == 1)
		{
			PPAccountActivationScreen.show();
		}
		if(ch == 2)
		{
			PPAccountActivationScreen.show1();
		}
		if(ch == 3)
		{
			PPAccountActivationScreen.show11();
		}
		if(ch == 4)
		{
			PPAccountActivationScreen.show2();
		}
		
	}
	
public static void show() throws IOException, ClassNotFoundException {
		
		//System.out.print("At Starting of function....");
		//System.out.println(accounts);
		HashSet<PPAccount> hs = new HashSet<PPAccount>();
		hs.addAll(accounts);
		accounts.clear();
		accounts.addAll(hs);
		//System.out.print("At after of function....");
		//System.out.println(accounts);
		//String email = ""; //change to get console input
		
		/*
		 * TODO
		 * fetch the account object using email address
		 * check if the account is suspended
		 * if suspended then activate it
		 * if activation code invalid, retry for 2 more attempts
		 * on successful activation show main menu
		 * on failure show the error message and continue to main menu
		 */		
		//System.out.println("Inside Show.....");
		int tries = 3;
		Scanner sc = new Scanner(System.in);
		Scanner strsc = new Scanner(System.in);
		
		System.out.println("Enter Email Address:");
		String email = strsc.nextLine();
		
		System.out.println("Enter Activation Code:");
		String code = strsc.nextLine();
		tries--;
	
		PPAccount account = null;
		System.out.println(accounts.size());
		for(int i=0;i<accounts.size();i++)
		{
			//System.out.println("Inside Loop....");
			System.out.println(accounts.get(i).geetEmail());
			if(accounts.get(i).geetEmail().equals(email))
			{
				//System.out.println("Found Email....");
				account = accounts.get(i);
				boolean chek = account.checkactivationcode(code);
				if(chek == false)
				{
					System.out.println("You still have 2 tries");
					System.out.println("Enter Activation Code:");
					code = strsc.nextLine();
					chek = account.checkactivationcode(code);
					tries--;
				}
				if(chek == false)
				{
					System.out.println("You still have 1 tries");
					System.out.println("Enter Activation Code:");
					code = strsc.nextLine();
					chek = account.checkactivationcode(code);
					tries--;
				}
				
				if(chek)
				{
					boolean check = account.isActivated();
					
					if(check == true)
					{
						System.out.println("Do u want to suspend the account(press 1 for YES) and 2 for(NO)");
						int ch = sc.nextInt();
						if(ch == 1)
						{
							account.setActivated(false);
							accounts.set(i, account);
							System.out.println("Your account is Suspended..!!");
						}
						if(ch == 2)
						{
							account.setActivated(true);
							accounts.set(i, account);
						}
					}
					else
					{
						account.setActivated(true);
						accounts.set(i, account);
						System.out.println("Your account is activated!!");
					}
					
					
				}
				else
				{
					if(tries == 0) System.out.println("Exceeded maximum no of tries...");
					tries = 3;
				}
				
				
				
			}
			
		}
		
		
		
		//System.out.println("ACCOUNTS ARE:");
		//System.out.println(accounts);
		writeAccount(accounts);
		
		
		//check if account is active. if yes then ask for suspending it
		
		// if yes suspend it if not go back to main menu
		
		// accept activation code, check if valid, if not give 2 more attempts
		
		//proceed to main menu
		//MainMenu.show();
	
	}
		
	
	
public static void show1() throws IOException, ClassNotFoundException {
		
	    int tries = 3;
		//System.out.println("Inside Show.....");
		Scanner sc = new Scanner(System.in);
		Scanner strsc = new Scanner(System.in);
		
		System.out.println("Enter Email Address:");
		String email = strsc.nextLine();
		
		System.out.println("Enter Activation Code:");
		String code = strsc.nextLine();
		tries--;
	
		PPRestrictedAccount account = null;
		//System.out.println(RAaccounts.size());
		for(int i=0;i<RAaccounts.size();i++)
		{
			//System.out.println("Inside Loop....");
			//System.out.println(RAaccounts.get(i).geetEmail());
			if(RAaccounts.get(i).geetEmail().equals(email))
			{
				//System.out.println("Found Email....");
				account = RAaccounts.get(i);
				boolean chek = account.checkactivationcode(code);
				if(chek == false)
				{
					System.out.println("You still have 2 tries");
					System.out.println("Enter Activation Code:");
					code = strsc.nextLine();
					chek = account.checkactivationcode(code);
					tries--;
				}
				if(chek == false)
				{
					System.out.println("You still have 1 tries");
					System.out.println("Enter Activation Code:");
					code = strsc.nextLine();
					chek = account.checkactivationcode(code);
					tries--;
				}
				
				if(chek)
				{
					boolean check = account.isActivated();
					
					if(check == true)
					{
						System.out.println("Do u want to suspend the account(press 1 for YES) and 2 for(NO)");
						int ch = sc.nextInt();
						if(ch == 1)
						{
							account.setActivated(false);
						}
						if(ch == 2)
						{
							account.setActivated(true);
						}
					}
					else
					{
						account.setActivated(true);
						System.out.println("Your account is activated!!");
					}
					
					RAaccounts.set(i, account);
				}
								
			}
			else
			{
				if(tries == 0) System.out.println("Exceeded maximum no of tries...");
				tries = 3;
			}
			
		}
		
		//System.out.println(RAaccounts);
		writeAccount1(RAaccounts);
	
	}


public static void show11() throws IOException, ClassNotFoundException {
	
	int tries = 3;
	//System.out.println("Inside Show.....");
	Scanner sc = new Scanner(System.in);
	Scanner strsc = new Scanner(System.in);
	
	System.out.println("Enter Email Address:");
	String email = strsc.nextLine();
	
	System.out.println("Enter Activation Code:");
	String code = strsc.nextLine();

	PPRestrictedAccount account = null;
	//System.out.println(RAaccounts.size());
	for(int i=0;i<RAaccounts.size();i++)
	{
		//System.out.println("Inside Loop....");
		//System.out.println(RAaccounts.get(i).geetEmail());
		if(RAaccounts.get(i).geetEmail().equals(email))
		{
			//System.out.println("Found Email....");
			account = RAaccounts.get(i);
			boolean chek = account.checkactivationcodeB(code);
			if(chek == false)
			{
				System.out.println("You still have 2 tries");
				System.out.println("Enter Activation Code:");
				code = strsc.nextLine();
				chek = account.checkactivationcode(code);
				tries--;
			}
			if(chek == false)
			{
				System.out.println("You still have 1 tries");
				System.out.println("Enter Activation Code:");
				code = strsc.nextLine();
				chek = account.checkactivationcode(code);
				tries--;
			}
			
			if(chek)
			{
				boolean check = account.isActivatedB();
				
				if(check == true)
				{
					System.out.println("Do u want to suspend the account(press 1 for YES) and 2 for(NO)");
					int ch = sc.nextInt();
					if(ch == 1)
					{
						account.setActivatedB(false);
					}
					if(ch == 2)
					{
						account.setActivatedB(true);
					}
				}
				else
				{
					account.setActivatedB(true);
					System.out.println("Your account is activated!!");
				}
				
				RAaccounts.set(i, account);
			}
			else
			{
				if(tries == 0) System.out.println("Exceeded maximum no of tries...");
				tries = 3;
			}
							
		}
		
	}
	
	//System.out.println(RAaccounts);
	writeAccount1(RAaccounts);

}



	public static void show2() throws IOException, ClassNotFoundException {
	
	//System.out.println("Inside Show.....");
	Scanner sc = new Scanner(System.in);
	Scanner strsc = new Scanner(System.in);
	
	System.out.println("Enter Email Address:");
	String email = strsc.nextLine();
	
	System.out.println("Enter Activation Code:");
	String code = strsc.nextLine();

	PPBusinessAccount account = null;
	System.out.println(BAaccounts.size());
	for(int i=0;i<BAaccounts.size();i++)
	{
		//System.out.println("Inside Loop....");
		//System.out.println(BAaccounts.get(i).geetEmail());
		if(BAaccounts.get(i).geetEmail().equals(email))
		{
			//System.out.println("Found Email....");
			account = BAaccounts.get(i);
			boolean chek = account.checkactivationcode(code);
			if(chek)
			{
				boolean check = account.isActivated();
				
				if(check == true)
				{
					System.out.println("Do u want to suspend the account(press 1 for YES) and 2 for(NO)");
					int ch = sc.nextInt();
					if(ch == 1)
					{
						account.setActivated(false);
					}
					if(ch == 2)
					{
						account.setActivated(true);
					}
				}
				else
				{
					account.setActivated(true);
					System.out.println("Your account is activated!!");
				}
				
				BAaccounts.set(i, account);
			}
							
		}
		
	}
	
			
	writeAccount2(BAaccounts);

}

	
	
	public static void writeAccount(ArrayList<PPAccount> accs) throws IOException
	{
		File fp1 = new File("PPAccounts.paypal");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		oos = new ObjectOutputStream(fos);
		//System.out.println(accs.size());
		for(int i=0;i<accs.size();i++)
		{
			//System.out.println(accs.get(i));
			oos.writeObject(accs.get(i));
		}
		oos.flush();
		oos.close();
	}
	
	
	public static void writeAccount1(ArrayList<PPRestrictedAccount> accs) throws IOException
	{
		File fp1 = new File("PPRestrictedAccounts.paypal");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		oos = new ObjectOutputStream(fos);
	//	System.out.println(accs.size());
		for(int i=0;i<accs.size();i++)
		{
			//System.out.println(accs.get(i));
			oos.writeObject(accs.get(i));
		}
		oos.flush();
		oos.close();
	}
	
	public static void writeAccount2(ArrayList<PPBusinessAccount> accs) throws IOException
	{
		File fp1 = new File("PPBusinessAccounts.paypal");
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		oos = new ObjectOutputStream(fos);
		//System.out.println(accs.size());
		for(int i=0;i<accs.size();i++)
		{
			//System.out.println(accs.get(i));
			oos.writeObject(accs.get(i));
		}
		oos.flush();
		oos.close();
	}

}