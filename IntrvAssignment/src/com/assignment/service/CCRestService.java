package com.assignment.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.assignment.model.CreditCard;
import com.assignment.model.CreditCardist;
import com.assignment.model.creditCardType;
import com.assignment.processors.CreditCardNumberProcessor;
import com.assignment.processors.LuhnNumberGenerator;
import com.assignment.processors.LuhnNumberValidator;

@Path("/")
public class CCRestService {

	@GET
	@Path("CCEngine/{cardType}/{count}")
	@Produces("application/json")
	public ArrayList<CreditCard> getCreditCardNumber(@PathParam("cardType") String cardType, @PathParam("count") int count){	
		
	
		creditCardType cardTypeObj;
		switch(cardType.toUpperCase()) {
		case "VISA" : 
			cardTypeObj = creditCardType.VISA;
			break;
		case "MASTER" :
			cardTypeObj = creditCardType.MASTER;
			break;
		case "AMEX" : 
			cardTypeObj = creditCardType.AMERICAN_EXPRESS;
			break;
		case "DISCOVER" :
			cardTypeObj = creditCardType.DISCOVER;
			break;
		 default:
			return null;		
		}
		
	//	return LuhnNumberGenerator.generateCreditCardNumber(cardTypeObj, count);
		return CreditCardNumberProcessor.getCreditCardNumbers(cardTypeObj, count);
		
	}
	
	@POST
	@Path("validate")
	@Produces("application/json")
	@Consumes({MediaType.APPLICATION_JSON})	
	public ArrayList<CreditCard> validateCreditCards(ArrayList<CreditCard> creditCards){									
		return CreditCardNumberProcessor.validateCreditCards(creditCards);		
	}
}
