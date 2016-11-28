package model;

import java.sql.Date;

public class MFinanciamento {
	private int codigo;
	private float jurosMensal;
	private float valorSemJuros;
	private float valorParcela;
	private int qtdParcelas;
	private int qtdParcelasPagas;
	private Date dataPrimeiraParcela;
	private int car_codigo;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("C�digo Inv�lido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setJurosMensal(float jurosMensal) throws Exception{
		if(jurosMensal < 0)
			throw new Exception("Juros Mensal Inv�lido!");
		this.jurosMensal = jurosMensal;
	}
	public float getJurosMensal(){
		return this.jurosMensal;
	}
	
	public void setValorSemJuros(float valorSemJuros) throws Exception{
		if(valorSemJuros <= 0)
			throw new Exception("Valor Sem Juros Inv�lido!");
		this.valorSemJuros = valorSemJuros;
	}
	public float getValorSemJuros(){
		return this.valorSemJuros;
	}
	
	public void setValorParcela(float valorParcela) throws Exception{
		if(valorParcela <= 0)
			throw new Exception("Valor da Parcela Inv�lido!");
		this.valorParcela = valorParcela;
	}
	public float getValorParcela(){
		return this.valorParcela;
	}
	
	public void setQtdParcelas(int qtdParcelas) throws Exception{
		if(qtdParcelas <= 0)
			throw new Exception("Quantidade de Parcelas Inv�lida!");
		this.qtdParcelas = qtdParcelas;
	}
	public int getQtdParcelas(){
		return this.qtdParcelas;
	}
	
	public void setQtdParcelasPagas(int qtdParcelasPagas) throws Exception{
		if(qtdParcelasPagas <= 0)
			throw new Exception("Quantidade de Parcelas Pagas Inv�lida!");
		this.qtdParcelasPagas = qtdParcelasPagas;
	}
	public int getQtdParcelasPagas(){
		return this.qtdParcelasPagas;
	}
	
	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) throws Exception{
		if(dataPrimeiraParcela == null)
			throw new Exception("Data da Primeira Parcela Inv�lida!");
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}
	public Date getDataPrimeiraParcela(){
		return this.dataPrimeiraParcela;
	}
	
	public void setCar_codigo(int car_codigo) throws Exception{
		if(car_codigo <= 0)
			throw new Exception("C�digo do Carro Inv�lido!");
		this.car_codigo = car_codigo;
	}
	public int getCar_codigo(){
		return this.car_codigo;
	}
}