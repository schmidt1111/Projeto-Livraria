package com.qintess.model;
import com.qintess.model.Livro;
import com.qintess.model.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class LivroDao {
	
	public void buscaPorTodos (int id) {
		
		Livro livro = new Livro();
		
		try {
			//caminho para achar o drive
			Class.forName("org.h2.Driver");
			//criar conexao
			//       Connection test = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			//declarando coisas na conexao utilizada
			//       Statement comandos = test.createStatement();
			//string de apoio para o codigo SQL
		
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			
		String sql = "SELECT IDLIVRO, TITULO, PRECO, ESTOQUE,"
				+"IDGENERO FROM LIVRO WHERE IDLIVRO = ?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			livro.setIdlivro(id);
			livro.setTitulo(rs.getString(2));
			livro.setPreco(rs.getDouble(3));
			livro.setEstoque(rs.getInt(4));
			
			Genero genero = new Genero();
			
			genero.setIdgenero(rs.getInt(5));
			livro.setIdgenero(genero);
			
		}
		
		System.out.println("Executado com sucesso.");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
		
public List<Livro> buscaTodos(){
	return null;
}
public void atualiza (Livro livro) {
	}
public void insere (Livro livro) {
	
}
public void remove (Livro livro) {
	
}
public void ler (int idlivro) {
	Conexao con = new Conexao();
	
	String sqlselect = "SELECT IDVENDA,TOTAL, IDCLIENTE, DATA FROM VENDA";
	PreparedStatement stm = con.preparedStatement (sqlselect);
	
	ResultSet rs = con.executaBusca(sqlselect);
	
	try {
		While (rs.next()){
			
			int idlivro = rs.getInt("idvenda");
			float total = rs.getFloat("total");
			int idcliente = rs.getInt("idcliente");
			LocalDate data = rs.getLocalDate.("data");
			
			System.out.println(idvenda +" - " + total+ " - "+ idcliente+ " - "+ data);
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private void While(boolean next) {
	// TODO Auto-generated method stub
	
}

}
