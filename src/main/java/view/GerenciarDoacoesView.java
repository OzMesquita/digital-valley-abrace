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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class GerenciarDoacoesView {
	GerenciarDoacoesControle controle;
	protected Shell shlGerenciarDoacoes;
	private Table table;
	private TableColumn tblclmnNomeDoDoador;
	private TableColumn tblclmnDataDoacao;
	private TableColumn tblclmnValor;
	private Label lblSelecioneUmDoador;
	private Label lblPesquisarDoador;
	private Text tfPesquisa;
	private Button btnCadastrar;
	private Button btnExcluir;
	private Button btnCancelar;
	private Table table_1;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	
	public GerenciarDoacoesView() {
		this.controle = new GerenciarDoacoesControle(this);
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
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
		lblGerenciarDoacoes.setText("Gerenciar Doa��es");
		
		lblSelecioneUmDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblSelecioneUmDoador.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmDoador.setBounds(209, 88, 296, 25);
		lblSelecioneUmDoador.setText("Selecione ou pesquise uma doa\u00E7\u00E3o");
		
		tfPesquisa = new Text(shlGerenciarDoacoes, SWT.BORDER);
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(196, 133, 360, 36);
		
		Button btnPesquisar = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
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
		
		table = new Table(shlGerenciarDoacoes, SWT.BORDER | SWT.FULL_SELECTION | SWT.NO_SCROLL | SWT.V_SCROLL);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				btnExcluir.setEnabled(true);
			}
		});
		table.setLinesVisible(true);
		table.setToolTipText("");
		table.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		table.setBounds(10, 206, 672, 322);
		
		tblclmnNomeDoDoador = new TableColumn(table, SWT.LEFT);
		tblclmnNomeDoDoador.setWidth(275);
		tblclmnNomeDoDoador.setText("Nome do Doador");
		tblclmnNomeDoDoador.setResizable(false);
		
		tblclmnValor = new TableColumn(table, SWT.CENTER);
		tblclmnValor.setResizable(false);
		tblclmnValor.setWidth(144);
		tblclmnValor.setText("Valor");
		
		tblclmnDataDoacao = new TableColumn(table, SWT.CENTER);
		tblclmnDataDoacao.setResizable(false);
		tblclmnDataDoacao.setWidth(250);
		tblclmnDataDoacao.setText("Data da doa��o");
		
		lblPesquisarDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblPesquisarDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarDoador.setBounds(10, 136, 180, 30);
		lblPesquisarDoador.setText("Pesquisar Doador:");
		
		btnExcluir = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnExcluir.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnExcluir.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/lixeira.png").getAbsolutePath()));
		btnExcluir.setBounds(158, 550, 208, 54);
		btnExcluir.setText("Excluir doa\u00E7\u00E3o");
		
		btnCadastrar = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnCadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
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
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoacoes.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(158, 610, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarDoacoes, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacoes.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		table_1 = new Table(shlGerenciarDoacoes, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setToolTipText("");
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 14, SWT.NORMAL));
		table_1.setBounds(10, 175, 672, 36);
		
		tableColumn_1 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_1.setWidth(275);
		tableColumn_1.setText("Nome do Doador");
		tableColumn_1.setResizable(false);
		
		tableColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_2.setWidth(144);
		tableColumn_2.setText("Valor");
		tableColumn_2.setResizable(false);
		
		tableColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_3.setWidth(249);
		tableColumn_3.setText("Data da doa\u00E7\u00E3o");
		tableColumn_3.setResizable(false);
		
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
		shlGerenciarDoacoes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlGerenciarDoacoes.setSize(730, 711);
		shlGerenciarDoacoes.setText("Gerenciar Doa��es - ABRACE");
		shlGerenciarDoacoes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlGerenciarDoacoes.dispose();
		}
	}
}