package com.cg.bankapp.exception;

public class InvalidAadhar extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidAadhar(){
		System.err.println("Aadhar card number should be only 12 digits.");
	}
}
