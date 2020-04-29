package com.qintess.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qintess.conexao.Conexao;
import com.qintess.model.Genero;

public class GeneroDao {
	public static void createGeneroTable() {
		String sql = "CREATE TABLE GENERO ("
				+ "IDGENERO INT PRIMARY KEY AUTO_INCREMENT,"
				+ "DESCRICAO VARCHAR(100) NOT NULL)";
		
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement()){
			
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("A tabela já existe.");
		}
	}
	
	public static void insert(int idgenero, String descricao) {
		String sql = "INSERT TINTO CLIENTE IDGENERO, DESCRICAO VALUES ?, ?";
		
		try(Connection conn = Conexao.getConnection();
			
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, idgenero);
			ps.setString(2, descricao);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela.");
		}
	}

	public static List<Genero> selectAll() {
		List<Genero> listaGenero = new ArrayList<Genero>();
		String sql = "SELECT IDGENERO, DESCRICAO FROM GENERO";
		
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				Genero genero = new Genero (rs.getInt("IDGENERO"), rs.getString("DESCRICAO"));
				listaGenero.add(genero);
			}
			return listaGenero;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou se o nome está escrito corretamente.");
		}
		return null;
	}
	
	public static void delete (int idgenero, String descricao) {
		String sql = "DELETE FROM GENERE WHERE IDGENERO = ?";
		
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idgenero);
			ps.setString(2, descricao);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou verifique se existe esse idgenero.");
		}
	}
	public static List<Genero> selectByName(int idgenero) {
		String sql = "SELECT IDGENERO FROM GENERO WHERE IDGENERO ?";
		List<Genero> lista = new ArrayList<Genero>();
		
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idgenero);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lista.add(new Genero (rs.getInt("IDGENERO"), rs.getString("DESCRICAO")));
			}
			rs.close();
			return lista;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se a tabela existe e/ou id genero é valido.");
		}
		return null;
	}
}	
	

