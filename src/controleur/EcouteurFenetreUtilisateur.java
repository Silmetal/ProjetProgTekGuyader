package controleur;
import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;
import model.*;
import vue.*;
import java.sql.*;
import java.util.regex.*;

/**
 * Cette classe est l'écouteur Du bouton "Connexion" de FenetreConnexion. Elle associe le bouton à son action.
 */
public class EcouteurFenetreUtilisateur implements ActionListener {
	
	/**
	 * La FenetreNouvelUtilisateur à écouter
	 */
	private FenetreNouvelUtilisateur fu;

	private String table;

	private BaseDeDonnees uneBaseDeDonnees;
	
	/**
	 * Le constructeur de la classe. Initialise ses attributs avec les paramètres.
	 * @param fu la FenetreNouvelUtilisateur à écouter
	 * @param uneBaseDeDonnees La Base De Donnee selectionnée
	 * @param table La table séléctionnée
	 */
	public EcouteurFenetreUtilisateur(FenetreNouvelUtilisateur fu,BaseDeDonnees uneBaseDeDonnees,String table){
		this.uneBaseDeDonnees = uneBaseDeDonnees;
		this.table = table;
		if(this.table.equals("")) this.table="*";
		this.fu = fu;
		fu.getJb().addActionListener(this);
		
	}
	
	/**
	 * Lorsque le bouton valider est pressé, récupère les informations entrées par l'utilisateur et essaye de créer un utilisateur avec ces informations.
	 * <P> Si les informations sont incorrectes, affiche un message d'erreur.
	 */
	public void actionPerformed(ActionEvent e){
		int num=0;
		if(!(fu.getJtf1().getText().equals("") || String.valueOf(fu.getJpf2().getPassword()).equals("")) && (fu.getJrb1().isSelected() || fu.getJrb2().isSelected())){

			try{
				if(fu.getJrb2().isSelected()){
					num=1;
				}
				uneBaseDeDonnees.ajouterNouvelUtilisateur(fu.getJtf1().getText(),String.valueOf(fu.getJpf2().getPassword()),table,num);
				fu.dispose();
			}
			catch(SQLException se){
				
			}
			catch(Exception ex){
	
			}
		}
		else{
			System.out.println("Des champs ne sont pas remplis");
		}
	}
 }