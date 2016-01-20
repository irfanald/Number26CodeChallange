package com.number26.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.number26.model.Transaction;

public class TransactionDB {

	private static final Map<Long, Transaction> inmemoryDB = new HashMap<>();

	public void addTransaction(long transactionid, Transaction transaction) {
		System.out.println("Adding Transaction for "+transaction);
		inmemoryDB.put(transactionid, transaction);
		System.out.println("current inmemory map"+inmemoryDB);
	}

	public boolean existsTransaction(long transactionId) {

		return inmemoryDB.containsKey(transactionId);

	}

	public Transaction getTransactionById(long transactionId) {

		return inmemoryDB.get(transactionId);

	}

	public Set<Long>  getTransactionByType(String type) {

		Set<Long> keys = new HashSet<Long>();
		for (Entry<Long, Transaction> entry : inmemoryDB.entrySet()) {
			if (type.equals(entry.getValue().getType())) {
				keys.add(entry.getKey());
			}
		}
		return keys;

	}


}
