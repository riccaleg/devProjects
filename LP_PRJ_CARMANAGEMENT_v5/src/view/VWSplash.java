package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class VWSplash extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon imgLogoSplash = new ImageIcon("img/icon_logo.png");
	private JLabel lblLogoSplash = new JLabel("");

	private static JProgressBar pbSplash = new JProgressBar();

	
	public VWSplash() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Sistema para Gerenciamento de Carros");
		
		setType(Type.UTILITY);
		// SETANDO TAMANHO DA TELA
		this.setSize(new Dimension(438, 220));

		// SETANDO IMAGEM NA LABEL DO LOGO
		lblLogoSplash.setIcon(imgLogoSplash);

		// COLETANDO TAMANHO DA TELA E DETERMINANDO ABERTURA CENTRALIZADA
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		getContentPane().setLayout(null);

		// FORMATANDO LABEL SPLASH
		lblLogoSplash.setBounds(65, 31, 291, 69);
		getContentPane().add(lblLogoSplash);
		pbSplash.setForeground(Color.BLACK);
		pbSplash.setStringPainted(true);
		
		//FORMATANDO PROGRESS BAR
		pbSplash.setBounds(10, 153, 402, 17);
		getContentPane().add(pbSplash);
		
		
		
	}
	
	

	public static void main(String[] args) {
		VWSplash vwSp = new VWSplash();
		vwSp.setVisible(true);
		carregarPB();
		vwSp.setVisible(false);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = null;
				frame = new VWMenu();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
		
	}

	public static void carregarPB() {
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(60);
				pbSplash.setValue(i);
				
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Não foi possível carregar!" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
