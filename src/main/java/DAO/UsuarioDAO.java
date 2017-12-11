package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.UsuarioInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;
import model.Usuario;

public class UsuarioDAO extends ExecutaSQL{

		public UsuarioDAO(Connection connection) {
			super(connection);
		}
		
		public Usuario getUsuario(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException, SQLException {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
	        	String sql = "SELECT ABRACE.Usuario.idPessoa FROM ABRACE.Usuario, ABRACE.Pessoa WHERE ABRACE.Usuario.login=? AND ABRACE.Usuario.senha=? AND ABRACE.Pessoa.ativo=?";
	        	stmt = getConexao().prepareStatement(sql);
	        	stmt.setString(1, login);
	            stmt.setString(2, senha);
	            stmt.setBoolean(3, true);
	            rs = stmt.executeQuery();
	            if(rs.next()){
	                int id = rs.getInt(1);
	                return new Usuario(id, login, senha);
	            }
	            rs.close();
				stmt.close();
	        }catch (SQLException ex) {
	            System.err.println("Erro com a sintaxe SQL no metodo de consulta. GerenteDAO");    
	        }finally {
	        	 rs.close();
		         stmt.close();
			}
	        return null;
	    }
		
		public boolean inserirUsuario(Usuario usuario) throws PessoaInvalidaException {
			try {
				getConexao().setAutoCommit(false);
				PessoaDAO pessoa = new PessoaDAO(getConexao());
				PessoaFisicaDAO pessoaFisica = new PessoaFisicaDAO(getConexao());
				pessoa.cadastrarPessoa(usuario);
				pessoaFisica.cadastrarPessoaFisica(usuario);
				cadastrarUsuario(usuario);
				getConexao().commit();
			} catch (SQLException e) {
				rollBack(e);
				return false;
			}
			return true;
		}

		private void cadastrarUsuario(Usuario usuario) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO ABRACE.Usuario (login, senha, idPessoa)" + "VALUES (?, ?, ?)";
				stmt = getConexao().prepareStatement(sql);
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getSenha());
				stmt.setInt(3, usuario.getId());
				stmt.execute();
				stmt.close();
		}
		
		public boolean editarUsuario(Usuario usuario) {
			String sql = "UPDATE ABRACE.Usuario SET ABRACE.Usuario.login=?, ABRACE.Usuario.senha=? WHERE ABRACE.Usuario.idPessoa=?";
			PreparedStatement stmt = null;
			try {
				stmt = getConexao().prepareStatement(sql);
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getSenha());
				stmt.setInt(3, usuario.getId());
				stmt.executeUpdate();
				stmt.close();
			}catch(SQLException e) {
				rollBack(e);
				return false;
			}
			return true;
		}
		
		public void excluirUsuario(Usuario usuario) throws SQLException{
			String sql = "UPDATE ABRACE.Pessoa SET ativo=false WHERE idPessoa=?";
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.executeQuery();
			stmt.close();
		}
		
		public ArrayList<Usuario> listarUsuarios() throws SQLException{
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			String informacaoPessoa = "ABRACE.Pessoa.idPessoa, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
	                				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,ABRACE.Pessoa.isDoador,";
			String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
			String sql = "SELECT "+informacaoPessoa+informacaoPessoaFisica+ "ABRACE.Usuario.login, ABRACE.Usuario.senha "
					+ "FROM ABRACE.Pessoa, ABRACE.Pessoa_Fisica, ABRACE.Usuario "
					+ "WHERE ABRACE.Pessoa.ativo=True AND ABRACE.Pessoa.idPessoa=ABRACE.Pessoa_Fisica.idPessoa "
					+ "AND ABRACE.Pessoa_Fisica.idPessoa=ABRACE.Usuario.idPessoa";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = getConexao().prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					int id = rs.getInt(1);
					String nome = rs.getString(2);
					String endereco = rs.getString(3);
					String telefone = rs.getString(4);
					String telefone2 = rs.getString(5);
					String email = rs.getString(6);
					LocalDate dataCadastro = rs.getDate(7).toLocalDate();
					boolean isDoador = rs.getBoolean(8);
					String cpf = rs.getString(9);
					String rg = rs.getString(10);
					LocalDate dataNasc = rs.getDate(11).toLocalDate();
					String login = rs.getString(12);
					String senha = rs.getString(13);
					usuarios.add(new Usuario(id, nome, endereco, dataCadastro, telefone, telefone2, email, true, isDoador, cpf, rg, dataNasc, login, senha));
				}
				rs.close();
				stmt.close();
			}catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}catch (PessoaInvalidaException e) {
				e.printStackTrace();
			} catch (PessoaFisicaException e) {
				e.printStackTrace();
			} catch (UsuarioInvalidoException e) {
				e.printStackTrace();
			}finally {
				rs.close();
				stmt.close();
			}
			return usuarios;
		}
		
		public Usuario getUsuario(int id) throws SQLException, UsuarioInvalidoException, PessoaFisicaException {
			PessoaFisicaDAO dao = new PessoaFisicaDAO(getConexao());
			PessoaFisica pessoa = dao.getPessoaFisica(id);
			String sql = "SELECT ABRACE.Usuario.login, ABRACE.Usuario.senha FROM ABRACE.Usuario WHERE ABRACE.Usuario.idPessoa=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = getConexao().prepareStatement(sql);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				if (rs.next()) {
					String login = rs.getString(1);
					String senha = rs.getString(2);
					return new Usuario(id, pessoa.getNome(), pessoa.getEndereco(), pessoa.getDataCadastro(), pessoa.getTelefone(), pessoa.getTelefone2(),
							pessoa.getEmail(), pessoa.isAtivo(), pessoa.isDoador(), pessoa.getCpf(), pessoa.getRg(), pessoa.getDataNasc(), login, senha);
				}
				rs.close();
				stmt.close();
			} catch (PessoaInvalidaException e) {
				e.printStackTrace(); 
			}finally {
				rs.close();
				stmt.close();
			}
			return null;
		}
}
