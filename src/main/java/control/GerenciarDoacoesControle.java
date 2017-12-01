package control;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import model.Doacao;
import relatorio.Extenso;
import view.GerenciarDoacoesView;
import view.SelecionarDoadorView;

public class GerenciarDoacoesControle {
	
	private GerenciarDoacoesView viewDoacoes;
	private DoacaoFachada fachadaDoacoes;
	private ArrayList<Doacao> listarTodasDoacoes;
	private ArrayList<Doacao> listaExibidaNaTabela;
	
	public GerenciarDoacoesControle(GerenciarDoacoesView gerenciarDoacoesView) {
		this.setViewDoacoes(gerenciarDoacoesView);
		this.fachadaDoacoes = new DoacaoFachada();
	}
	
	public GerenciarDoacoesControle() {
		
	}

	public GerenciarDoacoesView getViewDoacoes() {
		return viewDoacoes;
	}

	public void setViewDoacoes(GerenciarDoacoesView viewDoacoes) {
		this.viewDoacoes = viewDoacoes;
	}

	public DoacaoFachada getFachadaDoacoes() {
		return fachadaDoacoes;
	}

	public void setFachadaDoacoes(DoacaoFachada fachada) {
		this.fachadaDoacoes = fachada;
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
		viewDoacoes.getTable().removeAll();
	}

	public void preencherTabelaDoacoes(ArrayList<Doacao> doacoes) {
		excluirLinhasDaTabela();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		listaExibidaNaTabela = doacoes;
		for(int i = 0; i < doacoes.size(); i++) {
			TableItem item = new TableItem(viewDoacoes.getTable(), SWT.NONE);
			item.setText(0, doacoes.get(i).getDoador().getNome());
			item.setText(1, new Extenso(new BigDecimal(doacoes.get(i).getValor())).DecimalFormat().trim());
			item.setText(2, doacoes.get(i).getData().format(formatador));
		}
		viewDoacoes.setLblTotalDeDoaes("Total de doações: " + new Extenso(new BigDecimal(somarTotalExibido())).DecimalFormat().trim());
	}
	
	public double somarTotalExibido() {
		double total = 0;
		for(int i = 0; i < listaExibidaNaTabela.size(); i++) {
			total += listaExibidaNaTabela.get(i).getValor();
		}
		return total;
	}
	
	public boolean confirmacao() {
        MessageBox messageBox = new MessageBox(viewDoacoes.getShlGerenciarDoacoes(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
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
		listarTodasDoacoes = fachadaDoacoes.listarDoacoes();
		return listarTodasDoacoes;
	}
	
	public ArrayList<Doacao> pesquisarDoacoes(String nomePesquisa){
		excluirLinhasDaTabela();
		ArrayList<Doacao> doacoes = new ArrayList<Doacao>();
		for(int i = 0; i < listarTodasDoacoes.size(); i++) {
			if(listarTodasDoacoes.get(i).getDoador().getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				doacoes.add(listarTodasDoacoes.get(i));
			}
		}
		return doacoes;
	}
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoacoes(pesquisarDoacoes(viewDoacoes.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Realizar Doação}")) {
			viewDoacoes.getShlGerenciarDoacoes().dispose();
			SelecionarDoadorView.main();
			GerenciarDoacoesView.main();
		}
		if(event.getSource().toString().equals("Button {Excluir Doação}")) {
			if(confirmacao()) {
				try {
					fachadaDoacoes.excluirDoacao(listaExibidaNaTabela.get(viewDoacoes.getTable().getSelectionIndex()).getId());
					excluirLinhasDaTabela();
					preencherTabelaDoacoes(obterTodasDoacoes());
				} catch (DoacaoInvalidaException e) {
					e.printStackTrace();
				}
			}
		}
	}
}