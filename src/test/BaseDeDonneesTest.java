package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;


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
		
		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:xe","root", "root");
    	Assert.assertTrue(maBase.getConnection()!=null);

	}
	
/*
	@Test() // valeur local actuellement
	public void testCreerRequete(){

		// test avec l'attribut connexion qui existe

		BaseDeDonnees maBase = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:xe","root", "root");
		Statement maRequete = maBase.creerRequete();

		Assert.assertTrue(maRequete!=null);

		// test sans l'attribut connexion

		BaseDeDonnees maBase2 = new BaseDeDonnees("jdbc:oracle:thin:@localhost:1521:orcl","root", "root");
		Statement maRequete2 = maBase.creerRequete();

		Assert.assertTrue(maRequete2==null);


	}
*/






}