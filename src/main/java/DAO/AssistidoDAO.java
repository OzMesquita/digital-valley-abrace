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
			PessoaFisicaDAO pessoaFisica = new PessoaFisicaDAO(getConexao());
			pessoaFisica.inserirPessoaFisica(assistido);
			cadastrarAssistido(assistido);
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
			throw e;
		}
		return true;
	}

	public void cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.Assistido (idpessoa, status, apelido, tipocancer)" + "VALUES (?, ?, ?, ?)";
		// prepared statement para inserção
		stmt = getConexao().prepareStatement(sql);
		// seta os valores
		stmt.setInt(1, assistido.getId());
		stmt.setBoolean(2, assistido.getSituacao());
		stmt.setString(3, assistido.getApelido());
		stmt.setString(4, assistido.getTipoDeCancer());
		// executa
		stmt.execute();
		stmt.close();
	}
	
	public boolean editarAssistido(Assistido assistido) throws PessoaInvalidaException {
		try {
			getConexao().setAutoCommit(false);
			if(assistido.getSituacao() == false) {
				excluirAssistido(assistido);
			}
			PessoaFisicaDAO pessoaFisica = new PessoaFisicaDAO(getConexao());
			pessoaFisica.editarDoadorFisico(assistido);
			editar(assistido);
			getConexao().commit();
			return true;
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
	}

	private void editar(Assistido assistido) {
		String sql = "UPDATE ABRACE.Assistido SET tipoCancer=?, apelido=?, status=? WHERE idPessoa=?";
		PreparedStatement stmt;
		try {
			stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, assistido.getTipoDeCancer());
			stmt.setString(2, assistido.getApelido());
			stmt.setBoolean(3, assistido.getSituacao());
			stmt.setInt(4, assistido.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public boolean excluirAssistido(Assistido assistido) {
		try {
			String sql = "UPDATE ABRACE.Pessoa SET ativo=False and isDoador=False WHERE ABRACE.Pessoa.idPessoa=?";
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, assistido.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Assistido getAssistido(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro, ABRACE.Pessoa.isDoador,";
		String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
		String sql = "SELECT " + informacaoPessoa + informacaoPessoaFisica
				+ " ABRACE.Assistido.tipoCancer, ABRACE.Assistido.apelido, ABRACE.Assistido.status"
				+ " FROM ABRACE.Assistido, ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
				+ " WHERE ABRACE.Pessoa.idPessoa=? AND ABRACE.Pessoa.idPessoa=ABRACE.Pessoa_Fisica.idPessoa "
				+ " AND ABRACE.Pessoa_Fisica.idPessoa=ABRACE.Assistido.idPessoa";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				boolean ativo = rs.getBoolean(1);
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
				String tipoDeCancer = rs.getString(12);
				String apelido = rs.getString(13);
				boolean situacao = rs.getBoolean(14);
				return new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, isDoador, cpf, rg, dataNasc, apelido, tipoDeCancer, situacao);
			}
			rs.close();
			stmt.close();
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
		String sql = "SELECT * " + 
				"FROM PESSOA " + 
				"JOIN PESSOA_FISICA ON PESSOA.idPessoa=PESSOA_FISICA.idPessoa " + 
				"join ASSISTIDO ON ASSISTIDO.idPessoa=PESSOA_FISICA.idPessoa WHERE PESSOA.ativo = ? order by nome";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, true);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idpessoa");
				String nome = rs.getString("nome");
				String endereco = rs.getString("endereco");
				String telefone = rs.getString("telefone1");
				String telefone2 = rs.getString("telefone2");
				String email = rs.getString("email");
				LocalDate dataCadastro = rs.getDate("datacadastro").toLocalDate();
				boolean isDoador = rs.getBoolean("isdoador");
				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				LocalDate dataNasc = rs.getDate("datanascimento").toLocalDate();
				String tipoDeCancer = rs.getString("tipocancer");
				String apelido = rs.getString("apelido");
				boolean situacao = rs.getBoolean("status");
				assistidos.add(new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, true, isDoador, cpf, rg, dataNasc, apelido, tipoDeCancer, situacao));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} catch (AssistidoInvalidoException e) {
			e.printStackTrace();
		}
		return assistidos;
	}
}