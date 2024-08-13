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

public class Enseignant {

	int idEns;
	String matEns;
	String nomEns;
	String prenomEns;
	String sexeEns;
	String emailEns;
	String phoneEns;
	String photoEns;
	int idMat;
	
	Connection con = Connexion.getConnection();
	PreparedStatement ps;
	
	public int getMax() {
		int id = 0;
		Statement st;
		String sql = "SELECT max(idEns) FROM enseignant";
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
		}
		return id + 1;
	}
	
	public void insertProf(int idEns, String matEns, String nomEns, String prenomEns,
			String sexe, String email, String phone, String specialite, String matiere, String grade, String residence,  String photo) {
		
		String sql = "INSERT INTO enseignant VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEns);
			ps.setString(2, matEns);
			ps.setString(3, nomEns);
			ps.setString(4, prenomEns);
			ps.setString(5, sexe);
			ps.setString(6, email);
			ps.setString(7, phone);
			ps.setString(8, specialite);
			ps.setString(9, matiere);
			ps.setString(10, grade);
			ps.setString(11, residence);
			ps.setString(12, photo);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Le professeur " +nomEns+ " a ete ajoute avec success");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void getProfValue(JTable table, String searchValue) {
		String sql = "SELECT * FROM enseignant WHERE concat(idEns, matEns, nomEns, prenomEns, emailEns, phonEns) LIKE ? ORDER BY idEns desc";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+searchValue+"%");
			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Object[] row;
			while (rs.next()) {
				row = new Object[12];
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
				model.addRow(row);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void updateProf(int idEns, String matEns, String nomEns, String prenomEns,
			String sexe, String email, String phone, String specialite, String matiere, String grade, String residence,  String photo) {
		
		String sql = "UPDATE enseignant SET matEns=?, nomEns=?, prenomEns=?, "
				+ "sexe=?, emailEns=?, phoneEns=?, specialEns=?, matierEns=?, gradEns=?, residence=?, image=? WHERE idEns=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, matEns);
			ps.setString(2, nomEns);
			ps.setString(3, prenomEns);
			ps.setString(4, sexe);
			ps.setString(5, email);
			ps.setString(6, phone);
			ps.setString(7, specialite);
			ps.setString(8, matiere);
			ps.setString(9, grade);
			ps.setString(10, residence);
			ps.setString(11, photo);
			ps.setInt(12, idEns);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "M/Mme " +nomEns+ " a ete modifie avec success");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void deleteProf(int id, String nomEns) {
		int yesOrNo = JOptionPane.showConfirmDialog(null, "la classe qui esr affectee sera supprimee", "Suppression du professeur", JOptionPane.OK_CANCEL_OPTION, 0);
		String sql = "DELETE FROM enseignant WHERE idEns = ?";
		
		if(yesOrNo == JOptionPane.OK_OPTION) {
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);

				if(ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null,  "M/Mme" +nomEns+ " a ete supprime avec success");
				}
			} catch (Exception e) {

				Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public void printProf(JTable table) {
		
		try {
			MessageFormat header = new MessageFormat("LISTE DES PROFESSEURS");
			MessageFormat footer = new MessageFormat("page{0,number,integer}");
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (Exception e) {
			
			Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
}


