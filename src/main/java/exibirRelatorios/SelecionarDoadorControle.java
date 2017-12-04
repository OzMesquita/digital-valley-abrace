package exibirRelatorios;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;

import com.itextpdf.text.DocumentException;

import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import facade.DoadorFachada;
import facade.PessoaFachada;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import relatorio.RelatorioAnualPessoaFachada;
import view.InserirValorView;
import view.SelecionarTipoDoadorView;

public class SelecionarDoadorControle {
	private SelecionarDoadorRelatorioView viewDoador;
	private InserirValorView viewValor;
	private DoacaoFachada fachadaDoacao = new DoacaoFachada();
	private DoadorFachada fachadaDoador = new DoadorFachada();
	private List<Pessoa> listaExibidaNaTabela;
	private List<Pessoa> listaTodosDoadores;
	private PessoaFachada pessoaFachada = new PessoaFachada();
	
	public SelecionarDoadorControle(SelecionarDoadorRelatorioView selecionarDoadorRelatorioView) {
		setViewDoador(selecionarDoadorRelatorioView); 
	}
	
	public SelecionarDoadorControle(InserirValorView inserirValorView) {
		setViewValor(inserirValorView);
	}

	public SelecionarDoadorRelatorioView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(SelecionarDoadorRelatorioView viewDoador) {
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
				EmitirRelatorioSingleton.setDoador(pessoaFachada.obterPessoa(idPessoa));
				EmitirRelatorioSingleton.setCpfCNPJ(fachadaDoacao.obterCPFCNPJ(idPessoa));
				viewDoador.getShlDoacao().dispose();
				gerarRelatorioAnualPessoa(pessoaFachada.obterPessoa(idPessoa), 2017);
			} catch (ArrayIndexOutOfBoundsException e) {
				viewDoador.mensagemErro(new Exception("Selecione um doador para continuar"));
			} catch (DoacaoInvalidaException e) {
				e.printStackTrace();
			}
		}if(event.getSource().toString().equals("Button {Pesquisar}")) {
			if(getViewDoador().getTfPesquisar().getText() == "") {
				preencherTabelaDoadores(obterTodosDoadores());
			}
			preencherTabelaDoadores(pesquisarDoadores(getViewDoador().getTfPesquisar().getText()));
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
		if(evento.keyCode == 27) {
			viewValor.getShlRealizarDoao().dispose();
		}
	}
	
	public static void gerarRelatorioAnualPessoa(Pessoa pessoa, int ano) throws DoacaoInvalidaException {
		try {
			RelatorioAnualPessoaFachada relatorioAnualPessoaFachada = new RelatorioAnualPessoaFachada();
			relatorioAnualPessoaFachada.relatorioAnualPessoa(new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(pessoa.getId()), ano);
			relatorioAnualPessoaFachada.abrirPDF();
			relatorioAnualPessoaFachada.salvarPDF();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
