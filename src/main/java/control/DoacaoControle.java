package control;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import doacao.DoacaoSingleton;
import doacao.InserirValorView;
import doacao.SelecionarDoadorView;
import facade.DoacaoFachada;
import facade.DoadorFachada;
import facade.PessoaFachada;
import model.Doacao;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;


public class DoacaoControle {
	
	private SelecionarDoadorView view1;
	private InserirValorView view2;
	private DoacaoFachada fachada;
	private DoadorFachada doadorFachada = new DoadorFachada();
	private List<Pessoa> listaExibidaNaTabela;
	private List<Pessoa> listaTodosDoadores;
	private PessoaFachada pessoaFachada = new PessoaFachada();
	
	public DoacaoControle(SelecionarDoadorView view1) {
		setView1(view1); 
	}
	
	public SelecionarDoadorView getView1() {
		return view1;
	}

	public void setView1(SelecionarDoadorView view1) {
		this.view1 = view1;
	}

	public InserirValorView getView2() {
		return view2;
	}

	public void setView2(InserirValorView view2) {
		this.view2 = view2;
	}

	public DoacaoFachada getFachada() {
		return fachada;
	}
	
	public void setFachada(DoacaoFachada fachada) {
		this.fachada = fachada;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cadastrar doador}")) {
			//Criar tela para linkar com o cadastro de doador pessoa fisica ou juridica
		}
		if (event.getSource().toString().equals("Button {Continuar}")) {
			pessoaFachada.obterPessoa(403);
			
			DoacaoSingleton.setDoador(pessoaFachada.obterPessoa(listaExibidaNaTabela.get(view1.getTable().getSelectionIndex()).getId()));
			view1.getShlDoacao().dispose();
			InserirValorView.main();
		}
		if (event.getSource().toString().equals("Button {Salvar doação}")) {
			DoacaoSingleton.setValor(Double.parseDouble(view2.getTfValor().getText()));
			DoacaoSingleton.setDataDoacao(LocalDate.of(view2.getDateTime_1().getYear(), view2.getDateTime_1().getMonth() + 1, view2.getDateTime_1().getDay()));
			
			if(fachada.realizarDoacao(DoacaoSingleton.getDoacao())) {
				
			}
		}
	}
	
	public List<Pessoa> obterTodosDoadores() {
		excluirLinhasDaTabela();
		listaTodosDoadores = doadorFachada.getTodosDoadores();
		return listaTodosDoadores;
	}
	
	public void preencherTabelaDoadores(List<Pessoa> list) {
		excluirLinhasDaTabela();
		listaExibidaNaTabela = list;
		for(int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(view1.getTable(), SWT.NONE);
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
	
	public List<Pessoa> getListaExibidaNaTabela() {
		return listaExibidaNaTabela;
	}

	public void setListaExibidaNaTabela(List<Pessoa> listaExibidaNaTabela) {
		this.listaExibidaNaTabela = listaExibidaNaTabela;
	}

	public void excluirLinhasDaTabela() {
		view1.getTable().removeAll();
	}
}
