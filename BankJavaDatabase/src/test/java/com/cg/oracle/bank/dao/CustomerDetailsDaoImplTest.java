package com.cg.oracle.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cg.oracle.bank.beans.Customer;

class CustomerDetailsDaoImplTest {

	CustomerDetailsDaoImpl con = new CustomerDetailsDaoImpl();
	Customer cust = new Customer();
	//Customer cust1 = new Customer();

	@Test
	void testRegistration() throws Exception {
		double actual = 1000000001;
		cust.setFirstName("pavan");
		cust.setLastName("kumar");
		cust.setEmail("pavan1@gmail.com");
		cust.setPassword("pavan@1");
		cust.setPancardNo("pav12345");
		cust.setAadharNo(456453456123L);
		cust.setAddress("hyd");
		cust.setMobile(7894578234L);
		cust.setBalance(0);
		double expected = con.registration(cust);
		assertEquals(expected, actual);
	}

	@Test
	void testLogin() throws Exception {
		boolean actual = true;
		boolean expected = con.login(1000000002, "Saishiva@1");
		assertEquals(expected, actual);
	}

}
