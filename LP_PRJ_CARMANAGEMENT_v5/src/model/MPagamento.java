package model;

public class MPagamento {
	private int codigo;
	private String descricao;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("C�digo Inv�lido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setDescricao(String descricao) throws Exception{
		if(descricao.equals("") || descricao == null || descricao.length() > 50)
			throw new Exception("Descri��o Inv�lida!");
		this.descricao = descricao;
	}
	public String getDescricao(){
		return this.descricao;
	}
}