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
	

	/** Constructeur de la classe. Il prend en paramètre une chaine de caractère
	* qui est un identifiant qui au préalable a été comparé dans la méthode qui
	* l'instancie.
	* @param id  Une chaine de caractère : un identifiant unique
	*/
	public Utilisateur(String id) {
		this.id=id;
		lesBasesDeDonnees = new ArrayList<BaseDeDonnees>();
		this.selection = -1 ;
	}
	

	/** Permet de se connecter a une base de donnée oracle ou mySQL.
	* @param url L'adresse de la base de donnée
	* @param user Un nom d'utilisateur (Attention selon l'endroit où vous vous connectez 
	* , il est nécéssaire d'avoir des droits particuliers, prenez donc en compte se détail)
	* @param password Le mot de passe de l'utilisateur pour accéder à la base de donnée
	* @throws ClassNotFoundException si le driver correspondant n'est pas trouvé
	* @throws SQLException si une erreur SQL empêche la connexion à la base de données
	* @throws Exception si une autre erreur empe^che la connexion
	*/
	public void connect(String url,String user, String password) throws ClassNotFoundException, SQLException, Exception{
		try {
			lesBasesDeDonnees.add(new BaseDeDonnees(url,user,password));
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
				Requete nouvelleRequete = new Requete(this.lesBasesDeDonnees.get(this.selection).getConnection());
			}
			else throw new NullPointerException("Attention selection incorect");
		}
		catch(SQLException sqle) {
			
			throw sqle;
		}
		
	}
	
	public String toString() {
		
		return "";
		
	}

	public void setSelection(int selection){
		this.selection=selection;
	}

	public int getSelection(){
		return this.selection;
	}

	public ArrayList<BaseDeDonnees> getLesBasesDeDonnees(){
		return this.lesBasesDeDonnees;
	}


}