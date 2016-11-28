package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MDespesa;

public class DDespesa {
	public static void Alterar(MDespesa d) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL des_alterar(?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, d.getCodigo());
			stmt.setDate(2, d.getData());
			stmt.setFloat(3, d.getCusto());
			stmt.setInt(4, d.getCar_codigo());
			stmt.setInt(5, d.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Despesa!");
		}
	}
	
	public static MDespesa BuscarCodigo(MDespesa d) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL des_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, d.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				d.setData(rs.getDate("des_data"));
				d.setCusto(rs.getFloat("des_custo"));
				d.setCar_codigo(rs.getInt("des_car_codigo"));
				d.setPag_codigo(rs.getInt("des_pag_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return d;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Despesa!");
		}
	}
	
	public static void Excluir(MDespesa d) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL des_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, d.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Despesa!");
		}
	}
	
	public static void Inserir(MDespesa d) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL des_inserir(?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1, d.getData());
			stmt.setFloat(2, d.getCusto());
			stmt.setInt(3, d.getCar_codigo());
			stmt.setInt(4, d.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Despesa!");
		}
	}
	
	public static ArrayList<MDespesa> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MDespesa> array = new ArrayList<MDespesa>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MDespesa d;
			
			String sql = "{ CALL des_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				d = new MDespesa();
				d.setCodigo(rs.getInt("des_codigo"));
				d.setData(rs.getDate("des_data"));
				d.setCusto(rs.getFloat("des_custo"));
				d.setCar_codigo(rs.getInt("des_car_codigo"));
				d.setPag_codigo(rs.getInt("des_pag_codigo"));
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Despesas!");
		}
	}
}