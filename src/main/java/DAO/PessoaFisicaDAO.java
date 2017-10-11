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
		String sql = "insert into ABRACE.PESSOA_FISICA" + "(datanascimento, rg, cpf, idPessoa)" + "(?, ?, ?, ?)";
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
	
	public ArrayList<PessoaFisica> listaDoadorFisico() throws PessoaInvalidaException, PessoaFisicaException {
		ArrayList<Pessoa> pessoasAtivas = new PessoaDAO(getConexao()).listaPessoasAtivas();
		ArrayList<PessoaFisica> listaPessoasFisicas = new ArrayList<PessoaFisica>();
		String sql = "SELECT (cpf, rg, dataNascimento) FROM ABRACE.Pessoa_Fisica WHERE idPessoa = ?";
    	try {
    		for(Pessoa p : pessoasAtivas) {
    			PreparedStatement ps = getConexao().prepareStatement(sql);
    			ps.setInt(1, p.getId());
    			ResultSet rs = ps.executeQuery();
    			if(rs.next()) {
    				int id = p.getId();
    				String nome = p.getNome();
    				String endereco = p.getEndereco();
    				LocalDate dataCadastro = p.getDataCadastro();
    				String telefone = p.getTelefone();
    				String telefone2 = p.getTelefone2();
    				String email = p.getEmail();
    				boolean ativo = p.isAtivo();
    				String cpf = rs.getString(1);
    				String rg = rs.getString(2);
    				LocalDate dataNasc = rs.getDate(3).toLocalDate();
    				listaPessoasFisicas.add(new PessoaFisica(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, cpf, rg, dataNasc));
    			}
    		}
    	}catch (SQLException ex) {
    			throw new RuntimeException(ex.getMessage());
    		}
    	return listaPessoasFisicas;
	}
	
	public void excluiDoadorFisico() {
		
	}
	
}