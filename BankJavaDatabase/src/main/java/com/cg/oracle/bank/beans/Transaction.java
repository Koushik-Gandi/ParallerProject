package com.cg.oracle.bank.beans;

public class Transaction {

	private long transactionId,fromAccountNo,toAccountNo;
	private double amountTransfered;
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public long getToAccountNo() {
		return toAccountNo;
	}
	public void setToAccountNo(long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}
	public double getAmountTransfered() {
		return amountTransfered;
	}
	public void setAmountTransfered(double amountTransfered) {
		this.amountTransfered = amountTransfered;
	}
	
}
