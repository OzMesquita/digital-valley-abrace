package exibirRelatorios;

import java.io.File;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import view.Centralize;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EmitirRelatoriosView {

	protected Shell shlCadastroAssistido;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			EmitirRelatoriosView window = new EmitirRelatoriosView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCadastroAssistido.open();
		shlCadastroAssistido.layout();
		Centralize.centralize(shlCadastroAssistido);
		while (!shlCadastroAssistido.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCadastroAssistido = new Shell();
		shlCadastroAssistido.setMinimumSize(new Point(136, 29));
		shlCadastroAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroAssistido.setSize(671, 575);
		shlCadastroAssistido.setText("Emitir relat\u00F3rios - ABRACE");
		
		Label lblEmitirRelatrios = new Label(shlCadastroAssistido, SWT.NONE);
		lblEmitirRelatrios.setText("Emitir Relat\u00F3rios");
		lblEmitirRelatrios.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblEmitirRelatrios.setBounds(183, 42, 288, 54);
		
		Label label = new Label(shlCadastroAssistido, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/relatorio.png").getAbsolutePath()));
		label.setBounds(508, 10, 97, 118);
		
		Button btnNewButton = new Button(shlCadastroAssistido, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				EmitirRelatorioAnualPorDoadorView.main();
			}
		});
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnNewButton.setBounds(131, 135, 392, 68);
		btnNewButton.setText("Relat\u00F3rio anual de doa\u00E7\u00F5es (por pessoa)");
		
		Button btnRelatrioMensalDe = new Button(shlCadastroAssistido, SWT.NONE);
		btnRelatrioMensalDe.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				EmitirRelatorioDoacoesView.main();
			}
		});
		btnRelatrioMensalDe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRelatrioMensalDe.setText("Relat\u00F3rio de doa\u00E7\u00F5es (ABRACE)");
		btnRelatrioMensalDe.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRelatrioMensalDe.setBounds(131, 209, 392, 68);
		
		Button btnRelatrioAnualDe = new Button(shlCadastroAssistido, SWT.NONE);
		btnRelatrioAnualDe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRelatrioAnualDe.setText("Relat\u00F3rio anual de doa\u00E7\u00F5es (ABRACE)");
		btnRelatrioAnualDe.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRelatrioAnualDe.setBounds(131, 283, 392, 68);
		
		Button btnRelatrioDeAssistidos = new Button(shlCadastroAssistido, SWT.NONE);
		btnRelatrioDeAssistidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRelatrioDeAssistidos.setText("Relat\u00F3rio de assistidos (ABRACE)");
		btnRelatrioDeAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRelatrioDeAssistidos.setBounds(131, 357, 392, 68);
		
		Button btnRelatrioDeDoadores = new Button(shlCadastroAssistido, SWT.NONE);
		btnRelatrioDeDoadores.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnRelatrioDeDoadores.setText("Relat\u00F3rio de doadores (ABRACE)");
		btnRelatrioDeDoadores.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnRelatrioDeDoadores.setBounds(131, 431, 392, 68);

	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlCadastroAssistido.dispose();
		}
	}
}
