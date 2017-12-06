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
		Usuario usuario = new Usuario("ggoncalvesm", "doit", LocalDate.now(), "646878766", "646878766", "kjhakjhajk@kjhjhajh.com", true, "342.535.568-68", "200154874868-9", LocalDate.now(), true, "isabelly", "doit");
		daoTeste.inserirUsuario(usuario);
	}
}
