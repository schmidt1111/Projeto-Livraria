package com.qintess.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
	public Connection getConexao() {
		
		try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")){
			return conn;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public PreparedStatement preparedStatement(String sqlselect) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet executaBusca(String sqlselect) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
