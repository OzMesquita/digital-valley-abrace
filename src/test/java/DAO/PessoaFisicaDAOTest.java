package DAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;

public class PessoaFisicaDAOTest {

public static void main(String[] args) throws PessoaInvalidaException, SQLException, PessoaFisicaException {
		
		//cadastrar();
		//listar();
		//excluir();
	}
	
	//Teste do editar foi feito aqui
	public static void cadastrar() throws PessoaInvalidaException, SQLException, PessoaFisicaException{
		PessoaFisica pf = new PessoaFisica();
		
		pf.setNome("Sicrano");
		pf.setEndereco("Rua de teste");
		pf.setTelefone("78965412");
		pf.setTelefone2("78945612");
		pf.setDataCadastro(LocalDate.now());
		pf.setEmail("mm@gmail.com");
		pf.setAtivo(true);
		pf.setDataNasc(LocalDate.now());
		pf.setRg("20022459688");
		pf.setCpf("34734118841");
		
		new PessoaFisicaDAO(new ConnectionFactory().getConnection()).cadastrarPessoaFisica(pf);
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
