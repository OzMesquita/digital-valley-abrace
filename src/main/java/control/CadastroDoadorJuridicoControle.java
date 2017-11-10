package control;

import java.sql.SQLException;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import facade.DoadorFachada;
import model.PessoaJuridica;
import view.CadastroDoadorPJuridicoView;

public class CadastroDoadorJuridicoControle {
	private CadastroDoadorPJuridicoView view;
	private DoadorFachada facade;
	public CadastroDoadorJuridicoControle(CadastroDoadorPJuridicoView cadastroDoadorJuridicoView) {
		setView(cadastroDoadorJuridicoView);
		setFacade(new DoadorFachada());
	}
	
	public void getEvent(SelectionEvent event){
		if (event.getSource().toString().equals("Button {Cadastrar}")) {
			PessoaJuridica doador = new PessoaJuridica();
			try {
				doador.setNome(view.getTfNome().getText());
				doador.setCnpj(view.getTfCNPJ().getText());
				doador.setNomeFantasia(view.gettfFantasia().getText());
				doador.setEndereco(view.getTfEndereco().getText());
				doador.setTelefone(view.getTfTelefone1().getText());
				doador.setTelefone2(view.getTfTelefone2().getText());
				doador.setEmail(view.getTfEmail().getText());
				doador.setDoador(true);
				if (facade.cadastrarDoadorJuridico(doador)) {
					view.mensagemSucesso(doador);
					view.getShlCadastroDoador().dispose();
				}
			} catch (SQLException ex) {
				if(ex.getMessage().contains("CNPJ já existente")) {
					view.mensagemErro(ex);
				}
				else {
					view.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}catch(PessoaInvalidaException ex) {
				view.mensagemErro(ex);
			} catch (PessoaJuridicaInvalidaException ex) {
				view.mensagemErro(ex);
			}

		}
	}

	
	public CadastroDoadorPJuridicoView getView() {
		return view;
	}
	
	public void setView(CadastroDoadorPJuridicoView view) {
		if(view != null)
			this.view = view;
	}
	
	public DoadorFachada getFacade() {
		return facade;
	}

	public void setFacade(DoadorFachada facade) {
		if(facade!=null)
		this.facade = facade;
	}
	
}
