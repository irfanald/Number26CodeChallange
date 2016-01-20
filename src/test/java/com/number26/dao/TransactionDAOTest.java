package com.number26.dao;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.number26.model.Transaction;

public class TransactionDAOTest {
	private static TransactionDAO transactionDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	transactionDAO = new TransactionDAO();
	transactionDAO.addTransaction(10,new Transaction(10, 5000, "cars"));
	transactionDAO.addTransaction(11,new Transaction(11, 10000, "shops",10));
	transactionDAO.addTransaction(12,new Transaction(12, 1000, "shops",11));
	transactionDAO.addTransaction(13,new Transaction(13, 3000, "cars",10));
	
	}

	@Test
	public void testAddTransaction() {
		transactionDAO.addTransaction(15,new Transaction(15, 3000, "cars",12));
	}

	@Test
	public void testGetTransactionById() {
		Transaction transaction = transactionDAO.getTransactionById(10);
		assertTrue(transaction.getAmount()==5000.0);
		assertTrue(transaction.getType().equals("cars"));
		assertTrue(transaction.getParent_id()==0);
	}

	@Test
	public void testGetTransactionByType() {
		Set<Long> ids = transactionDAO.getTransactionByType("shops");
		assertTrue(ids.size()==2);
	}

	@Test
	public void testGetSumOfAllLinkedTransactionsById() {
		double sum = transactionDAO.getSumOfAllLinkedTransactionsById(10);
		assertTrue(sum == 19000.0);

	}

}
