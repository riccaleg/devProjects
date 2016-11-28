package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class VWMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// determinando JMenu e seus itens
	private JMenuBar menu = new JMenuBar();
	private JMenu cadastro = new JMenu("Cadastros");
	private JMenu listas = new JMenu("Listas");
	private JMenu sair = new JMenu("Sair");

	// DETERMINANDO ITENS DO MENU
	// CADASTRAR
	private JMenuItem itemCadastrarCondutor = new JMenuItem("Cadastrar Condutor");
	private JMenuItem itemCadastrarCarro = new JMenuItem("Cadastrar Carro");
	private JMenuItem itemCadastrarAbastecimento = new JMenuItem("Cadastrar Abastecimento");
	private JMenuItem itemCadastrarManutencao = new JMenuItem("Cadastrar Manutenção");

	// LISTAR
	private JMenuItem itemListarCondutores = new JMenuItem("Listar Condutores");
	private JMenuItem itemListarCarros = new JMenuItem("Listar Carros");
	private JMenuItem itemListarAbastecimentos = new JMenuItem("Listar Abastecimentos");
	private JMenuItem itemListarManutencao = new JMenuItem("Listar Manutenções");

	// SAIR
	private JMenuItem itemSair = new JMenuItem("Sair");

	// DETERMINANDO JFRAMES
	// CADASTROS
	private JFrame JFVWCadCondutores;
	private JFrame JFVWCadCarros;
	private JFrame JFVWCadAbastecimentos;
	private JFrame JFVWCadManutencao;
	// LISTAS
	private JFrame JFVWLstCondutores;
	private JFrame JFVWLstCarros;
	private JFrame JFVWLstAbastecimentos;
	private JFrame JFVWLstManutencao;

	// DETERMINANDO LABEL DO ÍCONE DE LOGO, MSG e DATA&HORA
	private Icon imgLogo = new ImageIcon("img/icon_logo.png");
	private JLabel lblImgLogo = new JLabel("");
	private JLabel lblMsg = new JLabel("");
	private JLabel lblData = new JLabel("");
	private JLabel lblHora = new JLabel("");

	//CONSTRUTOR VWMenu
	public VWMenu() {
		super("CAR MANAGEMENT | Menu Principal");
		setResizable(false);

		// MENU CADASTRO + seus itens
		menu.add(cadastro);
		cadastro.add(itemCadastrarCondutor);
		cadastro.add(itemCadastrarCarro);
		cadastro.add(itemCadastrarAbastecimento);
		cadastro.add(itemCadastrarManutencao);

		// MENU LISTAS + seus itens
		menu.add(listas);
		listas.add(itemListarCondutores);
		listas.add(itemListarCarros);
		listas.add(itemListarAbastecimentos);
		listas.add(itemListarManutencao);

		// MENU SAIR + seus itens
		menu.add(sair);
		sair.addSeparator();
		sair.add(itemSair);

		// APLICANDO COR AOS ITENS DOS MENUS
		itemCadastrarCondutor.setBackground(Color.WHITE);
		itemCadastrarCarro.setBackground(Color.WHITE);
		itemCadastrarAbastecimento.setBackground(Color.WHITE);
		itemCadastrarManutencao.setBackground(Color.WHITE);
		itemListarCondutores.setBackground(Color.WHITE);
		itemListarCarros.setBackground(Color.WHITE);
		itemListarAbastecimentos.setBackground(Color.WHITE);
		itemListarManutencao.setBackground(Color.WHITE);
		itemSair.setBackground(Color.WHITE);

		// SETANDO IMAGEM NA LABEL DO LOGO
		lblImgLogo.setIcon(imgLogo);

		// ADICIONANDO ÍCONE NO JFRAME
		Image imgJanela = Toolkit.getDefaultToolkit().getImage("img/icon_car.png");
		this.setIconImage(imgJanela);

		// OBTENDO DATA DO SISTEMA
		Date dataSistema = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
		lblData.setText("Jundiaí - " + formatDate.format(dataSistema));

		// OBTENDO HORA DO SISTEMA
		Timer timer = new Timer(1000, new hora());
		timer.start();

		// EVENTOS PARA ITENS DOS MENUS
		// CADASTROS
		// ITEM "SAIR" DO MENU SAIR
		itemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(lblMsg, "Deseja realmente sair do sistema?", "Alerta",
						JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});
		// ITEM "CADASTRAR CARRO" DO MENU CADASTRAR
		itemCadastrarCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemCadCar();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "CADASTRAR ABASTECIMENTO" DO MENU CADASTRAR
		itemCadastrarAbastecimento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemCadAba();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "CADASTRAR CONDUTOR" DO MENU CADASTRAR
		itemCadastrarCondutor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemCadCond();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "CADASTRAR MANUTENCAO" DO MENU CADASTRAR
		itemCadastrarManutencao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemCadManu();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// LISTAS
		// ITEM "LISTAR CONDUTORES" DO MENU LISTAS
		itemListarCondutores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemLstCond();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "LISTAR ABASTECIMENTOS" DO MENU LISTAS
		itemListarAbastecimentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemLstAba();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "LISTAR CONDUTORES" DO MENU LISTAS
		itemListarCarros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemLstCar();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		// ITEM "LISTAR MANUTENÇÃO" DO MENU LISTAS
		itemListarManutencao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					menuBarItemLstManu();
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// SETANDO BARRA DE MENU
		this.setJMenuBar(menu);

		// SETANDO TAMANHO DA TELA
		this.setSize(new Dimension(800, 400));

		this.propriedadesLabelsLogoMsg();

		// COLETANDO TAMANHO DA TELA E DETERMINANDO ABERTURA CENTRALIZADA
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);

		// SETANDO LAYOUT
		this.getContentPane().setLayout(null);

	} // FIM DO CONSTRUTOR

	// COLETANDO HORA ATUAL
	class hora implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Calendar now = Calendar.getInstance();
			lblHora.setText(String.format("- " + "%1$tH:%1$tM:%1$tS", now));
		}
	}

	private void propriedadesLabelsLogoMsg() {
		lblImgLogo.setBounds(483, 260, 291, 69);
		getContentPane().add(lblImgLogo);
		// REFERÊNCIA P/ EXIBIÇÃO DE CONFIRMAÇÃO DE SÁIDA DO SISTEMA
		lblMsg.setBounds(359, 146, 46, 14);
		getContentPane().add(lblMsg);
		// FORMATANDO LABEL DATA
		lblData.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblData.setBounds(10, 315, 119, 14);
		getContentPane().add(lblData);
		// FORMATANDO LABEL DATA
		lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblHora.setBounds(129, 315, 80, 14);
		getContentPane().add(lblHora);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				criarMostrarGUI();
			}
		});
	} // FIM DO MAIN

	private static void criarMostrarGUI() {
		JFrame janela = new VWMenu();
		janela.setVisible(true);
	}

	// CADASTROS
	private void menuBarItemCadCar() throws ParseException, SQLException {
		if (JFVWCadCarros == null)
			JFVWCadCarros = new VWCadastroCarro(VWCadastroCarro.ALTERAR);
		JFVWCadCarros.setVisible(true);
		;

	}

	private void menuBarItemCadAba() throws ParseException, SQLException {
		if (JFVWCadAbastecimentos == null)
			JFVWCadAbastecimentos = new VWCadastroAbastecimento();
		JFVWCadAbastecimentos.setVisible(true);
		;
	}

	private void menuBarItemCadCond() throws ParseException, SQLException {
		if (JFVWCadCondutores == null)
			// JFVWCadCondutores = new VWCadastroAbastecimento();
			JFVWCadCondutores.setVisible(true);
		;
	}

	private void menuBarItemCadManu() throws ParseException, SQLException {
		if (JFVWCadManutencao == null)
			// JFVWCadManutencao = new VWCadastroAbastecimento();
			JFVWCadManutencao.setVisible(true);
		;
	}

	// LISTAS
	private void menuBarItemLstManu() throws ParseException, SQLException {
		if (JFVWLstManutencao == null)
			// JFVWLstManutencao = new VWCadastroAbastecimento();
			JFVWLstManutencao.setVisible(true);
		;
	}

	private void menuBarItemLstCond() throws ParseException, SQLException {
		if (JFVWLstCondutores == null)
			// JFVWLstCondutores = new VWCadastroAbastecimento();
			JFVWLstCondutores.setVisible(true);
		;
	}

	private void menuBarItemLstAba() throws ParseException, SQLException {
		if (JFVWLstAbastecimentos == null)
			// JFVWLstAbastecimentos = new VWCadastroAbastecimento();
			JFVWLstAbastecimentos.setVisible(true);
		;
	}
	
	private void menuBarItemLstCar() throws ParseException, SQLException {
		if (JFVWLstCarros == null)
			// JFVWLstCarros = new VWCadastroAbastecimento();
			JFVWLstCarros.setVisible(true);
		;
	}

}
