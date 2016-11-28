package model;

import java.sql.Date;

public class MMulta {
	private int codigo;
	private float valor;
	private String descricao;
	private Date dataInfracao;
	private Date dataVencimento;
	private int car_codigo;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("Código Inválido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setValor(float valor) throws Exception{
		if(valor <= 0)
			throw new Exception("Valor Inválido!");
		this.valor = valor;
	}
	public float getValor(){
		return this.valor;
	}
	
	public void setDescricao(String descricao) throws Exception{
		if(descricao.equals("") || descricao == null || descricao.length() > 40)
			throw new Exception("Descrição Inválida!");
		this.descricao = descricao;
	}
	public String getDescricao(){
		return this.descricao;
	}
	
	public void setDataInfracao(Date dataInfracao) throws Exception{
		if(dataInfracao == null)
			throw new Exception("Data da Infração Inválida!");
		this.dataInfracao = dataInfracao;
	}
	public Date getDataInfracao(){
		return this.dataInfracao;
	}
	
	public void setDataVencimento(Date dataVencimento) throws Exception{
		if(dataVencimento == null)
			throw new Exception("Data de Vencimento Inválida!");
		this.dataVencimento = dataVencimento;
	}
	public Date getDataVencimento(){
		return this.dataVencimento;
	}
	
	public void setCar_codigo(int car_codigo) throws Exception{
		if(car_codigo <= 0)
			throw new Exception("Código do Carro Inválido!");
		this.car_codigo = car_codigo;
	}
	public int getCar_codigo(){
		return this.car_codigo;
	}
}