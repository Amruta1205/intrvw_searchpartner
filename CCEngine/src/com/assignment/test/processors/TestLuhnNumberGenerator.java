package com.assignment.test.processors;

import java.util.ArrayList;

import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;
import com.assignment.processors.CreditCardNumberProcessor;
import com.assignment.processors.LuhnNumberGenerator;
import com.assignment.processors.LuhnNumberValidator;

import junit.framework.TestCase;

public class TestLuhnNumberGenerator extends TestCase{

	CreditCard cc = null;
	ArrayList<CreditCard> ccList = new ArrayList<>();
	
	
	public TestLuhnNumberGenerator(String TestName){
		super(TestName);
	}
	
	public void testGenerateCreditCardNumber_VISA() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.VISA, 2);
		assertEquals(4000000000000002L, ccList.get(0).getCreditCardNo());
		assertEquals(4000000000000010L, ccList.get(1).getCreditCardNo());
	}
	
	public void testGenerateCreditCardNumber_MASTER() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.MASTER, 2);
		assertEquals(5000000000000009L, ccList.get(0).getCreditCardNo());
		assertEquals(5000000000000017L, ccList.get(1).getCreditCardNo());
	}
	
	public void testGenerateCreditCardNumber_AMEX() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.AMERICAN_EXPRESS, 2);
		assertEquals(3700000000000007L, ccList.get(0).getCreditCardNo());
		assertEquals(3700000000000015L, ccList.get(1).getCreditCardNo());
	}
	
	public void testGenerateCreditCardNumber_DISCOVER() {
		ccList = LuhnNumberGenerator.generateCreditCardNumber(creditCardType.DISCOVER, 2);
		assertEquals(6000000000000007L, ccList.get(0).getCreditCardNo());
		assertEquals(6000000000000015L, ccList.get(1).getCreditCardNo());
	}
	
}
