package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MImp_Tipo;

public class DImp_tipo {
	public static void Alterar(MImp_Tipo it) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL imp_tipo_alterar(?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, it.getCodigo());
			stmt.setString(2, it.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Tipo de Imposto!");
		}
	}
	
	public static MImp_Tipo BuscarCodigo(MImp_Tipo it) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL imp_tipo_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, it.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				it.setDescricao(rs.getString("imp_tipo_descricao"));
			}
			
			con.close();
			return it;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Tipo de Imposto!");
		}
	}
	
	public static void Excluir(MImp_Tipo it) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL imp_tipo_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, it.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Tipo de Imposto!");
		}
	}
	
	public static void Inserir(MImp_Tipo it) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql ="{ CALL imp_tipo_inserir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, it.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Tipo de Imposto");
		}
	}
	
	public static ArrayList<MImp_Tipo> Listar() throws Exception{
		try {
			ArrayList<MImp_Tipo> array = new ArrayList<MImp_Tipo>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MImp_Tipo it;
			
			String sql = "{ CALL imp_tipo_listar }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				it = new MImp_Tipo();
				it.setCodigo(rs.getInt("imp_tipo_codigo"));
				it.setDescricao(rs.getString("imp_tipo_descricao"));
				array.add(it);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Tipos de Imposto!");
		}
	}
}