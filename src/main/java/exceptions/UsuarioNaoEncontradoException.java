package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("Não foi possível fazer o login. \nVocê digitou o login ou senha inválido.");
	}
}
