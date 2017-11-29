package control;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;
import facade.DoadorFachada;
import model.PessoaFisica;
import view.CadastroDoadorPFisicaView;

public class CadastroDoadorPFControle {
	private CadastroDoadorPFisicaView viewDoador;
	private DoadorFachada fachadaDoador; 
	
	public void getEvent(SelectionEvent event) {
		if (event.getSource().toString().equals("Button {Cadastrar}")){
			try {
				PessoaFisica doador = new PessoaFisica();
				doador.setNome(viewDoador.getTfNome().getText());
				doador.setCpf(viewDoador.getTfCPF().getText());
				doador.setRg(viewDoador.getTfRG().getText());
				doador.setEndereco(viewDoador.getTfEndereco().getText());
				doador.setDataNasc(LocalDate.of(viewDoador.getTfDataNascimento().getYear(),viewDoador.getTfDataNascimento().getMonth() + 1, viewDoador.getTfDataNascimento().getDay()));
				doador.setTelefone(viewDoador.getTfTelefone1().getText());
				doador.setTelefone2(viewDoador.getTfTelefone2().getText());
				doador.setEmail(viewDoador.getTfEmail().getText());
				doador.setDoador(true);
				if(fachadaDoador.cadastrarDoadorFisico(doador)) {
					viewDoador.mensagemSucesso(doador);
					viewDoador.getShlCadastroDoador().dispose();
				}
			} catch (PessoaInvalidaException e) {
				viewDoador.mensagemErro(e);
			} catch (PessoaFisicaException e) {
				viewDoador.mensagemErro(e);
			} catch (SQLException e) {
				if(e.getMessage().contains("CPF já existente")) {
					viewDoador.mensagemErro(e);
				}
				else {
					viewDoador.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
				}
			}
		}
	}
	
	public void getFocus(FocusEvent arg0) {
		if(viewDoador.getTfCPF().getText() == "") {
			return;
		}else {
			try {
				StringBuilder sb = new StringBuilder(viewDoador.getTfCPF().getText().replace(".", "").replace("-", ""));
				sb.insert(3, ".");
				sb.insert(7, ".");
				sb.insert(11, "-");
				if(fachadaDoador.verificaCPF(sb.toString())) {
					List<PessoaFisica> lista = fachadaDoador.listarTabelaPessoasFisicas();
					for(PessoaFisica pessoa : lista) {
						if(pessoa.getCpf().equals(sb.toString()) && !(pessoa.isAtivo())) {
							if(viewDoador.reativarDoador(pessoa)) {
								fachadaDoador.ativaDoador(pessoa.getId());
								viewDoador.mensagemSucessoReativacao(fachadaDoador.obterDoadorFisico(pessoa.getId()));
								viewDoador.getShlCadastroDoador().dispose();
							}
							break;
						}
					}
				}
			} catch (SQLException e) {
				viewDoador.mensagemErro(new Exception("Erro na operação! Contate o suporte!"));
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
	
	public void filtrarTelefone1(KeyEvent evt) {
		String k = viewDoador.getTfTelefone1().getText();
		String j = new String();
		for(int i=0;i<k.length();i++) {
			char[] caractere= {k.charAt(i)};
			if("0123456789".contains(new String(caractere)))
				j+=k.charAt(i);
		}
        String temp = new String();
        for (int i = 0; i < j.length(); i++) {
            if(i==0){
                temp+="("+j.charAt(i);
            }else if(i==2){
                temp+=")"+j.charAt(i);
            }else if(i==6){
                temp+="-"+j.charAt(i);
            }else if(i == 12 || i<11){
                temp+=j.charAt(i);
            }
        }
        viewDoador.setTfTelefone1(temp);
        viewDoador.getTfTelefone1().setSelection(viewDoador.getTfTelefone1().getText().length());
    }
	
	public void filtrarTelefone2(KeyEvent evt) {
		String k = viewDoador.getTfTelefone2().getText();
		String j = new String();
		for(int i=0;i<k.length();i++) {
			char[] caractere= {k.charAt(i)};
			if("0123456789".contains(new String(caractere)))
				j+=k.charAt(i);
		}
        String temp = new String();
        for (int i = 0; i < j.length(); i++) {
            if(i==0){
                temp+="("+j.charAt(i);
            }else if(i==2){
                temp+=")"+j.charAt(i);
            }else if(i==6){
                temp+="-"+j.charAt(i);
            }else if(i == 12 || i<11){
                temp+=j.charAt(i);
            }
        }
        viewDoador.setTfTelefone2(temp);
        viewDoador.getTfTelefone2().setSelection(viewDoador.getTfTelefone2().getText().length());
    }
	
	public CadastroDoadorPFControle(CadastroDoadorPFisicaView cadastroDoadorView) {
		setCadastroDoadorView(cadastroDoadorView);
		setFachadaDoador(new DoadorFachada());
	}

	private void setCadastroDoadorView(CadastroDoadorPFisicaView cadastroDoadorView) {
		if(cadastroDoadorView != null) {
			this.viewDoador = cadastroDoadorView;
		}
	}

	private void setFachadaDoador(DoadorFachada fachadaDoador) {
		if(fachadaDoador != null) {
			this.fachadaDoador = fachadaDoador; 
		}
	}
}