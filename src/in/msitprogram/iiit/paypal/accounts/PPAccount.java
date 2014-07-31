package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;
import java.util.ArrayList;

public class PPAccount implements Serializable
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Profile profile;
	private String email;
	private float accountBal;
	private boolean isActivated;
	private String activationCode;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}


	public String getActivationCode() {
		return activationCode;
	}


	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}


	public boolean isActivated() {
		return isActivated;
	}


	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}


	public float getAccountBal() {
		return accountBal;
	}


	public void setAccountBal(float accountBal) {
		this.accountBal = accountBal;
	}


	public String geetEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Profile getProfile() {
		return profile;
	}

	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	
	
	
	public PPAccount(Profile profile) {
		// TODO Auto-generated constructor stub
		this.profile = profile;
	}

	public String toString()
	{
		return "Name	Address		Phone	Email	 Balance	Activated(?)	Code" + "\n" + profile.getName() + "	" + profile.getAddress() + "		" + profile.getPhone() + "	" + this.geetEmail() + "   " + this.getAccountBal() + "    	" + this.isActivated + "		" + this.getActivationCode() + "\n";
		// implement this function to return a beautiful looking string
		// to display the summary of the account
		//return profile.name + profile.address + profile.phone + accountBal + isActivated;;
	}

	public void activate(String activationCode) 
	{
		
		// TODO Auto-generated method stub
		if(activationCode.equals(this.activationCode))
		{
			isActivated = true;
		}
		
	}
	
	public void suspend() 
	{
		// TODO Auto-generated method stub
		isActivated = false;
	}


	public boolean withdraw(float withdrawAmount) {
		
		if(accountBal >= withdrawAmount)
		{
			accountBal -= withdrawAmount;
			return true;
		}
		else
		{
			return false;
		}
	}


	public boolean addFunds(float creditAmount) 
	{
		
		if(creditAmount > 0)
		{
			accountBal += creditAmount;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean sendMoney(float creditAmount) 
	{
		
		return false;
	}
	
	public boolean requestMoney(float creditAmount) 
	{
		
		return false;
	}

	
	
	public boolean checkactivationcode(String actcode)
	{
		return actcode.equals(this.activationCode);
	}
	
	
	
	
}
