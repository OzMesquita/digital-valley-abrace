package DAO;

import java.sql.Connection;

public class ExecutaSQL {
	private Connection connection;
	public ExecutaSQL(Connection connection) {
		setConnection(connection);
	}
	
	 public Connection getConexao(){
		 return this.connection;
	 }
	    
	 public void setConnection(Connection connection){
		 if(connection != null)
			 this.connection = connection;
	 }
}