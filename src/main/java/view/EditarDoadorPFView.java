package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.EditarDoadorPFControle;
import model.PessoaFisica;

public class EditarDoadorPFView {

	private EditarDoadorPFControle controle;
	
	protected Shell shlEditarDoador = new Shell();
	
	private PessoaFisica doador;
	/**
	 * @wbp.parser.entryPoint
	 */
	public EditarDoadorPFView(PessoaFisica doador) {
		this.setControle(new EditarDoadorPFControle(this));
		this.setDoador(doador);
	}

	public EditarDoadorPFView() {
		this.setControle(new EditarDoadorPFControle(this));
	}

	public EditarDoadorPFControle getControle() {
		return controle;
	}

	public void setControle(EditarDoadorPFControle controle) {
		this.controle = controle;
	}

	public PessoaFisica getDoador() {
		return doador;
	}

	public void setDoador(PessoaFisica doador) {
		this.doador = doador;
	}

	private Text tfNome;
	private Text tfCPF;
	private Text tfRG;
	private Text tfEndereco;
	private DateTime tfDataNascimento;
	public DateTime getTfDataNascimento() {
		return tfDataNascimento;
	}

	public void setTfDataNascimento(DateTime tfDataNascimento) {
		this.tfDataNascimento = tfDataNascimento;
	}

	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	private Combo tfStatus;
	private Text tfRazaoSocial;
	public Shell getShlEditarDoador() {
		return shlEditarDoador;
	}

	public Text getTfNome() {
		return tfNome;
	}

	public void setTfNome(Text tfNome) {
		this.tfNome = tfNome;
	}

	public Text getTfCPF() {
		return tfCPF;
	}

	public void setTfCPF(Text tfCPF) {
		this.tfCPF = tfCPF;
	}

	public Text getTfRG() {
		return tfRG;
	}

	public void setTfRG(Text tfRG) {
		this.tfRG = tfRG;
	}

	public Text getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(Text tfEndereco) {
		this.tfEndereco = tfEndereco;
	}

	public Text getTfTelefone1() {
		return tfTelefone1;
	}

	public void setTfTelefone1(Text tfTelefone1) {
		this.tfTelefone1 = tfTelefone1;
	}

	public Text getTfTelefone2() {
		return tfTelefone2;
	}

	public void setTfTelefone2(Text tfTelefone2) {
		this.tfTelefone2 = tfTelefone2;
	}

	public Text getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(Text tfEmail) {
		this.tfEmail = tfEmail;
	}

