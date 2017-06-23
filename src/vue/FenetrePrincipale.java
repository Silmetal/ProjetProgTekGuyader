package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;


/**
 * Cette classe est la classe d'IHM de la fenêtre permettant la connexion à la base de donnée. Trois champs de texte sont présents, demandant
 * à l'utilsiateur son identifiant, son mot de passe et l'adresse de la base. Une fois ces champs remplis, l'utilsateur n'a qu'à cliquer sur le bouton de connexion pour
 * se connecter à la base, si bien sûr aucune erreur de connexion ne survient.
 * <P>Le champs de saisie du mot de passe cache les caractères saisis.
 */
public class FenetrePrincipale extends JFrame{
	
	/**
	 * Le panneau qui constitue la partie gauche la fenêtre principale. Contient le JTree et le bouton de connexion
	 */
	private PanneauGauche panneauGauche;
	
	/**
	 * Le bouton permettant à l'utilisateur d'écrire une requête. Ouvre une nouvelle FenetreRequete.
	 */
	private JButton nouvRequ;
	
	/**
	 * Le bouton qui ouvre le menu permettant de créer ou supprimer un tuple
	 */
	private JButton tuple;
	
	/**
	 * Le bouton qui ouvre le menu permettant de créer ou supprimer un trigger
	 */
	private JButton trigger;
	
	/**
	 * Le bouton qui ouvre le menu permettant de créer ou supprimer une table
	 */
	private JButton table;
	
	/**
	 * Le bouton qui ouvre le menu permettant de créer ou supprimer une vue
	 */
	private JButton vue;
	
	/**
	 * Le menu proposant de créer ou supprimer un tuple
	 */
	private JPopupMenu tupleMenu;
	
	/**
	 * Le menu proposant de créer ou supprimer une table
	 */
	private JPopupMenu tableMenu;
	
	/**
	 * Le menu proposant de créer ou supprimer un trigger
	 */
	private JPopupMenu triggerMenu;
	
	/**
	 * Le menu proposant de créer ou supprimer une vue
	 */
	private JPopupMenu vueMenu;
	
	/**
	 * L'élément de tupleMenu permettant de créer un nouveau tuple
	 */
	private JMenuItem nouvTuple;
	
	/**
	 * L'élément de tupleMenu permettant de modifier les tuples d'une table
	 */
	private JMenuItem modifTuple;
	
	/**
	 * L'élément de tupleMenu permettant de supprimer un tuple
	 */
	private JMenuItem supprTuple;
	
	/**
	 * L'élément de tableMenu permettant de créer une nouvelle table
	 */
	private JMenuItem nouvTable;
	
	/**
	 * L'élément de tableMenu permettant de supprimer une table
	 */
	private JMenuItem supprTable;
	
	/**
	 * L'élément de triggerMenu permettant de créer un nouveau trigger
	 */
	private JMenuItem nouvTrigger;
	
	/**
	 * L'élément de triggerMenu permettant de supprimer un trigger
	 */
	private JMenuItem supprTrigger;
	
	/**
	 * L'élément de vueMenu permettant de créer une nouvelle vue
	 */
	private JMenuItem nouvVue;
	
	/**
	 * L'élément de vueMenu permettant de supprimer une vue
	 */
	private JMenuItem supprVue;
	
	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;
	
	/**
	 * La console qui affiche le résultat d'une requête ainsi que les erreurs qui se sont produites lors de l'exécution d'une commande SQL
	 */
	private JTextArea console;
	
	/**
	 * L'écouteur permettant aux boutons et menus de faire ce qu'ils sont censés faire.
	 */
	private EcouteurMouseAdapter monEcouteur;
	
	
	/**
	 * L'utilisateur qui a instancié cette fenêtre
	 */
	private Utilisateur lUtilisateur;

	/**
	 * Le modèle utilisé par le JTable pour se créer et se mettre à jour correctement
	 */
	private DefaultTableModel dTM;
	
