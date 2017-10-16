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
	 
	public void rollBack(SQLException e) throws SQLException {
		if (getConexao() != null) {
			getConexao().rollback();
			throw new SQLException(e.getMessage() + " Transação está retornando ao estado anterior");
		}
	}
	
	public void verificaConexao(PreparedStatement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		getConexao().setAutoCommit(true);

	}
}