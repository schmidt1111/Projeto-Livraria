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

public class ItemDaVendaDao {
	
		public static void CreateItensDaVendaTable() {
			
		String sql = "CREATE TABLE ITENS_DA_VENDA(IDVENDA INT NOT NULL,"
				+ "IDLIVRO INT NOT NULL,"
				+ "FOREIGN KEY (IDVENDA) REFERENCES VENDA(IDVENDA),"
				+ "FOREIGN KEY (IDLIVRO) REFERENCES LIVRO (IDLIVRO),"
				+ "PRIMARY KEY (IDVENDA, IDLIVRO),"
				+ "QTD INT NOT NULL,"
				+ "SUBTOTAL DECIMAL (10,2) NOT NULL)";
		
				
		try(Connection conn = Conexao.getConnection();
		Statement stmt = conn.createStatement()	) {
			stmt.execute(sql);
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Tabela existente.");
		}
	}
		
	public static void insert (ItemDaVenda item) {
		String sql = "INSERT INTO ITENS_DA_VENDA (IDVENDA,IDLIVRO,QTD,SUBTOTAL) VALUES (?, ?, ?, ?)";
		
		try (Connection conn = Conexao.getConnection();
		PreparedStatement ps= conn.prepareStatement(sql)) {
			
			ps.setInt(1,item.getIdvenda());
			ps.setInt(2, item.getIdlivro());
			ps.setInt(3, item.getQtd());
			ps.setDouble(4, item.getSubtotal());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("O item não foi inserido, precisa criar a tabela primeiro.");
		}
	}
	
	public static List <ItemDaVenda> selectAll() {
		List<ItemDaVenda> listaItemDaVenda = new ArrayList<ItemDaVenda>();
		String sql = "SELECT IDVENDA,IDLIVRO,QTD,SUBTOTAL FROM ITENS_DA_VENDA";
		
		try (Connection conn = Conexao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
					
					while (rs.next()) {
						ItemDaVenda itensDaVenda = new ItemDaVenda(rs.getInt("IDVENDA"), rs.getInt("IDLIVRO"), rs.getInt("QTD"), rs.getDouble("SUBTOTAL"));
						listaItemDaVenda.add(itensDaVenda);
					}
					return listaItemDaVenda;
				} catch(SQLException e) {
					e.printStackTrace();
					System.out.println("A tabela não foi criada, precisa criar a tabela primeiro.");
				}
		return null;
	}
	
	public static void delete (int idvenda) {
		String sql = "DELETE FROM ITENS_DA_VENDA WHERE IDVENDA =?";
		
			try(Connection conn = Conexao.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {
						ps.setInt(1, idvenda);
						ps.execute();
						
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
	}
	public static List<ItemDaVenda> selectByIdVenda(int idvenda){
		
		String sql = "SELECT IDVENDA, IDLIVRO, QTD, SUBTOTAL FROM ITENS_DA_VENDA WHERE IDVENDA = ?";
			List<ItemDaVenda> lista = new ArrayList<ItemDaVenda>();
		
		try(Connection conn = Conexao.getConnection();
				
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, idvenda);
			ResultSet rs = ps.executeQuery();
				
			
			while(rs.next()) {
				lista.add(new ItemDaVenda(rs.getInt("idvenda"), rs.getInt("idlivro"), rs.getInt("qtd"), rs.getDouble("subtotal")));
				
			}
			rs.close();
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("A tabela não foi criada precisa criar a tabela primeiro.");
		}
		return null;
	}
	public static ItemDaVenda selectTop1() {
		String sql = "select top 1 idlivro, sum (qtd) as qtd from itens_Da_venda group by idlivro;";
		try(Connection conn = Conexao.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			ItemDaVenda resul = null;
			while(rs.next()) {
				resul = new ItemDaVenda(rs.getInt("idlivro"), rs.getInt("qtd"));
			}
			rs.close();
			return resul;
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("A tabela não foi criada precisa criar a tabela primeiro.");
		return null;
	}}
	
	
	
	
	public static Object selectByIdLivro(String idlivro, String idvenda, String qtd, String subtotal) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void insert(int idvenda, int idlivro, int qtd, double subtotal) {
		// TODO Auto-generated method stub
		
	}

	
	
}
