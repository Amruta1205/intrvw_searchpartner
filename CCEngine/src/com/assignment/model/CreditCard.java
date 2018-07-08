package com.assignment.model;
import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreditCard {

	private String cardType;
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
	public CreditCard(long cNo, creditCardType cardType) {
		this.creditCardNo = cNo;
		this.discardNumber = false;
		this.cardType = cardType.getType();
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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

@Override
public String toString() {
	String validStatus = (this.isDiscardNumber() ? " Invalid-Discard " : " Valid");
	return "CreditCard Type : " + this.getCardType() + " , Credit Card No : " + this.getCreditCardNo() +
			" , ValidStatus : " + validStatus + " , Expiry Date : " + this.expiryDate; 
}	

@Override
public boolean equals(Object obj) {

	CreditCard obj2 = (CreditCard)obj;
	return this.creditCardNo==obj2.getCreditCardNo() && this.cardType.equals(obj2.getCardType());
}
}
