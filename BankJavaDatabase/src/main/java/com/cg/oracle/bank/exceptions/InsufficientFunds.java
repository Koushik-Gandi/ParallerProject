package com.cg.oracle.bank.exceptions;

public class InsufficientFunds extends Exception {
	private static final long serialVersionUID = 1L;

	public InsufficientFunds(String str) {
		super(str);
	}
}
