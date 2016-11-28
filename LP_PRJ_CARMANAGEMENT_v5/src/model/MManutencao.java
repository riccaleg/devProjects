package model;

import java.sql.Date;

public class MManutencao {
	private int codigo;
	private String nomeServico;
	private Date dataManutencao;
	private Date dataValidade;
	private int kmValidade;
	private int hodometro;
	private float valorPago;
	private String anotacoes;
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
	
	public void setNomeServico(String nomeServico) throws Exception{
		if(nomeServico.equals("") || nomeServico == null || nomeServico.length() > 60)
			throw new Exception("Nome do Serviço Inválido!");
		this.nomeServico = nomeServico;
	}
	public String getNomeServico(){
		return this.nomeServico;
	}
	
	public void setDataManutencao(Date dataManutencao) throws Exception{
		if(dataManutencao == null)
			throw new Exception("Data da Manutenção Inválida!");
		this.dataManutencao = dataManutencao;
	}
	public Date getDataManutencao(){
		return this.dataManutencao;
	}
	
	public void setDataValidade(Date dataValidade) throws Exception{
		if(dataValidade == null)
			throw new Exception("Data de Validade Inválida!");
		this.dataValidade = dataValidade;
	}
	public Date getDataValidade(){
		return this.dataValidade;
	}
	
	public void setKmValidade(int kmValidade) throws Exception{
		if(kmValidade <= 0)
			throw new Exception("KMs de Validade Inválidos!");
		this.kmValidade = kmValidade;
	}
	public int getKmValidade(){
		return this.kmValidade;
	}
	
	public void setHodometro(int hodometro) throws Exception{
		if(hodometro <= 0)
			throw new Exception("^Valor do Hodômetro Incorreto!");
		this.hodometro = hodometro;
	}
	public int getHodometro(){
		return this.hodometro;
	}
	
	public void setValorPago(float valorPago) throws Exception{
		if(valorPago <= 0)
			throw new Exception("Valor Pago Incorreto!");
		this.valorPago = valorPago;
	}
	public float getValorPago(){
		return this.valorPago;
	}
	
	public void setAnotacoes(String anotacoes) throws Exception{
		if(anotacoes.equals("") || anotacoes == null || anotacoes.length() > 100)
			throw new Exception("Anotações Incorreto!");
		this.anotacoes = anotacoes;
	}
	public String getAnotacoes(){
		return this.anotacoes;
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