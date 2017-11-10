package control;

import java.sql.SQLException;
import java.time.LocalDate;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.CadastroDoadorPFisicaView;

public class CadastroDoadorControle {
	private CadastroDoadorPFisicaView view;
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
				doador.setDoador(true);
				if(facade.cadastrarDoador(doador)) {
					view.mensagemSucesso(doador);
					view.getShlCadastroDoador().dispose();
				}
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				view.mensagemErro(e);
			} catch (SQLException e) {
				if(e.getMessage().contains("CPF já existente")) {
					view.mensagemErro(e);
				}
				else {
					view.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}
		}
	}
	
	public CadastroDoadorControle(CadastroDoadorPFisicaView cadastroDoadorView) {
		setCadastroDoadorView(cadastroDoadorView);
		setFacade(new DoadorFachada());
	}

	private void setCadastroDoadorView(CadastroDoadorPFisicaView cadastroDoadorView) {
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
