package DAO;

import java.sql.SQLException;

import java.time.LocalDate;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;

public class PessoaFisicaDAOTest {

	public static void main(String[] args) throws PessoaInvalidaException, SQLException, PessoaFisicaException {

		//cadastrar();
		editarInvalido();
		//listar();
		//excluir();
	}

	public static void cadastrar() throws PessoaInvalidaException, SQLException, PessoaFisicaException{
		PessoaFisica pf = new PessoaFisica();

		pf.setNome("Maria Joaquina de Lima");
		pf.setEndereco("Rua José");
		pf.setTelefone("8894161510");
		pf.setTelefone2("8597136640");
		pf.setDataCadastro(LocalDate.now());
		pf.setEmail("joaquina@gmail.com");
		pf.setAtivo(true);
		pf.setDataNasc(LocalDate.now());
		pf.setRg("20066897933");
		pf.setCpf("47855774602");

		new PessoaFisicaDAO(new ConnectionFactory().getConnection()).inserirPessoaFisica(pf);
	}

	public static void editarInvalido() throws PessoaFisicaException, PessoaInvalidaException  {
		PessoaFisica pf = new PessoaFisica();

//		pf.setNome("Maria Joaquina de Lima");
//		pf.setEndereco("Rua José");
//		pf.setTelefone("8894161510");
//		pf.setTelefone2("3411-1015");  //alterando telefone2 8597136640 para 3411-1015 na tabela PESSOA
//		pf.setDataCadastro(LocalDate.now());
//		pf.setEmail("joaquina@gmail.com");
//		pf.setAtivo(true);
//		pf.setDataNasc(LocalDate.now()); //Incluindo um erro no rg na Tabela PessoaFisica
//		pf.setRg("20066897933--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		pf.setCpf("47855774602");
//		pf.setId(201);

		//Incluindo um erro no nome na tabela PESSOA
		pf.setNome("Maria Joaquina de Lima------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		pf.setEndereco("Rua José");
		pf.setTelefone("8894161510");
		pf.setTelefone2("3411-1015"); //alterando telefone2 8597136640 para 3411-1015 na tabela PESSOA
		pf.setDataCadastro(LocalDate.now());
		pf.setEmail("joaquina@gmail.com");
		pf.setAtivo(true);
		pf.setDataNasc(LocalDate.now());
		pf.setRg("20066897444"); //alterando rg de 20066897933 para 20066897444
		pf.setCpf("47855774602");
		pf.setId(201);
		new PessoaFisicaDAO(new ConnectionFactory().getConnection()).editarDoadorFisico(pf);

	}

	/*public static void listar() throws SQLException {

		boolean situacao = true; 

		ArrayList<PessoaFisica> lista = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).listarPessoasFisicas(situacao);
		for (PessoaFisica p : lista) {
			System.out.println(p + "\n");
		}
	}

	public static void excluir() throws SQLException{

		ArrayList<PessoaFisica> lista = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).listarPessoasFisicas(true);
		for (PessoaFisica p : lista) {
			if(p.getId() == 1){
				new PessoaFisicaDAO(new ConnectionFactory().getConnection()).excluirPessoaFisica(p);
				System.out.println(p + "\n");
			}
		}
	}*/
}
