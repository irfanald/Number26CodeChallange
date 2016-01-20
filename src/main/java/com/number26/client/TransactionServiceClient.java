package com.number26.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TransactionServiceClient {
	
	private static final String url= "http://localhost:8080/Number26CodeChallange/transactionservice";
	private static Client client = Client.create();
	
	public static void main(String[] args) {

		try {
			String input = "{ \"amount\": 1500, \"type\": \"cars\"}";
			testPutTransation("transaction",10,input);
			input = "{ \"amount\": 500, \"type\": \"cars\", \"parent_id\": 10}";
			testPutTransation("transaction",11,input);
			input = "{ \"amount\": 1000, \"type\": \"shops\", \"parent_id\": 11}";
			testPutTransation("transaction",12,input);
			
			testGetTransaction("transaction","10");
			testGetTransaction("types","cars");
			testGetTransaction("sum","10");

	  } catch (Exception e) {

		e.printStackTrace();

	  }

	
	}

	private static void testPutTransation(String resource, long transactionid, String input){

	WebResource webResource = client
	   .resource(url+"/"+resource+"/"+transactionid);

	ClientResponse response = webResource.type("application/json")
	   .put(ClientResponse.class, input);

	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
		     + response.getStatus());
	}

	System.out.println("Output from Server .... \n");
	String output = response.getEntity(String.class);
	System.out.println(output);

	}

	private static void testGetTransaction(String resource, String param){

	WebResource webResource = client
	   .resource(url+"/"+resource+"/"+param);

	ClientResponse response = webResource.type("application/json")
	   .get(ClientResponse.class);

	if (response.getStatus() != 200) {
		throw new RuntimeException("Failed : HTTP error code : "
		     + response.getStatus());
	}

	System.out.println("Output from Server .... \n");
	String output = response.getEntity(String.class);
	System.out.println(output);

}

}
