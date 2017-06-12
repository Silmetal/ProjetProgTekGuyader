import org.junit.*;
import static org.junit.Assert.*;

public class BaseDeDonneesTest{

	private BaseDeDonnees maBase;

	public BaseDeDonneesTest(String name){
		super(name);
	}


	@Before()
	protected void miseEnPlace(){		
	}

	@Test()
	public void testVerifPilote(){
		boolean test=true;
		
		try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            test=false;
        }

        Assert.assertEquals(test,BaseDeDonnees.verifPilote());
	}
	

	@Test() // valeur local actuellement
	public void testConnexion(){
		boolean res;

		//Test de la connexion avec des valeurs valides

		res = BaseDeDonnees.connexion("jdbc:oracle:thin:@localhost:1521:xe","root", "root"); 
		Assert.assertTrue(res);


		//Test de la connexion avec des valeurs erronées

		//Adresse erronée

		res = BaseDeDonnees.connexion("jdbc:oracle:thin:@localhost:1521:orcl","root", "root"); 
		Assert.assertFalse(res);
		//Nom Utilisateur erroné
		res = BaseDeDonnees.connexion("jdbc:oracle:thin:@localhost:1521:xe","admin", "root"); 
		Assert.assertFalse(res);
		//Mot de Passe erroné
		res = BaseDeDonnees.connexion("jdbc:oracle:thin:@localhost:1521:xe","root", "admin"); 
		Assert.assertFalse(res);
	}


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







}