package com.assignment.processors;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class CreditCardNumberProcessor {

	LuhnNumberGenerator CCGenerator;
	private static ExecutorService executor;
	LuhnNumberValidator CCValidator;
	public static final CreditCardNumberProcessor Instance = new CreditCardNumberProcessor();
	
	private CreditCardNumberProcessor() {
		CCGenerator = LuhnNumberGenerator.getInstance();
		executor = Executors.newFixedThreadPool(creditCardType.values().length);
//		CCValidator validator = LuhnNumberValidator();
	}
	
	
	public static ArrayList<CreditCard> getCreditCardNumbers(creditCardType cardType, int count){
		return LuhnNumberGenerator.generateCreditCardNumber(cardType, count);
		//return validateCreditCards(LuhnNumberGenerator.generateCreditCardNumber(cardType, count));
	}
	
	public static ArrayList<CreditCard> validateCreditCards(ArrayList<CreditCard> creditCards){
		
		ArrayList<CreditCard> validatedCreditCards = new ArrayList<>();

		for(CreditCard ccNew : creditCards) {
			LuhnNumberValidator validateTask = new LuhnNumberValidator(ccNew);
			Future<CreditCard> fut =  executor.submit(validateTask);
			CreditCard cc=null;
			try {
				cc = fut.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				cc = new CreditCard();
				cc.setDiscardNumber(true);				
				e.printStackTrace();
			}
			validatedCreditCards.add(cc);
		}
				
		return validatedCreditCards;		
	}
	
}
