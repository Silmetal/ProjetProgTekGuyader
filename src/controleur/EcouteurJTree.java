package controleur;
import java.util.*;
import java.awt.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.tree.TreePath;
import model.*;
import vue.*;
import java.util.regex.*;

/**
* Cette classe est l'écouteur du JTree de la FenetrePrincipale. Permet de savoir quelle base de données, quelle table et quel attribut est sélectionné.
*/
public class EcouteurJTree implements TreeSelectionListener {
	
	/**
	 * L'Utilisateur qui a instancié la fenêtre contenant le JTree écouté par cette instance.
	 */
	private Utilisateur lUtilisateur;
	
	/**
	 * La FenetrePrincipale qui contient le JTree écouté par cette instance.
	 */
	private FenetrePrincipale fp;
	
	/**
	 * Le constructeur de la classe. Initialisé les attributs de la classe en fonction des données passés en paramètre.
	 */
	public EcouteurJTree(Utilisateur lUtilisateur, FenetrePrincipale fp){
		this.lUtilisateur=lUtilisateur;
		this.fp = fp;
	}

	/**
	 * La méthode qui modifier l'attribut selection de l'Utilisateur, qui indique quelle BaseDeDonnees est sélectionnée, en fonction de la base de données sur laquelle l'utilisateur a cliqué.
	 * Si l'utilisateur clique sur une table, la méthode modifie également l'attribut table de l'Utilisateur, et affiche le contenu de la table sélectionnée dans la FenetrePrincipale 
	 */
	public void valueChanged(TreeSelectionEvent event) {
	try{	

		String res = event.getNewLeadSelectionPath().toString();
	// 	System.out.println(res);

		String [] splitArray = null;


		DefaultMutableTreeNode node = (DefaultMutableTreeNode)fp.getPanneauGauche().getArborescence().getLastSelectedPathComponent();
		Object[] nodeInfo = node.getUserObjectPath();


		for(int i=0; i < nodeInfo.length;i++){
			if(i>=2){
				lUtilisateur.setSelection(lUtilisateur.getPositionBase(((Noeud)nodeInfo[2]).getName()));
			}

			if (i>=3){
				lUtilisateur.setTable(((Noeud)nodeInfo[3]).getName());
				fp.setJTable(lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()),lUtilisateur.getTable());

			}
			else{
				lUtilisateur.setTable("");
			}
		}
		
		String retTable = DBTablePrinter.printTable(lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection(), lUtilisateur.getTable());
		// System.out.println(retTable);
	}
	catch(NullPointerException npe){
		lUtilisateur.setSelection(-1);
		lUtilisateur.setTable("");
	}

	System.out.println("Table : "+lUtilisateur.getTable());
	System.out.println("Base : "+lUtilisateur.getSelection());

	// String retTable = DBTablePrinter.printTable(lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection(), lUtilisateur.getTable());
	// System.out.println(retTable);
	}
 }