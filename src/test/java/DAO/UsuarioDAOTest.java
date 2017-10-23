package DAO;

import java.sql.SQLException;
import java.time.LocalDate;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import model.Usuario;

public class UsuarioDAOTest {
	public static void main(String[] args) throws UsuarioInvalidoException, PessoaInvalidaException, SQLException, PessoaFisicaException {
		UsuarioDAO daoTeste = new UsuarioDAO(new ConnectionFactory().getConnection());
		Usuario usuario = new Usuario("Jhean", "kjhskjsh", LocalDate.now(), "646878766", "646878766", "kjhakjhajk@kjhjhajh.com", true, "03451451352", "2001548748689", LocalDate.now(), "Jhean", "doit");
		daoTeste.inserirUsuario(usuario);
	}
}
