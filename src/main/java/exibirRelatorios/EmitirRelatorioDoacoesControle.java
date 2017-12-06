package exibirRelatorios;

import java.io.IOException;
import java.time.LocalDate;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;

import com.itextpdf.text.DocumentException;

import DAO.ConnectionFactory;
import DAO.DoacaoDAO;
import exceptions.DoacaoInvalidaException;
import relatorio.RelatorioDoacaoFachada;

public class EmitirRelatorioDoacoesControle {
	private EmitirRelatorioDoacoesView view;
	
	public EmitirRelatorioDoacoesControle(EmitirRelatorioDoacoesView selecionarDoadorRelatorioView) {
		setViewDoador(selecionarDoadorRelatorioView); 
	}

	public EmitirRelatorioDoacoesView getViewDoador() {
		return view;
	}

	public void setViewDoador(EmitirRelatorioDoacoesView viewDoador) {
		this.view = viewDoador;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cancelar}")) {
			view.getShlEmitirRelatrioMensal().dispose();
		}if(event.getSource().toString().equals("Button {Gerar relatório}")) {
			LocalDate dataInicio = LocalDate.of(view.getDataInicio().getYear(), view.getDataInicio().getMonth()+1, view.getDataInicio().getDay());
			LocalDate dataFim = LocalDate.of(view.getDataTermino().getYear(), view.getDataTermino().getMonth()+1, view.getDataTermino().getDay());
			try {
				gerarRelatorioMensal(dataInicio, dataFim);
				view.getShlEmitirRelatrioMensal().dispose();
			} catch (DoacaoInvalidaException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean verificarData(DateTime dataInicio, DateTime dataFim) {
		if(!(dataInicio.getYear() > dataFim.getYear())) {
			if(!(dataInicio.getMonth() > dataFim.getMonth())) {
				if(!(dataInicio.getDay() > dataFim.getDay())) {
					return true;
				}
			}
		}
		return false;
	}
		
	public static void gerarRelatorioMensal(LocalDate dataInicio, LocalDate dataFim) throws DoacaoInvalidaException {
		try {
			new RelatorioDoacaoFachada().relatorioDoacaoMensal(new DoacaoDAO(new ConnectionFactory().getConnection()).listarDoacoes(),dataInicio,dataFim);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DateTime inicio = new DateTime(null, 0);
		inicio.setYear(2016);
		inicio.setMonth(02);
		inicio.setDay(1);
		
		DateTime fim = new DateTime(null, 0);
		fim.setYear(2018);
		fim.setMonth(3);
		fim.setDay(7);
		verificarData(inicio, fim);
	}
}
