package model;

import java.time.LocalDate;

import exceptions.DoacaoInvalidaException;

public class Doacao {
	private int id;
	private String valor;//No diagrama tava String, mas preferi usar float
	private LocalDate data;
	private Pessoa doador;
	
	public int getId() {
		return id;
	}
	public void setId(int id) throws DoacaoInvalidaException {
		if(id < 0) {
			throw new DoacaoInvalidaException("O ID informado � inv�lido!");
		}
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) throws DoacaoInvalidaException {
		if(valor == null) {
			throw new DoacaoInvalidaException("O valor informado � inv�lido!");
		}
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) throws DoacaoInvalidaException {
		if(data == null) {
			throw new DoacaoInvalidaException("A data informada � inv�lida!");
		}
		this.data = data;
	}
	public Pessoa getDoador() {
		return doador;
	}
	public void setDoador(Pessoa doador) throws DoacaoInvalidaException {
		if(doador == null) {
			throw new DoacaoInvalidaException("O doador informado � inv�lido!");
		}
		this.doador = doador;
	}
	@Override
	public String toString() {
		return "Doacao [id=" + id + ", valor=" + valor + ", data=" + data + ", doador=" + doador + "]";
	}
	
}
