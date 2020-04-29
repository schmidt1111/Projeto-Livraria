package com.qintess.model;
import com.qintess.model.Livro;
import com.qintess.model.Autor;

public class Escreve {
	private Livro idlivro;
	private Autor idautor;
	
	public void Livro (Livro idlivro, Autor idautor) {
		this.idautor = idautor;
		this.idlivro = idlivro;
	}
		
	@Override
	public String toString() {
		return "Escreve [idlivro=" + idlivro + ", idautor=" + idautor + "]";
	}


	public Livro getIdlivro() {
		return idlivro;
	}

	public void setIdlivro(Livro idlivro) {
		this.idlivro = idlivro;
	}

	public Autor getIdautor() {
		return idautor;
	}

	public void setIdautor(Autor idautor) {
		this.idautor = idautor;
	}
	
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idautor;
		result = prime * result + idlivro;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escreve other = (Escreve) obj;
		if (idautor != other.idautor)
			return false;
		if (idlivro != other.idlivro)
			return false;
		return true;
	}*/
}

	