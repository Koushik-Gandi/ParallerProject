package com.cg.bankapp.service;

import com.cg.bankapp.dao.BankCollectionsDao;
import com.cg.bankapp.dao.BankCollectionsDaoImpl;
import com.cg.bankapp.exception.InsufficientBalance;
import com.cg.bankapp.exception.CustomerAleadyExistException;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;

public class BankCollectionsServiceImpl implements BankCollectionsService {
	BankCollectionsDao dao = new BankCollectionsDaoImpl();

	public long registration(CustomerDetails customerDetails) {
		return dao.registration(customerDetails);
	}

	public CustomerDetails login(long accountNo, String password) {
		return dao.login(accountNo, password);
	}

	public boolean validation(String aadharNo) {
		boolean check = false;
		if (aadharNo.matches("[0-9]+")) {
			if (aadharNo.length() == 12) {
				if (dao.validation(aadharNo)) {
					check = true;
				} else {
					try {
						throw new CustomerAleadyExistException();
					} catch (CustomerAleadyExistException e) {
					}
				}
			} else
				check = false;
		}
		return check;

	}

	public boolean validateNumber(String mobile) {
		if (mobile.length() == 10) {
			return true;
		} else {
			return false;
		}
	}

	public long withdraw(long accountNo, long amount) {
		long balance = dao.withdraw(accountNo, amount);
		if (balance < 0) {
			try {
				throw new InsufficientBalance();
			} catch (InsufficientBalance e) {
			}
		}
		return balance;
	}

	public long deposit(long accountNo, long amount) {
		return dao.deposit(accountNo, amount);
	}

	public long fundTransfer(TransactionDetails transactionDetails) {
		return dao.fundTransfer(transactionDetails);
	}

}
