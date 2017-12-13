package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import DAO.PessoaJuridicaDAO;
import exceptions.PessoaInvalidaException;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;

public class DoadorFachada {
	public boolean cadastrarDoadorFisico(PessoaFisica doadorFisico) throws SQLException, PessoaInvalidaException {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.inserirPessoaFisica(doadorFisico);
		dao.getConexao().close();
		return resultado;
	}
	
	public boolean cadastrarDoadorJuridico(PessoaJuridica doadorJuridico) throws SQLException, PessoaInvalidaException {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.inserirDoadorJuridico(doadorJuridico);
		dao.getConexao().close();
		return resultado;
	}
	
	public PessoaFisica obterDoadorFisico(int idDoadorFisico) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		PessoaFisica doadorFisico = dao.getPessoaFisica(idDoadorFisico);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadorFisico;
	}
	
	public ArrayList<PessoaFisica> listarPessoaFisica(){
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaFisica> doadoresFisicos = new ArrayList<PessoaFisica>();
		try {
			doadoresFisicos = dao.listarDoadoresFisicos();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadoresFisicos;
	}
	
	public List<Pessoa> getTodosDoadores(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        pessoas.addAll(listarPessoaFisica());
        pessoas.addAll(listarPessoaJuridica());
        Collections.sort(pessoas);
        return pessoas;
    }

	
	public PessoaJuridica obterDoadorJuridico(int idDoadorJuridico) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		PessoaJuridica doadorJuridico = dao.getPessoaJuridica(idDoadorJuridico);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadorJuridico;
	}
	
	public ArrayList<PessoaJuridica> listarPessoaJuridica() {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaJuridica> doadoresJuridicos = new ArrayList<PessoaJuridica>();
		try {
			doadoresJuridicos = dao.listarDoadorJuridico();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadoresJuridicos;
	}
	
	public boolean editarDoadorFisico(PessoaFisica doadorFisico) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		try {
			dao.editarDoadorFisico(doadorFisico);
			dao.getConexao().close();
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean editarDoadorJuridico(PessoaJuridica doadorJuridico) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		try {
			dao.editarDoadorJuridico(doadorJuridico);
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean excluirDoadorFisico(int idDoadorFisico) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirDoadorFisico(dao.getPessoaFisica(idDoadorFisico));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	private boolean excluirDoadorJuridico(int idDoadorJuridico) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.excluirDoadorJuridico(dao.getPessoaJuridica(idDoadorJuridico));
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public boolean excluirDoador(int idDoador) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		PessoaJuridica doador = dao.getPessoaJuridica(idDoador);
		try {
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(doador == null) {
			return excluirDoadorFisico(idDoador);
		}else {
			return excluirDoadorJuridico(idDoador);
		}
	}

	public static Pessoa getDadosDoador(int idPessoa) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		Pessoa pessoa = dao.getPessoaFisica(idPessoa);
		try {
			if(pessoa != null) {
				dao.getConexao().close();
				return pessoa;
			}else {
				PessoaJuridicaDAO novaDao = new PessoaJuridicaDAO(dao.getConexao());
				pessoa = novaDao.getPessoaJuridica(idPessoa);
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
	
	public boolean verificaCPF(String CPF) throws SQLException {
		PessoaFisicaDAO dao = new PessoaFisicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.getPessoaPeloCPF(CPF);
		dao.getConexao().close();
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
	
	public boolean verificaCNPJ(String CNPJ) throws SQLException {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		boolean resultado = dao.getPessoaPeloCNPJ(CNPJ);
		dao.getConexao().close();
		return resultado;
	}

	
	public ArrayList<PessoaJuridica> listarTabelaPessoaJuridica() {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO(new ConnectionFactory().getConnection());
		ArrayList<PessoaJuridica> doadoresJuridicos = new ArrayList<PessoaJuridica>();
		try {
			doadoresJuridicos = dao.listarTabelaDoadorJuridico();
			dao.getConexao().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doadoresJuridicos;
	}
}