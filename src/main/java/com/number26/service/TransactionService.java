package com.number26.service;

import java.util.Set;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.number26.dao.TransactionDAO;
import com.number26.model.Transaction;

@Path(TransactionService.TRANSACTION_RESOURCE_PATH)
public class TransactionService {

	private TransactionDAO transactionDAO = new TransactionDAO();
	
	public static final String TRANSACTION_RESOURCE_PATH = "/transactionservice";
    public static final String TRANSACTION_ID_PATH_PARAM = "/{transactionId}";

	@PUT
	@Path("/transaction/{transactionId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createTransaction(@PathParam("transactionId") long transactionId, Transaction transaction){
		System.out.println("Adding transactionId"+transactionId);
		if(transaction!=null){
			transaction.setTransactionId(transactionId);
		}
		transactionDAO.addTransaction(transactionId, transaction);
		return "{ \"status\": \"ok\" }";
	}
	

	@GET
	@Path("/transaction/{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Transaction getTransaction(@PathParam("transactionId") long transactionId){
		return transactionDAO.getTransactionById(transactionId);
	}
	
	@GET
	@Path("/sum/{transactionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSumOfAllLinkedTransactionsById(@PathParam("transactionId") long transactionId){
		System.out.println("getting the sum of transaction "+transactionId);
		double sum = transactionDAO.getSumOfAllLinkedTransactionsById(transactionId);
		
		
		return "{\"sum\":"+sum+"}";
	}
	
	@GET
	@Path("/types/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTransactionsByType(@PathParam("type") String type){
		Set<Long> ids = transactionDAO.getTransactionByType(type);
		return ids.toString();
	}	

}
