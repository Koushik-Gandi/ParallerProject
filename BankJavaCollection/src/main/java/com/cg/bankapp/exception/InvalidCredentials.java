package com.cg.bankapp.exception;

public class InvalidCredentials extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidCredentials() {
		System.out.println("Invalid username or password");
	}
}
