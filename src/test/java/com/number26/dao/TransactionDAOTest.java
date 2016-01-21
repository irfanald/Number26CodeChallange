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
	transactionDAO.addTransaction(50,new Transaction(50, 5000, "cars"));
	transactionDAO.addTransaction(51,new Transaction(51, 10000, "shops",50));
	transactionDAO.addTransaction(52,new Transaction(52, 1000, "shops",51));
	transactionDAO.addTransaction(53,new Transaction(53, 3000, "cars",50));
	
	}

	@Test
	public void testAddTransaction() {
		transactionDAO.addTransaction(55,new Transaction(55, 3000, "cars",52));
	}

	@Test
	public void testGetTransactionById() {
		Transaction transaction = transactionDAO.getTransactionById(50);
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
		double sum = transactionDAO.getSumOfAllLinkedTransactionsById(50);
		assertTrue(sum == 19000.0);

	}

}
