package com.qintess.model;

public class ModelCreateBanck {
/*import java.sql.Connection;        BIBLIOTECAS UTILIZADAS
import java.sql.SQLException;
import java.sql.Statement;

public class CreateBank {
	public void createBank() throws SQLException {       

		try (Connection conn = ConnectionFactory.getConnection()) {    CONECTANDO AO BANCO
			Statement stmt = conn.createStatement();
	
			stmt.execute("CREATE DATABASE IF NOT EXISTS bank");          CRIANDO O BANCO 
		
			String generos = "CREATE TABLE IF NOT EXISTS generos ("
					+ "idgenero INT AUTO_INCREMENT PRIMARY KEY,"
					+ "descricao VARCHAR(100) NOT NULL"
					+ ")";

			String livros = "CREATE TABLE IF NOT EXISTS livros ("
					+ "idlivro INT AUTO_INCREMENT PRIMARY KEY,"
					+ "titulo VARCHAR(100) NOT NULL,"
					+ "preco FLOAT NOT NULL,"
					+ "estoque INT NOT NULL,"
					+ "idgenero INT NOT NULL,"
					+ "FOREIGN KEY (idgenero) REFERENCES generos (idgenero)"
					+ ")";

			String autores = "CREATE TABLE IF NOT EXISTS autores ("
					+ "idautor INT AUTO_INCREMENT PRIMARY KEY,"
					+ "nome VARCHAR(100) NOT NULL,"
					+ "email VARCHAR(100) NOT NULL"
					+ ")";

			String escreve = "CREATE TABLE IF NOT EXISTS escreve ("
					+ "idlivro INT NOT NULL,"
					+ "idautor INT NOT NULL,"
					+ "FOREIGN KEY (idlivro) REFERENCES livros (idlivro),"
					+ "FOREIGN KEY (idautor) REFERENCES autores (idautor)"
					+ ")";

			String clientes = "CREATE TABLE IF NOT EXISTS clientes ("
					+ "idcliente INT AUTO_INCREMENT PRIMARY KEY,"
					+ "nome VARCHAR (100) NOT NULL,"
					+ "telefone VARCHAR (100) NOT NULL"
					+ ")";

			String vendas = "CREATE TABLE IF NOT EXISTS vendas ("
					+ "idvenda INT AUTO_INCREMENT PRIMARY KEY,"
					+ "data DATE NOT NULL,"
					+ "total FLOAT NOT NULL,"
					+ "idcliente INT NOT NULL,"
					+ "FOREIGN KEY (idcliente) REFERENCES clientes (idcliente)"
					+ ")";

			String itensDeVendas = "CREATE TABLE IF NOT EXISTS itens_de_vendas ("
					+ "idvenda INT NOT NULL,"
					+ "idlivro INT NOT NULL,"
					+ "qtd INT NOT NULL,"
					+ "subtotal FLOAT NOT NULL,"
					+ "FOREIGN KEY (idvenda) REFERENCES vendas (idvenda),"
					+ "FOREIGN KEY (idlivro) REFERENCES livros (idlivro)"
					+ ")";

			stmt.execute(generos);
			stmt.execute(livros);
			stmt.execute(autores);
			stmt.execute(escreve);
			stmt.execute(clientes);
			stmt.execute(vendas);
			stmt.execute(itensDeVendas);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/
}
