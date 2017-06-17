package model;
import java.util.*;
import java.sql.*;


public class Attribut {


	private String nomVariable;
	private Type type;
	private int valeur;
	private boolean estNonNul;
	private boolean aClePrimaire;
	private boolean aCleEtrangere;
	private String referenceTableEtrangere;
	private String referenceAttributEtranger;
	
	public Attribut(String nomVariable, Type type, int valeur, boolean estNonNul, boolean aClePrimaire, boolean aCleEtrangere){
		this.nomVariable=nomVariable;
		this.type=type;
		this.valeur=valeur;
		this.estNonNul=estNonNul;
		this.aClePrimaire=aClePrimaire;
		this.aClePrimaire=aClePrimaire;
	}

	public Attribut(String nomVariable, Type type, int valeur, boolean estNonNul, boolean aClePrimaire, boolean aCleEtrangere, String referenceTableEtrangere,String referenceAttributEtranger){
		this.nomVariable=nomVariable;
		this.type=type;
		this.valeur=valeur;
		this.estNonNul=estNonNul;
		this.aClePrimaire=aClePrimaire;
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

	public boolean getAClePrimaire(){
		return this.aClePrimaire;
	}

	public boolean getACleEtrangere(){
		return this.aCleEtrangere;
	}

	public String getContrainte(){
		String ret="";


		if(aCleEtrangere){
			ret = ret + "\nCONSTRAINT fk"+nomVariable+"REFERENCES "+referenceTableEtrangere+"("+referenceAttributEtranger+")";
		}

		if(estNonNul){
			ret = ret + "\nCONSTRAINT nn"+nomVariable+" NOT NULL";
		}


		return ret;
	}









}