package model;

import java.sql.*;

/**
 * Cette classe établit une connexion à une base de données grâce à JDBC et les classes de java.sql.
 * La classe crée un objet Connnection sur lequel on peut effectuer des requêtes grâce à l'objet Statement également crée et à la classe Requête.
 * Exemple pour oracle : 
 * <P>BaseDeDonnees bdd = new BaseDeDonnees("jdbc:oracle:thin:@localhsot:1521:xe", "root", "root")
 * "jdbc:oracle:thin:" indique que l'on se connect à une BDD oracle.
 * <P>"localhost" correspond au nom d'hôte
 * <P>"1521" correspond au port de la base
 * <P>"xe" correspond au SID.
 * <P>Les deux derniers champs sont, respectivement, le nom d'utilisateur et le mot de passe. 
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
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			ret = true;
		}
		
		catch (ClassNotFoundException e) {
			
			System.out.println("Pilote non trouvé");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * Appelle la méthode verifPilote, puis, si le pilote est présent, essaye d'établir une connexion en créant un objet Connection avec les paramètres en appellant les méthodes de JDBC.
	 * Si la connexion est établit, place l'objet Connection crée dans l'attribut connexion
	 * @param adresse l'adresse de la base à laquelle on essaye de se connecter.
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter.
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur.
	 * @return true si la connexion est établie, false sinon.
	 */
	private boolean connexion(String adresse, String nomUtili, String motDePasse) {
		
		try {
			
			connexion = DriverManager.getConnection(adresse, nomUtili, motDePasse);
		}
		
		catch(SQLExeception e) {
			
			System.out.println("Connexion échouée ! Vérifiez vos identifiants et l'adresse de connexion");
			e.print
		
		return ret;
	}
	
	/**
	 * Créer un objet Statement qui permettra d'exécuter des requêtes SQL 
	 * si l'attribut connexion est non null.
	 * @return l'objet Statement crée
	 */
	private Statement creerRequete(){
		
		Statement requete = null;
		
		try {
			requete = connexion.createStatement();
		}
		catch (SQLException se) {
		
			se.printStackTrace();
		}		
		
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