package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.utility.Database;
import com.cg.model.CustomerDetails;
import com.cg.model.TransactionDetails;
import com.cg.service.BankService;
import com.cg.service.TransactionService;

@RestController
public class HomeController {
	Database d = new Database();
	int accountNo;
	@Autowired
	BankService bank;
	@Autowired
	TransactionService tService;

	@GetMapping(value = "/")
	public String start() {

		return "Hello";
	}

	@PostMapping(value = "/register")
	public ResponseEntity register(@RequestBody CustomerDetails customer) {
		bank.register(customer);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value = "/login")
	public void login(@RequestBody CustomerDetails c) {
		accountNo = bank.login(c);

	}

	@PutMapping(value = "/deposit")
	public int deposit(@RequestBody int amt) {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.deposit(accountNo, amt);
		}
		return amount;
	}

	@PutMapping(value = "/withdraw")
	public int withdraw(@RequestBody int amt) {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.withdraw(accountNo, amt);
		}

		return amount;

	}

	@GetMapping(value = "/showbalance")
	public int showBalance() {
		int amount = 0;
		if (accountNo != 0) {
			amount = tService.showBalance(accountNo);
		}
		return amount;

	}

	@PutMapping(value = "/fundtransfer")
	public int fundTransfer(@RequestBody TransactionDetails transaction) {
		int amount = 0;
		if (accountNo != 0) {
			transaction = tService.fundTransfer(accountNo, transaction);
			boolean isInserted = tService.insertTransaction(transaction);
		}

		return amount;
	}

}
