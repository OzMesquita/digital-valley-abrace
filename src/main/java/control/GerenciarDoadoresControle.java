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
import view.EditarDoadorPJView;
import view.EditarDoadorPFView;
import view.GerenciarDoadoresView;

public class GerenciarDoadoresControle {
	
	private GerenciarDoadoresView viewDoadores; 
	private DoadorFachada fachadaDoadores;
	private List<Pessoa> listaTodosDoadores;
	private List<Pessoa> listaExibidaNaTabela;
	
	public GerenciarDoadoresView getViewDoadores() {
		return viewDoadores;
	}

	public void setViewDoadores(GerenciarDoadoresView viewDoadores) {
		this.viewDoadores = viewDoadores;
	}

	public DoadorFachada getFachadaDoadores() {
		return fachadaDoadores;
	}

	public void setFachadaDoadores(DoadorFachada fachadaDoadores) {
		this.fachadaDoadores = fachadaDoadores;
	}
	
	public GerenciarDoadoresControle (GerenciarDoadoresView viewDoadores) {
		setViewDoadores(viewDoadores);
		setFachadaDoadores(new DoadorFachada());
	}
	
	public GerenciarDoadoresControle () {
	}

	public void excluirLinhasDaTabela() {
		viewDoadores.getTable().removeAll();
	}
	
	public List<Pessoa> obterTodosDoadores() {
		excluirLinhasDaTabela();
		listaTodosDoadores = fachadaDoadores.getTodosDoadores();
		return listaTodosDoadores;
	}
	
	public void preencherTabelaDoadores(List<Pessoa> list) {
		excluirLinhasDaTabela();
		listaExibidaNaTabela = list;
		for(int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(viewDoadores.getTable(), SWT.NONE);
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
        MessageBox messageBox = new MessageBox(viewDoadores.getShlGerenciarDoadoresFisicos(),SWT.ICON_WARNING | SWT.CANCEL | SWT.OK);
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
	
	public List<Pessoa> pesquisarDoadores(String nomePesquisa) {
		excluirLinhasDaTabela();
		List<Pessoa> listaPesquisaDoadoresFisicos = new ArrayList<Pessoa>();
		for(int i = 0; i < listaTodosDoadores.size(); i++) {
			if(listaTodosDoadores.get(i).getNome().toLowerCase().contains(nomePesquisa.toLowerCase())) {
				listaPesquisaDoadoresFisicos.add(listaTodosDoadores.get(i));
			}
		}
		return listaPesquisaDoadoresFisicos;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Pesquisar}")) {
			preencherTabelaDoadores(pesquisarDoadores(viewDoadores.getTfPesquisa().getText()));
		}
		if (event.getSource().toString().equals("Button {Editar Doador}")) {
			PessoaFisica pessoaFisica = fachadaDoadores.obterDoadorFisico(listaExibidaNaTabela.get(viewDoadores.getTable().getSelectionIndex()).getId());
			if(pessoaFisica == null) {
				PessoaJuridica pessoaJuridica = fachadaDoadores.obterDoadorJuridico(listaExibidaNaTabela.get(viewDoadores.getTable().getSelectionIndex()).getId());
				viewDoadores.getShlGerenciarDoadoresFisicos().dispose();
				EditarDoadorPJView.main(pessoaJuridica);
			}else {
				viewDoadores.getShlGerenciarDoadoresFisicos().dispose();
				EditarDoadorPFView.main(pessoaFisica);
			}
		}
		if(event.getSource().toString().equals("Button {Excluir Doador}")) {
			if(confirmacao()) {
				fachadaDoadores.excluirDoador(listaExibidaNaTabela.get(viewDoadores.getTable().getSelectionIndex()).getId());
				excluirLinhasDaTabela();
				preencherTabelaDoadores(obterTodosDoadores());
			}
		}
	}
}