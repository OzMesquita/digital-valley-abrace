package control;

import java.sql.SQLException;
import java.util.List;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import facade.DoadorFachada;
import model.PessoaJuridica;
import view.CadastroDoadorPJuridicoView;

public class CadastroDoadorPJControle extends Controle{
	private CadastroDoadorPJuridicoView viewDoadorJuridico;
	private DoadorFachada fachadaDoadorJuridico;
	
	public CadastroDoadorPJControle(CadastroDoadorPJuridicoView viewDoadorJuridico) {
		setViewDoadorJuridico(viewDoadorJuridico);
		setFachadaDoadorJuridico(new DoadorFachada());
	}
	
	public void getEvent(SelectionEvent event){
		if (event.getSource().toString().equals("Button {Cadastrar}")) {
			PessoaJuridica doador = new PessoaJuridica();
			try {
				doador.setNome(viewDoadorJuridico.getTfNome().getText());
				doador.setCnpj(viewDoadorJuridico.getTfCNPJ().getText());
				doador.setNomeFantasia(viewDoadorJuridico.gettfFantasia().getText());
				doador.setEndereco(viewDoadorJuridico.getTfEndereco().getText());
				doador.setTelefone(viewDoadorJuridico.getTfTelefone1().getText());
				doador.setTelefone2(viewDoadorJuridico.getTfTelefone2().getText());
				doador.setEmail(viewDoadorJuridico.getTfEmail().getText());
				doador.setDoador(true);
				if (fachadaDoadorJuridico.cadastrarDoadorJuridico(doador)) {
					viewDoadorJuridico.mensagemSucesso(doador);
					viewDoadorJuridico.getShlCadastroDoador().dispose();
				}
			} catch (SQLException ex) {
				if(ex.getMessage().contains("CNPJ já existente")) {
					viewDoadorJuridico.mensagemErro(ex);
				}
				else {
					viewDoadorJuridico.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}catch(PessoaInvalidaException ex) {
				viewDoadorJuridico.mensagemErro(ex);
			} catch (PessoaJuridicaInvalidaException ex) {
				viewDoadorJuridico.mensagemErro(ex);
			}
		}
	}
	
	public void getFocus(FocusEvent arg0) {
		if(viewDoadorJuridico.getTfCNPJ().getText() == "") {
			return;
		}else {
			try {
				if(fachadaDoadorJuridico.verificaCNPJ(viewDoadorJuridico.getTfCNPJ().getText())) {
					List<PessoaJuridica> lista = fachadaDoadorJuridico.listarTabelaPessoaJuridica();
					for(PessoaJuridica pessoa : lista) {
						if(pessoa.getCnpj().equals(viewDoadorJuridico.getTfCNPJ().getText()) && !(pessoa.isAtivo())) {
							if(viewDoadorJuridico.reativarDoador(pessoa)) {
								fachadaDoadorJuridico.ativaDoador(pessoa.getId());
								viewDoadorJuridico.mensagemSucessoReativacao(fachadaDoadorJuridico.obterDoadorJuridico(pessoa.getId()));
								viewDoadorJuridico.getShlCadastroDoador().dispose();
							}
							break;
						}
					}
				}
			} catch (SQLException e) {
				viewDoadorJuridico.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
			}
		}
	}
	
	public CadastroDoadorPJuridicoView getViewDoadorJuridico() {
		return viewDoadorJuridico;
	}
	
	public void setViewDoadorJuridico(CadastroDoadorPJuridicoView viewDoadorJuridico) {
		if(viewDoadorJuridico != null)
			this.viewDoadorJuridico = viewDoadorJuridico;
	}
	
	public DoadorFachada getFachadaDoadorJuridico() {
		return fachadaDoadorJuridico;
	}

	public void setFachadaDoadorJuridico(DoadorFachada fachadaDoadorJuridico) {
		if(fachadaDoadorJuridico!=null)
		this.fachadaDoadorJuridico = fachadaDoadorJuridico;
	}
	
	
}