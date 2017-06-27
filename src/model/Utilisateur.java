package model;

import java.sql.*;
import java.util.*;
import utilitaire.*;


/** Cette classe permet d'instancier des utilisateurs de la base de donnée
 *  La classe permet de se connecter/déconnecter à différentes bases de données
 *  
 */
public class Utilisateur {
	/**
	 * La chaine de caractère est un identifiant unique qui permet d'identifier
	 * chaques utilisateurs.
	 */
	private String id;
	
	/**
	 * La liste de bases de données à laquelle l'utilisateur est actuellement connecté
	 */
	private ArrayList<BaseDeDonnees> lesBasesDeDonnees;
	
	/**
	 * L'indice de la base de données actuellement selectionné
	 */
	private int selection;
	
	/**
	 * La table actuellement sélectionnée
	 */
	private String table;
	
	/**
	 * Associe les bases de données contenue dans l'ArrayList à des clés.
	 */
	private HashMap<String,Integer> association;


	/** Constructeur de la classe. Il prend en paramètre une chaine de caractère
	* qui est un identifiant qui au préalable a été comparé dans la méthode qui
	* l'instancie.
	* @param id  Une chaine de caractère : un identifiant unique
	*/
	public Utilisateur(String id) {
		table="";
		selection = -1;
		this.id=id;
		lesBasesDeDonnees = new ArrayList<BaseDeDonnees>();
		
	}
	

