package com.cg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cg.model.CustomerDetails;
import com.cg.utility.Database;

@Repository
public class BankDaoImpl implements BankDao {
	Database d = new Database();

	public CustomerDetails register(CustomerDetails cust) {
		ApplicationContext context = new ClassPathXmlApplicationContext("hibernate.cfg.xml");
		Session session = d.getSession();
		Transaction transaction = session.beginTransaction();
		session.persist(cust);
		transaction.commit();
		session.close();
		return cust;
	}

	public int login(CustomerDetails c) {
		int accountNo = 0;
		Session session = d.getSession();
		CustomerDetails cd = session.get(CustomerDetails.class, c.getAccountNo());
		if (cd.getPassword().equals(c.getPassword())) {
			accountNo = c.getAccountNo();
		}
		return accountNo;
	}

}
