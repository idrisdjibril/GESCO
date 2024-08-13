package com.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import com.java.model.User;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Inscription {

	JFrame inframe;
	private JTextField txtName;
	private JPasswordField txtPass;
	private JPasswordField txtCpass;
	
	User user = new User();
	private JComboBox txtRole;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription window = new Inscription();
					window.inframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inscription() {
		initialize();
	}

	public boolean isEmpty() {
		if(txtName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtName, "entrer le nom d'utilisateur !");
			return false;
		}
		if(String.valueOf(txtPass.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(txtPass, "Entrer le mot de passe !");
			return false;
		}
		if(String.valueOf(txtPass.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(txtPass, "Confirmer le mot de passe !");
			return false;
		}
		return true;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inframe = new JFrame();
		inframe.setBounds(100, 100, 1010, 670);
		inframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 994, 631);
		inframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 638, 631);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtName.setBounds(319, 126, 247, 20);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtPass.setBounds(319, 190, 247, 20);
		panel_1.add(txtPass);
		
		txtCpass = new JPasswordField();
		txtCpass.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtCpass.setBounds(319, 259, 247, 20);
		panel_1.add(txtCpass);
		
		JComboBox txtEtat = new JComboBox();
		txtEtat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtEtat.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		txtEtat.setBounds(319, 384, 132, 22);
		panel_1.add(txtEtat);
		
		JButton btnNewButton = new JButton("S'inscrire");
		btnNewButton.setBackground(new Color(0, 204, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmpty()) {
					int id = 0;
					String username = txtName.getText();
					String password = String.valueOf(txtPass.getPassword());
					String confirmPassword = String.valueOf(txtCpass.getPassword());
					String role = txtRole.getSelectedItem().toString();
					String etat = txtEtat.getSelectedItem().toString();
					if (password.equals(confirmPassword)) {
						/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
						String hashedPassword = encoder.encode(password);*/
						user.insertUser(id, username, password, role, etat);
					}else {
						JOptionPane.showMessageDialog(btnNewButton, "les mots de passe ne sont pas identiques !");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(319, 482, 113, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Se connecter");
		btnNewButton_1.setBackground(new Color(0, 153, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentification auth = new Authentification();
				auth.auframe.setVisible(true);
				inframe.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBounds(453, 482, 113, 23);
		panel_1.add(btnNewButton_1);
		
		txtRole = new JComboBox();
		txtRole.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtRole.setModel(new DefaultComboBoxModel(new String[] {"administrateur", "enseignant", "surveillant", "censeur"}));
		txtRole.setBounds(319, 334, 247, 22);
		panel_1.add(txtRole);
		
		lblNewLabel = new JLabel("Inscription");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(152, 31, 344, 43);
		panel_1.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nom d'utilisateur :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(31, 129, 265, 14);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Mot de passe de l'utilisateur : ");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(31, 193, 265, 14);
		panel_1.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Confirmer le mot de passe : ");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(31, 262, 265, 14);
		panel_1.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Fonction de l'utilisateur : ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(31, 338, 265, 14);
		panel_1.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Statut de l'utilisateur : ");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(31, 388, 265, 14);
		panel_1.add(lblNewLabel_5);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 204, 255));
		panel_2.setBounds(637, 0, 357, 631);
		panel.add(panel_2);
		panel_2.setLayout(null);
	}
}
