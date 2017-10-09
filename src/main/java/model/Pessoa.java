package model;

import java.time.LocalDate;

import exceptions.PessoaInvalidaException;

public abstract class Pessoa {
	private int id;
    private String nome;
    private String endereco;
	private String telefone;
    private String telefone2;
    private LocalDate dataCadastro;
    private String email;
    private boolean ativo;

    public Pessoa() {
    	
    }
    
    public Pessoa(String nome, String endereco, String telefone, String telefone2, LocalDate dataCadastro,String email, boolean ativo) throws PessoaInvalidaException{
    	setNome(nome);
    	setTelefone(telefone);
    	setTelefone2(telefone2);
    	setEndereco(endereco);
    	setDataCadastro(dataCadastro);
    	setEmail(email);
     }
    
	public Pessoa(int id, String nome, String endereco,  LocalDate dataCadastro, String telefone, String telefone2, String email, boolean ativo) throws PessoaInvalidaException{
    	this(nome, endereco, telefone, telefone2, dataCadastro, email, ativo);
		setId(id);
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) throws PessoaInvalidaException {
		if(id < 0) {
			throw new PessoaInvalidaException("O id informado é inválido");
		}
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws PessoaInvalidaException{
		if(nome == null) {
			throw new PessoaInvalidaException("O nome informado é inválido");
		}
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) throws PessoaInvalidaException{
		if(endereco == null) {
			throw new PessoaInvalidaException("O endereco informado é inválido");
		}
		this.endereco = endereco;
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) throws PessoaInvalidaException{
		if(dataCadastro == null) {
			throw new PessoaInvalidaException("A data informada é inválida");
		}
		this.dataCadastro = dataCadastro;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) throws PessoaInvalidaException{
		if(telefone == null) {
			throw new PessoaInvalidaException("O telefone informado é inválido");
		}
		this.telefone = telefone;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) throws PessoaInvalidaException{
		if(telefone2 == null) {
			throw new PessoaInvalidaException("O telefone informado é inválido");
		}
		this.telefone2 = telefone2;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws PessoaInvalidaException{
		if(email == null || !email.contains("@")) {
			throw new PessoaInvalidaException("O e-mail informado é inválido");
		}
		this.email = email;
	}
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
