package com.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.db.Connexion;

public class Classe {

	int idCla;
	String codeCla;
	String libelle;
	
	Connection con = Connexion.getConnection();
	PreparedStatement ps;
	
	public int getMax() {

		int id = 0;
		Statement st;
		String sql = "SELECT max(idClasse) FROM Classe";
		
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
	
}
