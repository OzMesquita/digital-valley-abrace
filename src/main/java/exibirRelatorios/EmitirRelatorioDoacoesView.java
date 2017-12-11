package exibirRelatorios;

import java.io.File;
import java.time.LocalDate;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import view.Centralize;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;

public class EmitirRelatorioDoacoesView {

	protected Shell shlEmitirRelatrioMensal;
	private EmitirRelatorioDoacoesControle controle;
	private DateTime dataInicio;
	private DateTime dataTermino;

	public Shell getShlEmitirRelatrioMensal() {
		return shlEmitirRelatrioMensal;
	}

	public void setShlEmitirRelatrioMensal(Shell shlEmitirRelatrioMensal) {
		this.shlEmitirRelatrioMensal = shlEmitirRelatrioMensal;
	}

	public EmitirRelatorioDoacoesControle getControle() {
		return controle;
	}

	public void setControle(EmitirRelatorioDoacoesControle controle) {
		this.controle = controle;
	}

	public DateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public DateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(DateTime dataTermino) {
		this.dataTermino = dataTermino;
	}
	
	public EmitirRelatorioDoacoesView() {
		this.setControle(new EmitirRelatorioDoacoesControle(this));
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			EmitirRelatorioDoacoesView window = new EmitirRelatorioDoacoesView();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEmitirRelatrioMensal.open();
		shlEmitirRelatrioMensal.layout();
		Centralize.centralize(shlEmitirRelatrioMensal);
		
		Label lblSelecioneOPerodo = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblSelecioneOPerodo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneOPerodo.setBounds(212, 160, 289, 25);
		lblSelecioneOPerodo.setText("Informe o per\u00EDodo para o relat\u00F3rio");
		
		dataInicio = new DateTime(shlEmitirRelatrioMensal, SWT.BORDER);
		dataInicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		
		dataInicio.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		dataInicio.setBounds(204, 207, 116, 36);
		
		Label lblDataInicio = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblDataInicio.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblDataInicio.setBounds(102, 213, 94, 25);
		lblDataInicio.setText("Data Inicio:");
		
		Label lblDataTrmino = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblDataTrmino.setText("Data T\u00E9rmino:");
		lblDataTrmino.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblDataTrmino.setBounds(361, 213, 116, 25);
		
		dataTermino = new DateTime(shlEmitirRelatrioMensal, SWT.BORDER);
		dataTermino.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		dataTermino.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		dataTermino.setBounds(485, 207, 116, 36);
		
		Button button = new Button(shlEmitirRelatrioMensal, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				controle.getEvent(arg0);
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button.setBounds(138, 278, 214, 54);
		
		Button btnGerarRelatrio = new Button(shlEmitirRelatrioMensal, SWT.NONE);
		btnGerarRelatrio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(! controle.verificarData()) {
					mensagemErro();
				}else {
					controle.getEvent(arg0);
				}
			}
		});
		btnGerarRelatrio.setText("Gerar relat\u00F3rio");
		btnGerarRelatrio.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		btnGerarRelatrio.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnGerarRelatrio.setBounds(358, 278, 214, 54);
		
		Label lblEsteRelatrioLista = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblEsteRelatrioLista.setText("Este relat\u00F3rio lista todas as doa\u00E7\u00F5es recebidas no per\u00EDodo informado");
		lblEsteRelatrioLista.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblEsteRelatrioLista.setBounds(69, 97, 576, 25);
		while (!shlEmitirRelatrioMensal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEmitirRelatrioMensal = new Shell();
		shlEmitirRelatrioMensal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				identificarESC(arg0);
			}
		});
		shlEmitirRelatrioMensal.setSize(730, 397);
		shlEmitirRelatrioMensal.setText("Emitir relat\u00F3rio de doa\u00E7\u00F5es - ABRACE");
		shlEmitirRelatrioMensal.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		
		Label lblRelatrioMensalDe = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblRelatrioMensalDe.setText("Relat\u00F3rio de Doa\u00E7\u00F5es");
		lblRelatrioMensalDe.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblRelatrioMensalDe.setBounds(182, 10, 349, 50);
		
		Label label_1 = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/relatorio.png").getAbsolutePath()));
		label_1.setBounds(584, 17, 80, 105);
		
		Label lblOngAbrace = new Label(shlEmitirRelatrioMensal, SWT.NONE);
		lblOngAbrace.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblOngAbrace.setBounds(300, 60, 114, 25);
		lblOngAbrace.setText("ONG ABRACE");

	}
	
	public void mensagemErro(){
		 MessageBox messageBox = new MessageBox(shlEmitirRelatrioMensal,SWT.ICON_ERROR | SWT.OK); 
	     messageBox.setText("Erro nas datas informadas");
	     messageBox.setMessage("Não foi possível gerar o relatório, pois a data de início informada ocorre após a de término\nInsira uma data inicial correta!");
	     messageBox.open();
	}
	
	public void identificarESC(KeyEvent arg0) {
		if(arg0.keyCode == 27) {
			shlEmitirRelatrioMensal.dispose();
		}
	}
}
