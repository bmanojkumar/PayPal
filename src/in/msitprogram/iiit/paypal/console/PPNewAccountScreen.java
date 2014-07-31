package in.msitprogram.iiit.paypal.console;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;
import in.msitprogram.iiit.paypal.accounts.Profile;
import in.msitprogram.iiit.paypal.persistance.DataStore;
import in.msitprogram.iiit.paypal.utils.PPToolkit;

public class PPNewAccountScreen {
	Profile profile;
	PPAccount account;
	PPRestrictedAccount Raccount;
	PPBusinessAccount Baccount;
	String email;
	Scanner scan;
	Scanner intscan;
	private ArrayList <PPRestrictedAccount> accountOperators = new ArrayList<PPRestrictedAccount>();
	
	public PPNewAccountScreen(String email) {
		profile = new Profile();
		scan = new Scanner(System.in);
		intscan = new Scanner(System.in);
		this.email = email;
	}

	public void show() throws IOException, ClassNotFoundException {
		//check if the account with the given email address exists //if not create the user profile	//show the account types //based on the given account type selected create the account object
		 System.out.println("1.Create Personal Account");
		 System.out.println("2.Create Student Account");
		 System.out.println("3.Create Business Account");
		 System.out.println("");
		 System.out.println("Enter Choice:");
		 int ch = intscan.nextInt();
		 
		 if(ch == 1)
		 {
			 account = DataStore.lookupAccount(email);
			 
			 if(account == null) createPersonalAccount();
			 else System.out.println("Account already Exists!!Try different email address!!");
		 }
		 
		 if(ch == 2)
		 {
			 account = null;
			 account = DataStore.lookupAccount(email);
			
			 
			 
			 if(account != null) 
			 {
				 createStudentAccount();
			 }
			 else System.out.println("Account does not exist!!");
		 }
		 
		 if(ch == 3)
		 {
			 
				 createBusinessAccount();
		 }
		
	}

	private Profile createProfile() {
		// use this for creating the profile
		
		Profile profile = new Profile();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Name:");
		profile.setName(sc.nextLine());
		
		System.out.println("Enter Address:");
		profile.setAddress(sc.nextLine());
		
		System.out.println("Enter Phone:");
		profile.setPhone(sc.nextLine());
		
		return profile;
	}

	private void createBusinessAccount() throws IOException, ClassNotFoundException {
		//use this for creating the business account
		Scanner st = new Scanner(System.in);
		Scanner flst = new Scanner(System.in);
		Scanner intst = new Scanner(System.in);
		Baccount = DataStore.lookupAccount11(email);
		if(Baccount == null)
		{
			profile = createProfile();
			Baccount = new PPBusinessAccount(profile);
			Baccount.setEmail(email);
			System.out.println("Enter total amount in business account:");
			Baccount.setAccBal(flst.nextFloat());
			Baccount.setActivated(false);
			String code2 = PPToolkit.generateActivationCode();
			Baccount.setActivationCode(code2);
			System.out.println("Activation code is: " + code2);
			
			System.out.println("Enter no.of accounts you want to have in business accounts:");
			int no = intst.nextInt();
			
			for(int i=0;i<no;i++)
			{
				System.out.println("Enter the restricted account you want to add:");
				String accnt = st.nextLine();
				PPRestrictedAccount ra = DataStore.lookupAccount1(accnt);
				if(ra != null)
				{
					System.out.println("Enter limit of amount:");
					ra.setBusinesswithdrawlimit(flst.nextFloat());
					ra.setBusinessaccount(email);
					ra.setActivatedB(false);
					String code3 = PPToolkit.generateActivationCode();
					ra.setActivationCodeB(code3);
					System.out.println("Activation code for Business account is: " + code3);
					DataStore.updateRestricted(ra);
					accountOperators.add(ra);										
				}
				else
				{
					System.out.println("Restricted Account does not exist");
					i--;
				}
			}
			
			Baccount.setAccountOperators(accountOperators);
			DataStore.writeAccount(Baccount);
			
		}
		
	}

	private void createStudentAccount() throws IOException, ClassNotFoundException {
		//use this for creating the student account 
		Scanner st = new Scanner(System.in);
		Scanner flst = new Scanner(System.in);
		System.out.println("Enter activation code:");
		String code = st.nextLine();
		
		boolean check = account.checkactivationcode(code);
		
		if(!check)
		{
			System.out.println("Wrong code!!");
		}
		else
		{
			System.out.println("Enter email address:");
			String mail = st.nextLine();
			System.out.println(mail);
			Raccount = DataStore.lookupAccount1(mail);
			if(Raccount == null)
			{
				profile = createProfile();
				Raccount = new PPRestrictedAccount(profile);
				Raccount.setParentEmail(email);
				System.out.println("Enter withdraw limit:");
				Raccount.setWithdrawLimit(flst.nextFloat());
				Raccount.setEmail(mail);
//				System.out.println("Setting parent email....1");
//				System.out.println("EMAIL --->" + email);
				Raccount.setParentaccount(email);
//				System.out.println("Setting parent email....2");
				Raccount.setActivated(false);
				String code2 = PPToolkit.generateActivationCode();
				Raccount.setActivationCode(code2);
				System.out.println("Activation code is: " + code2);	
//				System.out.println("ACCOUNT IS ----->" + Raccount);
				DataStore.writeAccount(Raccount);
				
				
				/*PPAccount account = null;
				account = new PPAccount(profile);
				account.setEmail(email);
				//System.out.println("Enter account balance:");
				account.setAccountBal(flst.nextFloat());
				account.setActivated(false);
				String code3 = PPToolkit.generateActivationCode();
				account.setActivationCode(code3);
				System.out.println("Activation code is: " + code3);
				System.out.println("Transaction Complete..Personal Student Account!!"); */
				
		

			}
			else
			{
				System.out.println("Student account already exists!!");
			}
			
		}
		
	}

	/*
	 * this method create the personal account, saves it to the file system
	 * and redirects the users to the next screen
	 */
	private void createPersonalAccount() throws IOException, ClassNotFoundException {
		//use this for creating the personal account
		System.gc();
		Scanner st = new Scanner(System.in);
		profile = createProfile();
		account = new PPAccount(profile);
		account.setEmail(email);
		System.out.println("Enter account balance:");
		account.setAccountBal(st.nextFloat());
		account.setActivated(false);
		String code = PPToolkit.generateActivationCode();
		account.setActivationCode(code);
		System.out.println("Activation code is: " + code);
		System.out.println("Transaction Complete!!");
		DataStore.writeAccount(account);
		
		
	}
	
	private void completeAccountCreation() throws IOException{
		//generate activation code and set it to account
		
		//send activation code to the phone
		
		//ask & redirect the user to the activation screen or the main menu
		
	}

}
