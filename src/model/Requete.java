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
	 *
	 */
	public int MenuChoix() {
		
		return 0;
	
	}
	
	/** 
	 *
	 */
	public int ajouterTable() {
		
		return 0;
	}
	
	/** 
	 *
	 */
	public int ajouterTuple() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int ajouterTrigger() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int ajouterVue() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int enleverTable() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int enleverTuple() {
		return 0;
	}
	
	/**
	 *
	 */
	public int enleverTrigger() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int enleverVue() {
		
		return 0;
	}
	
	/**
	 *
	 */
	public int manuel() {
		
		return 0;
	}
}