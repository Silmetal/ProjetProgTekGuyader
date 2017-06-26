package vue;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import model.*;
import javax.swing.tree.*;
import java.sql.*;
import controleur.*;

/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class PanneauGauche extends JPanel{
	/**
	 * Le bouton de connexion
	 */
	private JButton connexion;
	
	/**
	 * L'arborescence permmetant de changer de base de données, de table, de trigger etc.
	 */
	private JTree arborescence;
	
	/**
	 * L'Utilisateur qui a instancé ce panneau.
	 */
	private Utilisateur lUtilisateur;

	/**
	 * Le JPanel contenu dans ce panneau qui contient le JTree
	 */
	private JPanel arbre;
	
	/**
	 * La FenetrePrincipale qui contient de PanneauGauche
	 */
	private FenetrePrincipale fp;


	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 * @param lUtilisateur l'utilisateur qui a instancié la fenêtre
	 * @param fp la fenêtre contenant ce panneau
	 */
	public PanneauGauche(Utilisateur lUtilisateur,FenetrePrincipale fp){
		this.fp = fp;
		this.setLayout(new BorderLayout());
		this.lUtilisateur=lUtilisateur;
		miseEnPlace();
		
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans le panneau.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(lUtilisateur.getId());
		DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Attente de la première connexion ...");
		racine.add(rep);

		arborescence = new JTree(racine);
		arborescence.addTreeSelectionListener(new EcouteurJTree(lUtilisateur, fp));
		connexion = new JButton("Nouvelle Connexion");
		connexion.setName("connexion");
		
		// Création des sous-panneaux
		arbre = new JPanel();
		JPanel panneauBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		JScrollPane span1 = new JScrollPane(arborescence);
		panneauBouton.add(connexion);
		
		// Ajout des sous-panneaux 
		this.add(span1, BorderLayout.WEST);
		this.add(panneauBouton, BorderLayout.NORTH);		
	}
	
	/**
	 * Mets à jour le JTree en fonction des requêtes et des informations de connexion. Cette méthode est appelée à la connexion et à chaque fois qu'une requête est lancée.
	 */
	public void constructionJTree(){
		TreePath tp = arborescence.getSelectionPath();
		int i=0;
	//	ImageIcon imgIc = new ImageIcon("../fichier/bdd.jpg");
		try{
			DefaultMutableTreeNode racine = new DefaultMutableTreeNode(lUtilisateur.getId());
			DefaultMutableTreeNode tableVue = new DefaultMutableTreeNode("TABLES ET VUES");
			for(BaseDeDonnees base : lUtilisateur.getLesBasesDeDonnees()){
				DefaultMutableTreeNode rep = new DefaultMutableTreeNode(new Noeud(base.getNomDeLaBase(),"base"));
				ArrayList<String> lesTables = base.parcourirBase();
				for(String s : lesTables){
					DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(new Noeud(s,"table"));
					
					ArrayList<String> lesAttributs = (ArrayList<String>) (base.parcourirTable(s))[0];
					for(String str : lesAttributs){
						DefaultMutableTreeNode rep3 = new DefaultMutableTreeNode(new Noeud(str,"attribut"));
						rep2.add(rep3);
					}
					rep.add(rep2);
				}
				tableVue.add(rep);
				racine.add(tableVue);		
			}

			((DefaultTreeModel)arborescence.getModel()).setRoot(racine);
			arborescence.setCellRenderer(new ModificationJTree());
			
			arborescence.setSelectionPath(tp);

			arborescence.expandRow(1);
			System.out.println(lUtilisateur.getSelection());
			if(lUtilisateur.getSelection()!=-1)
			arborescence.expandRow(2+lUtilisateur.getSelection());
			//lUtilisateur.getSelection();


		}
		catch(SQLException se){

		}
		catch(Exception e){

		}
	}
	
	/**
	 * Retourne la FenêtrePrincipale contenant ce PanneauGauche
	 * @return la FenêtrePrincipale contenant ce PanneauGauche
	 */
	public FenetrePrincipale getFP() {
		
		return this.fp;
	}
	
	/**
	 * Retourne le BoutonConnexion de l'instance
	 * @return le BoutonConnexion de l'instance
	 */
	public JButton getBoutonConnexion() {
		
		return this.connexion;
	}
	
	/**
	 * Retourne le JTree arborescence de l'instance
	 * @return le JTree arborescence de l'instance
	 */
	public JTree getArborescence() {
		
		return this.arborescence;
	}
}