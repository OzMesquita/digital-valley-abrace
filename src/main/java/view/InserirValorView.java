package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import control.DoacaoControle;
import control.DoacaoSingleton;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class InserirValorView {

	protected Shell shlRealizarDoao;
	private DateTime tfDataDoacao;
	DoacaoControle controle = new DoacaoControle(this);
	private Text tfNomeDoador;
	private Text tfCPFCNPJ;
	private Text tfValor;
	private Text tfEndereco;
	private Text tfEmail;
	private DateTime dateTime_1;

	public Shell getShlRealizarDoao() {
		return shlRealizarDoao;
	}

	public void setShlRealizarDoao(Shell shlRealizarDoao) {
		this.shlRealizarDoao = shlRealizarDoao;
	}

	public Text getTfNomeDoador() {
		return tfNomeDoador;
	}

	public void setTfNomeDoador(Text tfNomeDoador) {
		this.tfNomeDoador = tfNomeDoador;
	}

	public Text getTfCPFCNPJ() {
		return tfCPFCNPJ;
	}

	public void setTfCPFCNPJ(Text tfCPFCNPJ) {
		this.tfCPFCNPJ = tfCPFCNPJ;
	}

	public Text getTfValor() {
		return tfValor;
	}

	public void setTfValor(Text tfValor) {
		this.tfValor = tfValor;
	}

	public Text getTfEndereco() {
		return tfEndereco;
	}

	public void setTfEndereco(Text tfEndereco) {
		this.tfEndereco = tfEndereco;
	}

	public Text getTfEmail() {
		return tfEmail;
	}

	public void setTfEmail(Text tfEmail) {
		this.tfEmail = tfEmail;
	}

	public DateTime getDateTime_1() {
		return dateTime_1;
	}

	public void setDateTime_1(DateTime dateTime_1) {
		this.dateTime_1 = dateTime_1;
	}

	/**
	 * Launch the application.
	 * @param args
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		try {
			InserirValorView window = new InserirValorView();
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
		Centralize.centralize(shlRealizarDoao);
		
		Label label = new Label(shlRealizarDoao, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/doacao.png").getAbsolutePath()));
		label.setBounds(550, 18, 140, 127);
		shlRealizarDoao.open();
		shlRealizarDoao.layout();
		while (!shlRealizarDoao.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public DateTime getTfDataDoacao() {
		return tfDataDoacao;
	}

	public void setTfDataDoacao(DateTime tfDataDoacao) {
		this.tfDataDoacao = tfDataDoacao;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRealizarDoao = new Shell();
		shlRealizarDoao.setSize(730, 762);
		shlRealizarDoao.setText("Realizar Doa\u00E7\u00E3o - Passo 2 de 2 - ABRACE");
		shlRealizarDoao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		
		Label label = new Label(shlRealizarDoao, SWT.NONE);
		label.setText("Registrar Doa\u00E7\u00E3o");
		label.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		label.setBounds(217, 25, 280, 50);
		
		Label lblNomeDoDoador = new Label(shlRealizarDoao, SWT.NONE);
		lblNomeDoDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblNomeDoDoador.setBounds(99, 224, 164, 30);
		lblNomeDoDoador.setText("Nome do doador:");
		
		tfNomeDoador = new Text(shlRealizarDoao, SWT.BORDER);
		tfNomeDoador.setEnabled(false);
		tfNomeDoador.setEditable(false);
		tfNomeDoador.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfNomeDoador.setBounds(267, 221, 331, 37);
		
		Label lblCpfCnpj = new Label(shlRealizarDoao, SWT.NONE);
		lblCpfCnpj.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblCpfCnpj.setBounds(155, 267, 108, 30);
		lblCpfCnpj.setText("CPF / CNPJ:");
		
		tfCPFCNPJ = new Text(shlRealizarDoao, SWT.BORDER);
		tfCPFCNPJ.setEnabled(false);
		tfCPFCNPJ.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfCPFCNPJ.setEditable(false);
		tfCPFCNPJ.setBounds(266, 264, 331, 37);
		
		tfValor = new Text(shlRealizarDoao, SWT.BORDER);
		tfValor.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfValor.setBounds(267, 503, 331, 37);
		
		Label lblValorr = new Label(shlRealizarDoao, SWT.NONE);
		lblValorr.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblValorr.setBounds(170, 506, 93, 30);
		lblValorr.setText("Valor (R$):");
		
		Label lblDataDaDoacao = new Label(shlRealizarDoao, SWT.NONE);
		lblDataDaDoacao.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblDataDaDoacao.setBounds(111, 549, 152, 30);
		lblDataDaDoacao.setText("Data da doa\u00E7\u00E3o:");
		
		dateTime_1 = new DateTime(shlRealizarDoao, SWT.BORDER);
		dateTime_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		dateTime_1.setBounds(267, 546, 331, 37);
		
		Label lblEndereo = new Label(shlRealizarDoao, SWT.NONE);
		lblEndereo.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblEndereo.setBounds(172, 310, 91, 30);
		lblEndereo.setText("Endere\u00E7o:");
		
		tfEndereco = new Text(shlRealizarDoao, SWT.BORDER);
		tfEndereco.setEnabled(false);
		tfEndereco.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEndereco.setEditable(false);
		tfEndereco.setBounds(267, 307, 331, 37);
		
		Label lblConfirmeOsDados = new Label(shlRealizarDoao, SWT.NONE);
		lblConfirmeOsDados.setAlignment(SWT.CENTER);
		lblConfirmeOsDados.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblConfirmeOsDados.setBounds(218, 81, 277, 50);
		lblConfirmeOsDados.setText("Confirme os dados do doador\r\nIndique o valor e data de doa\u00E7\u00E3o");
		
		Label label_1 = new Label(shlRealizarDoao, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(0, 151, 714, 2);
		
		Label lblDadosDoDoador = new Label(shlRealizarDoao, SWT.NONE);
		lblDadosDoDoador.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		lblDadosDoDoador.setBounds(263, 168, 188, 32);
		lblDadosDoDoador.setText("Dados do doador");
		
		Label label_2 = new Label(shlRealizarDoao, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(0, 422, 714, 2);
		
		Label lblEmail = new Label(shlRealizarDoao, SWT.NONE);
		lblEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblEmail.setBounds(200, 353, 63, 30);
		lblEmail.setText("E-mail:");
		
		tfEmail = new Text(shlRealizarDoao, SWT.BORDER);
		tfEmail.setEnabled(false);
		tfEmail.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		tfEmail.setEditable(false);
		tfEmail.setBounds(267, 350, 331, 37);
		
		Label lblDadosDaDoao = new Label(shlRealizarDoao, SWT.NONE);
		lblDadosDaDoao.setText("Dados da doa\u00E7\u00E3o");
		lblDadosDaDoao.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		lblDadosDaDoao.setBounds(263, 452, 188, 32);
		
		Button button = new Button(shlRealizarDoao, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlRealizarDoao.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button.setBounds(155, 626, 214, 54);
		
		Button btnSalvarDoao = new Button(shlRealizarDoao, SWT.NONE);
		btnSalvarDoao.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		btnSalvarDoao.setText("Salvar doa\u00E7\u00E3o");
		btnSalvarDoao.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnSalvarDoao.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnSalvarDoao.setBounds(375, 626, 214, 54);
		
		tfNomeDoador.setText(DoacaoSingleton.getDoacao().getDoador().getNome());
		tfEndereco.setText(DoacaoSingleton.getDoacao().getDoador().getEndereco());
		tfEmail.setText(DoacaoSingleton.getDoacao().getDoador().getEmail());
		tfCPFCNPJ.setText(DoacaoSingleton.getCpfCNPJ());
	}
	
	public void mensagemErro(Exception e){
		MessageBox messageBox = new MessageBox(shlRealizarDoao,SWT.ICON_ERROR | SWT.OK); 
		messageBox.setText("Problemas no registro da doação");
		messageBox.setMessage(e.getMessage()+"\nTente novamente");
		messageBox.open();
	}
	
	public void mensagemSucesso(){
		MessageBox messageBox = new MessageBox(shlRealizarDoao,SWT.ICON_WORKING | SWT.OK); 
		messageBox.setText("Doação realizada com sucesso!");
		messageBox.setMessage("A doação foi realizada com sucesso! :)");
		messageBox.open();
	}
}
