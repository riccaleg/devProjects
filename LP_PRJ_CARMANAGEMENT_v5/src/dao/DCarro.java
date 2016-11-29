package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;

public class DCarro {
	
	public static void Alterar(MCarro c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL car_alterar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			stmt.setString(2, c.getMarca());
			stmt.setString(3,  c.getModelo());
			stmt.setString(4, c.getPlaca());
			stmt.setInt(5, c.getAnoModelo());
			stmt.setInt(6,  c.getAnoFabricacao());
			stmt.setString(7, c.getNumeroChassi());
			stmt.setString(8, c.getRenavam());
			stmt.setInt(9, c.getQtdPortas());
			stmt.setInt(10,  c.getQtdPassageiros());
			stmt.setFloat(11, c.getCapacidadeTanque());
			stmt.setString(12, c.getDescricao());
			stmt.setInt(13, c.getCom_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Carro!");
		}
	}
	
	public static MCarro BuscarCodigo(MCarro c) {
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL car_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				c.setCodigo(rs.getInt("car_codigo"));
				c.setCon_codigo(rs.getInt("car_con_codigo"));
				c.setMarca(rs.getString("car_marca"));
				c.setModelo(rs.getString("car_modelo"));
				c.setPlaca(rs.getString("car_placa"));
				c.setAnoModelo(rs.getInt("car_anoModelo"));
				c.setAnoFabricacao(rs.getInt("car_anoFabricacao"));
				c.setNumeroChassi(rs.getString("car_numeroChassi"));
				c.setRenavam(rs.getString("car_renavam"));
				c.setQtdPortas(rs.getInt("car_qtdPortas"));
				c.setQtdPassageiros(rs.getInt("car_qtdPassageiros"));
				c.setCapacidadeTanque(rs.getFloat("car_capacidadeTanque"));
				c.setDescricao(rs.getString("car_descricao"));
				c.setCom_codigo(rs.getInt("com_codigo"));
				c.setCom_descricao(rs.getString("com_descricao"));
			} else{
				throw new Exception();
			}
			
			con.close();
			return c;
		} catch (Exception e) {
			return c;
		}
	}
	//MÉTODO PARA EXCLSÃO
	public static void Excluir(MCarro c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL car_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Carro!");
		}
	}
	
	public static void Inserir(MCarro c) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL car_inserir(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCon_codigo());
			stmt.setString(2, c.getMarca());
			stmt.setString(3,  c.getModelo());
			stmt.setString(4, c.getPlaca());
			stmt.setInt(5, c.getAnoModelo());
			stmt.setInt(6,  c.getAnoFabricacao());
			stmt.setString(7, c.getNumeroChassi());
			stmt.setString(8, c.getRenavam());
			stmt.setInt(9, c.getQtdPortas());
			stmt.setInt(10,  c.getQtdPassageiros());
			stmt.setFloat(11, c.getCapacidadeTanque());
			stmt.setString(12, c.getDescricao());
			stmt.setInt(13, c.getCom_codigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Carro!");
		}
	}
	
	public static ArrayList<MCarro> Listar() throws Exception{
		try {
			ArrayList<MCarro> array = new ArrayList<MCarro>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MCarro c;
			
			String sql = "{ CALL car_listar }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				c = new MCarro();
				c.setCodigo(rs.getInt("car_codigo"));
				c.setMarca(rs.getString("car_marca"));
				c.setModelo(rs.getString("car_modelo"));
				c.setPlaca(rs.getString("car_placa"));
				c.setAnoModelo(rs.getInt("car_anoModelo"));
				c.setAnoFabricacao(rs.getInt("car_anoFabricacao"));
				c.setNumeroChassi(rs.getString("car_numeroChassi"));
				c.setRenavam(rs.getString("car_renavam"));
				c.setQtdPortas(rs.getInt("car_qtdPortas"));
				c.setQtdPassageiros(rs.getInt("car_qtdPassageiros"));
				c.setCapacidadeTanque(rs.getFloat("car_capacidadeTanque"));
				c.setDescricao(rs.getString("car_descricao"));
				c.setCom_descricao(rs.getString("com_descricao"));
				array.add(c);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Carros!");
		}
	}
}