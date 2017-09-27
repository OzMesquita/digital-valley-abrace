package Model;

import java.time.LocalDate;

import Exceptions.PessoaInvalidaException;

public abstract class Pessoa extends Doador{
	int id;
    private String nome;
    private String cpf;
    private String rg;
    private String endereco;
    private String telefone;
    private String telefone2;
    private LocalDate dataCad;
    private String email;
    private boolean ativo;
    
    public Pessoa() {
    	
    }
    
    public Pessoa(String nome, String cpf, String rg, String endere�o, String telefone, 
    		String telefone2, LocalDate dataCad, String email, boolean ativo) throws PessoaInvalidaException{
    	setNome(nome);
    	setCpf(cpf);
    	setRg(rg);
    	setEndereco(endereco);
    	setTelefone(telefone);
    	setTelefone2(telefone2);
    	setDataCad(dataCad);
    	setEmail(email);
    	setAtivo(ativo);
		
    }
    
    public Pessoa(int id, String nome, String cpf, String rg, String endere�o, String telefone, 
    		String telefone2, LocalDate dataCad, String email, boolean ativo) throws PessoaInvalidaException{
    	setId(id);
    	setNome(nome);
    	setCpf(cpf);
    	setRg(rg);
    	setEndereco(endereco);
    	setTelefone(telefone);
    	setTelefone2(telefone2);
    	setDataCad(dataCad);
    	setEmail(email);
    	setAtivo(ativo);
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) throws PessoaInvalidaException {
		if(id < 0) {
			throw new PessoaInvalidaException("O id informado � inv�lido");
		}
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) throws PessoaInvalidaException{
		if(nome == null) {
			throw new PessoaInvalidaException("O nome informado � inv�lido");
		}
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) throws PessoaInvalidaException{
		if(cpf == null) {
			throw new PessoaInvalidaException("O cpf informado � inv�lido");
		}//Aqui deveria ter um teste para ver se o cpf � valido!!!!
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) throws PessoaInvalidaException{
		if(endereco == null) {
			throw new PessoaInvalidaException("O endereco informado � inv�lido");
		}
		this.endereco = endereco;
	}
	public LocalDate getDataCad() {
		return dataCad;
	}
	public void setDataCad(LocalDate dataCad) throws PessoaInvalidaException{
		if(dataCad == null || LocalDate.now().isBefore(dataCad)) {
			throw new PessoaInvalidaException("A data informada � inv�lida");
		}
		this.dataCad = dataCad;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) throws PessoaInvalidaException{
		if(telefone == null) {
			throw new PessoaInvalidaException("O telefone informado � inv�lido");
		}
		this.telefone = telefone;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) throws PessoaInvalidaException{
		if(telefone2 == null) {
			throw new PessoaInvalidaException("O telefone informado � inv�lido");
		}
		this.telefone2 = telefone2;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) throws PessoaInvalidaException{
		if(rg == null) {
			throw new PessoaInvalidaException("O RG informado � inv�lido");
		}
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws PessoaInvalidaException{
		if(email == null || !email.contains("@")) {
			throw new PessoaInvalidaException("O e-mail informado � inv�lido");
		}
		this.email = email;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) throws PessoaInvalidaException{
		if (ativo == null) {
			throw new PessoaInvalidaException("A informa��o � inv�lida");
		}
		this.ativo = ativo;
	}
}
