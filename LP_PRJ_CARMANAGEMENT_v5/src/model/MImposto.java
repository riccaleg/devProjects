package model;

import java.util.Date;

public class MImposto {
	private int codigo;
	private int ano;
	private float valorImposto;
	private float valorParcela;
	private int qtdParcelas;
	private int qtdParcelasPagas;
	private Date dataPrimeiraParcela;
	private int imp_tipo_codigo;
	private int car_codigo;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("Código Inválido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setAno(int ano) throws Exception{
		if(ano < 1900 || ano > 2030)
			throw new Exception("Ano Inválido!");
		this.ano = ano;
	}
	public int getAno(){
		return this.ano;
	}
	
	public void setValorImposto(float valorImposto) throws Exception{
		if(valorImposto <= 0)
			throw new Exception("Valor do Imposto Inválido!");
		this.valorImposto = valorImposto;
	}
	public float getValorImposto(){
		return this.valorImposto;
	}
	
	public void setValorParcela(float valorParcela) throws Exception{
		if(valorParcela <= 0)
			throw new Exception("Valor da Parcela Inválido!");
		this.valorParcela = valorParcela;
	}
	public float getValorParcela(){
		return this.valorParcela;
	}
	
	public void setQtdParcelas(int qtdParcelas) throws Exception{
		if(qtdParcelas <= 0)
			throw new Exception("Quantidade de Parcelas Inválida!");
		this.qtdParcelas = qtdParcelas;
	}
	public int getQtdParcelas(){
		return this.qtdParcelas;
	}
	
	public void setQtdParcelasPagas(int qtdParcelasPagas) throws Exception{
		if(qtdParcelasPagas <= 0)
			throw new Exception("Quantidade de Parcelas Pagas Inválida!");
		this.qtdParcelasPagas = qtdParcelasPagas;
	}
	public int getQtdParcelasPagas(){
		return this.qtdParcelasPagas;
	}
	
	public void setDataPrimeiraParcela(Date dataPrimeiraParcela) throws Exception{
		if(dataPrimeiraParcela == null)
			throw new Exception("Data da Primeira Parcela Inválida!");
		this.dataPrimeiraParcela = dataPrimeiraParcela;
	}
	public Date getDataPrimeiraParcela(){
		return this.dataPrimeiraParcela;
	}
	
	public void setImp_tipo_codigo(int imp_tipo_codigo) throws Exception{
		if(imp_tipo_codigo <= 0)
			throw new Exception("Código do Tipo de Imposto Inválido!");
		this.imp_tipo_codigo = imp_tipo_codigo;
	}
	public int getImp_tipo_codigo(){
		return this.imp_tipo_codigo;
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