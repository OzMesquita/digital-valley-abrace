package view;

import java.io.File;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import control.GerenciarDoacoesControle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Combo;

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
	private Label lblTotalDeDoaes;
	private Label lblPesquisarData;
	private Button btnLimparBusca;
	private Label lblmsano;
	private Text txAno;
	private Label lblAno;
	private Combo cmbMes;
	private Label label_1;
	private Button btnEmitirRecibo;

	public Combo getCmbMes() {
		return cmbMes;
	}

	public void setCmbMes(Combo cmbMes) {
		this.cmbMes = cmbMes;
	}

	public void setLblTotalDeDoaes(String lblTotalDeDoaes) {
		this.lblTotalDeDoaes.setText(lblTotalDeDoaes);
	}

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
		lblGerenciarDoacoes.setBounds(197, 16, 329, 54);
		lblGerenciarDoacoes.setText("Gerenciar Doações");
		
		lblSelecioneUmDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblSelecioneUmDoador.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmDoador.setBounds(207, 100, 309, 25);
		lblSelecioneUmDoador.setText("Voc\u00EA pode utilizar os filtros de busca");
		
		tfPesquisa = new Text(shlGerenciarDoacoes, SWT.BORDER);
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(180, 134, 374, 36);
		
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
		btnPesquisar.setBounds(560, 134, 144, 36);
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
				btnEmitirRecibo.setEnabled(true);
			}
		});
		table.setLinesVisible(true);
		table.setToolTipText("");
		table.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		table.setBounds(10, 255, 694, 234);
		
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
		tblclmnDataDoacao.setText("Data da doação");
		
		lblPesquisarDoador = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblPesquisarDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarDoador.setBounds(10, 134, 167, 30);
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
		btnExcluir.setBounds(155, 544, 208, 54);
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
				controle.getEvent(arg0);
				shlGerenciarDoacoes.dispose();
				
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacao-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(369, 604, 208, 54);
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
		btnCancelar.setBounds(155, 604, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarDoacoes, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacoes.png").getAbsolutePath()));
		label.setBounds(560, 15, 137, 109);
		
		table_1 = new Table(shlGerenciarDoacoes, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setToolTipText("");
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 14, SWT.NORMAL));
		table_1.setBounds(10, 224, 694, 36);
		
		tableColumn_1 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_1.setWidth(276);
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
		
		Label lblporNome = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblporNome.setBounds(62, 160, 60, 15);
		lblporNome.setText("(por nome)");
		
		lblTotalDeDoaes = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblTotalDeDoaes.setAlignment(SWT.CENTER);
		lblTotalDeDoaes.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblTotalDeDoaes.setBounds(10, 502, 694, 25);
		
		lblPesquisarData = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblPesquisarData.setText("Filtrar:");
		lblPesquisarData.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarData.setBounds(117, 178, 57, 30);
		
		btnLimparBusca = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnLimparBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnLimparBusca.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnLimparBusca.setText("Limpar Busca");
		btnLimparBusca.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnLimparBusca.setBounds(560, 178, 144, 36);
		
		cmbMes = new Combo(shlGerenciarDoacoes, SWT.READ_ONLY);
		cmbMes.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		cmbMes.setItems(new String[] {"Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"});
		cmbMes.setBounds(180, 178, 183, 38);
		
		lblmsano = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblmsano.setText("(m\u00EAs/ano)");
		lblmsano.setBounds(118, 205, 55, 15);
		
		txAno = new Text(shlGerenciarDoacoes, SWT.BORDER);
		txAno.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		txAno.setBounds(426, 178, 128, 36);
		
		lblAno = new Label(shlGerenciarDoacoes, SWT.NONE);
		lblAno.setText("Ano:");
		lblAno.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblAno.setBounds(377, 181, 43, 30);
		
		label_1 = new Label(shlGerenciarDoacoes, SWT.NONE);
		label_1.setText("Selecione ou pesquise uma doa\u00E7\u00E3o");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		label_1.setBounds(214, 72, 296, 25);
		
		btnEmitirRecibo = new Button(shlGerenciarDoacoes, SWT.NONE);
		btnEmitirRecibo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnEmitirRecibo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnEmitirRecibo.setText("Emitir recibo");
		btnEmitirRecibo.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\Desktop\\src\\main\\java\\view\\img\\lixeira.png"));
		btnEmitirRecibo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnEmitirRecibo.setEnabled(false);
		btnEmitirRecibo.setBounds(369, 544, 208, 54);
		
		controle.preencherTabelaDoacoes(controle.obterTodasDoacoes());
		
		shlGerenciarDoacoes.open();
		shlGerenciarDoacoes.layout();
		while (!shlGerenciarDoacoes.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Text getTxAno() {
		return txAno;
	}

	public void setTxAno(Text txAno) {
		this.txAno = txAno;
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
		shlGerenciarDoacoes.setText("Gerenciar Doações - ABRACE");
		shlGerenciarDoacoes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlGerenciarDoacoes.dispose();
		}
	}
	
	public void mensagemErro(Exception e){
		MessageBox messageBox = new MessageBox(shlGerenciarDoacoes,SWT.ICON_ERROR | SWT.OK); 
		messageBox.setText("Problemas ao filtrar doações!");
		messageBox.setMessage(e.getMessage());
		messageBox.open();
	}
}