package vue;

import javax.swing.*;
import java.awt.*;

public class Cadre extends JFrame{
	
	private PanneauConnexion pannConnex;
	
	public Cadre(String titre){
		super(titre);
		this.miseEnPlace();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private void miseEnPlace(){
		
		pannConnex = new PanneauConnexion();
		Container p = this.getContentPane();
		
		p.add(pannConnex);
	}
	
}