package com.cg.oracle.bank.exceptions;

public class InvalidEmail extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidEmail(String str) {
		super(str);
	}
}
