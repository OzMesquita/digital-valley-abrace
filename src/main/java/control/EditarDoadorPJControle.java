package control;


import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import facade.DoadorFachada;
import model.PessoaJuridica;
import view.EditarDoadorPJView;
import view.GerenciarDoadoresView;

public class EditarDoadorPJControle extends Controle{
	private EditarDoadorPJView viewDoador;
	private DoadorFachada fachadaDoador;
	
	public EditarDoadorPJControle (EditarDoadorPJView viewDoador){
		setViewDoador(viewDoador);
		setFachadaDoador(new DoadorFachada());
	}
	
	public EditarDoadorPJView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(EditarDoadorPJView viewDoador) {
		this.viewDoador = viewDoador;
	}

	public DoadorFachada getFachadaDoador() {
		return fachadaDoador;
	}

	public void setFachadaDoador(DoadorFachada fachadaDoador) {
		this.fachadaDoador = fachadaDoador;
	}

	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			PessoaJuridica doador = new PessoaJuridica();
			try {
				doador.setId(viewDoador.getDoador().getId());
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCnpj(viewDoador.getTfCNPJ().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setNomeFantasia(viewDoador.gettfFantasia().getText());
				if (fachadaDoador.editarDoadorJuridico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlCadastroDoador().dispose();
					GerenciarDoadoresView.main();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaJuridicaInvalidaException e) {
				viewDoador.mensagemErro(e);
			}
		}
	}
}