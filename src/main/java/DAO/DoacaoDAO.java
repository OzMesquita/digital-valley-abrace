package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;

import exceptions.DoacaoInvalidaException;
import model.Doacao;
import model.Pessoa;

public class DoacaoDAO extends ExecutaSQL{
	
	public DoacaoDAO(Connection connection) {
		super(connection);
	}
	
	public boolean cadastrarDoacao(Doacao doacao) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.DOACAO (idDoacao, valor, data, ativo) values (?, ?, ?, ?)";
		try {
			stmt = getConexao().prepareStatement(sql);
			
			stmt.setInt(1, doacao.getId());
			stmt.setDouble(2,  doacao.getValor());
			stmt.setDate(3, Date.valueOf(doacao.getData()));
			stmt.setBoolean(4, doacao.isAtivo());
			
			stmt.execute();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}
	
	public boolean editarDoacao(Doacao doacao) {
		String sql = "UPDATE ABRACE.DOACAO SET valor=?, data=?, ativo=? WHERE idDoacao=?";
		PreparedStatement stmt = null;
		
		try {
			stmt = getConexao().prepareStatement(sql);
			
			stmt.setDouble(1, doacao.getValor());
			stmt.setDate(1, Date.valueOf(doacao.getData()));
			
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
	
	public ArrayList<Doacao> listarDoacoes(boolean ativo) throws DoacaoInvalidaException{
		ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
		String sql = "SELECT * FROM ABRACE.DOACAO WHERE ativo=?";
		PreparedStatement stmt = null;
		try {
			stmt = getConexao().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				Double valor = rs.getDouble(2);
				LocalDate data = rs.getDate(3).toLocalDate();
				Pessoa doador = (Pessoa) rs.getObject(4);
				
				doacoes.add(new Doacao(id, valor, data, ativo, doador));
			}
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return doacoes;
	}
}
