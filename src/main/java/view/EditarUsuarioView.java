package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import control.EditarUsuarioControle;
import model.PessoaFisica;
import model.Usuario;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class EditarUsuarioView {
	protected Shell shlEditarUsuario;
	private Usuario usuario;
	private EditarUsuarioControle controle = new EditarUsuarioControle(this);
	private Text tfLogin;
	public Text getTfLogin() {
		return tfLogin;
	}

	public void setTfLogin(Text tfLogin) {
		this.tfLogin = tfLogin;
	}

	private Text TFSenha;

	public EditarUsuarioControle getControle() {
		return controle;
	}

	public void setControle(EditarUsuarioControle controle) {
		this.controle = controle;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setDoador(Usuario usuario) {
		this.usuario = usuario;
	}
	public Shell getShlEditarDoadorPessoa() {
		return shlEditarUsuario;
	}

	public void setShlEditarUsuario(Shell shlEditarUsuario) {
		this.shlEditarUsuario = shlEditarUsuario;
	}

	public static void main(Usuario usuario) {
		try {
			EditarUsuarioView window = new EditarUsuarioView();
			window.setDoador(usuario);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEditarUsuario.open();
		shlEditarUsuario.layout();
		while (!shlEditarUsuario.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlEditarUsuario = new Shell();
		shlEditarUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlEditarUsuario.setSize(715, 381);
		shlEditarUsuario.setText("Editar doador - ABRACE");
		Centralize.centralize(shlEditarUsuario);
		
		Label lblEditarUsurio = new Label(shlEditarUsuario, SWT.NONE);
		lblEditarUsurio.setText("Editar Usu\u00E1rio");
		lblEditarUsurio.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblEditarUsurio.setBounds(226, 21, 245, 54);
		
		Label label_2 = new Label(shlEditarUsuario, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_2.setBounds(538, 21, 109, 114);
		
		Label label_1 = new Label(shlEditarUsuario, SWT.NONE);
		label_1.setText("* Campos Obrigat\u00F3rios");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(263, 82, 160, 21);
		
		Label lblLogin = new Label(shlEditarUsuario, SWT.NONE);
		lblLogin.setText("Login:");
		lblLogin.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblLogin.setBounds(167, 131, 55, 28);
		
		tfLogin = new Text(shlEditarUsuario, SWT.BORDER);
		tfLogin.setTextLimit(16);
		tfLogin.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfLogin.setBounds(239, 127, 369, 38);
		
		Label lblSenha = new Label(shlEditarUsuario, SWT.NONE);
		lblSenha.setText("Senha:");
		lblSenha.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblSenha.setBounds(160, 175, 62, 28);
		
		TFSenha = new Text(shlEditarUsuario, SWT.BORDER | SWT.PASSWORD);
		TFSenha.setTextLimit(128);
		TFSenha.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		TFSenha.setBounds(239, 171, 369, 38);
		
		Button button = new Button(shlEditarUsuario, SWT.NONE);
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarUsuario.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button.setBounds(160, 258, 184, 60);
		
		Button btnSalvarAlteraes = new Button(shlEditarUsuario, SWT.NONE);
		btnSalvarAlteraes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnSalvarAlteraes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnSalvarAlteraes.setText("Salvar altera\u00E7\u00F5es");
		btnSalvarAlteraes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarAlteraes.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnSalvarAlteraes.setBounds(361, 258, 224, 60);
		tfLogin.setText(usuario.getUsuario());
		
	}
	
	public Text getTFSenha() {
		return TFSenha;
	}

	public void setTFSenha(Text tFSenha) {
		TFSenha = tFSenha;
	}

	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlEditarUsuario,SWT.ICON_ERROR | SWT.OK); 
	     messageBox.setText("Problemas ao editar dados do usu�rio!");
	     messageBox.setMessage("N�o foi poss�vel editar este usu�rio. \nConsulte o suporte.");
	     messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlEditarUsuario,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Opera��o realizada com sucesso!");
		messageBox.setMessage("Os dados do usu�rio foram alterados com sucesso!");
		messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlEditarUsuario.dispose();
		}
	}
}