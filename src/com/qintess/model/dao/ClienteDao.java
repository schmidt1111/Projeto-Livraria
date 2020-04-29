package com.qintess.model.dao;

import com.qintess.conexao.Conexao;
import com.qintess.model.Cliente;
import com.qintess.model.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
	
	public static void createCienteDaoTable() {
		String sql = "CREATE TABLE CLIENTE ("
				+ "IDCLIENTE INT PRIMATY KEY AUTO_INCREMENT,"
				+ "NOME VARCHAR(100) NOT NULL,"
				+ "TELEFONE VARCHAR(45) NOT NULL)";
				
				try (Connection conn = Conexao.getConnection();
					Statement stmt = conn.createStatement()) {
					
					stmt.execute(sql);
					
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("A tabela já foi criada.");
					
				}
	}
	
	public static void insert (int idcliente, String nome, String telefone) {
		String sql = "INSERT INTO CLIENTE IDCLIENTE, NOME, TELEFONE VALUES ?, ?, ?";
		 
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
				
			ps.setInt(1, idcliente);
			ps.setString (2, nome);
			ps.setString(3, telefone);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se a tabela está criada antes de inserir dados.");
		}
	}
	
	public static List<Cliente> selectAll(){
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		String sql = "SELECT IDCLIENTE, NOME, TELEFONE FROM CLIENTE";
		
		try(Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("IDCLIENTE"), rs.getString("NOME"), rs.getString("TELEFONE"));
				listaCliente.add(cliente);
			}
			return listaCliente;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há a tabela e/ou se o nome está correto.");
		}
		return null;
	}

	public static void delete (int idcliente) {
		
		String sql = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
		
		try(Connection conn = Conexao.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, idcliente);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há a tabela e/ou se o id esta correto.");
		}
	}

	public static Cliente selectIdCliente (int idcliente){
		String sql = "SELECT IDCLIENTE, NOME, TELEFONE FROM CLIENTE WHERE IDCLIENTE = ?";
		Cliente resu = null;
		
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idcliente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				resu = new Cliente(rs.getInt("IDCLIENTE"), rs.getString("NOME"), rs.getNString("TELEFONE"));
			}
			rs.close();
			return resu;
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Verifique se há tabela e/ou se o nome esta correto.");
		}
		return null;
	}

	public static void insert(String idcliente, String nome, String telefone) {
		// TODO Auto-generated method stub
		
	}

	
}
