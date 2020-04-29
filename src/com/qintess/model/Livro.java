package com.qintess.model;

public class Livro {
	private int idlivro;
	private String titulo;
	private double preco;
	private int estoque;
	private int idgenero;
	
	
	public Livro (int idlivro, String titulo, double preco, int estoque, int idgenero) {
		this.idlivro = idlivro;
		this.titulo = titulo;
		this.preco = preco;
		this.estoque = estoque;
		this.idgenero = idgenero;
	}
	
	@Override
	public String toString() {
		return "Livro [idlivro=" + idlivro + ", titulo=" + titulo + ", preco=" + preco + ", estoque=" + estoque
				+ ", idgenero=" + idgenero + "]";
	}

	public Livro() {
		// TODO Auto-generated constructor stub
	}
	
	public int getIdlivro() {
		return idlivro;
	}
	public void setIdlivro(int idlivro) {
		this.idlivro = idlivro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public int getIdgenero() {
		return idgenero;
	}
	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}
	
	
}
