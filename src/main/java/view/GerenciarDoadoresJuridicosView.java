package view;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import control.GerenciarDoadoresFisicosControle;

public class GerenciarDoadoresJuridicosView {
	
	GerenciarDoadoresJuridicosControle controle;

	protected Shell shlGerenciarDoadoresFisicos;
	private Table table;
	private TableColumn tblclmnId;
	private TableColumn tblclmnNomeDoDoadorFisico;
	private TableColumn tblclmnDataDeNascimento;
	private TableColumn tblclmnCpf;
	private Label lblSelecioneUmDoadorFisico;
	private Label lblPesquisarDoadorFisico;
	private Text tfPesquisa;
	private Button btnCadastrar;
	private Button btnEditar;
	
	private Button btnExcluir;
	private Button btnCancelar;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			GerenciarDoadoresJuridicosView window = new GerenciarDoadoresJuridicosView();
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");

	}

}
