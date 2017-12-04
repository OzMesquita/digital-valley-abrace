package exibirRelatorios;

import java.time.LocalDate;
import exceptions.DoacaoInvalidaException;
import model.Doacao;
import model.Pessoa;

public class EmitirRelatorioSingleton {
	//Essa classe armanezar� os dados da doa��o que est� sendo realizada em 3 passos. Caso em uma das telas o usu�rio clicar em "Cancelar" a doca��o ser� cancelada
	private static Doacao doacao = new Doacao();
	private static String cpfCNPJ = null;
	private EmitirRelatorioSingleton() {
		
	}
	
	public static Doacao getDoacao() {
		return doacao;
	}
	
	public static void setDoacao(Doacao doacao) {
		EmitirRelatorioSingleton.doacao = doacao;
	}
	
	public static void setDoador(Pessoa doador) {
		try {
			EmitirRelatorioSingleton.doacao.setDoador(doador);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public static void setValor(double valor) {
		try {
			EmitirRelatorioSingleton.doacao.setValor(valor);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public static void setDataDoacao(LocalDate dataDoacao) {
		try {
			EmitirRelatorioSingleton.doacao.setData(dataDoacao);
		} catch (DoacaoInvalidaException e) {
			e.printStackTrace();
		}
	}
	
	public static String getCpfCNPJ() {
		return cpfCNPJ;
	}
	
	public static void setCpfCNPJ(String cpfCNPJ) {
		EmitirRelatorioSingleton.cpfCNPJ = cpfCNPJ;
	}
}