package com.cg.oracle.bank.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cg.oracle.bank.exceptions.InsufficientFunds;
import com.cg.oracle.bank.exceptions.InvalidAccountNumber;
import com.cg.oracle.bank.exceptions.TransactionFailed;
import com.cg.oracle.bank.utility.Database;

public class TransactionDetailsDaoImpl implements TransactionDetailsDao {

	Database con = new Database();
	CustomerDetailsDaoImpl cust=new CustomerDetailsDaoImpl(); 
	
//	Deposit method
	public double deposit(long accountNo, double amount) throws Exception {
		PreparedStatement pst1 = con.connect()
				.prepareStatement("select * from customer_details where account_no=" + accountNo);
		ResultSet rs = pst1.executeQuery();
		rs.next();
		double bal = rs.getDouble("balance") + amount;
		PreparedStatement pst2 = con.connect()
				.prepareStatement("update customer_details set balance=? where account_no=" + accountNo);
		pst2.setDouble(1, bal);
		int i = pst2.executeUpdate();
		if (i == 1)
			return bal;
		else
			throw new TransactionFailed("Transaction Failed");
	}

//	Debit/Withdraw method
	public double debit(long accountNo, double amount) throws Exception {
		double bal;
		PreparedStatement pst1 = con.connect()
				.prepareStatement("select * from customer_details where account_no=" + accountNo);
		ResultSet rs = pst1.executeQuery();
		rs.next();
		if (rs.getDouble("balance") > amount) {
			bal = rs.getDouble("balance") - amount;
			PreparedStatement pst2 = con.connect()
					.prepareStatement("update customer_details set balance=? where account_no=" + accountNo);
			pst2.setDouble(1, bal);
			int i = pst2.executeUpdate();
			if (i == 1)
				return bal;
			else
				throw new TransactionFailed("Transaction Failed");
		} else
			throw new InsufficientFunds("Insufficient Funds");
	}

	public double checkBalance(long accountNo) throws Exception {
		PreparedStatement pst1 = con.connect()
				.prepareStatement("select balance from customer_details where account_no=" + accountNo);
		ResultSet rs = pst1.executeQuery();
		rs.next();
		return rs.getDouble("balance");

	}

	public double fundTransfer(long accountNo, long toAccount, double amount) throws Exception {
		double bal, bal1;
		if(cust.validAccount(toAccount)) {
		PreparedStatement pst1 = con.connect()
				.prepareStatement("select * from customer_details where account_no=" + accountNo);
		ResultSet rs = pst1.executeQuery();
		rs.next();
		if (rs.getDouble("balance") > amount) {
			bal = rs.getDouble("balance") - amount;
			PreparedStatement pst3 = con.connect()
					.prepareStatement("update customer_details set balance=? where account_no=" + accountNo);
			pst3.setDouble(1, bal);
			int i = pst3.executeUpdate();
			rs.close();
			PreparedStatement pst2 = con.connect()
					.prepareStatement("select * from customer_details where account_no=" + toAccount);
			ResultSet rs1 = pst2.executeQuery();
			rs1.next();
			bal1 = rs1.getDouble("balance") + amount;

			PreparedStatement pst4 = con.connect()
					.prepareStatement("update customer_details set balance=? where account_no=" + toAccount);
			pst4.setDouble(1, bal1);
			int j = pst4.executeUpdate();
			rs1.close();
			if (i == 1 && j == 1) {
				PreparedStatement pst5 = con.connect()
						.prepareStatement("insert into transaction_details values(transaction_id.nextval,?,?,?)");
				pst5.setLong(1, accountNo);
				pst5.setLong(2, toAccount);
				pst5.setDouble(3, amount);
				int k = pst5.executeUpdate();
				if (k == 1)
					return bal;
				else
					throw new TransactionFailed("Transaction Failed");
			} else
				throw new TransactionFailed("Transaction Failed");
		} else
			throw new InsufficientFunds("Insufficient Funds");
		}else throw new InvalidAccountNumber("Invalid Account Number");
	}

}
