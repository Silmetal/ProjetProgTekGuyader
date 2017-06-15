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
	 *
	 */
	private PanneauGauche panneauGauche;
	
	private JButton nouvRequ;
	
	private JButton tuple;
	private JButton trigger;
	private JButton table;
	private JButton vue;
	
	private JPopupMenu tupleMenu;
	private JPopupMenu tableMenu;
	private JPopupMenu triggerMenu;
	private JPopupMenu vueMenu;
	
	private JMenuItem nouvTuple;
	private JMenuItem supprTuple;
	private JMenuItem nouvTable;
	private JMenuItem supprTable;
	private JMenuItem nouvTrigger;
	private JMenuItem supprTrigger;
	private JMenuItem nouvVue;
	private JMenuItem supprVue;
	
	private JTextArea resultat;
	
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public PanneauPrincipal(){
		
		super(new BorderLayout(25, 25));
		panneauGauche = new PanneauGauche();
		miseEnPlace();
		
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans le panneau.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		
		nouvRequ = new JButton("Nouvelle requête");		
		tuple = new JButton("T-uple");		
		trigger = new JButton("Trigger");		
		table = new JButton("Table");		
		vue = new JButton("Vue");

		tupleMenu = new JPopupMenu();
		tableMenu = new JPopupMenu();
		triggerMenu = new JPopupMenu();
		vueMenu = new JPopupMenu();
		
		nouvTable = new JMenuItem("Créer une nouvelle table");
		supprTable = new JMenuItem("Supprimer une table");
		nouvTuple = new JMenuItem("Ajouter un tuple");
		supprTuple = new JMenuItem("Retirer un tuple");
		nouvTrigger = new JMenuItem("Créer un nouveau trigger");
		supprTrigger = new JMenuItem("Supprimer un trigger");
		nouvVue = new JMenuItem("Créer une nouvelle vue");
		supprVue = new JMenuItem("Supprimer une vue");
		
		tableMenu.add(nouvTable);
		tableMenu.add(supprTable);
		
		tupleMenu.add(nouvTuple);
		tupleMenu.add(supprTuple);
		
		triggerMenu.add(nouvTrigger);
		triggerMenu.add(supprTrigger);
		
		vueMenu.add(nouvVue);
		vueMenu.add(supprVue);
		
		resultat = new JTextArea("", 1, 1);
		resultat.setEditable(false);
		resultat.setSize(300,400);
		
		JLabel labelResul = new JLabel("Résultat");
		
		
		// Création des sous-panneaux
		JPanel panneauBoutton = new JPanel(new GridLayout(5, 1, 5, 5));
		JPanel panneauCentral = new JPanel(new BorderLayout(5, 5));
		JPanel panneauLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panneauResultat = new JPanel(new BorderLayout());
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		panneauBoutton.add(nouvRequ);
		panneauBoutton.add(table);
		panneauBoutton.add(tuple);
		panneauBoutton.add(trigger);
		panneauBoutton.add(vue);
		
		panneauLabel.add(labelResul);
		
		panneauResultat.add(resultat, BorderLayout.CENTER);
		panneauResultat.add(panneauLabel, BorderLayout.NORTH);
		
		panneauCentral.add(panneauBoutton, BorderLayout.WEST);
		panneauCentral.add(panneauResultat, BorderLayout.CENTER);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(panneauCentral, BorderLayout.CENTER);
		this.add(panneauGauche, BorderLayout.WEST);
		
		
	}
	
	public JButton getBouttonNouvRequ() {
		
		return this.nouvRequ;
	}
	public JButton getBouttonTable() {
		
		return this.table;
	}
	public JButton getBouttonTrigger() {
		
		return this.trigger;
	}
	public JButton getBouttonTuple() {
		
		return this.tuple;
	}
	public JButton getBouttonVue() {
		
		return this.vue;
	}
}