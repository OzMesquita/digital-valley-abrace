package model;

import java.time.LocalDate;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;

public class PessoaJuridica extends Pessoa{
	private String cnpj;
	private String razaoSocial;
	
	public PessoaJuridica(int id, String  cnpj, String razaoSocial) throws PessoaJuridicaInvalidaException, PessoaInvalidaException {
		setId(id);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
	}
	
	public PessoaJuridica(String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, String cnpj, String razaoSocial) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(nome, endereco, telefone, telefone2, dataCadastro, email, ativo);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
	}
	 
	public PessoaJuridica(int id,String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, String cnpj, String razaoSocial) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(id, nome, endereco, dataCadastro, telefone, telefone2, email, ativo);
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
	}
	 
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) throws PessoaJuridicaInvalidaException {
		CNP validador = new CNP();
		if(cnpj == null || ! validador.isValidCNPJ(cnpj.replace(".", "").replace("/", ""))) {
			throw new PessoaJuridicaInvalidaException("O cnpj informado é inválido");
		}
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) throws PessoaJuridicaInvalidaException {
		if(razaoSocial == null) {
			throw new PessoaJuridicaInvalidaException("A Razão social informada é inválida");
		}
		this.razaoSocial = razaoSocial;
	}
}
