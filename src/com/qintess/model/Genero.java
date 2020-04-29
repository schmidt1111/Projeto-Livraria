package com.qintess.model;

public class Genero {
	private int idgenero;
	private String descricao;
	
	public Genero (int idgenero, String descricao) {
		this.idgenero = idgenero;
		this.descricao = descricao;
	}
	
	public Genero() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Genero [idgenero=" + idgenero + ", descricao=" + descricao + "]";
	}

	public int getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
