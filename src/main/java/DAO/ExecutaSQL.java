package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	 
	public void rollBack(SQLException e) {
		if (getConexao() != null) {
			try {
				getConexao().rollback();
				//System.out.println(e.getMessage() + " Transação está retornando ao estado anterior");
			} catch (SQLException e1) {
				//System.out.println(e1.getMessage());
			}
		}
	}
	
	public void verificaConexao(PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
		}
		getConexao().setAutoCommit(true);
		} catch (SQLException e1) {
			//System.out.println(e1.getMessage());
		}
	}
}