package com.cg.bankapp.ui;

import java.util.Scanner;

import com.cg.bankapp.exception.InvalidAadhar;
import com.cg.bankapp.exception.InvalidCredentials;
import com.cg.bankapp.exception.InvalidMobileNumber;
import com.cg.bankapp.model.CustomerDetails;
import com.cg.bankapp.model.TransactionDetails;
import com.cg.bankapp.service.BankCollectionsService;
import com.cg.bankapp.service.BankCollectionsServiceImpl;

public class Main {
	private static Scanner scanner;

	public static void main(String[] args) {
		long balance;
		scanner = new Scanner(System.in);
		BankCollectionsServiceImpl serviceValidation = new BankCollectionsServiceImpl();
		BankCollectionsService service = new BankCollectionsServiceImpl();
		TransactionDetails transactionDetails = new TransactionDetails();
		while (true) {
			// Menu
			System.out.println("1. Registration\n2. Login\n3. Exit");
			int ch = scanner.nextInt();
			switch (ch) {
			case 1:
				CustomerDetails customerDetails = new CustomerDetails();

				// Taking customer details for registration
				System.out.println("Enter firstname");
				customerDetails.setFirstname(scanner.next());
				System.out.println("Enter lastname");
				customerDetails.setLastname(scanner.next());
				System.out.println("Enter Email");
				customerDetails.setEmail(scanner.next());
				System.out.println("Enter password");
				customerDetails.setPassword(scanner.next());
				System.out.println("Enter pan no");
				customerDetails.setPanNo(scanner.next());
				System.out.println("Enter aadhar");
				customerDetails.setAadharCardNo(scanner.next());
				if (service.validation(customerDetails.getAadharCardNo())) {
					System.out.println("Enter address");
					customerDetails.setAddress(scanner.next());
					System.out.println("Enter mobile number");
					String mobileNo = scanner.next();
					if (serviceValidation.validateNumber(mobileNo)) {
						customerDetails.setMobileNo(mobileNo);
						customerDetails.setBalance(0);
						System.out.println("Account number is " + service.registration(customerDetails));
					} else {
						try {
							throw new InvalidMobileNumber();
						} catch (InvalidMobileNumber e) {
						}
					}
				} else {
					try {
						throw new InvalidAadhar();
					} catch (InvalidAadhar e) {
					}
				}
				break;
			case 2:
				CustomerDetails customerDetails2 = new CustomerDetails();
				System.out.println("Enter account number");
				customerDetails2.setAccountNo(scanner.nextLong());
				System.out.println("Enter password");
				customerDetails2.setPassword(scanner.next());
				customerDetails2 = service.login(customerDetails2.getAccountNo(), customerDetails2.getPassword());
				System.out.println("Welcome " + customerDetails2.getFirstname());

				int choice = 0;
				while (choice != 1) {
					if (customerDetails2.getAccountNo() > 0) {
						System.out.println("Enter your choice");
						System.out.println(
								"1. Deposit\n2. Withdraw\n3. Show balance\n4. Fund transfer\n5. Return to main menu\n6. Exit");
						ch = scanner.nextInt();
						switch (ch) {
						case 1:
							System.out.println("Enter amount for deposit");
							balance = service.deposit(customerDetails2.getAccountNo(), scanner.nextLong());
							System.out.println("Updated balance: " + balance);
							break;
						case 2:
							System.out.println("Enter amount for withdrawal");
							balance = service.withdraw(customerDetails2.getAccountNo(), scanner.nextLong());
							if (balance > 0)
								System.out.println("Updated balance: " + balance);
							break;
						case 3:
							System.out.println("Balance is " + customerDetails2.getBalance());
							break;
						case 4:
							System.out.println("Enter account number to transfer");
							transactionDetails.setToAccountNo(scanner.nextLong());
							System.out.println("Enter amount to tranfer");
							transactionDetails.setAmount_transfered(scanner.nextLong());
							transactionDetails.setFromAccountNo(customerDetails2.getAccountNo());
							long trans_id = service.fundTransfer(transactionDetails);
							if (trans_id > 0) {
								System.out.println("Amount of Rs." + transactionDetails.getAmount_transfered()
										+ " tranfered from account number " + transactionDetails.getFromAccountNo()
										+ " to account number " + transactionDetails.getToAccountNo());
								System.out.println("Transaction id:" + trans_id);
							}
							break;
						case 5:
							choice++;
							break;

						case 6:
							System.exit(0);

						default:
							System.out.println("Enter valid input");
							break;

						}
					} else {
						try {
							throw new InvalidCredentials();
						} catch (InvalidCredentials e) {
						}
					}

				}
				break;

			case 3:
				System.exit(0);

			default:
				System.out.println("Enter valid input.");
				break;
			}
		}
	}
}