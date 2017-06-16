package vue;

import javax.swing.*;
import java.awt.*;

public class Cadre extends JFrame{
	
//	private FenetreRequete panRequete;

	
	public Cadre(String titre){
		super(titre);
		this.miseEnPlace();
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
	}
	
	private void miseEnPlace(){
		
		//panRequete = new FenetreRequete();

		Container p = this.getContentPane();
		
//		p.add(panRequete);
	}
	
}