package model;

import java.time.LocalDate;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;

public class PessoaJuridica extends Pessoa{
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	
	public PessoaJuridica(int id, String  cnpj, String razaoSocial, String nomeFantasia) throws PessoaJuridicaInvalidaException, PessoaInvalidaException {
		setId(id);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setNomeFantasia(nomeFantasia);
		
	}
	
	public PessoaJuridica(String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, String cnpj, String razaoSocial, String nomeFantasia) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(nome, endereco, telefone, telefone2, dataCadastro, email, ativo);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setNomeFantasia(nomeFantasia);
	}
	 
	public PessoaJuridica(int id,String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, String cnpj, String razaoSocial, String nomeFantasia) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setNomeFantasia(nomeFantasia);
	}
	 
	public PessoaJuridica() {
		super();
	}

	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) throws PessoaJuridicaInvalidaException {
		CNP validador = new CNP();
		if(cnpj == null || ! validador.isValidCNPJ(cnpj.replace(".", "").replace("/", ""))) {
			throw new PessoaJuridicaInvalidaException("O cnpj informado � inv�lido");
		}
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) throws PessoaJuridicaInvalidaException {
		if(razaoSocial == null) {
			throw new PessoaJuridicaInvalidaException("A Raz�o social informada � inv�lida");
		}
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
}
