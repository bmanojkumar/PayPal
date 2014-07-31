/**
 * 
 */
package in.msitprogram.iiit.paypal.console;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Transaction;
import in.msitprogram.iiit.paypal.persistance.DataStore;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author manoj
 *
 */
public class MainMenu {
	
	public static void show() throws IOException, ClassNotFoundException{
		//show the welcome message with the main menu options
		
		//take the menu option as input from the console
		
		//take email address as input from the console
		
		//based on the given menu option instantiate the respective screens
		PrintWriter pw = new PrintWriter(System.out,true);
		Scanner sc = new Scanner(System.in);
		Scanner strsc = new Scanner(System.in);
		
			pw.println("------------------------------------------------MENU---------------------------------------------");
			pw.println("1.Create Account"); 
			pw.println("2.Activate/Suspend your Account");
			pw.println("3.Login");
			pw.println("4.List Details of an account");
			pw.println("5.List Transactions");
			/*pw.println("3.Login to Personal Account");
			pw.println("4.Login to Student Account");
			pw.println("5.Login to Business Account");*/
			/*pw.println("6.List Personal Accounts");
			pw.println("7.List Restricted Accounts");
			pw.println("8.List Business Accounts");*/
			
			/*pw.println("9.List all Personal Account Transactions of an account");
			pw.println("10.List all Restricted Account Transactions of an account");
			pw.println("11.List all Business Account Transactions of an account");*/
			
			//pw.println("6.Login to Main Business Account");
			pw.println("6.List all different types of accounts");
			pw.println("7.Display total amount in bank");
			pw.println("8.Exit");
			pw.println("-------------------------------------------------------------------------------------------------");
			
			int ch = sc.nextInt();
			if(ch == 8)
			{
				System.out.println("You have been EXITED!!");
				System.exit(0);
			}
			
			if(ch == 1)
			{
				System.out.println("Enter Email Address:");
				String email = strsc.nextLine();
				PPNewAccountScreen newacc = new PPNewAccountScreen(email);
				newacc.show();
			}
			if(ch == 2)
			{
				System.out.println("2");
				PPAccountActivationScreen nk = new PPAccountActivationScreen();
				//PPAccountActivationScreen.show();
			}
			if(ch == 3)
			{
				pw.println("1.Login to Personal 	 Account");
				pw.println("2.Login to Student  	 Account");
				pw.println("3.Login to Business 	 Account");
				pw.println("4.Login to Main Business Account");
				
				int k = sc.nextInt();
				
				if(k == 1)
				{
					System.out.println("Enter Email Address:");
					String email = strsc.nextLine();
					PPAccountScreen ps = new PPAccountScreen(email);
					ps.show();
				}
				if(k == 2)
				{
					System.out.println("Enter Email Address:");
					String email = strsc.nextLine();
					PPAccountScreen ps = new PPAccountScreen(email);
					ps.PPRestrictedAccount(email);
					ps.show1();
				}
				if(k == 3)
				{
					System.out.println("Enter Email Address:");
					String email = strsc.nextLine();
					PPAccountScreen ps = new PPAccountScreen(email);
					ps.PPRestrictedAccount(email);
					ps.PPBusinessAccount(email);
					ps.show2();
				}
				if(k == 4)
				{
					LMBA();
				}
			}
			if(ch == 4)
			{
				pw.println("1.List details of Personal   Accounts");
				pw.println("2.List details of Restricted Accounts");
				pw.println("3.List details of Business   Accounts");
				
				int kk = sc.nextInt();
				
				if(kk == 1)
				{
					PPAD();
				}
				if(kk == 2)
				{
					RSAD();
				}
				if(kk == 3)
				{
					BBAD();
				}
				
			}
			if(ch == 5)
			{
				pw.println("1.List Transactions of Personal 	Account");
				pw.println("2.List Transactions of Restricted Accounts");
				pw.println("3.List Transactions of Business   Accounts");
				
				int kk = sc.nextInt();
				
				if(kk == 1)
				{
					PPAL();
				}
				if(kk == 2)
				{
					RSAL();
				}
				if(kk == 3)
				{
					BBAL();
				}
			}
			
					
			if(ch == 6)
			{
				PPAccountActivationScreen.count();
				System.out.println("No of Personal Accounts are: " + PPAccountActivationScreen.getnoofPPA());
				System.out.println("No of Student  Accounts are: " + PPAccountActivationScreen.getnoofPPR());
				System.out.println("No of Business Accounts are: " + PPAccountActivationScreen.getnoofPPB());
			}
			
			
			if(ch == 7)
			{
				PPAccountActivationScreen.count();
				System.out.println("The total amount in bank is: " + PPAccountActivationScreen.total);
			}
		
	}
	
