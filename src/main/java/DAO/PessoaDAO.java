package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}