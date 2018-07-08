package com.assignment.test.processors;

import java.util.ArrayList;
import com.assignment.model.CreditCard;
import junit.framework.TestCase;
import com.assignment.processors.LuhnNumberGenerator;
import com.assignment.processors.LuhnNumberGenerator;
import com.assignment.model.creditCardType;
import com.assignment.processors.CreditCardNumberProcessor;

public class TestLuhnValidator extends TestCase{

	CreditCard cc = null;
	ArrayList<CreditCard> ccList = new ArrayList<>();
	public void testValidateCreditCardNumber_VISA() {
	ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.VISA, 1);
	ArrayList<CreditCard> ccList2 = CreditCardNumberProcessor.validateCreditCards(ccList);
	
	assertTrue(!ccList2.get(0).isDiscardNumber());
	}
	
	public void testValidateCreditCardNumber_MASTER() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.MASTER, 1);
		ArrayList<CreditCard> ccList2 = CreditCardNumberProcessor.validateCreditCards(ccList);
		
		assertTrue(!ccList2.get(0).isDiscardNumber());
	}
	
	public void testValidateCreditCardNumber_AMEX() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.AMERICAN_EXPRESS, 1);
		ArrayList<CreditCard> ccList2 = CreditCardNumberProcessor.validateCreditCards(ccList);
		
		assertTrue(!ccList2.get(0).isDiscardNumber());
	}
	
	public void testValidateCreditCardNumber_DISCOVER() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.DISCOVER, 1);
		ArrayList<CreditCard> ccList2 = CreditCardNumberProcessor.validateCreditCards(ccList);
		
		assertTrue(!ccList2.get(0).isDiscardNumber());
	}
	
	public void testValidateCreditCardNumber_Negative() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.MASTER, 1);
		ccList.get(0).setCardType(creditCardType.DISCOVER.getType());
		ArrayList<CreditCard> ccList2 = CreditCardNumberProcessor.validateCreditCards(ccList);
		
		assertTrue(ccList2.get(0).isDiscardNumber());
	}
	
}
