package control;

import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.AssistidoFachada;
import model.Assistido;
import view.EditarAssistidoView;

public class EditarAssistidoControle {
	private EditarAssistidoView view;
	private AssistidoFachada fachada;
	
	public EditarAssistidoControle (EditarAssistidoView view){
		setView(view);
		setFachada(new AssistidoFachada());
	}
	
	public EditarAssistidoView getView() {
		return view;
	}


	public void setView(EditarAssistidoView view) {
		this.view = view;
	}


	public AssistidoFachada getFachada() {
		return fachada;
	}


	public void setFachada(AssistidoFachada fachada) {
		this.fachada = fachada;
	}

	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			Assistido assistido = new Assistido();
			try {
				assistido.setId(view.getAssistido().getId());
				assistido.setNome(view.getTfNome().getText());
				assistido.setApelido(view.getTfApelido().getText());
				assistido.setCpf(view.getTfCPF().getText());
				assistido.setRg(view.getTfRG().getText());
				assistido.setEndereco(view.getTfEndereco().getText());
				assistido.setDataNasc(LocalDate.of(view.getTfDataNascimento().getYear(),
						view.getTfDataNascimento().getMonth() + 1, view.getTfDataNascimento().getDay()));
				assistido.setTelefone(view.getTfTelefone1().getText());
				assistido.setTelefone2(view.getTfTelefone2().getText());
				assistido.setEmail(view.getTfEmail().getText());
				assistido.setTipoDeCancer(view.getTfTipoCancer().getText());
				assistido.setSituacao(view.getTfSituacao());
				if (fachada.editarAssistido(assistido)) {
					view.mensagemSucesso(assistido);
					view.getShlEditarAssistido().dispose();
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