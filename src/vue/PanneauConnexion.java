package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class PanneauConnexion extends JPanel{
	/**
	 * Le bouton de connexion
	 */
	private JButton connexion;
	
	/**
	 * Les intitutlés des différents champs de saisie
	 */
	private JLabel nomUtili, mdp, adresse;
	
	/**
	 * Les champs de saisie de l'identifiant et de l'adresse
	 */
	private JTextField nomUtiliTF, adresseTF;
	
	/**
	 * Le champ de saisie du mot de passe. Les caractères saisis sont remplacés par le symbole &bull;
	 */
	private JPasswordField mdpPF;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public PanneauConnexion(){
		
		super(new BorderLayout());
		miseEnPlace();
		
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans le panneau.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		connexion = new JButton("Connexion");
		nomUtili = new JLabel("Nom d'utilisateur : ");
		adresse = new JLabel("Adresse : ");
		mdp = new JLabel("Mot de passe : ");
		nomUtiliTF = new JTextField();
		adresseTF = new JTextField();
		mdpPF = new JPasswordField();
		
		// Création des sous-panneaux
		JPanel panneauBoutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panneauChamps = new JPanel(new GridLayout(3,2));
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		panneauBoutton.add(connexion);
		panneauChamps.add(nomUtili);
		panneauChamps.add(nomUtiliTF);
		panneauChamps.add(mdp);
		panneauChamps.add(mdpPF);
		panneauChamps.add(adresse);
		panneauChamps.add(adresseTF);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(panneauChamps, BorderLayout.CENTER);
		this.add(panneauBoutton, BorderLayout.SOUTH);
		
		
	}
	
}