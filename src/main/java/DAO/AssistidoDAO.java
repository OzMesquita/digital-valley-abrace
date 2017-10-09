package DAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import exceptions.PessoaInvalidaException;
import model.Assistido;
public class AssistidoDAO {
    private Connection con;
    public Connection getCon() {
        return con;
    }
    public void setCon(Connection con) {
        this.con = con;
    }
    public AssistidoDAO() throws ClassNotFoundException {
        this.setCon(new ConnectionFactory().getConnection());
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
            con.setAutoCommit(false);
            stmt = getCon().prepareStatement(sql);
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
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                System.out.println("Transação está retornando ao estado anterior");
                con.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }
        String sql2 = "select idpessoa from pessoa where nome=" + assistido.getNome();
        int idpessoa;
        try {
            // prepared statement para inserção
            con.setAutoCommit(false);
            stmt = getCon().prepareStatement(sql2);
            // seta os valores
            idpessoa = stmt.executeQuery().getInt(0);
            // executa
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                System.out.println("Transação está retornando ao estado anterior");
                con.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }
        return idpessoa;
    }
    @SuppressWarnings("deprecation")
    public void cadastrarPessoaFisica(Assistido assistido) throws PessoaInvalidaException, SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into ABRACE.PESSOA_FISICA" + "(datanascimento, rg, cpf, idPessoa)" + "(?, ?, ?, ?)";
        try {
            // prepared statement para inserção
            con.setAutoCommit(false);
            stmt = getCon().prepareStatement(sql);
            // seta os valores
            stmt.setDate(1, new Date(assistido.getDataNasc().getYear(), assistido.getDataNasc().getMonthValue(),
                    assistido.getDataNasc().getDayOfMonth()));
            stmt.setString(2, assistido.getRg());
            stmt.setString(3, assistido.getCpf());
            stmt.setInt(4, assistido.getId());
            // executa
            stmt.execute();
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                System.out.println("Transação está retornando ao estado anterior");
                con.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }
    }
    public boolean cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException {
        PreparedStatement stmt = null;
        String sql = "insert into ABRACE.ASSISTIDO" + "(idpessoa, status, apelido, tipodecancer)" + "(?, ?, ?, ?";
        try {
            // prepared statement para inserção
            con.setAutoCommit(false);
            stmt = getCon().prepareStatement(sql);
            // seta os valores
            stmt.setInt(1, assistido.getId());
            stmt.setBoolean(2, assistido.getSituacao());
            stmt.setString(3, assistido.getApelido());
            stmt.setString(4, assistido.getTipoDeCancer());
            // executa
            if (stmt.execute()) {
                return true;
            }
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                System.out.println("Transação está retornando ao estado anterior");
                con.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            con.setAutoCommit(true);
        }
        return false;
    }
}