package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import control.LoginSingleton;
import exibirRelatorios.EmitirRelatoriosView;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class AplicacaoView {
	protected Shell shlOngRussasTransformando;

	public static void main() {
		try {
			AplicacaoView window = new AplicacaoView();
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
		Button btnListarDoadores = new Button(shlOngRussasTransformando, SWT.NONE);
		btnListarDoadores.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnListarDoadores.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnListarDoadores.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores-small.png").getAbsolutePath()));
		btnListarDoadores.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GerenciarDoadoresView.main();
			}
		});
		btnListarDoadores.setText("Gerenciar Doadores");
		btnListarDoadores.setBounds(333, 251, 243, 68);
		Button btnRealizarDoacao = new Button(shlOngRussasTransformando, SWT.NONE);
		btnRealizarDoacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRealizarDoacao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacao-small.png").getAbsolutePath()));
		btnRealizarDoacao.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRealizarDoacao.setText("Realizar Doação");
		btnRealizarDoacao.setBounds(72, 337, 243, 68);
		btnRealizarDoacao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelecionarDoadorView.main();
			}
		});
		
		Button btnGerenciarDoaes = new Button(shlOngRussasTransformando, SWT.NONE);
		btnGerenciarDoaes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnGerenciarDoaes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GerenciarDoacoesView.main();
			}
		});
		btnGerenciarDoaes.setText("Gerenciar Doa\u00E7\u00F5es");
		btnGerenciarDoaes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacoes-small.png").getAbsolutePath()));
		btnGerenciarDoaes.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnGerenciarDoaes.setBounds(333, 337, 243, 68);
		Label label = new Label(shlOngRussasTransformando, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/abrace-logo.png").getAbsolutePath()));
		label.setBounds(482, 28, 101, 101);
		
		Button btnRelatrios = new Button(shlOngRussasTransformando, SWT.NONE);
		btnRelatrios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRelatrios.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				EmitirRelatoriosView.main();
			}
		});
		btnRelatrios.setText("Emitir Relat\u00F3rios");
		btnRelatrios.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/relatorio-small.png").getAbsolutePath()));
		btnRelatrios.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRelatrios.setBounds(333, 421, 243, 68);
		
		Button bntEditaUsuario = new Button(shlOngRussasTransformando, SWT.NONE);
		bntEditaUsuario.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				EditarUsuarioView.main(LoginSingleton.getUsuario());
			}
		});
		bntEditaUsuario.setText("Editar Usu\u00E1rio");
		bntEditaUsuario.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/perfil-small.png").getAbsolutePath()));
		bntEditaUsuario.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		bntEditaUsuario.setBounds(72, 421, 243, 68);
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
		shlOngRussasTransformando.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evento) {
				identificarESC(evento);
			}
		});
		shlOngRussasTransformando.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlOngRussasTransformando.setSize(657, 562);
		shlOngRussasTransformando.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Label lblBemVindo = new Label(shlOngRussasTransformando, SWT.NONE);
		lblBemVindo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblBemVindo.setBounds(72, 39, 404, 30);
		lblBemVindo.setText("Bem vindo, " + LoginSingleton.getUsuario().getNome());
		
		Label lblSelecioneAOpo = new Label(shlOngRussasTransformando, SWT.NONE);
		lblSelecioneAOpo.setText("Selecione a op\u00E7\u00E3o desejada");
		lblSelecioneAOpo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblSelecioneAOpo.setBounds(197, 91, 256, 30);
		
		Button btnCadastrarAssistido = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarAssistido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evento) {
				identificarESC(evento);
			}
		});
		btnCadastrarAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrarAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido-small.png").getAbsolutePath()));
		btnCadastrarAssistido.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroAssistidoView.main();
			}
		});
		btnCadastrarAssistido.setBounds(72, 165, 243, 68);
		btnCadastrarAssistido.setText("Cadastrar Assistido");
		
		Button btnListarAssistidos = new Button(shlOngRussasTransformando, SWT.NONE);
		btnListarAssistidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnListarAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnListarAssistidos.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistidos-small.png").getAbsolutePath()));
		btnListarAssistidos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GerenciarAssistidosView.main();
			}
		});
		btnListarAssistidos.setBounds(333, 165, 243, 68);
		btnListarAssistidos.setText("Gerenciar Assistidos");
		
		Button btnCadastrarDoador = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarDoador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCadastrarDoador.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrarDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doador-small.png").getAbsolutePath()));
		btnCadastrarDoador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelecionarTipoDoadorView.main();
			}
		});
		btnCadastrarDoador.setText("Cadastrar Doador");
		btnCadastrarDoador.setBounds(72, 251, 243, 68);
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlOngRussasTransformando.dispose();
		}
	}
}