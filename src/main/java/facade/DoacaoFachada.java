package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ConnectionFactory;
import DAO.DoacaoDAO;
import DAO.PessoaDAO;
import exceptions.DoacaoInvalidaException;
import model.Doacao;

public class DoacaoFachada {

	public ArrayList<Doacao> listarDoacoes() {
		return new ArrayList<Doacao>();
	}
	
	public Doacao obterDoacao(int idDoacao) throws DoacaoInvalidaException {
		DoacaoDAO dao = new DoacaoDAO(new ConnectionFactory().getConnection());
		Doacao doacao = dao.getDoacao(idDoacao);
		try {
			dao.getConexao().close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return doacao;
	}
	
	public boolean excluirDoacao(int idDoacao) throws DoacaoInvalidaException {
		DoacaoDAO dao = new DoacaoDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirDoacao(dao.getDoacao(idDoacao));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public String obterCPFCNPJ(int id) {
		PessoaDAO dao = new PessoaDAO(new  ConnectionFactory().getConnection());
		String cpfCNPJ = null;
		try {
			cpfCNPJ = dao.getCPFCNPJ(id);
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cpfCNPJ;
	}
	
	public boolean realizarDoacao(Doacao doacao) {
		DoacaoDAO dao = new DoacaoDAO(new  ConnectionFactory().getConnection());
		if(dao.cadastrarDoacao(doacao)) {
			try {
				dao.getConexao().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				return true;
			}
		}
		return false;
	}
}
