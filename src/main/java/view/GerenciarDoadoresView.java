package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import control.GerenciarDoadoresControle;
import org.eclipse.swt.events.KeyAdapter;

public class GerenciarDoadoresView {
	GerenciarDoadoresControle controle;
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
	
	public GerenciarDoadoresView() {
		this.controle = new GerenciarDoadoresControle(this);
	}

	public GerenciarDoadoresControle getControle() {
		return controle;
	}

	public void setControle(GerenciarDoadoresControle controle) {
		this.controle = controle;
	}

	public Shell getShlGerenciarDoadoresFisicos() {
		return shlGerenciarDoadoresFisicos;
	}

	public void setShlGerenciarDoadoresFisicos(Shell shlGerenciarDoadoresFisicos) {
		this.shlGerenciarDoadoresFisicos = shlGerenciarDoadoresFisicos;
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

	public TableColumn getTblclmnNomeDoDoadorFisico() {
		return tblclmnNomeDoDoadorFisico;
	}

	public void setTblclmnNomeDoDoadorFisico(TableColumn tblclmnNomeDoDoadorFisico) {
		this.tblclmnNomeDoDoadorFisico = tblclmnNomeDoDoadorFisico;
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

	public Label getLblSelecioneUmDoadorFisico() {
		return lblSelecioneUmDoadorFisico;
	}

	public void setLblSelecioneUmDoadorFisico(Label lblSelecioneUmDoadorFisico) {
		this.lblSelecioneUmDoadorFisico = lblSelecioneUmDoadorFisico;
	}

	public Label getLblPesquisarDoadorFisico() {
		return lblPesquisarDoadorFisico;
	}

	public void setLblPesquisarDoadorFisico(Label lblPesquisarDoadorFisico) {
		this.lblPesquisarDoadorFisico = lblPesquisarDoadorFisico;
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

	public static void main() {
		try {
			GerenciarDoadoresView window = new GerenciarDoadoresView();
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
		Centralize.centralize(shlGerenciarDoadoresFisicos);
		
		Label lblGerenciarDoadroesFisicos = new Label(shlGerenciarDoadoresFisicos, SWT.NONE);
		lblGerenciarDoadroesFisicos.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblGerenciarDoadroesFisicos.setBounds(182, 28, 349, 54);
		lblGerenciarDoadroesFisicos.setText("Gerenciar Doadores");
		
		lblSelecioneUmDoadorFisico = new Label(shlGerenciarDoadoresFisicos, SWT.NONE);
		lblSelecioneUmDoadorFisico.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmDoadorFisico.setBounds(214, 88, 285, 25);
		lblSelecioneUmDoadorFisico.setText("Selecione ou pesquise um doador");
		
		tfPesquisa = new Text(shlGerenciarDoadoresFisicos, SWT.BORDER);
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(113, 141, 448, 36);
		
		Button btnPesquisar = new Button(shlGerenciarDoadoresFisicos, SWT.NONE);
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
		btnPesquisar.setBounds(565, 141, 144, 36);
		btnPesquisar.setText("Pesquisar");
		
		table = new Table(shlGerenciarDoadoresFisicos, SWT.BORDER | SWT.FULL_SELECTION);
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
		table.setBounds(15, 183, 694, 385);
		table.setHeaderVisible(true);
		
		tblclmnId = new TableColumn(table, SWT.CENTER);
		tblclmnId.setWidth(47);
		tblclmnId.setResizable(false);
		
		tblclmnNomeDoDoadorFisico = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoDoadorFisico.setWidth(250);
		tblclmnNomeDoDoadorFisico.setText("Nome do Doador");
		tblclmnNomeDoDoadorFisico.setResizable(false);
		
		tblclmnDataDeNascimento = new TableColumn(table, SWT.CENTER);
		tblclmnDataDeNascimento.setWidth(195);
		tblclmnDataDeNascimento.setText("CPF / CNPJ");
		tblclmnDataDeNascimento.setResizable(false);
		
		tblclmnCpf = new TableColumn(table, SWT.CENTER);
		tblclmnCpf.setWidth(186);
		tblclmnCpf.setText("Endere\u00E7o");
		tblclmnCpf.setResizable(false);
		
		lblPesquisarDoadorFisico = new Label(shlGerenciarDoadoresFisicos, SWT.NONE);
		lblPesquisarDoadorFisico.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarDoadorFisico.setBounds(15, 144, 92, 30);
		lblPesquisarDoadorFisico.setText("Pesquisar:");
		
		btnEditar = new Button(shlGerenciarDoadoresFisicos, SWT.NONE);
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
		btnEditar.setBounds(346, 588, 223, 54);
		btnEditar.setText("Editar Doador");
		
		btnExcluir = new Button(shlGerenciarDoadoresFisicos, SWT.NONE);
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
		btnExcluir.setBounds(117, 588, 223, 54);
		btnExcluir.setText("Excluir Doador");
		
		btnCadastrar = new Button(shlGerenciarDoadoresFisicos, SWT.NONE);
		btnCadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoadoresFisicos.dispose();
				SelecionarTipoDoadorView.main();
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doador-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(346, 652, 223, 54);
		btnCadastrar.setText("Cadastrar Doador");
		
		btnCancelar = new Button(shlGerenciarDoadoresFisicos, SWT.NONE);
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoadoresFisicos.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(117, 652, 223, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarDoadoresFisicos, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistidos.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		controle.preencherTabelaDoadores(controle.obterTodosDoadores());
		shlGerenciarDoadoresFisicos.open();
		shlGerenciarDoadoresFisicos.layout();
		while (!shlGerenciarDoadoresFisicos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlGerenciarDoadoresFisicos = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlGerenciarDoadoresFisicos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlGerenciarDoadoresFisicos.setSize(730, 762);
		shlGerenciarDoadoresFisicos.setText("Gerenciar Assistidos - ABRACE");
		shlGerenciarDoadoresFisicos.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlGerenciarDoadoresFisicos.dispose();
		}
	}
}