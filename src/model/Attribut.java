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
 * <P>- * <P>- Si c'est une clé étrangère, le nom de l'attribut qu'elle référence
 */
public class Attribut {


	private String nomVariable;
	private Type type;
	private int valeur;
	private boolean estNonNul;
	private boolean estClePrimaire;
	private boolean aCleEtrangere;
	private String referenceTableEtrangere;
	private String referenceAttributEtranger;
	
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

	public String getNomVariable(){
		return this.nomVariable;
	}

	public Type getType(){
		return this.type;
	}

	public int getValeur(){
		return this.valeur;
	}

	public boolean getEstNonNul(){
		return this.estNonNul;
	}

	public boolean getEstClePrimaire(){
		return this.estClePrimaire;
	}

	public boolean getACleEtrangere(){
		return this.aCleEtrangere;
	}

	public String getContrainte(){
		String ret="";


		if(estNonNul){
			ret = ret + " NOT NULL";
		}


		return ret;
	}

	public String getReferenceTableEtrangere(){
		return this.referenceTableEtrangere;
	}
	public String getReferenceAttributEtranger(){
		return this.referenceAttributEtranger;
	}









}