package com.cg.oracle.bank.exceptions;

public class InvalidAccountNumber extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumber(String str) {
		super(str);
	}
}
