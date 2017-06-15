package controleur;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import IHM.*;



public class EcouteurJButton implements ActionListener {

	private JFrame jf;
	private FenetreRequete fr;

	public ButtonParamListener(JFrame jf,FenetreRequete fr){
		this.jf=jf;
		this.fr=fr;
	}
	

	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();

		if(jb.getName().equals("lancer")){
			this.lancer();
		}
		else if(jb.getName().equals("enregistrer")){
			this.enregistrer();
		}
		else if(jb.getName().equals("enregistrerSous")){
			this.enregistrerSous();
		}
		else if(jb.getName().equals("ouvrir")){
			this.ouvrir();
		}

	}


	public void lancer(){

	}


	public void enregistrer(){

	}

	public void enregistrerSous(){

	}

	public void ouvrir(){

	}


	public void addListener(){
		
	}

}
