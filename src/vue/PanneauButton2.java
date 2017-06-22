package vue;

import javax.swing.*;
import java.awt.*;
import java.util.*;


public class PanneauButton2 extends JPanel{
	
	/**
	 * Le JButton "Lancer" de la FenetreRequete contenant ce panneau
	 */
	private JButton buttonLanceur;
	
	/**
	 * Le JButton "Enregistrer" de la FenetreRequete contenant ce panneau
	 */
	private JButton buttonEnregistrer;
	
	/**
	 * Le JButton "Enregistrer Sous" de la FenetreRequete contenant ce panneau
	 */
	private JButton buttonEnregistrerSous ;
	
	/**
	 * Le JButton "Ouvrir" de la FenetreRequete contenant ce panneau
	 */
	private JButton buttonOuvrir;
	
	/**
	 * Le GridLayout qui indique à ce panneau quelel disposition doit avoir ses éléments.
	 */
	private GridLayout monGridLayout;

	/**
	 * Le constructeur de la classe. Créé un nouveau panneau et lui applique un GridLayout(1, 4, 25, 5), puis mets en place les éléments du panneau.
	 */
	public PanneauButton2(){
		monGridLayout = new GridLayout(1,4,25,5);
		this.setLayout(monGridLayout);
		miseEnPlace();
		
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
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

	/**
	 * Retourne le bouton Lancer de l'instance
	 * @return le bouton Lancer de l'instance
	 */
	public JButton getButtonLanceur(){
		return this.buttonLanceur;
	}
	
	/**
	 * Retourne le bouton Enregistrer de l'instance
	 * @return le bouton Enregistrer de l'instance
	 */
	public JButton getButtonEnregistrer(){
		return this.buttonEnregistrer;
	}
	
	/**
	 * Retourne le bouton Enregistrer Sous de l'instance
	 * @return le bouton Enregistrer Sous de l'instance
	 */
	public JButton getButtonEnregisterSous(){
		return this.buttonEnregistrerSous;
	}
	
	/**
	 * Retourne le bouton Ouvrir de l'instance
	 * @return le bouton Ouvrir de l'instance
	 */
	public JButton getButtonOuvrir(){
		return this.buttonOuvrir;
	}



	
}