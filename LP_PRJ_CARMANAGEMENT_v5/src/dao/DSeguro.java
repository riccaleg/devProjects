package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MSeguro;

public class DSeguro {
	public static void Alterar(MSeguro s) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL seg_alterar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, s.getCodigo());
			stmt.setString(2, s.getNomeSeguradora());
			stmt.setInt(3, s.getNumApolice());
			stmt.setFloat(4, s.getValor());
			stmt.setFloat(5, s.getValorParcela());
			stmt.setInt(6, s.getQtdParcelas());
			stmt.setInt(7, s.getQtdParcelasPagas());
			stmt.setDate(8, s.getDataValidade());
			stmt.setDate(9, s.getDataPrimeiraParcela());
			stmt.setInt(10, s.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Seguro!");
		}
	}
	
	public static MSeguro BuscarCodigo(MSeguro s) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL seg_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, s.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				s.setNomeSeguradora(rs.getString("seg_nomeSeguradora"));
				s.setNumApolice(rs.getInt("seg_numApolice"));
				s.setValor(rs.getFloat("seg_valor"));
				s.setValorParcela(rs.getFloat("seg_valorParcela"));
				s.setQtdParcelas(rs.getInt("seg_qtdParcelas"));
				s.setQtdParcelasPagas(rs.getInt("seg_qtdParcelasPagas"));
				s.setDataValidade(rs.getDate("seg_dataValidade"));
				s.setDataPrimeiraParcela(rs.getDate("seg_dataPrimeiraParcela"));
				s.setCar_codigo(rs.getInt("seg_car_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return s;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Seguro!");
		}
	}
	
	public static void Excluir(MSeguro s) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL seg_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, s.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Seguro!");
		}
	}
	
	public static void Inserir(MSeguro s) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL seg_inserir(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, s.getNomeSeguradora());
			stmt.setInt(2, s.getNumApolice());
			stmt.setFloat(3, s.getValor());
			stmt.setFloat(4, s.getValorParcela());
			stmt.setInt(5, s.getQtdParcelas());
			stmt.setInt(6, s.getQtdParcelasPagas());
			stmt.setDate(7, s.getDataValidade());
			stmt.setDate(8, s.getDataPrimeiraParcela());
			stmt.setInt(9, s.getCar_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Seguro!");
		}
	}
	
	public static ArrayList<MSeguro> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MSeguro> array = new ArrayList<MSeguro>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MSeguro s;
			
			String sql = "{ CALL seg_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				s = new MSeguro();
				s.setCodigo(rs.getInt("seg_codigo"));
				s.setNomeSeguradora(rs.getString("seg_nomeSeguradora"));
				s.setNumApolice(rs.getInt("seg_numApolice"));
				s.setValor(rs.getFloat("seg_valor"));
				s.setValorParcela(rs.getFloat("seg_valorParcela"));
				s.setQtdParcelas(rs.getInt("seg_qtdParcelas"));
				s.setQtdParcelasPagas(rs.getInt("seg_qtdParcelasPagas"));
				s.setDataValidade(rs.getDate("seg_dataValidade"));
				s.setDataPrimeiraParcela(rs.getDate("seg_dataPrimeiraParcela"));
				s.setCar_codigo(rs.getInt("seg_car_codigo"));
				array.add(s);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Pagamentos!");
		}
	}
}