package control;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.Pessoa;
import model.PessoaFisica;
import view.EditarDoadorPFView;

public class EditarDoadorPFControle {
	private EditarDoadorPFView view;
	private DoadorFachada fachada;
	
	public EditarDoadorPFControle (EditarDoadorPFView view){
		setView(view);
		setFachada(new DoadorFachada());
	}
	
	public EditarDoadorPFView getView() {
		return view;
	}

	public void setView(EditarDoadorPFView view) {
		this.view = view;
	}

	public DoadorFachada getFachada() {
		return fachada;
	}

	public void setFachada(DoadorFachada fachada) {
		this.fachada = fachada;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")) {
			PessoaFisica pessoa = new PessoaFisica();
			try {
				pessoa.setNome(view.getDoador().getNome());
				pessoa.setCpf(view.getDoador().getCpf());
				pessoa.setRg(view.getDoador().getRg());
				pessoa.setEndereco(view.getDoador().getEndereco());
				pessoa.setDataNasc(view.getDoador().getDataNasc());
				pessoa.setTelefone(view.getDoador().getTelefone());
				pessoa.setTelefone2(view.getDoador().getTelefone2());
				pessoa.setEmail(view.getDoador().getEmail());
				pessoa.setAtivo(view.getDoador().isAtivo());
				
				if(fachada.editarDoadorPF(pessoa)) {
					view.mensagemSucesso(pessoa);
					view.getShlEditarDoador().dispose();
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				view.mensagemErro(e);
			}
		}
	}

}
