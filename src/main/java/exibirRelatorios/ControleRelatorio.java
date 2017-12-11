package exibirRelatorios;

import org.eclipse.swt.events.KeyEvent;

public class ControleRelatorio {

	
	public void filtrarAno(KeyEvent evt, ViewRelatorio view) {
		String k = view.getTfAno().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789".contains(new String(caractere)))
				j += k.charAt(i);
		}
		String temp = new String();
		for (int i = 0; i < j.length(); i++) {
			if (i < 4) {
				temp += j.charAt(i);
			}
		}
		view.getTfAno().setText(temp);
		view.getTfAno().setSelection(view.getTfAno().getText().length());
	}
}
