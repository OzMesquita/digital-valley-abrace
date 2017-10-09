package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UsuarioInvalidoException;
import exceptions.PessoaInvalidaException;
import model.Usuario;

public class UsuarioDAO extends ExecutaSQL{

		public UsuarioDAO(Connection connection) {
			super(connection);
		}
		
		public Usuario getFuncionario(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException {
	        try {
	        	String sql = "SELECT idPessoa FROM ABRACE.Usuario WHERE login=? AND senha=?";
	        	PreparedStatement ps = getConexao().prepareStatement(sql);
	            ps.setString(1, login);
	            ps.setString(2, senha);
	            ResultSet rs = ps.executeQuery();
	            if(rs != null){
	                rs.next();
	                int id = rs.getInt(1);
	                return new Usuario(id, login, senha);
	            }
	            }catch (SQLException ex) {
	                System.err.println("Erro com a sintaxe SQL no metodo de consulta. GerenteDAO");    
	            }
	        return null;
	    }
		


	}
