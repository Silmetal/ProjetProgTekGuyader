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

			monUtilisateur.connect("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
			monUtilisateur.setSelection(0);
			BaseDeDonnees bd = monUtilisateur.getLesBasesDeDonnees().get(monUtilisateur.getSelection());
			Requete uneRequete = new Requete(bd.getConnection(),"TestUnitaire","");
			monUtilisateur.creerBaseDeDonnees("iutvannes","NouvelleBase",uneRequete);
 			

			String nomDesBases="";
			Requete interogation = new Requete(bd.getConnection(),"","");
			ResultSet rs = (ResultSet) ((interogation.manuel("SHOW DATABASES;"))[1]);
			ResultSetMetaData rsmd = rs.getMetaData();
	   		int columnsNumber = rsmd.getColumnCount();		
	   		while (rs.next()) {
		       for (int j = 1; j <= columnsNumber; j++) {
		           	nomDesBases = nomDesBases + rs.getString(j);
		        }
		    }

		    if (nomDesBases.indexOf("NouvelleBase") >= 0) {
		    	Assert.assertTrue(true);
		    }
		    else{
		    	Assert.assertTrue(false);
		    }
		    monUtilisateur.setSelection(1);
			monUtilisateur.supprimerBaseDeDonnees("iutvannes",uneRequete);


			nomDesBases="";
			interogation = new Requete(bd.getConnection(),"","");
			rs = (ResultSet) ((interogation.manuel("SHOW DATABASES;"))[1]);
			rsmd = rs.getMetaData();
	   		columnsNumber = rsmd.getColumnCount();		
	   		while (rs.next()) {
		       for (int j = 1; j <= columnsNumber; j++) {
		           	nomDesBases = nomDesBases + rs.getString(j);
		        }
		    }

		    if (nomDesBases.indexOf("NouvelleBase") >= 0) {
		    	Assert.assertTrue(false);
		    }
		    else{
		    	Assert.assertTrue(true);
		    }

		}
		catch(Exception e){
			Assert.assertTrue(false);
		}

	}





}