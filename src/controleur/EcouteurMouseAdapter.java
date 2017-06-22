package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import vue.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.*;


/**
 * Cette classe est l'écouteur des boutons et JPopupMenu de FenetrePrincipale. Elle associe les éléments à leurs actions.
 */
public class EcouteurMouseAdapter extends MouseAdapter {
	
	/**
	 * La fenêtre principale à écouter
	 */
	private FenetrePrincipale fp;
	
	/**
	 * L'Utilisateur qui a instancié la fenêtre écoutée par cette instance.
	 */
	private Utilisateur lUtilisateur;

	public EcouteurMouseAdapter(FenetrePrincipale fp, Utilisateur lUtilisateur){
		this.fp=fp;
		this.lUtilisateur=lUtilisateur;
		addListener();

	}
	
	/**
	* Cette méthode associe chaque élément à son action.
	* <P>Le bouton "Nouvelle Connexion" instancie une nouvelle FenetreConnexion
	* <P>Le bouton "Nouvelle Requête" instance une nouvelel FenetreRequete
	* <P>Le bouton "Table" affiche le JPopupMenu menuTable, qui propose "Créer une nouvelle table" et "Supprimer une table"
	* <P>Le bouton "Tuple" affiche le JPopupMenu menuTuple, qui propose "Ajouter un tuple" et "Supprimer un tuple"
	* <P>Le bouton "Trigger" affiche le JPopupMenu menuTrigger, qui propose "Créer un nouveau trigger" et "Supprimer un trigger"
	* <P>Le bouton "Vue" affiche le JPopupMenu menuVue, qui propose "Créer une nouvelle vue" et "Supprimer une vue"
	* <P>Cliquer sur "Créer une nouvelle table" instancie une nouvelle FenetreNouvelleTable et son écouteur.
	* <P>Cliquer sur "Supprimer une table" ouvre un JOptionPane demandant quelle table supprimer, en proposant une liste 
	* déroulante des tables de la base, puis supprime la table choisie par l'utilisateur.
	* <P>Cliquer sur "Créer un nouveau tuple"... TO BE CONTINUED
	* <P>Clique sur "Supprimer un tuple" ouvre un JOptionPane demandant quelle tuple supprimer, en proposant une liste 
	* déroulante des clés primaires des tuples de la table sélectionnée, puis supprime le tuple choisit par l'utilisateur7
	* TO BE CONTINUED
	*/
	public void mousePressed(MouseEvent e) {
			
		
			if(e.getComponent() instanceof JButton){
				System.out.println("jb");
				JButton jb = (JButton) e.getComponent();



				if(jb.getName().equals("connexion")){
					FenetreConnexion fenConnexion = new FenetreConnexion(lUtilisateur,fp);
				}
				else if(jb.getName().equals("nouvRequ")){
					if(lUtilisateur.getLesBasesDeDonnees().size()>0 && lUtilisateur.getSelection()!=-1){
						FenetreRequete fenRequete = new FenetreRequete("Requete",lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection(),fp);
					}else{

					}
				}
				else if(jb.getName().equals("tuple")){
					fp.getTupleMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-2*(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("trigger")){
					fp.getTriggerMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-3*(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("table")){
					fp.getTableMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("vue")){
					fp.getVueMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-4*(e.getComponent().getHeight()));
				}
			}
			else if(e.getComponent() instanceof JMenuItem){
				try{
					String laTableSelectionee="";
					JMenuItem jmi = (JMenuItem) e.getComponent();
					BaseDeDonnees laBaseSelectionee = lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection());
					laTableSelectionee = lUtilisateur.getTable();
					Requete nouvelleRequete = new Requete(laBaseSelectionee.getConnection(),laBaseSelectionee.getNomDeLaBase(),laTableSelectionee);


					if(jmi.getName().equals("nouvTable")){
						FenetreNouvelleTable fnt = new FenetreNouvelleTable();
						EcouteurFenetreNouvTable efnt = new EcouteurFenetreNouvTable(fnt, nouvelleRequete, this);
					}
					else if(jmi.getName().equals("supprTable")){
						
						supprimerTable(laBaseSelectionee,laTableSelectionee,nouvelleRequete);
							
					}
					else if(jmi.getName().equals("nouvTuple")){
						if(lUtilisateur.getSelection()!=-1 && !(lUtilisateur.getTable().equals(""))){
							
							nouvelleRequete.ajouterTuple();
						}
					}
					else if(jmi.getName().equals("supprTuple")){
						
						supprimerTuple(laBaseSelectionee,laTableSelectionee,nouvelleRequete);
						
					}
					else if(jmi.getName().equals("nouvTrigger")){
						nouvelleRequete.ajouterTrigger();
					}
					else if(jmi.getName().equals("supprTrigger")){
						nouvelleRequete.enleverTrigger();
					}
					else if(jmi.getName().equals("nouvVue")){
						nouvelleRequete.ajouterVue();
					}
					else if(jmi.getName().equals("supprVue")){
						nouvelleRequete.enleverVue();
					}
				}
				catch(SQLException se){
					se.printStackTrace();
				}
				catch(Exception ex){

				}
			}

	}



