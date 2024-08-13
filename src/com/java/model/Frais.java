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

public class Frais {

	int idPai;
	String anneScolaire;
	String dateIns;
	Double Inscription;
	Double Montant1;
	Double Montant2;
	Double Montant3;
	Double Montant4;
	
	
	Connection con = Connexion.getConnection();
	PreparedStatement ps;
	
	public int getMax() {
		int id = 0;
		Statement st;
		String sql = "SELECT max(idPaie) FROM frais";
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
		return id + 1;
	}
	
	public boolean getId(int id) {
		try {
			ps = con.prepareStatement("SELECT * FROM eleve WHERE idEl = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Acceuil.txtIdEleve.setText(String.valueOf(rs.getInt(1)));
				return true;
			}else {
				JOptionPane.showMessageDialog(null, "Cette eleve n'existe pas !");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}
	
	public boolean isExistMotif(int id, String motif) {
		String sql = "SELECT * FROM frais WHERE idEl = ? AND motif = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, motif);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}
	
	public void insertFrais(int idPaie, int idEl, String motif, Double montant, String date, String annee) {
		
		String sql = "INSERT INTO frais VALUES(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idPaie);
			ps.setInt(2, idEl);
			ps.setString(3, motif);
			ps.setDouble(4, montant);
			ps.setString(5, date);
			ps.setString(6, annee);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "operation effectuee avec succes");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void getFraisValue(JTable table, String searchValue) {
		String sql = "SELECT * FROM frais WHERE concat(idPaie, idEl, motif, datPaie, annScolaire) LIKE ? ORDER BY idPaie desc";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+searchValue+"%");
			ResultSet rs = ps.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			Object[] row;
			while (rs.next()) {
				row = new Object[6];
				row[0] = rs.getInt(1);
				row[1] = rs.getInt(2);
				row[2] = rs.getString(3);
				row[3] = rs.getDouble(4);
				row[4] = rs.getString(5);
				row[5] = rs.getString(6);
				model.addRow(row);
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void updateFrais(int idPaie, int idEl, String motif, Double montant, String date, String annee) {
		
		String sql = "UPDATE frais SET idEl=?, motif=?, montant=?, datPaie=?, annScolaire=? WHERE idPaie=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEl);
			ps.setString(2, motif);
			ps.setDouble(3, montant);
			ps.setString(4, date);
			ps.setString(5, annee);
			ps.setInt(6, idPaie);
						
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Mise a jour effectue avec succes");
			}
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public void deleteFrais(int id) {
		int yesOrNo = JOptionPane.showConfirmDialog(null, "l'eleve sera supprime", "Suppression des transactions", JOptionPane.OK_CANCEL_OPTION, 0);
		String sql = "DELETE FROM frais WHERE idPaie = ?";
		
		if(yesOrNo == JOptionPane.OK_OPTION) {
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);

				if(ps.executeUpdate() > 0) {
					JOptionPane.showMessageDialog(null,  "suppression de la transaction");
				}
			} catch (Exception e) {

				Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	public void printFrais(JTable table) {
		
		try {
			MessageFormat header = new MessageFormat("FICHE DE PAIEMENT");
			MessageFormat footer = new MessageFormat("page{0,number,integer}");
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (Exception e) {
			
			Logger.getLogger(Frais.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
