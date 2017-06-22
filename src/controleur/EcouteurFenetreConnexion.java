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
public class EcouteurFenetreConnexion implements ActionListener {
	
	/**
	 * La FenetreConnexion à écouter
	 */
	private FenetreConnexion fc;
	
	/**
	 * Le constructeur de la classe. Initialise son attribut avec le paramètre.
	 */
	public EcouteurFenetreConnexion(FenetreConnexion fc){
		
		this.fc = fc;
		
	}
	
	/**
	 * Lorsque le bouton Connexion est pressé, récupère les informations entrées par l'utilisateur et essaye d'établir une connexion avec ces informations.
	 * <P> Si les informations sont incorrectes, affiche un message d'erreur.
	 */
	public void actionPerformed(ActionEvent e){
		if(!(fc.getNomUtiliTF().getText().equals("") || fc.getAdresseTF().getText().equals("") || String.valueOf(fc.getMdpPF().getPassword()).equals(""))){

			try{
				fc.getUtilisateur().connect(fc.getAdresseTF().getText(),fc.getNomUtiliTF().getText(),String.valueOf(fc.getMdpPF().getPassword()),fc.getNomDeLaBaseTF().getText());
				fc.getFP().getPanneauGauche().constructionJTree();
				fc.dispose();
			} catch(ClassNotFoundException cnfe){
				System.out.println("FenetreConnexion : Pilote non trouvée");
			} catch(SQLException se){
				System.out.println("FenetreConnexion : Erreur SQL");
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.out.println(ex.getMessage());
				System.out.println("FenetreConnexion : Erreur");
			}
		}
		else{
			System.out.println("Des champs ne sont pas remplis");
		}
	}
 }