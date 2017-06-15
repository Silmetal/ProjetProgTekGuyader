import model.*;
import vue.*;

public class Launcher {

	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		Cadre leCadre = new Cadre("Gestionnaire de base de données");
		}});
		
	}
}