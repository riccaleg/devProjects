package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import dao.DCarro;
import dao.DCombustivel;
import dao.DCondutor;
import model.MCarro;
import model.MCombustivel;
import model.MCondutor;

public class VWCadastroCarro extends JFrame {

	private static final long serialVersionUID = 1L;

	// DECLARA��O DE CONSTANTES PARA MODOS
	public static final int VISUALIZAR = 0;
	public static final int ALTERAR = 1;
	public static final int INSERIR = 2;
	@SuppressWarnings("unused")
	private static int MODO;
	private MCarro CARRO;

	/**** DECLARA��O COMPONENTES DA TELA ****/
	// DECLARANDO LABELS
	private JLabel lblTituloCar = new JLabel("Cadastro de Carros");
	private JLabel lblCodigoVWCar = new JLabel("C�digo");
	private JLabel lblPlaca = new JLabel("*Placa");
	private JLabel lblChassi = new JLabel("Chassi");
	private JLabel lblRenavam = new JLabel("Renavam");
	private JLabel lblMarca = new JLabel("*Marca");
	private JLabel lblModelo = new JLabel("*Modelo");
	private JLabel lblAnoFabricacao = new JLabel("Ano Fabrica��o");
	private JLabel lblAnoModelo = new JLabel("Ano Modelo");
	private JLabel lblQtdPortas = new JLabel("Qtd. Portas");
	private JLabel lblQtdPassageiros = new JLabel("Qtd. Passageiros");
	private JLabel lblTipoCombustivel = new JLabel("Combust�vel");
	private JLabel lblCapacidadeDoTanque = new JLabel("Cap. Tanque");
	private JLabel lblDescricaoDoCarro = new JLabel("Descri��o do Carro");
	private JLabel lblCondutorCar = new JLabel("Condutor");
	private JLabel lblDataDeCadastro = new JLabel("Data de Cadastro");
	private final JLabel lblSituacao = new JLabel(" ");

	// DECLARANDO MASK FORMATTER PARA CAMPO DATA
	private MaskFormatter maskFormatData = new MaskFormatter();
	private MaskFormatter maskFormatPlaca = new MaskFormatter();

	// DECLARANDO CAMPOS PARA INSERIR DADOS
	// JTextField's
	private JTextField txtCodigoCar = new JTextField();
	private JFormattedTextField txtPlaca = new JFormattedTextField(maskFormatPlaca);
	private JTextField txtChassi = new JTextField();
	private JTextField txtRenavam = new JTextField();
	private JTextField txtAnoFabricacao = new JTextField();
	private JTextField txtAnoModelo = new JTextField();
	private JTextField txtQtdPortas = new JTextField();
	private JTextField txtQtdPassageiros = new JTextField();
	private JTextField txtCapTanque = new JTextField();
	private JFormattedTextField txtDataCadCar = new JFormattedTextField(maskFormatData);
	private JTextField txtMarca = new JTextField();
	private JTextField txtModelo = new JTextField();

	// JTextArea
	private JTextArea txtaDescCadCar = new JTextArea(); 

	// ComboBox
	private JComboBox<String> cboxTipoCombustivel = new JComboBox<String>();
	private JComboBox<String> cboxCondutorCar = new JComboBox<String>();
	// DECLARANDO BOT�ES DE A��O
	// Buttons
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnExcluir = new JButton("Excluir");
	private JButton btnFechar = new JButton("Fechar");
	
	// DECLARANDO AGRUPADORES
	// Panel's
	private JScrollPane pnlScrTxtArea = new JScrollPane(txtaDescCadCar);
	private JPanel pnlReferencia = new JPanel();
	private JPanel pnlDocumentacao = new JPanel();
	private JPanel pnlCaracteristicas = new JPanel();

