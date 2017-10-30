package control;

import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import doacao.InserirValorView;
import doacao.SelecionarDoadorView;
import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import model.Doacao;


public class DoacaoControle {
	
	private SelecionarDoadorView view;
	private InserirValorView view2;
	private DoacaoFachada fachada;
	
	public DoacaoControle(SelecionarDoadorView view) {
		setView(view);
		setFachada(new DoacaoFachada());
	}
	
	public SelecionarDoadorView getView() {
		return view;
	}
	
	public void setView(SelecionarDoadorView view) {
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
				doacao.setData(LocalDate.of(view2.getTfDataDoacao().getYear(),view2.getTfDataDoacao().getMonth() + 1, view2.getTfDataDoacao().getDay()));
			}catch(DoacaoInvalidaException e) {
				view.mensagemErro(e);
			}
		}
	}
}
