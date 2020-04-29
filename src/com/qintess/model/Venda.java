package com.qintess.model;
import com.qintess.model.Cliente;

public class Venda {
	private int idvenda;
	private String data;   
	private double total;
	private int idcliente;
	
	private int qtdidcliente;
	
	public Venda (int idvenda, String data, double total, int idcliente) {
		this.idvenda = idvenda;
		this.data = data;
		this.total = total;
		this.idcliente = idcliente;
	}

	public Venda(int idcliente, double total) {
		this.idcliente = idcliente;
		this.total = total;
		// TODO Auto-generated constructor stub
	}

	public Venda(int idcliente, int qtdidcliente) {
		this.idcliente = idcliente;
		this.setQtdidcliente(qtdidcliente);
	}
	
	public int getIdvenda() {
		return idvenda;
	}

	public void setIdvenda(int idvenda) {
		this.idvenda = idvenda;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Venda [idvenda=" + idvenda + ", data=" + data + ", total=" + total + ", idcliente=" + idcliente + "]";
	}

	public int getQtdidcliente() {
		return qtdidcliente;
	}

	public void setQtdidcliente(int qtdidcliente) {
		this.qtdidcliente = qtdidcliente;
	}
	
	
}
