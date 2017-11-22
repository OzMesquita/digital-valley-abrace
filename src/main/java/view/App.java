package view;

import java.sql.SQLException;

import DAO.Instalacao;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;

public class App {
	public static void main(String[] args) throws SQLException, PessoaInvalidaException, UsuarioInvalidoException, PessoaFisicaException {
		Instalacao.executarInstalacao();
		LoginView.main();
	}
}
