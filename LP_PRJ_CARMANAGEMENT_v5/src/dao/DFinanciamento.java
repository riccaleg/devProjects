package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MFinanciamento;

public class DFinanciamento {
	public static void Alterar(MFinanciamento f) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL fin_alterar(?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getCodigo());
			stmt.setFloat(2, f.getJurosMensal());
			stmt.setFloat(3, f.getValorSemJuros());
			stmt.setFloat(4, f.getValorParcela());
			stmt.setInt(5, f.getQtdParcelas());
			stmt.setInt(6, f.getQtdParcelasPagas());
			stmt.setDate(7, f.getDataPrimeiraParcela());
			stmt.setInt(8, f.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Financiamento!");
		}
	}
	
	public static MFinanciamento BuscarCodigo(MFinanciamento f) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL fin_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				f.setJurosMensal(rs.getFloat("fin_jurosMensal"));
				f.setValorSemJuros(rs.getFloat("fin_valorSemJuros"));
				f.setValorParcela(rs.getFloat("fin_valorParcela"));
				f.setQtdParcelas(rs.getInt("fin_qtdParcelas"));
				f.setQtdParcelasPagas(rs.getInt("fin_qtdParcelasPagas"));
				f.setDataPrimeiraParcela(rs.getDate("fin_dataPrimeiraParcela"));
				f.setCar_codigo(rs.getInt("fin_car_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return f;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Financiamento!");
		}
	}
	
	public static void Excluir(MFinanciamento f) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL fin_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Financiamento!");
		}
	}
	
	public static void Inserir(MFinanciamento f) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL fin_inserir(?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setFloat(1, f.getJurosMensal());
			stmt.setFloat(2, f.getValorSemJuros());
			stmt.setFloat(3, f.getValorParcela());
			stmt.setInt(4, f.getQtdParcelas());
			stmt.setInt(5, f.getQtdParcelasPagas());
			stmt.setDate(6, f.getDataPrimeiraParcela());
			stmt.setInt(7, f.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Financiamento!");
		}
	}
	
	public static ArrayList<MFinanciamento> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MFinanciamento> array = new ArrayList<MFinanciamento>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MFinanciamento f;
			
			String sql = "{ CALL fin_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				f = new MFinanciamento();
				f.setCodigo(rs.getInt("fin_codigo"));
				f.setJurosMensal(rs.getFloat("fin_jurosMensal"));
				f.setValorSemJuros(rs.getFloat("fin_valorSemJuros"));
				f.setValorParcela(rs.getFloat("fin_valorParcela"));
				f.setQtdParcelas(rs.getInt("fin_qtdParcelas"));
				f.setQtdParcelasPagas(rs.getInt("fin_qtdParcelasPagas"));
				f.setDataPrimeiraParcela(rs.getDate("fin_dataPrimeiraParcela"));
				f.setCar_codigo(rs.getInt("fin_car_codigo"));
				array.add(f);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Financiamentos!");
		}
	}
}