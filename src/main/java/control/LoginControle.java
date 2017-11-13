package control;

import java.sql.SQLException;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;

import DAO.ConnectionFactory;
import DAO.PessoaDAO;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import exceptions.UsuarioNaoEncontradoException;
import facade.LoginFachada;
import model.Usuario;
import view.AplicacaoView;
import view.LoginView;

public class LoginControle {
	private LoginView view;
	private LoginFachada facade;
	private PessoaDAO pessoaDAO = new PessoaDAO(new ConnectionFactory().getConnection());
	
	public void identificarTelasEspeciais(KeyEvent evento) {
		if(evento.keyCode == 13 || evento.keyCode == 16777296) {
			validarLogin();
		} if(evento.keyCode == 27) {
			view.getShlOngRussasTransformando().dispose();
		}
	}
		
	public void getEvent(SelectionEvent event){
		if (event.getSource().toString().equals("Button {Entrar}")){
			validarLogin();
		}else {
			view.mensagemErro(new UsuarioNaoEncontradoException());
		}
	}
	
	public LoginControle(LoginView view) {
		setLogin(view);
		setFacade(new LoginFachada());
	}

	private void setLogin(LoginView view) {
		if(view != null) {
			this.view = view;
		}
	}

	private void setFacade(LoginFachada facade) {
		if(facade != null) {
			this.facade = facade; 
		}
	}
	
	public void validarLogin() {
		try {
			Usuario usuario = facade.autenticar(view.getTfUsuario().getText(), view.getTfSenha().getText());
			if(usuario == null) {
				throw new UsuarioNaoEncontradoException();
			}else {
				LoginSingleton.LoginSingleton(pessoaDAO.getPessoa(usuario.getId()));
				this.view.getShlOngRussasTransformando().dispose();
				AplicacaoView.main();
			}
		} catch (UsuarioInvalidoException e) {
			view.mensagemErro(e);
		} catch (PessoaInvalidaException e) {
			view.mensagemErro(e);
		}catch(UsuarioNaoEncontradoException e) {
			view.mensagemErro(e);
		} catch (SQLException e) {
			view.mensagemErro(e);
		}
	}
		
		
}
