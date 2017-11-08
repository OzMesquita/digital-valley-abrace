package control;

import java.sql.SQLException;
import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.CadastroDoadorView;

public class CadastroDoadorControle {
	private CadastroDoadorView view;
	private DoadorFachada facade; 
	
	public void getEvent(SelectionEvent event) throws PessoaInvalidaException, PessoaFisicaException, SQLException{
		if (event.getSource().toString().equals("Button {Cadastrar}")){
			try {
				PessoaFisica doador = new PessoaFisica();
				doador.setNome(view.getTfNome().getText());
				doador.setCpf(view.getTfCPF().getText());
				doador.setRg(view.getTfRG().getText());
				doador.setEndereco(view.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(view.getTfDataNascimento().getYear(), view.getTfDataNascimento().getMonth() + 1, view.getTfDataNascimento().getDay()));
				doador.setTelefone(view.getTfTelefone1().getText());
				doador.setTelefone2(view.getTfTelefone2().getText());
				doador.setEmail(view.getTfEmail().getText());
				doador.setAtivo(view.getTfStatus());
				
				if(facade.cadastrarDoador(doador)) {
					view.mensagemSucesso(doador);
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				view.mensagemErro(e);
			} catch (SQLException e) {
				view.mensagemErro(e);
			}
		}
	}
	
	public CadastroDoadorControle(CadastroDoadorView cadastroDoadorView) {
		setCadastroDoadorView(cadastroDoadorView);
		setFacade(new DoadorFachada());
	}

	private void setCadastroDoadorView(CadastroDoadorView cadastroDoadorView) {
		if(cadastroDoadorView != null) {
			this.view = cadastroDoadorView;
		}
	}

	private void setFacade(DoadorFachada facade) {
		if(facade != null) {
			this.facade = facade; 
		}
	}
}
