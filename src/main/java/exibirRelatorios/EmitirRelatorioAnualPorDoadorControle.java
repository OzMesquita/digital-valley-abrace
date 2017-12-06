package exibirRelatorios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import com.itextpdf.text.DocumentException;

import DAO.ConnectionFactory;
import DAO.PessoaFisicaDAO;
import DAO.PessoaJuridicaDAO;
import exceptions.DoacaoInvalidaException;
import facade.DoacaoFachada;
import facade.DoadorFachada;
import facade.PessoaFachada;
import model.Pessoa;
import model.PessoaFisica;
import model.PessoaJuridica;
import relatorio.RelatorioAnualPessoaFachada;

public class EmitirRelatorioAnualPorDoadorControle {
	private EmitirRelatorioAnualPorDoadorView viewDoador;
	private DoacaoFachada fachadaDoacao = new DoacaoFachada();
	private DoadorFachada fachadaDoador = new DoadorFachada();
	private List<Pessoa> listaExibidaNaTabela;
	private List<Pessoa> listaTodosDoadores;
	private PessoaFachada pessoaFachada = new PessoaFachada();
	
	public EmitirRelatorioAnualPorDoadorControle(EmitirRelatorioAnualPorDoadorView selecionarDoadorRelatorioView) {
		setViewDoador(selecionarDoadorRelatorioView); 
	}

	public EmitirRelatorioAnualPorDoadorView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(EmitirRelatorioAnualPorDoadorView viewDoador) {
		this.viewDoador = viewDoador;
	}

	public DoacaoFachada getFachada() {
		return fachadaDoacao;
	}
	
	public void setFachada(DoacaoFachada fachada) {
		this.fachadaDoacao = fachada;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Continuar}")) {
			try {
				int idPessoa = listaExibidaNaTabela.get(viewDoador.getTable().getSelectionIndex()).getId();
				gerarRelatorioAnualPessoa(pessoaFachada.obterPessoa(idPessoa), Integer.parseInt(viewDoador.getTfAno().getText()));
				viewDoador.getShlDoacao().dispose();
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
	
	public static void gerarRelatorioAnualPessoa(Pessoa pessoa, int ano) throws DoacaoInvalidaException {
		try {
			RelatorioAnualPessoaFachada relatorioAnualPessoaFachada = new RelatorioAnualPessoaFachada();
			
			PessoaJuridica pj = new PessoaJuridicaDAO(new ConnectionFactory().getConnection()).getPessoaJuridica(pessoa.getId());
			
			if(pj == null){
				relatorioAnualPessoaFachada.relatorioAnualPessoa(new PessoaFisicaDAO(new ConnectionFactory().getConnection()).getPessoaFisica(pessoa.getId()), ano);
			} else {
				relatorioAnualPessoaFachada.relatorioAnualPessoa(pj, ano);
			}
			
			relatorioAnualPessoaFachada.abrirPDF();
			relatorioAnualPessoaFachada.salvarPDF();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void filtrarAno(KeyEvent evt) {
		String k = viewDoador.getTfAno().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789".contains(new String(caractere)))
				j += k.charAt(i);
		}
		String temp = new String();
		for (int i = 0; i < j.length(); i++) {
			if (i < 4) {
				temp += j.charAt(i);
			}
		}
		viewDoador.setTfAno(temp);
		viewDoador.getTfAno().setSelection(viewDoador.getTfAno().getText().length());
	}
}
