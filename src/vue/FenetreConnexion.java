package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.*;
import controleur.*;
import java.sql.*;
import java.lang.*;

/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class FenetreConnexion extends JFrame{
	/**
	 * Le bouton de connexion
	 */
	private JButton connexion;
	
	/**
	 * Les intitutlés des différents champs de saisie
	 */
	private JLabel nomUtili, mdp, adresse, nomDeLaBase;
	
	/**
	 * Les champs de saisie de l'identifiant et de l'adresse
	 */
	private JTextField nomUtiliTF, adresseTF, nomDeLaBaseTF;
	
	/**
	 * Le champ de saisie du mot de passe. Les caractères saisis sont remplacés par le symbole &bull;
	 */
	private JPasswordField mdpPF;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */

	private Utilisateur lUtilisateur;

	private FenetrePrincipale fp;

	public FenetreConnexion(Utilisateur lUtilisateur,FenetrePrincipale fp){
		super("Connexion");
		this.lUtilisateur=lUtilisateur;
		this.fp = fp;
		this.setLayout(new BorderLayout());
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		miseEnPlace();
		this.setVisible(true);
		this.setSize(500,300);
	}
	
	public FenetreConnexion(Utilisateur lUtilisateur,FenetrePrincipale fp, int l3){
		super("Connexion");
		this.lUtilisateur=lUtilisateur;
		this.fp = fp;
		this.setLayout(new BorderLayout());
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		miseEnPlace();
		this.nomUtiliTF.setText("admin");
		this.mdpPF.setText("iutvannes");
		this.adresseTF.setText("jdbc:mysql://vps.arthurguyader.fr:3306/");
		this.setVisible(true);
		this.setSize(500,300);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		connexion = new JButton("Connexion");
		nomUtili = new JLabel("Nom d'utilisateur : ");
		adresse = new JLabel("Adresse : ");
		mdp = new JLabel("Mot de passe : ");
		nomDeLaBase = new JLabel("Nom de la base : ");
		nomUtiliTF = new JTextField();
		adresseTF = new JTextField();
		mdpPF = new JPasswordField();
		nomDeLaBaseTF = new JTextField();
		
		// Création des sous-panneaux
		JPanel panneauBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panneauChamps = new JPanel(new GridLayout(4,2));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		panneauBouton.add(connexion);
		panneauChamps.add(nomUtili);
		panneauChamps.add(nomUtiliTF);
		panneauChamps.add(mdp);
		panneauChamps.add(mdpPF);
		panneauChamps.add(adresse);
		panneauChamps.add(adresseTF);
		panneauChamps.add(nomDeLaBase);
		panneauChamps.add(nomDeLaBaseTF);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(panneauChamps, BorderLayout.CENTER);
		this.add(panneauBouton, BorderLayout.SOUTH);
		
		connexion.addActionListener(new EcouteurFenetreConnexion(this));
	}
	
	/**
	 * Retourne le bouton Connexion de l'instance
	 * @return le bouton Connexion de l'instance
	 */
	public JButton getBoutonConnexion(){
		return this.connexion;
	}
	
	/**
	 * Retourne le champs de saisie de nom d'utilisateur de l'instance (nomUtiliTF)
	 * @return le champs de saisie de nom d'utilisateur de l'instance (nomUtiliTF)
	 */
	public JTextField getNomUtiliTF(){
		return this.nomUtiliTF;
	}
	
	/**
	 * Retourne le champs de saisie d'adresse de l'instance (adresseTF)
	 * @return le champs de saisie d'adresse de l'instance (adresseTF)
	 */
	public JTextField getAdresseTF(){
		return this.adresseTF;
	}
	
	/**
	 * Retourne le champs de saisie de nom de la base de l'instance (nomDeLaBaseTF)
	 * @return le champs de saisie de nom de la base de l'instance (nomDeLaBaseTF)
	 */
	public JTextField getNomDeLaBaseTF(){
		return this.nomDeLaBaseTF;
	}
	
	/**
	 * Retourne le champs de saisie de mot de passe de l'instance (mdpPF)
	 * @return le champs de saisie de mot de passe de l'instance (mdpPF)
	 */
	public JPasswordField getMdpPF(){
		return this.mdpPF;
	}
	
	/**
	 * Retourne l'Utilisateur associé à l'instance
	 * @return l'Utilisateur associé à l'instance
	 */
	public Utilisateur getUtilisateur(){
		return this.lUtilisateur;
	}
	
	/**
	 * Retourne la FenetrePrincipale associée à l'instance
	 * @return la FenetrePrincipale associée à l'instance
	 */
	public FenetrePrincipale getFP(){
		return this.fp;
	}
}