	/** Permet de se connecter a une base de donnée oracle ou mySQL.
	* @param url L'adresse de la base de donnée
	* @param user Un nom d'utilisateur (Attention selon l'endroit où vous vous connectez 
	* , il est nécéssaire d'avoir des droits particuliers, prenez donc en compte se détail)
	* @param password Le mot de passe de l'utilisateur pour accéder à la base de donnée
	* @param nomDeLaBase le nom de la base (Au choix)
	* @throws ClassNotFoundException si le driver correspondant n'est pas trouvé
	* @throws SQLException si une erreur SQL empêche la connexion à la base de données
	* @throws Exception si une autre erreur empe^che la connexion
	*/
	public void connect(String url,String user, String password,String nomDeLaBase) throws ClassNotFoundException, SQLException, Exception{
		ArrayList<BaseDeDonnees> lesBases = new ArrayList<BaseDeDonnees>();
		DatabaseMetaData dmd;
		ResultSet tables;
		int i=0;

		ArrayList<String> lesTables = new ArrayList<String>();
		BaseDeDonnees laBase;

		try {
			
			laBase = new BaseDeDonnees(url,user,password,nomDeLaBase);
			lesTables = laBase.parcourirBase();

			
			if(lesTables.size()==0){
				try{
					
					Requete interogation = new Requete(laBase.getConnection(),nomDeLaBase,table);
					ResultSet rs = (ResultSet) ((interogation.manuel("SHOW DATABASES;"))[1]);
					ResultSetMetaData rsmd = rs.getMetaData();
			   		int columnsNumber = rsmd.getColumnCount();		
			   		while (rs.next()) {
				       for (int j = 1; j <= columnsNumber; j++) {

				           	String columnValue = rs.getString(j);

				           	if(!columnValue.equals("")){
				           		BaseDeDonnees uneBase = new BaseDeDonnees(url+columnValue+"?allowMultiQueries=true",user,password,columnValue);
				           		lesBases.add(uneBase);

				           		lesTables = laBase.parcourirBase();
				           	}
				       }
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}

				for(BaseDeDonnees base : lesBases){
					lesBasesDeDonnees.add(base);
				}

			}
			else{
				laBase = new BaseDeDonnees(url+"?allowMultiQueries=true",user,password,nomDeLaBase);
				lesBasesDeDonnees.add(laBase);
			}

			selection = -1;
		}
		catch (ClassNotFoundException cnfe) {
			throw cnfe;
		}
		catch (SQLException sqle) {
			throw sqle;
		}
		catch (Exception e) {
			throw e;
		}
	}

	private void miseAJourDuHashMap(){
		int i=0;
		association = new HashMap<String,Integer>();

		for (BaseDeDonnees base : lesBasesDeDonnees) {
			association.put(base.getNomDeLaBase(),i);
			i++;
		}
	}
	
	/**
	 * Créée une nouvelle base de données.
	 * @param motDePasse le mot de passe de la base sélectionnée, demandé par mesure de sécurité
	 * @param nomBase le nom de la nouvelle base 
	 * @param nouvelleRequete l'objet Requete qui va recevoir et exécuter la requête de création de base.
	 * @throws SQLException si une erreur SQL empêche la création de la base
	 * @throws Exception si une autre erreur empêche la création de la base
	 */
	public void creerBaseDeDonnees(String motDePasse,String nomBase, Requete nouvelleRequete) throws SQLException, Exception{
		BaseDeDonnees laBaseSelectionee = lesBasesDeDonnees.get(selection);
		BaseDeDonnees uneBase = new BaseDeDonnees(laBaseSelectionee.getAdresse(),laBaseSelectionee.getNomUtili(),motDePasse,laBaseSelectionee.getNomDeLaBase());
	  	nouvelleRequete.manuel("CREATE DATABASE "+nomBase);
		String url = laBaseSelectionee.getAdresse();
		url = ModifierString.supprimerExtrait(url,laBaseSelectionee.getNomDeLaBase()+"?allowMultiQueries=true");
		BaseDeDonnees uneBase2 = new BaseDeDonnees(url+nomBase+"?allowMultiQueries=true",laBaseSelectionee.getNomUtili(),motDePasse,nomBase);
		lesBasesDeDonnees.add(uneBase2);
	}
	
	/**
	 * Supprime la base de données sélectionnée
	 * @param motDePasse le mot de passe de la base sélectionnée, demandé par mesure de sécurité
	 * @param nouvelleRequete l'objet Requete qui va recevoir et exécuter la requête de suppression de base.
	 * @throws SQLException si une erreur SQL empêche la suppression de la base
	 * @throws Exception si une autre erreur empêche la suppression de la base
	 */
	public void supprimerBaseDeDonnees(String motDePasse,Requete nouvelleRequete)throws SQLException, Exception{
		BaseDeDonnees laBaseSelectionee = lesBasesDeDonnees.get(selection);
		BaseDeDonnees uneBase = new BaseDeDonnees(laBaseSelectionee.getAdresse(),laBaseSelectionee.getNomUtili(),motDePasse,laBaseSelectionee.getNomDeLaBase());
		nouvelleRequete.manuel("DROP DATABASE "+laBaseSelectionee.getNomDeLaBase());
		lesBasesDeDonnees.remove(laBaseSelectionee);
	}
	
	/**
	 * Retourne la position de la base dans la liste de BaseDeDonnees de l'utilisateur
	 * @param base le nom de la base dont on veut la position
	 * @return la position de la base dont le nom est passé en paramètre
	 */
	public int getPositionBase(String base){
		miseAJourDuHashMap();
		int ret = association.get(base);
		return ret;
	}

	/**
	 * Change la base actuellement sélectionnée
	 * @param table le nom de la table à sélectionner
	 */
	public void setTable(String table){
		this.table=table;
	}
	
	/**
	 * Retourne le nom de la base sélectionnée
	 * @return le nom de la base sélectionnée
	 */
	public String getTable(){
		return this.table;
	}
	
	
	/* public String toString() {
		
		return "";
		
	} */
	
	/**
	 * Retourne l'identifiant de l'utilisateur
	 * @return l'identifiant de l'utilisateur
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Change la base sélectionnée
	 * @param base l'indice de la base à sélectionner
	 */
	public void setSelection(int base){
		this.selection=base;
	}
	
	/**
	 * Retourne l'indice de la base sélectionnée
	 * @return l'indice de la base sélectionnée
	 */
	public int getSelection(){
		return this.selection;
	}

	/**
	 * Retourne la liste de bases de données
	 * @return la liste de bases de données
	 */
	public ArrayList<BaseDeDonnees> getLesBasesDeDonnees(){
		return this.lesBasesDeDonnees;
	}


}