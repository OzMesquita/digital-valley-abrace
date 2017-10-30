package control;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import facade.DoadorFachada;
import model.PessoaJuridica;
import view.EditarDoadorJuridicoView;
import view.GerenciarDoadoresJuridicosView;

public class GerenciarDoadoresJuridicosControle {
	private GerenciarDoadoresJuridicosView view;
	private DoadorFachada fachada;
	private ArrayList<PessoaJuridica> listaTodosDoadoresJuridicos;
	private ArrayList<PessoaJuridica> listaExibidaNaTabela;
	
	public GerenciarDoadoresJuridicosControle(GerenciarDoadoresJuridicosView view) {
		this.setView(view);
	}
	
	public void excluirLinhasDaTabela() {
		view.getTable().removeAll();
	}
	
	public ArrayList<PessoaJuridica> obterTodosDoadoresJuridicos() {
		excluirLinhasDaTabela();
		listaTodosDoadoresJuridicos = fachada.listarPessoaJuridica();
		return listaTodosDoadoresJuridicos;
	}
	
	public DoadorFachada getFachada() {
		return fachada;
	}


	public void setFachada(DoadorFachada fachada) {
		this.fachada = fachada;
	}
	
	private void setView(GerenciarDoadoresJuridicosView view) {
		if(view != null) {
			this.view = view;
		}
	}

	public void preencherTabelaDoadoresJuridico(ArrayList<PessoaJuridica> doadores) {
		excluirLinhasDaTabela();
		listaExibidaNaTabela = doadores;
		for(int i = 0; i < doadores.size(); i++) {
			TableItem item = new TableItem(view.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(doadores.get(i).getId()));
			item.setText(1, doadores.get(i).getNome());
			item.setText(2, doadores.get(i).getCnpj());
			item.setText(3, doadores.get(i).getNomeFantasia());
		}	
	}
	
	public boolean confirmacao() {
        MessageBox messageBox = new MessageBox(view.getShlGerenciarDoadoresJuridicos(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
        
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
	
	public ArrayList<PessoaJuridica> pesquisarDoadores(String nomePesquisa) {
		excluirLinhasDaTabela();
		ArrayList<PessoaJuridica> listaPesquisaDoadoresJuridicos = new ArrayList<PessoaJuridica>();
		
		for(int i = 0; i < listaTodosDoadoresJuridicos.size(); i++) {
			if(listaTodosDoadoresJuridicos.get(i).getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				listaPesquisaDoadoresJuridicos.add(listaTodosDoadoresJuridicos.get(i));
			}
		}
		return listaPesquisaDoadoresJuridicos;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoadoresJuridico(pesquisarDoadores(view.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Doador}")) {
			PessoaJuridica a = fachada.obterDoadorJuridico(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
			this.view.getShlGerenciarDoadoresJuridicos().dispose();
			new EditarDoadorJuridicoView(a).open();
		}
		if(event.getSource().toString().equals("Button {Excluir Doador}")) {
			if(confirmacao()) {
				fachada.excluirDoadorJuridico(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
				excluirLinhasDaTabela();
				preencherTabelaDoadoresJuridico(obterTodosDoadoresJuridicos());
			}
		}
	}

}
