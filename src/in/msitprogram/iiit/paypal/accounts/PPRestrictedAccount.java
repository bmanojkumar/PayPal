package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;
import java.util.ArrayList;

public class PPRestrictedAccount extends PPAccount implements Serializable{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 4344632058782201932L;
	private String   parentEmail;
	private float    withdrawLimit;
	private float    businesswithdrawlimit;
	private String   businessaccount;
	private String   parentaccount;
	private boolean  isActivatedB;
	private String   activationCodeB;


	public boolean checkactivationcodeB(String actcode)
	{
		return actcode.equals(this.activationCodeB);
	}
	public String getActivationCodeB() {
		return activationCodeB;
	}

	public void setActivationCodeB(String activationCodeB) {
		this.activationCodeB = activationCodeB;
	}

	public boolean isActivatedB() {
		return isActivatedB;
	}

	public void setActivatedB(boolean isActivatedB) {
		this.isActivatedB = isActivatedB;
	}

	public String getParentaccount() {
		return parentaccount;
	}

	public void setParentaccount(String parentaccount) {
		this.parentaccount = parentaccount;
	}

	public String getBusinessaccount() {
		return businessaccount;
	}

	public void setBusinessaccount(String businessaccount) {
		this.businessaccount = businessaccount;
	}

	public float getBusinesswithdrawlimit() {
		return businesswithdrawlimit;
	}

	public void setBusinesswithdrawlimit(float businesswithdrawlimit) {
		this.businesswithdrawlimit = businesswithdrawlimit;
	}

	public PPRestrictedAccount(Profile profile) {
		super(profile);
	}
	
	public void setEmail(String email)
	{
		super.setEmail(email);
	}
	
	
	public void setAccBal(float bal)
	{
		super.setAccountBal(bal);
	}
	
	public void setisActivated(boolean k)
	{
		super.setActivated(k);
	}
	
	public void setactivationCode(String code)
	{
		super.setActivationCode(code);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return super.getTransactions();
	}


	public void setTransactions(ArrayList<Transaction> transactions) {
		super.setTransactions(transactions);
	}
	
	public String getParentEmail() {
		return parentEmail;
	}


	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}


	public float getWithdrawLimit() {
		return withdrawLimit;
	}


	public void setWithdrawLimit(float withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	public String toString() {
		
		return "Name	Address		Phone	Email	 Parent-Email 	Remaining-Student Balance	Student-Activated(?)	Student-Code	Business-Email	Business-Withdraw-Limit		Business-Activated	Business-Code" + "\n" + super.getProfile().getName() + "	" + super.getProfile().getAddress() + "		" + super.getProfile().getPhone() + "	" + this.geetEmail() + "   " + this.parentEmail + "    	" + this.withdrawLimit + "		" + super.isActivated() + "	" + super.getActivationCode() + "	"	+ this.businessaccount + "	 " + this.businesswithdrawlimit + "   " + this.isActivatedB + "   " + this.activationCodeB + "\n";
		
        //return String.format("Address[street: %s, city: %s]", street, city);
    }
	
	

}
