package DAO;

import java.sql.*;

public class ConnectionFactory {
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String dbName = "localhost";
	private Connection connection;

	public Connection getConnection() {
		if (connection != null)
			closeConnection();
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection("jdbc:derby:" + dbName + ";create=true", "abrace", "abrace");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

}