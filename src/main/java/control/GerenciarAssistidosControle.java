package control;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import facade.AssistidoFachada;
import model.Assistido;
import view.CadastroAssistidoView;
import view.EditarAssistidoView;
import view.GerenciarAssistidosView;

public class GerenciarAssistidosControle {
	private GerenciarAssistidosView viewAssistido; 
	private AssistidoFachada fachadaAssistido;
	private ArrayList<Assistido> listaTodosAssistidos;
	private ArrayList<Assistido> listaExibidaNaTabela;
	
	public GerenciarAssistidosView getViewAssistido() {
		return viewAssistido;
	}

	public void setViewAssistido(GerenciarAssistidosView viewAssistido) {
		this.viewAssistido = viewAssistido;
	}

	public AssistidoFachada getFachadaAssistido() {
		return fachadaAssistido;
	}

	public void setFachadaAssistido(AssistidoFachada fachadaAssistido) {
		this.fachadaAssistido = fachadaAssistido;
	}
	
	public GerenciarAssistidosControle (GerenciarAssistidosView viewAssistido) {
		setViewAssistido(viewAssistido);
		setFachadaAssistido(new AssistidoFachada());
	}
	
	public GerenciarAssistidosControle () {
	}

	public void excluirLinhasDaTabela() {
		viewAssistido.getTable().removeAll();
	}
	
	public ArrayList<Assistido> obterTodosAssistidos() {
		excluirLinhasDaTabela();
		listaTodosAssistidos = fachadaAssistido.listarTodosAssistidos();
		return listaTodosAssistidos;
	}
	
	public void preencherTabelaAssistidos(ArrayList<Assistido> assistidos) {
		excluirLinhasDaTabela();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		listaExibidaNaTabela = assistidos;
		for(int i = 0; i < assistidos.size(); i++) {
			TableItem item = new TableItem(viewAssistido.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(assistidos.get(i).getId()));
			item.setText(1, assistidos.get(i).getNome());
			item.setText(2, assistidos.get(i).getDataNasc().format(formatador));
			item.setText(3, assistidos.get(i).getCpf());
		}	
	}
	
	public boolean confirmacao() {
        MessageBox messageBox = new MessageBox(viewAssistido.getShlGerenciarAssistidos(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
        messageBox.setText("Aviso!");
        messageBox.setMessage("Você deseja realmente deletar o assistido do sistema?");
        int buttonID = messageBox.open();
        switch(buttonID) {
          case SWT.CANCEL:
        	  return false;
          case SWT.OK:
            return true;
          default:
        	  return false;
        }
    }
	
	public ArrayList<Assistido> pesquisarAssistidos(String nomePesquisa) {
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
			preencherTabelaAssistidos(pesquisarAssistidos(viewAssistido.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Assistido}")) {
			Assistido assistido = fachadaAssistido.obterAssistido(listaExibidaNaTabela.get(viewAssistido.getTable().getSelectionIndex()).getId());
			this.viewAssistido.getShlGerenciarAssistidos().dispose();
			new EditarAssistidoView(assistido).open();
		}
		if(event.getSource().toString().equals("Button {Excluir Assistido}")) {
			if(confirmacao()) {
				fachadaAssistido.excluirAssistido(listaExibidaNaTabela.get(viewAssistido.getTable().getSelectionIndex()).getId());
				excluirLinhasDaTabela();
				preencherTabelaAssistidos(obterTodosAssistidos());
			}
		}
		if(event.getSource().toString().equals("Button {Cadastrar Assistido}")) {
			viewAssistido.getShlGerenciarAssistidos().dispose();
			CadastroAssistidoView.main();
			GerenciarAssistidosView.main();
		}
	}
}