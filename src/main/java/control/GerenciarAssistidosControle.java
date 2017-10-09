package control;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import facade.AssistidoFachada;
import model.Assistido;
import view.GerenciarAssistidosView;

public class GerenciarAssistidosControle {
	private GerenciarAssistidosView view; 
	private AssistidoFachada fachada;
	
	public GerenciarAssistidosView getView() {
		return view;
	}


	public void setView(GerenciarAssistidosView view) {
		this.view = view;
	}


	public AssistidoFachada getFachada() {
		return fachada;
	}


	public void setFachada(AssistidoFachada fachada) {
		this.fachada = fachada;
	}
	
	public GerenciarAssistidosControle (GerenciarAssistidosView view) {
		setView(view);
		setFachada(new AssistidoFachada());
	}

	
	
	public void listarTodosAssistidos() {
		ArrayList<Assistido> assistidos = fachada.listarTodosAssistidos();
		
		for(int i = 0; i < assistidos.size(); i++) {
			TableItem item = new TableItem(view.getTable(), SWT.NONE);
			item.setText(0, Integer.toString(assistidos.get(i).getId()));
			item.setText(1, assistidos.get(i).getNome());
			item.setText(2, assistidos.get(i).getDataNasc().toString());
			item.setText(3, assistidos.get(i).getCpf());
		}
		
	}
}
