package facade;


import java.sql.SQLException;

import DAO.ConnectionFactory;
import DAO.UsuarioDAO;
import model.Usuario;

public class UsuarioFacade {
	public boolean editarUsuario(Usuario usuario) {
			UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory().getConnection());
			dao.editarUsuario(usuario);
			try {
				dao.getConexao().close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}
}
