package com.assignment.model;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreditCard {

	private long creditCardNo;
	private Date expiryDate;
	private boolean discardNumber;
	
	public CreditCard() {
		this.discardNumber = false;
	}
	public CreditCard(long cNo) {
		this.creditCardNo = cNo;
		this.discardNumber = false;
	}
	
	public long getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(long creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public boolean isDiscardNumber() {
		return discardNumber;
	}
	public void setDiscardNumber(boolean discardNumber) {
		this.discardNumber = discardNumber;
	}

	
	
}
