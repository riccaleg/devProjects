package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MManutencao;

public class DManutencao {
	public static void Alterar(MManutencao m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL man_alterar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			stmt.setString(2, m.getNomeServico());
			stmt.setDate(3, m.getDataManutencao());
			stmt.setDate(4, m.getDataValidade());
			stmt.setInt(5, m.getKmValidade());
			stmt.setInt(6, m.getHodometro());
			stmt.setFloat(7, m.getValorPago());
			stmt.setString(8, m.getAnotacoes());
			stmt.setInt(9, m.getCar_codigo());
			stmt.setInt(10, m.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Manutenção!");
		}
	}
	
	public static MManutencao BuscarCodigo(MManutencao m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL man_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				m.setNomeServico(rs.getString("man_nomeServico"));
				m.setDataManutencao(rs.getDate("man_dataManutencao"));
				m.setDataValidade(rs.getDate("man_dataValidade"));
				m.setKmValidade(rs.getInt("man_kmValidade"));
				m.setHodometro(rs.getInt("man_hodometro"));
				m.setValorPago(rs.getFloat("man_valorPago"));
				m.setAnotacoes(rs.getString("man_anotacoes"));
				m.setCar_codigo(rs.getInt("man_car_codigo"));
				m.setPag_codigo(rs.getInt("man_pag_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return m;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Manutenção!");
		}
	}
	
	public static void Excluir(MManutencao m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL man_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Manutenção!");
		}
	}
	
	public static void Inserir(MManutencao m) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL man_inserir(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, m.getNomeServico());
			stmt.setDate(2, m.getDataManutencao());
			stmt.setDate(3, m.getDataValidade());
			stmt.setInt(4, m.getKmValidade());
			stmt.setInt(5, m.getHodometro());
			stmt.setFloat(6, m.getValorPago());
			stmt.setString(7, m.getAnotacoes());
			stmt.setInt(8, m.getCar_codigo());
			stmt.setInt(9, m.getPag_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Manutenção!");
		}
	}
	
	public static ArrayList<MManutencao> Listar(MCarro c) throws Exception{
		try {
			ArrayList<MManutencao> array = new ArrayList<MManutencao>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MManutencao m;
			
			String sql = "{ CALL man_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				m = new MManutencao();
				m.setCodigo(rs.getInt("man_codigo"));
				m.setNomeServico(rs.getString("man_nomeServico"));
				m.setDataManutencao(rs.getDate("man_dataManutencao"));
				m.setDataValidade(rs.getDate("man_dataValidade"));
				m.setKmValidade(rs.getInt("man_kmValidade"));
				m.setHodometro(rs.getInt("man_hodometro"));
				m.setValorPago(rs.getFloat("man_valorPago"));
				m.setAnotacoes(rs.getString("man_anotacoes"));
				m.setCar_codigo(rs.getInt("man_car_codigo"));
				m.setPag_codigo(rs.getInt("man_pag_codigo"));
				array.add(m);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Manutenções!");
		}
	}
}