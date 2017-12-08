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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class CadastroAssistidoView{
	CadastroAssistidoControle controle;
	protected Shell shlCadastroAssistido;
	private Text tfRG;
	private Text tfCPF;
	private Text tfNome;
	private Text tfEndereco;
	private DateTime tfDataNascimento;
	private DateTime tfDataCadastro;
	public DateTime getTfDataCadastro() {
		return tfDataCadastro;
	}

	public void setTfDataCadastro(DateTime tfDataCadastro) {
		this.tfDataCadastro = tfDataCadastro;
	}

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

	public void setTfCPF(String tfCPF) {
		this.tfCPF.setText(tfCPF);
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

	public void setTfTelefone2(String tfTelefone2) {
		this.tfTelefone2.setText(tfTelefone2);
	}

	public Text getTfTelefone1() {
		return tfTelefone1;
	}

	public void setTfTelefone1(String tfTelefone1) {
		this.tfTelefone1.setText(tfTelefone1);
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

	public static void main() {
		try {
			CadastroAssistidoView window = new CadastroAssistidoView();
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
		Centralize.centralize(shlCadastroAssistido);
		Button button = new Button(shlCadastroAssistido, SWT.NONE);
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlCadastroAssistido.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button.setBounds(162, 665, 184, 60);
		
		Label lblPessoaFsica = new Label(shlCadastroAssistido, SWT.NONE);
		lblPessoaFsica.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		lblPessoaFsica.setBounds(241, 57, 143, 35);
		lblPessoaFsica.setText("Pessoa F\u00EDsica");
		
		Label label = new Label(shlCadastroAssistido, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(241, 129, 7, 21);
		label.setText("*");
		
		Label label_2 = new Label(shlCadastroAssistido, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(241, 219, 7, 21);
		
		Label label_3 = new Label(shlCadastroAssistido, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(241, 263, 7, 21);
		
		Label label_4 = new Label(shlCadastroAssistido, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(241, 302, 7, 21);
		
		Label label_5 = new Label(shlCadastroAssistido, SWT.NONE);
		label_5.setText("*");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_5.setBounds(241, 351, 7, 21);
		
		Label label_8 = new Label(shlCadastroAssistido, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_8.setBounds(241, 569, 7, 21);
		
		Label label_9 = new Label(shlCadastroAssistido, SWT.NONE);
		label_9.setText("*");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_9.setBounds(241, 614, 7, 21);
		
		Label lblCamposObrigatrios = new Label(shlCadastroAssistido, SWT.NONE);
		lblCamposObrigatrios.setText("* Campos Obrigat\u00F3rios");
		lblCamposObrigatrios.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblCamposObrigatrios.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblCamposObrigatrios.setBounds(254, 98, 160, 21);
		
		DateTime dateTime = new DateTime(shlCadastroAssistido, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		dateTime.setBounds(254, 390, 369, 38);
		
		Label lblDataDeCadastro = new Label(shlCadastroAssistido, SWT.NONE);
		lblDataDeCadastro.setText("Data de Cadastro:");
		lblDataDeCadastro.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblDataDeCadastro.setBounds(73, 395, 175, 28);
		shlCadastroAssistido.open();
		shlCadastroAssistido.layout();
		while (!shlCadastroAssistido.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	protected void createContents() {
		shlCadastroAssistido = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlCadastroAssistido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlCadastroAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlCadastroAssistido.setSize(698, 764);
		shlCadastroAssistido.setText("Cadastro Assistido - ABRACE");
		
		tfNome = new Text(shlCadastroAssistido, SWT.BORDER);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(254, 125, 369, 38);
		tfNome.setTextLimit(128);
		
		tfApelido = new Text(shlCadastroAssistido, SWT.BORDER);
		tfApelido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfApelido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfApelido.setBounds(254, 169, 369, 38);
		tfApelido.setTextLimit(32);
		
		tfCPF = new Text(shlCadastroAssistido, SWT.BORDER);
		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarCpf(arg0);
				identificarESC(arg0);
			}
		});
		tfCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				controle.getFocus(arg0);
			}
		});
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(254, 214, 369, 38);
		tfCPF.setTextLimit(14);
		
		tfRG = new Text(shlCadastroAssistido, SWT.BORDER);
		tfRG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(254, 258, 369, 38);
		tfRG.setTextLimit(16);
		
		tfEndereco = new Text(shlCadastroAssistido, SWT.BORDER);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(254, 302, 369, 38);
		tfEndereco.setTextLimit(256);
		
		tfDataNascimento = new DateTime(shlCadastroAssistido, SWT.BORDER);
		tfDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(254, 346, 369, 38);
		
		tfDataCadastro = new DateTime(shlCadastroAssistido, SWT.BORDER);
		tfDataCadastro.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataCadastro.setBounds(254, 390, 369, 38);
		
		Label lblDataDeCadastro = new Label(shlCadastroAssistido, SWT.NONE);
		lblDataDeCadastro.setText("Data de Cadastro:");
		lblDataDeCadastro.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblDataDeCadastro.setBounds(73, 395, 175, 28);
		
		tfTelefone1 = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTelefone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone1(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(254, 432, 369, 38);
		tfTelefone1.setTextLimit(16);
		
		tfTelefone2 = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTelefone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone2(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(254, 476, 369, 38);
		tfTelefone2.setTextLimit(16);
		
		tfEmail = new Text(shlCadastroAssistido, SWT.BORDER);
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				controle.validarEmail();
			}
		});
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(254, 520, 369, 38);
		tfEmail.setTextLimit(128);
		
		tfTipoCancer = new Text(shlCadastroAssistido, SWT.BORDER);
		tfTipoCancer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfTipoCancer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTipoCancer.setBounds(254, 564, 369, 38);
		tfTipoCancer.setTextLimit(64);
		
		tfSituacao = new Combo(shlCadastroAssistido, SWT.READ_ONLY);
		tfSituacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfSituacao.setItems(new String[] {"Vivo", "Falecido"});
		tfSituacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfSituacao.setBounds(254, 609, 99, 38);
		tfSituacao.select(0);
		
		Label label_1 = new Label(shlCadastroAssistido, SWT.NONE);
		label_1.setText("Email:");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_1.setBounds(183, 525, 55, 28);
		
		Label label_2 = new Label(shlCadastroAssistido, SWT.NONE);
		label_2.setText("Telefone 2:");
		label_2.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_2.setBounds(136, 481, 102, 28);
		
		Label label_3 = new Label(shlCadastroAssistido, SWT.NONE);
		label_3.setText("Telefone 1:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(136, 437, 102, 28);
		
		Label label_4 = new Label(shlCadastroAssistido, SWT.NONE);
		label_4.setText("Data de Nascimento:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(43, 351, 195, 28);
		
		Label label_5 = new Label(shlCadastroAssistido, SWT.NONE);
		label_5.setText("Endere\u00E7o:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(147, 307, 91, 28);
		
		Label label_6 = new Label(shlCadastroAssistido, SWT.NONE);
		label_6.setText("RG:");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_6.setBounds(206, 263, 32, 28);
		
		
		Label label_7 = new Label(shlCadastroAssistido, SWT.NONE);
		label_7.setText("CPF:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(198, 219, 40, 28);
		
		Label lblNome = new Label(shlCadastroAssistido, SWT.NONE);
		lblNome.setText("Nome:");
		lblNome.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblNome.setBounds(176, 129, 62, 28);
		
		Label lblCadastroDeAssistido = new Label(shlCadastroAssistido, SWT.NONE);
		lblCadastroDeAssistido.setText("Cadastro de Assistido");
		lblCadastroDeAssistido.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblCadastroDeAssistido.setBounds(118, 9, 382, 54);
		
		Label label_10 = new Label(shlCadastroAssistido, SWT.NONE);
		label_10.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/assistido.png").getAbsolutePath()));
		label_10.setBounds(534, 0, 75, 119);
		
		Label lblApelido = new Label(shlCadastroAssistido, SWT.NONE);
		lblApelido.setText("Apelido:");
		lblApelido.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblApelido.setBounds(162, 174, 76, 28);
		
		Label lblTipoDeCncer = new Label(shlCadastroAssistido, SWT.NONE);
		lblTipoDeCncer.setText("Tipo de C\u00E2ncer:");
		lblTipoDeCncer.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblTipoDeCncer.setBounds(94, 569, 144, 28);
		
		Label lblSituao = new Label(shlCadastroAssistido, SWT.NONE);
		lblSituao.setText("Situa\u00E7\u00E3o:");
		lblSituao.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		lblSituao.setBounds(154, 614, 84, 28);
		
		Button button_1 = new Button(shlCadastroAssistido, SWT.NONE);
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
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
		button_1.setBounds(363, 665, 184, 60);
	}
	
	public void mensagemErro(Exception e){
		 MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_ERROR | SWT.OK); 
       messageBox.setText("Problemas no cadastro do assistido!");
       messageBox.setMessage(e.getMessage()+"\nNão foi possível cadastrar este assistido.");
       messageBox.open();
	}
	
	public void mensagemCPFJaCadastrado(Exception e){
        MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_ERROR | SWT.OK);
     messageBox.setText("Problemas no cadastro do assistido!");
     messageBox.setMessage(e.getMessage());
     messageBox.open();
   }
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Assistido cadastrado com sucesso!");
		messageBox.setMessage("Cadastro realizado com sucesso!\n" + pessoaFisica.getNome()+", agora é um de nossos assistidos! :)");
		messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlCadastroAssistido.dispose();
		}
	}
	
	public boolean reativarDoador(PessoaFisica pessoa) {
		MessageBox messageBox = new MessageBox(shlCadastroAssistido,SWT.ICON_WORKING | SWT.NO | SWT.YES); 
		messageBox.setText("O CPF informado é de uma pessoa inativa no sistema!");
		messageBox.setMessage("Deseja reativar " + pessoa.getNome()+" CPF:"+ pessoa.getCpf()+"?");
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
}