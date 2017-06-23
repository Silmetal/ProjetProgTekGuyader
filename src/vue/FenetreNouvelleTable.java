package vue;
import controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;

/**
 * Cette fenêtre est ouverte lorsque l'utilisateur appuie sur "Créer une nouvelle table" dans la fenêtre principale. Dans celle-ci, l'utilisateur peut saisir le nom
 * de la table u'i lveut créer, et un tableau avec les colonne "Nom", "Type", "Valeur", "Nul ?",  "Unique ?", "Clé Primaire ?", "Clé étrangère ?", "Table référée", "Attribut référé" est affiché.
 * En changeant la valeur du JSpinner nbColonne, le nombre de ligne du tableau est modifié, avec un minimum de 1 ligne t un maximum de 99999. Chaque ligne correspond à une colonne du table qui va être crée.
 * L'utilisateur saisit les caractéristiques des colonnes à créer dans les lignes. Une fois ceci terminé, cliquer sur le bouton "Créer la table" créé et exécute la requête de création de table suivant les 
 * données saisies.Si une erreur SQL empêche l'exécution de la requête, affiche un message d'erreur.
 */
public class FenetreNouvelleTable extends JFrame{
		
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	/**
	 * Le JTextField dans lequel l'utilisateur entre le nom de la table
	 */
	private JTextField nomTableTF;
	
	/**
	 * Le JSpinner qui indique au tableau combien de lignes afficher. Valeur minimale : 1, Valeur maximale : 99999, pas : 1.
	 */
	private JSpinner nbColonne;
	
	/**
	 * La JComboBox qui contient les différents types d'attribut possibles. Les Types possibles sont énumérés dans l'énuméraion Type.
	 */
	private JComboBox listeType;
	
	/**
	 * Le JButton permettant de créer la table en fonction des données entrées par l'utilisateur dans le JTable
	 */
	private JButton creerTable;
	
	/**
	 * Le modèle utilisé par le JTable pour se créer et se mettre à jour correctement
	 */
	private MyTableModel mtm;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public FenetreNouvelleTable(){
		super("Nouvelle Table");
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
		
		this.nomTableTF = new JTextField("");
		JLabel nomTableLabel = new JLabel("Nom de la table");

		JLabel nbColonneLabel = new JLabel("Nombre de colonne");
		nbColonne = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));
		
		mtm = new MyTableModel();
		
		jTable = new JTable(mtm);
		jTable.getTableHeader().setReorderingAllowed(false);
		comboBoxTable(jTable.getColumnModel().getColumn(1));
		
		creerTable = new JButton("Créer la table");
		
		
		// Création des sous-panneaux
		JScrollPane scrollPane = new JScrollPane(jTable);
		JPanel pannEnTete = new JPanel(new GridLayout(1,4, 5, 5));
		JPanel pannBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		pannEnTete.add(nomTableLabel);
		pannEnTete.add(nomTableTF);
		pannEnTete.add(nbColonneLabel);
		pannEnTete.add(nbColonne);
		pannBouton.add(creerTable);
		
		// Ajout des sous-panneaux dans le panneau principal
		this.add(pannEnTete, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(pannBouton, BorderLayout.SOUTH);
	}
	
	/**
	 * Cette méthode modifie la colonne passée en paramètre pour que les cases soient des JComboBox. Les valeurs possibles de cette JComboBox
	 * sont les valeurs de l'énumération Type.
	 */
	public void comboBoxTable(TableColumn colType) {
		
		listeType = new JComboBox();
		for(model.Type t : model.Type.values()) {
			listeType.addItem(t);
		}
		colType.setCellEditor(new DefaultCellEditor(listeType)); // La colonne passée en paramètre est maintenant une colonne de combo box
    }
	
	/**
	 * Retourne le JTable de cette fenêtre.
	 * @return le JTable de cette fenêtre.
	 */
	public JTable getTable() {
		
		return this.jTable;
	}
	
	/**
	 * Retourne le JTextField nomTableTF de cette fenêtre.
	 * @return le JTextField nomTableTF de cette fenêtre.
	 */
	public JTextField getNomTableTF() {
		
		return this.nomTableTF;
	}
	
	/**
	 * Retourne le JSpinner nbColonne de cette fenêtre
	 * @return le JSpinner nbColonne de cette fenêtre
	 */
	public JSpinner getNbColonne() {
		
		return this.nbColonne;
	}
	
	/**
	 * Retourne le JButton creerTable de cette fenêtre
	 * @return le JButton creerTable de cette fenêtre
	 */
	public JButton getCreerTableBouton() {
		
		return this.creerTable;
	}
	
	/**
	 * Retourne le modèle du JTable de cetet instance
	 * @return le modèle du JTable de cetet instance
	 */
	public MyTableModel getMTM() {
		
		return this.mtm;
	}
}