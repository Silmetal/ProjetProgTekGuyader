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
	 *
	 */
	public Attribut(String nomVariable, Type type, int valeur, boolean estNonNul,  boolean estUnique, boolean estClePrimaire, boolean aCleEtrangere, String referenceTableEtrangere,String referenceAttributEtranger){
		if(!aCleEtrangere){
			referenceTableEtrangere="";
			referenceAttributEtranger="";
		}
		
		

		this.nomVariable=nomVariable;
		this.type=type;
		this.valeur=valeur;
		this.estNonNul=estNonNul;
		this.estClePrimaire=estClePrimaire;
		this.aCleEtrangere=aCleEtrangere;
		this.referenceTableEtrangere=referenceTableEtrangere;
		this.referenceAttributEtranger=referenceAttributEtranger;
	}
	
	/** 
	 *
	 */
	public String getNomVariable(){
		return this.nomVariable;
	}
	
	/** 
	 *
	 */
	public Type getType(){
		return this.type;
	}
	
	/** 
	 * Retourne la valeur associée au type de l'attribut
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
	 * 
	 */
	public String getContrainte(){
		String ret="";


		if(estNonNul){
			ret = ret + " NOT NULL";
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