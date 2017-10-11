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
		if(assistido.inserirAssistido(a)) {
			assistido.getConexao().close();
			return true;
		}
		return false;
	}
	
	public ArrayList<Assistido> listarTodosAssistidos(){
		return new ArrayList<Assistido>();
	}
	
}