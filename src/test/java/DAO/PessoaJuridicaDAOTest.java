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

		 cadastrar();
		// editar();
		// listar();
		// excluir();
	}

	public static void cadastrar() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("João do teste");
		pessoaJ.setEndereco("Avenida Dom Lino");
		pessoaJ.setTelefone("3411-4444");
		pessoaJ.setTelefone2("3411-4444");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("joaoteste@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("586495730001-------20");
		pessoaJ.setNomeFantasia("João Variedades");
		pessoaJ.setRazaoSocial("João T LTDA");

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).cadastrarDoadorJuridico(pessoaJ);
	}

	public static void editar() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("João do teste");
		pessoaJ.setEndereco("Avenida Dom Lino");
		pessoaJ.setTelefone("3411-4444");
		pessoaJ.setTelefone2("3411-4444");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("joaoteste@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("58.649.573/0001-20");
		pessoaJ.setNomeFantasia("João Variedades");
		pessoaJ.setRazaoSocial("João T LTDA");

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).editarDoadorJuridico(pessoaJ);

	}

	public static void excluir() throws SQLException {

		ArrayList<PessoaJuridica> lista = new PessoaJuridicaDAO(new ConnectionFactory().getConnection())
				.listarDoadorJuridico(true);
		for (PessoaJuridica pessoaJ : lista) {
			if (pessoaJ.getId() == 1) {
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
