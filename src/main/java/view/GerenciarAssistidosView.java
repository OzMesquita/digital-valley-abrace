package view;

import java.io.File;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import control.GerenciarAssistidosControle;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GerenciarAssistidosView {
	
	GerenciarAssistidosControle controle;

	protected Shell shlGerenciarAssistidos;
	private Table table;
	private TableColumn tblclmnId;
	private TableColumn tblclmnNomeDoAssistido;
	private TableColumn tblclmnDataDeNascimento;
	private TableColumn tblclmnCpf;
	private Label lblSelecioneUmAssistido;
	private Label lblPesquisarAssistido;
	private Text tfPesquisa;
	private Button btnCadastrar;
	private Button btnEditar;
	
	private Button btnExcluir;
	private Button btnCancelar;
	
	public GerenciarAssistidosView() {
		this.controle = new GerenciarAssistidosControle(this);
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TableColumn getTblclmnId() {
		return tblclmnId;
	}

	public void setTblclmnId(TableColumn tblclmnId) {
		this.tblclmnId = tblclmnId;
	}

	public TableColumn getTblclmnNomeDoAssistido() {
		return tblclmnNomeDoAssistido;
	}

	public void setTblclmnNomeDoAssistido(TableColumn tblclmnNomeDoAssistido) {
		this.tblclmnNomeDoAssistido = tblclmnNomeDoAssistido;
	}

	public TableColumn getTblclmnDataDeNascimento() {
		return tblclmnDataDeNascimento;
	}

	public void setTblclmnDataDeNascimento(TableColumn tblclmnDataDeNascimento) {
		this.tblclmnDataDeNascimento = tblclmnDataDeNascimento;
	}

	public TableColumn getTblclmnCpf() {
		return tblclmnCpf;
	}

	public void setTblclmnCpf(TableColumn tblclmnCpf) {
		this.tblclmnCpf = tblclmnCpf;
	}

	public Label getLblSelecioneUmAssistido() {
		return lblSelecioneUmAssistido;
	}

	public void setLblSelecioneUmAssistido(Label lblSelecioneUmAssistido) {
		this.lblSelecioneUmAssistido = lblSelecioneUmAssistido;
	}

	public Label getLblPesquisarAssistido() {
		return lblPesquisarAssistido;
	}

	public void setLblPesquisarAssistido(Label lblPesquisarAssistido) {
		this.lblPesquisarAssistido = lblPesquisarAssistido;
	}

	public Text getTfPesquisa() {
		return tfPesquisa;
	}

	public void setTfPesquisa(Text tfPesquisa) {
		this.tfPesquisa = tfPesquisa;
	}

	public Button getBtnCadastrar() {
		return btnCadastrar;
	}

	public void setBtnCadastrar(Button btnCadastrar) {
		this.btnCadastrar = btnCadastrar;
	}

	public Button getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(Button btnEditar) {
		this.btnEditar = btnEditar;
	}

	public Button getBtnExcluir() {
		return btnExcluir;
	}

	public void setBtnExcluir(Button btnExcluir) {
		this.btnExcluir = btnExcluir;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
	public Shell getShlGerenciarAssistidos() {
		return shlGerenciarAssistidos;
	}

	public void setShlGerenciarAssistidos(Shell shlGerenciarAssistidos) {
		this.shlGerenciarAssistidos = shlGerenciarAssistidos;
	}

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			GerenciarAssistidosView window = new GerenciarAssistidosView();
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
		Centralize.centralize(shlGerenciarAssistidos);
		
		Label lblGerenciarAssistidos = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblGerenciarAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblGerenciarAssistidos.setBounds(180, 28, 354, 54);
		lblGerenciarAssistidos.setText("Gerenciar Assistidos");
		
		lblSelecioneUmAssistido = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblSelecioneUmAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmAssistido.setBounds(209, 88, 296, 25);
		lblSelecioneUmAssistido.setText("Selecione ou pesquise um assistido");
		
		table = new Table(shlGerenciarAssistidos, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		});
		table.setLinesVisible(true);
		table.setToolTipText("");
		table.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		table.setBounds(10, 175, 694, 356);
		table.setHeaderVisible(true);
		
		tblclmnId = new TableColumn(table, SWT.CENTER);
		tblclmnId.setWidth(47);
		tblclmnId.setText("ID");
		tblclmnId.setResizable(false);
		
		tblclmnNomeDoAssistido = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoAssistido.setWidth(275);
		tblclmnNomeDoAssistido.setText("Nome do Assistido");
		tblclmnNomeDoAssistido.setResizable(false);
		
		tblclmnDataDeNascimento = new TableColumn(table, SWT.CENTER);
		tblclmnDataDeNascimento.setWidth(187);
		tblclmnDataDeNascimento.setText("Data de Nascimento");
		tblclmnDataDeNascimento.setResizable(false);
		
		tblclmnCpf = new TableColumn(table, SWT.CENTER);
		tblclmnCpf.setWidth(147);
		tblclmnCpf.setText("CPF");
		tblclmnCpf.setResizable(false);
		
		lblPesquisarAssistido = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblPesquisarAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarAssistido.setBounds(10, 136, 180, 30);
		lblPesquisarAssistido.setText("Pesquisar Assistido:");
		
		tfPesquisa = new Text(shlGerenciarAssistidos, SWT.BORDER);
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(196, 133, 360, 36);
		
		Button btnPesquisar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnPesquisar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnPesquisar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pesquisar.png").getAbsolutePath()));
		btnPesquisar.setBounds(560, 133, 144, 36);
		btnPesquisar.setText("Pesquisar");
		
		btnEditar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnEditar.setEnabled(false);
		btnEditar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnEditar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/editar.png").getAbsolutePath()));
		btnEditar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnEditar.setBounds(132, 551, 208, 54);
		btnEditar.setText("Editar Assistido");
		
		btnExcluir = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnExcluir.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/lixeira.png").getAbsolutePath()));
		btnExcluir.setBounds(346, 551, 208, 54);
		btnExcluir.setText("Excluir Assistido");
		
		btnCadastrar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarAssistidos.dispose();
				CadastroAssistidoView.main();
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(346, 615, 208, 54);
		btnCadastrar.setText("Cadastrar Assistido");
		
		btnCancelar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarAssistidos.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(132, 615, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarAssistidos, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistidos.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		controle.preencherTabelaAssistidos(controle.obterTodosAssistidos());
		
		shlGerenciarAssistidos.open();
		shlGerenciarAssistidos.layout();
		while (!shlGerenciarAssistidos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGerenciarAssistidos = new Shell();
		shlGerenciarAssistidos.setSize(730, 730);
		shlGerenciarAssistidos.setText("Gerenciar Assistidos - ABRACE");
		shlGerenciarAssistidos.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));

	}
}