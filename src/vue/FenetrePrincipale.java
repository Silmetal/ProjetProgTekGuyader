package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;


/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class FenetrePrincipale extends JFrame{
	
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
	
	private JTable jTable;
	
	private JTextArea console;

	private EcouteurMouseAdapter monEcouteur;
	
	private Utilisateur lUtilisateur;

	private DefaultTableModel dTM;
	private String [][] data;

	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public FenetrePrincipale(){
		super("Gestionnaire de base de données");
		lUtilisateur = new Utilisateur("ArthurG");
		this.setLayout(new BorderLayout(25, 25));
		panneauGauche = new PanneauGauche(lUtilisateur,this);
		miseEnPlace();
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans le panneau.
	 */
	private void miseEnPlace(){
		
		// Initialisation des composants
		
		nouvRequ = new JButton("Nouvelle requête");	
		nouvRequ.setName("nouvRequ");	
		tuple = new JButton("T-uple");	
		tuple.setName("tuple");	
		trigger = new JButton("Trigger");	
		trigger.setName("trigger");	
		table = new JButton("Table");	
		table.setName("table");	
		vue = new JButton("Vue");
		vue.setName("vue");

		tupleMenu = new JPopupMenu();

		tableMenu = new JPopupMenu();
		triggerMenu = new JPopupMenu();
		vueMenu = new JPopupMenu();
		
		nouvTable = new JMenuItem("Créer une nouvelle table");
		nouvTable.setName("nouvTable");
		supprTable = new JMenuItem("Supprimer une table");
		supprTable.setName("supprTable");
		nouvTuple = new JMenuItem("Ajouter un tuple");
		nouvTuple.setName("nouvTuple");
		supprTuple = new JMenuItem("Retirer un tuple");
		supprTuple.setName("supprTuple");
		nouvTrigger = new JMenuItem("Créer un nouveau trigger");
		nouvTrigger.setName("nouvTrigger");
		supprTrigger = new JMenuItem("Supprimer un trigger");
		supprTrigger.setName("supprTrigger");
		nouvVue = new JMenuItem("Créer une nouvelle vue");
		nouvVue.setName("nouvVue");
		supprVue = new JMenuItem("Supprimer une vue");
		supprVue.setName("supprVue");
		
		tableMenu.add(nouvTable);
		tableMenu.add(supprTable);
		
		tupleMenu.add(nouvTuple);
		tupleMenu.add(supprTuple);
		
		triggerMenu.add(nouvTrigger);
		triggerMenu.add(supprTrigger);
		
		vueMenu.add(nouvVue);
		vueMenu.add(supprVue);
		



		String[] titre={"Selectionner la table a afficher"};
		data = new String[1][1];
		dTM = new DefaultTableModel(data,titre);
		jTable = new JTable(dTM);
		jTable.setSize(300,400);
		
		JLabel labelConsole = new JLabel("Console");
		
		
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
		
		panneauLabel.add(labelConsole);

		console = new JTextArea();
		console.setEditable(false);

		JScrollPane span2 = new JScrollPane(console);

		panneauResultat.add(span2, BorderLayout.CENTER);
		panneauResultat.add(panneauLabel, BorderLayout.NORTH);
		

		JPanel panneauDroite = new JPanel();
		panneauDroite.setLayout(new GridLayout(2,1));


		JScrollPane span1 = new JScrollPane(jTable);

		panneauDroite.add(span1);
		panneauDroite.add(panneauResultat);

		panneauCentral.add(panneauBoutton, BorderLayout.WEST);
		panneauCentral.add(panneauDroite, BorderLayout.CENTER);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(panneauCentral, BorderLayout.CENTER);
		this.add(panneauGauche, BorderLayout.WEST);


		monEcouteur = new EcouteurMouseAdapter(this,lUtilisateur);
	}


	public void setJTable(BaseDeDonnees bd,String table){
		String[] lesVal=null;
		dTM.setColumnCount(0);
		if(!table.equals("")){


			ArrayList<String> lesAttribut = bd.parcourirTable(table);
			for(String str : lesAttribut){
				ArrayList<String> lesValeurs = bd.parcourirAttribut(str,table);
				lesVal = new String[lesValeurs.size()];

				for(int i=0;i<lesValeurs.size();i++){
	//				System.out.println(lesValeurs.get(i));
					lesVal[i]=lesValeurs.get(i);

				}
				dTM.setRowCount(lesVal.length);
				dTM.addColumn(str,lesVal);

			}		

				
			
			
		}



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

	public JPopupMenu getTupleMenu(){
		return this.tupleMenu;
	}

	public JPopupMenu getTableMenu(){
		return this.tableMenu;
	}

	public JPopupMenu getTriggerMenu(){
		return this.triggerMenu;
	}

	public JPopupMenu getVueMenu(){
		return this.vueMenu;
	}

	public JMenuItem getNouvTable(){
		return this.nouvTable;
	}

	public JMenuItem getSupprTable(){
		return this.supprTable;
	}

	public JMenuItem getNouvTuple(){
		return this.nouvTuple;
	}

	public JMenuItem getSupprTuple(){
		return this.supprTuple;
	}

	public JMenuItem getNouvTrigger(){
		return this.nouvTrigger;
	}

	public JMenuItem getSupprTrigger(){
		return this.supprTrigger;
	}

	public JMenuItem getNouvVue(){
		return this.nouvVue;
	}

	public JMenuItem getSupprVue(){
		return this.supprVue;
	}
	
	public JTable getTable(){
		return this.jTable;
	}
	
	public JTextArea getConsole(){
		return this.console;
	}

	public PanneauGauche getPanneauGauche(){
		return this.panneauGauche;
	}

	public Utilisateur getUtilisateur(){
		return this.lUtilisateur;
	}







}