package DAO;

import java.sql.Connection;




import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAO extends ExecutaSQL{
	
	public PessoaJuridicaDAO(Connection connection) {
		super(connection);
	}
	
	public void cadastrarDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException, PessoaInvalidaException  {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO ABRACE.PESSOA_JURIDICA (cnpj, fantasia, razaoSocial, idPessoa)" + "VALUES(?, ?, ?, ?)";

        try {
            getConexao().setAutoCommit(false);
            stmt = getConexao().prepareStatement(sql);

            new PessoaDAO(getConexao()).cadastrarPessoa(pessoaJ);

            stmt.setString(1, pessoaJ.getCnpj());
            stmt.setString(2, pessoaJ.getNomeFantasia());
            stmt.setString(3, pessoaJ.getRazaoSocial());
            stmt.setInt(4, pessoaJ.getId());

            stmt.execute();
            getConexao().commit();

        } catch (SQLException e) {
            rollBack(e);
        } finally {
            verificaConexao(stmt);
        }

    }


	public void editarDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException {
		String sql = "UPDATE ABRACE.PESSOA_JURIDICA SET cnpj=? fantasia=? razaoSocial=? WHERE idPessoa = ?";
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		try {
			new PessoaDAO(getConexao()).editarPessoa(pessoaJ);

			stmt.setDate(1, Date.valueOf(pessoaJ.getCnpj()));
			stmt.setString(2, pessoaJ.getNomeFantasia());
			stmt.setString(3, pessoaJ.getRazaoSocial());
			stmt.setInt(4, pessoaJ.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			rollBack(e);
		} finally {
			verificaConexao(stmt);
		}
	}

	public void excluirDoadorJuridico(PessoaJuridica pessoaJ) throws SQLException {

		String sql = "UPDATE ABRACE.PESSOA SET ativo=false WHERE idPessoa=" + pessoaJ.getId();
		PreparedStatement stmt = getConexao().prepareStatement(sql);

		stmt.execute();
		stmt.close();
	}

	public ArrayList<PessoaJuridica> listarDoadorJuridico(boolean situacao) {
		ArrayList<PessoaJuridica> listaPessoasJuridicas = new ArrayList<PessoaJuridica>();
		
		String informacaoPessoa = "ABRACE.PESSOA.idPessoa, ABRACE.PESSOA.nome, ABRACE.PESSOA.endereco, ABRACE.PESSOA.telefone1,"
				+ "ABRACE.PESSOA.telefone2, ABRACE.PESSOA.email, ABRACE.PESSOA.dataCadastro,";
		
		String sql = "SELECT " + informacaoPessoa
				+ "ABRACE.PESSOA_JURIDICA.cnpj, ABRACE.PESSOA_JURIDICA.fantasia, ABRACE.PESSOA_JURIDICA.razaoSocial  FROM ABRACE.PESSOA_JURIDICA, ABRACE.PESSOA WHERE ABRACE.PESSOA_JURIDICA.idPessoa = ABRACE.PESSOA.idPessoa AND ativo = "
				+ situacao;
		
		try {
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				String endereco = rs.getString(3);
				String telefone1 = rs.getString(4);
				String telefone2 = rs.getString(5);
				String email = rs.getString(6);
				LocalDate dataCadastro = rs.getDate(7).toLocalDate();
				String cnpj = rs.getString(8);
				String nomeFantasia = rs.getString(9);
				String razaoSocial = rs.getString(10);

				listaPessoasJuridicas.add(new PessoaJuridica(id, nome, endereco, telefone1, telefone2, dataCadastro, email, situacao, cnpj, razaoSocial, nomeFantasia));
			}
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaJuridicaInvalidaException e) {
			e.printStackTrace();
		}

		return listaPessoasJuridicas;
	}
	
}