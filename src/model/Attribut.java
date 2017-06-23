package model;
import java.util.*;
import java.sql.*;


public class Attribut {


	private String nomVariable;
	private Type type;
	private int valeur;
	private boolean estNonNul;
	private boolean estClePrimaire;
	private boolean aCleEtrangere;
	private String referenceTableEtrangere;
	private String referenceAttributEtranger;
	private boolean estUnique;
	
	public Attribut(String nomVariable, Type type, int valeur, boolean estNonNul,  boolean estUnique, boolean estClePrimaire, boolean aCleEtrangere, String referenceTableEtrangere,String referenceAttributEtranger){
		if(!aCleEtrangere){
			referenceTableEtrangere="";
			referenceAttributEtranger="";
		}
		
		
		this.estUnique=estUnique;
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


		if(!estNonNul){
			ret = ret + " NOT NULL";
		}

		if(estUnique){
			ret = ret + "";
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