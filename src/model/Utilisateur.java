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
	* @param user Un nom d'utilisateur ()
	* @param 
	*/
	public void connect(String url,String user, String password) {
		lesBasesDeDonnees.add(new BaseDeDonnees(url,user,password));
		selection = lesBasesDeDonnees.size();
	}
	
	public void disconnect() throws NullPointerException {
		if(this.selection!=-1 && this.selection>=0 && this.selection<lesBasesDeDonnees.size()){
			lesBasesDeDonnees.get(this.selection).deconnexion();
			lesBasesDeDonnees.get(this.selection).remove();
			this.selection=-1;
		}
		else{
			throw new NullPointerException("Attention selection incorect");
		}
	}
	
	public void nouvelleRequete() {
		
	}
	
	public String toString() {
		
		return "";
		
	}

	public void setSelection(int selection){
		this.selection=selection;
	}
}