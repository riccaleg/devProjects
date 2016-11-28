package model;

import java.sql.Date;

public class MDespesa {
	private int codigo;
	private Date data;
	private float custo;
	private int car_codigo;
	private int pag_codigo;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("C�digo Inv�lido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setData(Date data) throws Exception{
		if(data == null)
			throw new Exception("Data Inv�lida!");
		this.data = data;
	}
	public Date getData(){
		return this.data;
	}
	
	public void setCusto(float custo) throws Exception{
		if(custo <= 0)
			throw new Exception("Custo Inv�lido!");
		this.custo = custo;
	}
	public float getCusto(){
		return this.custo;
	}
	
	public void setCar_codigo(int car_codigo) throws Exception{
		if(car_codigo <= 0)
			throw new Exception("C�digo do Carro Inv�lido!");
		this.car_codigo = car_codigo;
	}
	public int getCar_codigo(){
		return this.car_codigo;
	}
	
	public void setPag_codigo(int pag_codigo) throws Exception{
		if(pag_codigo <= 0)
			throw new Exception("C�digo do Pagamento Inv�lido!");
		this.pag_codigo = pag_codigo;
	}
	public int getPag_codigo(){
		return this.pag_codigo;
	}
}