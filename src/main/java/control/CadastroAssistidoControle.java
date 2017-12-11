package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionEvent;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.AssistidoFachada;
import model.Assistido;
import model.PessoaFisica;
import view.CadastroAssistidoView;

public class CadastroAssistidoControle extends Controle{
	private CadastroAssistidoView viewAssistido;
	private AssistidoFachada fachadaAssistido;

	public CadastroAssistidoControle(CadastroAssistidoView viewAssistido) throws ClassNotFoundException {
		setViewAssistido(viewAssistido);
		setFachadaAssistido(new AssistidoFachada());
	}

	public CadastroAssistidoView getViewAssistido() {
		return viewAssistido;
	}

	public void setViewAssistido(CadastroAssistidoView viewAssistido) {
		this.viewAssistido = viewAssistido;
	}

	public AssistidoFachada getFachadaAssistido() {
		return fachadaAssistido;
	}

	public void setFachadaAssistido(AssistidoFachada fachadaAssistido) {
		this.fachadaAssistido = fachadaAssistido;
	}

	public void getEvent(SelectionEvent event) throws PessoaInvalidaException, PessoaFisicaException, SQLException {
		if (event.getSource().toString().equals("Button {Cadastrar}")) {
			Assistido assistido = new Assistido();
			
			try {
				assistido.setNome(viewAssistido.getTfNome().getText());
				assistido.setApelido(viewAssistido.getTfApelido().getText());
				assistido.setCpf(viewAssistido.getTfCPF().getText());
				assistido.setRg(viewAssistido.getTfRG().getText());
				assistido.setEndereco(viewAssistido.getTfEndereco().getText());
				assistido.setDataNasc(LocalDate.of(viewAssistido.getTfDataNascimento().getYear(),
						viewAssistido.getTfDataNascimento().getMonth() + 1,
						viewAssistido.getTfDataNascimento().getDay()));
				assistido.setTelefone(viewAssistido.getTfTelefone1().getText());
				assistido.setTelefone2(viewAssistido.getTfTelefone2().getText());
				assistido.setEmail(viewAssistido.getTfEmail().getText());
				assistido.setTipoDeCancer(viewAssistido.getTfTipoCancer().getText());
				assistido.setSituacao(viewAssistido.getTfSituacao());
				assistido.setDoador(true);
				assistido.setDataCadastro(LocalDate.of(viewAssistido.getTfDataCadastro().getYear(),
						viewAssistido.getTfDataCadastro().getMonth() + 1,
						viewAssistido.getTfDataCadastro().getDay()));
				if (fachadaAssistido.cadastrarAssistido(assistido)) {
					viewAssistido.mensagemSucesso(assistido);
					viewAssistido.getShlCadastroAssistido().dispose();
				}
			} catch (PessoaInvalidaException e) {
				viewAssistido.mensagemErro(e);
			} catch (AssistidoInvalidoException e) {
				viewAssistido.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewAssistido.mensagemErro(e);
			} catch (SQLException e) {
				if (e.getMessage().contains("CPF j� existente")) {
					viewAssistido.mensagemErro(e);
				} else {
					viewAssistido.mensagemErro(new Exception("Erro na opera��o! Contate o suporte!"));
				}
			}
		}
	}

	public void getFocus(FocusEvent arg0) {
		if (viewAssistido.getTfCPF().getText() == "") {
			return;
		} else {
			try {
				if (fachadaAssistido.verificaCPF(viewAssistido.getTfCPF().getText())) {
					List<PessoaFisica> lista = fachadaAssistido.listarTabelaPessoasFisicas();
					for (PessoaFisica pessoa : lista) {
						if (pessoa.getCpf().equals(viewAssistido.getTfCPF().getText())) {
							if (! (pessoa.isAtivo())) {
								if(viewAssistido.reativarDoador(pessoa)) {
									fachadaAssistido.ativaDoador(pessoa.getId());
									viewAssistido.mensagemSucesso(fachadaAssistido.obterAssistido(pessoa.getId()));
									viewAssistido.getShlCadastroAssistido().dispose();
								}
							}else {
								viewAssistido.mensagemCPFJaCadastrado(new Exception("CPF informado pertence a uma pessoa ativa do sistema \nVoc� pode consulta-las em \"Gerenciar assistidos\"!"));							
								viewAssistido.setTfCPF("");
							}
							break;
						}
					}
				}
			}
			 catch (SQLException e) {
				viewAssistido.mensagemErro(new Exception("Erro na opera��o! Contate o suporte!"));
			}
		}
	}
}