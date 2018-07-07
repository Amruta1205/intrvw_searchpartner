package com.assignment.processors;

import java.util.ArrayList;

import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class CreditCardNumberProcessor {

	LuhnNumberGenerator CCGenerator;
	
	public CreditCardNumberProcessor() {
		CCGenerator = LuhnNumberGenerator.getInstance();
	}
	
	
	public ArrayList<CreditCard> getCreditCardNumbers(creditCardType cardType, int count){		
		return LuhnNumberGenerator.generateCreditCardNumber(cardType, count);		
	}
	
}
