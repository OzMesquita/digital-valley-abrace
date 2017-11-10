package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaInvalidaException;
import model.Pessoa;

public class PessoaDAO extends ExecutaSQL {

	public PessoaDAO(Connection connection) {
		super(connection);
	}

	public void cadastrarPessoa(Pessoa pessoa) throws SQLException, PessoaInvalidaException {
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.PESSOA (ativo, datacadastro, email, telefone2, telefone1, endereco, nome,isDoador) values (?, ?, ?, ?, ?, ?, ?, ?)";
		stmt = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		// se você chegou aqui após um erro ocorrer, é porque seu banco não foi atualizado com o novo atributo encontrado (isDoador). Apague o localhost e reexecute o script de instalacao
		pessoa.setDataCadastro(LocalDate.now());
		stmt.setBoolean(1, true);
		stmt.setDate(2, Date.valueOf(pessoa.getDataCadastro()));
		stmt.setString(3, pessoa.getEmail());
		stmt.setString(4, pessoa.getTelefone2());
		stmt.setString(5, pessoa.getTelefone());
		stmt.setString(6, pessoa.getEndereco());
		stmt.setString(7, pessoa.getNome());
		stmt.setBoolean(8, pessoa.isDoador());
		// execute
		stmt.execute();
		// get DB id
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			pessoa.setId(rs.getInt(1));
		}

	}

	public ArrayList<Pessoa> listarPessoas(Boolean situacao) throws PessoaInvalidaException {
		ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM ABRACE.Pessoa where ativo = ?";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, situacao);
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
				// se você chegou aqui após um erro ocorrer, é porque seu banco não foi atualizado com o novo atributo encontrado (isDoador). Apague o localhost e reexecute o script de instalacao
				listaPessoas.add(new Pessoa(id, nome, endereco, telefone1, telefone2, dataCadastro, email, true, isDoador));
			}
			stmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return listaPessoas;
	}

	public boolean editarPessoa(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA SET nome=?, endereco=?, telefone1=?, telefone2=?, email=?, ativo=? WHERE idPessoa=?";
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		stmt.setString(1, pessoa.getNome());
		stmt.setString(2, pessoa.getEndereco());
		stmt.setString(3, pessoa.getTelefone());
		stmt.setString(4, pessoa.getTelefone2());
		stmt.setString(5, pessoa.getEmail());
		stmt.setBoolean(6, pessoa.isAtivo());
		stmt.setInt(7, pessoa.getId());

		stmt.executeUpdate();

		return true;

	}

	public void excluirPessoa(Pessoa pessoa) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoa.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		stmt.setBoolean(1, pessoa.isAtivo());

		stmt.execute();
		stmt.close();
	}

	public String getCPFCNPJ(int id) {
		String sql = "SELECT ABRACE.Pessoa_Fisica.cpf FROM ABRACE.Pessoa_Fisica where ABRACE.Pessoa_Fisica.idPessoa=?";
		try {
			PreparedStatement ps = getConexao().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			String resultado = null;
			if (rs.next()) {
				resultado = rs.getString(1);
			}
			else {
				sql = "SELECT ABRACE.Pessoa_Juridica.cnpj FROM ABRACE.Pessoa_Juridica where ABRACE.Pessoa_Juridica.idPessoa=?";
				ps = getConexao().prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if(rs.next()) {
					resultado = rs.getString(1);
				}
				
			}
			return resultado;
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	public Pessoa getPessoa(int id) {

		String sql = "SELECT ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1, ABRACE.Pessoa.telefone2,"
				+ " ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro, ABRACE.Pessoa.isDoador FROM ABRACE.Pessoa WHERE ABRACE.Pessoa.idPessoa = ?";
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
				return new Pessoa(id, nome, endereco, telefone, telefone2, dataCadastro, email, ativo, isDoador);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		}
		return null;
	}

}