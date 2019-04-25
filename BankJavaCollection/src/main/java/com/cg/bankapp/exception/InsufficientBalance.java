package com.cg.bankapp.exception;

public class InsufficientBalance extends Exception{
	private static final long serialVersionUID = 1L;

	public InsufficientBalance() {
		System.err.println("Insufficient balance");
	}
}
