package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class FenetreRequete extends JFrame{
	
	/**
	 * Le JTextPane qui correspondant au champs de saisie 
	 */
	private JTextPane champsSaisie;
	
	/**
	 * Le JTextPane qui correspondant à la console
	 */
	private JTextPane console;
	
	/**
	 * Le panneau contenant les boutons "Lancer", "Enregistrer", "Ouvrir" et "Enregistrer Sous".
	 */
	private PanneauButton2 monPanneauButton2;
	
	/**
	 * L'écouteur des boutons de la fenêtre
	 */
	private EcouteurJButtonRequete monEcouteur;
	
	/**
	 * Le JScrollPane contenant le champsSaisie
	 */
	private JScrollPane jspan1;
	
	/**
	 * Le JScrollPane contenant la console
	 */
	private JScrollPane jspan2;
	
	/**
	 * La FenetrePrincipale dont dépend cette fenêtre.
	 */
	private FenetrePrincipale fp;

	/**
	 * Le constructeur de la classe. Créée une nouvelle fenêtre avec le titre passé en paramètre, puis mets en place les éléments et définit la taille et la visibilité.
	 * Initialise également son attribut fp avec la FenetrePrincipale passée en paramètre, et son écouteur avec la Connection passée en paramètre.
	 * @param titre le titre de la fenêtre
	 * @param maConnexion la Connection associée à la fenêtre
	 * @param fp la FenetrePrincipale dont dépend cette fenêtre
	 */
	public FenetreRequete(String titre, Connection maConnexion,FenetrePrincipale fp){
		super(titre);
		this.miseEnPlace();
		this.setSize(800,600);
		this.setVisible(true);
		this.fp = fp;
		
		monEcouteur = new EcouteurJButtonRequete(this,maConnexion,fp);

	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		JPanel panPrincipal = new JPanel();
		panPrincipal.setLayout(new GridBagLayout());
		champsSaisie = new JTextPane();
		console = new JTextPane();	
		monPanneauButton2 = new PanneauButton2();

		console.setEditable(false);

		champsSaisie.setPreferredSize(new Dimension(680,200));
		console.setPreferredSize(new Dimension(680,200));


		JScrollPane span1 = new JScrollPane(champsSaisie);
		JScrollPane span2 = new JScrollPane(console);


		//Placement boutons

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor= GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10,10,10,10);
		panPrincipal.add(monPanneauButton2,gbc);

		//Placement TextArea

		gbc.gridx = 0;
		gbc.gridy = 1;

		panPrincipal.add(span1,gbc);

		//Placement TextPane
		gbc.gridx = 0;
		gbc.gridy = 2;

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		panPrincipal.add(span2,gbc);
		this.add(panPrincipal);
	}


	/**
	 * Retourne le PanneauBouton2 contenu dans l'instance
	 * @return le PanneauBouton2 contenu dans l'instance
	 */
	public PanneauButton2 getMonPanneauButton2(){
		return this.monPanneauButton2;
	}
	
	/**
	 * Retourne le premier JTextPane contenu dans l'instance, qui correspond au champ de saisie
	 * @return le premier JTextPane contenu dans l'instance, qui correspond au champ de saisie
	 */
	public JTextPane getChampsSaisie(){
		return this.champsSaisie;
	}
	
	/**
	 * Retourne le second JTextPane contenu dans l'instance, qui correspond à la console
	 * @return le second JTextPane contenu dans l'instance, qui correspond à la console
	 */
	public JTextPane getConsole(){
		return this.console;
	}


}