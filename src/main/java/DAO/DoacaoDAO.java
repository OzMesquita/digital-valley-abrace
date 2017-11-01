package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptions.DoacaoInvalidaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.Doacao;
import model.Pessoa;

public class DoacaoDAO extends ExecutaSQL{
	
	public DoacaoDAO(Connection connection) {
		super(connection);
	}
	
	public boolean cadastrarDoacao(Doacao doacao) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.DOACAO (idPessoa, valor, data, ativo) values (?, ?, ?, true)";
		try {
			stmt = getConexao().prepareStatement(sql);
			
			stmt.setInt(1, doacao.getDoador().getId());
			stmt.setDouble(2,  doacao.getValor());
			stmt.setDate(3, Date.valueOf(doacao.getData()));
			stmt.execute();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}
	
	public boolean editarDoacao(Doacao doacao) {
		String sql = "UPDATE ABRACE.DOACAO SET valor=?, data=?, ativo=?, idPessoa=? WHERE idDoacao=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = getConexao().prepareStatement(sql);
			
			stmt.setDouble(1, doacao.getValor());
			stmt.setDate(2, Date.valueOf(doacao.getData()));
			stmt.setBoolean(3, doacao.isAtivo());
			stmt.setInt(4, doacao.getDoador().getId());
			stmt.setInt(5, doacao.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}
	
	public boolean excluirDoacao(Doacao doacao) {
		try{
			String sql = "UPDATE ABRACE.DOACAO SET ativo=false WHERE idDoacao="+doacao.getId();
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Doacao> listarDoacoes() throws DoacaoInvalidaException{
		ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
		String sql = "SELECT ABRACE.DOACAO.idDoacao, ABRACE.DOACAO.valor, ABRACE.DOACAO.data, ABRACE.DOACAO.idPessoa FROM ABRACE.DOACAO WHERE ativo=?";
		PreparedStatement stmt = null;
		try {
			stmt = getConexao().prepareStatement(sql);
			stmt.setBoolean(1, true);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Double valor = rs.getDouble(2);
				LocalDate data = rs.getDate(3).toLocalDate();
				int idDoador = rs.getInt(4);
				
				Pessoa doador = new PessoaDAO(new ConnectionFactory().getConnection()).getPessoa(idDoador);
				
				doacoes.add(new Doacao(id, valor, data,true , doador));
			}
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return doacoes;
	}
	
	public Doacao getDoacao(int idDoacao) {
		String sql = "SELECT ABRACE.DOACAO.valor, ABRACE.DOACAO.data, ABRACE.DOACAO.idPessoa WHERE idDoacao = "+idDoacao;
		return new Doacao();
	}
	
}