	public void nouvelleTable(Requete nouvelleRequete, EcouteurFenetreNouvTable efnt) throws SQLException, Exception{
		
		String nomTable = efnt.getNomTable();
		ArrayList<Attribut> listeAtt = efnt.getListeAtt();
		nouvelleRequete.ajouterTable(nomTable,listeAtt);		
	}

	public void nouveauTuple(){

	}

	public void supprimerTable(BaseDeDonnees laBaseSelectionee,String laTableSelectionee,Requete nouvelleRequete) throws SQLException,Exception{
		if(lUtilisateur.getSelection()!=-1){
			ArrayList<String> lesTables = laBaseSelectionee.parcourirBase();
			String[] lesTab = new String[lesTables.size()];

			for(int i=0;i<lesTables.size();i++){
				lesTab[i]=lesTables.get(i);
			}

			String table = (String) JOptionPane.showInputDialog(fp, 
		        "Quelle table/vue voulez vous supprimer ?",
		        "Suppression de table/vue",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        lesTab, 
		        lesTab[0]);

			nouvelleRequete.enleverTable(table);
			fp.getPanneauGauche().constructionJTree();
		}
	}

	public void supprimerTuple(BaseDeDonnees laBaseSelectionee,String laTableSelectionee,Requete nouvelleRequete) throws SQLException,Exception{
		int k=0;
		String attribut;
		if(lUtilisateur.getSelection()!=-1 && !(laTableSelectionee.equals(""))){
			ArrayList<String> lesValeurs;
			String[] lesVal=null;
			ArrayList<String> lesAttribut = laBaseSelectionee.parcourirTable(laTableSelectionee);
			
			attribut=lesAttribut.get(0);
			lesValeurs = laBaseSelectionee.parcourirAttribut(lesAttribut.get(0),laTableSelectionee,"");
			
			
			lesVal = new String[lesValeurs.size()];

			for(int i=0;i<lesValeurs.size();i++){
				lesVal[i]=lesValeurs.get(i);
			}

			String tuple = (String) JOptionPane.showInputDialog(fp, 
		        "Quelle tuple voulez vous supprimer ?",
		        "Suppression de tuple",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        lesVal, 
		        lesVal[0]);
			System.out.println("Attribut "+attribut);
			System.out.println("Tuple "+tuple);
			nouvelleRequete.enleverTuple(tuple,attribut);
		}
	}



	

	public void addListener(){
		fp.getBoutonNouvRequ().addMouseListener(this);
		fp.getBoutonTable().addMouseListener(this);
		fp.getBoutonTrigger().addMouseListener(this);
		fp.getBoutonTuple().addMouseListener(this);
		fp.getBoutonVue().addMouseListener(this);

		fp.getPanneauGauche().getBoutonConnexion().addMouseListener(this);

		fp.getNouvTable().addMouseListener(this);
		fp.getSupprTable().addMouseListener(this);
		fp.getNouvTuple().addMouseListener(this);
		fp.getSupprTuple().addMouseListener(this);
		fp.getNouvTrigger().addMouseListener(this);
		fp.getSupprTrigger().addMouseListener(this);
		fp.getNouvVue().addMouseListener(this);
		fp.getSupprVue().addMouseListener(this);
	}
}