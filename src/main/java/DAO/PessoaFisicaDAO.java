package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;

public class PessoaFisicaDAO extends ExecutaSQL {

	public PessoaFisicaDAO(Connection connection) {
		super(connection);
	}

	public int cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws SQLException, PessoaInvalidaException {
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.PESSOA_FISICA (datanascimento, rg, cpf, idPessoa) values (?, ?, ?, ?)";
		int id=0;
		try {
			// prepared statement for insertion
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql);

			id = new PessoaDAO(getConexao()).cadastrarPessoa(pessoaFisica);

			// seta os valores
			stmt.setDate(1, Date.valueOf(pessoaFisica.getDataNasc()));
			stmt.setString(2, pessoaFisica.getRg());
			stmt.setString(3, pessoaFisica.getCpf());
			stmt.setInt(4, id);

			// execute
			stmt.execute();
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexão(stmt);
		}
		return id;
	}

	public void editarPessoaFisca(PessoaFisica pessoaFisica) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA SET datanascimento=? rg=? cpf=? WHERE idPessoa=" + pessoaFisica.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		try {
			new PessoaDAO(getConexao()).editarPessoa(pessoaFisica);

			stmt.setDate(1, Date.valueOf(pessoaFisica.getDataNasc()));
			stmt.setString(2, pessoaFisica.getRg());
			stmt.setString(3, pessoaFisica.getCpf());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexão(stmt);
		}
	}

	public ArrayList<PessoaFisica> listarPessoasFisicas(Boolean situacao) throws PessoaInvalidaException, PessoaFisicaException {
		ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<PessoaFisica>();
		String sql = "SELECT ABRACE.PESSOA_FISICA.idPessoa, rg, cpf, dataNascimento FROM ABRACE.PESSOA_FISICA,ABRACE.PESSOA where ABRACE.PESSOA.ativo = ? and ABRACE.PESSOA_FISICA.idPessoa=ABRACE.PESSOA.idPessoa";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, situacao);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String rg = rs.getString(2);
				String cpf = rs.getString(3);
				LocalDate dataNascimento = rs.getDate(4).toLocalDate();

				listaPessoasFisicas.add(new PessoaFisica(id, cpf, rg, dataNascimento));
			}
			stmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return listaPessoasFisicas;
	}

	public static void main(String[] args) throws PessoaInvalidaException, PessoaFisicaException {
		ArrayList<PessoaFisica> pessoas = new PessoaFisicaDAO(new ConnectionFactory().getConnection())
				.listarPessoasFisicas(true);
		for (int i = 0; i < pessoas.size(); i++) {
			System.out.println(pessoas.get(i).getId());
		}
	}
}