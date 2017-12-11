package control;

import java.sql.SQLException;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import DAO.ConnectionFactory;
import DAO.UsuarioDAO;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import exceptions.UsuarioNaoEncontradoException;
import facade.LoginFachada;
import model.Usuario;
import view.AplicacaoView;
import view.LoginView;

public class LoginControle {
	
	private LoginView viewLogin;
	private LoginFachada fachadaLogin;
	public void identificarTelasEspeciais(KeyEvent evento) {
		if(evento.keyCode == 13 || evento.keyCode == 16777296) {
			validarLogin();
		} if(evento.keyCode == 27) {
			viewLogin.getShlOngRussasTransformando().dispose();
		}
	}
		
	public void getEvent(SelectionEvent event){
		if (event.getSource().toString().equals("Button {Entrar}")){
			validarLogin();
		}else {
			viewLogin.mensagemErro(new UsuarioNaoEncontradoException());
		}
	}
	
	public LoginControle(LoginView viewLogin) {
		setViewLogin(viewLogin);
		setFachadaLogin(new LoginFachada());
	}

	private void setViewLogin(LoginView viewLogin) {
		if(viewLogin != null) {
			this.viewLogin = viewLogin;
		}
	}

	private void setFachadaLogin(LoginFachada fachadaLogin) {
		if(fachadaLogin != null) {
			this.fachadaLogin = fachadaLogin; 
		}
	}
	
	public void validarLogin() {
		try {
			Usuario usuario = fachadaLogin.autenticar(viewLogin.getTfUsuario().getText(), viewLogin.getTfSenha().getText());
			if(usuario == null) {
				throw new UsuarioNaoEncontradoException();
			}else {
				UsuarioDAO dao = new UsuarioDAO(new ConnectionFactory().getConnection());
				LoginSingleton.LoginSingleton(dao.getUsuario(usuario.getId()));
				dao.getConexao().close();
				this.viewLogin.getShlOngRussasTransformando().dispose();
				AplicacaoView.main();
			}
		} catch (UsuarioInvalidoException e) {
			viewLogin.mensagemErro(e);
		} catch (PessoaInvalidaException e) {
			viewLogin.mensagemErro(e);
		}catch(UsuarioNaoEncontradoException e) {
			viewLogin.mensagemErro(e);
		} catch (SQLException e) {
			viewLogin.mensagemErro(e);
		} catch (PessoaFisicaException e) {
			viewLogin.mensagemErro(e);
		}
	}	
}