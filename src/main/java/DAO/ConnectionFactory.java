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
			Class.forName(driver);//Se der problema na reinstala��o mude shutdown=true para create=true, reinstale e mude para shutdown novamente 
			connection = DriverManager.getConnection("jdbc:derby:" + dbName + ";create=true", "abrace", "abrace");
			Statement s = connection.createStatement();
			s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.language.sequence.preallocator', '1')");
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