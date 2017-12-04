package model;

import java.time.LocalDate;

import exceptions.DoacaoInvalidaException;

public class Doacao implements Comparable<Doacao>{
	private int id;
	private Double valor;
	private LocalDate data;
	private boolean ativo;
	private Pessoa doador;
	
	public Doacao() {
		
	}
	public Doacao(int id, Double valor, LocalDate data, boolean ativo, Pessoa doador) throws DoacaoInvalidaException {
		setId(id);
		setValor(valor);
		setData(data);
		setAtivo(ativo);
		setDoador(doador);
	}
	public Doacao(Double valor, LocalDate data, boolean ativo, Pessoa doador) throws DoacaoInvalidaException {
		setValor(valor);
		setData(data);
		setAtivo(ativo);
		setDoador(doador);
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) throws DoacaoInvalidaException {
		if(id < 0) {
			throw new DoacaoInvalidaException("O ID informado é inválido!");
		}
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) throws DoacaoInvalidaException {
		if(valor < 0) {
			throw new DoacaoInvalidaException("O valor informado é inválido!");
		}
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) throws DoacaoInvalidaException {
		if(data == null) {
			throw new DoacaoInvalidaException("A data informada é inválida!");
		}
		this.data = data;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Pessoa getDoador() {
		return doador;
	}
	public void setDoador(Pessoa doador) throws DoacaoInvalidaException {
		if(doador == null) {
			throw new DoacaoInvalidaException("O doador informado é inválido!");
		}
		this.doador = doador;
	}
	@Override
	public String toString() {
		return "Doacao [id=" + id + ", valor=" + valor + ", data=" + data + ", ativo="+ ativo +", doador=" + this.getDoador().getNome() + " idDoador=" +this.getDoador().getId()+ "]";
	}
	public int compareTo(Doacao o) {
		return this.getData().compareTo(o.getData());
	}
	
	
}