	public VWCadastroCarro(int modoFormulario, MCarro mcarro) throws Exception {
		super("CAR MANAGEMENT | Cadastro de Carros");

		if (modoFormulario == INSERIR || mcarro == null || mcarro.getCodigo() <= 0) {
			JOptionPane.showMessageDialog(this, "Erro na chamada do formul�rio, contate o desenvolvedor!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

		// CONFIGURA O MODO DE FUNCIONAMENTO DO FORMUL�RIO
		CARRO = DCarro.BuscarCodigo(mcarro);
		MODO = modoFormulario;
		alterarCampos();

		// IMPEDINDO REDIMENSIONAMENTO
		this.setResizable(false);

		// M�TODO DE PROPRIEDADES DOS ITENS DA TELA
		this.propriedadesItensTela();

		// REGISTRANDO EVENTOS DE CLIQUE
		this.registrarEventos();

		// SETANDO DATA ATUAL PARA EXIBI��O NA TELA
		// DATA REAL, INSERIDA VIA ATRIBUTO NOW() DIRETAMENTE NO BANCO DE DADOS
		this.dataAtual();

		// FORMATANDO DATA
		this.DataFormat();

		// SETANDO TAMANHO DA TELA
		this.setSize(new Dimension(852, 576));

		// COLETANDO TAMANHO DA TELA E DETERMINANDO ABERTURA CENTRALIZADA
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);

		// SETANDO CAMPO DE "DATA" COMO INATIVO
		lblDataDeCadastro.setEnabled(false);
		txtDataCadCar.setEnabled(false);

		// SETANDO CAMPO "C�DIGO" COMO INATIVO
		lblCodigoVWCar.setEnabled(false);
		txtCodigoCar.setEnabled(false);

		// LIMITADORES DE CARACTERES DOS CAMPOS
		// CAMPO RENAVAM
		txtRenavam.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtRenavam.getText().length() >= 11) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});

		// CAMPO "CHASSI"
		txtChassi.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtChassi.getText().length() >= 17) {
					e.consume();
				}
			}
		});

		// CAMPO "ANO FABRICACAO"
		txtAnoFabricacao.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtAnoFabricacao.getText().length() >= 4) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "ANO MODELO"
		txtAnoModelo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtAnoModelo.getText().length() >= 4) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "QTD PORTAS"
		txtQtdPortas.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtQtdPortas.getText().length() >= 1) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "QTD PASSAGEIROS"
		txtQtdPassageiros.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtQtdPassageiros.getText().length() >= 1) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "CAP TANQUE"
		txtCapTanque.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtCapTanque.getText().length() >= 3) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		dadosCarroParaFormulario();
	}

	public VWCadastroCarro(int modoFormulario) throws ParseException {
		super("CAR MANAGEMENT | Cadastro de Carros");

		if (modoFormulario != INSERIR) {
			JOptionPane.showMessageDialog(this, "Erro na chamada do formul�rio, contate o desenvolvedor!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

		MODO = modoFormulario;

		// IMPEDINDO REDIMENSIONAMENTO
		this.setResizable(false);

		// M�TODO DE PROPRIEDADES DOS ITENS DA TELA
		this.propriedadesItensTela();

		// REGISTRANDO EVENTOS DE CLIQUE
		this.registrarEventos();

		// SETANDO DATA ATUAL PARA EXIBI��O NA TELA
		// DATA REAL, INSERIDA VIA ATRIBUTO NOW() DIRETAMENTE NO BANCO DE DADOS
		this.dataAtual();

		// FORMATANDO DATA
		this.DataFormat();

		// SETANDO TAMANHO DA TELA
		this.setSize(new Dimension(852, 576));

		// COLETANDO TAMANHO DA TELA E DETERMINANDO ABERTURA CENTRALIZADA
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);

		// SETANDO CAMPO DE "DATA" COMO INATIVO
		lblDataDeCadastro.setEnabled(false);
		txtDataCadCar.setEnabled(false);

		// SETANDO CAMPO "C�DIGO" COMO INATIVO
		lblCodigoVWCar.setEnabled(false);
		txtCodigoCar.setEnabled(false);

		// LIMITADORES DE CARACTERES DOS CAMPOS
		// CAMPO RENAVAM
		txtRenavam.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtRenavam.getText().length() >= 11) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}

			}
		});

		// CAMPO "CHASSI"
		txtChassi.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtChassi.getText().length() >= 17) {
					e.consume();
				}
			}
		});

		// CAMPO "MARCA"
		txtMarca.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtMarca.getText().length() >= 20) {
					e.consume();
				}
			}
		});

		// CAMPO "MODELO"
		txtModelo.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtModelo.getText().length() >= 20) {
					e.consume();
				}
			}
		});

		// CAMPO "ANO FABRICACAO"
		txtAnoFabricacao.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtAnoFabricacao.getText().length() >= 4) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "ANO MODELO"
		txtAnoModelo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (txtAnoModelo.getText().length() >= 4) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "QTD PORTAS"
		txtQtdPortas.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtQtdPortas.getText().length() >= 1) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "QTD PASSAGEIROS"
		txtQtdPassageiros.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtQtdPassageiros.getText().length() >= 1) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

		// CAMPO "CAP TANQUE"
		txtCapTanque.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtCapTanque.getText().length() >= 3) {
					e.consume();
				}
				String caracteres = "0987654321";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});

	}

	private void propriedadesItensTela() {

		// TIPO DE LAYOUT
		setForeground(new Color(0, 0, 0));
		getContentPane().setLayout(null);

		// ADICIONANDO T�TULO NA TELA
		getContentPane().add(lblTituloCar);

		// ADICIONANDO PAIN�IS NA TELA
		getContentPane().add(pnlReferencia);
		getContentPane().add(pnlCaracteristicas);
		getContentPane().add(pnlDocumentacao);

		// ADICIONANDO BOT�ES NA TELA
		getContentPane().add(btnSalvar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnExcluir);
		getContentPane().add(btnFechar);

		// ADICIONANDO �CONE NO JFRAME
		Image imgJanela = Toolkit.getDefaultToolkit().getImage("img/icon_car.png");
		this.setIconImage(imgJanela);

		/*------------------------------------------------------------------------------*/

		// ADICIONANDO ITENS NOS PAIN�IS
		// PAINEL "REFER�NCIAS"
		// LABEL's
		pnlReferencia.add(lblCodigoVWCar);
		pnlReferencia.add(lblDataDeCadastro);
		// CAMPOS
		pnlReferencia.add(txtCodigoCar);
		pnlReferencia.add(txtDataCadCar);

		// PAINEL "DOCUMENTA��O
		// LABEL's
		pnlDocumentacao.add(lblRenavam);
		pnlDocumentacao.add(lblChassi);
		pnlDocumentacao.add(lblPlaca);
		// CAMPOS
		pnlDocumentacao.add(txtChassi);
		pnlDocumentacao.add(txtPlaca);
		pnlDocumentacao.add(txtRenavam);

		// PAINEL "CARACTER�STICAS"
		// LABEL's
		pnlCaracteristicas.add(lblMarca);
		pnlCaracteristicas.add(lblAnoFabricacao);
		pnlCaracteristicas.add(lblAnoModelo);
		pnlCaracteristicas.add(lblCapacidadeDoTanque);
		pnlCaracteristicas.add(lblModelo);
		pnlCaracteristicas.add(lblQtdPortas);
		pnlCaracteristicas.add(lblQtdPassageiros);
		pnlCaracteristicas.add(lblDescricaoDoCarro);
		pnlCaracteristicas.add(lblTipoCombustivel);
		pnlCaracteristicas.add(lblSituacao);
		pnlCaracteristicas.add(lblCondutorCar);
		// CAMPOS
		pnlCaracteristicas.add(txtAnoFabricacao);
		pnlCaracteristicas.add(txtAnoModelo);
		pnlCaracteristicas.add(txtCapTanque);
		pnlCaracteristicas.add(txtMarca);
		pnlCaracteristicas.add(txtModelo);
		pnlCaracteristicas.add(cboxTipoCombustivel);
		pnlCaracteristicas.add(txtQtdPortas);
		pnlCaracteristicas.add(txtQtdPassageiros);
		pnlCaracteristicas.add(cboxCondutorCar);

		// PAINEL P/ BARRA DE ROLAGEM
		pnlCaracteristicas.add(pnlScrTxtArea);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS PAIN�IS
		// PAINEL "REFER�NCIA"
		pnlReferencia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlReferencia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Refer�ncia",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReferencia.setBounds(10, 63, 222, 103);
		pnlReferencia.setLayout(null);

		// PAINEL "DOCUMENTA��O"
		pnlDocumentacao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Documenta��o",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlDocumentacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlDocumentacao.setBounds(255, 63, 563, 103);
		pnlDocumentacao.setLayout(null);

		// PAINEL "CARACTER�STICAS"
		pnlCaracteristicas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Caracter�sticas",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCaracteristicas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlCaracteristicas.setBounds(10, 184, 808, 287);
		pnlCaracteristicas.setLayout(null);

		// PAINEL SCROLL "DESCRI��O"
		pnlScrTxtArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlScrTxtArea.setBounds(272, 119, 502, 131);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NAS LABELS
		// LABEL "T�TULO TELA"
		lblTituloCar.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTituloCar.setBounds(277, 11, 277, 41);

		// LABEL "MARCA"
		lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMarca.setBounds(272, 34, 40, 20);

		// LABEL "ANO FABRICA��O"
		lblAnoFabricacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAnoFabricacao.setBounds(25, 99, 98, 20);

		// LABEL "ANO MODELO"
		lblAnoModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAnoModelo.setBounds(146, 99, 76, 20);

		// LABEL "CAPACIDADE DO TANQUE"
		lblCapacidadeDoTanque.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCapacidadeDoTanque.setBounds(25, 207, 76, 20);

		// LABEL "C�DIGO"
		lblCodigoVWCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodigoVWCar.setBounds(21, 24, 46, 20);

		// LABEL "MODELO"
		lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblModelo.setBounds(447, 34, 46, 20);

		// LABEL "PLACA"
		lblPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblPlaca.setBounds(21, 24, 40, 22);

		// LABEL "QTD PORTAS"
		lblQtdPortas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblQtdPortas.setBounds(25, 153, 76, 20);

		// LABEL "QTD PASSAGEIROS"
		lblQtdPassageiros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblQtdPassageiros.setBounds(147, 153, 98, 20);

		// LABEL "DESCRI��O"
		lblDescricaoDoCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescricaoDoCarro.setBounds(272, 99, 114, 20);

		// LABEL "TIPO COMBUST�VEL"
		lblTipoCombustivel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblTipoCombustivel.setBounds(623, 34, 76, 20);

		// LABEL "RENAVAM"
		lblRenavam.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblRenavam.setBounds(345, 24, 54, 20);

		// LABEL "CHASSI"
		lblChassi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblChassi.setBounds(138, 24, 40, 20);

		// LABEL "DATA"
		lblDataDeCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDataDeCadastro.setBounds(102, 24, 98, 20);

		// LABEL "CONDUTOR CAR"
		lblCondutorCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCondutorCar.setBounds(25, 34, 57, 20);

		// LABEL "SITUACAO" - OCULTA PARA EXIBI��O DE ERROS
		lblSituacao.setBounds(10, 262, 788, 14);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS CAMPOS e COMBOBOX's
		// CAMPO "C�DIGO"
		txtCodigoCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCodigoCar.setBounds(21, 45, 65, 22);

		// CAMPO "ANO FABRICA��O"
		txtAnoFabricacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtAnoFabricacao.setBounds(25, 120, 98, 22);

		// CAMPO "ANO MODELO"
		txtAnoModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtAnoModelo.setBounds(146, 120, 98, 22);

		// CAMPO "CAPACIDADE TANQUE"
		txtCapTanque.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCapTanque.setBounds(25, 228, 98, 22);
		// txtCapTanque.setColumns(10);

		// COMBO "MARCA"
		txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtMarca.setBounds(272, 55, 156, 22);

		// CAMPO "MODELO"
		txtModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtModelo.setBounds(447, 55, 156, 22);

		// CAMPO "QTD PORTAS"
		txtQtdPortas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtQtdPortas.setBounds(25, 174, 98, 22);

		// CAMPO "QTD PASSAGEIROS"
		txtQtdPassageiros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtQtdPassageiros.setBounds(147, 174, 98, 22);

		// COMBO "COMBUSTIVEL"
		cboxTipoCombustivel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cboxTipoCombustivel.setBackground(Color.WHITE);
		cboxTipoCombustivel.setBounds(623, 55, 151, 22);
		// RETORNANDO ITENS DO BANCO NO COMBO "COMBUST�VEL"
		popularCboxTipoCombustivel();

		/*
		 * ITENS TESTE ** cboxTipoCombustivel.addItem("�lcool");
		 * cboxTipoCombustivel.addItem("Gasolina");
		 * cboxTipoCombustivel.addItem("Flex");
		 * cboxTipoCombustivel.addItem("Diesel");
		 * cboxTipoCombustivel.addItem("Teste I");
		 * cboxTipoCombustivel.addItem("Teste II");
		 * cboxTipoCombustivel.addItem("Teste III");
		 * cboxTipoCombustivel.addItem("Teste IV");
		 * cboxTipoCombustivel.addItem("Teste V");
		 */

		// COMBO "CONDUTOR"
		cboxCondutorCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cboxCondutorCar.setBackground(Color.WHITE);
		cboxCondutorCar.setBounds(25, 55, 220, 22);
		// RETORNANDO ITENS NO BANCO NO COMBO "CONDUTOR"
		popularCboxCondutorCar();

		/*
		 * ITENS TESTE** cboxCondutorCar.addItem("Jo�o Paulo Silva");
		 * cboxCondutorCar.addItem("Paulo Silva");
		 * cboxCondutorCar.addItem("Z� Beto");
		 */

		// CAMPO "PLACA"
		txtPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtPlaca.setBounds(21, 45, 98, 22);

		// CAMPO "CHASSI"
		txtChassi.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtChassi.setBounds(138, 45, 184, 22);

		// CAMPO "RENAVAM"
		txtRenavam.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtRenavam.setBounds(345, 45, 184, 22);

		// CAMPO "DATA"
		txtDataCadCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtDataCadCar.setBounds(102, 45, 98, 22);

		// AREA DE TEXTO "DESCRI��O"
		txtaDescCadCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtaDescCadCar.setLineWrap(true);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS BOT�ES
		// BOT�O "SALVAR"
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSalvar.setBounds(216, 489, 76, 23);

		// BOT�O "ALTERAR"
		btnAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAlterar.setBounds(317, 489, 76, 23);

		// BOT�O "EXCLUIR"
		btnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnExcluir.setBounds(420, 489, 76, 23);

		// BOT�O "FECHAR"
		btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnFechar.setBounds(525, 489, 76, 23);

		/*------------------------------------------------------------------------------*/

		// DETERMINANDO EXIBI��O DE TOOLTIPS NOS BOT�ES
		ToolTipManager.sharedInstance().setInitialDelay(100);
		ToolTipManager.sharedInstance().setDismissDelay(8000);
		btnSalvar.setToolTipText("Salvar dados");
		btnAlterar.setToolTipText("Alterar dados");
		btnExcluir.setToolTipText("Excluir dados");
		btnFechar.setToolTipText("Fechar m�dulo");

	}

	/*------------------------------------------------------------------------------*/

	// MOSTRANDO DATA E HORA ATUAL NO TXTDATACAD COM FORMATA��O DD/MM/YYYY
	// DATA INCLUSA NO JTXTFIELD APENAS COMO EXIBI��O NO CAMPO.
	// INFORMA��O REAL � INSERIDA POR ATRIBUTO NOW() DIRETAMENTE NO BANCO

	private void dataAtual() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(new Date());
		txtDataCadCar.setValue(data);
	}

	private void DataFormat() throws ParseException {
		maskFormatData.setMask("##/##/####");
		maskFormatPlaca.setMask("UUU-####");
		maskFormatPlaca.setPlaceholderCharacter('_');
		maskFormatData.install(txtDataCadCar);
		maskFormatPlaca.install(txtPlaca);

	}
	/*------------------------------------------------------------------------------*/

	// REGISTRANDO EVENTOS DE CLIQUE PARA OS BOT�ES
	private void registrarEventos() {
		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSalvar_click();
			}
		});

		btnAlterar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAlterar_click();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnExcluir_click();
			}
		});

		btnFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnFechar_click();
			}
		});

	}

	/*------------------------------------------------------------------------------*/
	// M�TODO PARA INCLUS�O DE DADOS | BOT�O "SALVAR"
	private void btnSalvar_click() {
		try {
			// OBTENDO ANO ATUAL
			Calendar hoje = Calendar.getInstance();

			// VARI�VEL COM VALOR DE ANO M�NIMO E M�XIMO PARA 'FABRICA��O' E
			// 'MODELO'
			int anoFabModelMin = 1900;

			// VARI�VEL COM CAPACIDADE M�XIMA DO TANQUE
			int capMaxTanque = 150;

			if (txtPlaca.getText().isEmpty() || txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()
					|| txtAnoFabricacao.getText().isEmpty() || txtAnoModelo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo *obrigat�rio em branco!", "Erro | Campo(s) Vazio(s)",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (txtChassi.getText().length() != 17) {
				txtChassi.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'CHASSI' inv�lido! Necess�rio 17 d�gitos!",
						"Erro | CHASSI Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtChassi.setBackground(SystemColor.text);
				txtChassi.requestFocus();
				return;

			} else if (txtRenavam.getText().length() != 11) {
				txtRenavam.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'RENAVAM' inv�lido! Necess�rio 11 d�gitos!",
						"Erro | Renavam Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtRenavam.setBackground(SystemColor.text);
				txtRenavam.requestFocus();
				return;
			} else if (txtAnoFabricacao.getText().length() != 4) {
				txtAnoFabricacao.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'ANO FABRICA��O' inv�lido! Necess�rio 4 d�gitos!",
						"Erro | ANO Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtAnoFabricacao.setBackground(SystemColor.text);
				txtAnoFabricacao.requestFocus();
				return;
			} else if (Integer.parseInt(txtAnoFabricacao.getText()) < anoFabModelMin
					|| Integer.parseInt(txtAnoFabricacao.getText()) > hoje.get(Calendar.YEAR)) {
				txtAnoFabricacao.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this,
						"'ANO FABRICA��O' inv�lido! Necess�rio valor � partir de '" + anoFabModelMin
								+ "' ou menor que '" + hoje.get(Calendar.YEAR) + "'",
						"Erro | ANO Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtAnoFabricacao.setBackground(SystemColor.text);
				txtAnoFabricacao.requestFocus();
				return;
			} else if (txtAnoModelo.getText().length() != 4) {
				txtAnoModelo.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'ANO MODELO' inv�lido! Necess�rio 4 d�gitos!",
						"Erro | ANO Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtAnoModelo.setBackground(SystemColor.text);
				txtAnoModelo.requestFocus();
				return;
			} else if (Integer.parseInt(txtAnoModelo.getText()) < anoFabModelMin
					|| Integer.parseInt(txtAnoModelo.getText()) > hoje.get(Calendar.YEAR)) {
				txtAnoModelo.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(
						this, "'ANO MODELO' inv�lido! Necess�rio valor � partir de '" + anoFabModelMin
								+ "' ou menor que '" + hoje.get(Calendar.YEAR) + "'",
						"Erro | ANO Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtAnoModelo.setBackground(SystemColor.text);
				txtAnoModelo.requestFocus();
				return;
			} else if (Integer.parseInt(txtCapTanque.getText()) > capMaxTanque) {
				txtCapTanque.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this,
						"'CAPACIDADE TANQUE' inv�lida! Necess�rio valor abaixo de '" + capMaxTanque + "'",
						"Erro | Capacidade Tanque Inv�lida", JOptionPane.ERROR_MESSAGE);
				txtCapTanque.setBackground(SystemColor.text);
				txtCapTanque.requestFocus();
				return;
			}

			/* CHAMADA DA INCLUS�O */

			MCarro mcarro = new MCarro();
			MCombustivel mcombustivel = new MCombustivel();
			MCondutor mcondutor = new MCondutor();

			mcondutor.setNomeCompleto(cboxCondutorCar.getSelectedItem().toString());
			mcondutor = DCondutor.GetIdByNomeCompleto(mcondutor);
			mcarro.setCon_codigo(mcondutor.getCodigo());
			mcarro.setPlaca(txtPlaca.getText());
			mcarro.setNumeroChassi(txtChassi.getText());
			mcarro.setRenavam(txtRenavam.getText());
			mcarro.setMarca(txtMarca.getText());
			mcarro.setModelo(txtModelo.getText());
			mcombustivel.setDescricao(cboxTipoCombustivel.getSelectedItem().toString());
			mcombustivel = DCombustivel.GetIdByDescricao(mcombustivel);
			mcarro.setCom_codigo(mcombustivel.getCodigo());
			mcarro.setAnoFabricacao(Integer.parseInt(txtAnoFabricacao.getText()));
			mcarro.setAnoModelo(Integer.parseInt(txtAnoModelo.getText()));
			mcarro.setQtdPortas(Integer.parseInt(txtQtdPortas.getText()));
			mcarro.setQtdPassageiros(Integer.parseInt(txtQtdPassageiros.getText()));
			mcarro.setCapacidadeTanque(Integer.parseInt(txtCapTanque.getText()));
			mcarro.setDescricao(txtaDescCadCar.getText());

			DCarro.Inserir(mcarro);

			// SE TUDO OCORRER OK - CONFIRMA��O DE INCLUS�O
			JOptionPane.showMessageDialog(this, "Carro inserido com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			// DEPOIS DE INSERIDO, O FORMUL�RIO � LIMPO
			limparItensTela();

		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());
		}

	}

	private void limparItensTela() {
		txtPlaca.setText("");
		txtChassi.setText("");
		txtRenavam.setText("");
		txtMarca.setText("");
		txtModelo.setText("");
		txtAnoFabricacao.setText("");
		txtAnoModelo.setText("");
		txtQtdPortas.setText("");
		txtQtdPassageiros.setText("");
		txtCapTanque.setText("");
		txtaDescCadCar.setText("");
		cboxTipoCombustivel.setSelectedIndex(0);
		cboxCondutorCar.setSelectedIndex(0);

	}

	// M�TODO PARA ALTERA��O DE DADOS | BOT�O "ALTERAR"
	private void btnAlterar_click() {
		try {

			int resp = JOptionPane.showConfirmDialog(this, "Confirma altera��o?", "Altera��o",
					JOptionPane.YES_NO_OPTION);
			if (resp == JOptionPane.YES_OPTION) {
				// CHAMADA DA ALTERA��O
				// D�VIDAS DE COMO COLOCAR MENSAGEM DE SUCESSO NA ALTERA��O
			}

		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());

		}
	}

	// M�TODO PARA EXCLUS�O DE DADOS | BOT�O "EXCLUIR"
	private void btnExcluir_click() {
		try {

			int resp = JOptionPane.showConfirmDialog(this, "Confirma a exclus�o?", "Exclus�o",
					JOptionPane.YES_NO_OPTION);
			if (resp == JOptionPane.YES_OPTION) {
				// CHAMADA DA EXCLUS�O
				// D�VIDAS DE COMO COLOCAR MENSAGEM DE SUCESSO NA EXCLUS�O
			}

		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());
		}
	}

	private void btnFechar_click() {
		int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do m�dulo?", "Alerta",
				JOptionPane.YES_NO_OPTION);
		if (resp == JOptionPane.YES_OPTION) {
			dispose();
		}

	}

	private void popularCboxTipoCombustivel() {
		try {
			cboxTipoCombustivel.setModel(DCombustivel.ListarComboBox());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao Listar Combust�veis!\nVerifique sua conex�o com a Internet.",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void popularCboxCondutorCar() {
		try {
			cboxCondutorCar.setModel(DCondutor.ListarComboBox());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Erro ao Listar Condutores!\nVerifique sua conex�o com a Internet.",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void dadosCarroParaFormulario() {
		try {
			// POPULA O FORMUL�RIO COM OS DADOS DO CARRO RECEBIDO;
			MCondutor mcondutor = new MCondutor();

			mcondutor.setCodigo(CARRO.getCon_codigo());
			mcondutor = DCondutor.BuscarCodigo(mcondutor);

			txtCodigoCar.setText(String.valueOf(CARRO.getCodigo()));
			txtPlaca.setText(CARRO.getPlaca());
			txtChassi.setText(CARRO.getNumeroChassi());
			txtRenavam.setText(CARRO.getRenavam());
			cboxCondutorCar.setSelectedItem(mcondutor.getNomeCompleto());
			txtMarca.setText(CARRO.getMarca());
			txtModelo.setText(CARRO.getModelo());
			cboxTipoCombustivel.setSelectedItem(CARRO.getCom_descricao());
			txtAnoFabricacao.setText(String.valueOf(CARRO.getAnoFabricacao()));
			txtAnoModelo.setText(String.valueOf(CARRO.getAnoModelo()));
			txtQtdPortas.setText(String.valueOf(CARRO.getQtdPortas()));
			txtQtdPassageiros.setText(String.valueOf(CARRO.getQtdPassageiros()));
			txtCapTanque.setText(String.valueOf(CARRO.getCapacidadeTanque()));
			txtaDescCadCar.setText(CARRO.getDescricao());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	// DEFINE O ACESSO AOS CAMPOS E BOT�ES DE ACORDO COM O MODO DO FORMUL�RIO
	private void alterarCampos() {

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = null;
				try {
					frame = new VWCadastroCarro(VWCadastroCarro.INSERIR);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
