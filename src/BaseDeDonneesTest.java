import org.junit.*;
import static org.junit.Assert.*;

public class BaseDeDonneesTest{

	public BaseDeDonneesTest(String name){
		super(name);
	}





	@Test()
	public void testVerifDriver(){
		assertTrue(BaseDeDonnees.verifDriver())
	}



}