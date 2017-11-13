package control;


import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import facade.DoadorFachada;
import model.PessoaJuridica;
import view.EditarDoadorJuridicoView;
import view.GerenciarDoadoresView;

public class EditarDoadorJuridicoControle {

	private EditarDoadorJuridicoView view;
	private DoadorFachada fachada;
	
	public EditarDoadorJuridicoControle (EditarDoadorJuridicoView view){
		setView(view);
		setFachada(new DoadorFachada());
	}
	
	public EditarDoadorJuridicoView getView() {
		return view;
	}


	public void setView(EditarDoadorJuridicoView view) {
		this.view = view;
	}


	public DoadorFachada getFachada() {
		return fachada;
	}


	public void setFachada(DoadorFachada fachada) {
		this.fachada = fachada;
	}

	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			PessoaJuridica doador = new PessoaJuridica();
			try {
				doador.setId(view.getDoador().getId());
				doador.setNome(view.getTfNome().getText());
				doador.setCnpj(view.getTfCNPJ().getText());
				doador.setEndereco(view.getTfEndereco().getText());
				doador.setTelefone(view.getTfTelefone1().getText());
				doador.setTelefone2(view.getTfTelefone2().getText());
				doador.setEmail(view.getTfEmail().getText());
				doador.setNomeFantasia(view.gettfFantasia().getText());
				if (fachada.editarDoadorJuridico(doador)) {
					view.mensagemSucesso(doador);
					view.getShlCadastroDoador().dispose();
					GerenciarDoadoresView.main();
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (PessoaJuridicaInvalidaException e) {
				view.mensagemErro(e);
			}
		}
	}

}
