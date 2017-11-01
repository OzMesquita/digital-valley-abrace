package doacao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import control.DoacaoControle;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;

public class InserirValorView {

	protected Shell shell;
	private Text text;
	private DateTime tfDataDoacao;
	DoacaoControle controle;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InserirValorView window = new InserirValorView();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public DateTime getTfDataDoacao() {
		return tfDataDoacao;
	}

	public void setTfDataDoacao(DateTime tfDataDoacao) {
		this.tfDataDoacao = tfDataDoacao;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(662, 461);
		shell.setText("SWT Application");
		
		Label lblInserirValor = new Label(shell, SWT.NONE);
		lblInserirValor.setFont(SWTResourceManager.getFont("Segoe UI", 26, SWT.NORMAL));
		lblInserirValor.setBounds(136, 28, 373, 47);
		lblInserirValor.setText("Insira o valor da doa\u00E7\u00E3o");
		
		Label lblValor = new Label(shell, SWT.NONE);
		lblValor.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lblValor.setBounds(136, 147, 67, 37);
		lblValor.setText("Valor:");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(209, 147, 332, 37);
		
		Label lblDataDaDoao = new Label(shell, SWT.NONE);
		lblDataDaDoao.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.NORMAL));
		lblDataDaDoao.setBounds(9, 190, 194, 37);
		lblDataDaDoao.setText("Data da doa\u00E7\u00E3o:");
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		dateTime.setBounds(209, 190, 332, 37);
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 26, SWT.NORMAL));
		btnCancelar.setBounds(229, 250, 144, 57);
		btnCancelar.setText("Cancelar");
		
		Button btnAvanar = new Button(shell, SWT.NONE);
		btnAvanar.setFont(SWTResourceManager.getFont("Segoe UI", 26, SWT.NORMAL));
		btnAvanar.setBounds(379, 250, 144, 57);
		btnAvanar.setText("Avan\u00E7ar");

	}
}
