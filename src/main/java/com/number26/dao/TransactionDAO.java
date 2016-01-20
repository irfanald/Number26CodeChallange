package com.number26.dao;

import java.util.Set;

import com.number26.db.TransactionDB;
import com.number26.exception.NoTransactionFoundException;
import com.number26.exception.TransactionAlreadyExistException;
import com.number26.model.Transaction;

public class TransactionDAO {

	private TransactionDB transactionDB = new TransactionDB();


	public void addTransaction(long transactionid, Transaction transaction) {
		Transaction transactionAvailable = getTransactionById(transactionid);
		if(transactionAvailable != null) {
			throw new TransactionAlreadyExistException("Transaction already exists for the given transaction id: "+transactionid);
		}
		System.out.println("No transaction available for transactionid "+transactionid);
		if(transaction.getParent_id()!=0) {
			if (transactionDB.existsTransaction(transaction.getParent_id())){

				getTransactionById(transaction.getParent_id()).addChildTransactions(transaction);
			}
			else {
				throw new NoTransactionFoundException("No transaction available for the given parent id: "+transaction.getParent_id());
			}


		}
		transactionDB.addTransaction(transactionid, transaction);

	}

	public Transaction getTransactionById(long transactionId) {

		return transactionDB.getTransactionById(transactionId);

	}

	public Set<Long>  getTransactionByType(String type) {

		return transactionDB.getTransactionByType(type);

	}



	public double getSumOfAllLinkedTransactionsById(long transactionId) {

		double sum = 0;
		Transaction transaction = getTransactionById(transactionId);
		if(transaction == null) {
			throw new NoTransactionFoundException("No transaction found for the given transaction id: "+transactionId);
		}
		sum = transaction.getAmount();
		if(transaction.hasChildTrasactions()) {
			for (Transaction child: transaction.getChildTransactions()){
				sum = sum + getSumOfAllLinkedTransactionsById(child.getTransactionId());
			}

		}

		return sum;

	}



}
