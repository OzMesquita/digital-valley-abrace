package exceptions;

public class UsuarioNaoEncontradoException extends Exception {
	public UsuarioNaoEncontradoException() {
		super("O usu�rio informado n�o est� cadastrado no sistema!");
	}
}
