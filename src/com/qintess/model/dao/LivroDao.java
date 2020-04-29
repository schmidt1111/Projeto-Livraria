package com.qintess.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qintess.conexao.Conexao;
import com.qintess.model.Livro;

public class LivroDao {
	public static void createLivroTable() {
		String sql = "CREATE TABLE LIVRO ("
				+ "IDLIVRO INT PRIMATY KEY AUTO_INCREMENT,"
				+ "TITULO VARCHAR(100) NOT NULL,"
				+ "PRECO DECIMAL(10,2) NOT NULL,"
				+ "ESTOQUE INT NOT NULL,"
				+ "IDGENERO INT, FOREIGN KEY (IDGENERO) REFERENCES GENERO (IDGENERO)";
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("A tabela já existe.");
		}
	}
	
	public static void insert (int idlivro, String titulo, double preco, int estoque, int idgenero) {
		String sql = "INSERT INTO IDLIVRO, TITULO, PRECO, ESTOQUE, IDGENERO VALUES ?, ?, ?, ?, ?";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idlivro);
			ps.setString(2, titulo);
			ps.setDouble(3, preco);
			ps.setInt(4, estoque);
			ps.setInt(5, idgenero);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou se os nomes estão corretos.");
		}
	}
	public static List<Livro> selectAll() {
		List<Livro> listaLivro = new ArrayList<Livro>(); 
			String sql = "SELECT IDLIVRO, TITULO, PRECO, ESTOQUE, IDGENERO FROM LIVRO";
			try(Connection conn = Conexao.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {
				
				while(rs.next()) {
					Livro livro = new Livro(rs.getInt("IDLIVRO"), rs.getString("TITULO"), rs.getDouble("PRECO"), rs.getInt("ESTOQUE"), rs.getInt("IDGENERO"));
					listaLivro.add(livro);
				}
				return listaLivro;
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Verifique se há tabela e/ou o nome está correto");
			}
				return null;
		}
	
	public static void delete (int idlivro) {
		String sql = "DELETE FROM LIVRO WHERE IDLIVRO = ?";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idlivro);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Tabela não encontrada e/ou nome inválido.");
		}
	}
	public static Livro selectByIdlivro (int idlivro) {
		String sql = "SELECT IDLIVRO, TITULO, PRECO, ESTOQUE, IDGENERO FROM LIVRO WHERE IDLIVRO = ?";
		Livro res = null;
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idlivro);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				res = new Livro (rs.getInt("IDLIVRO"), rs.getString("TITULO"), rs.getDouble("PRECO"), rs.getInt("ESTOQUE"), rs.getInt("IDGENERO"));
			}
			rs.close();
			return res;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se o nome está escrito corretamente.");
			}
		return null;
		}
	}

