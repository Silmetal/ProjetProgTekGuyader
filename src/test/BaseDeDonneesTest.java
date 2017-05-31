package test;

import org.junit.*;
import static org.junit.Assert.*;

public class BaseDeDonneesTest{

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

        assertEquals(test,BaseDeDonnees.verifPilote());


	}














}