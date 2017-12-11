package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.CadastroDoadorPFisicaView;

public class CadastroDoadorPFControle extends Controle {
	private CadastroDoadorPFisicaView viewDoador;
	private DoadorFachada fachadaDoador; 
	
	public CadastroDoadorPFisicaView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(CadastroDoadorPFisicaView viewDoador) {
		this.viewDoador = viewDoador;
	}

	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cadastrar}")){
			try {
				PessoaFisica doador = new PessoaFisica();
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCpf(viewDoador.getTfCPF().getText());
				doador.setRg(viewDoador.getTfRG().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(viewDoador.getTfDataNascimento().getYear(),viewDoador.getTfDataNascimento().getMonth() + 1, viewDoador.getTfDataNascimento().getDay()));
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setDoador(true);
				if(fachadaDoador.cadastrarDoadorFisico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlCadastroDoador().dispose();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewDoador.mensagemErro(e);
			} catch (SQLException e) {
				if(e.getMessage().contains("CPF já existente")) {
					viewDoador.mensagemErro(e);
				}
				else {
					viewDoador.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}
		}
	}
	
	public void getFocus(FocusEvent arg0) {
		if (viewDoador.getTfCPF().getText() == "") {
			return;
		} else {
			try {
				if (fachadaDoador.verificaCPF(viewDoador.getTfCPF().getText())) {
					List<PessoaFisica> lista = fachadaDoador.listarTabelaPessoasFisicas();
					for (PessoaFisica pessoa : lista) {
						if (pessoa.getCpf().equals(viewDoador.getTfCPF().getText())) {
							if (! (pessoa.isAtivo())) {
								if(viewDoador.reativarDoador(pessoa)) {
									fachadaDoador.ativaDoador(pessoa.getId());
									viewDoador.mensagemSucesso(fachadaDoador.obterDoadorFisico(pessoa.getId()));
									viewDoador.getShlCadastroDoador().dispose();
								}
							}else {
								viewDoador.mensagemCPFJaCadastrado(new Exception("CPF informado pertence a uma pessoa ativa do sistema \nVocê pode consulta-las em \"Gerenciar doadores pessoa física\"!"));							
								viewDoador.setTfCPF("");
							}
							break;
						}
					}
				}
			}
			 catch (SQLException e) {
				viewDoador.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
			}
		}
	}
	
	public CadastroDoadorPFControle(CadastroDoadorPFisicaView cadastroDoadorView) {
		setCadastroDoadorView(cadastroDoadorView);
		setFachadaDoador(new DoadorFachada());
	}

	private void setCadastroDoadorView(CadastroDoadorPFisicaView cadastroDoadorView) {
		if(cadastroDoadorView != null) {
			this.viewDoador = cadastroDoadorView;
		}
	}

	private void setFachadaDoador(DoadorFachada fachadaDoador) {
		if(fachadaDoador != null) {
			this.fachadaDoador = fachadaDoador; 
		}
	}
}