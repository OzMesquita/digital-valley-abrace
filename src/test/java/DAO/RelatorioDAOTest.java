package DAO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import exceptions.DoacaoInvalidaException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;
import relatorio.ReciboDoacaoFachada;
import relatorio.RelatorioAnualPessoaFachada;
import relatorio.RelatorioAssistidoFacade;
import relatorio.RelatorioDoacaoFachada;
import relatorio.RelatorioDoadoresFacade;

public class RelatorioDAOTest {


	public static void main(String[] args) throws DocumentException, IOException, PessoaInvalidaException, PessoaFisicaException, DoacaoInvalidaException {
		
		//gerarRelatorioAssistido();
		//gerarRelatorioDoadores();
		gerarRecibos();
		//gerarRelatorioMensal();
		//gerarRelatorioAnual();
		//gerarRelatorioAnualPessoa();
	}

	public static void gerarRelatorioAssistido(){
		try {
			RelatorioAssistidoFacade r = new RelatorioAssistidoFacade();
			r.relatorioDeAssistido(new AssistidoDAO(new ConnectionFactory().getConnection()).listaAssistido(),true,true);
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void gerarRelatorioDoadores(){
		//TESTES RELATORIO DE DOADORES
		DoadorFachada doadores = new DoadorFachada();
		try {
			RelatorioDoadoresFacade r = new RelatorioDoadoresFacade();
			r.relatorioDeDoadores(doadores.getTodosDoadores(), true, true, true, true);
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void gerarRecibos() throws PessoaInvalidaException, PessoaFisicaException, DocumentException, IOException, DoacaoInvalidaException{
		PessoaFisica isa = new PessoaFisica("Isabelly", "Jaguaruana", LocalDate.now(), "88996969696","88985858585", "isabelly@gmail.com", true, "06061487363", "20081547832", LocalDate.now());
		Doacao doa = new Doacao(1, 200.00, LocalDate.now(), true, isa);

		ReciboDoacaoFachada recibo = new ReciboDoacaoFachada();
		recibo.reciboDoadorFisico(doa);
		recibo.abrirPDF();

	}
	
	public static void gerarRelatorioMensal() throws DoacaoInvalidaException {
		try {
			RelatorioDoacaoFachada r = new RelatorioDoacaoFachada();
			r.relatorioDoacaoMensal(new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(),LocalDate.of(2017, 11, 1),LocalDate.of(2017, 11, 30));
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorioAnual() throws DoacaoInvalidaException {
		try {
			RelatorioDoacaoFachada r = new RelatorioDoacaoFachada();
			r.relatorioDoacaoAnual(new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(), LocalDate.now().getYear());
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void gerarRelatorioAnualPessoa() throws DoacaoInvalidaException {
		try {
			RelatorioAnualPessoaFachada relatorioAnualPessoaFachada = new RelatorioAnualPessoaFachada();
			relatorioAnualPessoaFachada.relatorioAnualPessoa(new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(101), LocalDate.of(2017, 1, 1).getYear());
			relatorioAnualPessoaFachada.abrirPDF();
			relatorioAnualPessoaFachada.salvarPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
