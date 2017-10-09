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

public class PessoaDAO extends ExecutaSQL{
	
	public PessoaDAO(Connection connection) {
		super(connection);
	}
	//Incompleto
	@SuppressWarnings("deprecation")
	public void cadastrarPessoa(Pessoa pessoa) {
		String sql = "insert into ABRACE.Pessoa"
					+"(idPessoa, nome, endereco, telefone1, telefone2, dataCadastro, email, ativo)"
					+"(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			
			stmt.setInt(1, pessoa.getId());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEndereco());
			stmt.setString(4, pessoa.getTelefone());
			stmt.setString(5, pessoa.getTelefone2());
			stmt.setDate(6, new Date(pessoa.getDataCadastro().getYear(), pessoa.getDataCadastro().getMonthValue(), pessoa.getDataCadastro().getDayOfMonth()));
			stmt.setString(7, pessoa.getEmail());
			stmt.setBoolean(8, pessoa.isAtivo());

			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
			
	}
	
	public ArrayList<Pessoa> listaPessoasAtivas() throws PessoaInvalidaException{
		ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		String sql = "SELECT (idPessoa, nome, endereco, telefone1, telefone2, email, dataCadastro, ativo) FROM ABRACE.Pessoa where ativo = True";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			Pessoa p;
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone1 = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				listaPessoas.add(new Pessoa(id, nome, endereco,  dataCadastro, telefone1, telefone2, email, true));
			}
			stmt.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return listaPessoas;
	}
}