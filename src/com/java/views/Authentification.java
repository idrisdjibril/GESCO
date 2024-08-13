package com.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import javax.swing.border.MatteBorder;

import com.java.db.Connexion;
import com.java.model.Eleve;
import com.java.model.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Authentification {

	public JFrame auframe;
	private JTextField txtUser;
	private JPasswordField pwd;
	private JLabel lbver, lbdev;
	private JButton bcon, bins, bins_1;
	
	User user = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.auframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	public boolean isEmpty() {
		if(txtUser.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtUser, "entrer le nom d'utilisateur !");
			return false;
		}
		if(String.valueOf(pwd.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(pwd, "Entrer le mot de passe !");
			return false;
		}
		
		return true;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		auframe = new JFrame();
		auframe.setBounds(100, 100, 1010, 670);
		auframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		auframe.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		auframe.getContentPane().add(panel, "name_101371689703000");
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 255));
		panel_1.setBounds(0, 0, 368, 637);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(368, 0, 626, 631);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(51, 204, 204), 2));
		panel_3.setBounds(197, 11, 230, 226);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 231, 226);
		panel_3.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Authentification.class.getResource("/images/user.jpg")));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(35, 325, 570, 287);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("NOM D'UTILISATEUR");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 11, 144, 28);
		panel_4.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MOT DE PASSE");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(10, 90, 144, 28);
		panel_4.add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Times New Roman", Font.BOLD, 11));
		txtUser.setColumns(10);
		txtUser.setBounds(164, 11, 298, 24);
		panel_4.add(txtUser);
		
		pwd = new JPasswordField();
		pwd.setFont(new Font("Times New Roman", Font.BOLD, 11));
		pwd.setBounds(164, 91, 298, 24);
		panel_4.add(pwd);
		
		bcon = new JButton("Se connecter");
		bcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty()) {
					Connection con = Connexion.getConnection();
					PreparedStatement ps;
					
					String username = txtUser.getText();
					String password = String.valueOf(pwd.getPassword());
					String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
					
					try {
						ps = con.prepareStatement(sql);
						ps.setString(1, username);
						ps.setString(2, password);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(bcon, "Connexion reussite !");
							Acceuil acc = new Acceuil();
							acc.acframe.setVisible(true);
							//acc.acframe.pack();
							auframe.dispose();
						}else {
							JOptionPane.showMessageDialog(bcon, "Nom d'utilisateur ou Mot de passe invalide", "Connexion echouee", 2);
						}
					} catch (Exception ex) {
						Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		});
		bcon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bcon.setBackground(new Color(0, 204, 255));
		bcon.setBounds(164, 165, 121, 23);
		panel_4.add(bcon);
		
		bins = new JButton("Quitter");
		bins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);		}
		});
		bins.setBackground(new Color(204, 255, 255));
		bins.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bins.setBounds(406, 165, 90, 23);
		panel_4.add(bins);
		
		lbver = new JLabel("");
		lbver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwd.setEchoChar((char)0);
				lbver.setVisible(false);
				lbdev.setVisible(true);
			}
		});
		lbver.setIcon(new ImageIcon(Authentification.class.getResource("/icones/lockapplication.png")));
		lbver.setBounds(473, 98, 23, 20);
		panel_4.add(lbver);
		
		lbdev = new JLabel("");
		lbdev.setVisible(false);
		lbdev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwd.setEchoChar(('.'));
				lbver.setVisible(true);
				lbdev.setVisible(false);
			}
		});
		lbdev.setIcon(new ImageIcon(Authentification.class.getResource("/icones/SECURITY.PNG")));
		lbdev.setBounds(472, 98, 23, 20);
		panel_4.add(lbdev);
		
		bins_1 = new JButton("Inscription");
		bins_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inscription ins = new Inscription();
				ins.inframe.setVisible(true);
				auframe.dispose();
			}
		});
		bins_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bins_1.setBackground(new Color(204, 255, 255));
		bins_1.setBounds(291, 165, 111, 23);
		panel_4.add(bins_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Mot de passe oublie ?");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Inscription ins = new Inscription();
				ins.inframe.setVisible(true);
				auframe.dispose();
			}
		});
		lblNewLabel_4_1.setForeground(new Color(0, 153, 255));
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(164, 125, 177, 14);
		panel_4.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_3 = new JLabel("Connexion");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 70));
		lblNewLabel_3.setBounds(150, 248, 347, 63);
		panel_2.add(lblNewLabel_3);
	}
}
