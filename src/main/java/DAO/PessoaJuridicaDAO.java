package DAO;

import java.sql.Connection;



import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PessoaJuridica;

public class PessoaJuridicaDAO extends ExecutaSQL{
	
	public PessoaJuridicaDAO(Connection connection) {
		super(connection);
	}
	@SuppressWarnings("deprecation")
	//INSERÇÃO DE DOADOR JURIDICO
	public boolean inserirDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException{
		PreparedStatement stmt = null;
		String sql = "insert into ABRACE.Pessoa_Juridica (cnpj, fantasia)" + "values(?, ?)"; 
		
		try {
			getConexao().setAutoCommit(false);
			stmt = getConexao().prepareStatement(sql);
			
			stmt.setString(1, pessoaJ.getCnpj());
			stmt.setString(2, pessoaJ.getNomeFantasia());
			
			if(stmt.execute()) {
				return true;
			}
				getConexao().commit();
			}catch (SQLException e) {
				if (getConexao() != null) {
					getConexao().rollback();
					throw new SQLException(e.getMessage()+ "Transação está retornando ao estado anterior");
				}
				throw new RuntimeException(e);
			}finally {
				if (stmt != null) {
					stmt.close();
				}
				getConexao().setAutoCommit(true);
			}
			return false;
	}
	//BUSCA PELO CNPJ
	public PessoaJuridica buscarDoadorJuridico(String cnpj) {
		
		try {
			String sql = "SELECT * FROM ABRACE.Pessoa_Juridica AS pj, ABRACE.Pessoa AS p WHERE pj.idPessoa = p.idPessoa";

			
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, cnpj);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				PessoaJuridica pessoaj = new PessoaJuridica();
				
				stmt.setString(1, pessoaj.getCnpj());
				stmt.setString(2, pessoaj.getNome());
				stmt.setString(3, pessoaj.getNomeFantasia());
				stmt.setString(4, pessoaj.getEndereco());
				stmt.setString(5, pessoaj.getEmail());
				stmt.setString(6, pessoaj.getTelefone());
				stmt.setString(7, pessoaj.getTelefone2());
				stmt.setBoolean(8, pessoaj.isAtivo());
				
				rs.close();
				stmt.close();
				
				return pessoaj;
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao buscar cnpj de um Doador juridico", e);
		}
	}
	//BUSCA PELO NOME
	//Imcompleto
	public List<PessoaJuridica> buscarPeloNomeDoadorJuridico(String nome) {

		List<PessoaJuridica> doadoresJuridicos = new ArrayList<PessoaJuridica>();

		try {
			String sql = "SELECT * FROM ABRACE.Pessoa_Juridica"; 
			
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, '%'+nome+'%');
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				PessoaJuridica pessoaj = new PessoaJuridica();
				
				
				doadoresJuridicos.add(pessoaj);
			}
			rs.close();
			stmt.close();
			return doadoresJuridicos;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao listar doadores juridicos pelo nome", e);
		}
	}
	
	public void listarDoadorJuridico() {
		
	}
	
	public void excluirDoadorJuridico() {
		
	}
}