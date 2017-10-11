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
import model.Assistido;
import model.Pessoa;
import model.PessoaFisica;

public class PessoaFisicaDAO extends ExecutaSQL{

	public PessoaFisicaDAO(Connection connection) {
		super(connection);
	}
	
	@SuppressWarnings("deprecation")
	public void cadastrarPessoaFisica(Assistido assistido) throws PessoaInvalidaException, SQLException {
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.PESSOA_FISICA" + "(datanascimento, rg, cpf, idPessoa) values (?, ?, ?, ?)";
		try {
			// prepared statement para inserção
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql);
			// seta os valores
			stmt.setDate(1, new Date(assistido.getDataNasc().getYear(), assistido.getDataNasc().getMonthValue(),
					assistido.getDataNasc().getDayOfMonth()));
			stmt.setString(2, assistido.getRg());
			stmt.setString(3, assistido.getCpf());
			stmt.setInt(4, assistido.getId());
			// executa
			stmt.execute();
			getConexao().commit();
		} catch (SQLException e) {
			if (getConexao() != null) {
				System.out.println("Transação está retornando ao estado anterior");
				getConexao().rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			getConexao().setAutoCommit(true);
		}
	}
	
	public void adicionarDoadorFisico(){
		
	}
	
	public void buscaDoadorFisico() {
		
	}
	
}