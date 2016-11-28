package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String urlAzure = "jdbc:mysql://us-cdbr-azure-central-a.cloudapp.net/carmanagement";
	private static Connection conexao;
	
	public Conexao() {}
	
	public static Connection getConexao() throws SQLException {
		if(conexao == null || conexao.isClosed()) {
			conexao = DriverManager.getConnection(urlAzure, "b1c5dd3c5fc953", "e08df950");
		}
		return conexao;
	}
}