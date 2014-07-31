package in.msitprogram.iiit.paypal.accounts;

import java.io.Serializable;

public class Transaction implements Serializable
{
	String tTime;
	String tDate;
	PPAccount account;
	String narration;
	String reference;
	String status;
	float debit;
	float credit;
	
	
	public String gettTime() {
		return tTime;
	}

	public void settTime(String tTime) {
		this.tTime = tTime;
	}

	public String gettDate() {
		return tDate;
	}

	public void settDate(String tDate) {
		this.tDate = tDate;
	}

	public PPAccount getAccount() {
		return account;
	}

	public void setAccount(PPAccount account) {
		this.account = account;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getDebit() {
		return debit;
	}

	public void setDebit(float debit) {
		this.debit = debit;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}


	
	public String toString()
	{
		//return the description of the transaction 
		String s = "Time :" + tTime + " " + " Date: " + tDate +" " + " Narration :" + narration + " Reference :" + reference + " Debit: " + debit + " Credit: " + credit;
		return s;
	}
	
}
