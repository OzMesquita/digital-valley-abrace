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
	
	public boolean inserirPessoaFisica(PessoaFisica pessoaFisica) throws SQLException, PessoaInvalidaException {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.cadastrarPessoa(pessoaFisica);
			cadastrarPessoaFisica(pessoaFisica);
			getConexao().commit();
			return true;
		}catch(SQLException e) {
			rollBack(e);
			throw e;
		}catch(PessoaInvalidaException e1) {
			System.out.println(e1.getMessage());
			throw e1;
		}
		
	}

	public void cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws SQLException  {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.PESSOA_FISICA (dataNascimento, rg, cpf, idPessoa) VALUES (?, ?, ?, ?)";
			stmt = getConexao().prepareStatement(sql);

			stmt.setDate(1, Date.valueOf(pessoaFisica.getDataNasc()));
			stmt.setString(2, pessoaFisica.getRg());
			stmt.setString(3, pessoaFisica.getCpf());
			stmt.setInt(4, pessoaFisica.getId());

			stmt.execute();
	}
	
	public boolean getPessoaPeloCPF(String cpf) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "SELECT ABRACE.PESSOA_FISICA.idPessoa FROM ABRACE.PESSOA_FISICA WHERE ABRACE.PESSOA_FISICA.cpf = ?";
		stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, cpf);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}

	public void editar(PessoaFisica pessoaFisica) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA_FISICA SET dataNascimento=?, rg=?, cpf=? WHERE idPessoa = ?";
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		
		stmt.setDate(1, Date.valueOf(pessoaFisica.getDataNasc()));
		stmt.setString(2, pessoaFisica.getRg());
		stmt.setString(3, pessoaFisica.getCpf());
		stmt.setInt(4, pessoaFisica.getId());
		
		stmt.executeUpdate();
	}
	
	public boolean editarDoadorFisico(PessoaFisica pessoaFisica) {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.editarPessoa(pessoaFisica);
			editar(pessoaFisica);
			
			getConexao().commit();
		}catch(SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}


	public boolean excluirDoadorFisico(PessoaFisica pessoaFisica) {
		
		try {
			String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoaFisica.getId();
			PreparedStatement stmt = getConexao().prepareStatement(sql);

			stmt.execute();
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public ArrayList<PessoaFisica> listarPessoasFisicas() throws SQLException {
		ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<PessoaFisica>();

		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro, ABRACE.PESSOA.isDoador,";

		String sql = "SELECT " + informacaoPessoa
				+ "ABRACE.PESSOA_FISICA.cpf, ABRACE.PESSOA_FISICA.rg, ABRACE.PESSOA_FISICA.dataNascimento FROM ABRACE.PESSOA_FISICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_FISICA.idPessoa = ABRACE.PESSOA.idPessoa AND ativo = ?";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, true);
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
				String cpf = rs.getString(9);
				String rg = rs.getString(10);
				LocalDate dataNasc = rs.getDate(11).toLocalDate();

				listaPessoasFisicas.add(new PessoaFisica(id, nome, endereco, dataCadastro, telefone1, telefone2, email, true, isDoador, cpf, rg, dataNasc));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		}

		return listaPessoasFisicas;
	}
	
	public ArrayList<PessoaFisica> listarDoadoresFisicos() throws SQLException {
		ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<PessoaFisica>();

		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro, ABRACE.PESSOA.isDoador,";

		String sql = "SELECT " + informacaoPessoa
				+ "ABRACE.PESSOA_FISICA.cpf, ABRACE.PESSOA_FISICA.rg, ABRACE.PESSOA_FISICA.dataNascimento FROM ABRACE.PESSOA_FISICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_FISICA.idPessoa = ABRACE.PESSOA.idPessoa AND ativo = ? AND isDoador = ?";
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
				String cpf = rs.getString(9);
				String rg = rs.getString(10);
				LocalDate dataNasc = rs.getDate(11).toLocalDate();

				listaPessoasFisicas.add(new PessoaFisica(id, nome, endereco, dataCadastro, telefone1, telefone2, email, true, isDoador, cpf, rg, dataNasc));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		}

		return listaPessoasFisicas;
	}

	public PessoaFisica getPessoaFisica(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro, ABRACE.PESSOA.ativo,";
		String sql = "SELECT " + informacaoPessoa + " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento"
				+ " FROM ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
				+ " WHERE ABRACE.Pessoa.idPessoa = ? AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa ";
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
				String cpf = rs.getString(9);
				String rg = rs.getString(10);
				LocalDate dataNasc = rs.getDate(11).toLocalDate();
				return new PessoaFisica(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, isDoador, cpf, rg, dataNasc);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}