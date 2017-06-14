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
	








}