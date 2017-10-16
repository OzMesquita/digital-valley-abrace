package facade;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.Assistido;

public class AssistidoFachada {
	public boolean cadastrarAssistido(Assistido a) throws PessoaInvalidaException, SQLException{
		AssistidoDAO assistido = new AssistidoDAO(new ConnectionFactory().getConnection());
		if (assistido.inserirAssistido(a)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Assistido> obterAssistido(int id) {
		return null;
	}
	
	public ArrayList<Assistido> listarTodosAssistidos(){
		AssistidoDAO dao = new AssistidoDAO(new ConnectionFactory().getConnection());
		ArrayList<Assistido> lista = dao.listaAssistido();
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
}