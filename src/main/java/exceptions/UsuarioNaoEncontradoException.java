package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("N�o foi poss�vel fazer o login. Voc� digitou uma senha ou um login incorreto");
	}
}
