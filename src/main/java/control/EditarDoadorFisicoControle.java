package control;

import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.EditarDoadorPFView;

public class EditarDoadorFisicoControle {
	private EditarDoadorPFView view;
	private DoadorFachada facade;
	public EditarDoadorFisicoControle(EditarDoadorPFView editarDoadorFisicoView) {
		if(editarDoadorFisicoView != null) {
			this.view = editarDoadorFisicoView;
			this.facade = new DoadorFachada();
		}
	}
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			PessoaFisica doador = new PessoaFisica();
			try {
				doador.setId(view.getDoador().getId());
				doador.setNome(view.getTfNome().getText());
				doador.setCpf(view.getTfCPF().getText());
				doador.setRg(view.getTfRG().getText());
				doador.setEndereco(view.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(view.getTfDataNascimento().getYear(), view.getTfDataNascimento().getMonth() + 1, view.getTfDataNascimento().getDay()));
				doador.setTelefone(view.getTfTelefone1().getText());
				doador.setTelefone2(view.getTfTelefone2().getText());
				doador.setEmail(view.getTfEmail().getText());
				doador.setDataCadastro(LocalDate.now());
				if (facade.editarDoadorFisico(doador)) {
					view.mensagemSucesso(doador);
					view.getShlEditarDoadorPessoa().dispose();
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				view.mensagemErro(e);;
			}
		}
	}

}
