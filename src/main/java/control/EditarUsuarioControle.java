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
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			
			try {
				if(fachadaLogin.autenticar(viewUsuario.getTfLogin().getText(), viewUsuario.getTxtSenhaAntiga().getText()) != null) {
					Usuario usuario = new Usuario();
					usuario.setId(viewUsuario.getUsuario().getId());
					usuario.setUsuario(viewUsuario.getUsuario().getUsuario());
					usuario.setUsuario(viewUsuario.getUsuario().getSenha());
					
					if(!viewUsuario.getTfNovoLogin().equals("")) {
						usuario.setUsuario(viewUsuario.getTfNovoLogin().getText());
					}
					
					if(!viewUsuario.getTFSenha().equals("")) {
						usuario.setSenha(viewUsuario.getTFSenha().getText());
					}
					
					if (fachadaUsuario.editarUsuario(usuario)) {
						viewUsuario.mensagemSucesso(usuario);
						viewUsuario.getShlEditarDoadorPessoa().dispose();
						LoginSingleton.setUsuario(usuario);
					}
					
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
