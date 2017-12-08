package facade;

import java.sql.SQLException;

import DAO.ConnectionFactory;
import DAO.PessoaDAO;
import model.Pessoa;

public class PessoaFachada {
	public Pessoa obterPessoa(int idPessoa) {
		PessoaDAO dao = new PessoaDAO(new ConnectionFactory().getConnection());
		Pessoa pessoa = null;
		try {
			pessoa = dao.getPessoa(idPessoa);
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pessoa;
	}
}
