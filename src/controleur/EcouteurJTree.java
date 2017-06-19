package controleur;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.tree.TreePath;
import model.*;
import vue.*;
import java.util.regex.*;

public class EcouteurJTree implements TreeSelectionListener {

	private Utilisateur lUtilisateur;
	private FenetrePrincipale fp;

	public EcouteurJTree(Utilisateur lUtilisateur, FenetrePrincipale fp){
		this.lUtilisateur=lUtilisateur;
		this.fp = fp;
	}


     public void valueChanged(TreeSelectionEvent event) {
	   try{
	     	String res = event.getNewLeadSelectionPath().toString();
	     	String [] splitArray = null;

	     	String carac1 = Pattern.quote("[");
	     	String carac2 = Pattern.quote("]");
	     	String res2 = (res.split(carac1))[1];
	     	String path = (res2.split(carac2))[0];
	     	splitArray = path.split(",");

	     	for(int i=0; i < splitArray.length;i++){
	     		if(i>=2){
					lUtilisateur.setSelection(lUtilisateur.getPositionBase(splitArray[2].trim()));
	     		}

	     		if (i==3){
	     			lUtilisateur.setTable(splitArray[3].trim());
	     		}
	     		else{
	     			lUtilisateur.setTable("");
	     		}
	     	}
	     }
	     catch(NullPointerException npe){
	     	lUtilisateur.setSelection(-1);
	     	lUtilisateur.setTable("");
	     }

		System.out.println("Base : "+lUtilisateur.getSelection());
     	System.out.println("Table : "+lUtilisateur.getTable());
		
		String retTable = DBTablePrinter.printTable(lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection(), lUtilisateur.getTable());
		System.out.println(retTable);
	 }
 }