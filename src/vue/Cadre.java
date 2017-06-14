package vue;

import javax.swing.*;
import java.awt.*;

public class Cadre extends JFrame{
	
	private PanneauConnexion pannConnex;
	private PanneauPrincipal pannPrinc;
	
	public Cadre(String titre){
		super(titre);
		this.miseEnPlace();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void miseEnPlace(){
		
		pannConnex = new PanneauConnexion();
		pannPrinc = new PanneauPrincipal();
		Container p = this.getContentPane();
		
		p.add(pannPrinc);
	}
	
}