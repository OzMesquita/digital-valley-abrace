package view;

import java.io.File;

import org.eclipse.swt.SWT;
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

import control.GerenciarDoadoresJuridicosControle;

public class GerenciarDoadoresJuridicosView {
	
	GerenciarDoadoresJuridicosControle controle;

	protected Shell shlGerenciarDoadoresJuridicos;
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
	
	

	
	public GerenciarDoadoresJuridicosView() {
		this.controle = new GerenciarDoadoresJuridicosControle(this);
	}

	public GerenciarDoadoresJuridicosControle getControle() {
		return controle;
	}

	public void setControle(GerenciarDoadoresJuridicosControle controle) {
		this.controle = controle;
	}

	public Shell getShlGerenciarDoadoresJuridicos() {
		return shlGerenciarDoadoresJuridicos;
	}

	public void setShlGerenciarDoadoresJuridicos(Shell shlGerenciarDoadoresJuridicos) {
		this.shlGerenciarDoadoresJuridicos = shlGerenciarDoadoresJuridicos;
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
		Centralize.centralize(shlGerenciarDoadoresJuridicos);
		
		Label lblGerenciarDoadroesFisicos = new Label(shlGerenciarDoadoresJuridicos, SWT.NONE);
		lblGerenciarDoadroesFisicos.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblGerenciarDoadroesFisicos.setBounds(66, 28, 475, 54);
		lblGerenciarDoadroesFisicos.setText("Gerenciar Doadores Jurídicos");
		
		lblSelecioneUmDoadorFisico = new Label(shlGerenciarDoadoresJuridicos, SWT.NONE);
		lblSelecioneUmDoadorFisico.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneUmDoadorFisico.setBounds(196, 88, 334, 25);
		lblSelecioneUmDoadorFisico.setText("Selecione ou pesquise um doador jurídico");
		
		table = new Table(shlGerenciarDoadoresJuridicos, SWT.BORDER | SWT.FULL_SELECTION);
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
		
		tblclmnNomeDoDoadorFisico = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoDoadorFisico.setWidth(275);
		tblclmnNomeDoDoadorFisico.setText("Nome do Doador Jurídico");
		tblclmnNomeDoDoadorFisico.setResizable(false);
		
		tblclmnDataDeNascimento = new TableColumn(table, SWT.CENTER);
		tblclmnDataDeNascimento.setWidth(187);
		tblclmnDataDeNascimento.setText("CNPJ");
		tblclmnDataDeNascimento.setResizable(false);
		
		tblclmnCpf = new TableColumn(table, SWT.CENTER);
		tblclmnCpf.setWidth(147);
		tblclmnCpf.setText("Fantasia");
		tblclmnCpf.setResizable(false);
		
		lblPesquisarDoadorFisico = new Label(shlGerenciarDoadoresJuridicos, SWT.NONE);
		lblPesquisarDoadorFisico.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPesquisarDoadorFisico.setBounds(10, 136, 180, 30);
		lblPesquisarDoadorFisico.setText("Pesquisar Doador:");
		
		tfPesquisa = new Text(shlGerenciarDoadoresJuridicos, SWT.BORDER);
		tfPesquisa.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisa.setBounds(196, 133, 360, 36);
		
		Button btnPesquisar = new Button(shlGerenciarDoadoresJuridicos, SWT.NONE);
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
		
		btnEditar = new Button(shlGerenciarDoadoresJuridicos, SWT.NONE);
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
		btnEditar.setText("Editar Doador");
		
		btnExcluir = new Button(shlGerenciarDoadoresJuridicos, SWT.NONE);
		btnExcluir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnExcluir.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/lixeira.png").getAbsolutePath()));
		btnExcluir.setBounds(346, 551, 208, 54);
		btnExcluir.setText("Excluir Doador");
		
		btnCadastrar = new Button(shlGerenciarDoadoresJuridicos, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoadoresJuridicos.dispose();
				CadastroDoadorJuridicoView.main();
			}
		});
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido-small.png").getAbsolutePath()));
		btnCadastrar.setBounds(346, 615, 208, 54);
		btnCadastrar.setText("Cadastrar Doador");
		
		btnCancelar = new Button(shlGerenciarDoadoresJuridicos, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlGerenciarDoadoresJuridicos.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(132, 615, 208, 54);
		btnCancelar.setText("Cancelar");
		
		Label label = new Label(shlGerenciarDoadoresJuridicos, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistidos.png").getAbsolutePath()));
		label.setBounds(560, 10, 137, 109);
		
		controle.preencherTabelaDoadoresJuridico(controle.obterTodosDoadoresJuridicos());
		shlGerenciarDoadoresJuridicos.open();
		shlGerenciarDoadoresJuridicos.layout();
		while (!shlGerenciarDoadoresJuridicos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGerenciarDoadoresJuridicos = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlGerenciarDoadoresJuridicos.setSize(730, 730);
		shlGerenciarDoadoresJuridicos.setText("Gerenciar Doadores - ABRACE");
		shlGerenciarDoadoresJuridicos.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));

	}

}
