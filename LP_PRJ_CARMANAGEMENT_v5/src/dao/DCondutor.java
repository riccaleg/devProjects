package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;

import controller.Conexao;
import model.MCondutor;

public class DCondutor {
	public static void Alterar(MCondutor c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL con_alterar(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			stmt.setString(2, c.getNome());
			stmt.setString(3, c.getSobrenome());
			stmt.setDate(4, c.getDataNascimento());
			stmt.setString(5, c.getCPF());
			stmt.setString(6, c.getRG());
			stmt.setString(7, c.getCNH());
			stmt.setString(8, c.getUsuario());
			stmt.setString(9, c.getSenha());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Alterar Condutor!");
		}
	}
	
	public static MCondutor BuscarCodigo(MCondutor c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL con_buscarCodigo(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				c.setCodigo(rs.getInt("con_codigo"));
				c.setNome(rs.getString("con_nome"));
				c.setSobrenome(rs.getString("con_sobrenome"));
				c.setDataNascimento(rs.getDate("con_dataNascimento"));
				c.setCPF(rs.getString("con_cpf"));
				c.setRG(rs.getString("con_rg"));
				c.setCNH(rs.getString("con_cnh"));
				c.setDataCadastro(rs.getDate("con_dataCadastro"));
				c.setNomeCompleto(rs.getString("con_nome") + " " + rs.getString("con_sobrenome"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return c;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Condutor!");
		}
	}
	
	public static void Excluir(MCondutor c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL con_excluir(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getCodigo());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Excluir Condutor!");
		}
	}
	
	public static MCondutor GetIdByNomeCompleto(MCondutor c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL con_getIdByNomeCompleto(?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNomeCompleto());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				c.setCodigo(rs.getInt("con_codigo"));
			} else {
				throw new Exception();
			}
			
			con.close();
			return c;
		} catch (Exception e) {
			throw new Exception("Erro ao Buscar Código de Condutor!");
		}
	}
	
	public static void Inserir(MCondutor c) throws Exception {
		try {
			Connection con = Conexao.getConexao();
			
			String sql = "{ CALL con_inserir(?, ?, ?, ?, ?, ?, ?, ?) }";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getSobrenome());
			stmt.setDate(3, c.getDataNascimento());
			stmt.setString(4, c.getCPF());
			stmt.setString(5, c.getRG());
			stmt.setString(6, c.getCNH());
			stmt.setString(7, c.getUsuario());
			stmt.setString(8, c.getSenha());
			
			stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao Inserir Condutor!");
		}
	}
	
	public static DefaultComboBoxModel<String> ListarComboBox() throws Exception {
		try {
			DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>();
			Connection con = Conexao.getConexao();
			ResultSet rs;
			
			String sql = "{ CALL con_listar }";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				modelo.addElement("" + rs.getString("con_nome") + " " + rs.getString("con_sobrenome"));
			}
			
			con.close();
			return modelo;
		} catch (Exception e) {
			throw new Exception("Erro ao Listar Condutores!");
		}
	}
}