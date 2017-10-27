package view;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class SelecionarTipoDoadorView {

	protected Shell shlSelecionarTipoDoador = new Shell();
	public static void main() {
		try {
			SelecionarTipoDoadorView window = new SelecionarTipoDoadorView();
			
			window.open();
			window.shlSelecionarTipoDoador.open();
			window.shlSelecionarTipoDoador.layout();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		this.createContents();
		Centralize.centralize(shlSelecionarTipoDoador);
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlSelecionarTipoDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlSelecionarTipoDoador.setSize(657, 491);
		shlSelecionarTipoDoador.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Button pessoaFisicabnt = new Button(shlSelecionarTipoDoador, SWT.NONE);
		pessoaFisicabnt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroDoadorView.main();
			}
		});
		pessoaFisicabnt.setBounds(92, 74, 224, 68);
		pessoaFisicabnt.setText("Cadastrar Doador Pessoa Física");
		
		Button pessoaJuridicabnt = new Button(shlSelecionarTipoDoador, SWT.NONE);
		pessoaJuridicabnt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				CadastroDoadorJuridicoView.main();
			}
		});
		pessoaJuridicabnt.setText("Cadastrar Doador Pessoa Jurídica");
		pessoaJuridicabnt.setBounds(334, 74, 224, 68);
	}

}
