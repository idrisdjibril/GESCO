package com.java.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

	private static Connection con = null;
	private static final String user = "root";
	private static final String host = "jdbc:mysql://localhost/bd";
	private static final String password = "";

	public static Connection getConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(host,user,password);
			System.out.println("Connexion etablie avec succes !!!");
			
		}
		catch(Exception e){
			System.out.println("Il y'a un soucis avec la connexion a la base de donnees !!!");
			
		}
		return con;
	}


}
