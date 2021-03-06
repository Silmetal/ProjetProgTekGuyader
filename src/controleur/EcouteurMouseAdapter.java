package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import vue.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.sql.SQLSyntaxErrorException;


/**
 * Cette classe est l'écouteur des boutons et JPopupMenu de FenetrePrincipale. Elle associe les éléments à leurs actions.
 */
public class EcouteurMouseAdapter extends MouseAdapter {
	
	/**
	 * La fenêtre principale à écouter
	 */
	private FenetrePrincipale fp;
	
	/**
	 * L'Utilisateur qui a instancié la fenêtre écoutée par cette instance.
	 */
	private Utilisateur lUtilisateur;

	public EcouteurMouseAdapter(FenetrePrincipale fp, Utilisateur lUtilisateur){
		this.fp=fp;
		this.lUtilisateur=lUtilisateur;
		addListener();

	}
	
	/**
	* Cette méthode associe chaque élément à son action.
	* <P>Le bouton "Nouvelle Connexion" instancie une nouvelle FenetreConnexion
	* <P>Le bouton "Nouvelle Requête" instance une nouvelel FenetreRequete
	* <P>Le bouton "Table" affiche le JPopupMenu menuTable, qui propose "Créer une nouvelle table" et "Supprimer une table"
	* <P>Le bouton "Tuple" affiche le JPopupMenu menuTuple, qui propose "Ajouter un tuple" et "Supprimer un tuple"
	* <P>Le bouton "Trigger" affiche le JPopupMenu menuTrigger, qui propose "Créer un nouveau trigger" et "Supprimer un trigger"
	* <P>Le bouton "Vue" affiche le JPopupMenu menuVue, qui propose "Créer une nouvelle vue" et "Supprimer une vue"
	* <P>Cliquer sur "Créer une nouvelle table" instancie une nouvelle FenetreNouvelleTable et son écouteur.
	* <P>Cliquer sur "Supprimer une table" ouvre un JOptionPane demandant quelle table supprimer, en proposant une liste 
	* déroulante des tables de la base, puis supprime la table choisie par l'utilisateur.
	* <P>Cliquer sur "Créer un nouveau tuple"... TO BE CONTINUED
	* <P>Clique sur "Supprimer un tuple" ouvre un JOptionPane demandant quelle tuple supprimer, en proposant une liste 
	* déroulante des clés primaires des tuples de la table sélectionnée, puis supprime le tuple choisit par l'utilisateur
	* TO BE CONTINUED
	*/
	public void mousePressed(MouseEvent e) {
			
		
			if(e.getComponent() instanceof JButton){
				JButton jb = (JButton) e.getComponent();



				if(jb.getName().equals("connexion")){
					FenetreConnexion fenConnexion = new FenetreConnexion(lUtilisateur,fp);
				}
				else if(jb.getName().equals("requete")){
					fp.getRequeteMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY());
				}
				else if(jb.getName().equals("tuple")){
					fp.getTupleMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-2*(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("trigger")){
					fp.getTriggerMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-3*(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("table")){
					fp.getTableMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("vue")){
					fp.getVueMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-4*(e.getComponent().getHeight()));
				}
				else if(jb.getName().equals("base")){
					fp.getBaseMenu().show(e.getComponent(), e.getComponent().getX()+e.getComponent().getWidth(), e.getComponent().getY()-15-5*(e.getComponent().getHeight()));
				}
			}
			else if(e.getComponent() instanceof JMenuItem){
				try{
					String laTableSelectionee="";
					JMenuItem jmi = (JMenuItem) e.getComponent();
					BaseDeDonnees laBaseSelectionee = lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection());
					laTableSelectionee = lUtilisateur.getTable();
					Requete nouvelleRequete = new Requete(laBaseSelectionee.getConnection(),laBaseSelectionee.getNomDeLaBase(),laTableSelectionee);

					if(jmi.getName().equals("nouvRequete")){
						if(lUtilisateur.getLesBasesDeDonnees().size()>0 && lUtilisateur.getSelection()!=-1){
							FenetreRequete fenRequete = new FenetreRequete("Requete",lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection(),fp);
						}
					}
					else if(jmi.getName().equals("choisirRequete")){
						ArrayList<String> cache = new ArrayList<String>();
						String[] lesCodesValid;
						String[] lesCodes = ModifierString.decomposerLigneParLigne(DiskFileExplorer.listDirectory("../prog"));
						for (int i=0;i<lesCodes.length;i++) {
							lesCodes[i] = ModifierString.supprimerExtrait(lesCodes[i],".sql");
							if(!lesCodes[i].equals("")){
								cache.add(lesCodes[i]);
							}
						}

						lesCodesValid = new String[cache.size()];
						for(int i=0;i<cache.size();i++){
							lesCodesValid[i]=cache.get(i);
						}

						String file = (String) JOptionPane.showInputDialog(fp, 
				        "Quel code voulez vous utilisez?",
				        "Requete",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        lesCodesValid, 
				        lesCodesValid[0]);

						choisirRequete(file,laBaseSelectionee.getConnection());
					}
					else if(jmi.getName().equals("nouvTable")){
						FenetreNouvelleTable fnt = new FenetreNouvelleTable();
						EcouteurFenetreNouvTable efnt = new EcouteurFenetreNouvTable(fnt, nouvelleRequete, this,fp);
					}
					else if(jmi.getName().equals("supprTable")){
						
						supprimerTable(laBaseSelectionee,laTableSelectionee,nouvelleRequete);							
					}
					else if(jmi.getName().equals("nouvTuple")){
						if(lUtilisateur.getSelection()!=-1 && !(lUtilisateur.getTable().equals(""))){
							FenetreNouvTuple fntup = new FenetreNouvTuple(fp);
							EcouteurFenetreNouvTuple efntup = new EcouteurFenetreNouvTuple(fntup, nouvelleRequete, this, fp);
						}
					}
					else if(jmi.getName().equals("modifTuple")){
						
						FenetreModifTable fmt = new FenetreModifTable(fp);
						EcouteurFenetreModifTable efmt = new EcouteurFenetreModifTable(fmt, nouvelleRequete, this, fp);
						
					}
					else if(jmi.getName().equals("supprTuple")){
						
						supprimerTuple(laBaseSelectionee,laTableSelectionee,nouvelleRequete);
						
					}
					else if(jmi.getName().equals("nouvTriggerLigne")){
						FenetreRequeteProg fenetre = new FenetreRequeteProg("Création de trigger de ligne",laBaseSelectionee.getConnection(),fp,RequeteProgramee.requeteCreationTriggerLigne(),false);
					}
					else if(jmi.getName().equals("nouvTriggerTable")){
						FenetreRequeteProg fenetre = new FenetreRequeteProg("Création de trigger de table",laBaseSelectionee.getConnection(),fp,RequeteProgramee.requeteCreationTriggerTable(),false);
					}
					else if(jmi.getName().equals("supprTrigger")){
						nouvelleRequete.manuel("use information_schema;");
						ResultSet rs =(ResultSet) nouvelleRequete.manuel("SELECT TRIGGER_NAME FROM TRIGGERS WHERE TRIGGER_SCHEMA='"+laBaseSelectionee.getNomDeLaBase()+"';")[1];
						Object[] res = nouvelleRequete.retournerResultSet(rs,false);
						ArrayList<String> lesValeurs = (ArrayList<String>)res[0];
						String [] lesValeursTab = new String[lesValeurs.size()];
						for(int i=0;i<lesValeurs.size();i++){
							lesValeursTab[i]=lesValeurs.get(i);
						}

						String leTrig = (String) JOptionPane.showInputDialog(fp, 
				        "Quel Trigger voulez vous supprimer?",
				        "Trigger",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        lesValeursTab, 
				        lesValeursTab[0]);

						nouvelleRequete.manuel("use "+laBaseSelectionee.getNomDeLaBase()+";");
						nouvelleRequete.enleverTrigger(leTrig);
					}
					else if(jmi.getName().equals("nouvVue")){
						FenetreRequeteProg fenetre = new FenetreRequeteProg("Création de vue",laBaseSelectionee.getConnection(),fp,RequeteProgramee.requeteCreationVue(),false);
					}
					else if(jmi.getName().equals("supprVue")){
						nouvelleRequete.manuel("use information_schema;");
						ResultSet rs =(ResultSet) nouvelleRequete.manuel("SELECT TABLE_NAME FROM VIEWS WHERE TABLE_SCHEMA='"+laBaseSelectionee.getNomDeLaBase()+"';")[1];
						Object[] res = nouvelleRequete.retournerResultSet(rs,false);
						ArrayList<String> lesValeurs = (ArrayList<String>)res[0];
						String [] lesValeursTab = new String[lesValeurs.size()];
						for(int i=0;i<lesValeurs.size();i++){
							lesValeursTab[i]=lesValeurs.get(i);
						}

						String laVue = (String) JOptionPane.showInputDialog(fp, 
				        "Quelle vue voulez vous supprimer?",
				        "Vue",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        lesValeursTab, 
				        lesValeursTab[0]);

						nouvelleRequete.manuel("use "+laBaseSelectionee.getNomDeLaBase()+";");
						nouvelleRequete.enleverVue(laVue);
					}
					else if(jmi.getName().equals("nouvelleBase")){
						nouvelleBase(nouvelleRequete,laBaseSelectionee);
					}
					else if (jmi.getName().equals("supprimerBase")) {
					
						String motDePasse="";
						JPanel panel = new JPanel();
						JLabel label = new JLabel("Entrer le mot de passe associé à la base de données : "+laBaseSelectionee.getNomDeLaBase());
						JPasswordField pass = new JPasswordField(10);
						panel.add(label);
						panel.add(pass);
						String[] options = new String[]{"Ok", "Quitter"};
						int option = JOptionPane.showOptionDialog(null, panel, "Mot De Passe",
						                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						                         null, options, options[1]);
						if(option == 0) // pressing OK button
						{
						    char[] password = pass.getPassword();
						    motDePasse = new String(password);
						}

						lUtilisateur.supprimerBaseDeDonnees(motDePasse,nouvelleRequete);


					}
					else if(jmi.getName().equals("lireBase")){

						String file="";
						JFileChooser filechoose = new JFileChooser();
						filechoose.setCurrentDirectory(new File("."));  // ouvrir la boite de dialogue dans répertoire courant 
						filechoose.setDialogTitle("Ouvrir"); // nom de la boite de dialogue 
						 
						 
						String approve = new String("Ouvrir"); // Le bouton pour valider
						int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
						if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){ // Si l’utilisateur clique sur le bouton ouvrir 
						    file = filechoose.getSelectedFile().getAbsolutePath()+"\\"; // pour avoir le chemin absolu  
						}


						if(!file.equals("")){
							nouvelleRequete.creerOuModifier(RWFile.readFile(file));
						}
					}
					else if(jmi.getName().equals("ecrireBase")){
						String cheminSauvegarde="";
						JFileChooser filechoose = new JFileChooser();
						filechoose.setCurrentDirectory(new File("."));  
						filechoose.setDialogTitle("Enregistrer sous"); 
						 
						//filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						 
						String approve = new String("Enregistrer"); 
						int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
						if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
						    cheminSauvegarde = filechoose.getSelectedFile().getAbsolutePath();
						    cheminSauvegarde = cheminSauvegarde +".sql";
						    laBaseSelectionee.ecrire(cheminSauvegarde);
						}
						
					}
					else if(jmi.getName().equals("genererUML")){
						genererUML(laBaseSelectionee);
					}
					else if (jmi.getName().equals("nouvelUtilisateur")) {
						FenetreNouvelUtilisateur fnu = new FenetreNouvelUtilisateur("Création d'un nouvel utilisateur",laBaseSelectionee,laTableSelectionee,fp);
					}
					else if (jmi.getName().equals("supprimerUtilisateur")) {
						nouvelleRequete.manuel("use mysql;");
			
						ResultSet rs =(ResultSet) nouvelleRequete.manuel("SELECT user FROM user;")[1];
						Object[] res = nouvelleRequete.retournerResultSet(rs,false);
						ArrayList<String> lesValeurs = (ArrayList<String>)res[0];
						String [] lesValeursTab = new String[lesValeurs.size()];
						for(int i=0;i<lesValeurs.size();i++){
							lesValeursTab[i]=lesValeurs.get(i);
						}

						String lUtilisateur = (String) JOptionPane.showInputDialog(fp, 
				        "Quelle utilisateur voulez vous supprimer?",
				        "Utilisateur",
				        JOptionPane.QUESTION_MESSAGE, 
				        null, 
				        lesValeursTab, 
				        lesValeursTab[0]);

						nouvelleRequete.manuel("use "+laBaseSelectionee.getNomDeLaBase()+";");



						laBaseSelectionee.supprimerUtilisateur(lUtilisateur);
						
					}





