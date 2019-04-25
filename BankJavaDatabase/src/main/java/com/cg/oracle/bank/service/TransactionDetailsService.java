package com.cg.oracle.bank.service;

public interface TransactionDetailsService {

	double deposit(long accountNo,double amount) throws Exception;

	double debit(long accountNo,double amount) throws Exception;

	double fundTransfer(long accountNo,long toAccount,double amount) throws Exception;

	double checkBalance(long accountNo) throws Exception;
}
