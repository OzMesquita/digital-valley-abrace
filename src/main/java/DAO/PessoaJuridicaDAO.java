package DAO;

import java.sql.Connection;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.PessoaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAO extends ExecutaSQL{
	
	public PessoaJuridicaDAO(Connection connection) {
		super(connection);
	}
	
	public void cadastrarDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException, PessoaInvalidaException  {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.PESSOA_JURIDICA (cnpj, fantasia)" + "VALUES(?, ?)";

		try {
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql);

			int id = new PessoaDAO(getConexao()).cadastrarPessoa(pessoaJ);

			stmt.setString(1, pessoaJ.getCnpj());
			stmt.setString(2, pessoaJ.getNomeFantasia());
			stmt.setInt(3, id);

			stmt.execute();
			getConexao().commit();

		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexão(stmt);
		}
	}

	public void editarDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA_JURIDICA SET cnpj=? nomeFantasia=? WHERE idPessoa=" + pessoaJ.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		try {
			new PessoaDAO(getConexao()).editarPessoa(pessoaJ);

			stmt.setDate(1, Date.valueOf(pessoaJ.getCnpj()));
			stmt.setString(2, pessoaJ.getNomeFantasia());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexão(stmt);
		}
	}

	public void excluirDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException {

		String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoaJ.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		stmt.execute();
		stmt.close();
	}

	public ArrayList<PessoaJuridica> listarDoadorJuridico(boolean situacao) {
		
		return null;
	}
}