package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import control.CadastroDoadorJuridicoControle;
import model.PessoaJuridica;

public class CadastroDoadorPJuridicoView {
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

	public CadastroDoadorPJuridicoView() {
		this.controle = new CadastroDoadorJuridicoControle(this);
	}

	public static void main() {
		try {
			CadastroDoadorPJuridicoView window = new CadastroDoadorPJuridicoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlCadastroDoador);
		
		Label lblDoadorPessoaJurdica = new Label(shlCadastroDoador, SWT.NONE);
		lblDoadorPessoaJurdica.setText("Pessoa Jur\u00EDdica");
		lblDoadorPessoaJurdica.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		lblDoadorPessoaJurdica.setBounds(250, 97, 167, 35);
		
		Label label = new Label(shlCadastroDoador, SWT.NONE);
		label.setText("* Campos Obrigat\u00F3rios");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label.setBounds(235, 154, 160, 21);
		
		Label label_1 = new Label(shlCadastroDoador, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(222, 186, 7, 21);
		
		Label label_2 = new Label(shlCadastroDoador, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(222, 274, 7, 21);
		
		Label label_3 = new Label(shlCadastroDoador, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(222, 230, 7, 21);
		
		Label label_5 = new Label(shlCadastroDoador, SWT.NONE);
		label_5.setText("*");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_5.setBounds(222, 318, 7, 21);
		shlCadastroDoador.open();
		shlCadastroDoador.layout();
		while (!shlCadastroDoador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlCadastroDoador = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlCadastroDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroDoador.setSize(694, 640);
		shlCadastroDoador.setText("Cadastro Doador - ABRACE");
		
		Label lblCadastroDeDoador = new Label(shlCadastroDoador, SWT.NONE);
		lblCadastroDeDoador.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeDoador.setBounds(149, 38, 356, 54);
		lblCadastroDeDoador.setText("Cadastro de Doador");
		
		Label lblRazoSocial = new Label(shlCadastroDoador, SWT.NONE);
		lblRazoSocial.setText("Raz\u00E3o Social:");
		lblRazoSocial.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblRazoSocial.setBounds(98, 186, 122, 28);
		
		tfNome = new Text(shlCadastroDoador, SWT.BORDER);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(235, 181, 369, 38);
		tfNome.setTextLimit(128);
		
		Label label_2 = new Label(shlCadastroDoador, SWT.NONE);
		label_2.setText("Fantasia:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(139, 230, 81, 28);
		
		tfFantasia = new Text(shlCadastroDoador, SWT.BORDER);
		tfFantasia.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfFantasia.setBounds(235, 225, 369, 38);
		tfFantasia.setTextLimit(128);
		
		Label label_1 = new Label(shlCadastroDoador, SWT.NONE);
		label_1.setText("CNPJ:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(165, 274, 55, 28);
		
		tfCNPJ = new Text(shlCadastroDoador, SWT.BORDER);
		tfCNPJ.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCNPJ.setBounds(235, 269, 369, 38);
		tfCNPJ.setTextLimit(32);
		
		Label label_3 = new Label(shlCadastroDoador, SWT.NONE);
		label_3.setText("Endere\u00E7o:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(129, 318, 91, 28);
		
		tfEndereco = new Text(shlCadastroDoador, SWT.BORDER);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(235, 313, 369, 38);
		tfEndereco.setTextLimit(256);
		
		Label lblTelefone_1 = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone_1.setText("Telefone 1:");
		lblTelefone_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone_1.setBounds(118, 363, 102, 28);
		
		tfTelefone1 = new Text(shlCadastroDoador, SWT.BORDER);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(235, 358, 369, 38);
		tfTelefone1.setTextLimit(16);
		
		Label lblTelefone = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone.setText("Telefone 2:");
		lblTelefone.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone.setBounds(118, 407, 102, 28);
		
		tfTelefone2 = new Text(shlCadastroDoador, SWT.BORDER);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(235, 402, 369, 38);
		tfTelefone2.setTextLimit(16);
		
		Label label_7 = new Label(shlCadastroDoador, SWT.NONE);
		label_7.setText("Email:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(165, 451, 55, 28);
		
		tfEmail = new Text(shlCadastroDoador, SWT.BORDER);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(235, 446, 369, 38);
		tfEmail.setTextLimit(128);
		
		Button btnCadastrar = new Button(shlCadastroDoador, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCadastrar.setBounds(350, 526, 184, 60);
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
		btnCancelar.setBounds(149, 526, 184, 60);
		btnCancelar.setText("Cancelar");
		
		Label label_9 = new Label(shlCadastroDoador, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_9.setBounds(513, 29, 109, 114);
	}
	
	public void mensagemErro(Exception e){
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_ERROR | SWT.OK); 
		messageBox.setText("Problemas no cadastro do doador jurídico!");
		messageBox.setMessage(e.getMessage()+"\nNão foi possível cadastrar este doador pessoa jurídica.");
		messageBox.open();
	}
	
	public void mensagemSucesso(PessoaJuridica pessoaJuridica){
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Assistido cadastrado com sucesso!");
		messageBox.setMessage("Cadastro realizado com sucesso!\n" + pessoaJuridica.getNome()+", agora é um de nossos doadores! :)");
		messageBox.open();
	}
}