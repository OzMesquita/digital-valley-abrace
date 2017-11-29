package control;

import java.time.LocalDate;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.EditarDoadorPFView;
import view.GerenciarDoadoresView;

public class EditarDoadorPFControle {
	private EditarDoadorPFView viewDoador;
	private DoadorFachada fachadaDoador;
	
	public EditarDoadorPFControle(EditarDoadorPFView editarDoadorFisicoView) {
		if(editarDoadorFisicoView != null) {
			this.viewDoador = editarDoadorFisicoView;
			this.fachadaDoador = new DoadorFachada();
		}
	}
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Salvar alterações}")){
			PessoaFisica doador = new PessoaFisica();
			try {
				doador.setId(viewDoador.getDoador().getId());
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCpf(viewDoador.getTfCPF().getText());
				doador.setRg(viewDoador.getTfRG().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(viewDoador.getTfDataNascimento().getYear(), viewDoador.getTfDataNascimento().getMonth() + 1, viewDoador.getTfDataNascimento().getDay()));
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setDataCadastro(LocalDate.now());
				if (fachadaDoador.editarDoadorFisico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlEditarDoadorPessoa().dispose();
					GerenciarDoadoresView.main();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewDoador.mensagemErro(e);;
			}
		}
	}
	
	public void filtrarCpf(KeyEvent evt) {
		String k = viewDoador.getTfCPF().getText();
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
            }else{
                temp+=j.charAt(i);
            }
        }
        viewDoador.setTfCPF(temp);
        viewDoador.getTfCPF().setSelection(viewDoador.getTfCPF().getText().length());
    }
}