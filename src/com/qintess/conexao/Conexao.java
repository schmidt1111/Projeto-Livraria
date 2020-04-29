package com.qintess.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Conexao {
	
	public static Connection getConnection() {
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
			return conn;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void CloseConnection (Connection conn) {
		try {
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void CloseResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

