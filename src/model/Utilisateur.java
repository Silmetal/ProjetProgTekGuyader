package model;

import java.sql.*;
import java.util.*;


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
	private ArrayList<BaseDeDonnees> lesBasesDeDonnees;
	private int selection;
	private String table;
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
					
					Requete interogation = new Requete(laBase.getConnection(),table);
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

			selection = lesBasesDeDonnees.size()-1;
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


	public void miseAJourDuHashMap(){
		int i=0;
		association = new HashMap<String,Integer>();

		for (BaseDeDonnees base : lesBasesDeDonnees) {
			association.put(base.getNomDeLaBase(),i);
			i++;
		}

	}
	


	/* //Recupere le nom des tables de la base
	public ArrayList<String> parcourirBase(BaseDeDonnees laBase){
		DatabaseMetaData dmd;
		ResultSet tables;
		int i=0;
		ArrayList<String> ret = new ArrayList<String>();
		try{
			dmd = laBase.getConnection().getMetaData();
			tables = dmd.getTables(laBase.getConnection().getCatalog(),null,"%",null);
			i=0;
			while(tables.next()){
				ret.add(tables.getString(3));
				i++;
			}
		} catch(SQLException e){
			System.out.println("Impossible de parcourir la base");
		}
		return ret;
	} */

	/** Permet de se deconnecter de la base de donnée sélectionné 
	*
	*/
	public void disconnect() throws NullPointerException {
		if(this.selection!=-1 && this.selection>=0 && this.selection<lesBasesDeDonnees.size()){
			lesBasesDeDonnees.get(this.selection).deconnexion();
			lesBasesDeDonnees.remove(this.selection);
			this.selection=-1;
		}
		else{
			throw new NullPointerException("Attention selection incorect");
		}
	}

	/** Fait appel a la base de donnée séléctionnée pour créer une nouvelle instance
	* de la classe requete.
	* @throws SQLException si une erreur SQL empêche la création de la requête
	* @throws NullPointerException si le curseur selection n'est pas à une valeur valide
	*/
	
	public void nouvelleRequete() throws SQLException, NullPointerException{
		
		try {
			
			if(this.selection!=-1 && this.selection>=0 && this.selection<lesBasesDeDonnees.size()){
				Requete nouvelleRequete = new Requete(this.lesBasesDeDonnees.get(this.selection).getConnection(),table);
			}
			else throw new NullPointerException("Attention selection incorect");
		}
		catch(SQLException sqle) {	
			throw sqle;
		}
	}

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