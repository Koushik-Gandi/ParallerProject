package com.cg.oracle.bank.service;

import java.util.Scanner;

import com.cg.oracle.bank.dao.*;

public class TransactionDetailsServiceImpl implements TransactionDetailsService {
	TransactionDetailsDao transaction = new TransactionDetailsDaoImpl();
	static Scanner sc = new Scanner(System.in);

	public double deposit(long accountNo, double amount) throws Exception {

		return transaction.deposit(accountNo, amount);
	}

	public double debit(long accountNo, double amount) throws Exception {
		return transaction.debit(accountNo, amount);
	}

	public double fundTransfer(long accountNo, long toAccount, double amount2) throws Exception {
		return transaction.fundTransfer(accountNo, toAccount, amount2);
	}

	public double checkBalance(long accountNo) throws Exception {
		return transaction.checkBalance(accountNo);
	}

}
