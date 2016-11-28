package model;

import java.sql.Date;

public class MAbastecimento {
	private int codigo;
	private Date data;
	private int hodometro;
	private float valorLitro;
	private float valorTotal;
	private int car_codigo;
	private int pag_codigo;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("Código Inválido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setData(Date data) throws Exception{
		if(data == null)
			throw new Exception("Data Inválida!");
		this.data = data;
	}
	public Date getData(){
		return this.data;
	}
	
	public void setHodometro(int hodometro) throws Exception{
		if(hodometro < 0)
			throw new Exception("Valor do Hodômetro Inválido!");
		this.hodometro = hodometro;
	}
	public int getHodometro(){
		return this.hodometro;
	}
	
	public void setValorLitro(float valorLitro) throws Exception{
		if(valorLitro <= 0)
			throw new Exception("Valor do Litro Inválido!");
		this.valorLitro = valorLitro;
	}
	public float getValorLitro(){
		return this.valorLitro;
	}
	
	public void setValorTotal(float valorTotal) throws Exception{
		if(valorTotal <= 0)
			throw new Exception("Valor Total Inválido!");
		this.valorTotal = valorTotal;
	}
	public float getValorTotal(){
		return this.valorTotal;
	}
	
	public void setCar_codigo(int car_codigo) throws Exception{
		if(car_codigo <= 0)
			throw new Exception("Código do Carro Inválido!");
		this.car_codigo = car_codigo;
	}
	public int getCar_codigo(){
		return this.car_codigo;
	}
	
	public void setPag_codigo(int pag_codigo) throws Exception{
		if(pag_codigo <= 0)
			throw new Exception("Código do Pagamento Inválido!");
		this.pag_codigo = pag_codigo;
	}
	public int getPag_codigo(){
		return this.pag_codigo;
	}
}