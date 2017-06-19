package controleur;
import java.util.*;
import javax.swing.event.*;
import java.awt.event.*;
import model.*;
import vue.*;
import java.sql.*;
import java.util.regex.*;

public class EcouteurFenetreConnexion implements ActionListener {

	private FenetreConnexion fc;

	public EcouteurFenetreConnexion(FenetreConnexion fc){
		
		this.fc = fc;
		
	}

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