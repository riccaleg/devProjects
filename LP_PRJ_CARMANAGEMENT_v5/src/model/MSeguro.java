package model;

import java.sql.Date;

public class MSeguro {
	private int codigo;
	private String nomeSeguradora;
	private int numApolice;
	private float valor;
	private float valorParcela;
	private int qtdParcelas;
	private int qtdParcelasPagas;
	private Date dataValidade;
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
	
	public void setNomeSeguradora(String nomeSeguradora) throws Exception{
		if(nomeSeguradora.equals("") || nomeSeguradora == null || nomeSeguradora.length() > 20)
			throw new Exception("Nome da Segura Inv�lido!");
		this.nomeSeguradora = nomeSeguradora;
	}
	public String getNomeSeguradora(){
		return this.nomeSeguradora;
	}
	
	//IMPLEMENTAR VALIDA��O DE QUANTIDADE DE CARACTERES
	public void setNumApolice(int numApolice) throws Exception{
		if(numApolice <= 0)
			throw new Exception("N�mero da Ap�lice Inv�lida!");
		this.numApolice = numApolice;
	}
	public int getNumApolice(){
		return this.numApolice;
	}
	
	public void setValor(float valor) throws Exception{
		if(valor <= 0)
			throw new Exception("Valor Inv�lido!");
		this.valor = valor;
	}
	public float getValor(){
		return this.valor;
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
	}
	public int getQtdParcelasPagas(){
		return this.qtdParcelasPagas;
	}
	
	public void setDataValidade(Date dataValidade) throws Exception{
		if(dataValidade == null)
			throw new Exception("Data da Validade");
		this.dataValidade = dataValidade;
	}
	public Date getDataValidade(){
		return this.dataValidade;
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