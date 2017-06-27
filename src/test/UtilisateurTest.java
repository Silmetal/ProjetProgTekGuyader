package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.*;


public class UtilisateurTest{

	private Utilisateur monUtilisateur;

	public UtilisateurTest(){
		
	}


	@Before()
	public void miseEnPlace(){
		monUtilisateur = new Utilisateur("utilisateur1");
	}

	@Test()
	public void testConnect(){
		

		try{
			monUtilisateur.connect("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");

			Assert.assertTrue(monUtilisateur.getLesBasesDeDonnees().size()==1);


			monUtilisateur.connect("jdbc:mysql://vps.arthurguyader.fr:3306/","admin", "iutvannes","");

			Assert.assertTrue(monUtilisateur.getLesBasesDeDonnees().size()>1);
		}
		catch(Exception e){
			Assert.assertTrue(false);
		}

	}

	@Test()
	public void testCreerSupprimerBaseDeDonnees(){
		try{
			BaseDeDonnees bd = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
			monUtilisateur.setSelection(0);
			Requete uneRequete = new Requete(bd.getConnection(),"TestUnitaire","");
			monUtilisateur.creerBaseDeDonnees("iutvannes","NouvelleBase",uneRequete);

			monUtilisateur.supprimerBaseDeDonnees("iutvannes",uneRequete);

		}
		catch(Exception e){

		}

	}


	@Test()
	public void testCreerBaseDeDonnees(){
		try{
			BaseDeDonnees bd = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
			monUtilisateur.setSelection(0);
			Requete uneRequete = new Requete(bd.getConnection(),"TestUnitaire","");
			monUtilisateur.creerBaseDeDonnees("iutvannes","NouvelleBase",uneRequete);
		}
		catch(Exception e){
			
		}

	}




}