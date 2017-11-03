package control;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;

import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import model.Doacao;
import view.GerenciarDoacoesView;

public class GerenciarDoacoesControle {
	
	private GerenciarDoacoesView view;
	private DoacaoFachada fachada;
	private ArrayList<Doacao> listarTodasDoacoes;
	private ArrayList<Doacao> listaExibidaNaTabela;
	
	public GerenciarDoacoesControle(GerenciarDoacoesView view) {
		this.setView(view);
	}
	
	public GerenciarDoacoesControle() {
		
	}

	public GerenciarDoacoesView getView() {
		return view;
	}

	public void setView(GerenciarDoacoesView view) {
		this.view = view;
	}

	public DoacaoFachada getFachada() {
		return fachada;
	}

	public void setFachada(DoacaoFachada fachada) {
		this.fachada = fachada;
	}

	public ArrayList<Doacao> getListarTodasDoacoes() {
		return listarTodasDoacoes;
	}

	public void setListarTodasDoacoes(ArrayList<Doacao> listarTodasDoacoes) {
		this.listarTodasDoacoes = listarTodasDoacoes;
	}

	public ArrayList<Doacao> getListaExibidaNaTabela() {
		return listaExibidaNaTabela;
	}

	public void setListaExibidaNaTabela(ArrayList<Doacao> listaExibidaNaTabela) {
		this.listaExibidaNaTabela = listaExibidaNaTabela;
	}
	
	public void excluirLinhasDaTabela() {
		view.getTable().removeAll();
	}
	
	
	public void preencherTabelaDoacoes(ArrayList<Doacao> doacoes) {
		
	}
	
	public boolean confirmacao() {
        MessageBox messageBox = new MessageBox(view.getShlGerenciarDoacoes(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
        
        messageBox.setText("Aviso!");
        messageBox.setMessage("Você deseja realmente deletar essa doação do sistema?");
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
	
	public ArrayList<Doacao> obterTodasDoacoes() {
		excluirLinhasDaTabela();
		listarTodasDoacoes = fachada.listarDoacoes();
		return listarTodasDoacoes;
	}
	
	public ArrayList<Doacao> pesquisarDoacoes(String nomePesquisa){
		excluirLinhasDaTabela();
		listarTodasDoacoes = fachada.listarDoacoes();
		return listarTodasDoacoes;
	}

	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoacoes(pesquisarDoacoes(view.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Doação}")) {
			//Editar doação
		}
		if(event.getSource().toString().equals("Button {Excluir Doação}")) {
			if(confirmacao()) {
				try {
					fachada.excluirDoacao(listaExibidaNaTabela.get(view.getTable().getSelectionIndex()).getId());
					excluirLinhasDaTabela();
					preencherTabelaDoacoes(obterTodasDoacoes());
				} catch (DoacaoInvalidaException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