	public static void PPAD() throws ClassNotFoundException, IOException
	{
		PPAccount account = null; 
		File fp = new File("PPAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPAccount)ois.readObject()) != null)
				{
					System.out.println(account);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
		
	}//end of PPAD()
	
	public static void RSAD() throws ClassNotFoundException, IOException
	{
		PPRestrictedAccount account = null; 
		File fp = new File("PPRestrictedAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPRestrictedAccount)ois.readObject()) != null)
				{
					System.out.println(account);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
		
	}//end of RSAD()
	public static void BBAD() throws ClassNotFoundException, IOException
	{
		PPBusinessAccount account = null; 
		File fp = new File("PPBusinessAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPBusinessAccount)ois.readObject()) != null)
				{
					System.out.println(account);
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
		
	}//end of BBAD()
	
	public static void PPAL() throws ClassNotFoundException, IOException
	{
		Scanner st = new Scanner(System.in);
		ArrayList<Transaction> transactions = null;
		System.out.println("Enter the email address");
		String email = st.nextLine();
		
		
		PPAccount account = null; 
		File fp = new File("PPAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPAccount)ois.readObject()) != null)
				{
					if(account.geetEmail().equals(email))
					{
						transactions = account.getTransactions();
						break;
					}
				}
				
				for(int i=0;i<transactions.size();i++)
				{
					System.out.println(transactions.get(i));
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
	}//end of PPAL()
	
	public static void RSAL() throws ClassNotFoundException, IOException
	{
		Scanner st = new Scanner(System.in);
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		System.out.println("Enter the email address of restrcited account..");
		String email = st.nextLine();
		
		
		PPRestrictedAccount account = null; 
		File fp = new File("PPRestrictedAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPRestrictedAccount)ois.readObject()) != null)
				{
					if(account.geetEmail().equals(email))
					{
						transactions = account.getTransactions();
						break;
					}
				}
				
				for(int i=0;i<transactions.size();i++)
				{
					System.out.println(transactions.get(i));
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
	}//end of RSAL()
	
	public static void BBAL() throws ClassNotFoundException, IOException
	{
		Scanner st = new Scanner(System.in);
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		System.out.println("Enter the email address of Business account..");
		String email = st.nextLine();
		
		
		PPBusinessAccount account = null; 
		File fp = new File("PPBusinessAccounts.paypal");
		if(!fp.exists())
		{
			//System.out.println("Error locating file...");
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				
				//System.out.println("Outside......");
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPBusinessAccount)ois.readObject()) != null)
				{
					if(account.geetEmail().equals(email))
					{
						transactions = account.getTransactions();
						break;
					}
				}
				
				for(int i=0;i<transactions.size();i++)
				{
					System.out.println(transactions.get(i));
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				
			}
			
		}
	}//end of BBAL()
	
	public static void LMBA() throws ClassNotFoundException, IOException
	{
		System.out.println("inside...busness");
		int tries = 3;
		ArrayList<PPBusinessAccount> BAaccounts = new ArrayList<PPBusinessAccount>();
		PPBusinessAccount raaccount1 = null; 		
		File fp1 = new File("PPBusinessAccounts.paypal");
		ObjectInputStream ois1 = null;
			try
			{
				
				FileInputStream fis1 = new FileInputStream(fp1);			//file input stream
				ois1 = new ObjectInputStream(fis1);	
				while((raaccount1 = (PPBusinessAccount)ois1.readObject()) != null)
				{
					System.out.println("Business" + raaccount1);
					BAaccounts.add(raaccount1);
				}
			}
			catch(EOFException e)
			{
				
//				System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
		if(ois1 !=null)	ois1.close();
		
		Scanner intscc = new Scanner(System.in);
		Scanner strscc = new Scanner(System.in);
		
		System.out.println("Enter business email...");
		String eemail = strscc.nextLine();
		
		PPBusinessAccount bs = DataStore.lookupAccount11(eemail);
		if(bs == null) //System.out.println("Cant find email...");
		
			System.out.println(bs.isActivated());
			
		if(!bs.isActivated()) System.out.println("Your Account is not Activated..!");
		else
		{
		
				System.out.println("Enter the activation code..!!");
				String atc = strscc.nextLine();
				
				boolean checck = bs.checkactivationcode(atc);
				if(checck == false) System.out.println("Wrong Code..!!");
				else
				{
		
					System.out.println("1.Add Funds");
					System.out.println("2.Withdraw Funds");
					
					System.out.println("Enter choice:");
					int chh = intscc.nextInt();
					
					
					if(chh == 1)
					{
						System.out.println("Enter the amount to add");
						int funds = intscc.nextInt();
						
						for(int i=0;i<BAaccounts.size();i++)
						{
							if(BAaccounts.get(i).geetEmail().equals(bs.geetEmail()))
							{
								//System.out.println("Found email....");
								bs.setAccBal(bs.getAccountBal() +  funds);
								BAaccounts.set(i, bs);
							}
						}
					}
					
					if(chh == 2)
					{
						System.out.println("Enter the amount to withdraw");
						int funds = intscc.nextInt();
						
						for(int i=0;i<BAaccounts.size();i++)
						{
							if(BAaccounts.get(i).geetEmail().equals(bs.geetEmail()))
							{
//								System.out.println("Found email....");
								if(bs.getAccountBal() -  funds > 0)
								{
								bs.setAccBal(bs.getAccountBal() -  funds);
								BAaccounts.set(i, bs);
								}
								else
								{
									System.out.println("not enough balance");
								}
							}
						}
					}
					
					ObjectOutputStream ios=null;
					for(int i=0;i<BAaccounts.size();i++)
					{
						File fp = new File("PPBusinessAccounts.paypal");
						FileOutputStream fs = new FileOutputStream(fp);
						ios = new ObjectOutputStream(fs);
						
						ios.writeObject(BAaccounts.get(i));
					}
					ios.close();
					
					System.gc();
				}
		}
	}//login to main Business Account

}//end of class
