package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

import controller.Conexao;
import model.MCombustivel;

public class DCombustivel {
	public static void Alterar(MCombustivel c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL com_alterar(?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			stmt.setString(2, c.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Combustível!");
		}
	}
	
	public static MCombustivel BuscarCodigo(MCombustivel c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL com_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				c.setDescricao(rs.getString("com_descricao"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return c;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Combustível!");
		}
	}
	
	public static void Excluir(MCombustivel c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL com_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Combustível!");
		}
	}
	
	public static MCombustivel GetIdByDescricao(MCombustivel c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL com_getIdByDescricao(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getDescricao());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				c.setCodigo(rs.getInt("com_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return c;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Código de Combustível!");
		}
	}
	
	public static void Inserir(MCombustivel c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL com_inserir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Combustível!");
		}
	}
	
	public static ArrayList<MCombustivel> Listar() throws Exception{
		try {
			ArrayList<MCombustivel> array = new ArrayList<MCombustivel>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MCombustivel c;
			
			String sql = "{ CALL com_listar }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				c = new MCombustivel();
				c.setCodigo(rs.getInt("com_codigo"));
				c.setDescricao(rs.getString("com_descricao"));
				array.add(c);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Combustíveis!");
		}
	}
	
	public static DefaultComboBoxModel<String> ListarComboBox() throws Exception{
		try {
			DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL com_listar }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				modelo.addElement(rs.getString("com_descricao"));
			}
			
			con.close();
			return modelo;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Combustíveis!");
		}
	}
}