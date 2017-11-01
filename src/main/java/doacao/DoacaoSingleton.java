package doacao;

import java.time.LocalDate;

import exceptions.DoacaoInvalidaException;
import model.Doacao;
import model.Pessoa;

public class DoacaoSingleton {
	//Essa classe armanezará os dados da doação que está sendo realizada em 3 passos. Caso em uma das telas o usuário clicar em "Cancelar" a docação será cancelada
	
	private static Doacao doacao = new Doacao();
	private DoacaoSingleton() {
		
	}
	public static Doacao getDoacao() {
		return doacao;
	}
	public static void setDoacao(Doacao doacao) {
		DoacaoSingleton.doacao = doacao;
	}
	
	public static void setDoador(Pessoa doador) {
		try {
			DoacaoSingleton.doacao.setDoador(doador);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public static void setValor(double valor) {
		try {
			DoacaoSingleton.doacao.setValor(valor);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDataDoacao(LocalDate dataDoacao) {
		try {
			DoacaoSingleton.doacao.setData(dataDoacao);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
}
