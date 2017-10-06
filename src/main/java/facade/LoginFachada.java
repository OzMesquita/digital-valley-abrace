package facade;

import java.sql.SQLException;

import DAO.ConnectionFactory;
import DAO.DAOUsuario;
import exceptions.UsuarioInvalidoException;
import exceptions.PessoaInvalidaException;
import model.Usuario;

public class LoginFachada {

	public Usuario loga(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException {
		Usuario func = null;
		DAOUsuario dao = new DAOUsuario(new ConnectionFactory().abreConexao());
		func = dao.getFuncionario(login, senha);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return func;
	}
}
