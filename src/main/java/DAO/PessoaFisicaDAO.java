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

	public void cadastrarPessoaFisica(PessoaFisica pessoaFisica) throws SQLException, PessoaInvalidaException  {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.PESSOA_FISICA (dataNascimento, rg, cpf, idPessoa) VALUES (?, ?, ?, ?)";
			stmt = getConexao().prepareStatement(sql);

			stmt.setDate(1, Date.valueOf(pessoaFisica.getDataNasc()));
			stmt.setString(2, pessoaFisica.getRg());
			stmt.setString(3, pessoaFisica.getCpf());
			stmt.setInt(4, pessoaFisica.getId());

			stmt.execute();
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


	public void excluirPessoaFisica(PessoaFisica pessoaFisica) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoaFisica.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		stmt.execute();
		stmt.close();
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
}