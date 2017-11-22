package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAO extends ExecutaSQL{
	
	public PessoaJuridicaDAO(Connection connection) {
		super(connection);
	}
	
	public boolean inserirDoadorJuridico(PessoaJuridica pessoaJuridica) throws PessoaInvalidaException, SQLException {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.cadastrarPessoa(pessoaJuridica);
			cadastrarDoadorJuridico(pessoaJuridica);
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
			throw e;
		}
		return true;
	}
	
	private void cadastrarDoadorJuridico(PessoaJuridica pessoaJuridica) throws SQLException{
        PreparedStatement stmt = null;
        String sql = "INSERT INTO ABRACE.Pessoa_Juridica (cnpj, fantasia, idPessoa) VALUES (?, ?, ?)";
        stmt = getConexao().prepareStatement(sql);
        if(getPessoaPeloCNPJ(pessoaJuridica.getCnpj())) {
			rollBack(new SQLException("CNPJ já existente no sistema!"));
			throw new SQLException("CNPJ já existente no sistema!");
		}
        stmt.setString(1, pessoaJuridica.getCnpj());
        stmt.setString(2, pessoaJuridica.getNomeFantasia());
        stmt.setInt(3, pessoaJuridica.getId());
        stmt.execute();
    }
	
	public boolean getPessoaPeloCNPJ(String cnpj) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "SELECT ABRACE.Pessoa_Juridica.idPessoa FROM ABRACE.Pessoa_Juridica WHERE ABRACE.Pessoa_Juridica.cnpj=?";
		stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, cnpj);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean editarDoadorJuridico(PessoaJuridica pessoaJuridica) {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.editarPessoa(pessoaJuridica);
			editar(pessoaJuridica);
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	} 
	
	public void editar(PessoaJuridica pessoaJuridica) throws SQLException {
		String sql = "UPDATE ABRACE.Pessoa_Juridica SET FANTASIA=?, CNPJ=? WHERE idPessoa=?";
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, pessoaJuridica.getNomeFantasia());
		stmt.setString(2, pessoaJuridica.getCnpj());
		stmt.setInt(3, pessoaJuridica.getId());
		stmt.executeUpdate();
	}
	
	public boolean excluirDoadorJuridico(PessoaJuridica pessoaJ) {
		try{
			String sql = "UPDATE ABRACE.Pessoa SET ativo=false WHERE idPessoa=?";
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, pessoaJ.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<PessoaJuridica> listarDoadorJuridico() {
		ArrayList<PessoaJuridica> listaPessoasJuridicas = new ArrayList<PessoaJuridica>();
		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro,ABRACE.Pessoa.isDoador,";
		String sql = "SELECT " + informacaoPessoa+ 
				"ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia FROM ABRACE.PESSOA_JURIDICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_JURIDICA.idPessoa=ABRACE.PESSOA.idPessoa AND ativo=? AND isDoador=?";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone1 = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				boolean isDoador = rs.getBoolean(8);
				String cnpj = rs.getString(9);
				String nomeFantasia = rs.getString(10);
				listaPessoasJuridicas.add(new PessoaJuridica(id, nome, endereco, telefone1, telefone2, dataCadastro, email, true, isDoador, cnpj, nomeFantasia));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaJuridicaInvalidaException e) {
			e.printStackTrace();
		} 
		return listaPessoasJuridicas;
	}

	
	public ArrayList<PessoaJuridica> listarTabelaDoadorJuridico() {
		ArrayList<PessoaJuridica> listaPessoasJuridicas = new ArrayList<PessoaJuridica>();
		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro,ABRACE.Pessoa.isDoador, ABRACE.Pessoa.ativo,";
		String sql = "SELECT " + informacaoPessoa+ 
				"ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia FROM ABRACE.PESSOA_JURIDICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_JURIDICA.idPessoa=ABRACE.PESSOA.idPessoa";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone1 = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				boolean isDoador = rs.getBoolean(8);
				boolean ativo = rs.getBoolean(9);
				String cnpj = rs.getString(10);
				String nomeFantasia = rs.getString(11);
				listaPessoasJuridicas.add(new PessoaJuridica(id, nome, endereco, telefone1, telefone2, dataCadastro, email, ativo, isDoador, cnpj, nomeFantasia));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaJuridicaInvalidaException e) {
			e.printStackTrace();
		} 
		return listaPessoasJuridicas;
	}
	public PessoaJuridica getPessoaJuridica(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,ABRACE.Pessoa.isDoador,";
		String sql = "SELECT " + informacaoPessoa + " ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia "
				+ " FROM ABRACE.Pessoa, ABRACE.Pessoa_Juridica"
				+ " WHERE ABRACE.Pessoa.idPessoa=? AND ABRACE.Pessoa.idPessoa=ABRACE.Pessoa_Juridica.idPessoa";
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
				String cnpj = rs.getString(9);
				String fantasia = rs.getString(10);
				return new PessoaJuridica(id, nome, endereco, telefone, telefone2, dataCadastro, email, ativo, isDoador, cnpj, fantasia);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaJuridicaInvalidaException e) {
			e.printStackTrace();
		}
		return null;
	}
}