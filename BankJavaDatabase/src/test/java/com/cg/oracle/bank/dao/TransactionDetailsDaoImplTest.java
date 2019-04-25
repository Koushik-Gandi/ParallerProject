package com.cg.oracle.bank.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.cg.oracle.bank.beans.Customer;
import com.cg.oracle.bank.beans.Transaction;

class TransactionDetailsDaoImplTest {

	CustomerDetailsDao con = new CustomerDetailsDaoImpl();
	TransactionDetailsDao trans = new TransactionDetailsDaoImpl();
	Customer cust = new Customer();
	Transaction transact = new Transaction();

	//@Test
	void testDeposit() throws Exception {
		assertEquals(trans.deposit(1000000002, 5000), 25000);
	}

	//@Test
	void testDebit() throws Exception {
		assertEquals(trans.debit(1000000002, 5000), 20000);
	}

	//@Test
	void testCheckBalance() throws Exception {
		assertEquals(trans.checkBalance(1000000002), 20000);

	}

	//@Test
	void testFundTransfer() throws Exception {
		assertEquals(trans.fundTransfer(1000000002,1000000000, 5000), 15000);

	}

}
