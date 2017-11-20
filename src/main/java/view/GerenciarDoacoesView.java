package view;

import java.io.File;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import control.GerenciarDoacoesControle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GerenciarDoacoesView {
	GerenciarDoacoesControle controle;
	protected Shell shlGerenciarDoacoes;
	private Table table;
	private TableColumn tblclmnId;
	private TableColumn tblclmnNomeDoDoador;
	private TableColumn tblclmnDataDoacao;
	private TableColumn tblclmnValor;
	private Label lblSelecioneUmDoador;
	private Label lblPesquisarDoador;
	private Text tfPesquisa;
	private Button btnCadastrar;
	private Button btnExcluir;
	private Button btnCancelar;
	
	public GerenciarDoacoesView() {
		this.controle = new GerenciarDoacoesControle(this);
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

	public TableColumn getTblclmnNomeDoDoador() {
		return tblclmnNomeDoDoador;
	}

	public void setTblclmnNomeDoDoador(TableColumn tblclmnNomeDoDoador) {
		this.tblclmnNomeDoDoador = tblclmnNomeDoDoador;
	}

	public TableColumn getTblclmnDataDoacao() {
		return tblclmnDataDoacao;
	}

	public void setTblclmnDataDoacao(TableColumn tblclmnDataDoacao) {
		this.tblclmnDataDoacao = tblclmnDataDoacao;
	}
	
	public TableColumn getTblclmnValor() {
		return tblclmnValor;
	}

	public void setTblclmnValor(TableColumn tblclmnValor) {
		this.tblclmnValor = tblclmnValor;
	}

	public Label getLblSelecioneUmDoador() {
		return lblSelecioneUmDoador;
	}

	public void setLblSelecioneUmDoador(Label lblSelecioneUmDoador) {
		this.lblSelecioneUmDoador = lblSelecioneUmDoador;
	}

	public Label getLblPesquisarDoador() {
		return lblPesquisarDoador;
	}

	public void setLblPesquisarDoador(Label lblPesquisarDoador) {
		this.lblPesquisarDoador = lblPesquisarDoador;
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
	
	public Shell getShlGerenciarDoacoes() {
		return shlGerenciarDoacoes;
	}

	public void setShlGerenciarDoacoes(Shell shlGerenciarDoacoes) {
		this.shlGerenciarDoacoes = shlGerenciarDoacoes;
	}

	public static void main() {
		try {
			GerenciarDoacoesView window = new GerenciarDoacoesView();
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
		Centralize.centralize(shlGerenciarDoacoes);
		
		Label lblGerenciarDoacoes = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblGerenciarDoacoes.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblGerenciarDoacoes.setBounds(180, 28, 354, 54);
		lblGerenciarDoacoes.setText("Gerenciar Doações");
		
		lblSelecioneUmDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblSelecioneUmDoador.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmDoador.setBounds(209, 88, 296, 25);
		lblSelecioneUmDoador.setText("Selecione ou pesquise uma doa\u00E7\u00E3o");
		
		table = new Table(shlGerenciarDoacoes, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
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
		tblclmnId.setResizable(false);
		
		tblclmnNomeDoDoador = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoDoador.setWidth(275);
		tblclmnNomeDoDoador.setText("Nome do Doador");
		tblclmnNomeDoDoador.setResizable(false);
		
		tblclmnValor = new TableColumn(table, SWT.CENTER);
		tblclmnValor.setWidth(147);
		tblclmnValor.setText("Valor");
		tblclmnValor.setResizable(false);
		
		tblclmnDataDoacao = new TableColumn(table, SWT.CENTER);
		tblclmnDataDoacao.setWidth(222);
		tblclmnDataDoacao.setText("Data da doação");
		tblclmnDataDoacao.setResizable(false);
		
		lblPesquisarDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblPesquisarDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarDoador.setBounds(10, 136, 180, 30);
		lblPesquisarDoador.setText("Pesquisar Doador:");
		
		tfPesquisa = new Text(shlGerenciarDoacoes, SWT.BORDER);
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(196, 133, 360, 36);
		
		Button btnPesquisar = new Button(shlGerenciarDoacoes, SWT.NONE);
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
		
		btnExcluir = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnExcluir.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/lixeira.png").getAbsolutePath()));
		btnExcluir.setBounds(158, 615, 208, 54);
		btnExcluir.setText("Excluir doa\u00E7\u00E3o");
		
		btnCadastrar = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoacoes.dispose();
				SelecionarDoadorView.main();
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacao-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(372, 550, 208, 54);
		btnCadastrar.setText("Realizar doa\u00E7\u00E3o");
		
		btnCancelar = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoacoes.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(158, 550, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarDoacoes, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacoes.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		controle.preencherTabelaDoacoes(controle.obterTodasDoacoes());
		
		shlGerenciarDoacoes.open();
		shlGerenciarDoacoes.layout();
		while (!shlGerenciarDoacoes.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlGerenciarDoacoes = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlGerenciarDoacoes.setSize(730, 730);
		shlGerenciarDoacoes.setText("Gerenciar Doações - ABRACE");
		shlGerenciarDoacoes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
	}
}