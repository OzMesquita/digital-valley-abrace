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
			System.out.println("Deu Certo!!!");
			return true;
		}
		return false;
	}
	
	public ArrayList<Assistido> obterAssistido(int id) {
		return null;
	}
	
	public ArrayList<Assistido> listarTodosAssistidos(){
		ArrayList<Assistido> lista = new ArrayList<Assistido>();
		try {
			Assistido a = new Assistido();
			a.setId(1);
			a.setNome("Thiago");
			a.setDataNasc(LocalDate.now());
			a.setCpf("05965203365");
			lista.add(a);
			
			Assistido a1 = new Assistido();
			a1.setId(2);
			a1.setNome("Gabriel");
			a1.setDataNasc(LocalDate.now());
			a1.setCpf("05965203365");
			lista.add(a1);
			
			Assistido a2 = new Assistido();
			a2.setId(3);
			a2.setNome("Jhean");
			a2.setDataNasc(LocalDate.now());
			a2.setCpf("05965203365");
			lista.add(a2);
			
		} catch (PessoaInvalidaException e) {
			e.printStackTrace();
		} catch (PessoaFisicaException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
}