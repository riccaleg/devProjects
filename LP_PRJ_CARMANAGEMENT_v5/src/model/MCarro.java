package model;

public class MCarro {
	//ATRIBUTOS DE CARRO
	private int codigo;
	private int con_codigo;
	private String marca;
	private String modelo;
	private String placa;
	private int anoModelo;
	private int anoFabricacao;
	private String numeroChassi;
	private String renavam;
	private int qtdPortas;
	private int qtdPassageiros;
	private float capacidadeTanque;
	private String descricao;
	
	//OUTROS ATRIBUTOS
	private int com_codigo;
	private String com_descricao;
	
	public void setCodigo(int codigo) throws Exception{
		if(codigo <= 0)
			throw new Exception("Código Inválido!");
		this.codigo = codigo;
	}
	public int getCodigo(){
		return this.codigo;
	}
	
	public void setCon_codigo(int con_codigo) throws Exception {
		if(con_codigo <= 0)
			throw new Exception("Código do Condutor Inválido!");
		this.con_codigo = con_codigo;
	}
	public int getCon_codigo() {
		return this.con_codigo;
	}
	
	public void setMarca(String marca) throws Exception{
		if(marca.equals("") || marca == null || marca.length() > 20)
			throw new Exception("Marca Inválida!");
		this.marca = marca;
	}
	public String getMarca(){
		return this.marca;
	}
	
	public void setModelo(String modelo) throws Exception{
		if(modelo.equals("") || modelo == null || modelo.length() > 20)
			throw new Exception("Modelo Inválido!");
		this.modelo = modelo;
	}
	public String getModelo(){
		return this.modelo;
	}
	
	public void setPlaca(String placa) throws Exception{
		if(placa.length() != 8)
			throw new Exception("Placa Inválida!");
		this.placa = placa;
	}
	public String getPlaca(){
		return this.placa;
	}
	
	public void setAnoModelo(int anoModelo) throws Exception{
		if(anoModelo < 1900 || anoModelo > 2030)
			throw new Exception("Ano do Modelo Inválido!");
		this.anoModelo = anoModelo;
	}
	public int getAnoModelo(){
		return this.anoModelo;
	}
	
	public void setAnoFabricacao(int anoFabricacao) throws Exception{
		if(anoFabricacao < 1900 || anoFabricacao > 2030)
			throw new Exception("Ano de Fabricação Inválido!");
		this.anoFabricacao = anoFabricacao;
	}
	public int getAnoFabricacao(){
		return this.anoFabricacao;
	}
	
	public void setNumeroChassi(String numeroChassi) throws Exception{
		if(numeroChassi.length() != 17)
			throw new Exception("Número do Chassi Inválido!");
		this.numeroChassi = numeroChassi;
	}
	public String getNumeroChassi(){
		return this.numeroChassi;
	}
	
	public void setRenavam(String renavam) throws Exception{
		if(renavam.length() != 11)
			throw new Exception("Número do Renavam Inválido!");
		this.renavam = renavam;
	}
	public String getRenavam(){
		return this.renavam;
	}
	
	public void setQtdPortas(int qtdPortas) throws Exception{
		if(qtdPortas <= 0 || qtdPortas >= 6)
			throw new Exception("Quantidade de Portas Inválida!");
		this.qtdPortas = qtdPortas;
	}
	public int getQtdPortas(){
		return this.qtdPortas;
	}
	
	public void setQtdPassageiros(int qtdPassageiros) throws Exception{
		if(qtdPassageiros <= 0 || qtdPassageiros > 20)
			throw new Exception("Quantidade de Passageiros Inválida!");
		this.qtdPassageiros = qtdPassageiros;
	}
	public int getQtdPassageiros(){
		return this.qtdPassageiros;
	}
	
	public void setCapacidadeTanque(float capacidadeTanque) throws Exception{
		if(capacidadeTanque <= 0 || capacidadeTanque > 200)
			throw new Exception("Capacidade do Tanque Inválida!");
		this.capacidadeTanque = capacidadeTanque;
	}
	public float getCapacidadeTanque(){
		return this.capacidadeTanque;
	}
	
	public void setDescricao(String descricao) throws Exception {
		if(descricao.isEmpty() || descricao.length() > 255)
			throw new Exception("Descrição Inválida!");
		this.descricao = descricao;
	}
	public String getDescricao() {
		return this.descricao;
	}
	
	public void setCom_codigo(int com_codigo) throws Exception {
		if(com_codigo <= 0)
			throw new Exception("Código do Combustível Inválido!");
		this.com_codigo = com_codigo;
	}
	public int getCom_codigo(){
		return this.com_codigo;
	}
	
	public void setCom_descricao(String com_descicao) throws Exception {
		if(com_descicao.isEmpty())
			throw new Exception("Descrição do Combustível Inválida!");
		this.com_descricao = com_descicao;
	}
	public String getCom_descricao() {
		return this.com_descricao;
	}
}