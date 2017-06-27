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

	public void creerBaseDeDonnees(String motDePasse,String nomBase, Requete nouvelleRequete) throws SQLException, Exception{
		BaseDeDonnees laBaseSelectionee = lesBasesDeDonnees.get(selection);
		BaseDeDonnees uneBase = new BaseDeDonnees(laBaseSelectionee.getAdresse(),laBaseSelectionee.getNomUtili(),motDePasse,laBaseSelectionee.getNomDeLaBase());
	  	nouvelleRequete.manuel("CREATE DATABASE "+nomBase);
		String url = laBaseSelectionee.getAdresse();
		url = ModifierString.supprimerExtrait(url,laBaseSelectionee.getNomDeLaBase()+"?allowMultiQueries=true");
		BaseDeDonnees uneBase2 = new BaseDeDonnees(url+nomBase+"?allowMultiQueries=true",laBaseSelectionee.getNomUtili(),motDePasse,nomBase);
		lesBasesDeDonnees.add(uneBase2);
	}

	public void supprimerBaseDeDonnees(String motDePasse,Requete nouvelleRequete)throws SQLException, Exception{
		BaseDeDonnees laBaseSelectionee = lesBasesDeDonnees.get(selection);
		BaseDeDonnees uneBase = new BaseDeDonnees(laBaseSelectionee.getAdresse(),laBaseSelectionee.getNomUtili(),motDePasse,laBaseSelectionee.getNomDeLaBase());
		nouvelleRequete.manuel("DROP DATABASE "+laBaseSelectionee.getNomDeLaBase());
		lesBasesDeDonnees.remove(laBaseSelectionee);
	}


	/** Permet de se deconnecter de la base de donnée sélectionné 
	*
	*/
	/*public void disconnect() throws NullPointerException {
		if(this.selection!=-1 && this.selection>=0 && this.selection<lesBasesDeDonnees.size()){
			lesBasesDeDonnees.get(this.selection).deconnexion();
			lesBasesDeDonnees.remove(this.selection);
			this.selection=-1;
		}
		else{
			throw new NullPointerException("Attention selection incorect");
		}
	}*/

	public int getPositionBase(String base){
		miseAJourDuHashMap();
		int ret = association.get(base);
		return ret;
	}


	public void setTable(String table){
		this.table=table;
	}

	public String getTable(){
		return this.table;
	}
	
	public String toString() {
		
		return "";
		
	}

	public String getId(){
		return this.id;
	}

	public void setSelection(int base){
		this.selection=base;
	}

	public int getSelection(){
		return this.selection;
	}

	public ArrayList<BaseDeDonnees> getLesBasesDeDonnees(){
		return this.lesBasesDeDonnees;
	}


}