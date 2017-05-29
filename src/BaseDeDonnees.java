package model;

import java.sql.*;

/**
 * Cette classe établit une connexion à une base de données grâce à JDBC et les classes de java.sql.
 * La classe crée un objet Connnection sur lequel on peut effectuer des requêtes grâce à l'objet Statement également crée et à la classe Requête.
 * Exemple pour oracle : 
 * /n BaseDeDonnees bdd = new BaseDeDonnees("jdbc:oracle:thin:@localhsot:1521:xe", "root", "root")
 * "jdbc:oracle:thin:" indique que l'on se connect à une BDD oracle.
 * /n "localhost" correspond au nom d'hôte
 * /n "1521" correspond au port de la base
 * /n "xe" correspond au SID.
 * Les deux derniers champs sont, respectivement, le nom d'utilisateur et le mot de passe.
 * 
 */
public BaseDeDonnees {
	
	/**
	 *
	 */
	private Connection connection;
	
	/**
	 *
	 */
	public BaseDeDonnees(String adresse, String nomUtili, String motDePasse){
		
	}
	
	/**
	 *
	 */
	private boolean verifDriver() {
		
	}
	
	/**
	 *
	 */
	private boolean connexion(String adresse, String motDePasse) {
		
	}
	
	/**
	 *
	 */
	private Statement createStatement(){
		
	}
	
	/**
	 *
	 */
	public void addNewID(){
		
	}
	
	/**
	 *
	 */
	public void write(){
		
	}
	
	/**
	 *
	 */	 
	public void read(){
		
	}
	
}