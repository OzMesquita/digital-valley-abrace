package control;

import java.time.LocalDate;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.AssistidoFachada;
import model.Assistido;
import view.EditarAssistidoView;

public class EditarAssistidoControle {
	private EditarAssistidoView viewAssistido;
	private AssistidoFachada fachadaAssistido;
	
	public EditarAssistidoControle (EditarAssistidoView viewAssistido){
		setViewAssistido(viewAssistido);
		setFachadaAssistido(new AssistidoFachada());
	}
	
	public EditarAssistidoView getViewAssistido() {
		return viewAssistido;
	}

	public void setViewAssistido(EditarAssistidoView viewAssistido) {
		this.viewAssistido = viewAssistido;
	}

	public AssistidoFachada getFachadaAssistido() {
		return fachadaAssistido;
	}

	public void setFachadaAssistido(AssistidoFachada fachadaAssistido) {
		this.fachadaAssistido = fachadaAssistido;
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			Assistido assistido = new Assistido();
			try {
				assistido.setId(viewAssistido.getAssistido().getId());
				assistido.setNome(viewAssistido.getTfNome().getText());
				assistido.setApelido(viewAssistido.getTfApelido().getText());
				assistido.setCpf(viewAssistido.getTfCPF().getText());
				assistido.setRg(viewAssistido.getTfRG().getText());
				assistido.setEndereco(viewAssistido.getTfEndereco().getText());
				assistido.setDataNasc(LocalDate.of(viewAssistido.getTfDataNascimento().getYear(), viewAssistido.getTfDataNascimento().getMonth()+1, viewAssistido.getTfDataNascimento().getDay()));
				assistido.setTelefone(viewAssistido.getTfTelefone1().getText());
				assistido.setTelefone2(viewAssistido.getTfTelefone2().getText());
				assistido.setEmail(viewAssistido.getTfEmail().getText());
				assistido.setTipoDeCancer(viewAssistido.getTfTipoCancer().getText());
				assistido.setSituacao(viewAssistido.getTfSituacao());
				if (fachadaAssistido.editarAssistido(assistido)) {
					viewAssistido.mensagemSucesso(assistido);
					viewAssistido.getShlEditarAssistido().dispose();
				}
			} catch (PessoaInvalidaException e) {
				viewAssistido.mensagemErro(e);
			} catch (AssistidoInvalidoException e) {
				viewAssistido.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewAssistido.mensagemErro(e);
			}
		}
	}
	
	public void filtrarCpf(KeyEvent evt) {
		String k = viewAssistido.getTfCPF().getText();
		String j = new String();
		for(int i=0;i<k.length();i++) {
			char[] caractere= {k.charAt(i)};
			if("0123456789".contains(new String(caractere)))
				j+=k.charAt(i);
		}
        String temp = new String();
        for (int i = 0; i < j.length(); i++) {
            if(i==3||i==6){
                temp+="."+j.charAt(i);
            }else if(i==9){
                temp+="-"+j.charAt(i);
            }else if(i<11){
                temp+=j.charAt(i);
            }
        }
        viewAssistido.setTfCPF(temp);
        viewAssistido.getTfCPF().setSelection(viewAssistido.getTfCPF().getText().length());
    }
	
	public void filtrarTelefone1(KeyEvent evt) {
		String k = viewAssistido.getTfTelefone1().getText();
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
		viewAssistido.setTfTelefone1(temp);
		viewAssistido.getTfTelefone1().setSelection(viewAssistido.getTfTelefone1().getText().length());
	}

	public void filtrarTelefone2(KeyEvent evt) {
		String k = viewAssistido.getTfTelefone2().getText();
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
		viewAssistido.setTfTelefone2(temp);
		viewAssistido.getTfTelefone2().setSelection(viewAssistido.getTfTelefone2().getText().length());
	}
}