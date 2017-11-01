package doacao;

public class GerenciadorDeJanelas {
	private static final GerenciadorDeJanelas janela = new GerenciadorDeJanelas();
	
	private GerenciadorDeJanelas() {
		
	}

	public static GerenciadorDeJanelas getInstance() {
		return janela;
	}
}
