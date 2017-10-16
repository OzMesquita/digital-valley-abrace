package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class AplicacaoView {

	protected Shell shlOngRussasTransformando;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main() {
		try {
			AplicacaoView window = new AplicacaoView();
			
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
		Centralize.centralize(shlOngRussasTransformando);
		
		Button btnNewButton = new Button(shlOngRussasTransformando, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				EditarAssistidoView.main();
			}
		});
		btnNewButton.setBounds(92, 222, 224, 68);
		btnNewButton.setText("Editar Assistido");
		
		Button btnListarAssistidos = new Button(shlOngRussasTransformando, SWT.NONE);
		btnListarAssistidos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GerenciarAssistidosView.main();
			}
		});
		btnListarAssistidos.setBounds(92, 148, 224, 68);
		btnListarAssistidos.setText("Gerenciar Assistidos");
		shlOngRussasTransformando.open();
		shlOngRussasTransformando.layout();
		while (!shlOngRussasTransformando.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlOngRussasTransformando = new Shell();
		shlOngRussasTransformando.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlOngRussasTransformando.setSize(657, 491);
		shlOngRussasTransformando.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Button btnCadastrarAssistido = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarAssistido.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroAssistidoView.main();
			}
		});
		btnCadastrarAssistido.setBounds(92, 74, 224, 68);
		btnCadastrarAssistido.setText("Cadastrar Assistido");
		
		Button btnCadastrarDoador = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarDoador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroDoadorView.main();
			}
		});
		btnCadastrarDoador.setText("Cadastrar Doador");
		btnCadastrarDoador.setBounds(334, 74, 224, 68);

	}
}
