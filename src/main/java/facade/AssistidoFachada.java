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
	public boolean cadastrarAssistido(Assistido a) throws PessoaInvalidaException, SQLException{
		AssistidoDAO assistido = new AssistidoDAO(new ConnectionFactory().getConnection());
		boolean resultado = assistido.inserirAssistido(a);
		assistido.getConexao().close();
		return resultado;
	}
	
	public Assistido obterAssistido(int id) {
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		Assistido assistido = dao.getAssistido(id);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return assistido;
	}
	
	public ArrayList<Assistido> listarTodosAssistidos(){
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		ArrayList<Assistido> lista = dao.listaAssistido();
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean editarAssistido(Assistido a) throws PessoaInvalidaException {
		AssistidoDAO assistido = new AssistidoDAO(new ConnectionFactory().getConnection());
		PessoaFisicaDAO fisicaDao = new PessoaFisicaDAO(assistido.getConexao());
		PessoaDAO pessoa = new PessoaDAO(assistido.getConexao());
		boolean resultado= false;
		try {
			pessoa.editarPessoa(a);
			fisicaDao.editarDoadorFisico(a);
			resultado = assistido.editarAssistido(a);
			assistido.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}return resultado;
	}
	
	public boolean excluirAssistido(int id) {
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirAssistido(dao.getAssistido(id));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	
	
}