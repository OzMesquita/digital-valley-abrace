package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ExecutaSQL;
import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;

public class AssistidoDAO extends ExecutaSQL {

	public AssistidoDAO(Connection connection) {
		super(connection);
	}

	public boolean inserirAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {

		try {

			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			PessoaFisicaDAO pessoaFisica = new PessoaFisicaDAO(getConexao());
			pessoa.cadastrarPessoa(assistido);
			pessoaFisica.cadastrarPessoaFisica(assistido);
			cadastrarAssistido(assistido);
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}

	public void cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.ASSISTIDO" + "(idpessoa, status, apelido, tipocancer) values (?, ?, ?, ?)";
		// try {
		// prepared statement para inserção
		stmt = getConexao().prepareStatement(sql);

		// seta os valores
		stmt.setInt(1, assistido.getId());
		stmt.setBoolean(2, assistido.getSituacao());
		stmt.setString(3, assistido.getApelido());
		stmt.setString(4, assistido.getTipoDeCancer());
		// executa
		stmt.execute();
	}

	public boolean editarAssistido(Assistido assistido) {
		boolean executou = true;
		String sql3 = "UPDATE ABRACE.ASSISTIDO SET tipoCancer=?, apelido=?, status=? WHERE idPessoa = ?";
		PreparedStatement stmt = null;
		try {
			stmt = getConexao().prepareStatement(sql3);
			stmt.setString(1, assistido.getTipoDeCancer());
			stmt.setString(2, assistido.getApelido());
			stmt.setBoolean(3, assistido.getSituacao());
			stmt.setInt(4, assistido.getId());
			
			stmt.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			rollBack(e);
			
			executou = false;
		}finally {
			verificaConexao(stmt);
		}
		return executou;
	}
	public boolean excluirAssistido(Assistido assistido) {
		try {
			String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa="+assistido.getId();
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Assistido getAssistido(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
		String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
		String sql = "SELECT " + informacaoPessoa + informacaoPessoaFisica
				+ " ABRACE.Assistido.tipoCancer, ABRACE.Assistido.apelido, ABRACE.Assistido.status"
				+ " FROM ABRACE.Assistido, ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
				+ " WHERE ABRACE.Pessoa.idPessoa = ? AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa "
				+ " AND ABRACE.Pessoa_Fisica.idPessoa = ABRACE.Assistido.idPessoa";
		try {
			PreparedStatement ps = getConexao().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				boolean ativo = rs.getBoolean(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				String cpf = rs.getString(8);
				String rg = rs.getString(9);
				LocalDate dataNasc = rs.getDate(10).toLocalDate();
				String tipoDeCancer = rs.getString(11);
				String apelido = rs.getString(12);
				boolean situacao = rs.getBoolean(13);
				return new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, cpf, rg,
						dataNasc, apelido, tipoDeCancer, situacao);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} catch (AssistidoInvalidoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Assistido> listaAssistido() {
		ArrayList<Assistido> assistidos = new ArrayList<Assistido>();
		String informacaoPessoa = "ABRACE.Pessoa.idPessoa, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
		String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
		// Vou pegar essas duas Strings que são necessárias para pegar os assistidos e
		// usar no SELECT do sql para pegar os dados de todas essas tabelas.
		String sql = "SELECT " + informacaoPessoa + informacaoPessoaFisica
				+ " ABRACE.Assistido.tipoCancer, ABRACE.Assistido.apelido, ABRACE.Assistido.status"
				+ " FROM ABRACE.Assistido, ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
				+ " WHERE ABRACE.Pessoa.ativo = True AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa "
				+ " AND ABRACE.Pessoa_Fisica.idPessoa = ABRACE.Assistido.idPessoa";
		try {
			PreparedStatement ps = getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
				String tipoDeCancer = rs.getString(11);
				String apelido = rs.getString(12);
				boolean situacao = rs.getBoolean(13);
				assistidos.add(new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, true, cpf,
						rg, dataNasc, apelido, tipoDeCancer, situacao));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} catch (AssistidoInvalidoException e) {
			e.printStackTrace();
		} finally {
			return assistidos;
		}
	}
}