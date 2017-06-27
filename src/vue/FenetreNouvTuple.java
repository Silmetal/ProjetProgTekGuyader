package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

/**
 * Cette fenêtre s'ouvre lorsque l'on clique sur "Ajouter un Tuple". Elle contient un JTable dont les colonnes sont
 * les mêmes que la colonne sélectionnée. Un JSpinner indique le nombre de lignes du tableau. Ce JSpinner
 * va de 1 à 99999. 
 * <P>L'utilisateur peut remplir le tableau et, lorsqu'il clique sur le bouton "Modifier la table", une requête
 * d'insertion de tuple, qui insère toutes les lignes du tableau.
 */
public class FenetreNouvTuple extends JFrame{
		
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	/**
	 * Le JSpinner dont la valeur indique le nombre de nouvelles lignes à afficher dans le JTable
	 */
	private JSpinner nbTuple;
	
	/**
	 * Le bouton qui lance la requête SQL d'insertion et modification de tuple suivant ce qui a été saisi par l'utilisateur dans le JTable
	 */
	private JButton modifTable;
	
	/**
	 * Le modèle utilisé par le JTable pour se créer et se mettre à jour correctement
	 */
	private DefaultTableModel dTM;
	
	/**
	 * La fenêtre principale dont dépend cette fenêtre
	 */
	private FenetrePrincipale fp;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 * @param fp la FenetrePrincipale dont dépend cette fenêtre
	 */
	public FenetreNouvTuple(FenetrePrincipale fp){
		super("Insérer des tuples");
		this.fp = fp;
		this.setLayout(new BorderLayout(10,10));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		miseEnPlace();
		this.setSize(800,600);
		this.setVisible(true);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		
		JLabel nbTupleLabel = new JLabel("Nombre de tuple");
		nbTuple = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));
		
		dTM = new DefaultTableModel();
	
		for (int i = 0; i < fp.getTable().getColumnCount(); i++) {
			dTM.addColumn(fp.getTable().getColumnName(i));
		}
		
		dTM.addRow(new Vector());
		
		jTable = new JTable(dTM);
		
		modifTable = new JButton("Insérer des tuples");
		
		
		// Création des sous-panneaux
		JScrollPane scrollPane = new JScrollPane(jTable);
		JPanel pannEnTete = new JPanel(new GridLayout(1,4, 5, 5));
		pannEnTete.add(new JPanel());
		JPanel pannBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		pannEnTete.add(nbTupleLabel);
		pannEnTete.add(nbTuple);
		pannEnTete.add(new JPanel());
		pannBouton.add(modifTable);
		
		// Ajout des sous-panneaux dans le panneau principal
		this.add(pannEnTete, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(pannBouton, BorderLayout.SOUTH);
	}
	
	/**
	 * Retourne le JTable contenu dans cette fenêtre
	 * @return le JTable contenu dans cette fenêtre
	 */
	public JTable getTable() {
		
		return this.jTable;
	}
	
	/**
	 * Retourne le JSpinner indiquant le nombre de lignes du tableau
	 * @return le JSpinner indiquant le nombre de lignes du tableau
	 */
	public JSpinner getNbTupleSpinner() {
		
		return this.nbTuple;
	}
	
	/**
	 * Retourne le bouton contenu dans cette fenêtre
	 * @return le bouton contenu dans cette fenêtre
	 */
	public JButton getModifTableBouton() {
		
		return this.modifTable;
	}
	
	/**
	 * Retourne le DefaultTableModel utilisé par le JTable contenu dans cette fenêtre
	 * @return le DefaultTableModel utilisé par le JTable contenu dans cette fenêtre
	 */
	public DefaultTableModel getDTM() {
		
		return this.dTM;
	}
}