package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.sql.*;

/**
* Cette classe est l'écouteur des boutons de la classe FenetreNouvelleTable. Elle associe au JSpinner son écouteur et au bouton son action.
*/
public class EcouteurFenetreModifTable implements ActionListener{
	
	/**
	 * La FenetreModifTable à écouter.
	 */
	private FenetreModifTable fmt;
	
	/**
	 * La liste des valeurs de la table. Initialisée par l'écouteur en récupérant les informations entrées dans la FenetreModifTable
	 */
	private ArrayList<String> listeVal;
	
	/**
	 * L'objet Requete qui va recevoir et exécuter la requête construite grâce aux attributs récupérés par cet écouteur.
	 */
	private Requete requ;
	
	/**
	 * L'écouteur de la FenetrePrincipale associée à la FenetreNouvelleTable. Permet l'exécution des requêtes.
	 */
	private EcouteurMouseAdapter ema;

	/**
	 * La fenetre principale associé à cette fenetre
	 */
	private FenetrePrincipale fp;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreNouvelleTable, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fmt la FenetreNouvelleTable à écouter
	 * @param requ l'objet Requete qui va recevoir et exécuter la requête construite grâce aux attributs récupérés par cet écouteur.
	 * @param ema l'écouteur de la FenetrePrincipale associée à la FenetreNouvelleTable. Permet l'exécution des requêtes.
	 * @param fp La fenetre principal
	 */
	public EcouteurFenetreModifTable(FenetreModifTable fmt, Requete requ, EcouteurMouseAdapter ema, FenetrePrincipale fp){
		this.fp = fp;
		this.fmt = fmt;
		this.requ=requ;
		this.ema = ema;
		// addListener();
	}
	
	/**
	 * La méthode qui associe au bouton créerTable son action, c'est-à-dire créer une nouvelle table. Si une erreur SQL empêche la création de la table,
	 * affiche un message d'erreur demandant à l'utilsiateur de vérifier les informations qu'il a entré.
	 */
	public void actionPerformed(ActionEvent e){
		
		/* listeVal = new ArrayList<String>();
		
		for (Object[] o :((DefaultTableModel)fmt.getTable().getModel()).getDataVector().toArray()){
			
			String val = "";
			
			for (int i = 0; i < o.length; i++) {
				val = val+o[i]+";";
			}
			
		}
		
		try{
			fmt.dispose();
		}
		catch(SQLException sqle) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "Erreur SQL, vérifiez vos informations.", "Erreur", JOptionPane.ERROR_MESSAGE);
			sqle.printStackTrace();
		}
		catch(Exception ex){
			ex.printStackTrace();
		} */
	}
	
	/**
	 * Retourne la listeVal créée par l'écouteur
	 * @return la listeVal créée par l'écouteur
	 */
	public ArrayList<String> getlisteVal(){
		return this.listeVal;
	}
}
