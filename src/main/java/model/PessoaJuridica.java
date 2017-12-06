package model;

import java.time.LocalDate;

import exceptions.PessoaInvalidaException;
import exceptions.PessoaJuridicaInvalidaException;

public class PessoaJuridica extends Pessoa{
	private String cnpj;
	private String nomeFantasia;
	private boolean isPJ;
	
	public boolean isPJ() {
		return isPJ;
	}

	public void setPJ(boolean isPJ) {
		this.isPJ = isPJ;
	}

	public PessoaJuridica(int id, String  cnpj, String nomeFantasia) throws PessoaJuridicaInvalidaException, PessoaInvalidaException {
		setId(id);
		setCnpj(cnpj);
		setNomeFantasia(nomeFantasia);
		setPJ(true);
		
	}
	
	public PessoaJuridica(String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, String cnpj, String nomeFantasia) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(nome, endereco, telefone, telefone2, dataCadastro, email, ativo);
		setCnpj(cnpj);
		setNomeFantasia(nomeFantasia);
		setPJ(true);
	}
	 
	public PessoaJuridica(int id,String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo, boolean isDoador, String cnpj, String nomeFantasia) throws PessoaInvalidaException, PessoaJuridicaInvalidaException{
		super(id, nome, endereco, telefone, telefone2, dataCadastro, email, ativo, isDoador);
		setCnpj(cnpj);
		setNomeFantasia(nomeFantasia);
		setPJ(true);
	}
	 
	public PessoaJuridica() {
		super();
		setPJ(true);
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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) throws PessoaJuridicaInvalidaException {
		if(nomeFantasia == null || nomeFantasia == "") {
			throw new PessoaJuridicaInvalidaException("A fantasia informada é inválida");
		}
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", nomeFantasia=" + nomeFantasia
				+ ", toString()=" + super.toString() + "]";
	}
	
	public String getInformacaoDoador() {
		 return this.cnpj;
	 }
	
}
