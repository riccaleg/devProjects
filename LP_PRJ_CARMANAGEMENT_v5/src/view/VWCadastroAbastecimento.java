package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.awt.SystemColor;

public class VWCadastroAbastecimento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**** DECLARA��O COMPONENTES DA TELA ****/

	// DECLARANDO LABELS
	private JLabel lblTituloAba = new JLabel("Cadastro de Abastecimentos");
	private JLabel lblCodigoVW = new JLabel("C�digo");
	private JLabel lblCarro = new JLabel("Carro");
	private JLabel lblHodometro = new JLabel("Hod�metro");
	private JLabel lblMarca = new JLabel("Marca");
	private JLabel lblModelo = new JLabel("Modelo");
	private JLabel lblAnoFabricacao = new JLabel("Ano Fabrica��o");
	private JLabel lblAnoModelo = new JLabel("Ano Modelo");
	private JLabel lblQtdPortas = new JLabel("Qtd. Portas");
	private JLabel lblQtdPassageiros = new JLabel("Qtd. Passageiros");
	private JLabel lblTipoCombustivel = new JLabel("Combust�vel");
	private JLabel lblCapacidadeDoTanque = new JLabel("Cap. Tanque");
	private JLabel lblDescricaoDoCarro = new JLabel("Descri��o do Carro");
	private JLabel lblDataAba = new JLabel("Data Abastecimento");
	private JLabel lblCondutorAba = new JLabel("Condutor");
	private final JLabel lblSituacao = new JLabel(" ");

	// DECLARANDO MASK FORMATTER PARA CAMPO DATA
	private MaskFormatter maskFormatData = new MaskFormatter();
	
	// DECLARANDO CAMPOS PARA INSERIR DADOS
	// JTextField's
	private JTextField txtCodigoAba = new JTextField();
	
	private JTextField txtHodometro = new JTextField();
	private JTextField txtAnoFabricacao = new JTextField();
	private JTextField txtAnoModelo = new JTextField();
	private JTextField txtQtdPortas = new JTextField();
	private JTextField txtQtdPassageiros = new JTextField();
	private JTextField txtCapTanque = new JTextField();
	private JFormattedTextField txtDataCad = new JFormattedTextField(maskFormatData);
	private JTextField txtMarca = new JTextField();
	private JTextField txtModelo = new JTextField();
	// JTextArea
	private JTextArea txtaDescCadCar = new JTextArea();

	// ComboBox
	private JComboBox<String> cboxCarro = new JComboBox<String>();
	private JComboBox<String> cboxTipoCombustivel = new JComboBox<String>();
	private JComboBox<String> cboxCondutorAba = new JComboBox<String>();
	
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
	private JPanel pnlCarro = new JPanel();
	private JPanel pnlCaracteristicas = new JPanel();

	public VWCadastroAbastecimento() throws ParseException {
		super("CAR MANAGEMENT | Cadastro de Carros");

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
		lblDataAba.setEnabled(false);
		txtDataCad.setEnabled(false);
		
		//SETANDO CAMPO "C�DIGO" COMO INATIVO
		lblCodigoVW.setEnabled(false);
		txtCodigoAba.setEnabled(false);

		// CAMPO "CHASSI"
		txtHodometro.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				if (txtHodometro.getText().length() >= 17) {
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
		getContentPane().add(lblTituloAba);

		// ADICIONANDO PAIN�IS NA TELA
		getContentPane().add(pnlReferencia);
		getContentPane().add(pnlCaracteristicas);
		getContentPane().add(pnlCarro);

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
		pnlReferencia.add(lblCodigoVW);
		pnlReferencia.add(lblDataAba);
		// CAMPOS
		pnlReferencia.add(txtCodigoAba);
		pnlReferencia.add(txtDataCad);
		
		//PAINEL "CARROS"
		//LABELs
		pnlCarro.add(lblHodometro);
		pnlCarro.add(lblCarro);
		pnlCarro.add(lblCondutorAba);
		// CAMPOS		
		pnlCarro.add(txtHodometro);
		pnlCarro.add(cboxCarro);
		pnlCarro.add(cboxCondutorAba);
		

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
		// CAMPOS
		pnlCaracteristicas.add(txtAnoFabricacao);
		pnlCaracteristicas.add(txtAnoModelo);
		pnlCaracteristicas.add(txtCapTanque);
		pnlCaracteristicas.add(txtMarca);
		pnlCaracteristicas.add(txtModelo);
		pnlCaracteristicas.add(cboxTipoCombustivel);
		pnlCaracteristicas.add(txtQtdPortas);
		pnlCaracteristicas.add(txtQtdPassageiros);
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
		pnlCarro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Carro",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlCarro.setBounds(255, 63, 563, 103);
		pnlCarro.setLayout(null);

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
		lblTituloAba.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTituloAba.setBounds(214, 11, 405, 41);

		// LABEL "MARCA"
		lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMarca.setBounds(25, 34, 46, 20);

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
		lblCodigoVW.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCodigoVW.setBounds(21, 24, 46, 20);

		// LABEL "MODELO"
		lblModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblModelo.setBounds(324, 33, 46, 20);

		// LABEL "PLACA"
		lblCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCarro.setBounds(186, 26, 40, 22);

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
		lblTipoCombustivel.setBounds(621, 34, 76, 20);

		// LABEL "CHASSI"
		lblHodometro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblHodometro.setBounds(346, 26, 62, 20);

		// LABEL "DATA"
		lblDataAba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDataAba.setBounds(102, 24, 110, 20);

		//LABEL "CONDUTOR"
		lblCondutorAba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCondutorAba.setBounds(25, 26, 62, 22);
		
		// LABEL "SITUACAO" - OCULTA PARA EXIBI��O DE ERROS
		lblSituacao.setBounds(10, 262, 788, 14);
		
		
		

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS CAMPOS e COMBOBOX's
		// CAMPO "C�DIGO"
		txtCodigoAba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCodigoAba.setBounds(21, 45, 65, 22);

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
		txtMarca.setBounds(25, 55, 269, 22);

		// CAMPO "MODELO"
		txtModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtModelo.setBounds(324, 55, 269, 22);

		// CAMPO "QTD PORTAS"
		txtQtdPortas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtQtdPortas.setBounds(25, 174, 98, 22);

		// CAMPO "QTD PASSAGEIROS"
		txtQtdPassageiros.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtQtdPassageiros.setBounds(147, 174, 98, 22);

		// COMBO "COMBUSTIVEL"
		cboxTipoCombustivel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cboxTipoCombustivel.setBackground(Color.WHITE);
		cboxTipoCombustivel.setBounds(621, 55, 153, 22);
		// ITENS TESTE
		cboxTipoCombustivel.addItem(null);
		cboxTipoCombustivel.addItem("�lcool");
		cboxTipoCombustivel.addItem("Gasolina");
		cboxTipoCombustivel.addItem("Flex");
		cboxTipoCombustivel.addItem("Diesel");
		cboxTipoCombustivel.addItem("Teste I");
		cboxTipoCombustivel.addItem("Teste II");
		cboxTipoCombustivel.addItem("Teste III");
		cboxTipoCombustivel.addItem("Teste IV");
		cboxTipoCombustivel.addItem("Teste V");
		
		//COMBO "CONDUTOR ABA"
		cboxCondutorAba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cboxCondutorAba.setBackground(Color.WHITE);
		cboxCondutorAba.setBounds(25, 47, 140, 22);

		// CAMPO "PLACA"
		cboxCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		cboxCarro.setBackground(Color.WHITE);
		cboxCarro.setBounds(186, 47, 140, 22);

		// CAMPO "CHASSI"
		txtHodometro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtHodometro.setBounds(346, 47, 184, 22);
		
		//COMBO
		// CAMPO "DATA"
		txtDataCad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtDataCad.setBounds(102, 45, 98, 22);

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

	// Mostrando Data e Hora Atual no txtDataCad com formata��o dd/MM/yyyy
	// data inclusa no JTxtField apenas como exibi��o no campo.
	// Informa��o real � inserida por atributo NOW() diretamente no banco
	private void dataAtual() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String data = sdf.format(new Date());
		txtDataCad.setValue(data);
	}

	private void DataFormat() throws ParseException {
		maskFormatData.setMask("##/##/####");
		maskFormatData.install(txtDataCad);
		

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
			if (cboxCarro.getSelectedItem().equals("") || txtHodometro.getText().isEmpty()
					|| txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()
					|| txtAnoFabricacao.getText().isEmpty() || txtAnoModelo.getText().isEmpty()
					|| txtQtdPortas.getText().isEmpty() || txtQtdPassageiros.getText().isEmpty()
					|| txtCapTanque.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo *obrigat�rio em branco!", "Erro | Campo(s) Vazio(s)",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (txtHodometro.getText().length() != 17) {	
				txtHodometro.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'CHASSI' inv�lido! Necess�rio 17 d�gitos!",
						"Erro | CHASSI Inv�lido", JOptionPane.ERROR_MESSAGE);
				txtHodometro.setBackground(SystemColor.text);
				txtHodometro.requestFocus();
				return;
				
			} else if (txtAnoFabricacao.getText().length() != 4) {
				txtAnoFabricacao.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'ANO FABRICA��O' inv�lido! Necess�rio 4 d�gitos!",
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
			}

			/* CHAMADA DA INCLUS�O */
			// SE TUDO OCORRER OK - CONFIRMA��O DE INCLUS�O

			JOptionPane.showMessageDialog(this, "Carro inserido com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());
		}

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

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = null;
				try {
					frame = new VWCadastroAbastecimento();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
