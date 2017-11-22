package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import control.LoginControle;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class LoginView {
	protected Shell shlOngRussasTransformando;
	private Text tfUsuario;
	private Text tfSenha;
	LoginControle controle;
	
	public LoginView() {
		this.controle = new LoginControle(this);
	}

	public Text getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(Text tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public Text getTfSenha() {
		return tfSenha;
	}

	public void setTfSenha(Text tfSenha) {
		this.tfSenha = tfSenha;
	}

	public Shell getShlOngRussasTransformando() {
		return shlOngRussasTransformando;
		
	}

	public static void main() {
		try {
			LoginView window = new LoginView();
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
		Centralize.centralize(shlOngRussasTransformando);
		shlOngRussasTransformando.open();
		shlOngRussasTransformando.layout();
		while (!shlOngRussasTransformando.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	protected void createContents() {
		shlOngRussasTransformando = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlOngRussasTransformando.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlOngRussasTransformando.setSize(530, 513);
		shlOngRussasTransformando.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Label lblUsurio = new Label(shlOngRussasTransformando, SWT.NONE);
		lblUsurio.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblUsurio.setBounds(99, 274, 75, 30);
		lblUsurio.setText("Usu\u00E1rio:");
		
		tfUsuario = new Text(shlOngRussasTransformando, SWT.BORDER);
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.identificarTelasEspeciais(arg0);
			}
		});
		tfUsuario.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfUsuario.setBounds(180, 271, 230, 36);
		
		Label lblSenha = new Label(shlOngRussasTransformando, SWT.NONE);
		lblSenha.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblSenha.setBounds(112, 324, 62, 30);
		lblSenha.setText("Senha:");
		
		tfSenha = new Text(shlOngRussasTransformando, SWT.BORDER | SWT.PASSWORD);
		tfSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.identificarTelasEspeciais(arg0);
			}
		});
		tfSenha.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfSenha.setBounds(180, 321, 230, 36);
		
		Button btnEntrar = new Button(shlOngRussasTransformando, SWT.NONE);
		btnEntrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
					controle.getEvent(e);
				
			}
		});
		btnEntrar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnEntrar.setBounds(198, 385, 128, 40);
		btnEntrar.setText("Entrar");
		
		Label lblBemVindo = new Label(shlOngRussasTransformando, SWT.NONE);
		lblBemVindo.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		lblBemVindo.setBounds(199, 149, 125, 32);
		lblBemVindo.setText("Bem Vindo!");
		
		
		Label lblDigiteSeusDados = new Label(shlOngRussasTransformando, SWT.NONE);
		lblDigiteSeusDados.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblDigiteSeusDados.setBounds(131, 225, 261, 30);
		lblDigiteSeusDados.setText("Digite seus dados de acesso");
		
		Label label = new Label(shlOngRussasTransformando, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/abrace-logo.png").getAbsolutePath()));
		label.setBounds(208, 27, 108, 112);

	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlOngRussasTransformando,SWT.ICON_ERROR | SWT.OK); 
         messageBox.setText("Login inválido");
         messageBox.setMessage(e.getMessage()+"\nTente novamente");
         messageBox.open();
	}	
}