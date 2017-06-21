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
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class FenetreNouvelleTable extends JFrame{
		
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	/**
	 *
	 */
	private JTextField nomTableTF;
	
	/**
	 *
	 */
	private JSpinner nbColonne;
	
	/**
	 *
	 */
	private JComboBox listeType;
	
	/**
	 * 
	 */
	private JComboBox listeClef;
	
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
		this.setLayout(new FlowLayout());
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		miseEnPlace();
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		
		this.nomTableTF = new JTextField();
		JLabel nomTableLabel = new JLabel("Nom de la table");

		JLabel nbColonneLabel = new JLabel("Nombre de colonne");
		nbColonne = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));
		
		
		
		mtm = new MyTableModel();
		
		jTable = new JTable(mtm);
		jTable.setSize(300,400);
		jTable.setFillsViewportHeight(true);
		
		
		// Création des sous-panneaux
		
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		this.add(nomTableLabel);
		this.add(nomTableTF);
		this.add(nbColonneLabel);
		this.add(nbColonne);
		this.add(jTable);
	}
	
	public void comboBoxTable(JTable jTable, TableColumn colType) {
	//Set up the editor for the sport cells.
	listeType = new JComboBox(Type.values());
	list = new JComboBox(Type.values());
	colType.setCellEditor(new DefaultCellEditor(listeType));
    }


	/**
	 * Cette méthode modifie le JTable pour afficher le contenu de la table selectionnée. Cette méthode est appelée à chaque fois que l'on clique sur un élément du JTree
	 * @param bd la BaseDeDonnees contenant la table sélectionnée
	 * @param table la table à afficher
	 */
	/* public void setJTable(BaseDeDonnees bd,String table){
		String[] lesVal=null;
		ArrayList<String> lesValeurs;
		String tablePrimaire="";
		int j=0;
		dTM.setColumnCount(0);
		if(!table.equals("")){

			try{
				ArrayList<String> lesAttribut = bd.parcourirTable(table);
				j=0;
				for(String str : lesAttribut){
					System.out.println(str);
					if(j==0){
						lesValeurs = bd.parcourirAttribut(str,table,"");
						tablePrimaire=str;
						System.out.println("passe");
					}
					else{
						lesValeurs = bd.parcourirAttribut(str,table,tablePrimaire);
						System.out.println("passage");
					}
					j++;
					lesVal = new String[lesValeurs.size()];

					for(int i=0;i<lesValeurs.size();i++){
						lesVal[i]=lesValeurs.get(i);
					}
					dTM.setRowCount(lesVal.length);
					dTM.addColumn(str,lesVal);
				}		
			}
			catch(SQLException se){

				se.printStackTrace();

			}
			catch(Exception e){

			}
		}
	} */
}