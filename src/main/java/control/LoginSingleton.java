package control;

import model.Usuario;

public class LoginSingleton {
	private static Usuario usuario;

	public static void LoginSingleton(Usuario usuario) {
		if (LoginSingleton.usuario == null) {
			setUsuario(usuario);
		}
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		if (LoginSingleton.usuario == null) {
			LoginSingleton.usuario = usuario;
		}
	}
}