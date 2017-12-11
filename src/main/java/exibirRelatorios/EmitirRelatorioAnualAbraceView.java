package exibirRelatorios;

import java.io.File;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import view.Centralize;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EmitirRelatorioAnualAbraceView {

	protected Shell shlEmitirRelatrioDe;
	private Text tfAno;
	private EmitirRelatorioAnualAbraceControle controle;
	
	public EmitirRelatorioAnualAbraceControle getControle() {
		return controle;
	}
	
	public void setControle(EmitirRelatorioAnualAbraceControle controle) {
		this.controle = controle;
	}

	public EmitirRelatorioAnualAbraceView() {
		this.controle = new EmitirRelatorioAnualAbraceControle(this);
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			EmitirRelatorioAnualAbraceView window = new EmitirRelatorioAnualAbraceView();
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
		shlEmitirRelatrioDe.open();
		shlEmitirRelatrioDe.layout();
		Centralize.centralize(shlEmitirRelatrioDe);
		while (!shlEmitirRelatrioDe.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlEmitirRelatrioDe = new Shell();
		shlEmitirRelatrioDe.setSize(730, 407);
		shlEmitirRelatrioDe.setText("Emitir relat\u00F3rio anual de doa\u00E7\u00F5es - ABRACE");
		shlEmitirRelatrioDe.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		
		Label label = new Label(shlEmitirRelatrioDe, SWT.NONE);
		label.setText("Relat\u00F3rio de Doa\u00E7\u00F5es");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		label.setBounds(182, 10, 349, 50);
		
		Label label_1 = new Label(shlEmitirRelatrioDe, SWT.NONE);
		label_1.setText("ONG ABRACE");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label_1.setBounds(292, 60, 129, 30);
		
		Label label_2 = new Label(shlEmitirRelatrioDe, SWT.NONE);
		label_2.setText("Informe o ano para emiss\u00E3o do relat\u00F3rio:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label_2.setBounds(112, 190, 378, 30);
		
		tfAno = new Text(shlEmitirRelatrioDe, SWT.BORDER | SWT.CENTER);
		tfAno.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfAno.setBounds(496, 187, 92, 36);
		
		Button button = new Button(shlEmitirRelatrioDe, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button.setBounds(140, 271, 214, 54);
		
		Label lblEsteRelatrioMostra = new Label(shlEmitirRelatrioDe, SWT.NONE);
		lblEsteRelatrioMostra.setAlignment(SWT.CENTER);
		lblEsteRelatrioMostra.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblEsteRelatrioMostra.setBounds(158, 103, 397, 50);
		lblEsteRelatrioMostra.setText("Este relat\u00F3rio lista todas as doa\u00E7\u00F5es que a ONG\r\nrecebeu durante o ano informado");
		
		Button btnSalvarAlteraes = new Button(shlEmitirRelatrioDe, SWT.NONE);
		btnSalvarAlteraes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnSalvarAlteraes.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnSalvarAlteraes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarAlteraes.setBounds(360, 271, 214, 54);
		btnSalvarAlteraes.setText("Gerar relat\u00F3rio");

	}

	public Shell getShlEmitirRelatrioDe() {
		return shlEmitirRelatrioDe;
	}

	public void setShlEmitirRelatrioDe(Shell shlEmitirRelatrioDe) {
		this.shlEmitirRelatrioDe = shlEmitirRelatrioDe;
	}

	public Text getTfAno() {
		return tfAno;
	}

	public void setTfAno(Text tfAno) {
		this.tfAno = tfAno;
	}
}
