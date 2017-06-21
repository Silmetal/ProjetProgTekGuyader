package utilitaire;
import java.io.*;
import java.util.*;


public class ModifierString {
	
	public static String supprimerExtrait(String base, String aSupprimer){
		String ret;
		
     	String part1 = (base.split(aSupprimer))[0];
     	String part2 = (base.split(aSupprimer))[1];
     	
     	ret = part1+part2;

		return ret;
	}	


	public static String supprimerMotAMot(String base,String mot1,String mot2){
		String ret =base;

		while (ret.indexOf(mot1) >= 0) {
			String part1 = (base.split(mot1))[0];
	     	String part2 = (base.split(mot2))[1];
	     	ret = part1+part2; 
		}

			
     	
     	

		return ret;
	}


	public static String supprimerEntreDeuxMots(String base,String mot1,String mot2){
		String ret;

		String part1 = (base.split(mot1))[0];
	 	String part2 = (base.split(mot2))[1];
	 	
	 	ret = part1+mot1+mot2+part2; 

		return ret;
	}

	public static String supprimerEntreDeuxMotsEtPremier(String base,String mot1,String mot2){
		String ret;

		String part1 = (base.split(mot1))[0];
	 	String part2 = (base.split(mot2))[1];

	 	
	 	ret = part1+mot2+part2; 

		return ret;
	}

	public static String supprimerEntreDeuxMotsEtDernier(String base,String mot1,String mot2){
		String ret;

		String part1 = (base.split(mot1))[0];
	 	String part2 = (base.split(mot2))[1];
	 	
	 	ret = part1+mot1+part2; 

		return ret;
	}


}
