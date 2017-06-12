package model;

import java.sql.*;

/**
 * Cette classe prend en paramètre un objet Statement et y intègre des requêtes SQL suivant la méthode appelée.
 * Le constructore ne fait qu'initialiser le paramètre Statement, que les méthodes pourront modifier et exécuter.
 */
public class Requete {

	/**
	 * L'objet Statement à modifier et exécuter pour exécuter les requêtes SQL.
	 */
	private Statement state;
	
	/**
	 * Constructeur de la classe. Prend en paramètre un objet Statement pour initialiser son paramètre state.
	 * @param state le Statement à modifier et exécuter
	 */
	public Requete(Statement state) {
		
	}
	
	/**
	 * Récupère le choix fait par l'utilisateur et exécute la requête correspondante.
	 * @param choix l'entier correspondant au choix fait par l'utilisateur
	 */
	public void MenuChoix(int choix) {

	}
	
	/** 
	 * Intègre à l'attribut state la commande SQL permettant de créer une nouvelle table à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int ajouterTable() {
		
		return 0;
	}
	
	/** 
	 * Intègre à l'attribut state la commande SQL permettant d'ajouter un tuple à la table précisée par l'utilisateur, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int ajouterTuple() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant d'ajouter un trigger à la table précisée par l'utilisateur, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int ajouterTrigger() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant d'ajouter une vue à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int ajouterVue() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant de supprimer une nouvelle table à la base de données à laquelle l'utilisateur, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int enleverTable() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant de supprimer un tuple à la table précisée par l'utilisateur, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int enleverTuple() {
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant de supprimer un trigger de la base de données à laquelle l'utilisateur, puis exécute cette requête.
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int enleverTrigger() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL permettant de supprimer une vue à la base de données à laquelle l'utilisateur est connecté, puis exécute cette requête.4
	 * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	 */
	public int enleverVue() {
		
		return 0;
	}
	
	/**
	 * Intègre à l'attribut state la commande SQL écrite par l'utilisateur, puis exécute cette requête.
	 * @return true true si l'instruction renvoie un ResultSet, false sinon
	 */
	public boolean manuel() {
		
		return true;
	}
}