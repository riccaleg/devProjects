package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MImposto;

public class DImposto {
	public static void Alterar(MImposto i) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL imp_alterar(?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, i.getCodigo());
			stmt.setFloat(2, i.getValorImposto());
			stmt.setFloat(3, i.getValorParcela());
			stmt.setInt(4, i.getQtdParcelas());
			stmt.setInt(5, i.getQtdParcelasPagas());
			stmt.setInt(6, i.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Imposto!");
		}
	}
	
	public static MImposto BuscarCodigo(MImposto i) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL imp_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, i.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				i.setAno(rs.getInt("imp_ano"));
				i.setValorImposto(rs.getFloat("imp_valorImposto"));
				i.setValorParcela(rs.getFloat("imp_valorParcela"));
				i.setQtdParcelas(rs.getInt("imp_qtdParcelas"));
				i.setQtdParcelasPagas(rs.getInt("imp_qtdParcelasPagas"));
				i.setDataPrimeiraParcela(rs.getDate("imp_dataPrimeiraParcela"));
				i.setCar_codigo(rs.getInt("imp_car_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return i;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Imposto!");
		}
	}
	
	public static void Excluir(MImposto i) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL imp_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, i.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Imposto!");
		}
	}
	
	public static void Inserir(MImposto i) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL imp_inserir(?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setFloat(1, i.getValorImposto());
			stmt.setFloat(2, i.getValorParcela());
			stmt.setInt(3, i.getQtdParcelas());
			stmt.setInt(4, i.getQtdParcelasPagas());
			stmt.setInt(5, i.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Imposto!");
		}
	}
	
	public static ArrayList<MImposto> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MImposto> array = new ArrayList<MImposto>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MImposto i;
			
			String sql = "{ CALL imp_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				i = new MImposto();
				i.setCodigo(rs.getInt("imp_codigo"));
				i.setAno(rs.getInt("imp_ano"));
				i.setValorImposto(rs.getFloat("imp_valorImposto"));
				i.setValorParcela(rs.getFloat("imp_valorParcela"));
				i.setQtdParcelas(rs.getInt("imp_qtdParcelas"));
				i.setQtdParcelasPagas(rs.getInt("imp_qtdParcelasPagas"));
				i.setDataPrimeiraParcela(rs.getDate("imp_dataPrimeiraParcela"));
				i.setCar_codigo(rs.getInt("imp_car_codigo"));
				array.add(i);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Impostos!");
		}
	}
}