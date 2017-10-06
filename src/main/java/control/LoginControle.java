package control;

import org.eclipse.swt.events.SelectionEvent;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import facade.LoginFachada;
import model.Usuario;
import view.LoginView;

public class LoginControle {
	private LoginView view;
	private LoginFachada facade;
		
	public void getEvent(SelectionEvent event) throws PessoaInvalidaException{
		if (event.getSource().toString().equals("Button {Entrar}")){
			try {
				Usuario usuario = facade.loga(view.getTfUsuario().getText(), view.getTfSenha().getText());
				
				if(usuario == null) {
					System.out.println("Não encontramos o usuário no banco");
				}
			} catch (UsuarioInvalidoException e) {
				view.mensagemErro(e);
			} catch (PessoaInvalidaException e) {
				view.mensagemErro(e);
			}
			
		}
	}
	
	public LoginControle(LoginView view) {
		setLoginView(view);
		setFacade(new LoginFachada());
	}

	private void setLoginView(LoginView login) {
		if(login != null) {
			this.view = login;
		}
	}

	private void setFacade(LoginFachada facade) {
		if(facade != null) {
			this.facade = facade; 
		}
	}
		
}
