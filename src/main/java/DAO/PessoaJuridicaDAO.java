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
	
	public boolean inserirDoadorJuridico(PessoaJuridica pessoaJ) throws PessoaInvalidaException, SQLException {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.cadastrarPessoa(pessoaJ);
			cadastrarDoadorJuridico(pessoaJ);
			getConexao().commit();
			return true;
		} catch (SQLException e) {
			rollBack(e);
			throw e;
		}
	}
	
	private void cadastrarDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException{
        PreparedStatement stmt = null;
        String sql = "INSERT INTO ABRACE.PESSOA_JURIDICA (cnpj, fantasia, idPessoa)" + "VALUES(?, ?, ?, ?)";

        stmt = getConexao().prepareStatement(sql);
        
        if(getPessoaPeloCNPJ(pessoaJ.getCnpj())) {
			rollBack(new SQLException("CNPJ j� existente no sistema!"));
			throw new SQLException("CNPJ j� existente no sistema!");
		}

        stmt.setString(1, pessoaJ.getCnpj());
        stmt.setString(2, pessoaJ.getNomeFantasia());
        stmt.setInt(3, pessoaJ.getId());

        stmt.execute();
    }
	
	public boolean getPessoaPeloCNPJ(String cnpj) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "SELECT ABRACE.PESSOA_JURIDICA.idPessoa FROM ABRACE.PESSOA_JURIDICA WHERE ABRACE.PESSOA_FISICA.cnpj = ?";
		stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, cnpj);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
	
	public boolean editarDoadorJuridico(PessoaJuridica pessoaJ) {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.editarPessoa(pessoaJ);
			editar(pessoaJ);
			getConexao().commit();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	} 
	
	public void editar(PessoaJuridica pessoaJ) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA_JURIDICA SET FANTASIA = ?, CNPJ = ? WHERE IDPESSOA = ?";
	
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		
		stmt.setString(1, pessoaJ.getNomeFantasia());
		stmt.setString(2, pessoaJ.getCnpj());
		stmt.setInt(3, pessoaJ.getId());
			
		stmt.executeUpdate();
		
	}
	
	public boolean excluirDoadorJuridico(PessoaJuridica pessoaJ) {
		
		try{
			String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoaJ.getId();
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<PessoaJuridica> listarDoadorJuridico() {
		ArrayList<PessoaJuridica> listaPessoasJuridicas = new ArrayList<PessoaJuridica>();
		
		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro,ABRACE.Pessoa.isDoador,";
		
		String sql = "SELECT " + informacaoPessoa+ 
				"ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia FROM ABRACE.PESSOA_JURIDICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_JURIDICA.idPessoa = ABRACE.PESSOA.idPessoa AND ativo = ? AND isDoador = ?";
		
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
		} finally {
			return listaPessoasJuridicas;
		}
	}

	public PessoaJuridica getPessoaJuridica(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,ABRACE.Pessoa.isDoador,";
		String sql = "SELECT " + informacaoPessoa + " ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia "
				+ " FROM ABRACE.Pessoa, ABRACE.Pessoa_Juridica"
				+ " WHERE ABRACE.Pessoa.idPessoa = ? AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Juridica.idPessoa";
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
	
	public static void main(String[] args) {
		
		try {
			PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
			dao.excluirDoadorJuridico(new PessoaJuridica(501,"12778567000172", "adasd"));
			ArrayList<PessoaJuridica> arrays = dao.listarDoadorJuridico();
			for(PessoaJuridica p : arrays) {
				System.out.println(p);
			}
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (PessoaJuridicaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		}
	}
}