package control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import model.CNP;
import view.interfaces.ViewAssistido;
import view.interfaces.ViewPessoa;

public class Controle {
	
	//======================================================//
	// 						Email							//
	//======================================================//
	public boolean validarEmail(ViewPessoa view) {
		if(!view.getTfEmail().getText().matches("^([0-9a-zA-Z]+([_.-]?[0-9a-zA-Z]+)*@[0-9a-zA-Z]+([.]?[0-9a-zA-Z]+)*)")&&!view.getTfEmail().getText().equals("")) {
			//view.getTfEmail().setForeground(view.getTfEmail().getDisplay().getSystemColor(SWT.COLOR_RED));
			return false;
		}
		else {
			//view.getTfEmail().setForeground(view.getTfEmail().getDisplay().getSystemColor(SWT.COLOR_BLACK));
			return true;
		}
	}
	public void filtrarEmail(ViewPessoa view) {
		String k = view.getTfEmail().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.@_-".contains(new String(caractere)))
				j += k.charAt(i);
		}
		j=j.toLowerCase();
		view.getTfEmail().setText(j);
		view.getTfEmail().setSelection(view.getTfEmail().getText().length());
	}
	//------------------------------------------------------//
	
	
	//======================================================//
	// 						CPF								//
	//======================================================//
	public void filtrarCpf(KeyEvent evt,ViewAssistido view) {
		String k = view.getTfCPF().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789".contains(new String(caractere)))
				j += k.charAt(i);
		}
		String temp = new String();
		for (int i = 0; i < j.length(); i++) {
			if (i == 3 || i == 6) {
				temp += "." + j.charAt(i);
			} else if (i == 9) {
				temp += "-" + j.charAt(i);
			} else {
				temp += j.charAt(i);
			}
		}
		view.getTfCPF().setText(temp);
		view.getTfCPF().setSelection(view.getTfCPF().getText().length());
	}
//	public boolean validarCPF(ViewAssistido viewAssistido) {
//		if(!new CNP().isValidCPF(viewAssistido.getTfCPF().getText().replace(".", "").replace("-", ""))&&!viewAssistido.getTfCPF().getText().equals("")) {
//			viewAssistido.getTfCPF().setForeground(viewAssistido.getTfCPF().getDisplay().getSystemColor(SWT.COLOR_RED));
//			viewAssistido.getTfCPF().setBackground(viewAssistido.getTfCPF().getDisplay().getSystemColor(SWT.COLOR_RED));
//			return false;
//		}
//		else {
//			viewAssistido.getTfCPF().setForeground(viewAssistido.getTfCPF().getDisplay().getSystemColor(SWT.COLOR_BLACK));
//			viewAssistido.getTfCPF().setBackground(viewAssistido.getTfCPF().getDisplay().getSystemColor(SWT.COLOR_WHITE));
//			return true;
//		}
//	}
	//------------------------------------------------------//

	
	//======================================================//
	// 						RG								//
	//======================================================//
	public void filtrarRG(KeyEvent evt,ViewAssistido view) {
		String k = view.getTfRG().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789".contains(new String(caractere)))
				j += k.charAt(i);
		}
		view.getTfRG().setText(j);
		view.getTfRG().setSelection(view.getTfRG().getText().length());
	}
	//------------------------------------------------------//
	
	
	//======================================================//
	// 						Nome							//
	//======================================================//
	public void filtrarNome(KeyEvent evt,ViewPessoa view) {
		String k = view.getTfNome().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ������������������������������ ".contains(new String(caractere)))
				j += k.charAt(i);
		}
		view.getTfNome().setText(j);
		view.getTfNome().setSelection(view.getTfNome().getText().length());
	}
	//------------------------------------------------------//

	
	//======================================================//
	// 						Apelido							//
	//======================================================//
	public void filtrarApelido(KeyEvent evt,ViewAssistido view) {
		String k = view.getTfApelido().getText();
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ������������������������������ ".contains(new String(caractere)))
				j += k.charAt(i);
		}
		view.getTfApelido().setText(j);
		view.getTfApelido().setSelection(view.getTfApelido().getText().length());
	}
	//------------------------------------------------------//

	
	//======================================================//
	// 				telefone1/telefone2						//
	//======================================================//
	public void filtrarTelefone1(KeyEvent evt,ViewPessoa view) {
		String temp = filtrarTelefone(view.getTfTelefone1().getText());
		view.getTfTelefone1().setText(temp);
		view.getTfTelefone1().setSelection(view.getTfTelefone1().getText().length());
		
	}
	public void filtrarTelefone2(KeyEvent evt,ViewPessoa view) {
		String temp = filtrarTelefone(view.getTfTelefone2().getText());
		view.getTfTelefone2().setText(temp);
		view.getTfTelefone2().setSelection(view.getTfTelefone2().getText().length());
	}
	private String filtrarTelefone(String k) {
		String j = new String();
		for (int i = 0; i < k.length(); i++) {
			char[] caractere = { k.charAt(i) };
			if ("0123456789".contains(new String(caractere)))
				j += k.charAt(i);
		}
		String temp = new String();
		for (int i = 0; i < j.length(); i++) {
			if (i == 0) {
				temp += "(" + j.charAt(i);
			} else if (i == 2) {
				temp += ")" + j.charAt(i);
			} else if (i == 6) {
				temp += "-" + j.charAt(i);
			} else if (i == 12 || i < 11) {
				temp += j.charAt(i);
			}
		}
		return temp;
	}
	public boolean validarTelefone1(KeyEvent evt,ViewPessoa view) {
		if(!view.getTfTelefone1().getText().equals("")&&view.getTfTelefone1().getText().length()<14) return false;
		else return true;
		
	}
	public boolean validarTelefone2(KeyEvent evt,ViewPessoa view) {
		if(!view.getTfTelefone2().getText().equals("")&&view.getTfTelefone2().getText().length()<14) return false;
		else return true;
		
	}
	

}
