package com.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.java.db.Connexion;
import com.java.views.Acceuil;
import com.java.views.Authentification;

public class User {

	int idUser;
	String username;
	String password;
	String role;
	int etat;
	
	Connection con = Connexion.getConnection();
	PreparedStatement ps;
	
	
	public void authentifier(String username, String password, JButton bcon){
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
				Authentification auth = new Authentification();
				auth.auframe.dispose();
			}else {
				JOptionPane.showMessageDialog(bcon, "Nom d'utilisateur ou Mot de passe invalide", "Connexion echouee", 2);
			}
		} catch (Exception ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void insertUser(int id, String username, String password, String role, String etat) {
		
		String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.setString(4, role);
			ps.setString(5, etat);
			
			if(ps.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "L'utilisateur " +username+ " a ete ajoute avec succes");
			}
		} catch (Exception e) {
			
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}

