package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;

public class Profile implements Serializable{
	
	private String name;
	private String address;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString()
	{
		return this.name + "   " + this.phone + "  " + this.address;
		// implement this function to return a beautiful looking string
		// to display the summary of the account
		//return profile.name + profile.address + profile.phone + accountBal + isActivated;;
	}
	
}
