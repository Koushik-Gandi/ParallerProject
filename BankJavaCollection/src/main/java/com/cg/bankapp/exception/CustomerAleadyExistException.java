package com.cg.bankapp.exception;

public class CustomerAleadyExistException extends Exception{

	private static final long serialVersionUID = 1L;

	public CustomerAleadyExistException() {
		System.out.println("User already exists");
	}
}
