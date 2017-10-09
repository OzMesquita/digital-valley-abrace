package control;

import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.AssistidoFachada;
import model.Assistido;
import view.CadastroAssistidoView;

public class CadastroAssistidoControle {
	private CadastroAssistidoView view; 
	private AssistidoFachada fachada;

	public CadastroAssistidoControle(CadastroAssistidoView view) throws ClassNotFoundException {
		setView(view);
		setFachada(new AssistidoFachada());
	}

	public CadastroAssistidoView getView() {
		return view;
	}

	public void setView(CadastroAssistidoView view) {
		this.view = view;
	}

	public AssistidoFachada getFachada() {
		return fachada;
	}

	public void setFachada(AssistidoFachada fachada) {
		this.fachada = fachada;
	}

	public void getEvent(SelectionEvent event) throws PessoaInvalidaException, PessoaFisicaException {
		if (event.toString().equals("Button {Cadastrar}")) {
			Assistido assistido = new Assistido();
			try {
				assistido.setNome(view.getTfNome().getText());
				assistido.setApelido(view.getTfApelido().getText());
				assistido.setCpf(view.getTfCPF().getText());
				assistido.setRg(view.getTfRG().getText());
				assistido.setEndereco(view.getTfEndereco().getText());
				assistido.setDataNasc(LocalDate.of(view.getTfDataNascimento().getYear(),
						view.getTfDataNascimento().getMonth(), view.getTfDataNascimento().getDay()));
				assistido.setTelefone(view.getTfTelefone1().getText());
				assistido.setTelefone2(view.getTfTelefone2().getText());
				assistido.setEmail(view.getTfEmail().getText());
				assistido.setTipoDeCancer(view.getTfTipoCancer().getText());
				assistido.setAtivo(view.getTfStatus());
				assistido.setSituacao(view.getTfSituacao());

				if (fachada.cadastrarAssistido(assistido)) {
					view.mensagemSucesso(assistido);
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (AssistidoInvalidoException e) {
				view.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				view.mensagemErro(e);
			}

		}
	}
}