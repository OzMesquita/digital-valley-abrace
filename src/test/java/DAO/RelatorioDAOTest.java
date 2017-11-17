package DAO;

import java.io.IOException;
import java.time.LocalDate;

import com.itextpdf.text.DocumentException;

import exceptions.DoacaoInvalidaException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.Doacao;
import model.PessoaFisica;
import relatorio.ReciboDoacaoFachada;
import relatorio.RelatorioDoadoresFacade;

public class RelatorioDAOTest {


	public static void main(String[] args) throws DocumentException, IOException, PessoaInvalidaException, PessoaFisicaException, DoacaoInvalidaException {
//		try {
//			new RelatorioAssistidoFacade().relatorioDeAssistido(new AssistidoDAO(new ConnectionFactory().getConnection()).listaAssistido(),false,true);
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		PessoaFisica isa = new PessoaFisica("Isabelly", "Jaguaruana", LocalDate.now(), "88996969696","88985858585", "isabelly@gmail.com", true, "06061487363", "20081547832", LocalDate.now());
		Doacao doa = new Doacao(1, 200.00, LocalDate.now(), true, isa);
		
		ReciboDoacaoFachada recibo = new ReciboDoacaoFachada();
		//recibo.reciboDoadorFisico(isa, doa);
		
		//TESTES RELATORIO DE DOADORES
		DoadorFachada doadores = new DoadorFachada();
		try {
			new RelatorioDoadoresFacade().relatorioDeDoadores(doadores.getTodosDoadores(), true, true, true, true);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
