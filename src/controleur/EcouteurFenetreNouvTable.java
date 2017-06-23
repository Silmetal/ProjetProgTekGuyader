package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.sql.*;

/**
* Cette classe est l'écouteur des boutons de la classe FenetreNouvelleTable. Elle associe au JSpinner son écouteur et au bouton son action.
*/
public class EcouteurFenetreNouvTable implements ActionListener, ChangeListener {
	
	/**
	 * La FenetreNouvelleTable à écouter.
	 */
	private FenetreNouvelleTable fnt;
	
	/**
	 * La valeur du JSpinner avant sa modification par l'utilisateur. Elle est actualisée après chaque action.
	 */
	private int spinnerValue;
	
	/**
	 * La liste des attributs de la table à créer. Initialisée par l'écouteur en récupérant les informations entrées dans la FenetreNouvelleTable
	 */
	private ArrayList<Attribut> listeAtt;
	
	/**
	 * Le nom de la table créée
	 */
	private String nomTable;
	
	/**
	 * L'objet Requete qui va recevoir et exécuter la requête construite grâce aux attributs récupérés par cet écouteur.
	 */
	private Requete requ;
	
	/**
	 * L'écouteur de la FenetrePrincipale associée à la FenetreNouvelleTable. Permet l'exécution des requêtes.
	 */
	private EcouteurMouseAdapter ema;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreNouvelleTable, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fnt la FenetreNouvelleTable à écouter
	 * @param Requete l'objet Requete qui va recevoir et exécuter la requête construite grâce aux attributs récupérés par cet écouteur.
	 * @param ema l'écouteur de la FenetrePrincipale associée à la FenetreNouvelleTable. Permet l'exécution des requêtes.
	 */
	public EcouteurFenetreNouvTable(FenetreNouvelleTable fnt, Requete requ, EcouteurMouseAdapter ema){
		this.fnt = fnt;
		this.requ=requ;
		this.ema = ema;
		spinnerValue = (int)fnt.getNbColonne().getValue();
		addListener();
	}
	
	/**
	 * La méthode qui associe au bouton créerTable son action, c'est-à-dire créer une nouvelle table. Si une erreur SQL empêche la création de la table,
	 * affiche un message d'erreur demandant à l'utilsiateur de vérifier les informations qu'il a entré.
	 */
	public void actionPerformed(ActionEvent e){
		
		listeAtt = new ArrayList<Attribut>();
		nomTable = fnt.getNomTableTF().getText();
		
		for (Object[] o :((MyTableModel)fnt.getTable().getModel()).getData()){
			
			int res = 0;
			try {
				res = Integer.parseInt((String)o[2]);
			}
			catch(NumberFormatException nfe) {
				res = -1;
			}
			Attribut att = new Attribut((String)o[0], (Type)o[1], res, (boolean)o[3], (boolean)o[4], (boolean)o[5], (boolean)o[6], (String)o[7], (String)o[8]);
			listeAtt.add(att);
		}
		
		try{
			ema.nouvelleTable(requ, this);
			fnt.getFenetrePrincipale().getPanneauGauche().constructionJTree();
			fnt.dispose();

		}
		catch(SQLException sqle) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "Erreur SQL, vérifiez vos informations.", "Erreur", JOptionPane.ERROR_MESSAGE);
			sqle.printStackTrace();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Écoute le JSpinner de la FenetreNouvelleTable pour afficher un nombre de ligne dans le tableau de cette fenêtre égal au nombre indiqué par le JSpinner.
	 */
	public void stateChanged(ChangeEvent e) {
		
		int newValue = (int)fnt.getNbColonne().getValue();
		
		if ((newValue - spinnerValue) > 0) {
			
			for(int i = 0; i < newValue - spinnerValue; i++) {
				((MyTableModel)fnt.getTable().getModel()).addRow(new Object[]{"", model.Type.INT, "", false, false, false, false, "", ""});
			}
		}
		else if ((newValue - spinnerValue) < 0) {
			
			for(int i = 0; i > newValue - spinnerValue; i--) {
				((MyTableModel)fnt.getTable().getModel()).removeRow(fnt.getTable().getModel().getRowCount()-1);
			}
		}
		
		this.spinnerValue = newValue;
    }
	
	/**
	 * Ajoute leurs écouteurs aux éléments graphiques de la FenetreNouvelleTable
	 */
	public void addListener() {
		
		fnt.getNbColonne().addChangeListener(this);
		fnt.getCreerTableBouton().addActionListener(this);
	}
	
	/**
	 * Retourne la listeAtt créée par l'écouteur
	 * @return la listeAtt créée par l'écouteur
	 */
	public ArrayList<Attribut> getListeAtt(){
		return this.listeAtt;
	}
	
	/**
	 * Retourne le nom de la table créée
	 * @return le nom de la table créée
	 */
	public String getNomTable(){
		return this.nomTable;
	}
}
