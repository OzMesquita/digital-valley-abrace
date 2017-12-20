package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import control.EditarDoadorPJControle;
import exceptions.PessoaInvalidaException;
import model.PessoaJuridica;
import view.interfaces.ViewPessoa;
import view.interfaces.ViewPessoaJuridica;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;

public class EditarDoadorPJView implements ViewPessoaJuridica,ViewPessoa{
	private EditarDoadorPJControle controle;
	private Shell shlEditarDoador_1;
	private PessoaJuridica doador;
	private Text tfNome;
	private Text tfCNPJ;
	private Text tfFantasia;
	private Text tfEndereco;
	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	
	public EditarDoadorPJView(PessoaJuridica doador) {
		this.setControle(new EditarDoadorPJControle(this));
		this.setDoador(doador);
	}

	public EditarDoadorPJView() {
		this.setControle(new EditarDoadorPJControle(this));
	}

	public EditarDoadorPJControle getControle() {
		return controle;
	}

	public void setControle(EditarDoadorPJControle controle) {
		this.controle = controle;
	}

	public PessoaJuridica getDoador() {
		return doador;
	}

	public void setDoador(PessoaJuridica doador) {
		this.doador = doador;
	}
	
	public Shell getShlCadastroDoador() {
		return shlEditarDoador_1;
	}

