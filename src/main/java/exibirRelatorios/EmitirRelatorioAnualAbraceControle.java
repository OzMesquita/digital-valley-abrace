package exibirRelatorios;

import java.io.IOException;

import org.eclipse.swt.events.SelectionEvent;

import com.itextpdf.text.DocumentException;

import DAO.ConnectionFactory;
import DAO.DoacaoDAO;
import exceptions.DoacaoInvalidaException;
import relatorio.RelatorioDoacaoFachada;

public class EmitirRelatorioAnualAbraceControle {
	private EmitirRelatorioAnualAbraceView view;
	
	public EmitirRelatorioAnualAbraceControle(EmitirRelatorioAnualAbraceView view) {
		setViewDoador(view); 
	}

	public EmitirRelatorioAnualAbraceView getViewDoador() {
		return view;
	}

	public void setViewDoador(EmitirRelatorioAnualAbraceView view) {
		this.view = view;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cancelar}")) {
			view.getShlEmitirRelatrioDe().dispose();
		}
		if (event.getSource().toString().equals("Button {Gerar relatório}")) {
			try {
				gerarRelatorioAnual(Integer.parseInt(view.getTfAno().getText()));
			} catch (NumberFormatException e) {
				view.mensagemErro(new Exception("Informe o ano para continuar."));
			} catch (DoacaoInvalidaException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void gerarRelatorioAnual(int ano) throws DoacaoInvalidaException {
		try {
			RelatorioDoacaoFachada r = new RelatorioDoacaoFachada();
			r.relatorioDoacaoAnual(new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(), ano);
			r.abrirPDF();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
