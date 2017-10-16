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
		
		public Usuario getUsuario(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException {
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
		
		public boolean cadastrarUsuario (Usuario usuario) throws UsuarioInvalidoException, PessoaInvalidaException, SQLException {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO ABRACE.USUARIO(login, senha) values(?, ?)";
			
			try {
				getConexao().setAutoCommit(false);
				stmt = getConexao().prepareStatement(sql);
				
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getSenha());
				
				if(stmt.execute()) {
					return true;
				}
				getConexao().commit();
			}catch(SQLException e) {
				if (getConexao() != null) {
					System.out.println(e.getMessage() + " Transação está retornando ao estado anterior");
					getConexao().rollback();
				}
				throw new RuntimeException(e);
			}finally {
				if (stmt != null) {
					stmt.close();
				}
				getConexao().setAutoCommit(true);
			}
			return false;
		}
		
		public void excluirUsuario(Usuario usuario) throws SQLException{
			String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa="+usuario.getId();
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.executeQuery();
		}
		


	}
