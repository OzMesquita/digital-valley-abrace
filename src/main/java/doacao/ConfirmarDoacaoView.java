package doacao;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

public class ConfirmarDoacaoView {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConfirmarDoacaoView window = new ConfirmarDoacaoView();
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

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblDesejaConfirmarA = new Label(shell, SWT.NONE);
		lblDesejaConfirmarA.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		lblDesejaConfirmarA.setBounds(41, 32, 352, 40);
		lblDesejaConfirmarA.setText("Deseja confirmar a doa\u00E7\u00E3o?");
		
		Button btnCancelar = new Button(shell, SWT.NONE);
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		btnCancelar.setBounds(97, 108, 109, 42);
		btnCancelar.setText("Cancelar");
		
		Button btnConfirmar = new Button(shell, SWT.NONE);
		btnConfirmar.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		btnConfirmar.setBounds(212, 108, 118, 42);
		btnConfirmar.setText("Confirmar");

	}

}
