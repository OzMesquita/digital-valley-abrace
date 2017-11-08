package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import DAO.PessoaJuridicaDAO;
import exceptions.PessoaInvalidaException;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class DoadorFachada {
	public boolean cadastrarDoador(PessoaFisica doador) throws SQLException, PessoaInvalidaException {
		PessoaFisicaDAO pessoaFisica = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = pessoaFisica.inserirPessoaFisica(doador);
		pessoaFisica.getConexao().close();
		return resultado;
	}
	
	public boolean cadastrarDoadorJuridico(PessoaJuridica doador) throws SQLException, PessoaInvalidaException {
		PessoaJuridicaDAO pessoaJuridica = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = pessoaJuridica.inserirDoadorJuridico(doador);
		pessoaJuridica.getConexao().close();
		return resultado;
	}
	
	public PessoaFisica obterDoadorFisico(int id) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		PessoaFisica doador = dao.getPessoaFisica(id);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doador;
	}
	
	public ArrayList<PessoaFisica> listarPessoaFisica(){
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaFisica> lista = new ArrayList<PessoaFisica>();
		try {
			lista = dao.listarPessoasFisicas();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Pessoa> getTodosDoadores(){
        ArrayList<Pessoa> todos = new ArrayList<Pessoa>();
        todos.addAll(listarPessoaFisica());
        todos.addAll(listarPessoaJuridica());
        return todos;
    }

	
	public PessoaJuridica obterDoadorJuridico(int id) {
	PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
	PessoaJuridica doador = dao.getPessoaJuridica(id);
	try {
		dao.getConexao().close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return doador;
	}
	
	public ArrayList<PessoaJuridica> listarPessoaJuridica() {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
		try {
			lista = dao.listarDoadorJuridico();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean editarDoadorPF(PessoaFisica doador) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		try {
			dao.editarDoadorFisico(doador);
			dao.getConexao().close();
			return true;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarDoadorJuridico(PessoaJuridica doador) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		try {
			dao.editarDoadorJuridico(doador);
			dao.getConexao().close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean excluirDoadorFisico(int id) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirDoadorFisico(dao.getPessoaFisica(id));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean excluirDoadorJuridico(int id) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
			boolean resultado = dao.excluirDoadorJuridico(dao.getPessoaJuridica(id));
			try {
				dao.getConexao().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return resultado;
	}

	public static Pessoa getDadosDoador(int id) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		Pessoa pessoa = dao.getPessoaFisica(id);
		try {
			if(pessoa != null) {
				dao.getConexao().close();
				return pessoa;
			}else {
				PessoaJuridicaDAO novaDao = new PessoaJuridicaDAO(dao.getConexao());
				pessoa = novaDao.getPessoaJuridica(id);
				if(pessoa != null) {
					novaDao.getConexao().close();
					return pessoa;
				}else {
					novaDao.getConexao().close();
					return null;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
