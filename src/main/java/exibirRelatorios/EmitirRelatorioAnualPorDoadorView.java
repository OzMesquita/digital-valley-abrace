package exibirRelatorios;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import model.Doacao;
import model.PessoaFisica;
import model.PessoaJuridica;
import view.Centralize;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;

public class  EmitirRelatorioAnualPorDoadorView{
	protected Shell shlDoacao;
	private PessoaFisica doadorFisico;
	private PessoaJuridica doadorJuridico;
	private Doacao doacao;
	private Combo tfAtivo;
	private Text tfPesquisar;
	private Table table;
	private SelecionarDoadorControle controle;
	private Text tfAno;
	
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
	
	public SelecionarDoadorControle getControle() {
		return controle;
	}
	
	public void setControle(SelecionarDoadorControle controle) {
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

	public Text getTfAno() {
		return tfAno;
	}

	public void setTfAno(String tfAno) {
		this.tfAno.setText(tfAno);
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

	public EmitirRelatorioAnualPorDoadorView(PessoaFisica doadorFisico) {
		if (doadorFisico == null) {
			this.setDoadorFisico(doadorFisico);
		}
		this.setControle(new SelecionarDoadorControle(this));
	}
	
	public EmitirRelatorioAnualPorDoadorView(PessoaJuridica doadorJuridico) {
		if (doadorJuridico == null) {
			this.setDoadorJuridico(doadorJuridico);
		}
		this.setControle(new SelecionarDoadorControle(this));
	}
	
	public EmitirRelatorioAnualPorDoadorView() {
		this.setControle(new SelecionarDoadorControle(this));
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlDoacao);
		
		Label lblRealizarDoao = new Label(shlDoacao, SWT.NONE);
		lblRealizarDoao.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblRealizarDoao.setBounds(103, 14, 453, 50);
		lblRealizarDoao.setText("Relat\u00F3rio Anual de Doa\u00E7\u00F5es");
		
		Label lblParaRegistrarUma = new Label(shlDoacao, SWT.NONE);
		lblParaRegistrarUma.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblParaRegistrarUma.setBounds(87, 70, 485, 25);
		lblParaRegistrarUma.setText("Para gerar um relat\u00F3rio, \u00E9 necess\u00E1rio informar um doador");
		
		Label lblSelecioneOuPesquise = new Label(shlDoacao, SWT.NONE);
		lblSelecioneOuPesquise.setText("Selecione ou pesquise um doador e clique em \"continuar\"");
		lblSelecioneOuPesquise.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneOuPesquise.setBounds(86, 101, 487, 25);
		
		Label label_1 = new Label(shlDoacao, SWT.NONE);
		label_1.setText("Pesquisar:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label_1.setBounds(10, 152, 92, 30);
		
		tfPesquisar = new Text(shlDoacao, SWT.BORDER);
		tfPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfPesquisar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfPesquisar.setBounds(108, 149, 448, 36);
		
		Button btnPesquisar = new Button(shlDoacao, SWT.NONE);
		btnPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnPesquisar.setText("Pesquisar");
		btnPesquisar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pesquisar.png").getAbsolutePath()));
		btnPesquisar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnPesquisar.setBounds(560, 149, 144, 36);
		
		btnPesquisar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		
		table = new Table(shlDoacao, SWT.BORDER | SWT.FULL_SELECTION);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		table.setToolTipText("");
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		table.setBounds(10, 194, 694, 263);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(47);
		tableColumn.setText("ID");
		tableColumn.setResizable(false);
		
		TableColumn tblclmnNomeDoDoador = new TableColumn(table, SWT.CENTER);
		tblclmnNomeDoDoador.setWidth(253);
		tblclmnNomeDoDoador.setText("Nome do Doador");
		tblclmnNomeDoDoador.setResizable(false);
		
		TableColumn tblclmnCpfCnpj_1 = new TableColumn(table, SWT.CENTER);
		tblclmnCpfCnpj_1.setWidth(183);
		tblclmnCpfCnpj_1.setText("CPF / CNPJ");
		tblclmnCpfCnpj_1.setResizable(false);
		
		TableColumn tblclmnCpfCnpj = new TableColumn(table, SWT.CENTER);
		tblclmnCpfCnpj.setWidth(200);
		tblclmnCpfCnpj.setText("Endere\u00E7o");
		tblclmnCpfCnpj.setResizable(false);
		
		Button btnCancelar = new Button(shlDoacao, SWT.NONE);
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlDoacao.dispose();
			}
		});
		btnCancelar.setText("Cancelar");
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCancelar.setBounds(143, 532, 214, 54);
		
		Button btnPrximo = new Button(shlDoacao, SWT.NONE);
		btnPrximo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnPrximo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnPrximo.setText("Continuar");
		btnPrximo.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/botao-proximo.png").getAbsolutePath()));
		btnPrximo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnPrximo.setBounds(363, 532, 214, 54);
		
		Label label = new Label(shlDoacao, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/relatorio.png").getAbsolutePath()));
		label.setBounds(607, 21, 80, 105);
		
		Label lblInformeUmAno = new Label(shlDoacao, SWT.NONE);
		lblInformeUmAno.setText("Informe o ano para emiss\u00E3o do relat\u00F3rio:");
		lblInformeUmAno.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblInformeUmAno.setBounds(125, 484, 343, 25);
		
		tfAno = new Text(shlDoacao, SWT.BORDER | SWT.CENTER);
		tfAno.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfAno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarAno(arg0);
			}
		});
		tfAno.setBounds(474, 478, 92, 36);
		
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
		shlDoacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlDoacao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlDoacao.setSize(730, 643);
		shlDoacao.setText("Emitir relat\u00F3rio anual por doador");
	}
	
	public static void main() {
		try {
			EmitirRelatorioAnualPorDoadorView window = new EmitirRelatorioAnualPorDoadorView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_ERROR | SWT.OK); 
      messageBox.setText("Problemas ao emitir relatório");
      messageBox.setMessage(e.getMessage()+"\nTente novamente");
      messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("O relatório foi gerado com sucesso!");
		messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlDoacao.dispose();
		}
	}
}