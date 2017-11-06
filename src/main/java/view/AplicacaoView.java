package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import doacao.SelecionarDoadorView;
import org.eclipse.swt.widgets.Label;
public class AplicacaoView {

	protected Shell shlOngRussasTransformando;

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			AplicacaoView window = new AplicacaoView();
			
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlOngRussasTransformando);
		
		Button btnListarDoadores = new Button(shlOngRussasTransformando, SWT.NONE);
		btnListarDoadores.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnListarDoadores.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\doadores-small.png"));
		btnListarDoadores.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelecionarTipoDoadorListadoView.main();
			}
		});
		btnListarDoadores.setText("Gerenciar Doadores");
		btnListarDoadores.setBounds(333, 239, 224, 68);
		
		Label lblBemVindo = new Label(shlOngRussasTransformando, SWT.NONE);
		lblBemVindo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblBemVindo.setBounds(72, 39, 222, 30);
		lblBemVindo.setText("Bem vindo, {nameUSER}");
		
		Label lblSelecioneAOpo = new Label(shlOngRussasTransformando, SWT.NONE);
		lblSelecioneAOpo.setText("Selecione a op\u00E7\u00E3o desejada");
		lblSelecioneAOpo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblSelecioneAOpo.setBounds(197, 91, 256, 30);
		
		Label label = new Label(shlOngRussasTransformando, SWT.NONE);
		label.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\ABRACE.png"));
		label.setBounds(530, 51, 64, 64);
		shlOngRussasTransformando.open();
		shlOngRussasTransformando.layout();
		while (!shlOngRussasTransformando.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlOngRussasTransformando = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlOngRussasTransformando.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlOngRussasTransformando.setSize(657, 491);
		shlOngRussasTransformando.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Button btnListarAssistidos = new Button(shlOngRussasTransformando, SWT.NONE);
		btnListarAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnListarAssistidos.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\assistidos-small.png"));
		btnListarAssistidos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GerenciarAssistidosView.main();
			}
		});
		btnListarAssistidos.setBounds(72, 239, 243, 68);
		btnListarAssistidos.setText("Gerenciar Assistidos");
		
		Button btnCadastrarAssistido = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrarAssistido.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\assistido-small.png"));
		btnCadastrarAssistido.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroAssistidoView.main();
			}
		});
		btnCadastrarAssistido.setBounds(72, 165, 243, 68);
		btnCadastrarAssistido.setText("Cadastrar Assistido");
		
		Button btnCadastrarDoador = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarDoador.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrarDoador.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\doador-small.png"));
		btnCadastrarDoador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelecionarTipoDoadorView.main();
			}
		});
		btnCadastrarDoador.setText("Cadastrar Doador");
		btnCadastrarDoador.setBounds(333, 165, 224, 68);
		
		Button btnRealizarDoacao = new Button(shlOngRussasTransformando, SWT.NONE);
		btnRealizarDoacao.setText("Realizar Doação");
		btnRealizarDoacao.setBounds(333, 313, 224, 68);
		btnRealizarDoacao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SelecionarDoadorView.main();
			}
		});
	}
}
