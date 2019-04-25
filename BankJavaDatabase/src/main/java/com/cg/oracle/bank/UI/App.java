package com.cg.oracle.bank.UI;

import java.util.Scanner;

import com.cg.oracle.bank.beans.*;
import com.cg.oracle.bank.exceptions.InvalidAadhar;
import com.cg.oracle.bank.exceptions.InvalidEmail;
import com.cg.oracle.bank.exceptions.InvalidMobile;
import com.cg.oracle.bank.service.*;

public class App {
	static double balance = 0;
	static Scanner sc = new Scanner(System.in);
	static Customer customers = new Customer();
	static Transaction transaction = new Transaction();
	static CustomerDetailsService registerCustomer = new CustomerDetailsServiceImpl();
	static CustomerDetailsServiceImpl validate = new CustomerDetailsServiceImpl();
	static TransactionDetailsServiceImpl service = new TransactionDetailsServiceImpl();

	public static void main(String[] args) throws Exception {

		select();

	}

	public static void select() throws Exception {
		System.out.println("1)Registration\n2)Login\n3)Exit");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			getInfo();
			break;
		case 2:
			toLogin();
			break;
		case 3:
			System.out.println("\nSystem Exited. Thank You.....");
			System.exit(0);
		}
	}

	public static void toLogin() throws Exception {
		System.out.println("Account No :");
		long accountNo = sc.nextLong();
		System.out.println("Password :");
		String password = sc.next();
		if (registerCustomer.login(accountNo, password)) {
			transaction(accountNo);
		}
	}

	public static void transaction(long accountNo) throws Exception {
		int choice;
		System.out.println("1)Deposit\n2)Debit\n3)Fund Transfer\n4)Check Balance");
		choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("Enter amount to deposit :");
			double amount = sc.nextDouble();
			System.out.println(
					"Amount Deposited Successfully. Available Balance is :" + service.deposit(accountNo, amount));

			System.out.println(
					"********************************************************************************************");

			System.out.println("Do you want to continue ?\nPress 1 to continue press any key to exit.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				transaction(accountNo);
				break;
			default:
				System.out.println("\nSystem Exited. Thank You.....");
				System.exit(0);
			}
			break;
		case 2:
			System.out.println("Enter amount to debit :");
			double amount1 = sc.nextDouble();
			System.out.println(
					"Ammount Debited Successfully. Available Balance is :" + service.debit(accountNo, amount1));

			System.out.println(
					"********************************************************************************************");

			System.out.println("Do you want to continue ?\nPress 1 to continue press any key to exit.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				transaction(accountNo);
				break;
			default:
				System.out.println("\nSystem Exited. Thank You.....");
				System.exit(0);
			}
			break;
		case 3:
			System.out.println("Enter To Account No :");
			long toAccount = sc.nextLong();
			System.out.println("Enter amount to transfer :");
			double amount2 = sc.nextDouble();
			System.out.println("Amount Successfully Transfered to Account No " + toAccount
					+ ".\nAvailable Balance in Your Account is :"
					+ service.fundTransfer(accountNo, toAccount, amount2));

			System.out.println(
					"********************************************************************************************");

			System.out.println("Do you want to continue ?\nPress 1 to continue press any key to exit.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				transaction(accountNo);
				break;
			default:
				System.out.println("\nSystem Exited. Thank You.....");
				System.exit(0);
			}
			break;
		case 4:
			System.out.println("Available Balance is :" + service.checkBalance(accountNo));

			System.out.println(
					"********************************************************************************************");

			System.out.println("Do you want to continue ?\nPress 1 to continue press any key to exit.");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				transaction(accountNo);
				break;
			default:
				System.out.println("\nSystem Exited. Thank You.....");
				System.exit(0);
			}
			break;
		case 5:
			System.out.println("\nSystem Exited. Thank You.....");
			System.exit(0);
			break;
		default:
			System.out.println("\nSystem Exited. Thank You.....");
			System.exit(0);
		}
	}

	public static void getInfo() throws Exception {
		System.out.println("Enter details to register :");
		System.out.println("Enter Firstname :");
		customers.setFirstName(sc.next());
		System.out.println("Enter Lastname :");
		customers.setLastName(sc.next());
		System.out.println("Enter Email :");
		String email = sc.next();
		if (validate.emailValidate(email)) {
			customers.setEmail(email);
			System.out.println("Set Password :");
			String pass = sc.next();
			if (validate.checkPassword(pass)) {
				customers.setPassword(pass);
				System.out.println("Enter PANcard No :");
				customers.setPancardNo(sc.next());
				System.out.println("Enter Aadhar No :");
				long aadhar = sc.nextLong();
				if (validate.validAadhar(aadhar)) {
					customers.setAadharNo(aadhar);
					System.out.println("Enter Address :");
					customers.setAddress(sc.next());
					System.out.println("Enter Mobile No :");
					long mobile = sc.nextLong();
					if (validate.validMobile(mobile)) {
						customers.setMobile(mobile);
						customers.setBalance(balance);
						System.out.println("Registered Successfully.\n\nYour Account Number is :"
								+ registerCustomer.registration(customers));

						System.out.println(
								"********************************************************************************************");

						System.out.println("Do you want to continue ?\nPress 1 to continue press any key to exit.");
						int choice = sc.nextInt();
						switch (choice) {
						case 1:
							select();
							break;
						default:
							System.exit(0);
						}
					} else
						throw new InvalidMobile("Invalid Mobile Number");
				} else
					throw new InvalidAadhar("Invalid Aadhar Number");
			}
		} else
			throw new InvalidEmail("Inveli Email Id");
	}
}
