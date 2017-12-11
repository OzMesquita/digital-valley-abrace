package control;


import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaInvalidaException;
import exceptions.UsuarioInvalidoException;
import facade.UsuarioFacade;
import model.Usuario;
import view.EditarUsuarioView;

public class EditarUsuarioControle {
	private EditarUsuarioView viewUsuario;
	private UsuarioFacade fachadaUsuario;
	
	public EditarUsuarioControle(EditarUsuarioView viewUsuario) {
		if(viewUsuario != null) {
			this.viewUsuario = viewUsuario;
			this.fachadaUsuario = new UsuarioFacade();
		}
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			Usuario usuario = new Usuario();
			try {
				usuario.setId(viewUsuario.getUsuario().getId());
				usuario.setUsuario(viewUsuario.getTfLogin().getText());
				if(viewUsuario.getTFSenha().getText()!= "" || viewUsuario.getTFSenha().getText()!= null) {
					usuario.setSenha(viewUsuario.getTFSenha().getText());
				}else {
					usuario.setSenha(viewUsuario.getUsuario().getSenha());
				}
				if(viewUsuario.getTxtSenhaAntiga().getText().equals(LoginSingleton.getUsuario().getSenha())) {
					if (fachadaUsuario.editarUsuario(usuario)) {
						viewUsuario.mensagemSucesso(usuario);
						viewUsuario.getShlEditarDoadorPessoa().dispose();
						LoginSingleton.setUsuario(usuario);
					}
				}else {
					viewUsuario.mensagemErro(new Exception("Você digitou a senha atual inválida"));
				}
			} catch (PessoaInvalidaException e) {
				viewUsuario.mensagemErro(new Exception("Consulte o suporte"));
			} catch (UsuarioInvalidoException e) {
				viewUsuario.mensagemErro(new Exception("Consulte o suporte"));
			}
		}else {
			this.viewUsuario.getShlEditarDoadorPessoa().dispose();
		}
	}
	
}
