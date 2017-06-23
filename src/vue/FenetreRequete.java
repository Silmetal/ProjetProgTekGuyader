package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

/**
 * Dans cetet fenêtre, l'utilisateur peut saisir des requêtes SQL de tout type (Sélection, mise à jour ,création etc.) et les exécuter. Si la requête est une sélection, affiche le résultat dans un JTable. 
 * Sinon, affiche le nombre de lignes modifiées ou les erreurs provoquées dans une console.
 * <P>L'utilisateur peut également enegistrer sa saisie dans un fichier SQL à l'endroit qu'il désire, et ouvrir un fichier SQL pour exécuter son contenu. Il peut également ouvrir, modifier puis sauvegarder un fichier SQL.
 */
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
	 * Le JScrollPane contenant le JTable
	 */
	private JScrollPane jspan3;
	
	/**
	 * La FenetrePrincipale dont dépend cette fenêtre.
	 */
	private FenetrePrincipale fp;


	/**
	 * Le modèle utilisé par le JTable pour se créer et se mettre à jour correctement
	 */
	private DefaultTableModel dTM;


	/**
	  * Le Layout permettant de gérer le JTable et le JTextPane
	  */
	private CardLayout card;

	/**
	  * Le container contenant le JTable et le JTextPane
	  */
	private Container cont;


	/**
	  * Le Panel contenant le Container
	  */
	private JPanel panneauDuBas;


	/**
	 * La JTable qui affiche le contenu d'une table
	 */
	private JTable jTable;



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
		

		jspan1 = new JScrollPane(champsSaisie);
		miseEnPlaceDuCardLayout();
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

		panPrincipal.add(jspan1,gbc);

		//Placement TextPane
		gbc.gridx = 0;
		gbc.gridy = 2;

		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		panPrincipal.add(panneauDuBas,gbc);
		this.add(panPrincipal);

	}



	public void miseEnPlaceDuCardLayout(){
		jspan2 = new JScrollPane(console);

		console.setPreferredSize(new Dimension(680,200));
		panneauDuBas = new JPanel();
		card = new CardLayout();
		panneauDuBas.setLayout(card);




		String[] titre={"Test"};
		String[][] data = new String[1][1];
		
		dTM = new DefaultTableModel(data,titre) {

			// Redéfinit la méthode pour rendre le tableau non éditable
			public boolean isCellEditable(int row, int column) {
			   return false;
			}
		};


		jTable = new JTable(dTM);
		jTable.setSize(680,200);

		jspan3 = new JScrollPane(jTable);
		panneauDuBas.setPreferredSize(new Dimension(680,200));

		panneauDuBas.add("console",jspan2);
		panneauDuBas.add("table",jspan3);


	}



	public void editerJTable(ResultSet rs){
		String[] lesVal=null;
		String[] row;
		Object[] res;
		dTM.setColumnCount(0);
		dTM.setRowCount(0);

		try{


			res = Requete.retournerResultSet(rs,false);
			ArrayList<String> lesValeurs = (ArrayList<String>)(res[0]);
			ArrayList<String> lesTitres = (ArrayList<String>)(res[1]);


			
			for (String s : lesTitres) {
				dTM.addColumn(s);
			}

			for(int j=0;j<(lesValeurs.size()/lesTitres.size())+1;j++){
				row = new String[lesTitres.size()];
				for(int i=0;i<lesTitres.size();i++){
					row[i]=lesValeurs.get((j*lesTitres.size())+i);
				}
				dTM.addRow(row);
			}
					
		}
		catch(SQLException se){

			se.printStackTrace();

		}
		catch(Exception e){

		}
		
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


	public JPanel getPanneauDuBas(){
		return this.panneauDuBas;
	}

	public CardLayout getCard(){
		return this.card;
	}


}