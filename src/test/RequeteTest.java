package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;


public class RequeteTest{

	private BaseDeDonnees maBase;

	public RequeteTest(){
		
	}


	@Before()
	public void miseEnPlace(){		
	}

	@Test()
	public void testConstructeur(){
		boolean test=true;
		BaseDeDonnees maBase2 = null;
		BaseDeDonnees maBase = null;
		
		try{
			maBase = new BaseDeDonnees("jdbc:mysql://arthurguyader.ddns.net:3306/","admin", "admin","test");
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
		finally {
			maBase.deconnexion();
		}


    	try{
    		maBase2 = new BaseDeDonnees("jdbc:mysql://arthurguyader.ddns.net:3306/","qfqsfn", "admin","test");
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
	

	/* @Test()
	public void testAjouterSupprimerUtilisateur(){
		boolean test=true;
		
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://arthurguyader.ddns.net:3306/","admin", "admin");
			maBase.ajouterNouvelUtilisateur("truc","truc", "projet",1);
			// BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:xe","admin", "admin");
			maBase.supprimerUtilisateur("truc");
			System.out.println("test ajout fait");
		}
		catch(Exception e){
			System.out.println("test ajout échoué");
			e.printStackTrace();
			assertTrue(false);
		}
		finally {
			maBase.deconnexion();
		}
	} */

}