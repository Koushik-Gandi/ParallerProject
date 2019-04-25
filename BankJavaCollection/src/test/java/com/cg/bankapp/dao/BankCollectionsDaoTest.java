package com.cg.bankapp.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;

class BankCollectionsDaoTest {

	static BankCollectionsDao bank;

	@BeforeAll
	public static void init() {
		bank = new BankCollectionsDaoImpl();
		
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setFirstname("koushik");
		customerDetails.setLastname("gandi");
		customerDetails.setAadharCardNo("123412391235");
		customerDetails.setAddress("hyd");
		customerDetails.setBalance(5000);
		customerDetails.setEmail("gandi.koushik@gmail.com");
		customerDetails.setMobileNo("7386159202");
		customerDetails.setPanNo("k123gm");
		customerDetails.setPassword("1234");
		bank.registration(customerDetails);
	}

	@Test
	void testRegistration() {
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setFirstname("prabhu");
		customerDetails.setLastname("sai");
		customerDetails.setAadharCardNo("123412391285");
		customerDetails.setAddress("hyd");
		customerDetails.setBalance(1000);
		customerDetails.setEmail("prabhusai@gmail.com");
		customerDetails.setMobileNo("74512063589");
		customerDetails.setPanNo("kotha@123");
		customerDetails.setPassword("1245");

		assertEquals(543211, bank.registration(customerDetails));
	}

	@Test
	public void testLogin() {
		CustomerDetails cust=bank.login(543210, "1234");
		assertEquals(543210, cust.getAccountNo());
	}

	@Test
	void testValidation() {
		assertEquals(true, bank.validation("123412391285"));
	}

	@Test
	void testWithdraw() {
		assertEquals(4500, bank.withdraw(543210, 500));
	}

	@Test
	void testDeposit() {
		assertEquals(5500, bank.deposit(543210, 1000));
	}

	@Test
	void testFundTransfer() {
		TransactionDetails details = new TransactionDetails();
		details.setFromAccountNo(543210);
		details.setToAccountNo(543211);
		details.setAmount_transfered(-1000);
		assertEquals(-1, bank.fundTransfer(details));
	}

}
