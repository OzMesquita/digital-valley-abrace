package DAO;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import model.PessoaJuridica;

public class PessoaJuridicaDAOTest {

	public static void main(String[] args) throws PessoaInvalidaException, PessoaJuridicaInvalidaException {
		PessoaJuridica pj = new PessoaJuridica();
		
		pj.setNomeFantasia("M M LTDA");
		pj.setCnpj("99.999.999/9999-99");
		
		
		new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).inserirDoadorJuridico(pj);
	}
}
