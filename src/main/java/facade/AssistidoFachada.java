package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import exceptions.PessoaInvalidaException;
import model.Assistido;
import model.PessoaFisica;

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
	
	public boolean ativaDoador(int id) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.ativaDoador(id);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean verificaCPF(String CPF) throws SQLException {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.getPessoaPeloCPF(CPF);
		dao.getConexao().close();
		return resultado;
	}
	
	public List<PessoaFisica> listarTabelaPessoasFisicas() {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaFisica> doadoresFisicos = new ArrayList<PessoaFisica>();
		try {
			doadoresFisicos = dao.listarTabelaPessoasFisicas();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadoresFisicos;
	}
	
	
}