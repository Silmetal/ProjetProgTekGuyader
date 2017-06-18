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
	

	private Utilisateur lUtilisateur;

	private HashMap<String,Integer> emplacementBase;

	private JPanel arbre;
	
	private FenetrePrincipale fp;


	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
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
		arborescence.addTreeSelectionListener(new EcouteurJTree());
		connexion = new JButton("Nouvelle Connexion");
		connexion.setName("connexion");
		
		// Création des sous-panneaux
		arbre = new JPanel();
		JPanel panneauBoutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		arbre.add(arborescence);
		panneauBoutton.add(connexion);
		
		// Ajout des sous-panneaux 
		this.add(arbre, BorderLayout.WEST);
		this.add(panneauBoutton, BorderLayout.NORTH);		
	}
	


	public void constructionJTree(){
		DatabaseMetaData dmd;
		ResultSet tables;
		String affichage="";
		int i=0;
		String url=lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getLesBasesDeDonnees().size()-1).getAdresse();
		System.out.println("url1"+url);
		String user=lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getLesBasesDeDonnees().size()-1).getNomUtili();
		String password=lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getLesBasesDeDonnees().size()-1).getMotDePasse();
		int selection = lUtilisateur.getSelection();
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(lUtilisateur.getId());
		for(BaseDeDonnees base : lUtilisateur.getLesBasesDeDonnees()){
			DefaultMutableTreeNode rep = new DefaultMutableTreeNode(base.getNomDeLaBase());
			try{

				dmd = base.getConnection().getMetaData();
				tables = dmd.getTables(base.getConnection().getCatalog(),null,"%",null);
				i=0;
				while(tables.next()){
					DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(tables.getString(3));
					rep.add(rep2);
					i++;
				}
			} catch(SQLException e){
				System.out.println("Impossible de construire l'arbre");
			}
			racine.add(rep);
		/*	if(i==0){
				try{
					
					Requete interogation = new Requete(base.getConnection());
					ResultSet rs = (ResultSet) ((interogation.manuel("SHOW DATABASES;"))[1]);
					System.out.println("passage1");
					ResultSetMetaData rsmd = rs.getMetaData();
			   		int columnsNumber = rsmd.getColumnCount();		
			   		while (rs.next()) {
			   			System.out.println("passage2");
			   			//System.out.println(columnsNumber);
				       for (int j = 1; j <= columnsNumber; j++) {

				           	String columnValue = rs.getString(j);

				           	if(!columnValue.equals("") && !columnValue.equals("information_schema") && !columnValue.equals("performance_schema") && !columnValue.equals("mysql") ){
				           		System.out.println(columnValue);
				           		System.out.println("url2"+url);
				           		lUtilisateur.connect(url+columnValue,user,password,columnValue);
				           		BaseDeDonnees base2 = lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getLesBasesDeDonnees().size()-1);
				           		rep = new DefaultMutableTreeNode(base2.getNomDeLaBase());
				           		
				      				dmd = base2.getConnection().getMetaData();
									tables = dmd.getTables(base2.getConnection().getCatalog(),null,"%",null);
									i=0;
									System.out.println("passage3");
									while(tables.next()){
										DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode(tables.getString(3));
										rep.add(rep2);
										i++;
										System.out.println("passage4");
									}
							
								racine.add(rep);
				           	}
				       }
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}

				lUtilisateur.setSelection(selection);
				lUtilisateur.disconnect();

			}else{
					
			}*/


		
		}

		System.out.println("ok");
		((DefaultTreeModel)arborescence.getModel()).setRoot(racine);
		
	}


	public JButton getBouttonConnexion() {
		
		return this.connexion;
	}
	
	public JTree getArborescence() {
		
		return this.arborescence;
	}
}