package com.assignment.processors;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;


public class LuhnNumberValidator {

	
	private static LuhnNumberValidator Instance = null;
	private static ConcurrentHashMap<String , AtomicLong> creditCardStore = 
			new ConcurrentHashMap<>();

	
	public static ArrayList<CreditCard> validateCreditCard(ArrayList<CreditCard> listOfCards) {				
		Long ccNumber = null;
	
		for(CreditCard cCard : listOfCards) {
			if(validateNumber(cCard.getCreditCardNo())){
				cCard.setExpiryDate(new Date());
			} else {
				cCard.setDiscardNumber(true);
			}
		}
	
		return listOfCards;
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
