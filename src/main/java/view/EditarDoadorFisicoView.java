package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import model.PessoaFisica;

public class EditarDoadorFisicoView {

	protected Shell shell;

	public EditarDoadorFisicoView(PessoaFisica a) {
	}

	public EditarDoadorFisicoView() {
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditarDoadorFisicoView window = new EditarDoadorFisicoView();
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

	}

}
