package control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import facade.DoacaoFachada;
import facade.DoadorFachada;
import facade.PessoaFachada;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import relatorio.ReciboDoacaoFachada;
import view.AplicacaoView;
import view.ApresentaPDFView;
import view.InserirValorView;
import view.SelecionarDoadorView;
import view.SelecionarTipoDoadorView;


public class DoacaoControle {
	private SelecionarDoadorView viewDoador;
	private InserirValorView viewValor;
	private DoacaoFachada fachadaDoacao = new DoacaoFachada();
	private DoadorFachada fachadaDoador = new DoadorFachada();
	private List<Pessoa> listaExibidaNaTabela;
	private List<Pessoa> listaTodosDoadores;
	private PessoaFachada pessoaFachada = new PessoaFachada();
	
	public DoacaoControle(SelecionarDoadorView viewDoador) {
		setViewDoador(viewDoador); 
	}
	
	public DoacaoControle(InserirValorView inserirValorView) {
		setViewValor(inserirValorView);
	}

	public SelecionarDoadorView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(SelecionarDoadorView viewDoador) {
		this.viewDoador = viewDoador;
	}

	public InserirValorView getViewValor() {
		return viewValor;
	}

	public void setViewValor(InserirValorView viewValor) {
		this.viewValor = viewValor;
	}

	public DoacaoFachada getFachada() {
		return fachadaDoacao;
	}
	
	public void setFachada(DoacaoFachada fachada) {
		this.fachadaDoacao = fachada;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cadastrar doador}")) {
			SelecionarTipoDoadorView.main();
		}
		if (event.getSource().toString().equals("Button {Continuar}")) {
			try {
				int idPessoa = listaExibidaNaTabela.get(viewDoador.getTable().getSelectionIndex()).getId();
				DoacaoSingleton.setDoador(pessoaFachada.obterPessoa(idPessoa));
				DoacaoSingleton.setCpfCNPJ(fachadaDoacao.obterCPFCNPJ(idPessoa));
				viewDoador.getShlDoacao().dispose();
				InserirValorView.main();
			} catch (ArrayIndexOutOfBoundsException e) {
				viewDoador.mensagemErro(new Exception("Selecione um doador para continuar"));
			}
		}
		if (event.getSource().toString().equals("Button {Salvar doação}")) {
			realizarDoacao();
		}if(event.getSource().toString().equals("Button {Pesquisar}")) {
			if(getViewDoador().getTfPesquisar().getText() == "") {
				preencherTabelaDoadores(obterTodosDoadores());
			}
			preencherTabelaDoadores(pesquisarDoadores(getViewDoador().getTfPesquisar().getText()));
		}
	}

	public void realizarDoacao() {
		try {
			DoacaoSingleton.setValor(Double.parseDouble(viewValor.getTfValor().getText()));
			DoacaoSingleton.setDataDoacao(LocalDate.of(viewValor.getDateTime_1().getYear(), viewValor.getDateTime_1().getMonth() + 1, viewValor.getDateTime_1().getDay()));
			if(fachadaDoacao.realizarDoacao(DoacaoSingleton.getDoacao())) {
				Pessoa p = fachadaDoador.obterDoadorFisico(DoacaoSingleton.getDoacao().getDoador().getId());
				if(p == null) {
					p = fachadaDoador.obterDoadorJuridico(DoacaoSingleton.getDoacao().getDoador().getId());
					viewValor.mensagemSucesso();
					viewValor.getShlRealizarDoao().dispose();
					ApresentaPDFView.main(new ReciboDoacaoFachada().reciboDoadorJuridico((PessoaJuridica)p, DoacaoSingleton.getDoacao()));
				}
				else {
					viewValor.mensagemSucesso();
					viewValor.getShlRealizarDoao().dispose();
					ApresentaPDFView.main(new ReciboDoacaoFachada().reciboDoadorFisico((PessoaFisica)p, DoacaoSingleton.getDoacao()));
				}
				
			}
		} catch(Exception e) {
			viewValor.mensagemErro(e);
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
	public List<Pessoa> obterTodosDoadores() {
		excluirLinhasDaTabela();
		listaTodosDoadores = fachadaDoador.getTodosDoadores();
		return listaTodosDoadores;
	}
	
	public void preencherTabelaDoadores(List<Pessoa> list) {
		excluirLinhasDaTabela();
		listaExibidaNaTabela = list;
		for(int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(viewDoador.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(i+1));
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
		viewDoador.getTable().removeAll();
	}
	
	public void identificarTelasEspeciais(KeyEvent evento) {
		if(evento.keyCode == 13 || evento.keyCode == 16777296) {
			realizarDoacao();
		} if(evento.keyCode == 27) {
			viewValor.getShlRealizarDoao().dispose();
		}
	}
}