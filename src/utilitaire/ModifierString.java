package utilitaire;
import java.lang.*;

public class ModifierString {
	
	
	public static String supprimerExtrait(String base, String aSupprimer){
		String ret=base;
		String ret1="";
		String ret2="";
		while(ret.indexOf(aSupprimer)>0){
			ret1 = ret.substring(0,ret.indexOf(aSupprimer));
			ret2 = ret.substring(ret.indexOf(aSupprimer)+aSupprimer.length(),ret.length());
			ret=ret1+ret2;
		}	
		return ret;
	}	


	public static String remplacerExtrait(String base, String aSupprimer, String aPlace){
		String ret=base;
		String ret1="";
		String ret2="";
		while(ret.indexOf(aSupprimer)>0){
			ret1 = ret.substring(0,ret.indexOf(aSupprimer));
			ret2 = ret.substring(ret.indexOf(aSupprimer)+aSupprimer.length(),ret.length());
			ret=ret1+aPlace+ret2;
		}	
		return ret;
	}


	public static String supprimerMotAMot(String base,String mot1,String mot2){
		String ret = base;
		String ret1="";
		String ret2="";
		while(ret.indexOf(mot1)<ret.indexOf(mot2) && ret.indexOf(mot2)>0){
			ret1 = ret.substring(0,ret.indexOf(mot1));
			ret2= ret.substring(ret.indexOf(mot2)+mot2.length(),ret.length());
			ret = ret1 + ret2;
		}
		return ret;
	}

	
	public static String supprimerAvecPlace(String base,int place, int tailleASup){
		String ret="";
		String ret1;
		String ret2;
		ret1=base.substring(0, place);
		ret2=base.substring(place+tailleASup,base.length());
		ret = ret1 + ret2;
		
		return ret;
	}

	public static String[] decomposerLigneParLigne(String base){
		return base.split("\n");
	}

	public static String[] decomposerEspaceParEspace(String base){
		return base.split("\\s+");
	}


	public static String remplacerUnCaractere(String base,char ancien,char nouveau){
		String ret = base.replace(ancien,nouveau);
		return ret;
	}


/*	public static String supprimerEntreDeuxMots(String base,String mot1,String mot2){
		String ret = base;
		ret = ret.substring(ret.indexOf(mot1)+mot1.length(),ret.indexOf(mot2)-1);
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
	}*/
	
	


}
