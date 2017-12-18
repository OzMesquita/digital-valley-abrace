package control;


import java.sql.SQLException;

import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import facade.LoginFachada;
import facade.UsuarioFacade;
import model.Usuario;
import view.EditarUsuarioView;

public class EditarUsuarioControle {
	private EditarUsuarioView viewUsuario;
	private UsuarioFacade fachadaUsuario;
	private LoginFachada fachadaLogin;
	
	public EditarUsuarioControle(EditarUsuarioView viewUsuario) {
		if(viewUsuario != null) {
			this.viewUsuario = viewUsuario;
			this.fachadaUsuario = new UsuarioFacade();
			this.fachadaLogin = new LoginFachada();
		}
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar altera��es}")){
			
			try {
				if(fachadaLogin.autenticar(viewUsuario.getTfLogin().getText(), viewUsuario.getTxtSenhaAntiga().getText()) != null) {
					Usuario usuario = new Usuario();
					usuario.setId(viewUsuario.getUsuario().getId());
					usuario.setUsuario(viewUsuario.getUsuario().getUsuario());
					usuario.setSenha(viewUsuario.getUsuario().getSenha());
					System.out.println("Login:"+usuario.getUsuario());
					System.out.println("Senha:"+usuario.getSenha());
					if(!viewUsuario.getTfNovoLogin().getText().equals("")) {
						usuario.setUsuario(viewUsuario.getTfNovoLogin().getText());
					}
					if(!viewUsuario.getTFSenha().getText().equals("")) {
						usuario.setSenha(viewUsuario.getTFSenha().getText());
					}
					if (fachadaUsuario.editarUsuario(usuario)) {
						viewUsuario.mensagemSucesso(usuario);
						viewUsuario.getShlEditarDoadorPessoa().dispose();
						LoginSingleton.setUsuario(usuario);
					}
					
				}else {
					viewUsuario.mensagemErro(new Exception("Usu�rio ou senha antigos inv�lidos"));
				}
			} catch (UsuarioInvalidoException e1) {
				viewUsuario.mensagemErro(new Exception("Consulte o suporte"));
			} catch (PessoaInvalidaException e1) {
				viewUsuario.mensagemErro(new Exception("Consulte o suporte"));
			} catch (SQLException e1) {
				viewUsuario.mensagemErro(new Exception("Consulte o suporte"));
			}
		}
	}
	
}
