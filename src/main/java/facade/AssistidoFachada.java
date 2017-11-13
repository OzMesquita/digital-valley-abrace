package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import DAO.PessoaDAO;
import DAO.PessoaFisicaDAO;
import exceptions.PessoaInvalidaException;
import model.Assistido;

public class AssistidoFachada {
	public boolean cadastrarAssistido(Assistido assistido) throws PessoaInvalidaException, SQLException{
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.inserirAssistido(assistido);
		dao.getConexao().close();
		return resultado;
	}
	
	public Assistido obterAssistido(int idAssistido) {
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		Assistido assistido = dao.getAssistido(idAssistido);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assistido;
	}
	
	public ArrayList<Assistido> listarTodosAssistidos(){
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		ArrayList<Assistido> assistidos = dao.listaAssistido();
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assistidos;
	}
	
	public boolean editarAssistido(Assistido assistido) throws PessoaInvalidaException {
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		boolean resultado= false;
		try {
			resultado = dao.editarAssistido(assistido);
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean excluirAssistido(int idAssistido) {
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirAssistido(dao.getAssistido(idAssistido));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}