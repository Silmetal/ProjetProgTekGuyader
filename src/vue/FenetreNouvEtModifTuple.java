package vue;
import controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

/**
 * 
 */
public class FenetreNouvEtModifTuple extends JFrame{
		
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
	private MyTableModel mtm;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public FenetreNouvEtModifTuple(){
		super("Modifier une table");
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
		
		mtm = new MyTableModel();
		
		jTable = new JTable(mtm);
		jTable.getTableHeader().setReorderingAllowed(false);
		
		modifTable = new JButton("Modifier la table");
		
		
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
	
	public JTable getTable() {
		
		return this.jTable;
	}
	
	public JSpinner getNbTupleSpinner() {
		
		return this.nbTuple;
	}
	
	public JButton getModifTableBouton() {
		
		return this.modifTable;
	}
	
	public MyTableModel getMTM() {
		
		return this.mtm;
	}
}