package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("N�o foi poss�vel fazer o login. \nVoc� digitou o login ou senha inv�lido.");
	}
}
