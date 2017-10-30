package DAO;

import java.sql.SQLException;

import java.time.LocalDate;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAOTest {

	public static void main(String[] args)
			throws PessoaInvalidaException, PessoaJuridicaInvalidaException, SQLException {

		 //inserir();
		 //inserirInvalido();
		//editar();
		 editarInvalida();
		// listar();
		// excluir();
	}

	public static void inserir() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("Cícero Alcantara");
		pessoaJ.setEndereco("Avenida Primeira Classe");
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8598001388");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152");
		pessoaJ.setNomeFantasia("Alcantara Imovéis");
		pessoaJ.setRazaoSocial("A Imovéis LTDA");

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

		pessoaJ.setNome("Cícero Alcantara");
		pessoaJ.setEndereco("Avenida Primeira Classe - Centro/CE"); //incluindo alteração no endereço
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8598001388");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152");
		pessoaJ.setNomeFantasia("Alcantara Imovéis");
		pessoaJ.setRazaoSocial("A Imovéis LTDA");
		pessoaJ.setId(301);

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).editarDoadorJuridico(pessoaJ);

	}

	public static void editarInvalida() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

//		pessoaJ.setNome("Cícero Alcantara");
//		pessoaJ.setEndereco("Avenida Simões"); //incluindo alteração no endereço
//		pessoaJ.setTelefone("3445-7894");
//		pessoaJ.setTelefone2("8899151620"); //incluindo alteração no telefone 8598001388 para 8899151620
//		pessoaJ.setDataCadastro(LocalDate.now());
//		pessoaJ.setEmail("imoveis@gmail.com");
//		pessoaJ.setAtivo(true);
//		pessoaJ.setCnpj("78533714000152"); //Incluindo um erro no nomeFantasia na Tabela PessoaJuridica
//		pessoaJ.setNomeFantasia("Alcantara Imovéis--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		pessoaJ.setRazaoSocial("A Imovéis LTDA");
//		pessoaJ.setId(301);
		
		//Incluindo um erro no nomeFantasia na Tabela PessoaJuridica
		pessoaJ.setNome("Cícero Alcantara------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		pessoaJ.setEndereco("Avenida Simões"); //incluindo alteração no endereço
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8899151620"); //incluindo alteração no telefone 8598001388 para 8899151620
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152"); 
		pessoaJ.setNomeFantasia("Imovéis Alcantara");//incluindo alteração no nomeFantasia
		pessoaJ.setRazaoSocial("A Imovéis LTDA");
		pessoaJ.setId(301);

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).editarDoadorJuridico(pessoaJ);

	}
	

	/*public static void excluir() throws SQLException {

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
*/
}
