package iotrestservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;

public class RestDbConnect {
	public Connection getConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/iot?useSSL=false";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Connection");
			connection = DriverManager.getConnection(connectionURL, "root", "123456");
			return connection;

		} catch (Exception e) {
			throw e;
		}

	}

}