	/**
	 * Le constructeur de la classe. Créé le panneau avec le constructeur de sa super-classe JPanel et lui applique un BorderLayout. Appelle ensuite sa méthode miseEnPlace() pour générer les éléments
	 * et les placer dans le panneau.
	 */
	public FenetrePrincipale(){
		super("Gestionnaire de base de données");
		lUtilisateur = new Utilisateur("ArthurG");
		this.setLayout(new BorderLayout(25, 25));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panneauGauche = new PanneauGauche(lUtilisateur,this);
		miseEnPlace();
		this.setSize(1200,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setVisible(true);
	}
	
	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
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
		supprTable = new JMenuItem("Modifier des tuples");
		nouvTuple.setName("modifTuple");
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
		tupleMenu.add(modifTuple);
		tupleMenu.add(supprTuple);
		
		triggerMenu.add(nouvTrigger);
		triggerMenu.add(supprTrigger);
		
		vueMenu.add(nouvVue);
		vueMenu.add(supprVue);
		



		String[] titre={"Selectionner la table a afficher"};
		String[][] data = new String[1][1];
		
		dTM = new DefaultTableModel(data,titre) {

			// Redéfinit la méthode pour rendre le tableau non éditable
			public boolean isCellEditable(int row, int column) {
			   return false;
			}
		};
		
		jTable = new JTable(dTM);
		jTable.setSize(300,400);
		
		JLabel labelConsole = new JLabel("Console");
		
		
		// Création des sous-panneaux
		JPanel panneauBouton = new JPanel(new GridLayout(5, 1, 5, 5));
		JPanel panneauCentral = new JPanel(new BorderLayout(10,10));
		JPanel panneauLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panneauResultat = new JPanel(new BorderLayout());
		
		// Ajout des composants dans leurs sous-panneaux respectifs
		panneauBouton.add(nouvRequ);
		panneauBouton.add(table);
		panneauBouton.add(tuple);
		panneauBouton.add(trigger);
		panneauBouton.add(vue);
		
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

		panneauCentral.add(panneauBouton, BorderLayout.WEST);
		panneauCentral.add(panneauDroite, BorderLayout.CENTER);
		
		// Ajout des sous-panneaux dans le panneau de connexion
		this.add(panneauCentral, BorderLayout.CENTER);
		this.add(panneauGauche, BorderLayout.WEST);


		monEcouteur = new EcouteurMouseAdapter(this,lUtilisateur);
	}

	/**
	 * Cette méthode modifie le JTable pour afficher le contenu de la table selectionnée. Cette méthode est appelée à chaque fois que l'on clique sur un élément du JTree
	 * @param bd la BaseDeDonnees contenant la table sélectionnée
	 * @param table la table à afficher
	 */
	public void setJTable(BaseDeDonnees bd,String table){
		String[] lesVal=null;
		ArrayList<String> lesValeurs;
		String tablePrimaire="";
		dTM.setColumnCount(0);
		if(!table.equals("")){

			try{
				Object[] lesAtt = bd.parcourirTable(table);

				
				ArrayList<String> valeur = (ArrayList<String>)(lesAtt[0]);
				ArrayList<String> titre =(ArrayList<String>)(lesAtt[1]);



				for(int j=0; j<valeur.size();j++){
					if(j==0){
						lesValeurs = bd.parcourirAttribut(valeur.get(0),table,"");
						tablePrimaire=valeur.get(0);
					}
					else{
						lesValeurs = bd.parcourirAttribut(valeur.get(j),table,tablePrimaire);
					}

					lesVal = new String[lesValeurs.size()];

					for(int i=0;i<lesValeurs.size();i++){
						lesVal[i]=lesValeurs.get(i);
					}
					dTM.setRowCount(lesVal.length);
					dTM.addColumn(titre.get(j),lesVal);
				}		
			}
			catch(SQLException se){

				se.printStackTrace();

			}
			catch(Exception e){

			}
		}
	}



	/**
	 * Retourne le bouton Nouvelle Requête de l'instance (nouvRequ)
	 * @return le bouton Nouvelle Requête de l'instance (nouvRequ)
	 */
	public JButton getBoutonNouvRequ() {
		
		return this.nouvRequ;
	}
	
	/**
	 * Retourne le bouton Table de l'instance
	 * @return le bouton Table de l'instance
	 */
	public JButton getBoutonTable() {
		
		return this.table;
	}
	
	/**
	 * Retourne le bouton Trigger de l'instance
	 * @return le bouton Trigger de l'instance
	 */
	public JButton getBoutonTrigger() {
		
		return this.trigger;
	}
	
