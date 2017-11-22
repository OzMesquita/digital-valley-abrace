package model;

import java.time.LocalDate;

import exceptions.PessoaFisicaException;
import exceptions.PessoaInvalidaException;

public class PessoaFisica extends Pessoa {
	private String cpf;
	private String rg;
	private LocalDate dataNasc;

	public PessoaFisica() {

	}

	public PessoaFisica(int id, String cpf, String rg, LocalDate dataNasc) throws PessoaFisicaException, PessoaInvalidaException {
		setId(id);
		setCpf(cpf);
		setRg(rg);
		setDataNasc(dataNasc);
	}

	public PessoaFisica(int id, String nome, String endereco, LocalDate dataCadastro, String telefone, String telefone2,
			String email, boolean ativo, boolean isDoador, String cpf, String rg, LocalDate dataNasc) throws PessoaInvalidaException, PessoaFisicaException {
		super(id, nome, endereco, telefone, telefone2, dataCadastro, email, ativo, isDoador);
		setCpf(cpf);
		setRg(rg);
		setDataNasc(dataNasc);
	}
	public PessoaFisica(String nome, String endereco, LocalDate dataCadastro, String telefone, String telefone2,
			String email, boolean ativo, String cpf, String rg, LocalDate dataNasc) throws PessoaInvalidaException, PessoaFisicaException {
		super(nome, endereco, telefone2, telefone, dataCadastro, email, ativo);
		setCpf(cpf);
		setRg(rg);
		setDataNasc(dataNasc);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws PessoaFisicaException {
		CNP validador = new CNP();
		
		StringBuilder sb = new StringBuilder(cpf.replace(".", "").replace("-", ""));
		sb.insert(3, ".");
		sb.insert(7, ".");
		sb.insert(11, "-");
		if ((cpf == null) || !(validador.isValidCPF(cpf.replace(".", "").replace("-", "")))) {
			throw new PessoaFisicaException("O CPF informado é inválido!");
		}
		this.cpf = sb.toString();
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) throws PessoaFisicaException {
		if (rg == null || rg == "") {
			throw new PessoaFisicaException("O RG informado é inválido!");
		}
		this.rg = rg;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) throws PessoaFisicaException {
		if (dataNasc == null || dataNasc.isAfter(LocalDate.now())) {
			throw new PessoaFisicaException("A data de nascimento informada é inválida!");
		}
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", rg=" + rg + ", dataNasc=" + dataNasc + ", toString()=" + super.toString() + "]";
	}
	 public String getInformacaoDoador() {
		 return this.cpf;
	 }
}
