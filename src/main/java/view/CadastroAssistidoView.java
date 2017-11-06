package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.File;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import control.CadastroAssistidoControle;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CadastroAssistidoView{
	CadastroAssistidoControle controle;

	protected Shell shlCadastroAssistido;
	private Text tfRG;
	private Text tfCPF;
	private Text tfNome;
	private Text tfEndereco;
	private DateTime tfDataNascimento;
	private Text tfTelefone2;
	private Text tfTelefone1;
	private Text tfEmail;
	private Text tfApelido;
	private Text tfTipoCancer;
	private Combo tfSituacao;
	
	

	public CadastroAssistidoView() throws ClassNotFoundException {
		this.controle= new CadastroAssistidoControle(this);
	}

	public Text getTfRG() {
		return tfRG;
	}

	public void setTfRG(Text tfRG) {
		this.tfRG = tfRG;
	}

	public Text getTfCPF() {
		return tfCPF;
	}

	public void setTfCPF(Text tfCPF) {
		this.tfCPF = tfCPF;
	}

	public Text getTfNome() {
		return tfNome;
	}

	public void setTfNome(Text tfNome) {
		this.tfNome = tfNome;
	}

	public Text getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(Text tfEndereco) {
		this.tfEndereco = tfEndereco;
	}
	
	public DateTime getTfDataNascimento() {
		return tfDataNascimento;
	}

	public void setTfDataNascimento(DateTime tfDataNascimento) {
		this.tfDataNascimento = tfDataNascimento;
	}

	public Text getTfTelefone2() {
		return tfTelefone2;
	}

	public void setTfTelefone2(Text tfTelefone2) {
		this.tfTelefone2 = tfTelefone2;
	}

	public Text getTfTelefone1() {
		return tfTelefone1;
	}

	public void setTfTelefone1(Text tfTelefone1) {
		this.tfTelefone1 = tfTelefone1;
	}

	public Text getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(Text tfEmail) {
		this.tfEmail = tfEmail;
	}

	public Text getTfApelido() {
		return tfApelido;
	}

	public void setTfApelido(Text tfApelido) {
		this.tfApelido = tfApelido;
	}

	public Text getTfTipoCancer() {
		return tfTipoCancer;
	}

	public void setTfTipoCancer(Text tfTipoCancer) {
		this.tfTipoCancer = tfTipoCancer;
	}

	public boolean getTfSituacao() {
		if(tfSituacao.getSelectionIndex() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setTfSituacao(boolean tfSituacao) {
		if(tfSituacao) {
			this.tfSituacao.select(0);
		} else {
			this.tfSituacao.select(1);
		}
	}

	public Shell getShlCadastroAssistido() {
		return shlCadastroAssistido;
	}

	public void setShlCadastroAssistido(Shell shlCadastroAssistido) {
		this.shlCadastroAssistido = shlCadastroAssistido;
	}

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			CadastroAssistidoView window = new CadastroAssistidoView();
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
		Centralize.centralize(shlCadastroAssistido);
		
		Label lblPessoaFsica = new Label(shlCadastroAssistido, SWT.NONE);
		lblPessoaFsica.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblPessoaFsica.setBounds(246, 87, 119, 30);
		lblPessoaFsica.setText("Pessoa F\u00EDsica");
		
		Label label = new Label(shlCadastroAssistido, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(241, 173, 7, 21);
		label.setText("*");
		
		Label label_1 = new Label(shlCadastroAssistido, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(241, 218, 7, 21);
		
		Label label_2 = new Label(shlCadastroAssistido, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(241, 263, 7, 21);
		
		Label label_3 = new Label(shlCadastroAssistido, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(241, 307, 7, 21);
		
		Label label_4 = new Label(shlCadastroAssistido, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(241, 346, 7, 21);
		
		Label label_5 = new Label(shlCadastroAssistido, SWT.NONE);
		label_5.setText("*");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_5.setBounds(241, 395, 7, 21);
		
		Label label_7 = new Label(shlCadastroAssistido, SWT.NONE);
		label_7.setText("*");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_7.setBounds(241, 527, 7, 21);
		
		Label label_8 = new Label(shlCadastroAssistido, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_8.setBounds(241, 571, 7, 21);
		
		Label label_9 = new Label(shlCadastroAssistido, SWT.NONE);
		label_9.setText("*");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_9.setBounds(241, 616, 7, 21);
		
		Label lblCamposObrigatrios = new Label(shlCadastroAssistido, SWT.NONE);
		lblCamposObrigatrios.setText("* Campos Obrigat\u00F3rios");
		lblCamposObrigatrios.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblCamposObrigatrios.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblCamposObrigatrios.setBounds(254, 142, 160, 21);
		shlCadastroAssistido.open();
		shlCadastroAssistido.layout();
		while (!shlCadastroAssistido.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCadastroAssistido = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlCadastroAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroAssistido.setSize(694, 806);
		shlCadastroAssistido.setText("Cadastro Assistido - ABRACE");
		
		tfNome = new Text(shlCadastroAssistido, SWT.BORDER);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(254, 169, 369, 38);
		tfNome.setTextLimit(128);
		
		tfApelido = new Text(shlCadastroAssistido, SWT.BORDER);
		tfApelido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfApelido.setBounds(254, 213, 369, 38);
		tfApelido.setTextLimit(32);
		
		tfCPF = new Text(shlCadastroAssistido, SWT.BORDER);
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(254, 258, 369, 38);
		tfCPF.setTextLimit(14);
		
		tfRG = new Text(shlCadastroAssistido, SWT.BORDER);
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(254, 302, 369, 38);
		tfRG.setTextLimit(16);
		
		tfEndereco = new Text(shlCadastroAssistido, SWT.BORDER);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(254, 346, 369, 38);
		tfEndereco.setTextLimit(256);
		
		tfDataNascimento = new DateTime(shlCadastroAssistido, SWT.BORDER);
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(254, 390, 369, 38);
		
		tfTelefone1 = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(254, 434, 369, 38);
		tfTelefone1.setTextLimit(16);
		
		tfTelefone2 = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(254, 478, 369, 38);
		tfTelefone2.setTextLimit(16);
		
		tfEmail = new Text(shlCadastroAssistido, SWT.BORDER);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(254, 522, 369, 38);
		tfEmail.setTextLimit(128);
		
		tfTipoCancer = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTipoCancer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTipoCancer.setBounds(254, 566, 369, 38);
		tfTipoCancer.setTextLimit(64);
		
		tfSituacao = new Combo(shlCadastroAssistido, SWT.READ_ONLY);
		tfSituacao.setItems(new String[] {"Vivo", "Falecido"});
		tfSituacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfSituacao.setBounds(254, 611, 99, 38);
		tfSituacao.select(0);
		
		Label label_1 = new Label(shlCadastroAssistido, SWT.NONE);
		label_1.setText("Email:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(183, 527, 55, 28);
		
		Label label_2 = new Label(shlCadastroAssistido, SWT.NONE);
		label_2.setText("Telefone 2:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(136, 483, 102, 28);
		
		Label label_3 = new Label(shlCadastroAssistido, SWT.NONE);
		label_3.setText("Telefone 1:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(136, 439, 102, 28);
		
		Label label_4 = new Label(shlCadastroAssistido, SWT.NONE);
		label_4.setText("Data de Nascimento:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(43, 395, 195, 28);
		
		Label label_5 = new Label(shlCadastroAssistido, SWT.NONE);
		label_5.setText("Endere\u00E7o:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(147, 351, 91, 28);
		
		Label label_6 = new Label(shlCadastroAssistido, SWT.NONE);
		label_6.setText("RG:");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_6.setBounds(206, 307, 32, 28);
		
		
		Label label_7 = new Label(shlCadastroAssistido, SWT.NONE);
		label_7.setText("CPF:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(198, 263, 40, 28);
		
		Label lblNome = new Label(shlCadastroAssistido, SWT.NONE);
		lblNome.setText("Nome:");
		lblNome.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblNome.setBounds(176, 173, 62, 28);
		
		Label lblCadastroDeAssistido = new Label(shlCadastroAssistido, SWT.NONE);
		lblCadastroDeAssistido.setText("Cadastro de Assistido");
		lblCadastroDeAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeAssistido.setBounds(114, 29, 382, 54);
		
		Label label_10 = new Label(shlCadastroAssistido, SWT.NONE);
		label_10.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido.png").getAbsolutePath()));
		label_10.setBounds(530, 20, 75, 119);
		
		Label lblApelido = new Label(shlCadastroAssistido, SWT.NONE);
		lblApelido.setText("Apelido:");
		lblApelido.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblApelido.setBounds(162, 218, 76, 28);
		
		Label label_9 = new Label(shlCadastroAssistido, SWT.NONE);
		label_9.setText("Tipo de Cancer:");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_9.setBounds(94, 571, 144, 28);
		
		Label lblSituao = new Label(shlCadastroAssistido, SWT.NONE);
		lblSituao.setText("Situa\u00E7\u00E3o:");
		lblSituao.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblSituao.setBounds(154, 616, 84, 28);
		
		Button button = new Button(shlCadastroAssistido, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlCadastroAssistido.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button.setBounds(175, 686, 184, 60);
		
		Button button_1 = new Button(shlCadastroAssistido, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					controle.getEvent(arg0);
				} catch (PessoaInvalidaException e1) {
					mensagemErro(e1);
				} catch (PessoaFisicaException e) {
					mensagemErro(e);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button_1.setText("Cadastrar");
		button_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		button_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button_1.setBounds(376, 686, 184, 60);
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_ERROR | SWT.OK); 
       messageBox.setText("Problemas no cadastro do assistido");
       messageBox.setMessage(e.getMessage()+"\nTente novamente");
       messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica p){
		MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Assistido cadastrado com sucesso!");
		messageBox.setMessage("Cadastro realizado com sucesso!\n" + p.getNome()+", agora é um de nossos assistidos! :)");
		messageBox.open();
	}
}
