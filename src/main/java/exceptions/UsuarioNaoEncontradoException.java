package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("O usuário informado não está cadastrado no sistema!");
	}
}
