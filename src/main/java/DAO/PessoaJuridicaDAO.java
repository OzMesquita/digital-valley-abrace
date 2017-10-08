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
	public void inserirDoadorJuridico(PessoaJuridica pessoaJ) {
		
		try {
			String sql1 = "insert into ABRACE.Pessoa (nome, endereco, telefone1, telefone2, dataCadastro, email, ativo)" + "values(?, ?, ?, ?, ?, ?, ?)";
			String sql2 = "insert into ABRACE.Pessoa_Juridica (cnpj, fantasia)" + "values(?, ?)"; 
			
			PreparedStatement stmt = getConexao().prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, pessoaJ.getNome());
			stmt.setString(2, pessoaJ.getEndereco());
			stmt.setString(3, pessoaJ.getTelefone());
			stmt.setString(4, pessoaJ.getTelefone2());
			stmt.setDate(5, new Date(pessoaJ.getDataCadastro().getYear(), pessoaJ.getDataCadastro().getMonthValue(), pessoaJ.getDataCadastro().getDayOfMonth()));//ajeitar
			stmt.setString(6, pessoaJ.getEmail());
			stmt.setBoolean(7, pessoaJ.isAtivo());
			
			stmt.execute();
			
			 // Recupera a id
	        ResultSet rs = stmt.getGeneratedKeys();
	        int id = 0;
	        if(rs.next()){
	            id = rs.getInt(1);
	        }
	        stmt = getConexao().prepareStatement(sql2);
	        
	        stmt.setString(1, pessoaJ.getCnpj());
	        stmt.setString(2, pessoaJ.getNomeFantasia());
	        stmt.setInt(3, id);
	        
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao inserir um Doador Juridico", e);
		}
		
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