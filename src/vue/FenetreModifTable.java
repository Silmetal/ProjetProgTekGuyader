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
 * 
 */
public class FenetreModifTable extends JFrame{
		
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	
	/**
	 * Le bouton qui lance la requête SQL d'insertion et modification de tuple suivant ce qui a été saisi par l'utilisateur dans le JTable
	 */
	private JButton termine;
	
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
	 */
	public FenetreModifTable(FenetrePrincipale fp){
		super("Modifier une table");
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
		
		dTM = new DefaultTableModel();
		
		jTable = new JTable(dTM);
		setJTable(fp.getUtilisateur().getLesBasesDeDonnees().get(fp.getUtilisateur().getSelection()),fp.getUtilisateur().getTable());
		
		termine = new JButton("Terminer");
		
		
		// Création des sous-panneaux
		JScrollPane scrollPane = new JScrollPane(jTable);
		JPanel pannBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		pannBouton.add(termine);
		
		// Ajout des sous-panneaux dans le panneau principal
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(pannBouton, BorderLayout.SOUTH);
	}
	
	/**
	 * Cette méthode modifie le JTable pour afficher le contenu de la table selectionnée. Cette méthode est appelée à chaque fois que l'on clique sur un élément du JTree
	 * @param bd la BaseDeDonnees contenant la table sélectionnée
	 * @param table la table à afficher
	 */
	public void setJTable(BaseDeDonnees bd,String table){
		String[] lesVal=null;
		ArrayList<String> lesValeurs;
		String tablePrimaire="";
		dTM.setColumnCount(0);
		if(!table.equals("")){

			try{
				Object[] lesAtt = bd.parcourirTable(table);

				
				ArrayList<String> valeur = (ArrayList<String>)(lesAtt[0]);
				ArrayList<String> titre =(ArrayList<String>)(lesAtt[1]);



				for(int j=0; j<valeur.size();j++){
					if(j==0){
						lesValeurs = bd.parcourirAttribut(valeur.get(0),table,"");
						tablePrimaire=valeur.get(0);
					}
					else{
						lesValeurs = bd.parcourirAttribut(valeur.get(j),table,tablePrimaire);
					}

					lesVal = new String[lesValeurs.size()];

					for(int i=0;i<lesValeurs.size();i++){
						lesVal[i]=lesValeurs.get(i);
					}
					dTM.setRowCount(lesVal.length);
					dTM.addColumn(titre.get(j),lesVal);
				}		
			}
			catch(SQLException se){

				se.printStackTrace();

			}
			catch(Exception e){

			}
		}
	}
	
	public JTable getTable() {
		
		return this.jTable;
	}
	
	public JButton getTermineBouton() {
		
		return this.termine;
	}
	
	public DefaultTableModel getDTM() {
		
		return this.dTM;
	}
}