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
public class EcouteurFenetreModifTable implements ActionListener, TableModelListener{
	
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
		addListener();
	}
	
	/**
	 * La méthode qui associe au bouton créerTable son action, c'est-à-dire créer une nouvelle table. Si une erreur SQL empêche la création de la table,
	 * affiche un message d'erreur demandant à l'utilsiateur de vérifier les informations qu'il a entré.
	 */
	public void actionPerformed(ActionEvent e){
		
		fmt.dispose();
	}
	
	public void tableChanged(TableModelEvent e) {
		
		String maj = "";
		
		System.out.println("test modif");
		
		System.out.println("test");
	
		int row = e.getFirstRow();
		int column = e.getColumn();
		
		TableModel model = (TableModel)e.getSource();
		Object data = model.getValueAt(row, column);
		
		
		if(data==null){
			
			maj = "UPDATE "+fp.getUtilisateur().getTable()+" SET "+fp.getTable().getColumnName(column)+"=\""+""+"\" WHERE "+fp.getTable().getColumnName(column)+"=\""+fp.getTable().getValueAt(row, 0)+"\"";
		}
		else{
			maj = "UPDATE "+fp.getUtilisateur().getTable()+" SET "+fp.getTable().getColumnName(column)+"=\""+data+"\" WHERE "+fp.getTable().getColumnName(0)+"=\""+fp.getTable().getValueAt(row, 0)+"\"";
		}
		
		System.out.println(maj);
	}
	
	/**
	 * Retourne la listeVal créée par l'écouteur
	 * @return la listeVal créée par l'écouteur
	 */
	public ArrayList<String> getlisteVal(){
		return this.listeVal;
	}
	
	/**
	 * Ajoute leurs écouteurs aux éléments graphiques de la FenetreNouvelleTable
	 */
	public void addListener() {
		
		fmt.getTermineBouton().addActionListener(this);
		fmt.getTable().getModel().addTableModelListener(this);
	}
	
}
