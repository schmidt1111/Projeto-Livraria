package com.qintess.model;

public class ItemDaVenda {
	private int idvenda;
	private int idlivro;
	private int qtd;
	private double subtotal;
	
	public ItemDaVenda(int idvenda, int idlivro, int qtd, double subtotal) {
		this.idvenda = idvenda;
		this.idlivro = idlivro;
		this.qtd = qtd;
		this.subtotal = subtotal;
	}

	public ItemDaVenda() {
		// TODO Auto-generated constructor stub
	}

	public ItemDaVenda(int idlivro, int qtd) {
		this.idlivro = idlivro;
		this.qtd = qtd;
		// TODO Auto-generated constructor stub
	}

	public int getIdvenda() {
		return idvenda;
	}

	public void setIdvenda(int idvenda) {
		this.idvenda = idvenda;
	}

	public int getIdlivro() {
		return idlivro;
	}

	public void setIdlivro(int idlivro) {
		this.idlivro = idlivro;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public double getSubtotal() {
		return subtotal;
	}
	public void SetDubtotal (double subtotal) {
		this.subtotal = subtotal;
	}
//metodo modificador com preco*quantidade
	/*public double setSubtotal(double subtotal) {
		this.subtotal = this.idlivro.getPreco()* this.qtd;
		return subtotal;
	}*/


	@Override
	public String toString() {
		return "ItemDaVenda [idvenda=" + idvenda + ", idlivro=" + idlivro + ", qtd=" + qtd + ", subtotal=" + subtotal
				+ "]";
	}
	
}
