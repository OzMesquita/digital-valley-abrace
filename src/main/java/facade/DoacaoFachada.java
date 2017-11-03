package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ConnectionFactory;
import DAO.DoacaoDAO;
import DAO.PessoaFisicaDAO;
import model.Doacao;

public class DoacaoFachada {

	public ArrayList<Doacao> listarDoacoes() {
		return new ArrayList<Doacao>();
	}
	
	public Doacao obterDoacao(int idDoacao) {
		DoacaoDAO dao = new DoacaoDAO(new ConnectionFactory().getConnection());
		Doacao doacao = dao.getDoacao(idDoacao);
		try {
			dao.getConexao().close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return doacao;
	}
	
	public boolean excluirDoacao(int idDoacao) {
		DoacaoDAO dao = new DoacaoDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirDoacao(dao.getDoacao(idDoacao));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean realizarDoacao(Doacao doacao) {
		return false;
	}
}
