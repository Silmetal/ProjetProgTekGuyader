package vue;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class PanneauButton2 extends JPanel{

	private JButton buttonLanceur,buttonEnregistrer,buttonEnregistrerSous,buttonOuvrir;
	private GridLayout monGridLayout;


	public PanneauButton2(){
		monGridLayout = new GridLayout(1,4,25,5);
		this.setLayout(monGridLayout);
		miseEnPlace();
		
	}
	

	private void miseEnPlace(){

		this.buttonLanceur=new JButton("LANCER");
		this.buttonEnregistrer= new JButton("ENREGISTRER");
		this.buttonEnregistrerSous = new JButton("ENREGISTRER SOUS");
		this.buttonOuvrir = new JButton("OUVRIR");


		this.buttonLanceur.setName("lancer");
		this.buttonEnregistrer.setName("enregistrer");
		this.buttonEnregistrerSous.setName("enregistrerSous");
		this.buttonOuvrir.setName("ouvrir");

		this.add(this.buttonLanceur);
		this.add(this.buttonEnregistrer);
		this.add(this.buttonEnregistrerSous);
		this.add(this.buttonOuvrir);

		this.buttonLanceur.setPreferredSize(new Dimension(150,50));
		this.buttonEnregistrer.setPreferredSize(new Dimension(150,50));
		this.buttonEnregistrerSous.setPreferredSize(new Dimension(150,50));
		this.buttonOuvrir.setPreferredSize(new Dimension(150,50));


	}


	public JButton getButtonLanceur(){
		return this.buttonLanceur;
	}

	public JButton getButtonEnregistrer(){
		return this.buttonEnregistrer;
	}

	public JButton getButtonEnregisterSous(){
		return this.buttonEnregistrerSous;
	}

	public JButton getButtonOuvrir(){
		return this.buttonOuvrir;
	}



	
}