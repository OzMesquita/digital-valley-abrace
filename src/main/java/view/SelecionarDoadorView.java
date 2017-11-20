package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import control.DoacaoControle;
import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class  SelecionarDoadorView{
	protected Shell shlDoacao;
	private PessoaFisica doadorFisico;
	private PessoaJuridica doadorJuridico;
	private Doacao doacao;
	private Combo tfAtivo;
	private Text tfPesquisar;
	private Table table;
	private DoacaoControle controle;
	
	public PessoaFisica getDoadorFisico() {
		return doadorFisico;
	}
	
	public void setDoadorFisico(PessoaFisica doadorFisico) {
		this.doadorFisico = doadorFisico;
	}
	
	public PessoaJuridica getDoadorJuridico() {
		return doadorJuridico;
	}
	
	public void setDoadorJuridico(PessoaJuridica doadorJuridico) {
		this.doadorJuridico = doadorJuridico;
	}
	
	public boolean getTfAtivo() {
		if(tfAtivo.getSelectionIndex() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public Doacao getDoacao() {
		return doacao;
	}
	
	public void setDoacao(Doacao doacao) {
		this.doacao = doacao;
	}
	
	public DoacaoControle getControle() {
		return controle;
	}
	
	public void setControle(DoacaoControle controle) {
		this.controle = controle;
	}

	public Shell getShlDoacao() {
		return shlDoacao;
	}

	public void setShlDoacao(Shell shlDoacao) {
		this.shlDoacao = shlDoacao;
	}
	
	public Text getTfPesquisar() {
		return tfPesquisar;
	}

	public void setTfPesquisar(Text tfPesquisar) {
		this.tfPesquisar = tfPesquisar;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setTfAtivo(Combo tfAtivo) {
		this.tfAtivo = tfAtivo;
	}

	public SelecionarDoadorView(PessoaFisica doadorFisico) {
		if (doadorFisico == null) {
			this.setDoadorFisico(doadorFisico);
		}
		this.setControle(new DoacaoControle(this));
	}
	
	public SelecionarDoadorView(PessoaJuridica doadorJuridico) {
		if (doadorJuridico == null) {
			this.setDoadorJuridico(doadorJuridico);
		}
		this.setControle(new DoacaoControle(this));
	}
	
	public SelecionarDoadorView() {
		this.setControle(new DoacaoControle(this));
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlDoacao);
		
		Label lblRealizarDoao = new Label(shlDoacao, SWT.NONE);
		lblRealizarDoao.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblRealizarDoao.setBounds(142, 28, 280, 50);
		lblRealizarDoao.setText("Registrar Doa\u00E7\u00E3o");
		
		Label lblParaRegistrarUma = new Label(shlDoacao, SWT.NONE);
		lblParaRegistrarUma.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblParaRegistrarUma.setBounds(27, 82, 511, 25);
		lblParaRegistrarUma.setText("Para registrar uma doa\u00E7\u00E3o, \u00E9 necess\u00E1rio informar um doador");
		
		Label lblSelecioneOuPesquise = new Label(shlDoacao, SWT.NONE);
		lblSelecioneOuPesquise.setText("Selecione ou pesquise um doador e clique em \"continuar\"");
		lblSelecioneOuPesquise.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneOuPesquise.setBounds(39, 113, 487, 25);
		
		Label label_1 = new Label(shlDoacao, SWT.NONE);
		label_1.setText("Pesquisar:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label_1.setBounds(10, 166, 92, 30);
		
		tfPesquisar = new Text(shlDoacao, SWT.BORDER);
		tfPesquisar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisar.setBounds(108, 163, 448, 36);
		
		Button btnPesquisar = new Button(shlDoacao, SWT.NONE);
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pesquisar.png").getAbsolutePath()));
		btnPesquisar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnPesquisar.setBounds(560, 163, 144, 36);
		
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		
		table = new Table(shlDoacao, SWT.BORDER | SWT.FULL_SELECTION);
		table.setToolTipText("");
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		table.setBounds(10, 205, 694, 356);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(47);
		tableColumn.setText("ID");
		tableColumn.setResizable(false);
		
		TableColumn tblclmnNomeDoDoador = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoDoador.setWidth(275);
		tblclmnNomeDoDoador.setText("Nome do Doador");
		tblclmnNomeDoDoador.setResizable(false);
		
		TableColumn tblclmnCpfCnpj_1 = new TableColumn(table, SWT.CENTER);
		tblclmnCpfCnpj_1.setWidth(155);
		tblclmnCpfCnpj_1.setText("CPF / CNPJ");
		tblclmnCpfCnpj_1.setResizable(false);
		
		TableColumn tblclmnCpfCnpj = new TableColumn(table, SWT.CENTER);
		tblclmnCpfCnpj.setWidth(200);
		tblclmnCpfCnpj.setText("Endere\u00E7o");
		tblclmnCpfCnpj.setResizable(false);
		
		Button btnCancelar = new Button(shlDoacao, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlDoacao.dispose();
			}
		});
		btnCancelar.setText("Cancelar");
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(140, 590, 214, 54);
		
		Button btnCadastrar = new Button(shlDoacao, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnCadastrar.setText("Cadastrar doador");
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido-small.png").getAbsolutePath()));
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCadastrar.setBounds(360, 650, 214, 54);
		
		Button btnPrximo = new Button(shlDoacao, SWT.NONE);
		btnPrximo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnPrximo.setText("Continuar");
		btnPrximo.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/botao-proximo.png").getAbsolutePath()));
		btnPrximo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnPrximo.setBounds(360, 590, 214, 54);
		
		Label lblNoEncontrouTente = new Label(shlDoacao, SWT.NONE);
		lblNoEncontrouTente.setAlignment(SWT.RIGHT);
		lblNoEncontrouTente.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblNoEncontrouTente.setBounds(130, 656, 224, 42);
		lblNoEncontrouTente.setText("N\u00E3o encontrou o doador?\r\nTente cadastrar um novo");
		
		Label label = new Label(shlDoacao, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacao.png").getAbsolutePath()));
		label.setBounds(547, 16, 140, 127);
		
		controle.preencherTabelaDoadores(controle.obterTodosDoadores());
		
		shlDoacao.open();
		shlDoacao.layout();
		while (!shlDoacao.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlDoacao = new Shell();
		shlDoacao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlDoacao.setSize(730, 762);
		shlDoacao.setText("Realizar Doa\u00E7\u00E3o - Passo 1 de 2 - ABRACE");
	}
	
	public static void main() {
		try {
			SelecionarDoadorView window = new SelecionarDoadorView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_ERROR | SWT.OK); 
      messageBox.setText("Problemas ao realizar a doação");
      messageBox.setMessage(e.getMessage()+"\nTente novamente");
      messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("A doação foi realizada com sucesso!");
		messageBox.open();
	}
}