package view;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class SelecionarTipoDoadorView {
	protected Shell shlSelecionarTipoDoador = new Shell(SWT.SHELL_TRIM & (~SWT.RESIZE) & (~SWT.MAX));
	
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

	public void open() {
		this.createContents();
		Centralize.centralize(shlSelecionarTipoDoador);
	}

	protected void createContents() {
		shlSelecionarTipoDoador.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		shlSelecionarTipoDoador.setSize(657, 503);
		shlSelecionarTipoDoador.setText("ONG Russas Transformando Vidas - ABRACE");
		
		Button pessoaFisicabnt = new Button(shlSelecionarTipoDoador, SWT.FLAT | SWT.CENTER);
		pessoaFisicabnt.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		pessoaFisicabnt.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pessoa-fisica-medium.png").getAbsolutePath()));
		pessoaFisicabnt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlSelecionarTipoDoador.dispose();
				CadastroDoadorPFisicaView.main();
			}
		});
		pessoaFisicabnt.setBounds(120, 162, 411, 89);
		pessoaFisicabnt.setText("Cadastrar Doador Pessoa Física");
		
		Button pessoaJuridicabnt = new Button(shlSelecionarTipoDoador, SWT.NONE);
		pessoaJuridicabnt.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/pessoa-juridica-medium.png").getAbsolutePath()));
		pessoaJuridicabnt.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		pessoaJuridicabnt.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlSelecionarTipoDoador.dispose();
				CadastroDoadorPJuridicoView.main();
			}
		});
		pessoaJuridicabnt.setText("Cadastrar Doador Pessoa Jurídica");
		pessoaJuridicabnt.setBounds(120, 265, 411, 89);
		
		Label label = new Label(shlSelecionarTipoDoador, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/ABRACE.png").getAbsolutePath()));
		label.setBounds(532, 37, 71, 68);
		
		Label lblSelecioneOTipo = new Label(shlSelecionarTipoDoador, SWT.NONE);
		lblSelecioneOTipo.setAlignment(SWT.CENTER);
		lblSelecioneOTipo.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL));
		lblSelecioneOTipo.setBounds(180, 64, 291, 64);
		lblSelecioneOTipo.setText("Selecione o tipo do doador\r\nque deseja cadastrar");
		
		Button button = new Button(shlSelecionarTipoDoador, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shlSelecionarTipoDoador.dispose();
			}
		});
		button.setText("Cancelar");
		button.setImage(SWTResourceManager.getImage(new File("src/main/java/view/img/cancelar.png").getAbsolutePath()));
		button.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		button.setBounds(221, 398, 208, 54);
	}
}