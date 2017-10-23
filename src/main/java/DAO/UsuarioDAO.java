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

	public void cadastrarUsuario(Usuario usuario) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "INSERT INTO ABRACE.USUARIO(login, senha, idPessoa) values(?, ?, ?)";
				stmt = getConexao().prepareStatement(sql);

				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getSenha());
				stmt.setInt(3, usuario.getId());
				
				stmt.execute();
			
		}
		
		public boolean editarUsuario(Usuario usuario) {
			boolean executou = true;
			String sql = "UPDATE ABRACE.USUARIO SET login=?, senha=? WHERE idPessoa="+usuario.getId();
			PreparedStatement stmt = null;
			
			try {
				stmt = getConexao().prepareStatement(sql);
				
				stmt.setString(1, usuario.getUsuario());
				stmt.setString(2, usuario.getSenha());
				
				stmt.executeUpdate();
			}catch(SQLException e) {
				rollBack(e);
				executou = false;
			}finally {
				verificaConexao(stmt);
			}
			return executou;
		}
		
		public void excluirUsuario(Usuario usuario) throws SQLException{
			String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa="+usuario.getId();
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.executeQuery();
		}
		
		public ArrayList<Usuario> listarUsuarios(){
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			
			String informacaoPessoa = "ABRACE.Pessoa.idPessoa, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
	                				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
			String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
			
			String sql = "SELECT "+informacaoPessoa+informacaoPessoaFisica+ "ABRACE.Usuario.login, ABRACE.Usuario.senha"
					+ "FROM ABRACE.Pessoa, ABRACE.Pessoa_Fisica, ABRACE.Usuario"
					+ "WHERE ABRACE.Pessoa.ativo = True AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa"
					+ "AND ABRACE.Pessoa_Fisica.idPessoa = ABRACE.Usuario.idPessoa";
			
			try {
				PreparedStatement stmt = getConexao().prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String nome = rs.getString(2);
					String endereco = rs.getString(3);
					String telefone = rs.getString(4);
					String telefone2 = rs.getString(5);
					String email = rs.getString(6);
					LocalDate dataCadastro = rs.getDate(7).toLocalDate();
					String cpf = rs.getString(8);
					String rg = rs.getString(9);
					LocalDate dataNasc = rs.getDate(10).toLocalDate();
					String login = rs.getString(11);
					String senha = rs.getString(12);
					
					usuarios.add(new Usuario(id, nome, endereco, dataCadastro, telefone, telefone2, email, true, cpf, rg, dataNasc, login, senha));
				}
			}catch(SQLException e) {
				throw new RuntimeException(e.getMessage());
			}catch (PessoaInvalidaException e) {
				e.printStackTrace();
			} catch (PessoaFisicaException e) {
				e.printStackTrace();
			} catch (UsuarioInvalidoException e) {
				e.printStackTrace();
			}finally {
				return usuarios;
			}
		}

	}
