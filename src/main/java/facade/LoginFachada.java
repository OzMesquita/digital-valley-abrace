package facade;

import java.sql.SQLException;

import DAO.ConnectionFactory;
import DAO.UsuarioDAO;
import exceptions.UsuarioInvalidoException;
import exceptions.PessoaInvalidaException;
import model.Usuario;

public class LoginFachada {
	public Usuario autenticar(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException, SQLException {
		Usuario usuario = null;
		UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory().getConnection());
		usuario = dao.getUsuario(login, senha);
		dao.getConexao().close();
		return usuario;
	}
}
