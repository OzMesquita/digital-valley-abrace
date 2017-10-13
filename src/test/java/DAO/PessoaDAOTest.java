package DAO;

import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.Pessoa;

public class PessoaDAOTest {

	public static void main(String[] args) throws PessoaInvalidaException, PessoaJuridicaInvalidaException, SQLException {
		Pessoa p = new Pessoa();
		
		p.setNome("Pessoa Test");
		p.setEndereco("Rua de teste");
		p.setTelefone("78965412");
		p.setTelefone2("78945612");
		
		p.setDataCadastro(LocalDate.now());
		p.setEmail("mm@gmail.com");
		p.setAtivo(true);
		
		System.out.println(new PessoaDAO(new ConnectionFactory().getConnection()).cadastrarPessoa(p));
	}
}
