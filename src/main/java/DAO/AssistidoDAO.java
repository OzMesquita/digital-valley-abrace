package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ExecutaSQL;
import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;

public class AssistidoDAO extends ExecutaSQL {

	public AssistidoDAO(Connection connection) {
		super(connection);
	}

	public boolean inserirAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
		return cadastrarAssistido(assistido);
	}

	public boolean cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into ABRACE.ASSISTIDO" + "(idpessoa, status, apelido, tipocancer) values (?, ?, ?, ?)";
        try {
            // prepared statement para inser��o
            getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql);
            
            int id = new PessoaFisicaDAO(getConexao()).cadastrarPessoaFisica(assistido);
            // seta os valores
            stmt.setInt(1, id);
            stmt.setBoolean(2, assistido.getSituacao());
            stmt.setString(3, assistido.getApelido());
            stmt.setString(4, assistido.getTipoDeCancer());
            // executa
            stmt.execute();
            getConexao().commit();
            return true;
        } catch (SQLException e) {
            if (getConexao() != null) {
                getConexao().rollback();
                throw new SQLException(e.getMessage() + "Transa��o est� retornando ao estado anterior");
            }
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            getConexao().setAutoCommit(true);
            
        }
    }
	
	public boolean editarAssistido(Assistido assistido) {
		String sql3 = "UPDATE ABRACE.ASSISTIDO SET tipoCancer=?, apelido=?, status=? WHERE idPessoa=" + assistido.getId();
		PreparedStatement stmt = null;
		try {
			stmt = getConexao().prepareStatement(sql3);
			stmt.setString(1, assistido.getTipoDeCancer());
			stmt.setString(2, assistido.getApelido());
			stmt.setBoolean(3, assistido.getSituacao());
			stmt.setInt(4, assistido.getId());
			
			stmt.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			try {
				rollBack(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}finally {
			try {
				verificaConexao(stmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	

	public void excluirAssistido(Assistido assistido) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa="+assistido.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		stmt.executeUpdate();
		
	}
	
	public Assistido getAssistido(int id) {
		String informacaoPessoa = "ABRACE.Pessoa.ativo, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
    			                + "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
    	String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
    	String sql = "SELECT "+informacaoPessoa+informacaoPessoaFisica+" ABRACE.Assistido.tipoCancer, ABRACE.Assistido.apelido, ABRACE.Assistido.status"
    			   +" FROM ABRACE.Assistido, ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
    			   +" WHERE ABRACE.Pessoa.idPessoa = ? AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa "
    			   +" AND ABRACE.Pessoa_Fisica.idPessoa = ABRACE.Assistido.idPessoa";
    	try {
    		PreparedStatement ps = getConexao().prepareStatement(sql);
    		ps.setInt(1, id);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			boolean ativo = rs.getBoolean(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				String cpf = rs.getString(8);
				String rg = rs.getString(9);
				LocalDate dataNasc = rs.getDate(10).toLocalDate();
				String tipoDeCancer = rs.getString(11);
				String apelido = rs.getString(12);
				boolean situacao = rs.getBoolean(13);
				return new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, cpf, rg, dataNasc, apelido, tipoDeCancer, situacao);
    		}
    	}catch(SQLException e){
    		throw new RuntimeException(e.getMessage());
    	} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} catch (AssistidoInvalidoException e) {
			e.printStackTrace();
		}
    	return null;
    }
	
	public ArrayList<Assistido> listaAssistido() {
    	ArrayList<Assistido> assistidos = new ArrayList<Assistido>();
    	String informacaoPessoa = "ABRACE.Pessoa.idPessoa, ABRACE.Pessoa.nome, ABRACE.Pessoa.endereco, ABRACE.Pessoa.telefone1,"
    			                + "ABRACE.Pessoa.telefone2, ABRACE.Pessoa.email, ABRACE.Pessoa.dataCadastro,";
    	String informacaoPessoaFisica = " ABRACE.Pessoa_Fisica.cpf, ABRACE.Pessoa_Fisica.rg, ABRACE.Pessoa_Fisica.dataNascimento,";
    	//Vou pegar essas duas Strings que s�o necess�rias para pegar os assistidos e usar no SELECT do sql para pegar os dados de todas essas tabelas.
    	String sql = "SELECT "+informacaoPessoa+informacaoPessoaFisica+" ABRACE.Assistido.tipoCancer, ABRACE.Assistido.apelido, ABRACE.Assistido.status"
    			   +" FROM ABRACE.Assistido, ABRACE.Pessoa, ABRACE.Pessoa_Fisica"
    			   +" WHERE ABRACE.Pessoa.ativo = True AND ABRACE.Pessoa.idPessoa = ABRACE.Pessoa_Fisica.idPessoa "
    			   +" AND ABRACE.Pessoa_Fisica.idPessoa = ABRACE.Assistido.idPessoa";
    	try {
    		PreparedStatement ps = getConexao().prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				String cpf = rs.getString(8);
				String rg = rs.getString(9);
				LocalDate dataNasc = rs.getDate(10).toLocalDate();
				String tipoDeCancer = rs.getString(11);
				String apelido = rs.getString(12);
				boolean situacao = rs.getBoolean(13);
				assistidos.add(new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, true, cpf, rg, dataNasc, apelido, tipoDeCancer, situacao));
    		}
    	}catch(SQLException e){
    		throw new RuntimeException(e.getMessage());
    	} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		} catch (AssistidoInvalidoException e) {
			e.printStackTrace();
		}finally {
			return assistidos;
		}
    }
	
	
}