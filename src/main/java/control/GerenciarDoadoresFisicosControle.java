package control;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import facade.DoadorFachada;
import model.PessoaFisica;
import view.EditarDoadorPFView;
import view.GerenciarDoadoresFisicosView;

public class GerenciarDoadoresFisicosControle {
	private GerenciarDoadoresFisicosView view; 
	private DoadorFachada fachada;
	private ArrayList<PessoaFisica> listaTodosDoadoresFisicos;
	private ArrayList<PessoaFisica> listaExibidaNaTabela;
	
	public GerenciarDoadoresFisicosView getView() {
		return view;
	}


	public void setView(GerenciarDoadoresFisicosView view) {
		this.view = view;
	}


	public DoadorFachada getFachada() {
		return fachada;
	}


	public void setFachada(DoadorFachada fachada) {
		this.fachada = fachada;
	}
	
	public GerenciarDoadoresFisicosControle (GerenciarDoadoresFisicosView view) {
		setView(view);
		setFachada(new DoadorFachada());
		
	}
	
	public GerenciarDoadoresFisicosControle () {
	}

	public void excluirLinhasDaTabela() {
		view.getTable().removeAll();
	}
	
	public ArrayList<PessoaFisica> obterTodosDoadoresFisicos() {
		excluirLinhasDaTabela();
		listaTodosDoadoresFisicos = fachada.listarPessoaFisica();
		return listaTodosDoadoresFisicos;
	}
	
	public void preencherTabelaDoadoresFisicos(ArrayList<PessoaFisica> doadores) {
		excluirLinhasDaTabela();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		listaExibidaNaTabela = doadores;
		for(int i = 0; i < doadores.size(); i++) {
			TableItem item = new TableItem(view.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(doadores.get(i).getId()));
			item.setText(1, doadores.get(i).getNome());
			item.setText(2, doadores.get(i).getDataNasc().format(formatador));
			item.setText(3, doadores.get(i).getCpf());
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
	
	public ArrayList<PessoaFisica> pesquisarAssistidos(String nomePesquisa) {
		excluirLinhasDaTabela();
		ArrayList<PessoaFisica> listaPesquisaDoadoresFisicos = new ArrayList<PessoaFisica>();
		
		for(int i = 0; i < listaTodosDoadoresFisicos.size(); i++) {
			if(listaTodosDoadoresFisicos.get(i).getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				listaPesquisaDoadoresFisicos.add(listaTodosDoadoresFisicos.get(i));
			}
		}
		return listaPesquisaDoadoresFisicos;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoadoresFisicos(pesquisarAssistidos(view.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Doador}")) {
			PessoaFisica a = fachada.obterDoadorFisico(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
			this.view.getShlGerenciarDoadoresFisicos().dispose();
			new EditarDoadorPFView(a).open();
		}
		if(event.getSource().toString().equals("Button {Excluir Doador}")) {
			if(confirmacao()) {
				fachada.excluirDoadorFisico(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
				excluirLinhasDaTabela();
				preencherTabelaDoadoresFisicos(obterTodosDoadoresFisicos());
			}
		}
	}
}