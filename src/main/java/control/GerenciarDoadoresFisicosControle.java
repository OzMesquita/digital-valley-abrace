package control;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import facade.DoadorFachada;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import view.EditarDoadorPFView;
import view.GerenciarDoadoresView;

public class GerenciarDoadoresFisicosControle {
	private GerenciarDoadoresView view; 
	private DoadorFachada fachada;
	private List<Pessoa> listaTodosDoadoresFisicos;
	private List<Pessoa> listaExibidaNaTabela;
	
	public GerenciarDoadoresView getView() {
		return view;
	}


	public void setView(GerenciarDoadoresView view) {
		this.view = view;
	}


	public DoadorFachada getFachada() {
		return fachada;
	}


	public void setFachada(DoadorFachada fachada) {
		this.fachada = fachada;
	}
	
	public GerenciarDoadoresFisicosControle (GerenciarDoadoresView view) {
		setView(view);
		setFachada(new DoadorFachada());
		
	}
	
	public GerenciarDoadoresFisicosControle () {
	}

	public void excluirLinhasDaTabela() {
		view.getTable().removeAll();
	}
	
	public List<Pessoa> obterTodosDoadoresFisicos() {
		excluirLinhasDaTabela();
		listaTodosDoadoresFisicos = fachada.getTodosDoadores();
		return listaTodosDoadoresFisicos;
	}
	
	public void preencherTabelaDoadores(List<Pessoa> list) {
		excluirLinhasDaTabela();
		listaExibidaNaTabela = list;
		for(int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(view.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(list.get(i).getId()));
			item.setText(1, list.get(i).getNome());
			if(list.get(i) instanceof PessoaFisica) {
				item.setText(2, ((PessoaFisica)list.get(i)).getCpf());
			}else {
				item.setText(2, ((PessoaJuridica)list.get(i)).getCnpj());	
			}
			item.setText(3, list.get(i).getEndereco());
		}	
	}
	
	public boolean confirmacao() {
        MessageBox messageBox = new MessageBox(view.getShlGerenciarDoadoresFisicos(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
        
        messageBox.setText("Aviso!");
        messageBox.setMessage("Você deseja realmente deletar esse doador do sistema?");
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
	
	public List<Pessoa> pesquisarAssistidos(String nomePesquisa) {
		excluirLinhasDaTabela();
		List<Pessoa> listaPesquisaDoadoresFisicos = new ArrayList<Pessoa>();
		
		for(int i = 0; i < listaTodosDoadoresFisicos.size(); i++) {
			if(listaTodosDoadoresFisicos.get(i).getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				listaPesquisaDoadoresFisicos.add(listaTodosDoadoresFisicos.get(i));
			}
		}
		return listaPesquisaDoadoresFisicos;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoadores(pesquisarAssistidos(view.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Doador}")) {
			PessoaFisica a = fachada.obterDoadorFisico(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
			this.view.getShlGerenciarDoadoresFisicos().dispose();
			new EditarDoadorPFView().main(a);
		}
		if(event.getSource().toString().equals("Button {Excluir Doador}")) {
			if(confirmacao()) {
				fachada.excluirDoador(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
				excluirLinhasDaTabela();
				preencherTabelaDoadores(obterTodosDoadoresFisicos());
			}
		}
	}
}