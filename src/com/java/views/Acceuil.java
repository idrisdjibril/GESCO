package com.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.java.model.Eleve;
import com.java.model.Enseignant;
import com.java.model.Frais;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Acceuil {

	public JFrame acframe;
	private JTable table;
	private JTextField txtsearch;
	private JTextField txtlieu;
	private JTextField txtcode, txtSearchClasse;
	private JTextField txtmat;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTextField txtemail;
	private JTextField txtphone;
	private JTextField txtpere;
	private JTextField txtmere;
	private JTextField txtRes;
	private JDateChooser txtdate, txtDF;
	private JLabel lbimage, txtDate, txtTime;
	private JComboBox<?> txtsexe, txtSP, txtMotF, txtSpeP;
	public static JTextField txtIdClasse, txtIdEleve;
	private JLabel lbcount, lbcountF, lbcountM;
	
	Eleve eleve = new Eleve();
	Enseignant prof = new Enseignant();
	Frais frais = new Frais();
	
	int xx, xy;
	private DefaultTableModel model;
	private String imagePath = null;
	private int rowIndex;

	
	private JTextField txtCP;
	private JTextField txtMatP;
	private JTextField txtNP;
	private JTextField txtPP;
	private JTextField txtEP;
	private JTextField txtTP;
	private JTextField txtMatiereP;
	private JTextField txtGP;
	private JTextField textField_11;
	private JTextField txtRP;
	private JLabel lbimageP;
	private JTable tableP;
	
	private JTextField txtF;
	private JTextField txtMonF;
	private JTextField textField_13;
	private JTextField txtAF;
	private JTable tableF;
	private JTextField txtSearchEleve;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil window = new Acceuil();
					window.acframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Acceuil() {
		initialize();
		init();
	}
	
	public void init() {
		setTime();
		tableViewStudent();
		tableViewTeacher();
		tableViewPrice();
		txtcode.setText(String.valueOf(eleve.getMax()));
		lbcount.setText(String.valueOf(eleve.getCount()));
		lbcountF.setText(String.valueOf(eleve.getCountBySex("Masc")));
		lbcountM.setText(String.valueOf(eleve.getCountBySex("Fem")));
		txtCP.setText(String.valueOf(prof.getMax()));
		txtF.setText(String.valueOf(frais.getMax()));
		
	}
	
	private void tableViewStudent() {
		eleve.getStudentValue(table, "");
		model = (DefaultTableModel) table.getModel();
		table.setRowHeight(20);
		table.setShowGrid(true);
		table.setGridColor(Color.black);
		table.setBackground(Color.LIGHT_GRAY);
	}
	
	private void tableViewTeacher() {
		prof.getProfValue(tableP, "");
		model = (DefaultTableModel) tableP.getModel();
		tableP.setRowHeight(20);
		tableP.setShowGrid(true);
		tableP.setGridColor(Color.black);
		tableP.setBackground(Color.LIGHT_GRAY);
	}
	
	private void tableViewPrice() {
		frais.getFraisValue(tableF, "");
		model = (DefaultTableModel) tableF.getModel();
		tableF.setRowHeight(20);
		tableF.setShowGrid(true);
		tableF.setGridColor(Color.black);
		tableF.setBackground(Color.LIGHT_GRAY);
	}

	public void clearStudent() {
		txtcode.setText(String.valueOf(eleve.getMax()));
		txtmat.setText(null);
		txtnom.setText(null);
		txtprenom.setText(null);
		txtlieu.setText(null);
		txtemail.setText(null);
		txtphone.setText(null);
		txtpere.setText(null);
		txtmere.setText(null);
		txtRes.setText(null);
		txtsexe.setSelectedIndex(0);
		txtdate.setDate(null);
		lbimage.setIcon(null);
		txtSearchClasse.setText(null);
		table.clearSelection();
	}
	
	public void clearTeacher() {
		txtCP.setText(String.valueOf(prof.getMax()));
		txtMatP.setText(null);
		txtNP.setText(null);
		txtPP.setText(null);
		txtEP.setText(null);
		txtTP.setText(null);
		txtMatiereP.setText(null);
		txtGP.setText(null);
		txtRP.setText(null);
		txtSP.setSelectedIndex(0);
		txtMotF.setSelectedIndex(0);
		lbimageP.setIcon(null);
		tableP.clearSelection();
	}
	
	public void clearPrice() {
		txtF.setText(String.valueOf(frais.getMax()));
		txtSearchEleve.setText(null);
		txtMotF.setSelectedIndex(0);
		txtMonF.setText(null);
		txtDF.setDate(null);
		txtAF.setText(null);
		tableF.clearSelection();
	}
	
	public boolean isEmptyStudent() {
		if(txtIdClasse.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtIdClasse, "retrouver d'abord la classe dans laquelle l'eleve sera enregistre");
			return false;
		}
		if(txtmat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtmat, "rassurez vous d'avoir entrer le matricule de l'eleve !");
			return false;
		}
		if(txtnom.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtnom, "rassurez vous d'avoir entrer le nom de l'eleve !");
			return false;
		}
		if(txtprenom.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtprenom, "rassurez vous d'avoir entrer le prenom de l'eleve !");
			return false;
		}
		if(txtdate.getDate() == null) {
			JOptionPane.showMessageDialog(txtdate, "rassurez vous d'avoir entrer la date de naissance de l'eleve !");
			return false;
		}
		if(txtdate.getDate().compareTo(new Date())>0) {
			JOptionPane.showMessageDialog(txtdate, "Impossible d'entrer une superieure a l'annee scolaire precedente !");
			return false;
		}
		if(txtlieu.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtlieu, "rassurez vous d'avoir entrer le lieu de naissance de l'eleve !");
			return false;
		}
		if(txtemail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtemail, "rassurez vous d'avoir entrer l'email !");
			return false;
		}
		if(!txtemail.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(txtemail, "L'email doit contenir des caracteres speciaux !");
			return false;
		}
		if(txtphone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtphone, "rassurez vous d'avoir entrer un numero de telephone !");
			return false;
		}
		if(txtphone.getText().length() >= 15 && txtphone.getText().length() < 9) {
			JOptionPane.showMessageDialog(txtphone, "Numero doit avoir etre compris entre 9 entre 15 caractere !");
			return false;
		}
		if(txtsexe.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(txtsexe, "rassurez vous d'avoir selectionner la classe de l'eleve !");
			return false;
		}
		if(txtpere.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtpere, "rassurez vous d'avoir entrer le nom du pere de l'eleve !");
			return false;
		}
		if(txtmere.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtmere, "rassurez vous d'avoir entrer le nom de la mere de l'eleve !");
			return false;
		}
		if(txtRes.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtRes, "rassurez vous d'avoir entrer l'adresse 1 !");
			return false;
		}
		
		if(txtmat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtcode, "rassurez vous d'avoir entrer le nom de l'eleve !");
			return false;
		}
		if(imagePath == null) {
			JOptionPane.showInputDialog(imagePath, "rassurez vous d'avoir selectionner une image !");
			return false;
		}
		return true;
	}
	
	public boolean isEmptyTeacher() {
		if(txtMatP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtMatP, "rassurez vous d'avoir entrer le matricule du professeur !");
			return false;
		}
		if(txtNP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtNP, "rassurez vous d'avoir entrer le nom du professeur !");
			return false;
		}
		if(txtPP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtPP, "rassurez vous d'avoir entrer le prenom du professeur !");
			return false;
		}
		if(txtSP.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(txtSP, "rassurez vous d'avoir selectionner le genre du professeur !");
			return false;
		}
		if(txtEP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtEP, "rassurez vous d'avoir entrer l'email !");
			return false;
		}
		if(!txtEP.getText().matches("^.+@.+\\..+$")) {
			JOptionPane.showMessageDialog(txtEP, "L'email doit contenir des caracteres speciaux !");
			return false;
		}
		if(txtTP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtTP, "rassurez vous d'avoir entrer un numero de telephone !");
			return false;
		}
		if(txtTP.getText().length() >= 15 && txtTP.getText().length() < 9) {
			JOptionPane.showMessageDialog(txtTP, "Numero doit avoir etre compris entre 9 entre 15 caractere !");
			return false;
		}
		if(txtMotF.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(txtMotF, "rassurez vous d'avoir selectionner la motif !");
			return false;
		}
		if(txtMatiereP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtMatiereP, "rassurez vous d'avoir entrer la matiere enseigne !");
			return false;
		}
		if(txtGP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtGP, "rassurez vous d'avoir entrer le grade !");
		}
		if(txtRP.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtRP, "rassurez vous d'avoir entrer la residence !");
			return false;
		}
		if(imagePath == null) {
			JOptionPane.showInputDialog(imagePath, "rassurez vous d'avoir selectionner une image !");
			return false;
		}
		return true;
	}
	
	public boolean isEmptyPrice() {
		if(txtIdEleve.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtIdEleve, "retrouver d'abord l'eleve qui paye l'operation");
			return false;
		}
		if(txtMotF.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(txtMotF, "rassurez vous d'avoir selectionner le motif de l'operation !");
			return false;
		}
		if(txtMonF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtMonF, "rassurez vous d'avoir entrer le montant de l'operation !");
			return false;
		}
		if(txtDF.getDate() == null) {
			JOptionPane.showMessageDialog(txtDF, "rassurez vous d'avoir entrer la date de paiement !");
			return false;
		}
		if(txtDF.getDate().compareTo(new Date())>0) {
			JOptionPane.showMessageDialog(txtDF, "Impossible d'entrer une superieure a l'annee scolaire en cours !");
			return false;
		}
		if(txtAF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(txtAF, "rassurez vous d'avoir entrer l'annee scolaire !");
			return false;
		}
		return true;
	}
	
	private ImageIcon imageAdjust(String path, byte[] pic) {
		ImageIcon myImage = null;
		if(path != null) {
			myImage = new ImageIcon(path);
		}else {
			myImage = new ImageIcon(pic);
		}
		Image img = myImage.getImage();
		Image newImage = img.getScaledInstance(lbimage.getWidth(), lbimage.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newImage);
		return icon;
	}
	
	public boolean check() {
		String newMatricule = txtmat.getText();
		String newEmail = txtemail.getText();
		String newPhone = txtphone.getText();
		String oldMatricule = model.getValueAt(rowIndex, 1).toString();
		String oldEmail = model.getValueAt(rowIndex, 8).toString();
		String oldPhone = model.getValueAt(rowIndex, 9).toString();
		if (newEmail.equals(oldEmail) && newMatricule.equals(oldMatricule) && newPhone.equals(oldPhone)) {
			return false;
		}else {
			if (newMatricule.equals(oldMatricule)) {
				boolean x = eleve.isMatriculeExist(newMatricule);
				if (!x) {
					JOptionPane.showMessageDialog(txtmat, "Ce matricule est deja occupe !");
				}
				return x;
			}
			
			if (newEmail.equals(oldEmail)) {
				boolean x = eleve.isEmailExist(newEmail);
				if (!x) {
					JOptionPane.showMessageDialog(txtemail, "Cet email existe deja !");
				}
				return x;
			}
			if (newPhone.equals(oldPhone)) {
				boolean x = eleve.isPhoneExist(newPhone);
				if (!x) {
					JOptionPane.showMessageDialog(txtphone, "Ce numero de telephone existe deja !");
				}
				return x;
			}
		}
		return false;
	}
	
	public void setTime() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, e);
					}
					Date date = new Date();
					SimpleDateFormat tf = new SimpleDateFormat("h:mm:ss aa");
					SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
					String time = tf.format(date);
					txtTime.setText(time.split(" ")[0] + " " + time.split(" ")[1]);
					txtDate.setText(df.format(date));
				}
				
			}
		}).start();;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		acframe = new JFrame();
		acframe.setBounds(100, 100, 1150, 680);
		acframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		acframe.getContentPane().setLayout(null);
		acframe.setLocation(100, 100);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int x = e.getX();
				int y = e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				panel.setLocation(x-xx, y-xy);
			}
		});
		panel.setBounds(0, 0, 1136, 643);
		acframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 204, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 102, 255), 4, true));
		panel_1.setBounds(0, 0, 1136, 72);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Systeme de gestion de l'etablissement");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(24, 11, 663, 45);
		panel_1.add(lblNewLabel);
		
		txtTime = new JLabel("");
		txtTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		txtTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtTime.setBounds(980, 11, 146, 14);
		panel_1.add(txtTime);
		
		txtDate = new JLabel("");
		txtDate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtDate.setBounds(980, 42, 146, 14);
		panel_1.add(txtDate);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tabbedPane.setBounds(0, 71, 1136, 572);
		panel.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Eleve", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 204, 255));
		panel_11.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_11.setBounds(0, 0, 438, 541);
		panel_2.add(panel_11);
		panel_11.setLayout(null);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_15.setBackground(Color.LIGHT_GRAY);
		panel_15.setBounds(10, 11, 418, 449);
		panel_11.add(panel_15);
		panel_15.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Photo");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBounds(322, 11, 49, 14);
		panel_15.add(lblNewLabel_2);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_18.setBounds(283, 25, 125, 120);
		panel_15.add(panel_18);
		panel_18.setLayout(null);
		
	    lbimage = new JLabel("");
		lbimage.setBounds(0, 0, 125, 120);
		panel_18.add(lbimage);
		
		JButton btnparcours = new JButton("Parcourrir");
		btnparcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("+.image", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int output = file.showSaveDialog(file);
				if(output == JFileChooser.APPROVE_OPTION) {
					File selectFile = file.getSelectedFile();
					String path = selectFile.getAbsolutePath();
					lbimage.setIcon(imageAdjust(path, null));
					imagePath = path;
				}else {
					JOptionPane.showInternalMessageDialog(btnparcours, "Image non selectionnee !");
				}
			}
		});
		btnparcours.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnparcours.setBounds(303, 149, 89, 23);
		panel_15.add(btnparcours);
		
		JLabel lblNewLabel_3 = new JLabel("ID Eleve");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_3.setBounds(10, 36, 76, 14);
		panel_15.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID Classe");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 61, 76, 14);
		panel_15.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nom Eleve");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5.setBounds(10, 114, 76, 14);
		panel_15.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Matricule");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 89, 76, 14);
		panel_15.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Date Nais.");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_7.setBounds(10, 183, 76, 14);
		panel_15.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Lieu");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_8.setBounds(224, 186, 49, 14);
		panel_15.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Sexe");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_9.setBounds(10, 211, 76, 14);
		panel_15.add(lblNewLabel_9);
		
		JLabel lblNewLabel_11 = new JLabel("Email");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_11.setBounds(10, 242, 76, 14);
		panel_15.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Telephone");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_12.setBounds(10, 267, 76, 14);
		panel_15.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Nom du pere");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_13.setBounds(10, 292, 76, 14);
		panel_15.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Nom de la mere");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_14.setBounds(10, 320, 76, 14);
		panel_15.add(lblNewLabel_14);
		
		txtdate = new JDateChooser();
		txtdate.setDateFormatString("yyyy-MM-dd");
		txtdate.setBounds(85, 180, 125, 20);
		panel_15.add(txtdate);
	
		txtlieu = new JTextField();
		txtlieu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtlieu.setBounds(283, 183, 125, 20);
		panel_15.add(txtlieu);
		txtlieu.setColumns(10);
		
		txtcode = new JTextField();
		txtcode.setForeground(Color.BLUE);
		txtcode.setBackground(Color.DARK_GRAY);
		txtcode.setEditable(false);
		txtcode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtcode.setBounds(85, 33, 192, 20);
		panel_15.add(txtcode);
		txtcode.setColumns(10);
		
		txtmat = new JTextField();
		txtmat.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtmat.setBounds(85, 86, 192, 20);
		panel_15.add(txtmat);
		txtmat.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtnom.setBounds(85, 111, 192, 20);
		panel_15.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtprenom.setBounds(85, 136, 192, 20);
		panel_15.add(txtprenom);
		txtprenom.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtemail.setBounds(85, 236, 323, 20);
		panel_15.add(txtemail);
		txtemail.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
				e.consume();
				}
			}
		});
		txtphone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtphone.setBounds(85, 261, 323, 20);
		panel_15.add(txtphone);
		txtphone.setColumns(10);
		
		txtpere = new JTextField();
		txtpere.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtpere.setBounds(85, 286, 323, 20);
		panel_15.add(txtpere);
		txtpere.setColumns(10);
		
		txtmere = new JTextField();
		txtmere.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtmere.setBounds(85, 314, 323, 20);
		panel_15.add(txtmere);
		txtmere.setColumns(10);
		
		txtRes = new JTextField();
		txtRes.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtRes.setColumns(10);
		txtRes.setBounds(85, 345, 323, 20);
		panel_15.add(txtRes);
		
		JLabel lblNewLabel_14_1 = new JLabel("Residence");
		lblNewLabel_14_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_14_1.setBounds(10, 351, 76, 14);
		panel_15.add(lblNewLabel_14_1);
		
		txtsexe = new JComboBox();
		txtsexe.setModel(new DefaultComboBoxModel(new String[] {"Masc", "Fem"}));
		txtsexe.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtsexe.setBounds(85, 207, 102, 22);
		panel_15.add(txtsexe);
		
		JButton btnClasse = new JButton("");
		btnClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String classe = txtsexe.getSelectedItem().toString();
				eleve.SearchByClasse(table, classe);
				table.setModel(new DefaultTableModel(null, new Object[] { "N°", "Classe ID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image" }));
				eleve.getStudentValue(table, classe);
				//clearStudent();
			}
		});
		btnClasse.setBackground(new Color(0, 204, 0));
		btnClasse.setBounds(192, 207, 18, 23);
		panel_15.add(btnClasse);
		
		txtIdClasse = new JTextField();
		txtIdClasse.setForeground(Color.BLUE);
		txtIdClasse.setBackground(Color.DARK_GRAY);
		txtIdClasse.setEditable(false);
		txtIdClasse.setBounds(85, 58, 192, 20);
		panel_15.add(txtIdClasse);
		txtIdClasse.setColumns(10);
		
		JLabel lblNewLabel_5_1 = new JLabel("Prenom");
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5_1.setBounds(10, 139, 76, 14);
		panel_15.add(lblNewLabel_5_1);
		
		txtSearchClasse = new JTextField();
		txtSearchClasse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
				e.consume();
				}
			}
		});
		txtSearchClasse.setBounds(85, 383, 192, 20);
		panel_15.add(txtSearchClasse);
		txtSearchClasse.setColumns(10);
		
		JButton btnSearchClasse = new JButton("New button");
		btnSearchClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearchClasse.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtSearchClasse, "Saissez l'identifiant de la classe !");
				}else {
					int id = Integer.parseInt(txtSearchClasse.getText());
					eleve.getId(id);
				}
			}
		});
		btnSearchClasse.setBounds(319, 382, 89, 23);
		panel_15.add(btnSearchClasse);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_16.setBounds(10, 471, 418, 59);
		panel_11.add(panel_16);
		panel_16.setLayout(null);
		
		lbcount = new JLabel("");
		lbcount.setIcon(new ImageIcon(Acceuil.class.getResource("/icones/Warehouse.png")));
		lbcount.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcount.setBounds(10, 11, 63, 31);
		panel_16.add(lbcount);
		
		lbcountF = new JLabel("");
		lbcountF.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountF.setBounds(191, 11, 63, 31);
		panel_16.add(lbcountF);
		
		lbcountM = new JLabel("");
		lbcountM.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountM.setBounds(345, 11, 63, 31);
		panel_16.add(lbcountM);
		
		JLabel lblNewLabel_4_1 = new JLabel("Nbre total d'eleves");
		lblNewLabel_4_1.setForeground(new Color(0, 102, 102));
		lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(10, 34, 114, 25);
		panel_16.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Nbre total de garcons");
		lblNewLabel_4_1_1.setForeground(new Color(153, 0, 255));
		lblNewLabel_4_1_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_1.setBounds(144, 36, 110, 20);
		panel_16.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_4_1_2 = new JLabel("Nbre total de filles");
		lblNewLabel_4_1_2.setForeground(new Color(255, 51, 204));
		lblNewLabel_4_1_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_2.setBounds(294, 36, 114, 20);
		panel_16.add(lblNewLabel_4_1_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(0, 153, 204));
		panel_12.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		panel_12.setBounds(444, 0, 687, 541);
		panel_2.add(panel_12);
		panel_12.setLayout(null);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(0, 204, 255));
		panel_13.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_13.setBounds(10, 11, 667, 64);
		panel_12.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("effectuer des recherches");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 26, 104, 14);
		panel_13.add(lblNewLabel_1);
		
		txtsearch = new JTextField();
		txtsearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtsearch.setBounds(113, 23, 236, 20);
		panel_13.add(txtsearch);
		txtsearch.setColumns(10);
		
		JButton btnsearch = new JButton("Rechercher");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtsearch.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtsearch, "Svp veiller saisir pour rechercher !");
				}else {
					table.setModel(new DefaultTableModel(null, new Object[] {"N°", "Classe ID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"}));
					eleve.getStudentValue(table, txtsearch.getText());
					clearStudent(); 
				}
			}
		});
		btnsearch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnsearch.setBounds(359, 22, 104, 23);
		panel_13.add(btnsearch);
		
		JButton btnrefresh = new JButton("Actualiser");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel(null, new Object[] {"N°", "Classe ID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"}));
				eleve.getStudentValue(table, "");
				txtsearch.setText(null);
			}
		});
		btnrefresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnrefresh.setBounds(568, 22, 89, 23);
		panel_13.add(btnrefresh);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(0, 204, 255));
		panel_14.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_14.setBounds(10, 465, 667, 47);
		panel_12.add(panel_14);
		panel_14.setLayout(null);
		
		JButton btnadd = new JButton("Ajouter");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearchClasse.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtSearchEleve, "Verifier d'abord l'existance de la classe en effectuant une recherche !");
				}else {
					if(isEmptyStudent()) {
						if(eleve.isMatriculeExist(txtmat.getText())) {
							if(eleve.isEmailExist(txtemail.getText())) {
								if(eleve.isPhoneExist(txtphone.getText())) {
									int id = eleve.getMax();
									int idClasse = Integer.parseInt(txtIdClasse.getText());
									String matEl = txtmat.getText();
									String nomEl = txtnom.getText();
									String prenomEl = txtprenom.getText();
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									String date = dateFormat.format(txtdate.getDate());
									String lieu = txtlieu.getText();
									String sexe = txtsexe.getSelectedItem().toString();
									String emailEL = txtemail.getText();
									String phoneEl = txtphone.getText();
									String nomPere = txtpere.getText();
									String nomMere = txtmere.getText();
									String residence = txtRes.getText();
									eleve.insert(id, idClasse, matEl, nomEl, prenomEl, date, lieu, sexe, emailEL, phoneEl, nomPere, nomMere, residence, imagePath);
									table.setModel(new DefaultTableModel(null, new Object[] {"N°", "ClasseID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"}));
									eleve.getStudentValue(table, "");
									clearStudent();
								}else {
									JOptionPane.showMessageDialog(txtpere, "Ce numero de telephone existe deja !");
								}
								
							}else {
								JOptionPane.showMessageDialog(txtpere, "Cet email existe deja  !");
							}
							
						}else {
							JOptionPane.showMessageDialog(txtpere, "Ce matricule est deja occupe !");
						}
					}

				}
			}
		});
		btnadd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnadd.setBounds(10, 11, 89, 23);
		panel_14.add(btnadd);
		
		JButton btnedit = new JButton("Modifier");
		btnedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEmptyStudent()){
					int id = Integer.parseInt(txtcode.getText());
					if (!eleve.isIdExist(id)) {
						if (!check()) {
							int idClasse = Integer.parseInt(txtIdClasse.getText());
							String matEl = txtmat.getText();
							String nomEl = txtnom.getText();
							String prenomEl = txtprenom.getText();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String date = dateFormat.format(txtdate.getDate());
							String lieu = txtlieu.getText();
							String sexe = txtsexe.getSelectedItem().toString();
							String emailEL = txtemail.getText();
							String phoneEl = txtphone.getText();
							String nomPere = txtpere.getText();
							String nomMere = txtmere.getText();
							String residence = txtRes.getText();
							eleve.update(id, idClasse, matEl, nomEl, prenomEl, date, lieu, sexe, emailEL, phoneEl, nomPere, nomMere, residence, imagePath);
							table.setModel(new DefaultTableModel(null, new Object[] {"N°", "Classe ID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"}));
							eleve.getStudentValue(table, "");
							clearStudent(); 
						}
					}else {
						JOptionPane.showMessageDialog(txtpere, "Cet identifiant n'existe pas !");
					}
				}
			}
		});
		btnedit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnedit.setBounds(109, 11, 89, 23);
		panel_14.add(btnedit);
		
		JButton btndelete = new JButton("Supprimer");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(txtcode.getText());
				String nomEl = txtnom.getText();
				if (eleve.isIdExist(id)) {
					eleve.delete(id, nomEl);
					table.setModel(new DefaultTableModel(null, new Object[] {"N°", "Classe ID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"}));
					eleve.getStudentValue(table, "");
					clearStudent(); 
				}else {
					JOptionPane.showMessageDialog(txtpere, "Cet identifiant n'existe pas !");
				}
			}
		});
		btndelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btndelete.setBounds(208, 11, 89, 23);
		panel_14.add(btndelete);
		
		JButton btnclear = new JButton("Nettoyer");
		btnclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== btnclear) {
					clearStudent();
				}
			}
		});
		btnclear.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnclear.setBounds(469, 11, 89, 23);
		panel_14.add(btnclear);
		
		JButton btnlogout = new JButton("Deconnecter");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int logout = JOptionPane.showConfirmDialog(btnlogout, "Vous allez etre deconnecte", "select", JOptionPane.YES_NO_OPTION);
				if(logout == 0) {
					Authentification auth = new Authentification();
					acframe.dispose();
					auth.auframe.setVisible(true);
				}
			}
		});
		btnlogout.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnlogout.setBounds(568, 11, 89, 23);
		panel_14.add(btnlogout);
		
		JButton btnprint = new JButton("Imprimer");
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleve.print(table);
			}
		});
		btnprint.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnprint.setBounds(370, 11, 89, 23);
		panel_14.add(btnprint);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(10, 86, 667, 368);
		panel_12.add(panel_17);
		panel_17.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 667, 368);
		panel_17.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model = (DefaultTableModel) table.getModel();
				rowIndex = table.getSelectedRow();
				txtcode.setText(model.getValueAt(rowIndex, 0).toString());
				txtIdClasse.setText(model.getValueAt(rowIndex, 1).toString());
				txtmat.setText(model.getValueAt(rowIndex, 2).toString());
				txtnom.setText(model.getValueAt(rowIndex, 3).toString());
				txtprenom.setText(model.getValueAt(rowIndex, 4).toString());
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(rowIndex, 5).toString());
					txtdate.setDate(date);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				
				txtlieu.setText(model.getValueAt(rowIndex, 6).toString());
				String sexe = model.getValueAt(rowIndex, 7).toString();
				if(sexe.equals("Masc")) {
					txtsexe.setSelectedIndex(0);
				}else {
					txtsexe.setSelectedIndex(1);
				}
				txtemail.setText(model.getValueAt(rowIndex, 8).toString());
				txtphone.setText(model.getValueAt(rowIndex, 9).toString());
				txtpere.setText(model.getValueAt(rowIndex, 10).toString());
				txtmere.setText(model.getValueAt(rowIndex, 11).toString());
				txtRes.setText(model.getValueAt(rowIndex, 12).toString());
				String path = model.getValueAt(rowIndex, 13).toString();
				imagePath = path;
				lbimage.setIcon(imageAdjust(path, null));
				
			}
		});
		table.setFont(new Font("Times New Roman", Font.BOLD, 8));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N°", "ClasseID", "Matricule", "Nom", "Prenom", "Date", "Lieu", "Sexe", "Email", "Phone", "Pere", "Mere", "Residence", "Image"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(45);
		table.getColumnModel().getColumn(7).setPreferredWidth(65);
		table.getColumnModel().getColumn(9).setPreferredWidth(65);
		table.getColumnModel().getColumn(10).setPreferredWidth(70);
		table.getColumnModel().getColumn(11).setPreferredWidth(70);
		table.getColumnModel().getColumn(12).setPreferredWidth(55);
		table.getColumnModel().getColumn(13).setPreferredWidth(55);
		scrollPane.setViewportView(table);
		
		JPanel panel_2_1 = new JPanel();
		tabbedPane.addTab("Enseignant", null, panel_2_1, null);
		panel_2_1.setLayout(null);
		
		JPanel panel_11_1 = new JPanel();
		panel_11_1.setBounds(0, 0, 438, 541);
		panel_11_1.setLayout(null);
		panel_11_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_11_1.setBackground(new Color(0, 204, 255));
		panel_2_1.add(panel_11_1);
		
		JPanel panel_15_1 = new JPanel();
		panel_15_1.setLayout(null);
		panel_15_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_15_1.setBackground(Color.LIGHT_GRAY);
		panel_15_1.setBounds(10, 11, 418, 342);
		panel_11_1.add(panel_15_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Photo");
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2_1.setBounds(322, 11, 49, 14);
		panel_15_1.add(lblNewLabel_2_1);
		
		JPanel panel_18_1 = new JPanel();
		panel_18_1.setLayout(null);
		panel_18_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_18_1.setBounds(283, 25, 125, 120);
		panel_15_1.add(panel_18_1);
		
		lbimageP = new JLabel("");
		lbimageP.setBounds(0, 0, 125, 120);
		panel_18_1.add(lbimageP);
		
		JButton btnparcours_1 = new JButton("Parcourrir");
		btnparcours_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("+.image", "jpg", "gif", "png");
				file.addChoosableFileFilter(filter);
				int output = file.showSaveDialog(file);
				if (output == JFileChooser.APPROVE_OPTION) {
					File selectFile = file.getSelectedFile();
					String path = selectFile.getAbsolutePath();
					lbimageP.setIcon(imageAdjust(path, null));
					imagePath = path;
				} else {
					JOptionPane.showInternalMessageDialog(btnparcours, "Image non selectionnee !");
				}
			}
		});
		btnparcours_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnparcours_1.setBounds(303, 149, 89, 17);
		panel_15_1.add(btnparcours_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("ID Prof.");
		lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_3_1.setBounds(10, 36, 76, 14);
		panel_15_1.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Nom Professeur");
		lblNewLabel_5_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5_2.setBounds(10, 91, 76, 14);
		panel_15_1.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6_1 = new JLabel("Matricule");
		lblNewLabel_6_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6_1.setBounds(10, 66, 76, 14);
		panel_15_1.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_9_1 = new JLabel("Sexe");
		lblNewLabel_9_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_9_1.setBounds(10, 148, 76, 14);
		panel_15_1.add(lblNewLabel_9_1);
		
		JLabel lblNewLabel_11_1 = new JLabel("Email");
		lblNewLabel_11_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_11_1.setBounds(10, 179, 76, 14);
		panel_15_1.add(lblNewLabel_11_1);
		
		JLabel lblNewLabel_12_1 = new JLabel("Telephone");
		lblNewLabel_12_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_12_1.setBounds(10, 204, 76, 14);
		panel_15_1.add(lblNewLabel_12_1);
		
		JLabel lblNewLabel_13_1 = new JLabel("Specialite");
		lblNewLabel_13_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_13_1.setBounds(10, 229, 76, 14);
		panel_15_1.add(lblNewLabel_13_1);
		
		JLabel lblNewLabel_14_2 = new JLabel("Matiere ");
		lblNewLabel_14_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_14_2.setBounds(10, 257, 76, 14);
		panel_15_1.add(lblNewLabel_14_2);
		
		txtCP = new JTextField();
		txtCP.setText("1");
		txtCP.setHorizontalAlignment(SwingConstants.CENTER);
		txtCP.setForeground(SystemColor.textHighlightText);
		txtCP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtCP.setEditable(false);
		txtCP.setColumns(10);
		txtCP.setBackground(Color.DARK_GRAY);
		txtCP.setBounds(85, 33, 192, 20);
		panel_15_1.add(txtCP);
		
		txtMatP = new JTextField();
		txtMatP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtMatP.setColumns(10);
		txtMatP.setBounds(85, 63, 192, 20);
		panel_15_1.add(txtMatP);
		
		txtNP = new JTextField();
		txtNP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtNP.setColumns(10);
		txtNP.setBounds(85, 88, 192, 20);
		panel_15_1.add(txtNP);
		
		txtPP = new JTextField();
		txtPP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtPP.setColumns(10);
		txtPP.setBounds(85, 113, 192, 20);
		panel_15_1.add(txtPP);
		
		txtEP = new JTextField();
		txtEP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtEP.setColumns(10);
		txtEP.setBounds(85, 173, 323, 20);
		panel_15_1.add(txtEP);
		
		txtTP = new JTextField();
		txtTP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
				e.consume();
				}
			}
		});
		txtTP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtTP.setColumns(10);
		txtTP.setBounds(85, 198, 323, 20);
		panel_15_1.add(txtTP);
		
		txtMatiereP = new JTextField();
		txtMatiereP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtMatiereP.setColumns(10);
		txtMatiereP.setBounds(85, 251, 323, 20);
		panel_15_1.add(txtMatiereP);
		
		txtGP = new JTextField();
		txtGP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtGP.setColumns(10);
		txtGP.setBounds(85, 282, 323, 20);
		panel_15_1.add(txtGP);
		
		JLabel lblNewLabel_14_1_1 = new JLabel("Grade");
		lblNewLabel_14_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_14_1_1.setBounds(10, 288, 76, 14);
		panel_15_1.add(lblNewLabel_14_1_1);
		
		txtSP = new JComboBox();
		txtSP.setModel(new DefaultComboBoxModel(new String[] {"Masc", "Fem"}));
		txtSP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtSP.setBounds(85, 144, 102, 22);
		panel_15_1.add(txtSP);
		
		JButton btnClasse_1 = new JButton("");
		btnClasse_1.setBackground(new Color(0, 204, 0));
		btnClasse_1.setBounds(192, 144, 18, 23);
		panel_15_1.add(btnClasse_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Prenom");
		lblNewLabel_5_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5_1_1.setBounds(10, 116, 76, 14);
		panel_15_1.add(lblNewLabel_5_1_1);
		
		txtRP = new JTextField();
		txtRP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtRP.setColumns(10);
		txtRP.setBounds(85, 313, 323, 20);
		panel_15_1.add(txtRP);
		
		JLabel lblNewLabel_14_1_1_1 = new JLabel("Residence");
		lblNewLabel_14_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_14_1_1_1.setBounds(10, 319, 76, 14);
		panel_15_1.add(lblNewLabel_14_1_1_1);
		
		txtSpeP = new JComboBox();
		txtSpeP.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtSpeP.setModel(new DefaultComboBoxModel(new String[] {"Scientifiques", "Litteraires", "Langues", "Evasion", "Extra scolaire", "Autres"}));
		txtSpeP.setBounds(85, 225, 323, 22);
		panel_15_1.add(txtSpeP);
		
		JPanel panel_16_1 = new JPanel();
		panel_16_1.setLayout(null);
		panel_16_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_16_1.setBounds(10, 471, 418, 59);
		panel_11_1.add(panel_16_1);
		
		JLabel lbcount_1 = new JLabel("0");
		lbcount_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcount_1.setBounds(10, 11, 63, 31);
		panel_16_1.add(lbcount_1);
		
		JLabel lbcountF_1 = new JLabel("0");
		lbcountF_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountF_1.setBounds(191, 11, 63, 31);
		panel_16_1.add(lbcountF_1);
		
		JLabel lbcountM_1 = new JLabel("0");
		lbcountM_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountM_1.setBounds(345, 11, 63, 31);
		panel_16_1.add(lbcountM_1);
		
		JLabel lblNewLabel_4_1_3 = new JLabel("Nbre total d'eleves");
		lblNewLabel_4_1_3.setForeground(new Color(0, 102, 102));
		lblNewLabel_4_1_3.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_3.setBounds(10, 34, 114, 25);
		panel_16_1.add(lblNewLabel_4_1_3);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Nbre total de garcons");
		lblNewLabel_4_1_1_1.setForeground(new Color(153, 0, 255));
		lblNewLabel_4_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_1_1.setBounds(144, 36, 110, 20);
		panel_16_1.add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_4_1_2_1 = new JLabel("Nbre total de filles");
		lblNewLabel_4_1_2_1.setForeground(new Color(255, 51, 204));
		lblNewLabel_4_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_2_1.setBounds(294, 36, 114, 20);
		panel_16_1.add(lblNewLabel_4_1_2_1);
		
		JPanel panel_12_1 = new JPanel();
		panel_12_1.setBounds(444, 0, 687, 541);
		panel_12_1.setLayout(null);
		panel_12_1.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		panel_12_1.setBackground(new Color(0, 153, 204));
		panel_2_1.add(panel_12_1);
		
		JPanel panel_13_1 = new JPanel();
		panel_13_1.setLayout(null);
		panel_13_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_13_1.setBackground(new Color(0, 204, 255));
		panel_13_1.setBounds(10, 11, 667, 64);
		panel_12_1.add(panel_13_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("effectuer des recherches");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(10, 26, 104, 14);
		panel_13_1.add(lblNewLabel_1_1);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_11.setColumns(10);
		textField_11.setBounds(113, 23, 236, 20);
		panel_13_1.add(textField_11);
		
		JButton btnsearch_1 = new JButton("Rechercher");
		btnsearch_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnsearch_1.setBounds(359, 22, 104, 23);
		panel_13_1.add(btnsearch_1);
		
		JButton btnrefresh_1 = new JButton("Actualiser");
		btnrefresh_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnrefresh_1.setBounds(568, 22, 89, 23);
		panel_13_1.add(btnrefresh_1);
		
		JPanel panel_14_1 = new JPanel();
		panel_14_1.setLayout(null);
		panel_14_1.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_14_1.setBackground(new Color(0, 204, 255));
		panel_14_1.setBounds(10, 465, 667, 47);
		panel_12_1.add(panel_14_1);
		
		JButton btnadd_1 = new JButton("Ajouter");
		btnadd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isEmptyTeacher()) {
					int id = eleve.getMax();
					String mat = txtMatP.getText();
					String nom = txtNP.getText();
					String prenom = txtPP.getText();
					String sexe = txtSP.getSelectedItem().toString();
					String email = txtEP.getText();
					String phone = txtTP.getText();
					String specialite = txtSpeP.getSelectedItem().toString();
					String matiere = txtMatiereP.getText();
					String grade = txtGP.getText();
					String residence = txtRP.getText();
					prof.insertProf(id, mat, nom, prenom, sexe, email, phone, specialite, matiere, grade, residence, imagePath);
					tableP.setModel(new DefaultTableModel(null, new Object[] {"N°", "Matricule", "Nom", "Prenom","Sexe", "Email", "Phone", "Specialite", "Matiere", "Grade", "Residence", "Photo"}));
					prof.getProfValue(tableP, "");
					clearTeacher();
					
				}
			}
		});
		btnadd_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnadd_1.setBounds(10, 11, 89, 23);
		panel_14_1.add(btnadd_1);
		
		JButton btnedit_1 = new JButton("Modifier");
		btnedit_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnedit_1.setBounds(109, 11, 89, 23);
		panel_14_1.add(btnedit_1);
		
		JButton btndelete_1 = new JButton("Supprimer");
		btndelete_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btndelete_1.setBounds(208, 11, 89, 23);
		panel_14_1.add(btndelete_1);
		
		JButton btnclear_1 = new JButton("Nettoyer");
		btnclear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTeacher();
			}
		});
		btnclear_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnclear_1.setBounds(469, 11, 89, 23);
		panel_14_1.add(btnclear_1);
		
		JButton btnlogout_1 = new JButton("Deconnecter");
		btnlogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int logout = JOptionPane.showConfirmDialog(btnlogout, "Vous allez etre deconnecte", "select", JOptionPane.YES_NO_OPTION);
				if(logout == 0) {
					Authentification auth = new Authentification();
					acframe.dispose();
					auth.auframe.setVisible(true);
				}
			}
		});
		btnlogout_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnlogout_1.setBounds(568, 11, 89, 23);
		panel_14_1.add(btnlogout_1);
		
		JButton btnprint_1 = new JButton("Imprimer");
		btnprint_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnprint_1.setBounds(370, 11, 89, 23);
		panel_14_1.add(btnprint_1);
		
		JPanel panel_17_1 = new JPanel();
		panel_17_1.setLayout(null);
		panel_17_1.setBounds(10, 86, 667, 368);
		panel_12_1.add(panel_17_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 667, 368);
		panel_17_1.add(scrollPane_1);
		
		tableP = new JTable();
		tableP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model = (DefaultTableModel) tableP.getModel();
				rowIndex = tableP.getSelectedRow();
				txtCP.setText(model.getValueAt(rowIndex, 0).toString());
				txtMatP.setText(model.getValueAt(rowIndex, 1).toString());
				txtNP.setText(model.getValueAt(rowIndex, 2).toString());
				txtPP.setText(model.getValueAt(rowIndex, 3).toString());
				String sexe = model.getValueAt(rowIndex, 4).toString();
				if(sexe.equals("Masc")) {
					txtSP.setSelectedIndex(0);
				}else {
					txtSP.setSelectedIndex(1);
				}
				txtEP.setText(model.getValueAt(rowIndex, 5).toString());
				txtTP.setText(model.getValueAt(rowIndex, 6).toString());
				String specialite = model.getValueAt(rowIndex, 7).toString();
				if(specialite.equals("Scientifiques")) {
					txtSpeP.setSelectedIndex(0);
				}else if (specialite.equals("Litteraires")) {
					txtSpeP.setSelectedIndex(1);
				}else if (specialite.equals("Langues")) {
					txtSpeP.setSelectedIndex(2);
				}else if (specialite.equals("Evasion")) {
					txtSpeP.setSelectedIndex(3);
				}else if (specialite.equals("Extra scolaire")) {
					txtSpeP.setSelectedIndex(4);
				}else {
					txtSpeP.setSelectedIndex(5);
				}
				txtMatiereP.setText(model.getValueAt(rowIndex, 8).toString());
				txtGP.setText(model.getValueAt(rowIndex, 9).toString());
				txtRP.setText(model.getValueAt(rowIndex, 10).toString());
				String path = model.getValueAt(rowIndex, 11).toString();
				imagePath = path;
				lbimageP.setIcon(imageAdjust(path, null));
			}
		});
		tableP.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N", "Matricule", "Nom", "Prenom", "Sexe", "Email", "Phone", "Specialite", "Matiere", "Grade", "Residence", "Photo"
			}
		));
		tableP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		scrollPane_1.setViewportView(tableP);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Classe", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Matiere", null, panel_5, null);
		
		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Absence", null, panel_6, null);
		
		JPanel panel_7 = new JPanel();
		tabbedPane.addTab("Note", null, panel_7, null);
		
		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Bulletin", null, panel_8, null);
		panel_8.setLayout(null);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		tabbedPane.addTab("Frais", null, panel_2_2, null);
		
		JPanel panel_11_2 = new JPanel();
		panel_11_2.setLayout(null);
		panel_11_2.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_11_2.setBackground(new Color(0, 204, 255));
		panel_11_2.setBounds(0, 0, 438, 541);
		panel_2_2.add(panel_11_2);
		
		JPanel panel_15_2 = new JPanel();
		panel_15_2.setLayout(null);
		panel_15_2.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_15_2.setBackground(Color.LIGHT_GRAY);
		panel_15_2.setBounds(10, 11, 418, 449);
		panel_11_2.add(panel_15_2);
		
		JLabel lblNewLabel_3_2 = new JLabel("ID Frais");
		lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_3_2.setBounds(10, 36, 115, 14);
		panel_15_2.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("ID Eleve");
		lblNewLabel_4_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_4_2.setBounds(10, 61, 115, 14);
		panel_15_2.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Montant");
		lblNewLabel_5_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5_3.setBounds(10, 114, 115, 14);
		panel_15_2.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_6_2 = new JLabel("Motif");
		lblNewLabel_6_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_6_2.setBounds(10, 89, 115, 14);
		panel_15_2.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_8_1 = new JLabel("Annnee Scolaire");
		lblNewLabel_8_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_8_1.setBounds(10, 178, 115, 14);
		panel_15_2.add(lblNewLabel_8_1);
		
		txtF = new JTextField();
		txtF.setText("2");
		txtF.setForeground(Color.BLUE);
		txtF.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtF.setEditable(false);
		txtF.setColumns(10);
		txtF.setBackground(Color.DARK_GRAY);
		txtF.setBounds(121, 36, 192, 20);
		panel_15_2.add(txtF);
		
		txtMonF = new JTextField();
		/*txtMonF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
				e.consume();
				}
			}
		});*/
		txtMonF.setText("0.0");
		txtMonF.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtMonF.setColumns(10);
		txtMonF.setBounds(121, 114, 192, 20);
		panel_15_2.add(txtMonF);
		
		txtMotF = new JComboBox();
		txtMotF.setModel(new DefaultComboBoxModel(new String[] {"Inscription", "Tranche 1", "Tranche 2", "Tranche 3", "Tranche 4"}));
		txtMotF.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtMotF.setBounds(121, 84, 188, 22);
		panel_15_2.add(txtMotF);
		
		txtIdEleve = new JTextField();
		txtIdEleve.setForeground(Color.BLUE);
		txtIdEleve.setEditable(false);
		txtIdEleve.setColumns(10);
		txtIdEleve.setBackground(Color.DARK_GRAY);
		txtIdEleve.setBounds(121, 61, 192, 20);
		panel_15_2.add(txtIdEleve);
		
		JLabel lblNewLabel_5_1_2 = new JLabel("Date Paiement");
		lblNewLabel_5_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_5_1_2.setBounds(10, 139, 115, 14);
		panel_15_2.add(lblNewLabel_5_1_2);
		
		JButton btnSearchClasse_1 = new JButton("New button");
		btnSearchClasse_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearchEleve.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtSearchEleve, "Saissez l'identifiant de l'eleve !");
				}else {
					int id = Integer.parseInt(txtSearchEleve.getText());
					frais.getId(id);
				}
			}
		});
		btnSearchClasse_1.setBounds(323, 4, 89, 23);
		panel_15_2.add(btnSearchClasse_1);
		
		txtDF = new JDateChooser();
		txtDF.setBounds(121, 142, 129, 20);
		panel_15_2.add(txtDF);
		
		txtAF = new JTextField();
		txtAF.setBounds(121, 172, 86, 20);
		panel_15_2.add(txtAF);
		txtAF.setColumns(10);
		
		txtSearchEleve = new JTextField();
		txtSearchEleve.setFont(new Font("Times New Roman", Font.BOLD, 12));
		txtSearchEleve.setColumns(10);
		txtSearchEleve.setBounds(121, 5, 192, 20);
		panel_15_2.add(txtSearchEleve);
		
		JPanel panel_16_2 = new JPanel();
		panel_16_2.setLayout(null);
		panel_16_2.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_16_2.setBounds(10, 471, 418, 59);
		panel_11_2.add(panel_16_2);
		
		JLabel lbcount_2 = new JLabel("1");
		lbcount_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcount_2.setBounds(10, 11, 63, 31);
		panel_16_2.add(lbcount_2);
		
		JLabel lbcountF_2 = new JLabel("1");
		lbcountF_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountF_2.setBounds(191, 11, 63, 31);
		panel_16_2.add(lbcountF_2);
		
		JLabel lbcountM_2 = new JLabel("0");
		lbcountM_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lbcountM_2.setBounds(345, 11, 63, 31);
		panel_16_2.add(lbcountM_2);
		
		JLabel lblNewLabel_4_1_4 = new JLabel("Nbre total d'eleves");
		lblNewLabel_4_1_4.setForeground(new Color(0, 102, 102));
		lblNewLabel_4_1_4.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_4.setBounds(10, 34, 114, 25);
		panel_16_2.add(lblNewLabel_4_1_4);
		
		JLabel lblNewLabel_4_1_1_2 = new JLabel("Nbre total de garcons");
		lblNewLabel_4_1_1_2.setForeground(new Color(153, 0, 255));
		lblNewLabel_4_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_1_2.setBounds(144, 36, 110, 20);
		panel_16_2.add(lblNewLabel_4_1_1_2);
		
		JLabel lblNewLabel_4_1_2_2 = new JLabel("Nbre total de filles");
		lblNewLabel_4_1_2_2.setForeground(new Color(255, 51, 204));
		lblNewLabel_4_1_2_2.setFont(new Font("Times New Roman", Font.BOLD, 11));
		lblNewLabel_4_1_2_2.setBounds(294, 36, 114, 20);
		panel_16_2.add(lblNewLabel_4_1_2_2);
		
		JPanel panel_12_2 = new JPanel();
		panel_12_2.setLayout(null);
		panel_12_2.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		panel_12_2.setBackground(new Color(0, 153, 204));
		panel_12_2.setBounds(444, 0, 687, 541);
		panel_2_2.add(panel_12_2);
		
		JPanel panel_13_2 = new JPanel();
		panel_13_2.setLayout(null);
		panel_13_2.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_13_2.setBackground(new Color(0, 204, 255));
		panel_13_2.setBounds(10, 11, 667, 64);
		panel_12_2.add(panel_13_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("effectuer des recherches");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(10, 26, 104, 14);
		panel_13_2.add(lblNewLabel_1_2);
		
		textField_13 = new JTextField();
		textField_13.setFont(new Font("Times New Roman", Font.BOLD, 12));
		textField_13.setColumns(10);
		textField_13.setBounds(113, 23, 236, 20);
		panel_13_2.add(textField_13);
		
		JButton btnsearch_2 = new JButton("Rechercher");
		btnsearch_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnsearch_2.setBounds(359, 22, 104, 23);
		panel_13_2.add(btnsearch_2);
		
		JButton btnrefresh_2 = new JButton("Actualiser");
		btnrefresh_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnrefresh_2.setBounds(568, 22, 89, 23);
		panel_13_2.add(btnrefresh_2);
		
		JPanel panel_14_2 = new JPanel();
		panel_14_2.setLayout(null);
		panel_14_2.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		panel_14_2.setBackground(new Color(0, 204, 255));
		panel_14_2.setBounds(10, 465, 667, 47);
		panel_12_2.add(panel_14_2);
		
		JButton btnadd_2 = new JButton("Ajouter");
		btnadd_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearchEleve.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtSearchEleve, "Verifier d'abord l'existance de l'eleve en effectuant une recherche !");
				}else {
					if (isEmptyPrice()) {
						int id = frais.getMax();
						int idel = Integer.parseInt(txtIdEleve.getText());
						String motif = txtMotF.getSelectedItem().toString();
						Double montant = Double.parseDouble(txtMonF.getText());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String date = dateFormat.format(txtDF.getDate());
						String annee = txtAF.getText();
						if (frais.isExistMotif(idel, motif)) {
							JOptionPane.showMessageDialog(txtIdEleve, "Cette eleve a deja paye l'/la " + motif);
							
						}else {
							frais.insertFrais(id, idel, motif, montant, date, annee);
							tableF.setModel(new DefaultTableModel(null, new Object[] {"N°_Frais", "Eleve_ID", "Motif", "Montant(XAF)", "Date Paiement", "Annee Scolaire"}));
							frais.getFraisValue(tableF, "");
							clearPrice();
						}
					}
				}
			}
		});
		btnadd_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnadd_2.setBounds(10, 11, 89, 23);
		panel_14_2.add(btnadd_2);
		
		JButton btnedit_2 = new JButton("Modifier");
		btnedit_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnedit_2.setBounds(109, 11, 89, 23);
		panel_14_2.add(btnedit_2);
		
		JButton btndelete_2 = new JButton("Supprimer");
		btndelete_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btndelete_2.setBounds(208, 11, 89, 23);
		panel_14_2.add(btndelete_2);
		
		JButton btnclear_2 = new JButton("Nettoyer");
		btnclear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnclear_2) {
					clearPrice();
				}
			}
		});
		btnclear_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnclear_2.setBounds(469, 11, 89, 23);
		panel_14_2.add(btnclear_2);
		
		JButton btnlogout_2 = new JButton("Deconnecter");
		btnlogout_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int logout = JOptionPane.showConfirmDialog(btnlogout, "Vous allez etre deconnecte", "select", JOptionPane.YES_NO_OPTION);
				if(logout == 0) {
					Authentification auth = new Authentification();
					acframe.dispose();
					auth.auframe.setVisible(true);
				}
			}
		});
		btnlogout_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnlogout_2.setBounds(568, 11, 89, 23);
		panel_14_2.add(btnlogout_2);
		
		JButton btnprint_2 = new JButton("Imprimer");
		btnprint_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnprint_2.setBounds(370, 11, 89, 23);
		panel_14_2.add(btnprint_2);
		
		JPanel panel_17_2 = new JPanel();
		panel_17_2.setLayout(null);
		panel_17_2.setBounds(10, 86, 667, 368);
		panel_12_2.add(panel_17_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 667, 368);
		panel_17_2.add(scrollPane_2);
		
		tableF = new JTable();
		tableF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				model = (DefaultTableModel) tableF.getModel();
				rowIndex = tableF.getSelectedRow();
				txtF.setText(model.getValueAt(rowIndex, 0).toString());
				txtIdEleve.setText(model.getValueAt(rowIndex, 1).toString());
				String motif = model.getValueAt(rowIndex, 2).toString();
				if(motif.equals("Inscription")) {
					txtMotF.setSelectedIndex(0);
				}else if (motif.equals("Tranche 1")) {
					txtMotF.setSelectedIndex(1);
				}else if (motif.equals("Tranche 2")) {
					txtMotF.setSelectedIndex(2);
				}else if (motif.equals("Tranche 3")) {
					txtMotF.setSelectedIndex(3);
				}else {
					txtMotF.setSelectedIndex(4);
				}
				txtMonF.setText(model.getValueAt(rowIndex, 3).toString());
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(rowIndex, 4).toString());
					txtDF.setDate(date);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				txtAF.setText(model.getValueAt(rowIndex, 5).toString());
			}
		});
		tableF.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N°_Frais", "Eleve_ID", "Motif", "Montant(XAF)", "Date Paiement", "Annee Scolaire"
			}
		));
		tableF.getColumnModel().getColumn(4).setPreferredWidth(90);
		tableF.getColumnModel().getColumn(5).setPreferredWidth(90);
		scrollPane_2.setViewportView(tableF);
		
		JPanel panel_10 = new JPanel();
		tabbedPane.addTab("Aide", null, panel_10, null);
	}
}
