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
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class GerenciarAssistidosView {
	GerenciarAssistidosControle controle;
	private Shell shlGerenciarAssistidos;
	private Table table;
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
	private Table table_1;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	
	public GerenciarAssistidosView() {
		this.controle = new GerenciarAssistidosControle(this);
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
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

	public static void main() {
		try {
			GerenciarAssistidosView window = new GerenciarAssistidosView();
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
		Centralize.centralize(shlGerenciarAssistidos);
		
		Label lblGerenciarAssistidos = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblGerenciarAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblGerenciarAssistidos.setBounds(180, 28, 354, 54);
		lblGerenciarAssistidos.setText("Gerenciar Assistidos");
		
		lblSelecioneUmAssistido = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblSelecioneUmAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmAssistido.setBounds(209, 88, 296, 25);
		lblSelecioneUmAssistido.setText("Selecione ou pesquise um assistido");
		
		tfPesquisa = new Text(shlGerenciarAssistidos, SWT.BORDER);
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(196, 133, 360, 36);
		
		Button btnPesquisar = new Button(shlGerenciarAssistidos, SWT.NONE);
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
		
		table = new Table(shlGerenciarAssistidos, SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				controle.getMouseEvent(arg0);
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
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
		table.setBounds(10, 206, 694, 320);
		
		tblclmnNomeDoAssistido = new TableColumn(table, SWT.LEFT);
		tblclmnNomeDoAssistido.setWidth(275);
		tblclmnNomeDoAssistido.setText("Nome do Assistido");
		tblclmnNomeDoAssistido.setResizable(false);
		
		tblclmnDataDeNascimento = new TableColumn(table, SWT.CENTER);
		tblclmnDataDeNascimento.setWidth(187);
		tblclmnDataDeNascimento.setText("Data de Nascimento");
		tblclmnDataDeNascimento.setResizable(false);
		
		tblclmnCpf = new TableColumn(table, SWT.CENTER);
		tblclmnCpf.setWidth(227);
		tblclmnCpf.setText("CPF");
		tblclmnCpf.setResizable(false);
		
		lblPesquisarAssistido = new Label(shlGerenciarAssistidos, SWT.NONE);
		lblPesquisarAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarAssistido.setBounds(10, 136, 180, 30);
		lblPesquisarAssistido.setText("Pesquisar Assistido:");
		
		btnEditar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnEditar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnEditar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/editar.png").getAbsolutePath()));
		btnEditar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnEditar.setBounds(372, 551, 208, 54);
		btnEditar.setText("Editar Assistido");
		
		btnExcluir = new Button(shlGerenciarAssistidos, SWT.NONE);
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
		btnExcluir.setBounds(152, 551, 208, 54);
		btnExcluir.setText("Excluir Assistido");
		
		btnCadastrar = new Button(shlGerenciarAssistidos, SWT.NONE);
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
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(372, 611, 208, 54);
		btnCadastrar.setText("Cadastrar Assistido");
		
		btnCancelar = new Button(shlGerenciarAssistidos, SWT.NONE);
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarAssistidos.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(152, 611, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarAssistidos, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistidos.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		table_1 = new Table(shlGerenciarAssistidos, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setToolTipText("");
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setFont(SWTResourceManager.getFont("Segoe UI Semibold", 14, SWT.NORMAL));
		table_1.setBounds(10, 175, 694, 36);
		
		tableColumn_1 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_1.setWidth(275);
		tableColumn_1.setText("Nome do Assistido");
		tableColumn_1.setResizable(false);
		
		tableColumn_2 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_2.setWidth(187);
		tableColumn_2.setText("Data de Nascimento");
		tableColumn_2.setResizable(false);
		
		tableColumn_3 = new TableColumn(table_1, SWT.CENTER);
		tableColumn_3.setWidth(227);
		tableColumn_3.setText("CPF");
		tableColumn_3.setResizable(false);
		
		controle.preencherTabelaAssistidos(controle.obterTodosAssistidos());
		
		shlGerenciarAssistidos.open();
		shlGerenciarAssistidos.layout();
		while (!shlGerenciarAssistidos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlGerenciarAssistidos = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlGerenciarAssistidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlGerenciarAssistidos.setSize(730, 713);
		shlGerenciarAssistidos.setText("Gerenciar Assistidos - ABRACE");
		shlGerenciarAssistidos.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlGerenciarAssistidos.dispose();
		}
	}
}