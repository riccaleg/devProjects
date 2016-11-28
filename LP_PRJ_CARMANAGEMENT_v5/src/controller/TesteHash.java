package controller;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.swing.JOptionPane;

public class TesteHash {
	public static void main(String[] args) {
		try {
			String senha = "usuario";
			
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(senha.getBytes(),0,senha.length());
			
			senha = new BigInteger(1, m.digest()).toString(16);
			String zeros = "";
			
			if(senha.length() < 32) {
				for(int i = 0; i < (32 - senha.length()); i++) {
					zeros += "0";
				}
			}
			
			senha = zeros + senha;
			
			JOptionPane.showMessageDialog(null, senha);
		} catch (Exception e) {
			System.exit(0);
		}
	}
}