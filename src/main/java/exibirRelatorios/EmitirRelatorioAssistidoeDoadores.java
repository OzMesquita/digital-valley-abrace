package exibirRelatorios;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import facade.DoadorFachada;
import relatorio.RelatorioAssistidoFacade;
import relatorio.RelatorioDoadoresFacade;

public class EmitirRelatorioAssistidoeDoadores {
	
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
			r.relatorioDeDoadores(doadores.getTodosDoadores(), true, true);
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
