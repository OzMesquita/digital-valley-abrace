package DAO;

import java.sql.Statement;
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

	public boolean cadastrarDoacao(Doacao doacao) throws SQLException {
		Statement s = getConexao().createStatement();
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY('derby.language.sequence.preallocator', '1')");
		PreparedStatement stmt = null;
		String sql = "INSERT INTO ABRACE.Doacao (idPessoa, valor, data, ativo)" + "VALUES (?, ?, ?, ?)";
		try {
			stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, doacao.getDoador().getId());
			stmt.setDouble(2,  doacao.getValor());
			stmt.setDate(3, Date.valueOf(doacao.getData()));
			stmt.setBoolean(4, true);
			stmt.execute();
		} catch (SQLException e) {
			rollBack(e);
			return false;
		}
		return true;
	}

	public boolean editarDoacao(Doacao doacao) {
		String sql = "UPDATE ABRACE.Doacao SET valor=?, data=?, ativo=?, idPessoa=? WHERE idDoacao=?";
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
			String sql = "UPDATE ABRACE.Doacao SET ativo=false WHERE idDoacao=?";
			PreparedStatement stmt;
			stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, doacao.getId());
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
				Pessoa doador;
				doador = new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).getPessoaJuridica(idDoador);
				if(doador == null){
					doador = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(idDoador);
				}
				doacoes.add(new Doacao(id, valor, data,true , doador));
			}
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return doacoes;
	}

	public Doacao getDoacao(int idDoacao) throws DoacaoInvalidaException {
		String sql = "SELECT IDPESSOA, VALOR, DATA FROM ABRACE.DOACAO WHERE IDDOACAO = ?";
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, idDoacao);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				double valor = rs.getDouble("valor");
				LocalDate data = rs.getDate("data").toLocalDate();
				Pessoa doador = new PessoaFisicaDAO(getConexao()).getPessoaFisica(rs.getInt("idpessoa"));
				if(doador == null) {
					doador = new PessoaJuridicaDAO(getConexao()).getPessoaJuridica(rs.getInt("idpessoa"));
					if(doador == null) {
						return null;
					}
				}
				return new Doacao(idDoacao, valor, data, true, doador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Doacao> listarDoacoes(Pessoa pessoa) throws DoacaoInvalidaException{
		ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
		String sql = "SELECT * FROM ABRACE.DOACAO WHERE IDPESSOA=?";
		PreparedStatement stmt = null;
		try {
			stmt = getConexao().prepareStatement(sql);
			stmt.setInt(1, pessoa.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("IDDOACAO");
				Double valor = rs.getDouble("VALOR");
				LocalDate data = rs.getDate("DATA").toLocalDate();
				doacoes.add(new Doacao(id, valor, data,true , pessoa));
			}
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return doacoes;
	}
	
	public static void main(String[] args) throws DoacaoInvalidaException {
		for(Doacao d : new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(1))) {
			System.out.println(d.getDoador().getNome()+" "+d.getId()+" "+d.getValor()+" "+d.getData());
		}
		
	}
	
	public ArrayList<Doacao> listarDoacoes(Pessoa pessoa,LocalDate mes,LocalDate ano) throws DoacaoInvalidaException{
        ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
        String sql = "SELECT * FROM ABRACE.DOACAO WHERE IDPESSOA =? and DATA>=? and DATA<=? and ativo=?";
        PreparedStatement stmt = null;
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setInt(1, pessoa.getId());
            if(mes!=null) {
            	stmt.setDate(2, Date.valueOf(mes.withDayOfMonth(1)));
            	stmt.setDate(3, Date.valueOf(mes.withDayOfMonth(mes.lengthOfMonth())));
            }else {
            	stmt.setDate(2, Date.valueOf(ano.withDayOfYear(1)));
            	stmt.setDate(3, Date.valueOf(ano.withDayOfYear(ano.lengthOfYear())));
            }
            
            stmt.setBoolean(4, true);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("IDDOACAO");
                Double valor = rs.getDouble("VALOR");
                LocalDate datadoacao = rs.getDate("DATA").toLocalDate();
                doacoes.add(new Doacao(id, valor, datadoacao,true , pessoa));
            }
            stmt.close();
        }catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return doacoes;
    }

}
