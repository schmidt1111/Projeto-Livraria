package com.qintess.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qintess.conexao.Conexao;
import com.qintess.model.ItemDaVenda;
import com.qintess.model.Venda;

public class VendaDao {
	public static void createVendaTable() {
		String sql = "CREATE TABLE VENDA ("
				+ "IDVENDA INT PRIMATY KEY AUTO_INCREMENT,"
				+ "DATA DATE NOT NULL,"
				+ "TOTAL DECIMAL(10,2) NOT NULL,"
				+ "FOREIGN KEY (IDCLIENTE) REFERENCES CLIENTE (IDCLIENTE)";
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("A tabela já existe.");
		}
	}

	public static void insert (int idvenda, String data, double total, int idcliente) {
		String sql = "INSERT INTO (IDVENDA, DATA, TOTAL, IDCLIENTE) VALUES ?, ?, ?, ?";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idvenda);
			ps.setString(2, data);
			ps.setDouble(3, total);
			ps.setInt(4, idcliente);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou se os nomes estão corretos.");
		}
	}
	
	public static List<Venda> selectAll() {
		List<Venda> listaVenda = new ArrayList<Venda>(); 
			String sql = "SELECT IDVENDA, DATA, TOTAL, IDCLIENTE FROM VENDA";
			try(Connection conn = Conexao.getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql)) {
				
				while(rs.next()) {
					Venda venda = new Venda(rs.getInt("IDVENDA"), rs.getString("DATA"), rs.getDouble("TOTAL"), rs.getInt("IDCLIENTE"));
					listaVenda.add(venda);
				}
				return listaVenda;
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Verifique se há tabela e/ou o nome está correto");
			}
				return null;
		}
	
	public static void delete (int idvenda) {
		String sql = "DELETE FROM VENDA WHERE IDVENDA = ?";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idvenda);
			ps.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Tabela não encontrada e/ou nome inválido.");
		}
	}
	
	public static List<Venda> selectById (int idvenda, String data, double total, int idcliente) {
		String sql = "SELECT IDVENDA, DATA, TOTAL, IDCLIENTE FROM VENDA WHERE IDVENDA = ?";
		List<Venda> lista = new ArrayList<Venda>();
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idvenda);
			ps.setString(2, data);
			ps.setDouble(3, total);
			ps.setInt(4, idcliente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lista.add(new Venda (rs.getInt("IDVENDA"), rs.getString("DATA"), rs.getDouble("TOTAL"), rs.getInt("IDCLIENTE")));
			}
			rs.close();
			return lista;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se o nome está escrito corretamente.");
			}
		return null;
		}
	
	public static Venda selectTop2() {
		String sql = "SELECT IDCLIENTE, TOTAL  FROM VENDA WHERE TOTAL = (SELECT MAX(TOTAL) FROM VENDA );";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			Venda resultop2 = null;
			while(rs.next()) {
				resultop2 = new Venda(rs.getInt("idcliente"), rs.getDouble("total"));
			}
			rs.close();
			return resultop2;
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("A tabela não foi criada precisa criar a tabela primeiro.");
		return null;
	}}
	
	public static List<Venda> selectTop3() {
		String sql = "SELECT IDCLIENTE, COUNT(IDCLIENTE) QTDIDCLIENTE FROM VENDA WHERE IDCLIENTE = IDCLIENTE GROUP BY IDCLIENTE ORDER BY QTDIDCLIENTE DESC";
		List<Venda> listqtdidcliente = new ArrayList<Venda>();
		
		Venda resultop3 = null;
		
		try (Connection conn = Conexao.getConnection();
				
			PreparedStatement ps = conn.prepareStatement(sql)){
		ResultSet rs = ps.executeQuery();
		
		int cont;
		
		for(cont=0; cont < 3; cont ++) {
			rs.next();
			resultop3 = new Venda(rs.getInt("idcliente"), rs.getInt("qtdidcliente"));
			listqtdidcliente.add(resultop3);
				
		}
			rs.close();
			return listqtdidcliente;
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verificação de erro.");
			return null;
			}
		}
	}


