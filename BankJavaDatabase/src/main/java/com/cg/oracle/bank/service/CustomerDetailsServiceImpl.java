package com.cg.oracle.bank.service;

import java.util.regex.*;

import com.cg.oracle.bank.beans.Customer;
import com.cg.oracle.bank.dao.*;
import com.cg.oracle.bank.exceptions.*;

public class CustomerDetailsServiceImpl implements CustomerDetailsService {

	CustomerDetailsDao customerDetails = new CustomerDetailsDaoImpl();
	CustomerDetailsDaoImpl customerDetail = new CustomerDetailsDaoImpl();

	public long registration(Customer customer) throws Exception {
		return customerDetails.registration(customer);
	}

	public boolean login(long accountNo, String password) throws Exception {
		return customerDetails.login(accountNo, password);
	}

	public boolean validAadhar(long aadhar) throws Exception {
		String aadharNo = String.valueOf(aadhar);
		if (aadharNo.length() == 12)
			return true;
		else
			return false;
	}

	public boolean validMobile(long mobile) throws Exception {
		String phone = String.valueOf(mobile);
		if (phone.length() == 10)
			return true;
		else
			return false;
	}

	public boolean checkPassword(String pwd) throws Exception {
		if (pwd.length() >= 8) {
			Pattern letter = Pattern.compile("[a-zA-z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

			Matcher hasLetter = letter.matcher(pwd);
			Matcher hasDigit = digit.matcher(pwd);
			Matcher hasSpecial = special.matcher(pwd);

			if (hasLetter.find() && hasDigit.find() && hasSpecial.find()) {
				return true;
			} else
				throw new InvalidPassword(
						"Password not valid please register again.\nPassword must have >8 chars\none caps\none digit\none special char.");
		}

		else
			throw new InvalidPassword(
					"Password not valid please register again.\nPassword must have >8 chars\none caps\none digit\none special char.");

	}

	public boolean emailValidate(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;

		return pat.matcher(email).matches();
	}

}
