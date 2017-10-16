package facade;

import java.sql.SQLException;

import DAO.ConnectionFactory;
import DAO.UsuarioDAO;
import exceptions.UsuarioInvalidoException;
import exceptions.PessoaInvalidaException;
import model.Usuario;

public class LoginFachada {

	public Usuario loga(String login, String senha) throws UsuarioInvalidoException, PessoaInvalidaException, SQLException {
		Usuario func = null;
		UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory().getConnection());
		func = dao.getFuncionario(login, senha);
		dao.getConexao().close();
		
		return func;
	}
}
