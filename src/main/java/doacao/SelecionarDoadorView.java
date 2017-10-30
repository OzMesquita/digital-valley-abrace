package doacao;

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
import view.Centralize;

import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class  SelecionarDoadorView{

	protected Shell shlDoacao;
	private Text tfDoador;
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
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlDoacao);
		
		Label lblDoador = new Label(shlDoacao, SWT.NONE);
		lblDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblDoador.setBounds(90, 171, 74, 30);
		lblDoador.setText("Doador:");
		
		Button btnConfirmar = new Button(shlDoacao, SWT.NONE);
		btnConfirmar.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		btnConfirmar.setBounds(328, 243, 150, 57);
		btnConfirmar.setText("Avan\u00E7ar");
		
		Button btnCancelar = new Button(shlDoacao, SWT.NONE);
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		btnCancelar.setBounds(178, 243, 144, 57);
		btnCancelar.setText("Cancelar");
		
		Label lblRealizarDoao = new Label(shlDoacao, SWT.NONE);
		lblRealizarDoao.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblRealizarDoao.setBounds(161, 47, 317, 50);
		lblRealizarDoao.setText("Selecione o doador");
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
		shlDoacao.setSize(656, 410);
		shlDoacao.setText("Realizar Doação - ABRACE");
		
		tfDoador = new Text(shlDoacao, SWT.BORDER);
		tfDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDoador.setBounds(170, 168, 369, 32);
		
		tfDoador.setText(getDoacao().getDoador().getNome());
	}
	
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	
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
	
	public void mensagemSucesso(PessoaFisica p){
		MessageBox messageBox = new MessageBox(shlDoacao,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("A doação foi realizada com sucesso!");
		messageBox.open();
	}
}