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
 * /nLes deux derniers champs sont, respectivement, le nom d'utilisateur et le mot de passe. 
 */
public class BaseDeDonnees {
	
	/**
	 * L'objet Connection représente le lien entre l'application et la base de données. Il est défini lors de l'appel à la méthode connexion.
	 */
	private Connection connexion;
	
	/**
	 * Constructeur de la classe. Utilise les paramètres pour créer l'objet connexion avec la méthode connexion, puis utilise la
	 * méthode créerRequete pour créer l'objet requete.
	 * @param adresse l'adresse de la base de données
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur utilisé pour se connecter.
	 */
	public BaseDeDonnees(String adresse, String nomUtili, String motDePasse){
		
	}
	
	/**
	 * Vérifie la présence du pilote correspondant à la base à laquelle on essaye de se connecter.
	 * @return true si le pilote est présent, false sinon.
	 */
	private boolean verifPilote() {
		
		boolean ret = false;
		
		return ret;
	}
	
	/**
	 * Appelle la méthode verifPilote, puis, si le pilote est présent, essaye d'établir une connexion avec les paramètres en appellant les méthodes de JDBC.
	 * @param adresse l'adresse de la base à laquelle on essaye de se connecter.
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter.
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur.
	 * @return true si la connexion est établie, false sinon.
	 */
	private boolean connexion(String adresse, String nomUtili, String motDePasse) {
		
		boolean ret = false;
		
		return ret;
	}
	
	/**
	 * Créer un objet Statement qui permettra d'exécuter des requêtes SQL
	 * @return l'objet Statement crée
	 */
	private Statement creerRequete(){
		
		Statement requete = connexion.createStatement();
		
		return requete;		
	}
	
	/**
	 * Prend en paramètre un nom d'utilisateur et un mot de passe pour créer un nouvel utilisateur et l'ajouter à la base.
	 * L'utilsiateur ainsi crée pourra se connecter à la base de données avec ces identifiants.
	 * @param nouvIdenti l'identifiant du nouvel utilisateur
	 * @param nouvMDP le mot de passe du nouvel utilisateur
	 */
	public void ajouterNouvelUtilisateur(String nouvIdenti, String nouvMDP){
		
		
		
	}
	
	/**
	 * Enregistre la base de données dans un fichier.
	 * Le fichier contiendra toutes les requêtes SQL nécessaires pour créer une nouvelle base de données
	 * identique à celle à laquelle l'utilsiateur est connecté.
	 * @param fileName le nom du fichier crée
	 */
	public void ecrire(String fileName){
		
	}
	
	/**
	 * Exécute les requêtes SQL de création d'une base de données stockées dans un fichier.
	 * @param fileName le nom du fichier à lire
	 */	 
	public void lire(String fileName){
		
	}
	
}