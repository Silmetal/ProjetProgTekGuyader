package utilitaire;
import java.io.*;
import java.util.*;

/** 
 * Cette classe contient des méthodes permettant de lire et écrire des données dans un fichier texte.
 */
public class RWFile {
	
	/**
	 * Cette méthode permet de lire le contenu d'un fichier texte et de récupérer les données dans un String
	 * @param fileName le nom du fichier à lire
	 * @return le String contenant le contenu du fichier lu
	 */
	public static String readFile(String fileName){
		String ret="";
		String ligne;
		try{
			FileReader file = new FileReader(fileName);
			BufferedReader in = new BufferedReader(file);
			ligne = in.readLine();
			while(ligne!=null){
				ret = ret + ligne + "\n";
				ligne = in.readLine();
			}
		}
		catch (IOException ex1){
			ex1.printStackTrace();
		}

		return ret;
	}
	
	/**
	 * Cette méthode permet d'écrire dans un fichier texte le String passé en paramètre.
	 * @param contenu le texte à écrire dans le fichier
	 * @param fileName le nom du fichier dans lequel écrire
	 */
	public static void writeFile(String contenu, String fileName){
		try{
			PrintStream out = new PrintStream(fileName);
			out.println(contenu);
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Cette méthode permet d'écrire le contenu passé en paramètre à la fin du fichier text passé en paramètre
	 * @param contenu le texte à écrire
	 * @param fileName le nom du fichier dans lequel écrire
	 */
	public static void writeEndOfFile(String contenu, String fileName){
		try{
			FileWriter out = new FileWriter(fileName,true);
			out.write("\r\n\r\n");
			out.write(contenu);
			out.close();
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
		catch(IOException exio){
			exio.printStackTrace();
		}
	}	
}
