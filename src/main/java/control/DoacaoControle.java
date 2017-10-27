package control;

import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import model.Doacao;
import view.DoacaoView;


public class DoacaoControle {
	
	private DoacaoView view;
	private DoacaoFachada fachada;
	
	public DoacaoControle(DoacaoView view) {
		setView(view);
		setFachada(new DoacaoFachada());
	}
	
	public DoacaoView getView() {
		return view;
	}
	
	public void setView(DoacaoView view) {
		this.view = view;
	}
	
	public DoacaoFachada getFachada() {
		return fachada;
	}
	
	public void setFachada(DoacaoFachada fachada) {
		this.fachada = fachada;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Realizar doação}")) {
			Doacao doacao = new Doacao();
	
			try {
				doacao.setId(view.getDoacao().getId());
				doacao.setDoador(view.getDoacao().getDoador());
				doacao.setValor(view.getDoacao().getValor());
				doacao.setData(LocalDate.of(view.getTfDataDoacao().getYear(),view.getTfDataDoacao().getMonth() + 1, view.getTfDataDoacao().getDay()));
			}catch(DoacaoInvalidaException e) {
				view.mensagemErro(e);
			}
		}
	}
}
