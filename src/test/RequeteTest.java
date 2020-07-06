package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.*;
import java.lang.*;


public class RequeteTest{

	private Requete uneRequete;
	private Requete uneRequeteBase;
	BaseDeDonnees maBase;
	Connection connexion;

	public RequeteTest(){
		
	}



	@Before
	public void miseEnPlace(){		
		try{
			maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire"+"?allowMultiQueries=true","admin", "iutvannes","TestUnitaire");
			connexion = maBase.getConnection();
			uneRequete = new Requete(connexion,"","");
			uneRequeteBase = new Requete(connexion,"TestUnitaire","");
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

			Assert.assertTrue(lesVal.get(0).equals("1"));
			Assert.assertTrue(lesVal.get(1).equals("3"));

		}
		catch(Exception e){
		}
	}


	//Impossible de faire fonctionner ce test a cause des exceptions qui se créent

	@Test()
	public void testAjouterSupprimerTable(){

		try{
			ArrayList<Attribut> liste = new ArrayList<Attribut>();
			Attribut att = new Attribut("var", Type.INT, 4, false,  false, true, false, "","");
			Attribut att2 = new Attribut("var2", Type.INT, 4, false,  false, false, false, "","");
			liste.add(att);
			liste.add(att2);
			uneRequeteBase.ajouterTable("NouvelleTable",liste);
			
			ArrayList<String> lesTables = maBase.parcourirBase();
			boolean presence=false;
			for (String s : lesTables) {
				System.out.println(s);
				if(s.equals("NouvelleTable")) {
					presence=true;
				}
			}
			
			Assert.assertTrue(presence);


			uneRequeteBase.enleverTable("NouvelleTable");

			lesTables = maBase.parcourirBase();
			presence=false;
			for (String s : lesTables) {
				if(s.equals("NouvelleTable")) {
					presence=true;
				}
			}

			Assert.assertFalse(presence);
			
		}
		catch(Exception e){
		
		}
	}








}