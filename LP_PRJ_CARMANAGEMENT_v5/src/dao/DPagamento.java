package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import controller.Conexao;
import model.MCarro;
import model.MPagamento;

public class DPagamento {
	public static void Alterar(MPagamento p) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL pag_alterar(?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			stmt.setString(2, p.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Pagamento!");
		}
	}
	
	public static MPagamento BuscarCodigo(MPagamento p) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL pag_buscar_codigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				p.setDescricao(rs.getString("pag_descricao"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return p;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Pagamento!");
		}
	}
	
	public static void Excluir(MPagamento p) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL pag_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, p.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluri Pagamento!");
		}
	}
	
	public static void Inserir(MPagamento p) throws Exception{
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL pag_inserir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getDescricao());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Pagamento!");
		}
	}
	
	public static ArrayList<MPagamento> Listar(MCarro c) throws Exception {
		try {
			ArrayList<MPagamento> array = new ArrayList<MPagamento>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			MPagamento p;
			
			String sql = "{ CALL pag_listar(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				p = new MPagamento();
				p.setCodigo(rs.getInt("pag_codigo"));
				p.setDescricao(rs.getString("pag_descricao"));
				array.add(p);
			}
			
			con.close();
			return array;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Pagamentos!");
		}
	}
}