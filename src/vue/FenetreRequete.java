package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class FenetreRequete extends JFrame{

	private JTextPane monTextPane1;
	private JTextPane monTextPane2;
	private PanneauButton2 monPanneauButton2;
	private EcouteurJButtonRequete monEcouteur;
	private JScrollPane jspan1;
	private JScrollPane jspan2;
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
		monTextPane1 = new JTextPane();
		monTextPane2 = new JTextPane();	
		monPanneauButton2 = new PanneauButton2();

		monTextPane2.setEditable(false);

		monTextPane1.setPreferredSize(new Dimension(680,200));
		monTextPane2.setPreferredSize(new Dimension(680,200));


		JScrollPane span1 = new JScrollPane(monTextPane1);
		JScrollPane span2 = new JScrollPane(monTextPane2);


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
	public JTextPane getMonTextPane1(){
		return this.monTextPane1;
	}
	
	/**
	 * Retourne le second JTextPane contenu dans l'instance, qui correspond à la console
	 * @return le second JTextPane contenu dans l'instance, qui correspond à la console
	 */
	public JTextPane getMonTextPane2(){
		return this.monTextPane2;
	}


}