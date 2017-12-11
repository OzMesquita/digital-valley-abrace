package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import control.EditarAssistidoControle;
import exceptions.PessoaFisicaException;
import model.Assistido;
import model.PessoaFisica;
import view.interfaces.ViewAssistido;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class EditarAssistidoView implements ViewAssistido{
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
	private Combo tfSituacao;
	private Assistido assistido;
	private DateTime tfDataNascimento;
	private EditarAssistidoControle controle;
	
	public Text getTfNome() {
		return tfNome;
	}

	public void setTfNome(String tfNome) {
		this.tfNome.setText(tfNome);
	}

	public Text getTfApelido() {
		return tfApelido;
	}

	public void setTfApelido(String tfApelido) {
		this.tfApelido.setText(tfApelido);
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

	public void setTfRG(String tfRG) {
		this.tfRG.setText(tfRG);
	}

	public Text getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(String tfEndereco) {
		this.tfEndereco.setText(tfEndereco);
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

	public Text getTfTipoCancer() {
		return tfTipoCancer;
	}

	public void setTfTipoCancer(String tfTipoCancer) {
		this.tfTipoCancer.setText(tfTipoCancer);
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

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido a) {
		this.assistido = a;
	}
	
    public EditarAssistidoControle getControle() {
		return controle;
	}

	public void setControle(EditarAssistidoControle controle) {
		this.controle = controle;
	}

	public DateTime getTfDataNascimento() {
		return tfDataNascimento;
	}

	public void setTfDataNascimento(DateTime tfDataNascimento) {
		this.tfDataNascimento = tfDataNascimento;
	}

	public Shell getShlEditarAssistido() {
		return shlEditarAssistido;
	}

	public void setShlEditarAssistido(Shell shlEditarAssistido) {
		this.shlEditarAssistido = shlEditarAssistido;
	}

	public EditarAssistidoView(Assistido assistido) {
        if(assistido != null) {
            this.setAssistido(assistido);
        }
        this.setControle(new EditarAssistidoControle(this));
    }
    
    public EditarAssistidoView() {
    	this.setControle(new EditarAssistidoControle(this));
    }

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Centralize.centralize(shlEditarAssistido);
		
		Label label = new Label(shlEditarAssistido, SWT.NONE);
		label.setText("Pessoa F\u00EDsica");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 19, SWT.NORMAL));
		label.setBounds(253, 94, 143, 35);
		
		Label label_1 = new Label(shlEditarAssistido, SWT.NONE);
		label_1.setText("* Campos Obrigat\u00F3rios");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_1.setBounds(261, 142, 160, 21);
		
		Label label_2 = new Label(shlEditarAssistido, SWT.NONE);
		label_2.setText("*");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_2.setBounds(250, 175, 7, 21);
		
		Label label_4 = new Label(shlEditarAssistido, SWT.NONE);
		label_4.setText("*");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_4.setBounds(250, 267, 7, 21);
		
		Label label_5 = new Label(shlEditarAssistido, SWT.NONE);
		label_5.setText("*");
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_5.setBounds(250, 311, 7, 21);
		
		Label label_6 = new Label(shlEditarAssistido, SWT.NONE);
		label_6.setText("*");
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_6.setBounds(250, 355, 7, 21);
		
		Label label_7 = new Label(shlEditarAssistido, SWT.NONE);
		label_7.setText("*");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_7.setBounds(250, 399, 7, 21);
		
		Label label_8 = new Label(shlEditarAssistido, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_8.setBounds(250, 577, 7, 21);
		
		Label label_3 = new Label(shlEditarAssistido, SWT.NONE);
		label_3.setText("*");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		label_3.setBounds(250, 625, 7, 21);
		shlEditarAssistido.open();
		shlEditarAssistido.layout();
		while (!shlEditarAssistido.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	protected void createContents() {
		shlEditarAssistido = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
		shlEditarAssistido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlEditarAssistido.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlEditarAssistido.setSize(715, 819);
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
		label_2.setBounds(185, 175, 62, 28);
		
		tfNome = new Text(shlEditarAssistido, SWT.BORDER);
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfNome.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNome.setBounds(261, 170, 369, 38);
		
		Label label_3 = new Label(shlEditarAssistido, SWT.NONE);
		label_3.setText("Apelido:");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_3.setBounds(171, 219, 76, 28);
		
		tfApelido = new Text(shlEditarAssistido, SWT.BORDER);
		tfApelido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfApelido.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfApelido.setBounds(261, 214, 369, 38);
		
		Label label_4 = new Label(shlEditarAssistido, SWT.NONE);
		label_4.setText("CPF:");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_4.setBounds(207, 267, 40, 28);
		
		tfCPF = new Text(shlEditarAssistido, SWT.BORDER);
		tfCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				controle.getFocus(arg0);
				controle.validarCPF(controle.getViewAssistido());
				try {
					new Assistido().setCpf(getTfCPF().getText());
				} catch(PessoaFisicaException e) {
					setTfCPF("");
					mensagemErro(e);
				}
			}
		});
		tfCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarCpf(arg0);
				identificarESC(arg0);
			}
		});
		tfCPF.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPF.setBounds(261, 262, 369, 38);
		
		Label label_5 = new Label(shlEditarAssistido, SWT.NONE);
		label_5.setText("RG:");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_5.setBounds(215, 311, 32, 28);
		
		tfRG = new Text(shlEditarAssistido, SWT.BORDER);
		tfRG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfRG.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfRG.setBounds(261, 306, 369, 38);
		
		Label label_6 = new Label(shlEditarAssistido, SWT.NONE);
		label_6.setText("Endere\u00E7o:");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_6.setBounds(156, 355, 91, 28);
		
		tfEndereco = new Text(shlEditarAssistido, SWT.BORDER);
		tfEndereco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setBounds(261, 350, 369, 38);
		
		Label label_7 = new Label(shlEditarAssistido, SWT.NONE);
		label_7.setText("Data de Nascimento:");
		label_7.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_7.setBounds(52, 399, 195, 28);
		
		tfDataNascimento = new DateTime(shlEditarAssistido, SWT.BORDER);
		tfDataNascimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfDataNascimento.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfDataNascimento.setBounds(261, 394, 369, 38);
		
		Label label_8 = new Label(shlEditarAssistido, SWT.NONE);
		label_8.setText("Telefone 1:");
		label_8.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_8.setBounds(145, 443, 102, 28);
		
		tfTelefone1 = new Text(shlEditarAssistido, SWT.BORDER);
		tfTelefone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone1(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone1.setBounds(261, 438, 369, 38);
		
		Label label_9 = new Label(shlEditarAssistido, SWT.NONE);
		label_9.setText("Telefone 2:");
		label_9.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_9.setBounds(145, 487, 102, 28);
		
		tfTelefone2 = new Text(shlEditarAssistido, SWT.BORDER);
		tfTelefone2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				controle.filtrarTelefone2(arg0);
				identificarESC(arg0);
			}
		});
		tfTelefone2.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTelefone2.setBounds(261, 482, 369, 38);
		
		Label label_10 = new Label(shlEditarAssistido, SWT.NONE);
		label_10.setText("Email:");
		label_10.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_10.setBounds(192, 531, 55, 28);
		
		tfEmail = new Text(shlEditarAssistido, SWT.BORDER);
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setBounds(261, 526, 369, 38);
		
		Label label_11 = new Label(shlEditarAssistido, SWT.NONE);
		label_11.setText("Tipo de Cancer:");
		label_11.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_11.setBounds(103, 577, 144, 28);
		
		tfTipoCancer = new Text(shlEditarAssistido, SWT.BORDER);
		tfTipoCancer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfTipoCancer.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfTipoCancer.setBounds(261, 572, 369, 38);
		
		Label label_13 = new Label(shlEditarAssistido, SWT.NONE);
		label_13.setText("Situa\u00E7\u00E3o:");
		label_13.setFont(SWTResourceManager.getFont("Segoe UI Emoji", 16, SWT.NORMAL));
		label_13.setBounds(163, 625, 84, 28);
		
		tfSituacao = new Combo(shlEditarAssistido, SWT.READ_ONLY);
		tfSituacao.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		tfSituacao.setItems(new String[] {"Vivo", "Falecido"});
		tfSituacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfSituacao.setBounds(261, 620, 99, 38);
		tfSituacao.select(0);
		tfNome.setText(getAssistido().getNome());
		tfApelido.setText(getAssistido().getApelido());
		tfCPF.setText(getAssistido().getCpf());
		tfRG.setText(getAssistido().getRg());
		tfEndereco.setText(getAssistido().getEndereco());
		tfTelefone1.setText(getAssistido().getTelefone());
		tfTelefone2.setText(getAssistido().getTelefone2());
		tfEmail.setText(getAssistido().getEmail());
		tfTipoCancer.setText(getAssistido().getTipoDeCancer());
		if(getAssistido().getSituacao()) {
			tfSituacao.select(0);
		} else {
			tfSituacao.select(1);
		}
		tfDataNascimento.setDay(getAssistido().getDataNasc().getDayOfMonth());
		tfDataNascimento.setMonth(getAssistido().getDataNasc().getMonthValue()-1);
		tfDataNascimento.setYear(getAssistido().getDataNasc().getYear());
		
		Button btnSalvarAlteraes = new Button(shlEditarAssistido, SWT.NONE);
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
				GerenciarAssistidosView.main();
			}
		});
		btnSalvarAlteraes.setText("Salvar altera\u00E7\u00F5es");
		btnSalvarAlteraes.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarAlteraes.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		btnSalvarAlteraes.setBounds(372, 702, 217, 60);
		
		Button button_1 = new Button(shlEditarAssistido, SWT.NONE);
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEditarAssistido.dispose();
				GerenciarAssistidosView.main();
			}
		});
		button_1.setText("Cancelar");
		button_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button_1.setBounds(171, 702, 184, 60);
	}

	public static void main() {
		try {
			EditarAssistidoView window = new EditarAssistidoView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mensagemErro(Exception e){
		MessageBox messageBox = new MessageBox(shlEditarAssistido,SWT.ICON_ERROR | SWT.OK); 
		messageBox.setText("Problemas ao editar dados do assistido!");
		messageBox.setMessage("Não foi possível editar este assistido. \nConsulte o suporte.");
		messageBox.open();
	}
	
	public void mensagemSucesso(PessoaFisica pessoaFisica){
		MessageBox messageBox = new MessageBox(shlEditarAssistido,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Operação realizada com sucesso!");
		messageBox.setMessage("Os dados do assitido foram alterados com sucesso!");
		messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlEditarAssistido.dispose();
		}
	}
	
	public boolean reativarDoador(PessoaFisica pessoa) {
		MessageBox messageBox = new MessageBox(shlEditarAssistido,SWT.ICON_WORKING | SWT.NO | SWT.YES); 
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
	public void mensagemCPFJaCadastrado(Exception e){
        MessageBox messageBox = new MessageBox(shlEditarAssistido,SWT.ICON_ERROR | SWT.OK);
	     messageBox.setText("Problemas no cadastro do assistido!");
	     messageBox.setMessage(e.getMessage());
	     messageBox.open();
   }
}