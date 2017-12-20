package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import control.CadastroDoadorPFControle;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import model.PessoaFisica;
import view.interfaces.ViewPessoa;
import view.interfaces.ViewPessoaFisica;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class CadastroDoadorPFisicaView implements ViewPessoaFisica, ViewPessoa{
	CadastroDoadorPFControle controle;
	protected Shell shlCadastroDoador;
	private Text tfNome;
	private Text tfCPF;
	private Text tfRG;
	private Text tfEndereco;
	DateTime tfDataNascimento;
	private Text tfTelefone1;
	private Text tfTelefone2;
	private Text tfEmail;
	
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

	public CadastroDoadorPFisicaView() {
		this.controle = new CadastroDoadorPFControle(this);
	}

	public static void main() {
		try {
			CadastroDoadorPFisicaView window = new CadastroDoadorPFisicaView();
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
		Centralize.centralize(shlCadastroDoador);
		
		Label lblDoadorPessoaFsica = new Label(shlCadastroDoador, SWT.NONE);
		lblDoadorPessoaFsica.setText("Pessoa F\u00EDsica");
		lblDoadorPessoaFsica.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		lblDoadorPessoaFsica.setBounds(254, 97, 143, 35);
		
		Label label = new Label(shlCadastroDoador, SWT.NONE);
		label.setText("* Campos Obrigat\u00F3rios");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label.setBounds(262, 152, 160, 21);
		
		Label label_1 = new Label(shlCadastroDoador, SWT.NONE);
		label_1.setText("*");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(249, 183, 7, 21);
		
		Label label_2 = new Label(shlCadastroDoador, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(249, 228, 7, 21);
		
		Label label_3 = new Label(shlCadastroDoador, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(249, 272, 7, 21);
		
		Label label_4 = new Label(shlCadastroDoador, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(249, 316, 7, 21);
		
		Label label_5 = new Label(shlCadastroDoador, SWT.NONE);
		label_5.setText("*");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_5.setBounds(249, 360, 7, 21);
		shlCadastroDoador.open();
		shlCadastroDoador.layout();
		while (!shlCadastroDoador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public Shell getShlCadastroDoador() {
		return shlCadastroDoador;
	}

	public void setShlCadastroDoador(Shell shlCadastroDoador) {
		this.shlCadastroDoador = shlCadastroDoador;
	}

	protected void createContents() {
		shlCadastroDoador = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlCadastroDoador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlCadastroDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroDoador.setSize(694, 668);
		shlCadastroDoador.setText("Cadastro Doador - ABRACE");
		
		Label lblCadastroDeDoador = new Label(shlCadastroDoador, SWT.NONE);
		lblCadastroDeDoador.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeDoador.setBounds(139, 35, 356, 54);
		lblCadastroDeDoador.setText("Cadastro de Doador");
		
		Label label = new Label(shlCadastroDoador, SWT.NONE);
		label.setText("Nome:");
		label.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label.setBounds(183, 184, 62, 28);
		
		tfNome = new Text(shlCadastroDoador, SWT.BORDER);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
				controle.filtrarNome(arg0, controle.getViewDoador());
			}
		});
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(262, 179, 369, 38);
		tfNome.setTextLimit(128);
		
		Label label_1 = new Label(shlCadastroDoador, SWT.NONE);
		label_1.setText("CPF:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(205, 228, 40, 28);
		
		tfCPF = new Text(shlCadastroDoador, SWT.BORDER);
		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarCpf(arg0,controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!tfCPF.getText().equals("")) {
					controle.getFocus(arg0);
					try {
						new PessoaFisica().setCpf(getTfCPF().getText());
					} catch(PessoaFisicaException e) {
						mensagemErro(e);
					}
				}
			}
		});
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(262, 223, 369, 38);
		tfCPF.setTextLimit(14);
		
		Label label_2 = new Label(shlCadastroDoador, SWT.NONE);
		label_2.setText("RG:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(213, 272, 32, 28);
		
		tfRG = new Text(shlCadastroDoador, SWT.BORDER);
		tfRG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(262, 267, 369, 38);
		tfRG.setTextLimit(16);
		
		Label label_3 = new Label(shlCadastroDoador, SWT.NONE);
		label_3.setText("Endere\u00E7o:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(154, 316, 91, 28);
		
		tfEndereco = new Text(shlCadastroDoador, SWT.BORDER);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(262, 311, 369, 38);
		tfEndereco.setTextLimit(268);
		
		Label label_4 = new Label(shlCadastroDoador, SWT.NONE);
		label_4.setText("Data de Nascimento:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(50, 360, 195, 28);
		
		tfDataNascimento = new DateTime(shlCadastroDoador, SWT.BORDER);
		tfDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(262, 355, 369, 38);
		
		Label lblTelefone_1 = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone_1.setText("Telefone 1:");
		lblTelefone_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone_1.setBounds(143, 404, 102, 28);
		
		tfTelefone1 = new Text(shlCadastroDoador, SWT.BORDER);
		
		tfTelefone1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarTelefone1(arg0, controle.getViewDoador())) {
						throw new PessoaInvalidaException("Telefone incorreto! Insira um telefone válido!");
					}
					new PessoaFisica().setTelefone(getTfTelefone1().getText());
				}catch(PessoaInvalidaException e1) {
					mensagemErroTelefone(e1);
				}
			}
		});
		tfTelefone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone1(arg0, controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(262, 399, 369, 38);
		tfTelefone1.setTextLimit(14);
		
		Label lblTelefone = new Label(shlCadastroDoador, SWT.NONE);
		lblTelefone.setText("Telefone 2:");
		lblTelefone.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTelefone.setBounds(143, 448, 102, 28);
		
		tfTelefone2 = new Text(shlCadastroDoador, SWT.BORDER);
		
		tfTelefone2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarTelefone2(arg0, controle.getViewDoador())) {
						throw new PessoaInvalidaException("Telefone incorreto! Insira um telefone válido!");
					}
					new PessoaFisica().setTelefone2(getTfTelefone2().getText());
				}catch(PessoaInvalidaException e1) {
					mensagemErroTelefone(e1);
				}
			}
		});
		tfTelefone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone2(arg0, controle.getViewDoador());
				identificarESC(arg0);
			}
		});
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(262, 443, 369, 38);
		tfTelefone2.setTextLimit(14);
		
		Label label_7 = new Label(shlCadastroDoador, SWT.NONE);
		label_7.setText("Email:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(190, 492, 55, 28);
		
		tfEmail = new Text(shlCadastroDoador, SWT.BORDER);
		
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					if(!controle.validarEmail(controle.getViewDoador())) {
						throw new PessoaInvalidaException("Email incorreto! Insira um e-mail válido!");
					}
					new PessoaFisica().setEmail(getTfEmail().getText());
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
		tfEmail.setBounds(262, 487, 369, 38);
		tfEmail.setTextLimit(128);
		
		Button btnCadastrar = new Button(shlCadastroDoador, SWT.NONE);
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
		btnCadastrar.setBounds(369, 550, 184, 60);
		btnCadastrar.setText("Cadastrar");
		
		Button btnCancelar = new Button(shlCadastroDoador, SWT.NONE);
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		btnCancelar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlCadastroDoador.dispose();
			}
		});
		btnCancelar.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		btnCancelar.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnCancelar.setBounds(168, 550, 184, 60);
		btnCancelar.setText("Cancelar");
		
		Label label_9 = new Label(shlCadastroDoador, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doadores.png").getAbsolutePath()));
		label_9.setBounds(511, 22, 109, 114);
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_ERROR | SWT.OK); 
        messageBox.setText("Problemas no cadastro do doador físico!");
        messageBox.setMessage(e.getMessage()+"\nNão foi possível cadastrar este doador pessoa física.");
        messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Doador cadastrado com sucesso!");
		messageBox.setMessage("Cadastro realizado com sucesso!\n" + pessoaFisica.getNome()+", agora é um de nossos doadores! :)");
		messageBox.open();
	}
	
	public void mensagemCPFJaCadastrado(Exception e){
        MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_ERROR | SWT.OK);
     messageBox.setText("Problemas no cadastro do doador pessoa física!");
     messageBox.setMessage(e.getMessage());
     messageBox.open();
   }
	
	public void mensagemErroEmail(Exception e) {
		MessageBox messageBox = new MessageBox(shlCadastroDoador, SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("E-mail incorreto!");
		messageBox.setMessage(e.getMessage());
		messageBox.open();
	}
	
	public void mensagemErroTelefone(Exception e) {
		MessageBox messageBox = new MessageBox(shlCadastroDoador, SWT.ICON_ERROR | SWT.OK);
		messageBox.setText("Telefone incorreto!");
		messageBox.setMessage(e.getMessage());
		messageBox.open();
	}
	
	public void mensagemSucessoReativacao(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Doador reativado com sucesso!");
		messageBox.setMessage("Reativação realizada com sucesso!\n" + pessoaFisica.getNome()+", voltou a ser um de nossos doadores! :)");
		messageBox.open();
	}

	public boolean reativarDoador(PessoaFisica pessoa) {
		MessageBox messageBox = new MessageBox(shlCadastroDoador,SWT.ICON_WORKING | SWT.NO | SWT.YES); 
		messageBox.setText("O CPF informado já está cadastrado");
		messageBox.setMessage("O CPF informado é de uma pessoa inativa no sistema. Deseja reativá-la?\nNome: " + pessoa.getNome()+"\nCPF:"+ pessoa.getCpf()+"\nData de Nascimento: " + pessoa.getDataNasc() + "?");
		int buttonID = messageBox.open();
        switch(buttonID) {
          case SWT.NO:
        	  return false;
          case SWT.YES:
            return true;
          default:
        	  return false;
        }
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlCadastroDoador.dispose();
		}
	}
}