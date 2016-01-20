package com.number26.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "transaction")
public class Transaction {

	private long transaction_id;
	private double amount;
	private String type;
	private long parent_id = 0;
	private List<Transaction> childTransactions;
	
	
	public Transaction() {
		super();
	}

	public Transaction(long transactionId, double amount, String type, long parent_id) {
		this.transaction_id = transactionId;
		this.amount = amount;
		this.type = type;
		this.parent_id = parent_id;
		childTransactions = new  ArrayList<Transaction>();
	}
	
	public Transaction(long transactionId, double amount, String type) {
		this.transaction_id = transactionId;
		this.amount = amount;
		this.type = type;
		childTransactions = new  ArrayList<Transaction>();
	}
	
	public void addChildTransactions(Transaction transaction){
		if(childTransactions==null){
			childTransactions = new  ArrayList<Transaction>();
		}
		childTransactions.add(transaction);
		
	}
	
	public boolean hasChildTrasactions() {
		 return childTransactions !=null && childTransactions.size() >0 ? true: false; 
	}
	
	public List<Transaction> getChildTransactions() {
		return childTransactions;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transaction_id + ", amount=" + amount + ", type=" + type + ", parentId="
				+ parent_id + "]";
	}

	public long getTransactionId() {
		return transaction_id;
	}
	public void setTransactionId(long transactionId) {
		this.transaction_id = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (parent_id ^ (parent_id >>> 32));
		result = prime * result + (int) (transaction_id ^ (transaction_id >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (parent_id != other.parent_id)
			return false;
		if (transaction_id != other.transaction_id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
