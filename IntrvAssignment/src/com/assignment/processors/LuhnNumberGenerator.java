package com.assignment.processors;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.assignment.model.CreditCard;
import com.assignment.model.creditCardType;


public class LuhnNumberGenerator {

	
	private static LuhnNumberGenerator Instance = null;
	private static ConcurrentHashMap<String , AtomicLong> creditCardStore = 
			new ConcurrentHashMap<>();
	
	private LuhnNumberGenerator(){

	}
	
	public static LuhnNumberGenerator getInstance() {
		if( Instance == null ) {			
			synchronized (LuhnNumberGenerator.class) {
				if(Instance == null) {
					Instance = new LuhnNumberGenerator();
				}
			}
		}
		return Instance;
	}
	
	public static ArrayList<CreditCard> generateCreditCardNumber(creditCardType cardType, int count) {				
		Long ccNumber = null;
		ArrayList<CreditCard> listOfCards = new ArrayList<>();
		if(creditCardStore.isEmpty()) {
			String xyz = String.format("%1$-15s", String.valueOf(cardType.getStartNumber())).replace( ' ', '0');
			System.out.println("initival valus is : " + xyz);
			ccNumber = Long.valueOf(xyz);			
			creditCardStore.put(cardType.getType(), (new AtomicLong(ccNumber)));
		} else {
			creditCardStore.get(cardType.getType()).compareAndSet(999999999999999L, 
					(Long.valueOf(String.format("%1$-15s", String.valueOf(cardType.getStartNumber())).replace( ' ', '0'))));
			ccNumber = creditCardStore.get(cardType.getType()).incrementAndGet();									
		}
				
		for(int i=0; i< count ; i++) {
			int checkNum = getLuhnCheckNumber(ccNumber);
			String xyz = "" + ccNumber + checkNum + "";
			CreditCard cNew = new CreditCard(Long.valueOf(xyz));
			listOfCards.add(cNew);
			if(i < count-1) {
				creditCardStore.get(cardType.getType()).compareAndSet(999999999999999L, 
						(Long.valueOf(String.format("%1$-15s", String.valueOf(cardType.getStartNumber())).replace( ' ', '0'))));
				ccNumber = creditCardStore.get(cardType.getType()).incrementAndGet();
			}						
		}
		return listOfCards;
	}
	
	private static int getLuhnCheckNumber(Long ccNumber){
		String str = Long.toString(ccNumber);
		int[] ints = new int[str.length()];
		for(int i = 0;i< str.length(); i++){
			ints[i] = Integer.parseInt(str.substring(i, i+1));
		}
		for(int i = ints.length-2; i>=0; i=i-2){
			int j = ints[i];
			j = j*2;
			if(j>9){
				j = j%10 + 1;
			}
			ints[i]=j;
		}
		int sum=0;
		for(int i = 0;i< ints.length; i++){
			sum+=ints[i];
		}
		if(sum%10==0){
			return 0;
		}else return 10-(sum%10);
	}
	
	
}
