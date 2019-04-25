package com.cg.oracle.bank.exceptions;

public class InvalidPassword extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidPassword(String str) {
		super(str);
	}
}
