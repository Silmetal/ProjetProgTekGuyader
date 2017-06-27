package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.*;


public class RequeteTest{

	private Requete uneRequete;
	private Requete uneRequeteBase;

	public RequeteTest(){
		
	}



	@Before()
	public void miseEnPlace(){		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire"+"?allowMultiQueries=true","admin", "iutvannes","TestUnitaire");
			Connection connexion = maBase.getConnection();
			Requete uneRequete = new Requete(connexion,"","");
			Requete uneRequeteBase = new Requete(connexion,"TestUnitaire","");
		}
		catch(Exception e){}
	}

	@Test()
	public void testCreerOuModifier(){
		try{
			int nb = uneRequeteBase.creerOuModifier("CREATE TABLE NouvelleTable");
			Assert.assertTrue(nb==1);
			uneRequeteBase.creerOuModifier("DROP TABLE NouvelleTable");
		}
		catch(Exception e){
		}
	}



	@Test()
	public void testManuelEtRetourneResultSet(){
		try{
			Object[] valeurs = uneRequeteBase.manuel("SELECT valeurTest FROM TableTest");
			Assert.assertTrue((boolean) valeurs[0]);

			ResultSet rs = (ResultSet) valeurs[1];

			Object[] result = Requete.retournerResultSet(rs,false);

			ArrayList<String> lesVal = (ArrayList<String>) result[0];

			ArrayList<String> lesCol = (ArrayList<String>) result[1];

			Assert.assertTrue(lesCol.get(0).equals("valeurTest"));

			Assert.assertTrue(lesVal.get(0).equals(1));
			Assert.assertTrue(lesVal.get(1).equals(3));

		}
		catch(Exception e){
		}
	}


	@Test()
	public void testAjouterSupprimerTable(){
		try{
			ArrayList<Attribut> liste = new ArrayList<Attribut>();
			Attribut att = new Attribut(var, Type.INT, 4, false,  false, true, false, "","");
			liste.add(att);
			uneRequeteBase.ajouterTable("NouvelleTable",liste);
			
		}
		catch(Exception e){
		}
	}








}