package utilitaire;
import java.io.*;
import java.util.*;


public class RWFile {
	
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
	
	public static void writeFile(String contenu, String fileName){
		try{
			PrintStream out = new PrintStream(fileName);
			out.println(contenu);
		}
		catch(FileNotFoundException ex){
			ex.printStackTrace();
		}
	}	
}
