/**
 * 
 */
package com.number26.service;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.number26.model.Transaction;


/**
 * @author Mohammad_Irfan01
 *
 */
public class TransactionServiceTest {
	private static TransactionService transactionService;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		transactionService = new TransactionService();
		transactionService.createTransaction(10,new Transaction(10, 5000, "cars"));
		transactionService.createTransaction(11,new Transaction(11, 1000, "bikes",10));
		transactionService.createTransaction(12,new Transaction(12, 1000, "bikes",11));
		transactionService.createTransaction(13,new Transaction(13, 3000, "cars",10));

	}


	@Test
	public void testPutTransactionById() {
		String response = transactionService.createTransaction(15,new Transaction(15, 3000, "cars",12));
		assertTrue("{ \"status\": \"ok\" }".equals(response));
		
	}

	@Test
	public void testGetTransactions() {
		Transaction transaction = transactionService.getTransaction(10);
		assertTrue(transaction.getAmount()==5000.0);
		assertTrue(transaction.getType().equals("cars"));
		assertTrue(transaction.getParent_id()==0);
	}

	@Test
	public void testGetSumOfAllLinkedTransactionsById() {
		String sum = transactionService.getSumOfAllLinkedTransactionsById(10);
		
		assertTrue("{\"sum\":10000.0}".equals(sum));
	}

	@Test
	public void testGetTransactionsByType() {
		String transaction = transactionService.getTransactionsByType("bikes");
		assertTrue("[11, 12]".equals(transaction));	
	}

}
