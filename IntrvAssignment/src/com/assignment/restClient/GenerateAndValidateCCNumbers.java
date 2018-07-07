package com.assignment.restClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.assignment.model.CreditCard;
import com.assignment.model.CreditCardist;
import com.assignment.model.creditCardType;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class GenerateAndValidateCCNumbers {

	public static void main(String[] args) {
		CreditCard cc = new CreditCard();
		cc.setCardType(creditCardType.MASTER.getType());
		cc.setCreditCardNo(5000000000000009L);
		
		ObjectMapper oMapper = new ObjectMapper();
		try {
			ByteArrayOutputStream oo = new ByteArrayOutputStream();
			JSONObject jObj = new JSONObject(oMapper.writeValueAsString(cc));
			URL url = new URL ("http://localhost:9999/IntrvAssignment/rest/validate");
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(1200);
			con.setReadTimeout(1200);
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(jObj.toString());
			out.close();
			
			
			   
		 
		      
									
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while(in.readLine()!=null) {
				
			}
			System.out.println("End -----");
			in.close();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	


	/*public static void main(String[] args) {

	try {

	    List<CreditCard> clientList = new ArrayList<CreditCard>();
	    clientList.add(new CreditCard(12345L));
	    clientList.add(new CreditCard(12345L));	    	    
	    
	    ClientConfig clientConfig = new DefaultClientConfig();

	    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

	    com.sun.jersey.api.client.Client c = com.sun.jersey.api.client.Client.create(clientConfig);
	    
	    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
	    defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
	    c.create(defaultClientConfig);

	    WebResource webResource = c.resource("http://localhost:9999/IntrvAssignment/rest/validate");

	    ClientResponse response = webResource.accept("application/json").type("application/json").post(ClientResponse.class, clientList);

	    if (response.getStatus() != 200) {
	    throw new RuntimeException("Failed sendClientList: HTTP error code : " + response.getStatus());
	    }

	    String output = response.getEntity(String.class);

	    System.out.println("sendClientList... Server response .... \n");
	    System.out.println(output);

	} catch (Exception e) {

	    e.printStackTrace();

	}
	}
	*/
}
