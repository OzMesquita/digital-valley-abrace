package exibirRelatorios;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.itextpdf.text.DocumentException;

import DAO.AssistidoDAO;
import DAO.ConnectionFactory;
import relatorio.RelatorioAssistidoFacade;
import view.Centralize;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class EmitirRelatorioAssistidosView {

	protected Shell shlEmitirRelatrioDe;
	private Combo combo;

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			EmitirRelatorioAssistidosView window = new EmitirRelatorioAssistidosView();
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
		shlEmitirRelatrioDe.open();
		shlEmitirRelatrioDe.layout();
		Centralize.centralize(shlEmitirRelatrioDe);
		while (!shlEmitirRelatrioDe.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEmitirRelatrioDe = new Shell();
		shlEmitirRelatrioDe.setSize(730, 407);
		shlEmitirRelatrioDe.setText("Emitir relat\u00F3rio de assistidos - ABRACE");
		shlEmitirRelatrioDe.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		
		Label lblRelatrioDeAssistidos = new Label(shlEmitirRelatrioDe, SWT.NONE);
		lblRelatrioDeAssistidos.setText("Relat\u00F3rio de Assistidos");
		lblRelatrioDeAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 28, SWT.NORMAL));
		lblRelatrioDeAssistidos.setBounds(171, 24, 371, 50);
		
		Label label_1 = new Label(shlEmitirRelatrioDe, SWT.NONE);
		label_1.setText("ONG ABRACE");
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		label_1.setBounds(292, 74, 129, 30);
		
		final Combo combo = new Combo(shlEmitirRelatrioDe, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		combo.setItems(new String[] {"Todos", "Vivos", "Mortos"});
		combo.setBounds(455, 209, 148, 31);
		combo.select(0);
		
		Label lblEsteRelatrioLista = new Label(shlEmitirRelatrioDe, SWT.NONE);
		lblEsteRelatrioLista.setText("Este relat\u00F3rio lista os assistidos da ONG\r\nVoc\u00EA deve selecionar quais assistidos quer listar");
		lblEsteRelatrioLista.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblEsteRelatrioLista.setAlignment(SWT.CENTER);
		lblEsteRelatrioLista.setBounds(156, 117, 402, 50);
		
		Button button = new Button(shlEmitirRelatrioDe, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlEmitirRelatrioDe.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button.setBounds(144, 285, 214, 54);
		
		Button button_1 = new Button(shlEmitirRelatrioDe, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(combo.getSelectionIndex() == 0) {
					gerarRelatorioAssistido(true, true);
				} else if(combo.getSelectionIndex() == 1) {
					gerarRelatorioAssistido(true, false);
				} else if(combo.getSelectionIndex() == 2) {
					gerarRelatorioAssistido(false, true);
				}
			}
		});
		button_1.setText("Gerar relat\u00F3rio");
		button_1.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ok.png").getAbsolutePath()));
		button_1.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button_1.setBounds(364, 285, 214, 54);
		
		Label lblSelecioneQuaisAssistidos = new Label(shlEmitirRelatrioDe, SWT.NONE);
		lblSelecioneQuaisAssistidos.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblSelecioneQuaisAssistidos.setBounds(124, 213, 325, 25);
		lblSelecioneQuaisAssistidos.setText("Selecione quais assistidos deseja listar:");

	}
	
	public static void gerarRelatorioAssistido(boolean vivo, boolean morto){
		try {
			RelatorioAssistidoFacade r = new RelatorioAssistidoFacade();
			r.relatorioDeAssistido(new AssistidoDAO(new ConnectionFactory().getConnection()).listaAssistido(),vivo,morto);
			r.abrirPDF();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
