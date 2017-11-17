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
	private CadastroDoadorPFisicaView viewDoador;
	private DoadorFachada fachadaDoador; 
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cadastrar}")){
			try {
				PessoaFisica doador = new PessoaFisica();
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCpf(viewDoador.getTfCPF().getText());
				doador.setRg(viewDoador.getTfRG().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(viewDoador.getTfDataNascimento().getYear(),viewDoador.getTfDataNascimento().getMonth() + 1, viewDoador.getTfDataNascimento().getDay()));
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setDoador(true);
				if(fachadaDoador.cadastrarDoadorFisico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlCadastroDoador().dispose();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewDoador.mensagemErro(e);
			} catch (SQLException e) {
				if(e.getMessage().contains("CPF já existente")) {
					viewDoador.mensagemErro(e);
				}
				else {
					viewDoador.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}
		}
	}
	
	public CadastroDoadorControle(CadastroDoadorPFisicaView cadastroDoadorView) {
		setCadastroDoadorView(cadastroDoadorView);
		setFachadaDoador(new DoadorFachada());
	}

	private void setCadastroDoadorView(CadastroDoadorPFisicaView cadastroDoadorView) {
		if(cadastroDoadorView != null) {
			this.viewDoador = cadastroDoadorView;
		}
	}

	private void setFachadaDoador(DoadorFachada fachadaDoador) {
		if(fachadaDoador != null) {
			this.fachadaDoador = fachadaDoador; 
		}
	}
}