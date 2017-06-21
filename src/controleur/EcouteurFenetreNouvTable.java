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
* Cette classe est l'écouteur des boutons de la classe FenetreRequete. Elle associe à chaque bouton l'action qu'il est censé déclencher.
*/
public class EcouteurFenetreNouvTable implements ActionListener, ChangeListener {
	
	/**
	 * La FenetreNouvelleTable
	 */
	private FenetreNouvelleTable fnt;
	
	private int spinnerValue;
	
	private ArrayList<Attribut> listeAtt;
	
	private String nomTable;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreRequete, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fr la FenetreRequete à écouter
	 * @param maConnexion la Connection associée
	 * @param fp la FenetrePrincipale dont dépend la FenetreRequete
	 */
	public EcouteurFenetreNouvTable(FenetreNouvelleTable fnt){
		this.fnt = fnt;
		spinnerValue = (int)fnt.getNbColonne().getValue();
		addListener();
	}
	
	/**
	 * La méthode qui associe chaque bouton à son action.
	 * <P>Si le bouton est le bouton "Lancer", exécute la méthode lancer()
	 * <P>Si le bouton est le bouton "Enregistrer", exécute la méthode enregistrer()
	 * <P>Si le bouton est le bouton "Enregistrer Sous", exécute la méthode enregistrerSous()
	 * <P>Si le bouton est le bouton "Ouvrir", exécute la méthode ouvrir()
	 */
	public void actionPerformed(ActionEvent e) {
		
		listeAtt = new ArrayList<Attribut>();
		nomTable = fnt.getNomTableTF().getText();
		
		for (Object[] o :((MyTableModel)fnt.getTable().getModel()).getData()){
			
			Attribut att = new Attribut((String)o[0], (Type)o[1], (int)o[2], (boolean)o[3], (boolean)o[4], (boolean)o[5], (String)o[6], (String)o[7]);
			listeAtt.add(att);
		}		
	}
	
	public void stateChanged(ChangeEvent e) {
		
		int newValue = (int)fnt.getNbColonne().getValue();
		
		if ((newValue - spinnerValue) > 0) {
			
			((MyTableModel)fnt.getTable().getModel()).addRow(new Object[]{"", model.Type.INT, 4, false, false, false, false, "", ""});
		}
		else if ((newValue - spinnerValue) < 0) {
			
			((MyTableModel)fnt.getTable().getModel()).removeRow(fnt.getTable().getModel().getRowCount()-1);
		}
		
		this.spinnerValue = newValue;
    }
	
	public void addListener() {
		
		fnt.getNbColonne().addChangeListener(this);
	}
}
