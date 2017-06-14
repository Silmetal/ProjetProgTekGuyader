package vue;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class PanneauPrincipal extends JPanel{
	/**
	 * Le bouton de connexion
	 */
	private JButton connexion;
	
	/**
	 * Les intitutlés des différents champs de saisie
	 */
	private JLabel nomUtili, mdp, adresse;
	
	private JTree arborescence;
	

	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public PanneauPrincipal(){
		
		super(new BorderLayout());
		miseEnPlace();
		
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans le panneau.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		arborescence = new JTree();
		
		// Création des sous-panneaux
		JPanel arbre = new JPanel();
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		arbre.add(arborescence);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(arbre, BorderLayout.WEST);
		
		
	}
	
}