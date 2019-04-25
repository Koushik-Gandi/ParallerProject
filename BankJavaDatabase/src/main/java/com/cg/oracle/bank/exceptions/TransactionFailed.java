package com.cg.oracle.bank.exceptions;

public class TransactionFailed extends Exception {
	private static final long serialVersionUID = 1L;

	public TransactionFailed(String str) {
		super(str);
	}
}
