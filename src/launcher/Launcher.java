package launcher;
import model.*;
import vue.*;

public class Launcher {

	public static void main(String[] args){
		
	BaseDeDonnees maBase = null;
		try{
			maBase = new BaseDeDonnees("jdbc:mysql://arthurguyader.ddns.net:3306/","admin", "admin","test");
		}catch(Exception e){

		}

		//FenetreRequete fen = new FenetreRequete("Gestionnaire de base de données",maBase.getConnection());
	
	}
}