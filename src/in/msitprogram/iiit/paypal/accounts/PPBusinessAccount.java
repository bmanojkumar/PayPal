package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;
import java.util.ArrayList;

public class PPBusinessAccount extends PPAccount implements Serializable{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 6336241787099656735L;
	private ArrayList <PPRestrictedAccount> accountOperators;
	//private float totalAmount;
	Profile profile;

	public ArrayList <PPRestrictedAccount> getAccountOperators() {
		return accountOperators;
	}

	public void setAccountOperators(ArrayList <PPRestrictedAccount> accountOperators) {
		this.accountOperators = accountOperators;
	}


	public PPBusinessAccount(Profile profile) {
		super(profile);
		new ArrayList<PPRestrictedAccount>();
	}
		
	public void setEmail(String email)
	{
		super.setEmail(email);
	}
	
	public String getEmail()
	{
		return super.geetEmail();
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
	
	public void addAccountOperator(PPRestrictedAccount accountOperator){
		//add account operators after checking if there are duplicates
	}

}