	public void setShlCadastroDoador(Shell shlCadastroDoador) {
		this.shlEditarDoador_1 = shlCadastroDoador;
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

	public void setTfCNPJ(String tfCNPJ) {
		this.tfCNPJ.setText(tfCNPJ);
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

	public void setTfEmail(String tfEmail) {
		this.tfEmail.setText(tfEmail);
	}

	public static void main(PessoaJuridica doador) {
		try {
			EditarDoadorPJView window = new EditarDoadorPJView(doador);
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
		Centralize.centralize(shlEditarDoador_1);
		
		Label lblDoadorPessoaJurdica = new Label(shlEditarDoador_1, SWT.NONE);
		lblDoadorPessoaJurdica.setText("Pessoa Jur\u00EDdica");
		lblDoadorPessoaJurdica.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		lblDoadorPessoaJurdica.setBounds(271, 93, 167, 35);
		
		Label label = new Label(shlEditarDoador_1, SWT.NONE);
		label.setText("* Campos Obrigat\u00F3rios");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label.setBounds(246, 141, 160, 21);
		
		Label label_1 = new Label(shlEditarDoador_1, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(232, 173, 7, 21);
		
		Label label_2 = new Label(shlEditarDoador_1, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(232, 261, 7, 21);
		
		Label label_3 = new Label(shlEditarDoador_1, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(232, 217, 7, 21);
		
		Label label_4 = new Label(shlEditarDoador_1, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(232, 305, 7, 21);
		shlEditarDoador_1.open();
		shlEditarDoador_1.layout();
		while (!shlEditarDoador_1.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlEditarDoador_1 = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlEditarDoador_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlEditarDoador_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlEditarDoador_1.setSize(715, 635);
		shlEditarDoador_1.setText("Editar Doador - ABRACE");
		
		Label lblCadastroDeDoador = new Label(shlEditarDoador_1, SWT.NONE);
		lblCadastroDeDoador.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeDoador.setBounds(232, 33, 245, 54);
		lblCadastroDeDoador.setText("Editar Doador");
		
		Label lblRazoSocial = new Label(shlEditarDoador_1, SWT.NONE);
		lblRazoSocial.setText("Raz\u00E3o Social:");
		lblRazoSocial.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblRazoSocial.setBounds(105, 173, 122, 28);
		
		tfNome = new Text(shlEditarDoador_1, SWT.BORDER);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(244, 168, 369, 38);
		
		Label label_1 = new Label(shlEditarDoador_1, SWT.NONE);
		label_1.setText("CNPJ:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(172, 261, 55, 28);
		
		tfCNPJ = new Text(shlEditarDoador_1, SWT.BORDER);
		tfCNPJ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarCnpj(arg0,controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfCNPJ.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCNPJ.setBounds(244, 256, 369, 38);
		
		Label label_2 = new Label(shlEditarDoador_1, SWT.NONE);
		label_2.setText("Fantasia:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(146, 217, 81, 28);
		
		tfFantasia = new Text(shlEditarDoador_1, SWT.BORDER);
		tfFantasia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfFantasia.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfFantasia.setBounds(244, 212, 369, 38);
		
		Label label_3 = new Label(shlEditarDoador_1, SWT.NONE);
		label_3.setText("Endere\u00E7o:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(136, 305, 91, 28);
		
		tfEndereco = new Text(shlEditarDoador_1, SWT.BORDER);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(244, 300, 369, 38);
		
		Label lblTelefone_1 = new Label(shlEditarDoador_1, SWT.NONE);
		lblTelefone_1.setText("Telefone 1:");
		lblTelefone_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone_1.setBounds(125, 349, 102, 28);
		
		tfTelefone1 = new Text(shlEditarDoador_1, SWT.BORDER);
		tfTelefone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone1(arg0,controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfTelefone1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarTelefone1(arg0, controle.getViewDoador())) {
						throw new PessoaInvalidaException("Telefone incorreto! Insira um telefone v�lido!");
					}
					new PessoaJuridica().setTelefone(getTfTelefone1().getText());
				}catch(PessoaInvalidaException e1) {
					mensagemErroTelefone(e1);
				}
			}
		});
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(244, 344, 369, 38);
		tfTelefone1.setTextLimit(14);
		
		Label lblTelefone = new Label(shlEditarDoador_1, SWT.NONE);
		lblTelefone.setText("Telefone 2:");
		lblTelefone.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone.setBounds(125, 393, 102, 28);
		
		tfTelefone2 = new Text(shlEditarDoador_1, SWT.BORDER);
		tfTelefone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone2(arg0,controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfTelefone2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarTelefone2(arg0, controle.getViewDoador())) {
						throw new PessoaInvalidaException("Telefone incorreto! Insira um telefone v�lido!");
					}
					new PessoaJuridica().setTelefone2(getTfTelefone2().getText());
				}catch(PessoaInvalidaException e1) {
					mensagemErroTelefone(e1);
				}
			}
		});
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(244, 388, 369, 38);
		tfTelefone2.setTextLimit(14);
		
		Label label_7 = new Label(shlEditarDoador_1, SWT.NONE);
		label_7.setText("Email:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(172, 437, 55, 28);
		
		tfEmail = new Text(shlEditarDoador_1, SWT.BORDER);
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarEmail(controle.getViewDoador())) {
						throw new PessoaInvalidaException("Email incorreto! Insira um e-mail v�lido!");
					}
					new PessoaJuridica().setEmail(getTfEmail().getText());
				}catch(PessoaInvalidaException e1) {
					mensagemErroEmail(e1);
				}
			}
		});
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(244, 432, 369, 38);
		tfTelefone2.setTextLimit(128);
		
		Button btnCadastrar = new Button(shlEditarDoador_1, SWT.NONE);
		btnCadastrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnCadastrar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnCadastrar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCadastrar.setBounds(360, 511, 232, 60);
		btnCadastrar.setText("Salvar altera\u00E7\u00F5es");
		
		Button btnCancelar = new Button(shlEditarDoador_1, SWT.NONE);
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarDoador_1.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCancelar.setBounds(159, 511, 184, 60);
		btnCancelar.setText("Cancelar");
		
		Label label_9 = new Label(shlEditarDoador_1, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pessoa-juridica-medium.png").getAbsolutePath()));
		label_9.setBounds(510, 10, 109, 114);
	
		tfNome.setText(doador.getNome());
		tfCNPJ.setText(doador.getCnpj());
		tfFantasia.setText(doador.getNomeFantasia());
		tfEndereco.setText(doador.getEndereco());
		tfTelefone1.setText(doador.getTelefone());
		tfTelefone2.setText(doador.getTelefone2());
		tfEmail.setText(doador.getEmail());
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlEditarDoador_1,SWT.ICON_ERROR | SWT.OK); 
		 messageBox.setText("Problemas ao editar dados do doador jur�dico!");
		 messageBox.setMessage("N�o foi poss�vel editar este doador pessoa jur�dica. \nConsulte o suporte.");
		 messageBox.open();
	}
	
	public void mensagemSucesso(PessoaJuridica pessoaJuridica){
		MessageBox messageBox = new MessageBox(shlEditarDoador_1,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Doador editado com sucesso!");
		messageBox.setMessage("As informa��es do doador foram alteradas com sucesso");
		messageBox.open();
	}
	public void mensagemErroEmail(Exception e) {
		MessageBox messageBox = new MessageBox(shlEditarDoador_1, SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("E-mail incorreto!");
		messageBox.setMessage(e.getMessage());
		messageBox.open();
	}
	
	public void mensagemErroTelefone(Exception e) {
		MessageBox messageBox = new MessageBox(shlEditarDoador_1, SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("Telefone incorreto!");
		messageBox.setMessage(e.getMessage());
		messageBox.open();
	}
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlEditarDoador_1.dispose();
		}
	}
}