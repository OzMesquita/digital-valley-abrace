package view;

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
	public static void main(String[] args) {
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
		shlOngRussasTransformando.setImage(SWTResourceManager.getImage("C:\\Users\\Thiago Hellen\\git\\digital-valley-abrace\\src\\main\\java\\view\\img\\ABRACE.png"));
		shlOngRussasTransformando.setSize(570, 491);
		shlOngRussasTransformando.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Button btnCadastrarAssistido = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarAssistido.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroAssistidoView.main();
			}
		});
		btnCadastrarAssistido.setBounds(165, 72, 224, 68);
		btnCadastrarAssistido.setText("Cadastrar Assistido");
		
		Button btnCadastrarDoador = new Button(shlOngRussasTransformando, SWT.NONE);
		btnCadastrarDoador.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroDoadorView.main();
			}
		});
		btnCadastrarDoador.setText("Cadastrar Doador");
		btnCadastrarDoador.setBounds(165, 154, 224, 68);

	}

}
