package facade;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import DAO.PessoaJuridicaDAO;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;
import model.PessoaJuridica;

public class DoadorFachada {
	public boolean cadastrarDoador(PessoaFisica doador) {
		return true;
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
}
