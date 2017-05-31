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
	public void testVerifDriver(){
		assertTrue(BaseDeDonnees.verifDriver());
	}







}