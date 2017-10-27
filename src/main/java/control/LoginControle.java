package control;

import java.sql.SQLException;

import org.eclipse.swt.events.SelectionEvent;
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
		
	public void getEvent(SelectionEvent event){
		if (event.getSource().toString().equals("Button {Entrar}")){
			try {
				Usuario usuario = facade.loga(view.getTfUsuario().getText(), view.getTfSenha().getText());
				if(usuario == null) {
					throw new UsuarioNaoEncontradoException();
				}else {
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
		
		
}
