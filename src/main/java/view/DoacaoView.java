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

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class  DoacaoView{

	protected Shell shlDoacao;
	private Text tfDoador;
	private Text tfValor;
	private DateTime tfDataDoacao;
	private PessoaFisica doadorFisico;
	private PessoaJuridica doadorJuridico;
	private Doacao doacao;
	private DoacaoControle controle;
	private Combo tfAtivo;
	
	public Text getTfDoador() {
		
		return tfDoador;
	}

	public void setTfDoador(String tfDoador) {
		this.tfDoador.setText(tfDoador);
	}

	public Text getTfValor() {
		return tfValor;
	}

	public void setTfValor(String tfValor) {
		this.tfValor.setText(tfValor);
	}

	public DateTime getTfDataDoacao() {
		return tfDataDoacao;
	}

	public void setTfDataDoacao(DateTime tfDataDoacao) {
		this.tfDataDoacao = tfDataDoacao;
	}
	
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
	
	public DoacaoView(PessoaFisica doadorFisico) {
		if (doadorFisico == null) {
			this.setDoadorFisico(doadorFisico);
		}
		this.setControle(new DoacaoControle(this));
	}
	
	public DoacaoView(PessoaJuridica doadorJuridico) {
		if (doadorJuridico == null) {
			this.setDoadorJuridico(doadorJuridico);
		}
		this.setControle(new DoacaoControle(this));
	}
	
	public DoacaoView() {
		this.setControle(new DoacaoControle(this));
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlDoacao);
		shlDoacao.open();
		shlDoacao.layout();
		while (!shlDoacao.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDoacao = new Shell();
		shlDoacao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlDoacao.setSize(715, 748);
		shlDoacao.setText("Realizar Doação - ABRACE");
		
		tfDoador = new Text(shlDoacao, SWT.BORDER);
		tfDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDoador.setBounds(261, 149, 369, 32);
		
		tfValor = new Text(shlDoacao, SWT.BORDER);
		tfValor.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfValor.setBounds(261, 188, 369, 32);
		
		tfDataDoacao = new DateTime(shlDoacao, SWT.BORDER);
		tfDataDoacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataDoacao.setBounds(261, 341, 369, 32);
		
		tfAtivo = new Combo(shlDoacao, SWT.READ_ONLY);
		tfAtivo.setItems(new String[] {"Ativo", "Inativo"});
		tfAtivo.setBounds(244, 530, 99, 38);
		tfAtivo.select(0);
		
		tfDoador.setText(getDoacao().getDoador().getNome());
		tfValor.setText(getDoacao().getValor()+"");
		tfDataDoacao.setDay(getDoacao().getData().getDayOfMonth());
		tfDataDoacao.setMonth(getDoacao().getData().getMonthValue());
		tfDataDoacao.setYear(getDoacao().getData().getYear());		
		
		
		Button btnRealizarDoacao = new Button(shlDoacao, SWT.NONE);
		btnRealizarDoacao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
				GerenciarAssistidosView.main();
			}
		});
		btnRealizarDoacao.setText("Realizar doa\u00E7\u00F5o");
		btnRealizarDoacao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnRealizarDoacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnRealizarDoacao.setBounds(380, 620, 217, 60);
		
		Button button_1 = new Button(shlDoacao, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlDoacao.dispose();
				GerenciarAssistidosView.main();
			}
		});
		button_1.setText("Cancelar");
		button_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button_1.setBounds(179, 620, 184, 60);
	}
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	
	public static void main() {
		try {
			DoacaoView window = new DoacaoView();
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
	
	public void mensagemSucesso(PessoaFisica p){
		MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("A doação foi realizada com sucesso!");
		messageBox.open();
	}
}