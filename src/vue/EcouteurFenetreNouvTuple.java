package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.sql.*;

/**
* Cette classe est l'écouteur des boutons de la classe FenetreNouvelleTable. Elle associe au JSpinner son écouteur et au bouton son action.
*/
public class EcouteurFenetreNouvTuple implements ActionListener, ChangeListener {
	
	/**
	 * La FenetreNouvTuple à écouter.
	 */
	private FenetreNouvTuple fnt;
	
	/**
	 * La valeur du JSpinner avant sa modification par l'utilisateur. Elle est actualisée après chaque action.
	 */
	private int spinnerValue;	
	
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
	 * Le String contenant la commande de modification de table créé par l'écouteur à partir des informations récupérées dans le tableau
	 */
	private String ret;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreNouvelleTable, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fnt la FenetreNouvelleTable à écouter
	 * @param requ l'objet Requete qui va recevoir et exécuter la requête construite grâce aux attributs récupérés par cet écouteur.
	 * @param ema l'écouteur de la FenetrePrincipale associée à la FenetreNouvelleTable. Permet l'exécution des requêtes.
	 * @param fp La fenetre principal
	 */
	public EcouteurFenetreNouvTuple(FenetreNouvTuple fnt, Requete requ, EcouteurMouseAdapter ema, FenetrePrincipale fp){
		this.fp = fp;
		this.fnt = fnt;
		this.requ=requ;
		this.ema = ema;
		spinnerValue = (int)fnt.getNbTupleSpinner().getValue();
		addListener();
	}
	
	/**
	 * La méthode qui associe au bouton créerTable son action, c'est-à-dire créer une nouvelle table. Si une erreur SQL empêche la création de la table,
	 * affiche un message d'erreur demandant à l'utilsiateur de vérifier les informations qu'il a entré.
	 */
	public void actionPerformed(ActionEvent e){
		
		int i = 0;
		int j = 1;
		
		ret = "INSERT INTO "+fp.getUtilisateur().getTable()+" (";
		
		for (j = 0; j < fnt.getTable().getColumnCount()-1; j++) {
			ret = ret+fnt.getTable().getColumnName(j)+",";
		}
		ret = ret+fnt.getTable().getColumnName(j)+")";

		ret = ret+"\nVALUES";
		
		
		
		for(i = 0; i < fnt.getTable().getRowCount()-1; i++){
			if (fnt.getTable().getValueAt(i,0) == null) {
				ret = ret+"\n	(NULL";
			}
			else { 
				try {
					Double.parseDouble(fnt.getTable().getValueAt(i,0).toString());
					ret = ret+"\n	("+fnt.getTable().getValueAt(i,0);
				}
				catch (NumberFormatException nfe) {
					ret = ret+"\n	('"+fnt.getTable().getValueAt(i,0)+"'";
				}
			}
			
			for (j = 1; j < fnt.getTable().getColumnCount(); j++) {
			
				if (fnt.getTable().getValueAt(i,j) == null) {
					ret = ret+",NULL";
				}
				
				else{
					try {
						Double.parseDouble(fnt.getTable().getValueAt(i,j).toString());
						ret = ret+","+fnt.getTable().getValueAt(i,j);
					}
					catch (NumberFormatException nfe) {
						ret = ret+",'"+fnt.getTable().getValueAt(i,j)+"'";
					}
				}
			}
			ret = ret+"),";
		}
		
		if (fnt.getTable().getValueAt(i,0) == null) {
			ret = ret+"\n	(NULL";
		}
		else {
			try {
				Double.parseDouble(fnt.getTable().getValueAt(i,0).toString());
				ret = ret+"\n	("+fnt.getTable().getValueAt(i,0);
			}
			catch (NumberFormatException nfe) {
				ret = ret+"\n	('"+fnt.getTable().getValueAt(i,0)+"'";
			}
		}

		for (j = 1; j < fnt.getTable().getColumnCount(); j++) {
			if (fnt.getTable().getValueAt(i,j) == null) {
				ret = ret+",NULL";
			}
			else {
				try {
					Double.parseDouble(fnt.getTable().getValueAt(i,j).toString());
					ret = ret+","+fnt.getTable().getValueAt(i,j);
				}
				catch (NumberFormatException nfe) {
					ret = ret+",'"+fnt.getTable().getValueAt(i,j)+"'";
				}
			}
		}
		
		ret = ret+");";
		
		try{
			ema.nouveauTuple(requ, this);
			fp.setJTable(fp.getUtilisateur().getLesBasesDeDonnees().get(fp.getUtilisateur().getSelection()), fp.getUtilisateur().getTable());
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
		
		int newValue = (int)fnt.getNbTupleSpinner().getValue();
		
		if ((newValue - spinnerValue) > 0) {
			
			for(int i = 0; i < newValue - spinnerValue; i++) {
				((DefaultTableModel)fnt.getTable().getModel()).addRow(new Vector());
			}
		}
		else if ((newValue - spinnerValue) < 0) {
			
			for(int i = 0; i > newValue - spinnerValue; i--) {
				((DefaultTableModel)fnt.getTable().getModel()).removeRow(fnt.getTable().getModel().getRowCount()-1);
			}
		}
		
		this.spinnerValue = newValue;
    }
	
	/**
	 * Ajoute leurs écouteurs aux éléments graphiques de la FenetreNouvelleTable
	 */
	public void addListener() {
		
		fnt.getNbTupleSpinner().addChangeListener(this);
		fnt.getModifTableBouton().addActionListener(this);
	}
	
	public String getComm() {
		return this.ret;
	}
}
