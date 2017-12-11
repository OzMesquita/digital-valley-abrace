package control;


import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;
import facade.DoadorFachada;
import model.PessoaJuridica;
import view.EditarDoadorPJView;
import view.GerenciarDoadoresView;

public class EditarDoadorPJControle extends Controle{
	private EditarDoadorPJView viewDoador;
	private DoadorFachada fachadaDoador;
	
	public EditarDoadorPJControle (EditarDoadorPJView viewDoador){
		setViewDoador(viewDoador);
		setFachadaDoador(new DoadorFachada());
	}
	
	public EditarDoadorPJView getViewDoador() {
		return viewDoador;
	}

	public void setViewDoador(EditarDoadorPJView viewDoador) {
		this.viewDoador = viewDoador;
	}

	public DoadorFachada getFachadaDoador() {
		return fachadaDoador;
	}

	public void setFachadaDoador(DoadorFachada fachadaDoador) {
		this.fachadaDoador = fachadaDoador;
	}

	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			PessoaJuridica doador = new PessoaJuridica();
			try {
				doador.setId(viewDoador.getDoador().getId());
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCnpj(viewDoador.getTfCNPJ().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setNomeFantasia(viewDoador.gettfFantasia().getText());
				if (fachadaDoador.editarDoadorJuridico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlCadastroDoador().dispose();
					GerenciarDoadoresView.main();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaJuridicaInvalidaException e) {
				viewDoador.mensagemErro(e);
			}
		}
	}
	
	public void filtrarCnpj(KeyEvent evt) {
		String k = viewDoador.getTfCNPJ().getText();
		String j = new String();
		for(int i=0;i<k.length();i++) {
			char[] caractere= {k.charAt(i)};
			if("0123456789".contains(new String(caractere)))
				j+=k.charAt(i);
		}
        String temp = new String();
        for (int i = 0; i < j.length(); i++) {
            if(i==2||i==5){
                temp+="."+j.charAt(i);
            }else if(i==8){
                temp+="/"+j.charAt(i);
            }else if(i==12){
                temp+="-"+j.charAt(i);
            }else if(i<14){
                temp+=j.charAt(i);
            }
        }
        viewDoador.setTfCNPJ(temp);
        viewDoador.getTfCNPJ().setSelection(viewDoador.getTfCNPJ().getText().length());
    }
	
	public void filtrarTelefone1(KeyEvent evt) {
		String k = viewDoador.getTfTelefone1().getText();
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
		viewDoador.setTfTelefone1(temp);
		viewDoador.getTfTelefone1().setSelection(viewDoador.getTfTelefone1().getText().length());
	}

	public void filtrarTelefone2(KeyEvent evt) {
		String k = viewDoador.getTfTelefone2().getText();
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
		viewDoador.setTfTelefone2(temp);
		viewDoador.getTfTelefone2().setSelection(viewDoador.getTfTelefone2().getText().length());
	}
}