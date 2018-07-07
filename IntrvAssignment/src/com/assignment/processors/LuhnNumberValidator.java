package com.assignment.processors;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;


public class LuhnNumberValidator implements Callable<CreditCard>{

	
	private static LuhnNumberValidator Instance = null;
	private static ConcurrentHashMap<String , AtomicLong> creditCardStore = 
			new ConcurrentHashMap<>();

//	ThreadLocal<ArrayList<CreditCard>> workCreditCards;
	CreditCard cc;
	
	public LuhnNumberValidator(CreditCard cc) {
		this.cc = cc;
	}
	
	@Override
	public CreditCard call() throws Exception {
		return validateCreditCard(this.cc);
	}
	
	
	public CreditCard validateCreditCard(CreditCard cc) {				
		Long ccNumber = null;
		
		if(validateNumber(cc.getCreditCardNo())){
			cc.setExpiryDate(new Date());
		} else {
			cc.setDiscardNumber(true);
		}
	
	/*	for(CreditCard cCard : listOfCards.get()) {
			if(validateNumber(cCard.getCreditCardNo())){
				cCard.setExpiryDate(new Date());
			} else {
				cCard.setDiscardNumber(true);
			}
		}*/
	
		return cc;
	}
	
	private static boolean validateNumber(Long ccNumber) {
		String str = String.valueOf(ccNumber);
		int[] ints = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ints[i] = Integer.parseInt(str.substring(i, i + 1));
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
}
