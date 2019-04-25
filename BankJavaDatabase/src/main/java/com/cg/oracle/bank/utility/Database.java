package com.cg.oracle.bank.utility;

import java.sql.*;

public class Database {

	public Connection connect() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "Koushik", "Koushik@9");
		return con;
	}

}
