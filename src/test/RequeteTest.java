package test;
import model.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.sql.*;


public class RequeteTest{

	private Requete uneRequete;
	private Requete uneRequeteBase;

	public RequeteTest(){
		try{
			BaseDeDonnees maBase = new BaseDeDonnees("jdbc:mysql://vps.arthurguyader.fr:3306/TestUnitaire","admin", "iutvannes","TestUnitaire");
			Connection connexion = maBase.getConnection();
			Requete uneRequete = new Requete(connexion,"","");
			Requete uneRequeteBase = new Requete(connexion,"TestUnitaire","");
		}
		catch(Exception e){

		}
	}



	@Before()
	public void miseEnPlace(){		
	}

	@Test()
	public void testConstructeur(){
		
	}

}