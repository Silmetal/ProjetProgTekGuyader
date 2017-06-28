package launcher;
import model.*;
import vue.*;

public class Launcher {

	public static void main(String[] args){
		


		FenetrePrincipale fen = new FenetrePrincipale();
		FenetreConnexion fenConnexion = new FenetreConnexion(fen.getUtilisateur(),fen, 1);

	
	}
}