					if(!jmi.getName().equals("nouvTable") && !jmi.getName().equals("nouvRequete") && !jmi.getName().equals("choisirRequete") ){
						fp.getPanneauGauche().constructionJTree();
					}
				}
				catch (ArrayIndexOutOfBoundsException aib){
					JOptionPane jop2 = new JOptionPane();
					jop2.showMessageDialog(null, "Vous n'avez pas selectionné une base de données", "Attention", JOptionPane.WARNING_MESSAGE);
				}
				catch(SQLException se){
					fp.getConsole().setText(se.getMessage());
					
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}

	}



	public void nouvelleTable(Requete nouvelleRequete, EcouteurFenetreNouvTable efnt) throws SQLException, Exception{
		
		String nomTable = efnt.getNomTable();
		ArrayList<Attribut> listeAtt = efnt.getListeAtt();
		nouvelleRequete.ajouterTable(nomTable,listeAtt);		
	}

	public void nouveauTuple(Requete nouvelleRequete, EcouteurFenetreNouvTuple efntup) throws SQLException, Exception{
		nouvelleRequete.creerOuModifier(efntup.getComm());
	}


	public void modifierTuple(Requete nouvelleRequete, EcouteurFenetreModifTable efmt) throws SQLException, Exception{
		nouvelleRequete.creerOuModifier(efmt.getComm());
	}


	public void nouvelleBase(Requete nouvelleRequete,BaseDeDonnees laBaseSelectionee) throws SQLException, Exception{
		String reponse;
		String motDePasse="";
	  	String message = "Nom de la base ?";
	  	reponse = JOptionPane.showInputDialog(null, message);

	  	JPanel panel = new JPanel();
		JLabel label = new JLabel("Entrer le mot de passe associé à la base de données : "+laBaseSelectionee.getNomDeLaBase());
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"Ok", "Quitter"};
		int option = JOptionPane.showOptionDialog(null, panel, "Mot De Passe",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[1]);
		if(option == 0) // pressing OK button
		{
		    char[] password = pass.getPassword();
		    motDePasse = new String(password);
		}


	  	if(!reponse.equals("") && !motDePasse.equals("") ){
	  		lUtilisateur.creerBaseDeDonnees(motDePasse,reponse,nouvelleRequete);
		}
	}

	public void genererUML(BaseDeDonnees laBaseSelectionee) throws SQLException,Exception{
		
		ArrayList<String> lesTables = laBaseSelectionee.parcourirBase();
		ArrayList<String> diagClasse = new ArrayList<String>();
		ArrayList<String> diagRef = new ArrayList<String>();
		String uneClasse="";
		Object[] tab;

		for(String str : lesTables){

			ArrayList<String> lesAttribut = (ArrayList<String>)laBaseSelectionee.parcourirTable(str)[0];
			uneClasse="class "+str+" {";
			for(String s : lesAttribut){
				tab = laBaseSelectionee.recupererInfo(str,s);
				boolean nonNull = (boolean) tab[0];
				boolean unique = (boolean) tab[1];
				String type = (String) tab[2];
				String tableRef = (String) tab[3];
				String attributRef = (String) tab[4];
				boolean clePrim = (boolean) tab[5];
				type = ModifierString.remplacerExtrait(type,"("," ");
				type = ModifierString.supprimerExtrait(type,")");
				uneClasse=uneClasse+"\r\n\t"+s+" : "+type;
				if(clePrim) uneClasse = uneClasse + " [1]";
				else{
					if(nonNull) uneClasse = uneClasse + " NN";
					if(unique) uneClasse = uneClasse +" UQ";
				}
				if(!(tableRef.equals("") && attributRef.equals(""))){
					String lien = genererLien(nonNull,unique,str,s,tableRef,attributRef);
					diagRef.add(lien);
				}
			}
			uneClasse=uneClasse+"\r\n}";
			diagClasse.add(uneClasse);
		}


		RWFile.writeFile("@startuml","../UML/UML.txt");

		for(int i=0;i<diagClasse.size();i++){
			RWFile.writeEndOfFile(diagClasse.get(i),"../UML/UML.txt");
		}

		for (String s : diagRef) {
			RWFile.writeEndOfFile(s,"../UML/UML.txt");
		}

		RWFile.writeEndOfFile("\r\n@enduml","../UML/UML.txt");


		Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","../lib/plantuml.jar","../UML/UML.txt"});

		Thread.sleep(10000);

		AfficherDiagrammeClasse diag = new AfficherDiagrammeClasse();
	}

	private String genererLien(boolean nonNull,boolean unique,String table,String attribut,String tableRef,String attributRef){
		String ret="";
		if(unique && nonNull){
			ret = table+" \"0..1\" -- \"1\" "+tableRef+" : "+attribut;
		}
		else if(unique){
			ret = table+" \"0..1\" -- \"0..1\" "+tableRef+" : "+attribut;
		}
		else if(nonNull){
			ret = table+" \"1\" -- \"*\" "+tableRef+" : "+attribut;
		}
		else{
			ret = table+" \"*\" -- \"0..1\" "+tableRef+" : "+attribut;
		}
		return ret;

	}

	public void supprimerTable(BaseDeDonnees laBaseSelectionee,String laTableSelectionee,Requete nouvelleRequete) throws SQLException,Exception{
		if(lUtilisateur.getSelection()!=-1){
			ArrayList<String> lesTables = laBaseSelectionee.parcourirBase();
			String[] lesTab = new String[lesTables.size()];

			for(int i=0;i<lesTables.size();i++){
				lesTab[i]=lesTables.get(i);
			}

			String table = (String) JOptionPane.showInputDialog(fp, 
		        "Quelle table/vue voulez vous supprimer ?",
		        "Suppression de table/vue",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        lesTab, 
		        lesTab[0]);

			nouvelleRequete.enleverTable(table);
		}
	}

	public void supprimerTuple(BaseDeDonnees laBaseSelectionee,String laTableSelectionee,Requete nouvelleRequete) throws SQLException,Exception{
		int k=0;
		String attribut;
		if(lUtilisateur.getSelection()!=-1 && !(laTableSelectionee.equals(""))){
			ArrayList<String> lesValeurs;
			String[] lesVal=null;
			ArrayList<String> lesAttribut = (ArrayList<String>)laBaseSelectionee.parcourirTable(laTableSelectionee)[0];
			
			attribut=lesAttribut.get(0);
			lesValeurs = laBaseSelectionee.parcourirAttribut(lesAttribut.get(0),laTableSelectionee,"");
			
			
			lesVal = new String[lesValeurs.size()];

			for(int i=0;i<lesValeurs.size();i++){
				lesVal[i]=lesValeurs.get(i);
			}

			String tuple = (String) JOptionPane.showInputDialog(fp, 
		        "Quelle tuple voulez vous supprimer ?",
		        "Suppression de tuple",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        lesVal, 
		        lesVal[0]);
			System.out.println("Attribut "+attribut);
			System.out.println("Tuple "+tuple);
			nouvelleRequete.enleverTuple(tuple,attribut);
		}
	}


	public void choisirRequete(String fichier,Connection laConnexion){
		String adresseFichier = "../prog/" + fichier+".sql";
		if(!fichier.equals("")){
			FenetreRequeteProg fenetre = new FenetreRequeteProg("Requete programmée",laConnexion,fp,RWFile.readFile(adresseFichier),true);
		} 
	}
	

	public void addListener(){
		fp.getBoutonRequete().addMouseListener(this);
		fp.getBoutonTable().addMouseListener(this);
		fp.getBoutonTrigger().addMouseListener(this);
		fp.getBoutonTuple().addMouseListener(this);
		fp.getBoutonVue().addMouseListener(this);
		fp.getBoutonBase().addMouseListener(this);

		fp.getPanneauGauche().getBoutonConnexion().addMouseListener(this);

		fp.getNouvRequete().addMouseListener(this);
		fp.getChoisirRequete().addMouseListener(this);
		fp.getNouvTable().addMouseListener(this);
		fp.getSupprTable().addMouseListener(this);
		fp.getNouvTuple().addMouseListener(this);
		fp.getModifTuple().addMouseListener(this);
		fp.getSupprTuple().addMouseListener(this);
		fp.getNouvTriggerLigne().addMouseListener(this);
		fp.getNouvTriggerTable().addMouseListener(this);
		fp.getSupprTrigger().addMouseListener(this);
		fp.getNouvVue().addMouseListener(this);
		fp.getSupprVue().addMouseListener(this);
		fp.getLireBase().addMouseListener(this);
		fp.getEcrireBase().addMouseListener(this);
		fp.getGenererUML().addMouseListener(this);
		fp.getNouvelleBase().addMouseListener(this);
		fp.getSupprimerBase().addMouseListener(this);
		fp.getNouvelUtilisateur().addMouseListener(this);
		fp.getSupprimerUtilisateur().addMouseListener(this);
	}
}