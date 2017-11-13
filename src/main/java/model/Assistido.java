package model;

import java.time.LocalDate;

import exceptions.AssistidoInvalidoException;
import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;

public class Assistido extends PessoaFisica {
	private String apelido; 
    private String tipoDeCancer;
    private boolean situacao;

    public Assistido(int id, String nome, String endereco,  LocalDate dataCadastro, String telefone, String telefone2, String email, boolean ativo, boolean isDoador, String cpf, String rg, 
    		LocalDate dataNasc, String apelido, String tipoDeCancer, boolean situacao) throws PessoaInvalidaException, PessoaFisicaException, AssistidoInvalidoException {
    	super(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo, isDoador, cpf, rg, dataNasc);
        setApelido(apelido);
        setSituacao(situacao);
        setTipoDeCancer(tipoDeCancer);
    }
    
    public Assistido(String apelido, String tipoDeCancer, boolean situacao) throws PessoaInvalidaException, PessoaFisicaException, AssistidoInvalidoException {
    	setApelido(apelido);
        setSituacao(situacao);
        setTipoDeCancer(tipoDeCancer);
    }

    public Assistido() {
		// TODO Auto-generated constructor stub
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) throws AssistidoInvalidoException {
		if(apelido == null) {
    		throw new AssistidoInvalidoException("O apelido informado é inválido!");
    	}
		this.apelido = apelido;
	}

	public String getTipoDeCancer() {
        return tipoDeCancer;
    }

    public void setTipoDeCancer(String tipoDeCancer) throws AssistidoInvalidoException {
    	if(tipoDeCancer == null || tipoDeCancer == "") {
    		throw new AssistidoInvalidoException("O tipo de cancer informado é inválido!");
    	}
        this.tipoDeCancer = tipoDeCancer;
    }

    public boolean getSituacao() {
        return situacao;
    }

    public void setSituacao(boolean status) {
        this.situacao = status;
    }

	@Override
	public String toString() {
		return "Assistido [apelido=" + apelido + ", tipoDeCancer=" + tipoDeCancer + ", situacao=" + situacao
				+ ", getCpf()=" + getCpf() + ", getRg()=" + getRg() + ", getDataNasc()=" + getDataNasc() + ", getId()="
				+ getId() + ", getNome()=" + getNome() + ", getEndereco()=" + getEndereco() + ", getDataCadastro()="
				+ getDataCadastro() + ", getTelefone()=" + getTelefone() + ", getTelefone2()=" + getTelefone2()
				+ ", getEmail()=" + getEmail() + ", isAtivo()=" + isAtivo() + ", toString()=" + super.toString() + "]";
	}
    
    

}