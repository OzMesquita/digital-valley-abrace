package DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.DoacaoInvalidaException;
import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;

public class DoacaoDAOTeste {

	public static void main(String[] args) throws DoacaoInvalidaException {

		//inserir();
		//inserirInvalido();
		//editar();
		//listar();
		excluir();
	}

	public static void inserir() throws DoacaoInvalidaException {
		Doacao doacao = new Doacao();

		//PessoaJuridica doador = new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).getPessoaJuridica(1);

		PessoaFisica doador = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(101);

		doacao.setAtivo(true);
		doacao.setDoador(doador);
		doacao.setData(LocalDate.now());
		doacao.setValor(1000.00);

		new DoacaoDAO(new ConnectionFactory().getConnection()).cadastrarDoacao(doacao);
	}

	public static void inserirInvalido() throws DoacaoInvalidaException  {

		Doacao doacao = new Doacao();

		//PessoaJuridica doador = new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).getPessoaJuridica(1);

		//PessoaFisica doador = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(101);

		doacao.setAtivo(true);
		doacao.setDoador(null); //incluir um Doador nulo
		doacao.setData(LocalDate.now());
		doacao.setValor(1000.00);

		new DoacaoDAO(new ConnectionFactory().getConnection()).cadastrarDoacao(doacao);

	}

	public static void editar() throws DoacaoInvalidaException  {

		Doacao doacao = new Doacao();

		PessoaJuridica doador = new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).getPessoaJuridica(1);

		//PessoaFisica doador = new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(101);

		doacao.setAtivo(true);
		doacao.setDoador(null); 
		doacao.setData(LocalDate.of(2017, 10, 29));
		doacao.setValor(2000.00);
		doacao.setId(1);

		new DoacaoDAO(new ConnectionFactory().getConnection()).editarDoacao(doacao);

	}

	public static void listar() throws DoacaoInvalidaException  {

		ArrayList<Doacao> lista = new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes();
		for (Doacao d : lista) {
			System.out.println(d + "\n");
		}
	}

	public static void excluir() throws DoacaoInvalidaException {

		ArrayList<Doacao> lista = new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes();

		for (Doacao d : lista) {
			if (d.getId() == 201) {
				if(new DoacaoDAO(new ConnectionFactory().getConnection()).excluirDoacao(d));
					System.out.println(d + "\n");
			}
		}
	}
}

