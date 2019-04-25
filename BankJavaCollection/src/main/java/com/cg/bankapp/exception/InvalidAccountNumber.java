package com.cg.bankapp.exception;

public class InvalidAccountNumber extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAccountNumber() {
		System.err.println("Account number doesn't exist");
	}
}
