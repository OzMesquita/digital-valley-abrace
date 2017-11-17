package control;

import model.Pessoa;

public class LoginSingleton {
	private static Pessoa usuario;

	public static void LoginSingleton(Pessoa usuario) {
		if (LoginSingleton.usuario == null) {
			setUsuario(usuario);
		}
	}

	public static Pessoa getUsuario() {
		return usuario;
	}

	public static void setUsuario(Pessoa usuario) {
		if (LoginSingleton.usuario == null) {
			LoginSingleton.usuario = usuario;
		}
	}
}