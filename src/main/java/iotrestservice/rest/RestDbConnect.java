package iotrestservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;

public class RestDbConnect {
	public Connection getConnection() throws Exception {
		try {
			String connectionURL = "jdbc:mysql://usturlab.izu.edu.tr:3306/iot?useSSL=false";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Connection");
			connection = DriverManager.getConnection(connectionURL, "iot_user", "iot_user_1Qaz2Wsx");
			return connection;

		} catch (Exception e) {
			throw e;
		}

	}

}
