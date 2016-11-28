package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MMulta;

public class DMulta {
	public static void Alterar(MMulta m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL mul_alterar(?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			stmt.setFloat(2, m.getValor());
			stmt.setString(3, m.getDescricao());
			stmt.setDate(4, m.getDataInfracao());
			stmt.setDate(5, m.getDataVencimento());
			stmt.setInt(6, m.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Multa!");
		}
	}
	
	public static MMulta BuscarCodigo(MMulta m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL mul_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				m.setValor(rs.getFloat("mul_valor"));
				m.setDescricao(rs.getString("mul_descricao"));
				m.setDataInfracao(rs.getDate("mul_dataInfracao"));
				m.setDataVencimento(rs.getDate("mul_dataVencimento"));
				m.setCar_codigo(rs.getInt("mul_car_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return m;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Multa!");
		}
	}
	
	public static void Excluir(MMulta m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL mul_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Multa!");
		}
	}
	
	public static void Inserir(MMulta m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL mul_inserir(?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setFloat(1, m.getValor());
			stmt.setString(2, m.getDescricao());
			stmt.setDate(3, m.getDataInfracao());
			stmt.setDate(4, m.getDataVencimento());
			stmt.setInt(5, m.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Multa!");
		}
	}
	
	public static ArrayList<MMulta> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MMulta> array = new ArrayList<MMulta>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MMulta m;
			
			String sql = "{ CALL mul_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				m = new MMulta();
				m.setCodigo(rs.getInt("mul_codigo"));
				m.setValor(rs.getFloat("mul_valor"));
				m.setDescricao(rs.getString("mul_descricao"));
				m.setDataInfracao(rs.getDate("mul_dataInfracao"));
				m.setDataVencimento(rs.getDate("mul_dataVencimento"));
				m.setCar_codigo(rs.getInt("mul_car_codigo"));
				array.add(m);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Multas!");
		}
	}
}