package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;
import model.Pessoa;
import model.PessoaFisica;
public class AssistidoDAO extends ExecutaSQL{
    
    public AssistidoDAO(Connection connection) {
		super(connection);
    }
    public boolean inserirAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
        assistido.setId(cadastrarPessoa(assistido));
        cadastrarPessoaFisica(assistido);
        return cadastrarAssistido(assistido);
    }
    @SuppressWarnings("deprecation")
    public int cadastrarPessoa(Assistido assistido) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into ABRACE.PESSOA" + "(ativo, datacadastro, email, telefone2, telefone1, endereco, nome)"
                + "(?, ?, ?, ?, ?, ?, ?)";
        try {
            // prepared statement para inserção
            getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql);
            // seta os valores
            stmt.setBoolean(1, assistido.isAtivo());
            stmt.setDate(2, new Date(assistido.getDataCadastro().getYear(), assistido.getDataCadastro().getMonthValue(),
                    assistido.getDataCadastro().getDayOfMonth()));
            stmt.setString(3, assistido.getEmail());
            stmt.setString(4, assistido.getTelefone2());
            stmt.setString(5, assistido.getTelefone());
            stmt.setString(6, assistido.getEndereco());
            stmt.setString(7, assistido.getNome());
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
        String sql2 = "select idpessoa from pessoa where nome=" + assistido.getNome();
        int idpessoa;
        try {
            // prepared statement para inserção
        	getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql2);
            // seta os valores
            idpessoa = stmt.executeQuery().getInt(0);
            // executa
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
        return idpessoa;
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
    public boolean cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into ABRACE.ASSISTIDO" + "(idpessoa, status, apelido, tipodecancer)" + "(?, ?, ?, ?";
        try {
            // prepared statement para inserção
        	getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql);
            // seta os valores
            stmt.setInt(1, assistido.getId());
            stmt.setBoolean(2, assistido.getSituacao());
            stmt.setString(3, assistido.getApelido());
            stmt.setString(4, assistido.getTipoDeCancer());
            // executa
            if (stmt.execute()) {
                return true;
            }
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
        return false;
    }
    
    public ArrayList<Assistido> listaAssistido() throws PessoaInvalidaException, PessoaFisicaException, AssistidoInvalidoException{
    	ArrayList<Assistido> listaAssistidos = new ArrayList<Assistido>();
    	ArrayList<PessoaFisica> listaPF = new PessoaFisicaDAO(getConexao()).listaDoadorFisico();
    	String sql = "SELECT (tipoCancer, apelido, status) FROM ABRACE.Assistido WHERE idPessoa = ?";
    	try {
    		for(PessoaFisica p : listaPF) {
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
    				String cpf = p.getCpf();
    				String rg = p.getRg();
    				LocalDate dataNasc = p.getDataNasc();
    				String tipoDeCancer = rs.getString(1);
    				String apelido = rs.getString(2);
    				boolean situacao = rs.getBoolean(3);
    				listaAssistidos.add(new Assistido(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, cpf, rg, dataNasc, apelido, tipoDeCancer, situacao));
    			}
    			
    		}
    	}catch(SQLException e){
    		throw new RuntimeException(e.getMessage());
    	}
    	return listaAssistidos;
    }
}