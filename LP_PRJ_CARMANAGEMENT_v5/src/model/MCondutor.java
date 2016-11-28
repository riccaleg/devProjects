package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Date;

public class MCondutor {
	private int codigo;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String cpf;
	private String rg;
	private String cnh;
	private String usuario;
	private String senha;
	private Date dataCadastro;
	
	private String nomeCompleto;
	
	public void setCodigo(int codigo) throws Exception {
		if(codigo <= 0)
			throw new Exception("C�digo Inv�lido!");
		this.codigo = codigo;
	}
	public int getCodigo() {
		return this.codigo;
	}
	
	public void setNome(String nome) throws Exception {
		if(nome.isEmpty() || nome.length() > 40)
			throw new Exception("Nome Inv�lido!");
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	
	public void setSobrenome(String sobrenome) throws Exception {
		if(sobrenome.isEmpty() || sobrenome.length() > 40)
			throw new Exception("Sobrenome Inv�lido!");
		this.sobrenome = sobrenome;
	}
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	public void setDataNascimento(Date dataNasciento) throws Exception {
		if(dataNascimento == null)
			throw new Exception("Data de Nasciemnto Inv�lida!");
		this.dataNascimento = dataNasciento;
	}
	public Date getDataNascimento() {
		return this.dataNascimento;
	}
	
	public void setCPF(String cpf) throws Exception {
		if(cpf.isEmpty() || cpf.length() > 14)
			throw new Exception("CPF Inv�lido!");
		this.cpf = cpf;
	}
	public String getCPF() {
		return this.cpf;
	}
	
	public void setRG(String rg) throws Exception {
		if(rg.isEmpty() || rg.length() > 13)
			throw new Exception("RG Inv�lido!");
		this.rg = rg;
	}
	public String getRG() {
		return this.rg;
	}
	
	public void setCNH(String cnh) throws Exception {
		if(cnh.isEmpty() || cnh.length() > 12)
			throw new Exception("CNH Inv�lida!");
		this.cnh = cnh;
	}
	public String getCNH() {
		return this.cnh;
	}
	
	public void setUsuario(String usuario) throws Exception {
		if(usuario.isEmpty() || usuario.length() > 20)
			throw new Exception("Usu�rio Inv�lido!");
		this.usuario = usuario;
	}
	public String getUsuario() {
		return this.usuario;
	}
	
	public void setSenha(String senha) throws Exception {
		if(senha.isEmpty() || senha.length() > 32)
			throw new Exception("Senha Inv�lida!");
		
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0,senha.length());
		
		senha = new BigInteger(1, m.digest()).toString(16);
		String zeros = "";
		
		if(senha.length() < 32) {
			for(int i = 0; i < (32 - senha.length()); i++) {
				zeros += "0";
			}
		}
		
		this.senha = zeros + senha;
	}
	public String getSenha() {
		return this.senha;
	}
	
	public void setDataCadastro(Date dataCadastro) throws Exception {
		if(dataCadastro == null)
			throw new Exception("Data de Cadastro Inv�lida!");
		this.dataCadastro = dataCadastro;
	}
	public Date getDataCadastro() {
		return this.dataCadastro;
	}
	
	public void setNomeCompleto(String nomeCompleto) throws Exception {
		if(nomeCompleto.isEmpty() || nomeCompleto.length() > 80)
			throw new Exception("Nome Completo Inv�lido!");
		this.nomeCompleto = nomeCompleto;
	}
	public String getNomeCompleto() {
		return this.nomeCompleto;
	}
}