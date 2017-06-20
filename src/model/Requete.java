package model;
import java.util.*;
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
	private String table;
	
	/**
	 * Constructeur de la classe. Prend en paramètre un objet Connection en paramètre et créé un objet Statement sur cette connexion. Stocke le Statement dans son attribut state.
	 * @param connexion la connexion sur laquelle créer un Statement
	 * @param table le nome de la table sur laquelle exécuter la requête
	 * @throws SQLException si la connexion est invalide, ou qu'une autre erreur SQL survient
	 */
	public Requete(Connection connexion,String table) throws SQLException{
		this.table = table;
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
		return ret;
	}	
	
	
	/** 
	  * Supprime la table dont le nom est donné en paramètre si celle-ci existe, puis créé une chaîne de caractère contenant une requête permettant de créer la table
	  * avec le nom et les attributs passés en paramètre, puis exécute cette requête.
	  * @param nomTable le nom de la table à ajouter
	  * @param listeAttribut la liste des attributs de la table 
	  * @return le nombre de ligne insérées et/ou modifiées et/ou supprimées
	  * @throws SQLException si l'ajout de la table est impossible à cause d'une erreur SQL
	  * @throws Exception si l'ajout de la table est impossible à cause d'une autre erreur
	  */
	 public int ajouterTable(String nomTable, ArrayList<Attribut> listeAttribut) throws SQLException,Exception {			
		int ret;
	 	try{
	 		manuel("DROP TABLE "+nomTable);
	 	}
	 	catch (SQLException se){
	 		throw se;
	 	}
	 	catch (Exception e){
	 		throw e;
	 	}
		
		String requete = "CREATE TABLE "+nomTable+"(\n";
		
		for(Attribut monAtt : listeAttribut){
			requete = requete + monAtt.getNomVariable() +" "+monAtt.getType();
			if(monAtt.getValeur()>-1) requete =requete +" ("+monAtt.getValeur()+")";
			if(!monAtt.getContrainte().equals("")){
				requete = requete + monAtt.getContrainte() +",\n";
			}
			else{
				requete = requete + ",\n";
			}
		}

		for(Attribut monAtt : listeAttribut){
			if(monAtt.getAClePrimaire()){
				requete = requete + "CONSTRAINT pk"+nomTable+" PRIMARY KEY ("+monAtt.getNomVariable()+")";
			}
		}
	
		ret = creerOuModifier(requete);
		return ret;
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



	public String retournerResultSet(ResultSet rs,boolean withColumnName) throws SQLException,Exception{
		String ret="";
		try{
		
			
			ResultSetMetaData rsmd = rs.getMetaData();
		   	int columnsNumber = rsmd.getColumnCount();
		   	while (rs.next()) {
		       for (int i = 1; i <= columnsNumber; i++) {
		           	if (i > 1) ret=ret+",  ";
		           	String columnValue = rs.getString(i);
		           	String columnName = rsmd.getColumnName(i);
		           	if(withColumnName){
		           		if(i==1){
		           			ret = ret +columnName+" "+ columnValue + " \n";
		           		}
		           		else{
		           			ret = ret + columnValue +" \n";
		           		}
		           	}
		           	else{
		           		ret=ret+columnValue + " \n";
		           	}

		         	
		         	
		       }

			}

		}
		catch(SQLException se){
			throw se;
		}
		catch(Exception e){
			throw e;
		}

		return ret;
	}


}