	public boolean getTfStatus() {
		if(tfStatus.getSelectionIndex() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setTfStatus(Combo tfStatus) {
		this.tfStatus = tfStatus;
	}
	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main(PessoaFisica a) {
		try {
			EditarDoadorPFView window = new EditarDoadorPFView(a);
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
		Centralize.centralize(shlEditarDoador);
		shlEditarDoador.open();
		shlEditarDoador.layout();
		while (!shlEditarDoador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Text getTfRazaoSocial() {
		return tfRazaoSocial;
	}

	public void setTfRazaoSocial(Text tfRazaoSocial) {
		this.tfRazaoSocial = tfRazaoSocial;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEditarDoador = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlEditarDoador.setSize(715, 652);
		shlEditarDoador.setText("Editar Doador (Pessoa F\u00EDsica) - ABRACE");
		shlEditarDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		
		Label lblEditarDoadorpessoa = new Label(shlEditarDoador, SWT.NONE);
		lblEditarDoadorpessoa.setText("Editar Doador");
		lblEditarDoadorpessoa.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblEditarDoadorpessoa.setBounds(227, 40, 245, 54);
		
		Label label_1 = new Label(shlEditarDoador, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_1.setBounds(532, 10, 109, 114);
		
		tfNome = new Text(shlEditarDoador, SWT.BORDER);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(255, 153, 369, 32);
		
		tfCPF = new Text(shlEditarDoador, SWT.BORDER);
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(255, 193, 369, 32);
		
		tfRG = new Text(shlEditarDoador, SWT.BORDER);
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(255, 231, 369, 32);
		
		tfEndereco = new Text(shlEditarDoador, SWT.BORDER);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(255, 270, 369, 32);
		
		tfDataNascimento = new DateTime(shlEditarDoador, SWT.BORDER);
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(255, 308, 369, 32);
		
		tfTelefone1 = new Text(shlEditarDoador, SWT.BORDER);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(255, 346, 369, 32);
		
		tfTelefone2 = new Text(shlEditarDoador, SWT.BORDER);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(255, 384, 369, 32);
		
		tfEmail = new Text(shlEditarDoador, SWT.BORDER);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(255, 421, 369, 32);
		
		tfStatus = new Combo(shlEditarDoador, SWT.NONE);
		tfStatus.setItems(new String[] {"Ativo", "Inativo"});
		tfStatus.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfStatus.setBounds(255, 459, 99, 38);
		tfStatus.select(0);
		
		Label label_2 = new Label(shlEditarDoador, SWT.NONE);
		label_2.setText("Status:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(187, 464, 62, 28);
		
		Label label_3 = new Label(shlEditarDoador, SWT.NONE);
		label_3.setText("Email:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(194, 423, 55, 28);
		
		Label label_4 = new Label(shlEditarDoador, SWT.NONE);
		label_4.setText("Telefone 2:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(147, 386, 102, 28);
		
		Label label_5 = new Label(shlEditarDoador, SWT.NONE);
		label_5.setText("Telefone 1:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(147, 348, 102, 28);
		
		Label label_6 = new Label(shlEditarDoador, SWT.NONE);
		label_6.setText("Data de Nascimento:");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_6.setBounds(54, 310, 195, 28);
		
		Label label_7 = new Label(shlEditarDoador, SWT.NONE);
		label_7.setText("Endere\u00E7o:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(158, 272, 91, 28);
		
		Label label_8 = new Label(shlEditarDoador, SWT.NONE);
		label_8.setText("RG:");
		label_8.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_8.setBounds(217, 233, 32, 28);
		
		Label label_9 = new Label(shlEditarDoador, SWT.NONE);
		label_9.setText("CPF:");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_9.setBounds(209, 195, 40, 28);
		
		Label label_10 = new Label(shlEditarDoador, SWT.NONE);
		label_10.setText("Nome:");
		label_10.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_10.setBounds(187, 155, 62, 28);
		
		Button btnCancelar = new Button(shlEditarDoador, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarDoador.dispose();
				GerenciarDoadoresView.main();
			}
		});
		btnCancelar.setText("Cancelar");
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCancelar.setBounds(187, 530, 184, 60);
		
		Button btnCadastrar = new Button(shlEditarDoador, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
				GerenciarDoadoresView.main();
			}
		});
		btnCadastrar.setText("Salvar altera\u00E7\u00F5es");
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCadastrar.setBounds(388, 530, 214, 60);
		
		Label lblPessoaFsica = new Label(shlEditarDoador, SWT.NONE);
		lblPessoaFsica.setText("Pessoa F\u00EDsica");
		lblPessoaFsica.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		lblPessoaFsica.setBounds(268, 89, 162, 40);
		
		tfNome.setText(getDoador().getNome());
		tfCPF.setText(getDoador().getCpf());
		tfRG.setText(getDoador().getRg());
		tfEndereco.setText(getDoador().getEndereco());
		tfDataNascimento.setDay(getDoador().getDataNasc().getDayOfMonth());
		tfDataNascimento.setMonth(getDoador().getDataNasc().getMonthValue());
		tfDataNascimento.setYear(getDoador().getDataNasc().getYear());
		tfTelefone1.setText(getDoador().getTelefone());
		tfTelefone2.setText(getDoador().getTelefone2());
		tfEmail.setText(getDoador().getEmail());
		if(getDoador().isAtivo()) {
			this.tfStatus.select(0);
		} else {
			this.tfStatus.select(1);
		}

	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlEditarDoador,SWT.ICON_ERROR | SWT.OK); 
		 messageBox.setText("Problemas ao editar dados do doador pessoa física!");
		 messageBox.setMessage(/*e.getMessage()+*/"Não foi possível este doador pessoa física. \nConsulte o suporte.");
		 messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica p){
		MessageBox messageBox = new MessageBox(shlEditarDoador,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("Os dados do assitido foram alterados com sucesso!");
		messageBox.open();
	}

}
