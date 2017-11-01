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

		pessoaJ.setNome("C�cero Alcantara");
		pessoaJ.setEndereco("Avenida Primeira Classe");
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8598001388");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152");
		pessoaJ.setNomeFantasia("Alcantara Imov�is");
		pessoaJ.setRazaoSocial("A Imov�is LTDA");

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
		pessoaJ.setNomeFantasia("M J Confec��es");
		pessoaJ.setRazaoSocial("M J LTDA");

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).inserirDoadorJuridico(pessoaJ);
	}
	
	public static void editar() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

		pessoaJ.setNome("C�cero Alcantara");
		pessoaJ.setEndereco("Avenida Primeira Classe - Centro/CE"); //incluindo altera��o no endere�o
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8598001388");
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152");
		pessoaJ.setNomeFantasia("Alcantara Imov�is");
		pessoaJ.setRazaoSocial("A Imov�is LTDA");
		pessoaJ.setId(301);

		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).editarDoadorJuridico(pessoaJ);

	}

	public static void editarInvalida() throws PessoaInvalidaException, SQLException, PessoaJuridicaInvalidaException {
		PessoaJuridica pessoaJ = new PessoaJuridica();

//		pessoaJ.setNome("C�cero Alcantara");
//		pessoaJ.setEndereco("Avenida Sim�es"); //incluindo altera��o no endere�o
//		pessoaJ.setTelefone("3445-7894");
//		pessoaJ.setTelefone2("8899151620"); //incluindo altera��o no telefone 8598001388 para 8899151620
//		pessoaJ.setDataCadastro(LocalDate.now());
//		pessoaJ.setEmail("imoveis@gmail.com");
//		pessoaJ.setAtivo(true);
//		pessoaJ.setCnpj("78533714000152"); //Incluindo um erro no nomeFantasia na Tabela PessoaJuridica
//		pessoaJ.setNomeFantasia("Alcantara Imov�is--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		pessoaJ.setRazaoSocial("A Imov�is LTDA");
//		pessoaJ.setId(301);
		
		//Incluindo um erro no nomeFantasia na Tabela PessoaJuridica
		pessoaJ.setNome("C�cero Alcantara------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		pessoaJ.setEndereco("Avenida Sim�es"); //incluindo altera��o no endere�o
		pessoaJ.setTelefone("3445-7894");
		pessoaJ.setTelefone2("8899151620"); //incluindo altera��o no telefone 8598001388 para 8899151620
		pessoaJ.setDataCadastro(LocalDate.now());
		pessoaJ.setEmail("imoveis@gmail.com");
		pessoaJ.setAtivo(true);
		pessoaJ.setCnpj("78533714000152"); 
		pessoaJ.setNomeFantasia("Imov�is Alcantara");//incluindo altera��o no nomeFantasia
		pessoaJ.setRazaoSocial("A Imov�is LTDA");
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
