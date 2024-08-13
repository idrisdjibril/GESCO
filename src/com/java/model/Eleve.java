package com.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;

import com.java.db.Connexion;
import com.java.views.Acceuil;

public class Eleve {

	int idEl;
	String matEl;
	String nomEl;
	String prenomEl;
	String dateEl;
	String lieuEl;
	String sexeEl;
	String classeEl;
	String emailEl;
	String phoneEl;
	String photoEl;
	int idTu;
	
	Connection con = Connexion.getConnection();
	PreparedStatement ps;
	
	public int getMax() {

		int id = 0;
		Statement st;
		String sql = "SELECT max(idEl) FROM eleve";
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return id + 1;
	}
	
	public boolean getId(int id) {
		try {
			ps = con.prepareStatement("SELECT * FROM classe WHERE idClasse = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Acceuil.txtIdClasse.setText(String.valueOf(rs.getInt(1)));
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Cette classe n'existe pas !");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}
	
	public int getCount() {
		int id = 0;
		Statement st;
		String sql = "SELECT count(idEl) FROM eleve";
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}
	
	public int getCountBySex(String sexe) {
		int id = 0;
		String sql = "SELECT count(idEl) FROM eleve WHERE sexe = '"+ sexe +"'";
		
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return id;
	}
	
	public void SearchByClasse(JTable table, String classe) {
		
		String sql = "SELECT * FROM eleve WHERE sexe = '"+ classe +"'";
		
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Object[] row;
			while(rs.next()) {
				row = new Object[14];
				row[0] = rs.getInt(1);
				row[1] = rs.getString(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(8);
				row[8] = rs.getString(9);
				row[9] = rs.getString(10);
				row[10] = rs.getString(11);
				row[11] = rs.getString(12);
				row[12] = rs.getString(13);
				row[13] = rs.getString(14);
				row[14] = rs.getString(15);
				model.addRow(row);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void insert(int idEl, int idClasse, String matEl, String nomEl, String prenomEl, String dateEl, String lieuEl,
			String sexeEl, String emailEl, String phoneEl, String pere, String mere, String residence,  String photoEl) {
		
		String sql = "INSERT INTO eleve VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEl);
			ps.setInt(2, idClasse);
			ps.setString(3, matEl);
			ps.setString(4, nomEl);
			ps.setString(5, prenomEl);
			ps.setString(6, dateEl);
			ps.setString(7, lieuEl);
			ps.setString(8, sexeEl);
			ps.setString(9, emailEl);
			ps.setString(10, phoneEl);
			ps.setString(11, pere);
			ps.setString(12, mere);
			ps.setString(13, residence);
			ps.setString(14, photoEl);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "L'eleve " +nomEl+ " a ete ajoute avec success");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public boolean isEmailExist(String emailEL) {
		String sql = "SELECT * FROM eleve WHERE email = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, emailEl);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return true;
	}
	
	public boolean isPhoneExist(String phoneEl) {
		String sql = "SELECT * FROM eleve WHERE phone = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, phoneEl);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return true;
	}
	
	public boolean isMatriculeExist(String matEl) {
		String sql = "SELECT * FROM eleve WHERE matEl = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, matEl);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return true;
	}
	
	public boolean isIdExist(int id) {
		String sql = "SELECT * FROM eleve WHERE idEl = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return false;
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
		return true;
	}
	
	public void getStudentValue(JTable table, String searchValue) {
		String sql = "SELECT * FROM eleve WHERE concat(idEL, matEl, nomELeve, prenomEl, email, phone) LIKE ? ORDER BY idEl desc";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+searchValue+"%");
			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Object[] row;
			while (rs.next()) {
				row = new Object[14];
				row[0] = rs.getInt(1);
				row[1] = rs.getInt(2);
				row[2] = rs.getString(3);
				row[3] = rs.getString(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				row[6] = rs.getString(7);
				row[7] = rs.getString(8);
				row[8] = rs.getString(9);
				row[9] = rs.getString(10);
				row[10] = rs.getString(11);
				row[11] = rs.getString(12);
				row[12] = rs.getString(13);
				row[13] = rs.getString(14);
				model.addRow(row);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void update(int idEl, int idClasse, String matEl, String nomEl, String prenomEl, String dateEl, String lieuEl,
			String sexeEl, String emailEl, String phoneEl, String pere, String mere, String residence,  String photoEl) {
		
		String sql = "UPDATE eleve SET idClasse=?, matEl=?, nomEleve=?, prenomEl=?, dateNaissance=?, lieuNaissance=?, "
				+ "sexe=?, email=?, phone=?, nomPereEleve=?, nomMereEleve=?, residence=?, image=? WHERE idEl=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idClasse);
			ps.setString(2, matEl);
			ps.setString(3, nomEl);
			ps.setString(4, prenomEl);
			ps.setString(5, dateEl);
			ps.setString(6, lieuEl);
			ps.setString(7, sexeEl);
			ps.setString(8, emailEl);
			ps.setString(9, phoneEl);
			ps.setString(10, pere);
			ps.setString(11, mere);
			ps.setString(12, residence);
			ps.setString(13, photoEl);
			ps.setInt(14, idEl);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "L'eleve " +nomEl+ " a ete modifie avec success");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void delete(int id, String nomEl) {
		int yesOrNo = JOptionPane.showConfirmDialog(null, "les notes, absences et paiement seront supprimes", "Supprime l'eleve", JOptionPane.OK_CANCEL_OPTION, 0);
		String sql = "DELETE FROM eleve WHERE idEl = ?";
		
		if(yesOrNo == JOptionPane.OK_OPTION) {
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);

				if(ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null,  "L'eleve" +nomEl+ " a ete supprime avec success");
				}
			} catch (Exception e) {

				Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public void print(JTable table) {
		
		try {
			MessageFormat header = new MessageFormat("LISTE DES ELEVES");
			MessageFormat footer = new MessageFormat("page{0,number,integer}");
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (Exception e) {
			
			Logger.getLogger(Eleve.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
}
