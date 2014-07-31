package in.msitprogram.iiit.paypal.persistance;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import in.msitprogram.iiit.paypal.accounts.PPAccount;
import in.msitprogram.iiit.paypal.accounts.PPBusinessAccount;
import in.msitprogram.iiit.paypal.accounts.PPRestrictedAccount;

public class DataStore 
{
	private static ArrayList<PPAccount> accounts = new ArrayList<PPAccount>();
	private static ArrayList<PPRestrictedAccount> RAaccounts = new ArrayList<PPRestrictedAccount>();
	private static ArrayList<PPBusinessAccount> BAaccounts = new ArrayList<PPBusinessAccount>();
	private static File fp;
	private static ObjectOutputStream oos;
	private static ObjectOutputStream oos1;
	private static ObjectOutputStream oos11;

	public static PPAccount lookupAccount(String email) throws IOException, ClassNotFoundException 
	{
			
		
		
		PPAccount account = null; 
		fp = new File("PPAccounts.paypal");
		if(!fp.exists())
		{
			account = null;
			fp.createNewFile();
			return null;
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
					//System.out.println("Inside......");
					//System.out.println(account.geetEmail());
					if(account.geetEmail().equals(email))
						return account;
					//
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
			//ois.close();
		}
		return null;
	}
	
	public static PPRestrictedAccount lookupAccount1(String email) throws IOException, ClassNotFoundException 
	{
		PPRestrictedAccount account = null; 
		fp = new File("PPRestrictedAccounts.paypal");
		if(!fp.exists())
		{
			account = null;
			fp.createNewFile();
			return null;
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPRestrictedAccount)ois.readObject()) != null)
				{
					//System.out.println(account);
					if(account.geetEmail().equals(email))
						return account;
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
			//ois.close();
		}
		return null;
	}
	
	public static PPBusinessAccount lookupAccount11(String email) throws IOException, ClassNotFoundException 
	{
		PPBusinessAccount account = null; 
		fp = new File("PPBusinessAccounts.paypal");
		if(!fp.exists())
		{
			account = null;
			fp.createNewFile();
			return null;
		}
		else
		{
			ObjectInputStream ois = null;
			try
			{
				FileInputStream fis = new FileInputStream(fp);			//file input stream
				ois = new ObjectInputStream(fis);	
				while((account = (PPBusinessAccount)ois.readObject()) != null)
				{
					//System.out.println(account);
					if(account.geetEmail().equals(email))
						return account;
				}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
			//ois.close();
			System.gc();
		}
		return null;
	}
			
		
	public static void writeAccount(PPAccount account) throws IOException, ClassNotFoundException
	{
		//System.out.println("BEFORE CLEAR...");
		//System.out.println(accounts);
		accounts.clear();
		//System.out.println("AFTER CLEAR...");
		//System.out.println(accounts);
		
		
		File fp1 = new File("PPAccounts.paypal");
		PPAccount account1;
		ObjectInputStream ois = null;
		try
		{
			
			FileInputStream fis = new FileInputStream(fp1);			//file input stream
			ois = new ObjectInputStream(fis);	
			while((account1 = (PPAccount)ois.readObject()) != null)
			{
				//System.out.println("INSIDE LOOP" + account1);
				accounts.add(account1);
			}
		}
		catch(EOFException e)
		{
			
			//System.out.println("EOF reached IN PA WRITE!!");
			//e.printStackTrace();
		}
		if(ois !=null)	ois.close();
		//else ois.close();
		
		//System.out.println("BEFORE...");
		//System.out.println(accounts);
		accounts.add(account);
		//System.out.println("AFTER...");
		//System.out.println(accounts);
		
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		for(int i=0;i<accounts.size();i++)
		{
			oos.writeObject(accounts.get(i));
		}
		
		oos.close();
		
		
		
	}
	
	public static void writeAccount(PPRestrictedAccount account) throws IOException, ClassNotFoundException
	{
		RAaccounts.clear();
		File fp1 = new File("PPRestrictedAccounts.paypal");
		PPRestrictedAccount account1;
		ObjectInputStream ois = null;
		try
		{
			
			FileInputStream fis = new FileInputStream(fp1);			//file input stream
			ois = new ObjectInputStream(fis);	
			while((account1 = (PPRestrictedAccount)ois.readObject()) != null)
			{
				//System.out.println(account1);
				RAaccounts.add(account1);
			}
		}
		catch(EOFException e)
		{
			
			//System.out.println("EOF reached!!");
			//e.printStackTrace();
		}
		
		if(ois !=null)	ois.close();
		//else ois.close();
		RAaccounts.add(account);
		
		
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos1 = new ObjectOutputStream(fos);
		for(int i=0;i<RAaccounts.size();i++)
		{
			oos1.writeObject(RAaccounts.get(i));
			System.out.println("Restricted Account Created Successfully!!");
		}
		
		//oos1.close();
		System.gc();
		
	}
	
	public static void writeAccount(PPBusinessAccount account) throws IOException, ClassNotFoundException
	{
		System.gc();
		File fp2 = new File("PPBusinessAccounts.paypal");
		PPBusinessAccount account1;
		ObjectInputStream ois11 = null;
		
		if(fp2.exists())
		{
			try
			{
					FileInputStream fis2 = new FileInputStream(fp2);			//file input stream
					
					if(fis2.available() != 0)
					{
						//System.out.println("Available Bytes are		:" + fis2.available());
						//System.out.println("File handler value:  " + fis2);
						ois11 = new ObjectInputStream(fis2);
							
						while((account1 = (PPBusinessAccount)ois11.readObject()) != null)
						{
							//System.out.println(account1);
							BAaccounts.add(account1);
						}
					
						if(ois11 !=null)	ois11.close();
						//else ois.close();
					}
			}
			catch(EOFException e)
			{
				
				//System.out.println("EOF reached!!");
				//e.printStackTrace();
			}
					
		}
		BAaccounts.add(account);
		
		System.gc();
		
		FileOutputStream fos = new FileOutputStream(fp2);  // file output stream
		ObjectOutputStream oos11 = new ObjectOutputStream(fos);
		for(int i=0;i<BAaccounts.size();i++)
		{
			oos11.writeObject(BAaccounts.get(i));
		}
		
		oos11.close();
	}
	
	public static void updateRestricted(PPRestrictedAccount account) throws IOException, ClassNotFoundException
	{
		RAaccounts.clear();
		File fp1 = new File("PPRestrictedAccounts.paypal");
		PPRestrictedAccount account1;
		ObjectInputStream ois = null;
		try
		{
			
			FileInputStream fis = new FileInputStream(fp1);			//file input stream
			ois = new ObjectInputStream(fis);	
			while((account1 = (PPRestrictedAccount)ois.readObject()) != null)
			{
				//System.out.println(account1);
				RAaccounts.add(account1);
			}
		}
		catch(EOFException e)
		{
			
			//System.out.println("EOF reached!!");
			//e.printStackTrace();
		}
		
		if(ois !=null)	ois.close();
		//else ois.close();
		//RAaccounts.add(account);
		
		for(int i=0;i<RAaccounts.size();i++)
		{
			if(RAaccounts.get(i).geetEmail().equals(account.geetEmail()))
			{
				RAaccounts.set(i, account);
				break;
			}
		}
		
		
		FileOutputStream fos = new FileOutputStream(fp1);  // file output stream
		ObjectOutputStream oos1 = new ObjectOutputStream(fos);
		for(int i=0;i<RAaccounts.size();i++)
		{
			oos1.writeObject(RAaccounts.get(i));
			//System.out.println("Restricted Account Created Successfully!!");
		}
		
		//oos1.close();
		System.gc();
	}
	
}
