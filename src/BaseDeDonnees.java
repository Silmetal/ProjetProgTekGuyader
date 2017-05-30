package model;

import java.sql.*;

/**
 * Cette classe établit une connexion à une base de données grâce à JDBC et les classes de java.sql.
 * La classe crée un objet Connnection sur lequel on peut effectuer des requêtes grâce à l'objet Statement également crée et à la classe Requête.
 * Exemple pour oracle : 
 * /n BaseDeDonnees bdd = new BaseDeDonnees("jdbc:oracle:thin:@localhsot:1521:xe", "root", "root")
 * "jdbc:oracle:thin:" indique que l'on se connect à une BDD oracle.
 * /n"localhost" correspond au nom d'hôte
 * /n"1521" correspond au port de la base
 * /n"xe" correspond au SID.
 * Les deux derniers champs sont, respectivement, le nom d'utilisateur et le mot de passe. 
 */
public class BaseDeDonnees {
	
	/**
	 * L'objet Connection représente le lien entre l'application et la base de données. Il est défini lors de l'appel à la méthode connexion.
	 */
	private Connection connexion;
	
	/**
	 * Constructeur de la classe. 
	 */
	public BaseDeDonnees(String adresse, String nomUtili, String motDePasse){
		
	}
	
	/**
	 *
	 */
	private boolean verifPilote() {
		
		boolean ret = false;
		
		return ret;
	}
	
	/**
	 *
	 */
	private boolean connexion(String adresse, String motDePasse) {
		
		boolean ret = false;
		
		return ret;
	}
	
	/**
	 *
	 */
	private Statement creerRequete(){
		
		Statement test = null;
		
		return test;		
	}
	
	/**
	 *
	 */
	public void ajouterNouvelUtilisateur(){
		
	}
	
	/**
	 *
	 */
	public void ecrire(){
		
	}
	
	/**
	 *
	 */	 
	public void lire(){
		
	}
	
}