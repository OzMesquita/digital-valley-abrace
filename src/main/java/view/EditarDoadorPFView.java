package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import control.EditarDoadorPFControle;
import model.PessoaFisica;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class EditarDoadorPFView {
	protected Shell shlEditarDoadorPessoa;
	private Text tfNome;
	private Text tfCPF;
	private Text tfRG;
	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	private DateTime tfDataNascimento;
	private PessoaFisica doador;
	private Text tfEndereco;
	private EditarDoadorPFControle controle = new EditarDoadorPFControle(this);
	
	public DateTime getTfDataNascimento() {
		return tfDataNascimento;
	}

	public void setTfDataNascimento(DateTime tfDataNascimento) {
		this.tfDataNascimento = tfDataNascimento;
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

	public void setTfCPF(String tfCPF) {
		this.tfCPF.setText(tfCPF);
	}

	public Text getTfRG() {
		return tfRG;
	}

	public void setTfRG(Text tfRG) {
		this.tfRG = tfRG;
	}

	public Text getTfTelefone1() {
		return tfTelefone1;
	}

	public void setTfTelefone1(String tfTelefone1) {
		this.tfTelefone1.setText(tfTelefone1);
	}

	public Text getTfTelefone2() {
		return tfTelefone2;
	}

	public void setTfTelefone2(String tfTelefone2) {
		this.tfTelefone2.setText(tfTelefone2);
	}

	public Text getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(Text tfEmail) {
		this.tfEmail = tfEmail;
	}

	public Text getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(Text tfEndereco) {
		this.tfEndereco = tfEndereco;
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
	public Shell getShlEditarDoadorPessoa() {
		return shlEditarDoadorPessoa;
	}

	public void setShlEditarDoadorPessoa(Shell shlEditarDoadorPessoa) {
		this.shlEditarDoadorPessoa = shlEditarDoadorPessoa;
	}

	public static void main(PessoaFisica pessoaFisica) {
		try {
			EditarDoadorPFView window = new EditarDoadorPFView();
			window.setDoador(pessoaFisica);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEditarDoadorPessoa.open();
		shlEditarDoadorPessoa.layout();
		while (!shlEditarDoadorPessoa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlEditarDoadorPessoa = new Shell();
		shlEditarDoadorPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlEditarDoadorPessoa.setSize(715, 674);
		shlEditarDoadorPessoa.setText("Editar doador - ABRACE");
		Centralize.centralize(shlEditarDoadorPessoa);
		
		Label label = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label.setText("Editar Doador");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		label.setBounds(227, 32, 245, 54);
		
		Label lblPessoaFsica = new Label(shlEditarDoadorPessoa, SWT.NONE);
		lblPessoaFsica.setText("Pessoa F\u00EDsica");
		lblPessoaFsica.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		lblPessoaFsica.setBounds(278, 92, 143, 35);
		
		Label label_2 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_2.setBounds(538, 21, 109, 114);
		
		Label label_1 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_1.setText("* Campos Obrigat\u00F3rios");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(264, 143, 160, 21);
		
		Label label_3 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_3.setText("Nome:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(185, 175, 62, 28);
		
		Label label_4 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(251, 174, 7, 21);
		
		tfNome = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfNome.setTextLimit(128);
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(264, 170, 369, 38);
		
		Label label_5 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_5.setText("CPF:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(207, 219, 40, 28);
		
		Label label_6 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_6.setText("*");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_6.setBounds(251, 219, 7, 21);
		
		tfCPF = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarCpf(arg0);
				identificarESC(arg0);
			}
		});
		tfCPF.setTextLimit(14);
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(264, 214, 369, 38);
		
		Label label_7 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_7.setText("RG:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(215, 263, 32, 28);
		
		Label label_8 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_8.setBounds(251, 263, 7, 21);
		
		tfRG = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfRG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfRG.setTextLimit(16);
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(264, 258, 369, 38);
		
		Label label_9 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_9.setText("Endere\u00E7o:");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_9.setBounds(156, 307, 91, 28);
		
		Label label_10 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_10.setText("*");
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_10.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_10.setBounds(251, 307, 7, 21);
		
		tfEndereco = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEndereco.setTextLimit(268);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(264, 302, 369, 38);
		
		Label label_11 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_11.setText("Data de Nascimento:");
		label_11.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_11.setBounds(52, 351, 195, 28);
		
		Label label_12 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_12.setText("*");
		label_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_12.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_12.setBounds(251, 351, 7, 21);
		
		tfDataNascimento = new DateTime(shlEditarDoadorPessoa, SWT.BORDER);
		tfDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(264, 346, 369, 38);
		
		Label label_13 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_13.setText("Telefone 1:");
		label_13.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_13.setBounds(145, 395, 102, 28);
		
		tfTelefone1 = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfTelefone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone1(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone1.setTextLimit(16);
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(264, 390, 369, 38);
		
		Label label_14 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_14.setText("Telefone 2:");
		label_14.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_14.setBounds(145, 439, 102, 28);
		
		tfTelefone2 = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfTelefone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone2(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone2.setTextLimit(16);
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(264, 434, 369, 38);
		
		Label label_15 = new Label(shlEditarDoadorPessoa, SWT.NONE);
		label_15.setText("Email:");
		label_15.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_15.setBounds(192, 483, 55, 28);
		
		tfEmail = new Text(shlEditarDoadorPessoa, SWT.BORDER);
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEmail.setTextLimit(128);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(264, 478, 369, 38);
		
		Button button = new Button(shlEditarDoadorPessoa, SWT.NONE);
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarDoadorPessoa.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button.setBounds(156, 546, 184, 60);
		
		Button btnSalvarAlteraes = new Button(shlEditarDoadorPessoa, SWT.NONE);
		btnSalvarAlteraes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnSalvarAlteraes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnSalvarAlteraes.setText("Salvar altera\u00E7\u00F5es");
		btnSalvarAlteraes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarAlteraes.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnSalvarAlteraes.setBounds(357, 546, 224, 60);
		
		tfNome.setText(doador.getNome());
		tfCPF.setText(doador.getCpf());
		tfRG.setText(doador.getRg());
		tfEndereco.setText(doador.getEndereco());
		tfDataNascimento.setDay(doador.getDataNasc().getDayOfMonth());
		tfDataNascimento.setMonth(doador.getDataNasc().getMonthValue() - 1);
		tfDataNascimento.setYear(doador.getDataNasc().getYear());
		tfTelefone1.setText(doador.getTelefone());
		tfTelefone2.setText(doador.getTelefone2());
		tfEmail.setText(doador.getEmail());
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlEditarDoadorPessoa,SWT.ICON_ERROR | SWT.OK); 
	     messageBox.setText("Problemas ao editar dados do doador!");
	     messageBox.setMessage("Não foi possível editar este doador. \nConsulte o suporte.");
	     messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlEditarDoadorPessoa,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("Os dados do doador foram alterados com sucesso!");
		messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlEditarDoadorPessoa.dispose();
		}
	}
}