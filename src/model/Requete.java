package model;

import java.sql.*;

/**
 * Cette classe prend en paramètre un objet Connexion et créée un objet Statement avec lequel on peut effectuer des commandes SQL sur la base de données.
 * Les méthodes de la classe prennent une chaîne de caractère qui représente une commande SQL. Ces méthodes ont des usages différents :
 * <P> manuel() permet d'exécuter n'importe quelle commande SQL
 * <P> 
 * Le constructore ne fait qu'initialiser le paramètre Statement, que les méthodes pourront modifier et exécuter.
 */
public class Requete {

	/**
	 * L'objet Statement à modifier et exécuter pour exécuter les requêtes SQL.
	 */
	private Statement state;
	
	/**
	 * Constructeur de la classe. Prend en paramètre un objet Connection en paramètre et créé un objet Statement sur cette connexion. Stocke le Statement dans son attribut state.
	 * @param connexion la connexion sur laquelle créer un Statement
	 * @throws SQLException si la connexion est invalide, ou qu'une autre erreur SQL survient
	 */
	public Requete(Connection connexion) throws SQLException{
		
		try {
			this.state = connexion.createStatement();
		}
		catch(SQLException sqle) {
			
			System.out.println("Erreur SQL : ");
			throw sqle;
		}
	}
	
	 // /**
	 // * Récupère le choix fait par l'utilisateur et exécute la requête correspondante.
	 // * @param choix l'entier correspondant au choix fait par l'utilisateur
	 // */
	// public void MenuChoix(int choix) {
		
		// if (choix == 0) {
			
			// ajouterTable();
		// }
		// else if (choix == 1) {
			
			// ajouterTuple();
		// }
		// else if (choix == 2) {
			
			// ajouterTrigger();
		// }
		// else if (choix == 3) {
			
			// ajouterVue();
		// }
		// else if (choix == 4) {
			
			// enleverTable();
		// }
		// else if (choix == 5) {
			
			// enleverTuple();
		// }
		// else if (choix == 6) {
			
			// enleverTrigger();
		// }
		// else if (choix == 7) {
			
			// enleverVue();
		// }
		// else if (choix == 8) {
			
			// manuel();
		// }
		// else System.out.println("Choix non reconnu");
	// }

	/** 
	 * Intègre à l'attribut state la commande SQL passée en paramètre, puis exécute cette requête. Retourne le nombre de tuples modifiés par la requête
	 * Cette méthode est utilisée pour créer ou supprimer des tables, vues ou triggers, mais aussi modifier, supprimer ou ajouter des tuples à une table existante.
	 * @param requete la requete à exécuter
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 * @throws SQLException si la requete est incorrecte, que state n'est pas initialisé ou si une autre erreur SQL survient
	 * @throws Exception si la connexion ne peut pas être fermée ou si une autre erreur survient
	 */
	public int creerOuModifier(String requete) throws SQLException, Exception{			
		
		int ret = 0;
		
		try {
			
			ret = state.executeUpdate(requete);		
		}
		catch(SQLException sqle) {
			System.out.println("Erreur SQL : ");
			throw sqle;
		}
		finally{ 
			if(state !=null){
				try{
					state.close();
				}
				catch(Exception e){
					System.out.println("Impossible de fermer la connexion");
					throw e;
				}
			} 
		}
		return ret;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL donnée en paramètre, puis exécute cette requête.
	 * Cette commande est générale, elle peut être utilisée pour créer, supprimer ou modifier des éléments, mais aussi pour faire des requêtes et en récupérer le résultat.
	 * @param requete la requete à exécuter
	 * @param resultat le ResultSet dans lequel sera stocké le résultat s'il y en a un.
	 * @return true si l'instruction renvoie un ResultSet, false sinon
	 * @throws SQLException si la requete est incorrecte, que state n'est pas initialisé ou si une autre erreur SQL survient
	 * @throws Exception si la connexion ne peut pas être fermée ou si une autre erreur survient
	 */
	public Object[] manuel(String requete) throws SQLException, Exception{
		Object[] ret = new Object[3];
		ret[2] = 0;
		
		try {
			ret[0] = state.execute(requete);
			if ((boolean)ret[0]) {
				 ret[1] = state.getResultSet();
			}
			else {
				ret[2] = state.getUpdateCount();
			}
		}
		catch(SQLException sqle) {
			System.out.println("Erreur SQL : ");
			throw sqle;
		}
		/*finally{ 
			if(state !=null){
				try{
					state.close();
				}
				catch(Exception e){
					System.out.println("Impossible de fermer la connexion");
					throw e;
				}
			} 
		}*/
		System.out.println(ret[2]);
		return ret;
	}	
	
	
	// /** 
	 // * Intègre à l'attribut state la commande SQL permettant de créer une nouvelle table à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int ajouterTable(String requete) {			
		
		// ret 0;
	// }
	
	// /** 
	 // * Intègre à l'attribut state la commande SQL permettant d'ajouter un tuple à la table précisée par l'utilisateur, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int ajouterTuple() {
		
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant d'ajouter un trigger à la table précisée par l'utilisateur, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int ajouterTrigger() {
		
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant d'ajouter une vue à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int ajouterVue() {
		
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant de supprimer une nouvelle table à la base de données à laquelle l'utilisateur, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int enleverTable() {
		
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant de supprimer un tuple à la table précisée par l'utilisateur, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int enleverTuple() {
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant de supprimer un trigger de la base de données à laquelle l'utilisateur, puis exécute cette requête.
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int enleverTrigger() {
		
		// return 0;
	// }
	
	// /**
	 // * Intègre à l'attribut state la commande SQL permettant de supprimer une vue à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.4
	 // * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 // */
	// public int enleverVue() {
		
		// return 0;
	// }
}