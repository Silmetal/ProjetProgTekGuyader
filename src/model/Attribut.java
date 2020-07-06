package model;
import java.util.*;
import java.sql.*;

/**
 * Cette classe modélise un Attribut d'une table, et contient toutes les caractéristiques de cet attribut :
 * <P>- Son nom
 * <P>- Son type
 * <P>- La valeur associée à son type (ex : INT(4))
 * <P>- Si l'attribut peut être nul ou non
 * <P>- Si l'attribut est une clé primaire ou non
 * <P>- Si l'attribut est une clé étrangère ou non
 * <P>- Si c'est une clé étrangère, le nom de la table qui contient l'attribut qu'elle référence
 * <P>- Si c'est une clé étrangère, le nom de l'attribut qu'elle référence
 */
public class Attribut {

	/** 
	 * Le nom de l'attribut
	 */
	private String nomVariable;
	
	/** 
	 * Le type de l'attribut
	 */
	private Type type;
	
	/** 
	 * La valeur associée au type de l'attribut (ex : INT(4))
	 */
	private int valeur;
	
	/** 
	 * Le booléan indiquant si l'attribut peut être nul ou non
	 */
	private boolean estNonNul;

	/** 
	 * Le booléan indiquant si l'attribut peut être nul ou non
	 */
	private boolean estUnique;

	
	/** 
	 * Le booléan indiquant si l'attribut est une clé primaire ou non
	 */
	private boolean estClePrimaire;
	
	/** 
	 * Le booléan indiquant si l'attribut référence une clé étrangère ou non
	 */
	private boolean aCleEtrangere;
	
	/** 
	 * Le nom de la table contenant l'attribut référencé si l'attribut est une clé étrangère 
	 */
	private String referenceTableEtrangere;
	
	/** 
	 * Le nom de l'attribut référencé par cet attribut
	 */
	private String referenceAttributEtranger;
	
	/** 
	 * Le constructeur de la classe. Initialise ses attributs en fonction de ses paramètres.
	 * Si le paramètre "Clé étrangère" passé a pour valeur false, initialise referenceTableEtrangere et referenceAttributEtranger à la chaine de caractère vide,
	 * même si l'utilisateur a entré des données pour ces champs.
	 * @param nomVariable le nom de l'attribut
	 * @param type le type de l'attribut
	 * @param valeur la valeur associée au type de l'attribut
	 * @param estNonNul true si l'attrbitu ne peut pas être nul, false sinon
	 * @param estUnique true si l'attribut est unique, false sinon
	 * @param estClePrimaire true si l'attribut est la clé primaire de la table, false sinon
	 * @param aCleEtrangere true si l'attribut est une clé étrangère, false sinon
	 * @param referenceTableEtrangere le nom de la table contentant l'attribut référencé si l'attribut est une clé étrangère
	 * @param referenceAttributEtranger le nom de l'attribut référencé si l'attribut est une clé étrangère
	 */
	public Attribut(String nomVariable, Type type, int valeur, boolean estNonNul,  boolean estUnique, boolean estClePrimaire, boolean aCleEtrangere, String referenceTableEtrangere,String referenceAttributEtranger){
		this.nomVariable=nomVariable;
		this.estUnique=estUnique;
		this.type=type;
		this.valeur=valeur;
		this.estNonNul=estNonNul;
		this.estClePrimaire=estClePrimaire;
		this.aCleEtrangere=aCleEtrangere;		
		
		if(!aCleEtrangere){
			this.referenceTableEtrangere="";
			this.referenceAttributEtranger="";
		}
		else {
			this.referenceTableEtrangere=referenceTableEtrangere;
			this.referenceAttributEtranger=referenceAttributEtranger;
		}
	}
	
	/** 
	 * Retourne le nom de l'attribut
	 * @return le nom de l'attribut
	 */
	public String getNomVariable(){
		return this.nomVariable;
	}
	
	/** 
	 * Retourne le type de l'attribut
	 * @return le type de l'attribut
	 */
	public Type getType(){
		return this.type;
	}
	
	/** 
	 * Retourne la valeur associée au type de l'attribut
	 * @return la valeur associée au type de l'attribut
	 */
	public int getValeur(){
		return this.valeur;
	}
	
	/** 
	 * Retourne true si l'attribut peut être non nul, false sinon
	 * @return true si l'attribut peut être non nul, false sinon
	 */
	public boolean getEstNonNul(){
		return this.estNonNul;
	}
	
	/** 
	 * Retourne true si l'attribut est clé primaire, false sinon
	 * @return true si l'attribut est clé primaire, false sinon
	 */
	public boolean getEstClePrimaire(){
		return this.estClePrimaire;
	}
	
	/** 
	 * Retourne true si l'attribut est une clé étrangère, false sinon
	 * @return true si l'attribut est une clé étrangère, false sinon
	 */
	public boolean getACleEtrangere(){
		return this.aCleEtrangere;
	}
	
	/** 
	 * Vérifie si l'attribut peut être nul ou non.
	 * <P>Si c'est le cas, renvoie un string contenant un mroceau de commande SQL permettant d'empêcher l'attribut créé d'être nul.
	 * <P>Sinon, renvoie une chaîne de caractère vide
	 * @return une chaîne vide si l'attribut peut être nul, la chaîne " NOT NULL" sinon
	 */
	public String getContrainte(){
		String ret="";

		if(estNonNul){
			ret = ret + " NOT NULL";
		}

		if (estUnique) {
			ret = ret + " UNIQUE";
		}


		return ret;
	}
	
	/** 
	 * Retourn le nom de la table contenant l'attribut référencé par cet attribut
	 * @return le nom de la table contenant l'attribut référencé par cet attribut
	 */
	public String getReferenceTableEtrangere(){
		return this.referenceTableEtrangere;
	}
	
	/** 
	 * Retourn le nom de l'attribut référencé par cet attribut
	 * @return le nom de l'attribut référencé par cet attribut
	 */
	public String getReferenceAttributEtranger(){
		return this.referenceAttributEtranger;
	}









}