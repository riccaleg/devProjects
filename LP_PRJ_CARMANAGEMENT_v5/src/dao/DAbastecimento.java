package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MAbastecimento;
import model.MCarro;

public class DAbastecimento {
	public static void Alterar(MAbastecimento a) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL aba_alterar(?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, a.getCodigo());
			stmt.setDate(2, a.getData());
			stmt.setInt(3, a.getHodometro());
			stmt.setFloat(4, a.getValorLitro());
			stmt.setFloat(5, a.getValorTotal());
			stmt.setInt(6, a.getCar_codigo());
			stmt.setInt(7, a.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Abastecimento!");
		}
	}
	
	public static MAbastecimento BuscarCodigo(MAbastecimento a) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL aba_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, a.getCodigo());
			
			rs = stmt.executeQuery();
			
			if (rs.next()){
				a.setData(rs.getDate("aba_data"));
				a.setHodometro(rs.getInt("aba_hodometro"));
				a.setValorLitro(rs.getFloat("aba_valorLitro"));
				a.setValorTotal(rs.getFloat("aba_valorTotal"));
				a.setCar_codigo(rs.getInt("aba_car_codigo"));
				a.setPag_codigo(rs.getInt("aba_pag_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			
			return a;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Abastecimento!");
		}
	}
	
	public static void Excluir(MAbastecimento a) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL aba_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, a.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Abastecimento!");
		}
	}
	
	public static void Inserir(MAbastecimento a) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL aba_inserirr(?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1, a.getData());
			stmt.setInt(3, a.getHodometro());
			stmt.setFloat(3, a.getValorLitro());
			stmt.setFloat(4, a.getValorTotal());
			stmt.setInt(5, a.getCar_codigo());
			stmt.setInt(6, a.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Abastecimento!");
		}
	}
	
	public static ArrayList<MAbastecimento> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MAbastecimento> array = new ArrayList<MAbastecimento>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MAbastecimento a;
			
			String sql = "{ CALL aba_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				a = new MAbastecimento();
				a.setCodigo(rs.getInt("aba_codigo"));
				a.setData(rs.getDate("aba_data"));
				a.setHodometro(rs.getInt("aba_hodometro"));
				a.setValorLitro(rs.getFloat("aba_valorLitro"));
				a.setValorTotal(rs.getFloat("aba_valorTotal"));
				a.setCar_codigo(rs.getInt("aba_car_codigo"));
				a.setPag_codigo(rs.getInt("aba_pag_codigo"));
				array.add(a);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Abastecimentos!");
		}
	}
}