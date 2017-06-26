package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;
import java.util.*;


public class BaseDeDonneesTest{

	private BaseDeDonnees maBase;

	public BaseDeDonneesTest(){
		
	}


	@Before()
	public void miseEnPlace(){		
	}

	@Test()
	public void testConstructeur(){
		boolean test=true;
		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/","admin", "iutvannes","base");
			assertNotNull(maBase.getConnection());
		}
		catch(ClassNotFoundException ce){
			assertFalse(true);
		}
		catch(SQLException se){
			assertFalse(true);
		}
		catch(Exception e){
			assertFalse(true);
		}


    	try{
    		BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/","qfqsfn", "iutvannes","base");
    	}
    	catch(ClassNotFoundException ce){
			assertFalse(false);
		}
		catch(SQLException se){
			assertFalse(false);
		}
		catch(Exception e){
			assertFalse(false);
		}


	}


	@Test()
	public void testParcourirBase(){
		try{
    		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
    		ArrayList<String> liste = maBase.parcourirBase();
    		
    		Assert.assertTrue(liste.get(0).equals("TableCleEtran"));
    		Assert.assertTrue(liste.get(1).equals("TableDeuxClePrim"));
    		Assert.assertTrue(liste.get(2).equals("TableTest"));

    	}
    	catch(ClassNotFoundException ce){
			assertTrue(false);
		}
		catch(SQLException se){
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(false);
		}
	}


	@Test()
	public void testParcourirTable(){
		try{
    		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
    		Object[] liste = maBase.parcourirTable("TableTest");
    		ArrayList<String> nomColonne = (ArrayList<String>) liste[0];
    		
    		Assert.assertTrue(nomColonne.get(0).equals("valeurTest"));
    		Assert.assertTrue(nomColonne.get(1).equals("valeurTest2"));


    	}
    	catch(ClassNotFoundException ce){
			assertTrue(false);
		}
		catch(SQLException se){
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(false);
		}
	}


	@Test()
	public void testParcourirAttribut(){
		try{
    		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
    		ArrayList<String> valeur1 = maBase.parcourirAttribut("valeurTest","TableTest","");
    		
    		ArrayList<String> valeur2 = maBase.parcourirAttribut("valeurTest2","TableTest","valeurTest");


    		Assert.assertTrue(valeur1.get(0).equals("1"));
    		Assert.assertTrue(valeur1.get(1).equals("3"));


    		Assert.assertTrue(valeur2.get(0).equals("2"));
    		Assert.assertTrue(valeur2.get(1).equals("4"));

    	}
    	catch(ClassNotFoundException ce){
			assertTrue(false);
		}
		catch(SQLException se){
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(false);
		}
	}


	@Test()
	public void testRecupererInfo(){
		try{
    		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
    		Object[] res = maBase.recupererInfo("TableTest","valeurTest");


    		Assert.assertTrue((boolean) res[0]);
    		Assert.assertTrue((boolean) res[1]);
    		Assert.assertTrue(((String)res[2]).equals("int(4)"));

    		res = maBase.recupererInfo("TableCleEtran","cleEtr");


    		Assert.assertFalse((boolean) res[0]);
    		Assert.assertFalse((boolean) res[1]);
    		Assert.assertTrue(((String)res[2]).equals("int(4)"));
    		Assert.assertTrue(((String)res[3]).equals("TableTest"));
    		Assert.assertTrue(((String)res[4]).equals("valeurTest"));

    	}
    	catch(ClassNotFoundException ce){
			assertTrue(false);
		}
		catch(SQLException se){
			assertTrue(false);
		}
		catch(Exception e){
			assertTrue(false);
		}
	}




	
	
/*
	@Test()
	public void testAjouterSupprimerUtilisateur(){
		boolean test=true;
		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://arthurguyader.ddns.net:3306/","user", "pass");
			maBase.ajouterNouvelUtilisateur("testk","root", "projet",1);
			// BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:xe","admin", "admin");
			
			System.out.println("test ajout fait");
		}
		catch(Exception e){
			System.out.println("test ajout échoué");
			e.printStackTrace();
			assertTrue(false);
		}

		try{
			maBase.supprimerUtilisateur("testk");
		}
		catch(Exception e){

		}


	}
	*/

}