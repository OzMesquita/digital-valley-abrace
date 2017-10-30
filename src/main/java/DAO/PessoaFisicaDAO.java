package DAO;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;
import model.PessoaFisica;
import model.PessoaJuridica;

public class PessoaFisicaDAO extends ExecutaSQL {

	public PessoaFisicaDAO(Connection connection) {
		super(connection);
	}
	
	public boolean inserirPessoaFisica(PessoaFisica pessoaFisica) {
		try {
			getConexao().setAutoCommit(false);
			PessoaDAO pessoa = new PessoaDAO(getConexao());
			pessoa.cadastrarPessoa(pessoaFisica);
			cadastrarPessoaFisica(pessoaFisica);
			getConexao().commit();
		}catch(SQLException e) {
			rollBack(e);
			return false;
		}catch(PessoaInvalidaException e1) {
			System.out.println(e1.getMessage());
			return false;
		}
		return true;
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

	private void editar(PessoaFisica pessoaFisica) throws SQLException {
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
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro,";

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
				String cpf = rs.getString(8);
				String rg = rs.getString(9);
				LocalDate dataNasc = rs.getDate(10).toLocalDate();

				listaPessoasFisicas.add(new PessoaFisica(id, nome, endereco, dataCadastro, telefone1, telefone2, email, true, cpf, rg, dataNasc));
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
				+ "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
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
				String cpf = rs.getString(8);
				String rg = rs.getString(9);
				LocalDate dataNasc = rs.getDate(10).toLocalDate();
				return new PessoaFisica(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, cpf, rg, dataNasc);
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