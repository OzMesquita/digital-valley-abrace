package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("Não foi possível fazer o login. Você digitou uma senha ou um login incorreto");
	}
}