	/**
	 * Retourne le bouton Tuple de l'instance
	 * @return le bouton Tuple de l'instance
	 */
	public JButton getBoutonTuple() {
		
		return this.tuple;
	}
	
	/**
	 * Retourne le bouton Vue de l'instance
	 * @return le bouton Vue de l'instance
	 */
	public JButton getBoutonVue() {
		
		return this.vue;
	}
	
	/**
	 * Retourne le JPopupMenu associé au bouton Tuple de l'instance
	 * @return le JPopupMenu associé au bouton Tuple de l'instance
	 */
	public JPopupMenu getTupleMenu(){
		return this.tupleMenu;
	}
	
	/**
	 * Retourne le JPopupMenu associé au bouton Table de l'instance
	 * @return le JPopupMenu associé au bouton Table de l'instance
	 */
	public JPopupMenu getTableMenu(){
		return this.tableMenu;
	}
	
	/**
	 * Retourne le JPopupMenu associé au bouton Trigger de l'instance
	 * @return le JPopupMenu associé au bouton Trigger de l'instance
	 */
	public JPopupMenu getTriggerMenu(){
		return this.triggerMenu;
	}

	/**
	 * Retourne le JPopupMenu associé au bouton Vue de l'instance
	 * @return le JPopupMenu associé au bouton Vue de l'instance
	 */
	public JPopupMenu getVueMenu(){
		return this.vueMenu;
	}
	
	/**
	 * Retourne le JMenuItem Nouvelle Table de l'instance
	 * @return le JMenuItem Nouvelle Table de l'instance
	 */
	public JMenuItem getNouvTable(){
		return this.nouvTable;
	}
	
	/**
	 * Retourne le JMenuItem Supprimer Table de l'instance
	 * @return le JMenuItem Supprimer Table de l'instance
	 */
	public JMenuItem getSupprTable(){
		return this.supprTable;
	}
	
	/**
	 * Retourne le JMenuItem Nouveau Tuple de l'instance
	 * @return le JMenuItem Nouveau Tuple de l'instance
	 */
	public JMenuItem getNouvTuple(){
		return this.nouvTuple;
	}
	
	/**
	 * Retourne le JMenuItem Modifier des tuples de l'instance
	 * @return le JMenuItem Modifier des tuples de l'instance
	 */
	public JMenuItem getModifTuple(){
		return this.modifTuple;
	}

	/**
	 * Retourne le JMenuItem Supprimer Tuple de l'instance
	 * @return le JMenuItem Supprimer Tuple de l'instance
	 */
	public JMenuItem getSupprTuple(){
		return this.supprTuple;
	}
	
	/**
	 * Retourne le JMenuItem Nouveau Trigger de l'instance
	 * @return le JMenuItem Nouveau Trigger de l'instance
	 */
	public JMenuItem getNouvTrigger(){
		return this.nouvTrigger;
	}

	/**
	 * Retourne le JMenuItem Supprimer Trigger de l'instance
	 * @return le JMenuItem Supprimer Trigger de l'instance
	 */
	public JMenuItem getSupprTrigger(){
		return this.supprTrigger;
	}

	/**
	 * Retourne le JMenuItem Nouvelle Vue de l'instance
	 * @return le JMenuItem Nouvelle Vue de l'instance
	 */
	public JMenuItem getNouvVue(){
		return this.nouvVue;
	}

	/**
	 * Retourne le JMenuItem Supprimer Vue de l'instance
	 * @return le JMenuItem Supprimer Vue de l'instance
	 */
	public JMenuItem getSupprVue(){
		return this.supprVue;
	}
	
	/**
	 * Retourne le JTable de l'instance
	 * @return le JTable de l'instance
	 */
	public JTable getTable(){
		return this.jTable;
	}
	
	/**
	 * Retourne le JTextArea correspondant à la console
	 * @return le JTextArea correspondant à la console
	 */
	public JTextArea getConsole(){
		return this.console;
	}
	
	/**
	 * Retourne le PanneauGauche contenu dans l'instance
	 * @return le PanneauGauche contenu dans l'instance
	 */
	public PanneauGauche getPanneauGauche(){
		return this.panneauGauche;
	}
	
	/**
	 * Retourne l'utilisateur associé à l'instance
	 * @return l'utilisateur associé à l'instance
	 */
	public Utilisateur getUtilisateur(){
		return this.lUtilisateur;
	}
}