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
	/**** DECLARAÇÃO COMPONENTES DA TELA ****/

	// DECLARANDO LABELS
	private JLabel lblTituloAba = new JLabel("Cadastro de Abastecimentos");
	private JLabel lblCodigoVW = new JLabel("Código");
	private JLabel lblCarro = new JLabel("Carro");
	private JLabel lblHodometro = new JLabel("Hodômetro");
	private JLabel lblMarca = new JLabel("Marca");
	private JLabel lblModelo = new JLabel("Modelo");
	private JLabel lblAnoFabricacao = new JLabel("Ano Fabricação");
	private JLabel lblAnoModelo = new JLabel("Ano Modelo");
	private JLabel lblQtdPortas = new JLabel("Qtd. Portas");
	private JLabel lblQtdPassageiros = new JLabel("Qtd. Passageiros");
	private JLabel lblTipoCombustivel = new JLabel("Combustível");
	private JLabel lblCapacidadeDoTanque = new JLabel("Cap. Tanque");
	private JLabel lblDescricaoDoCarro = new JLabel("Descrição do Carro");
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
	
	// DECLARANDO BOTÕES DE AÇÃO
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

		// MÉTODO DE PROPRIEDADES DOS ITENS DA TELA
		this.propriedadesItensTela();

		// REGISTRANDO EVENTOS DE CLIQUE
		this.registrarEventos();

		// SETANDO DATA ATUAL PARA EXIBIÇÃO NA TELA
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
		
		//SETANDO CAMPO "CÓDIGO" COMO INATIVO
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

		// ADICIONANDO TÍTULO NA TELA
		getContentPane().add(lblTituloAba);

		// ADICIONANDO PAINÉIS NA TELA
		getContentPane().add(pnlReferencia);
		getContentPane().add(pnlCaracteristicas);
		getContentPane().add(pnlCarro);

		// ADICIONANDO BOTÕES NA TELA
		getContentPane().add(btnSalvar);
		getContentPane().add(btnAlterar);
		getContentPane().add(btnExcluir);
		getContentPane().add(btnFechar);

		// ADICIONANDO ÍCONE NO JFRAME
		Image imgJanela = Toolkit.getDefaultToolkit().getImage("img/icon_car.png");
		this.setIconImage(imgJanela);

		/*------------------------------------------------------------------------------*/

		// ADICIONANDO ITENS NOS PAINÉIS
		// PAINEL "REFERÊNCIAS"
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
		

		// PAINEL "CARACTERÍSTICAS"
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

		// SETANDO PROPRIEDADES NOS PAINÉIS
		// PAINEL "REFERÊNCIA"
		pnlReferencia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlReferencia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Referência",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReferencia.setBounds(10, 63, 222, 103);
		pnlReferencia.setLayout(null);

		// PAINEL "DOCUMENTAÇÃO"
		pnlCarro.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Carro",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlCarro.setBounds(255, 63, 563, 103);
		pnlCarro.setLayout(null);

		// PAINEL "CARACTERÍSTICAS"
		pnlCaracteristicas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Características",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCaracteristicas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlCaracteristicas.setBounds(10, 184, 808, 287);
		pnlCaracteristicas.setLayout(null);

		// PAINEL SCROLL "DESCRIÇÃO"
		pnlScrTxtArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		pnlScrTxtArea.setBounds(272, 119, 502, 131);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NAS LABELS
		// LABEL "TÍTULO TELA"
		lblTituloAba.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTituloAba.setBounds(214, 11, 405, 41);

		// LABEL "MARCA"
		lblMarca.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblMarca.setBounds(25, 34, 46, 20);

		// LABEL "ANO FABRICAÇÃO"
		lblAnoFabricacao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAnoFabricacao.setBounds(25, 99, 98, 20);

		// LABEL "ANO MODELO"
		lblAnoModelo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblAnoModelo.setBounds(146, 99, 76, 20);

		// LABEL "CAPACIDADE DO TANQUE"
		lblCapacidadeDoTanque.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblCapacidadeDoTanque.setBounds(25, 207, 76, 20);

		// LABEL "CÓDIGO"
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

		// LABEL "DESCRIÇÃO"
		lblDescricaoDoCarro.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblDescricaoDoCarro.setBounds(272, 99, 114, 20);

		// LABEL "TIPO COMBUSTÍVEL"
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
		
		// LABEL "SITUACAO" - OCULTA PARA EXIBIÇÃO DE ERROS
		lblSituacao.setBounds(10, 262, 788, 14);
		
		
		

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS CAMPOS e COMBOBOX's
		// CAMPO "CÓDIGO"
		txtCodigoAba.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtCodigoAba.setBounds(21, 45, 65, 22);

		// CAMPO "ANO FABRICAÇÃO"
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
		cboxTipoCombustivel.addItem("Álcool");
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

		// AREA DE TEXTO "DESCRIÇÃO"
		txtaDescCadCar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtaDescCadCar.setLineWrap(true);

		/*------------------------------------------------------------------------------*/

		// SETANDO PROPRIEDADES NOS BOTÕES
		// BOTÃO "SALVAR"
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnSalvar.setBounds(216, 489, 76, 23);

		// BOTÃO "ALTERAR"
		btnAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnAlterar.setBounds(317, 489, 76, 23);

		// BOTÃO "EXCLUIR"
		btnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnExcluir.setBounds(420, 489, 76, 23);
		
		// BOTÃO "FECHAR"
		btnFechar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnFechar.setBounds(525, 489, 76, 23);

		/*------------------------------------------------------------------------------*/

		// DETERMINANDO EXIBIÇÃO DE TOOLTIPS NOS BOTÕES
		ToolTipManager.sharedInstance().setInitialDelay(100);
		ToolTipManager.sharedInstance().setDismissDelay(8000);
		btnSalvar.setToolTipText("Salvar dados");
		btnAlterar.setToolTipText("Alterar dados");
		btnExcluir.setToolTipText("Excluir dados");
		btnFechar.setToolTipText("Fechar módulo");

	}

	/*------------------------------------------------------------------------------*/

	// Mostrando Data e Hora Atual no txtDataCad com formatação dd/MM/yyyy
	// data inclusa no JTxtField apenas como exibição no campo.
	// Informação real é inserida por atributo NOW() diretamente no banco
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

	// REGISTRANDO EVENTOS DE CLIQUE PARA OS BOTÕES
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
	// MÉTODO PARA INCLUSÃO DE DADOS | BOTÃO "SALVAR"
	private void btnSalvar_click() {
		try {
			if (cboxCarro.getSelectedItem().equals("") || txtHodometro.getText().isEmpty()
					|| txtMarca.getText().isEmpty() || txtModelo.getText().isEmpty()
					|| txtAnoFabricacao.getText().isEmpty() || txtAnoModelo.getText().isEmpty()
					|| txtQtdPortas.getText().isEmpty() || txtQtdPassageiros.getText().isEmpty()
					|| txtCapTanque.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Campo *obrigatório em branco!", "Erro | Campo(s) Vazio(s)",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (txtHodometro.getText().length() != 17) {	
				txtHodometro.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'CHASSI' inválido! Necessário 17 dígitos!",
						"Erro | CHASSI Inválido", JOptionPane.ERROR_MESSAGE);
				txtHodometro.setBackground(SystemColor.text);
				txtHodometro.requestFocus();
				return;
				
			} else if (txtAnoFabricacao.getText().length() != 4) {
				txtAnoFabricacao.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'ANO FABRICAÇÃO' inválido! Necessário 4 dígitos!",
						"Erro | ANO Inválido", JOptionPane.ERROR_MESSAGE);
				txtAnoFabricacao.setBackground(SystemColor.text);
				txtAnoFabricacao.requestFocus();
				return;
			} else if (txtAnoModelo.getText().length() != 4) {
				txtAnoModelo.setBackground(SystemColor.info);
				JOptionPane.showMessageDialog(this, "'ANO MODELO' inválido! Necessário 4 dígitos!",
						"Erro | ANO Inválido", JOptionPane.ERROR_MESSAGE);
				txtAnoModelo.setBackground(SystemColor.text);	
				txtAnoModelo.requestFocus();
				return;
			}

			/* CHAMADA DA INCLUSÃO */
			// SE TUDO OCORRER OK - CONFIRMAÇÃO DE INCLUSÃO

			JOptionPane.showMessageDialog(this, "Carro inserido com sucesso!", "Sucesso",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());
		}

	}

	// MÉTODO PARA ALTERAÇÂO DE DADOS | BOTÃO "ALTERAR"
	private void btnAlterar_click() {
		try {

			int resp = JOptionPane.showConfirmDialog(this, "Confirma alteração?", "Alteração",
					JOptionPane.YES_NO_OPTION);
			if (resp == JOptionPane.YES_OPTION) {
				// CHAMADA DA ALTERAÇÃO
				// DÚVIDAS DE COMO COLOCAR MENSAGEM DE SUCESSO NA ALTERAÇÃO
			}

		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());

		}
	}

	// MÉTODO PARA EXCLUSÃO DE DADOS | BOTÃO "EXCLUIR"
	private void btnExcluir_click() {
		try {

			int resp = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Exclusão",
					JOptionPane.YES_NO_OPTION);
			if (resp == JOptionPane.YES_OPTION) {
				// CHAMADA DA EXCLUSÃO
				// DÚVIDAS DE COMO COLOCAR MENSAGEM DE SUCESSO NA EXCLUSÃO
			}

		} catch (Exception e) {
			lblSituacao.setText(e.getMessage());
		}
	}

	private void btnFechar_click() {
		int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do módulo?", "Alerta",
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
