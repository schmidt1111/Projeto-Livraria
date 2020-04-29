package com.qintess.model;

import java.util.List;

public class Cliente {
	private int idcliente;
	private String nome;
	private String telefone;
	
	public Cliente(int idcliente, String nome, String telefone) {
		this.idcliente = idcliente;
		this.nome = nome;
		this.telefone = telefone;
	}
	
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public static List<Cliente> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return "Cliente [idcliente=" + idcliente + ", nome=" + nome + ", telefone=" + telefone + "]";
	}
}
