package com.assignment.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;

import com.assignment.model.CreditCard;
import com.assignment.model.CreditCardist;
import com.assignment.model.creditCardType;
import com.assignment.processors.CreditCardNumberProcessor;
import com.assignment.processors.LuhnNumberGenerator;
import com.assignment.processors.LuhnNumberValidator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Path("/")
public class CCRestService {

	@GET
	@Path("{cardType}/{count}")
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
		
		return CreditCardNumberProcessor.getCreditCardNumbers(cardTypeObj, count);
		
	}
	
	@POST
	@Path("validate")
	@Produces("application/json")
	@Consumes({MediaType.APPLICATION_JSON})	
	public String validateCreditCards(String abc){
		String jsonStr = null;
		ObjectMapper o = new ObjectMapper();
		try {
			CreditCard[] cards = o.readValue(abc, CreditCard[].class);
			ArrayList<CreditCard> creditCards = CreditCardNumberProcessor.validateCreditCards(new ArrayList<>(Arrays.asList(cards)));								
			jsonStr = o.writeValueAsString(creditCards);					
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return jsonStr;		
	}
}
