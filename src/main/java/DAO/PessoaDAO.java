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

public class PessoaDAO extends ExecutaSQL{
	
	public PessoaDAO(Connection connection) {
		super(connection);
	}
	
	public int cadastrarPessoa(Pessoa pessoa) throws SQLException, PessoaInvalidaException  {
		PreparedStatement stmt = null;
        String sql = "insert into ABRACE.PESSOA (ativo, datacadastro, email, telefone2, telefone1, endereco, nome) values (?, ?, ?, ?, ?, ?, ?)";
        int id = 0;
        try {
            // prepared statement for insertion
            getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // set values
            pessoa.setDataCadastro(LocalDate.now());
            stmt.setBoolean(1, pessoa.isAtivo());
            stmt.setDate(2, Date.valueOf(pessoa.getDataCadastro()));
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getTelefone2());
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getEndereco());
            stmt.setString(7, pessoa.getNome());
            // execute
            stmt.execute();
            getConexao().commit();
            // get DB id
	        ResultSet rs = stmt.getGeneratedKeys();
	        if(rs.next()){
	            id = rs.getInt(1);
	        }
        } catch (SQLException e) {
        	rollBack(e);
        } finally {
            verificaConexao(stmt);
        }
        return id;
			
	}
	
	public ArrayList<Pessoa> listarPessoas(Boolean situacao) throws PessoaInvalidaException{
		ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM ABRACE.Pessoa where ativo = ?";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, situacao);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone1 = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				listaPessoas.add(new Pessoa(id, nome, endereco,  dataCadastro, telefone1, telefone2, email, situacao));
			}
			stmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return listaPessoas;
	}

	@SuppressWarnings("finally")
	public boolean editarPessoa(Pessoa pessoa) throws SQLException {
		String sql= "UPDATE ABRACE.PESSOA SET nome=?, endereco=?, telefone1=?, telefone2=?, email=?, dataCadastro=?, ativo=? WHERE idPessoa="+pessoa.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		try {

			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEndereco());
			stmt.setString(3, pessoa.getTelefone());
			stmt.setString(4, pessoa.getTelefone2());
			stmt.setString(5, pessoa.getEmail());
			stmt.setDate(6, Date.valueOf(pessoa.getDataCadastro()));
			stmt.setBoolean(7, pessoa.isAtivo());

			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexao(stmt);
			return false;
		}
	}
	public void excluirPessoa(Pessoa pessoa) throws SQLException{
		String sql= "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa="+pessoa.getId();
	    PreparedStatement stmt = getConexao().prepareStatement(sql);
	    
	    stmt.setBoolean(1, pessoa.isAtivo());
	    	
	    stmt.execute();
	    stmt.close();
	}
	public static void main(String[] args) throws PessoaInvalidaException {
		ArrayList<Pessoa> pessoas = new PessoaDAO(new ConnectionFactory().getConnection()).listarPessoas(true);
		for (int i=0;i<pessoas.size();i++) {
			System.out.println(pessoas.get(i).getId());
		}
	}
	   
}