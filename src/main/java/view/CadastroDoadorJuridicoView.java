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

import control.CadastroDoadorJuridicoControle;
import model.PessoaJuridica;

public class CadastroDoadorJuridicoView {

	private CadastroDoadorJuridicoControle controle;
	
	protected Shell shlCadastroDoador;
	

	private Text tfNome;
	private Text tfCNPJ;
	private Text tfFantasia;
	private Text tfEndereco;
	DateTime tfDataNascimento;
	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	Combo tfStatus;
	private Text tfRazaoSocial;
	public Shell getShlCadastroDoador() {
		return shlCadastroDoador;
	}

	public void setShlCadastroDoador(Shell shlCadastroDoador) {
		this.shlCadastroDoador = shlCadastroDoador;
	}
	public Text getTfNome() {
		return tfNome;
		
	}

	public void setTfNome(Text tfNome) {
		this.tfNome = tfNome;
	}

	public Text getTfCNPJ() {
		return tfCNPJ;
	}

	public void setTfCNPJ(Text tfCNPJ) {
		this.tfCNPJ = tfCNPJ;
	}

	public Text gettfFantasia() {
		return tfFantasia;
	}

	public void settfFantasia(Text tfFantasia) {
		this.tfFantasia = tfFantasia;
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

	public CadastroDoadorJuridicoView() {
		this.controle = new CadastroDoadorJuridicoControle(this);
	}

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			CadastroDoadorJuridicoView window = new CadastroDoadorJuridicoView();
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
		Centralize.centralize(shlCadastroDoador);
		
		
		
		shlCadastroDoador.open();
		shlCadastroDoador.layout();
		while (!shlCadastroDoador.isDisposed()) {
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
		shlCadastroDoador = new Shell();
		shlCadastroDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroDoador.setSize(715, 662);
		shlCadastroDoador.setText("Cadastro Doador - ABRACE");
		
		Label lblCadastroDeDoador = new Label(shlCadastroDoador, SWT.NONE);
		lblCadastroDeDoador.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeDoador.setBounds(149, 53, 356, 54);
		lblCadastroDeDoador.setText("Cadastro de Doador");
		
		Label label = new Label(shlCadastroDoador, SWT.NONE);
		label.setText("Nome:");
		label.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label.setBounds(168, 156, 62, 28);
		
		tfNome = new Text(shlCadastroDoador, SWT.BORDER);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(236, 154, 369, 32);
		
		Label label_1 = new Label(shlCadastroDoador, SWT.NONE);
		label_1.setText("CNPJ:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(175, 196, 55, 28);
		
		tfCNPJ = new Text(shlCadastroDoador, SWT.BORDER);
		tfCNPJ.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCNPJ.setBounds(236, 194, 369, 32);
		
		Label label_2 = new Label(shlCadastroDoador, SWT.NONE);
		label_2.setText("Fantasia:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(149, 236, 81, 28);
		
		tfFantasia = new Text(shlCadastroDoador, SWT.BORDER);
		tfFantasia.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfFantasia.setBounds(236, 232, 369, 32);
		
		tfRazaoSocial = new Text(shlCadastroDoador, SWT.BORDER);
		tfRazaoSocial.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRazaoSocial.setBounds(236, 270, 369, 32);
		
		Label lblRazoSocial = new Label(shlCadastroDoador, SWT.NONE);
		lblRazoSocial.setText("Raz\u00E3o social:");
		lblRazoSocial.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblRazoSocial.setBounds(110, 272, 120, 28);
		
		Label label_3 = new Label(shlCadastroDoador, SWT.NONE);
		label_3.setText("Endere\u00E7o:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(139, 309, 91, 28);
		
		tfEndereco = new Text(shlCadastroDoador, SWT.BORDER);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(236, 307, 369, 32);
		
		Label lblTelefone_1 = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone_1.setText("Telefone 1:");
		lblTelefone_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone_1.setBounds(128, 347, 102, 28);
		
		tfTelefone1 = new Text(shlCadastroDoador, SWT.BORDER);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(236, 345, 369, 32);
		
		Label lblTelefone = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone.setText("Telefone 2:");
		lblTelefone.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone.setBounds(128, 385, 102, 28);
		
		tfTelefone2 = new Text(shlCadastroDoador, SWT.BORDER);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(236, 383, 369, 32);
		
		Label label_7 = new Label(shlCadastroDoador, SWT.NONE);
		label_7.setText("Email:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(175, 422, 55, 28);
		
		tfEmail = new Text(shlCadastroDoador, SWT.BORDER);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(236, 420, 369, 32);
		
		Label lblStatus = new Label(shlCadastroDoador, SWT.NONE);
		lblStatus.setText("Status:");
		lblStatus.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblStatus.setBounds(168, 463, 62, 28);
		
		tfStatus = new Combo(shlCadastroDoador, SWT.READ_ONLY);
		tfStatus.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfStatus.setItems(new String[] {"Ativo", "Inativo"});
		tfStatus.setBounds(236, 458, 99, 38);
		tfStatus.select(0);
		
		Button btnCadastrar = new Button(shlCadastroDoador, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCadastrar.setBounds(391, 530, 184, 60);
		btnCadastrar.setText("Cadastrar");
		
		Button btnCancelar = new Button(shlCadastroDoador, SWT.NONE);
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlCadastroDoador.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCancelar.setBounds(190, 530, 184, 60);
		btnCancelar.setText("Cancelar");
		
		Label label_9 = new Label(shlCadastroDoador, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_9.setBounds(511, 23, 109, 114);
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_ERROR | SWT.OK); 
        messageBox.setText("Login inválido");
        messageBox.setMessage(e.getMessage()+".\nTente novamente");
       messageBox.open();
	}
	
	public void mensagemSucesso(PessoaJuridica p){
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Assistido cadastrado com sucesso!");
		messageBox.setMessage("Cadastro realizado com sucesso!\n" + p.getNome()+", agora é um de nossos doadores! :)");
		messageBox.open();
	}
}
