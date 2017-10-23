package DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAOTest {

	public static void main(String[] args)
			throws PessoaInvalidaException, PessoaJuridicaInvalidaException, SQLException {

		 //inserir();
		 //inserirInvalido();
		 editar();
		// listar();
		// excluir();
	}

	public static void inserir() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("Maria Juridica");
		pessoaJ.setEndereco("Avenida da Maria");
		pessoaJ.setTelefone("3411-5555");
		pessoaJ.setTelefone2("");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("mj@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("02215163000117");
		pessoaJ.setNomeFantasia("M J Confecções");
		pessoaJ.setRazaoSocial("M J LTDA");

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).inserirDoadorJuridico(pessoaJ);
	}
	
	public static void inserirInvalido() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("Maria Juridica");
		pessoaJ.setEndereco("Avenida da Maria");
		pessoaJ.setTelefone("3411-5555");
		pessoaJ.setTelefone2("");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("mj@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("02.215.163/0001-17");
		pessoaJ.setNomeFantasia("M J Confecções");
		pessoaJ.setRazaoSocial("M J LTDA");

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).inserirDoadorJuridico(pessoaJ);
	}

	public static void editar() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("Maria Juridica");
		pessoaJ.setEndereco("Avenida da Maria");
		pessoaJ.setTelefone("3411-6666");
		pessoaJ.setTelefone2("3411-2400");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("mj@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("02215163000117");
		pessoaJ.setNomeFantasia("M J Confecções");
		pessoaJ.setRazaoSocial("M J LTDA");
		pessoaJ.setId(901);

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).editarDoadorJuridico(pessoaJ);

	}

	public static void excluir() throws SQLException {

		ArrayList<PessoaJuridica> lista = new PessoaJuridicaDAO(new ConnectionFactory().getConnection())
				.listarDoadorJuridico(true);
		for (PessoaJuridica pessoaJ : lista) {
			if (pessoaJ.getId() == 901) {
				new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).excluirDoadorJuridico(pessoaJ);
				System.out.println(pessoaJ + "\n");
			}
		}
	}

	public static void listar() throws SQLException {

		boolean situacao = true;

		ArrayList<PessoaJuridica> lista = new PessoaJuridicaDAO(new ConnectionFactory().getConnection())
				.listarDoadorJuridico(situacao);
		for (PessoaJuridica pessoaJ : lista) {
			System.out.println(pessoaJ + "\n");
		}
	}

}
