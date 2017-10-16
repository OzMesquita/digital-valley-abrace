package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import model.Assistido;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EditarAssistidoView {

	protected Shell shlEditarAssistido;
	
	private Text tfNome;
	private Text tfApelido;
	private Text tfCPF;
	private Text tfRG;
	private Text tfEndereco;
	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	private Text tfTipoCancer;
	private Combo tfStatus;
	private Combo tfSituacao;
	private Assistido a;
	public EditarAssistidoView(Assistido a) {
		if(a != null) {
			this.a = a;
		}
	}
	
	public EditarAssistidoView() {
	}

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			EditarAssistidoView window = new EditarAssistidoView();
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
		Centralize.centralize(shlEditarAssistido);
		shlEditarAssistido.open();
		shlEditarAssistido.layout();
		while (!shlEditarAssistido.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEditarAssistido = new Shell();
		shlEditarAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlEditarAssistido.setSize(715, 748);
		shlEditarAssistido.setText("Editar Assistido - ABRACE");
		
		Label lblEditarAssistido = new Label(shlEditarAssistido, SWT.NONE);
		lblEditarAssistido.setText("Editar Assistido");
		lblEditarAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblEditarAssistido.setBounds(214, 42, 271, 54);
		
		Label label_1 = new Label(shlEditarAssistido, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido.png").getAbsolutePath()));
		label_1.setBounds(548, 10, 75, 119);
		
		Label label_2 = new Label(shlEditarAssistido, SWT.NONE);
		label_2.setText("Nome:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(193, 151, 62, 28);
		
		tfNome = new Text(shlEditarAssistido, SWT.BORDER);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(261, 149, 369, 32);
		
		Label label_3 = new Label(shlEditarAssistido, SWT.NONE);
		label_3.setText("Apelido:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(179, 190, 76, 28);
		
		tfApelido = new Text(shlEditarAssistido, SWT.BORDER);
		tfApelido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfApelido.setBounds(261, 188, 369, 32);
		
		Label label_4 = new Label(shlEditarAssistido, SWT.NONE);
		label_4.setText("CPF:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(215, 228, 40, 28);
		
		tfCPF = new Text(shlEditarAssistido, SWT.BORDER);
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(261, 226, 369, 32);
		
		Label label_5 = new Label(shlEditarAssistido, SWT.NONE);
		label_5.setText("RG:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(223, 266, 32, 28);
		
		tfRG = new Text(shlEditarAssistido, SWT.BORDER);
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(261, 264, 369, 32);
		
		Label label_6 = new Label(shlEditarAssistido, SWT.NONE);
		label_6.setText("Endere\u00E7o:");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_6.setBounds(164, 305, 91, 28);
		
		tfEndereco = new Text(shlEditarAssistido, SWT.BORDER);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(261, 303, 369, 32);
		
		Label label_7 = new Label(shlEditarAssistido, SWT.NONE);
		label_7.setText("Data de Nascimento:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(60, 343, 195, 28);
		
		DateTime tfDataNascimento = new DateTime(shlEditarAssistido, SWT.BORDER);
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(261, 341, 369, 32);
		
		Label label_8 = new Label(shlEditarAssistido, SWT.NONE);
		label_8.setText("Telefone 1:");
		label_8.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_8.setBounds(153, 381, 102, 28);
		
		tfTelefone1 = new Text(shlEditarAssistido, SWT.BORDER);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(261, 379, 369, 32);
		
		Label label_9 = new Label(shlEditarAssistido, SWT.NONE);
		label_9.setText("Telefone 2:");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_9.setBounds(153, 419, 102, 28);
		
		tfTelefone2 = new Text(shlEditarAssistido, SWT.BORDER);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(261, 417, 369, 32);
		
		Label label_10 = new Label(shlEditarAssistido, SWT.NONE);
		label_10.setText("Email:");
		label_10.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_10.setBounds(200, 456, 55, 28);
		
		tfEmail = new Text(shlEditarAssistido, SWT.BORDER);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(261, 454, 369, 32);
		
		Label label_11 = new Label(shlEditarAssistido, SWT.NONE);
		label_11.setText("Tipo de Cancer:");
		label_11.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_11.setBounds(113, 494, 144, 28);
		
		tfTipoCancer = new Text(shlEditarAssistido, SWT.BORDER);
		tfTipoCancer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTipoCancer.setBounds(262, 492, 369, 32);
		
		Label label_12 = new Label(shlEditarAssistido, SWT.NONE);
		label_12.setText("Status:");
		label_12.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_12.setBounds(193, 535, 62, 28);
		
		tfStatus = new Combo(shlEditarAssistido, SWT.READ_ONLY);
		tfStatus.setItems(new String[] {"Ativo", "Inativo"});
		tfStatus.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfStatus.setBounds(261, 530, 99, 38);
		tfStatus.select(0);
		
		Label label_13 = new Label(shlEditarAssistido, SWT.NONE);
		label_13.setText("Situa\u00E7\u00E3o:");
		label_13.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_13.setBounds(442, 534, 84, 28);
		
		tfSituacao = new Combo(shlEditarAssistido, SWT.READ_ONLY);
		tfSituacao.setItems(new String[] {"Vivo", "Falecido"});
		tfSituacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfSituacao.setBounds(531, 530, 99, 38);
		tfSituacao.select(0);
		
		Button btnSalvarAlteraes = new Button(shlEditarAssistido, SWT.NONE);
		btnSalvarAlteraes.setText("Salvar altera\u00E7\u00F5es");
		btnSalvarAlteraes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarAlteraes.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnSalvarAlteraes.setBounds(380, 620, 217, 60);
		
		Button button_1 = new Button(shlEditarAssistido, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarAssistido.dispose();
			}
		});
		button_1.setText("Cancelar");
		button_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button_1.setBounds(179, 620, 184, 60);
	}

	public Text getTfNome() {
		
		return tfNome;
	}

	public void setTfNome(Text tfNome) {
		this.tfNome = tfNome;
	}

	public Text getTfApelido() {
		return tfApelido;
	}

	public void setTfApelido(Text tfApelido) {
		this.tfApelido = tfApelido;
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

	public Text getTfTipoCancer() {
		return tfTipoCancer;
	}

	public void setTfTipoCancer(Text tfTipoCancer) {
		this.tfTipoCancer = tfTipoCancer;
	}
	
	public boolean getTfSituacao() {
		if(tfSituacao.getSelectionIndex() == 0) {
			return true;
		}
		return false;
	}
	
	public void setTfSituacao(boolean situacao) {
		if(situacao) {
			tfSituacao.select(0);
		} else {
			tfSituacao.select(1);
		}
	}
	
	public boolean getTfStatus() {
		if(tfStatus.getSelectionIndex() == 0) {
			return true;
		}
		return false;
	}
	
	public void setTfStatus(boolean status) {
		if(status) {
			tfStatus.select(0);
		} else {
			tfStatus.select(1);
		}
	}

}
