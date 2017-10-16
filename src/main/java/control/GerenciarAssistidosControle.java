package control;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import facade.AssistidoFachada;
import model.Assistido;
import view.EditarAssistidoView;
import view.GerenciarAssistidosView;

public class GerenciarAssistidosControle {
	private GerenciarAssistidosView view; 
	private AssistidoFachada fachada;
	private ArrayList<Assistido> listaTodosAssistidos;
	private ArrayList<Assistido> listaExibidaNaTabela;
	
	public GerenciarAssistidosView getView() {
		return view;
	}


	public void setView(GerenciarAssistidosView view) {
		this.view = view;
	}


	public AssistidoFachada getFachada() {
		return fachada;
	}


	public void setFachada(AssistidoFachada fachada) {
		this.fachada = fachada;
	}
	
	public GerenciarAssistidosControle (GerenciarAssistidosView view) {
		setView(view);
		setFachada(new AssistidoFachada());
		
	}
	
	public GerenciarAssistidosControle () {
	}

	public void excluirLinhasDaTabela() {
		for (int i = 0; i < view.getTable().getItems().length; i++) {
			view.getTable().remove(i);
		}
	}
	
	public ArrayList<Assistido> obterTodosAssistidos() {
		excluirLinhasDaTabela();
		listaTodosAssistidos = fachada.listarTodosAssistidos();
		return listaTodosAssistidos;
	}
	
	public void preencherTabelaAssistidos(ArrayList<Assistido> assistidos) {
		excluirLinhasDaTabela();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		listaExibidaNaTabela = assistidos;
		for(int i = 0; i < assistidos.size(); i++) {
			TableItem item = new TableItem(view.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(assistidos.get(i).getId()));
			item.setText(1, assistidos.get(i).getNome());
			item.setText(2, assistidos.get(i).getDataNasc().format(formatador));
			item.setText(3, assistidos.get(i).getCpf());
		}	
	}
	
	public ArrayList<Assistido> pesquisarAssistidos(ArrayList<Assistido> listaTodosAssistidos, String nomePesquisa) {
		excluirLinhasDaTabela();
		ArrayList<Assistido> listaPesquisaAssistidos = new ArrayList<Assistido>();
		
		for(int i = 0; i < listaTodosAssistidos.size(); i++) {
			if(listaTodosAssistidos.get(i).getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				listaPesquisaAssistidos.add(listaTodosAssistidos.get(i));
			}
		}
		return listaPesquisaAssistidos;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaAssistidos(pesquisarAssistidos(listaTodosAssistidos, view.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Assistido}")) {
			Assistido a = fachada.obterAssistido(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
			EditarAssistidoView novaView = new EditarAssistidoView(a);
		}
	}
}