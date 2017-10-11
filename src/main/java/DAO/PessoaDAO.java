package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaInvalidaException;
import model.Pessoa;

public class PessoaDAO extends ExecutaSQL {

	public PessoaDAO(Connection connection) {
		super(connection);
	}

	// Incompleto
	@SuppressWarnings("deprecation")

	public int cadastrarPessoa(Pessoa pessoa) throws SQLException, PessoaInvalidaException {
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.PESSOA" + "(ativo, datacadastro, email, telefone2, telefone1, endereco, nome)"
				+ "values (?, ?, ?, ?, ?, ?, ?)";
		try {
			// prepared statement para inser��o
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql);
			// seta os valores
			stmt.setBoolean(1, pessoa.isAtivo());
			pessoa.setDataCadastro(LocalDate.now());
			stmt.setDate(2, new Date(pessoa.getDataCadastro().getYear(), pessoa.getDataCadastro().getMonthValue() + 1,
					pessoa.getDataCadastro().getDayOfMonth()));
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getTelefone2());
			stmt.setString(5, pessoa.getTelefone());
			stmt.setString(6, pessoa.getEndereco());
			stmt.setString(7, pessoa.getNome());
			// executa
			stmt.execute();
			getConexao().commit();
		} catch (SQLException e) {
			if (getConexao() != null) {
				System.out.println("Transa��o est� retornando ao estado anterior");
				getConexao().rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			getConexao().setAutoCommit(true);
		}
		String sql2 = "select idpessoa from pessoa where nome=" + pessoa.getNome();
		int idpessoa;
		try {
			// prepared statement para inser��o
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql2);
			// seta os valores
			idpessoa = stmt.executeQuery().getInt(0);
			// executa
			getConexao().commit();
		} catch (SQLException e) {
			if (getConexao() != null) {
				System.out.println("Transa��o est� retornando ao estado anterior");
				getConexao().rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			getConexao().setAutoCommit(true);
		}
		return idpessoa;
	}

}