package com.qintess.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qintess.conexao.Conexao;
import com.qintess.model.Autor;

public class AutorDao {
	public static void createAutorTable() {
		String sql = "CREATE TABLE AUTOR ("
				+ "IDAUTOR INT PRIMATY KEY AUTO_INCREMENT,"
				+ "NOME VARCHAR (100) NOT NULL,"
				+ "EMAIL VARCHAR(100) NOT NULL)";
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("A tabela já existe.");
		}
	}

	public static void insert(int idautor, String nome, String email) {
		String sql = "INSERT INTO AUTOR (IDAUTOR, NOME, EMAIL) VALUES (?, ?, ?)";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, idautor);
				ps.setString(2, nome);
				ps.setString(3, email);
				ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou os dados são validos.");
		}
	}
	public static List<Autor> selectAll() {
		List<Autor> listaAutor = new ArrayList<Autor>();
		String sql = "SELECT IDAUTOR,NOME, EMAIL FROM AUTOR";
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				Autor autor = new Autor(rs.getInt("IDAUTOR"), rs.getString("NOME"), rs.getString("EMAIL"));
				listaAutor.add(autor);
			}
			return listaAutor;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há a tabela e/ou se o item existe.");
		}
		return null;
	}
	public static void delete (int idautor) {
		String sql = "DELETE FROM CLIENTE WHERE IDAUTOR = ?";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)){
					ps.setInt(1, idautor);
					ps.execute();
				} catch(SQLException e) {
					e.printStackTrace();
					System.out.println("Verifique se o idautor está correto.");
				}
	}
	public static List<Autor> selectByIdautor (int idautor, String nome, String email) {
		String sql = "SELECT IDAUTOR FROM AUTOR WHERE IDAUTOR = ?";
		List<Autor> lista = new ArrayList<Autor>();
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idautor);
			ps.setString(2, nome);
			ps.setString(3,email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lista.add(new Autor (rs.getInt("IDAUTOR"), rs.getString("NOME"), rs.getString("EMAIL")));
			}
			rs.close();
			return lista;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se a tabela existe e/ou se o nome esta correto.");
		}
		return null;
	}
	
}
