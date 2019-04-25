package com.cg.oracle.bank.dao;

public interface TransactionDetailsDao {

	double deposit(long accountNo, double amount) throws Exception;

	double debit(long accountNo, double amount) throws Exception;

	double fundTransfer(long accountNo, long toAccount, double amount) throws Exception;

	double checkBalance(long accountNo) throws Exception;

}
