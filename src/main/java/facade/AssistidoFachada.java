package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
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
	
	public boolean editarAssistido(Assistido a) {
		AssistidoDAO assistido = new AssistidoDAO(new ConnectionFactory().getConnection());
		return assistido.editarAssistido(a);
	}
	
	public boolean excluirAssistido(int id) {
		return false;
	}
	
	
	
}