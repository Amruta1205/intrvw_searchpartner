package com.assignment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="creditCardList")
public class CreditCardist {
	  
	@XmlElement(name = "cards")
	  private List<CreditCard> cardList;

	public List<CreditCard> getCardList() {
		return cardList;
	}

	public void setCardList(List<CreditCard> cardList) {
		this.cardList = cardList;
	}
	
	
	
}
