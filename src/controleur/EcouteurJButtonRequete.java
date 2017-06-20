package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;

/**
* Cette classe est l'écouteur des boutons de la classe FenetreRequete. Elle associe à chaque bouton l'action qu'il est censé déclencher.
*/
public class EcouteurJButtonRequete implements ActionListener {
	
	/**
	 * La FenetreRequete à écouter
	 */
	private FenetreRequete fr;
	
	/**
	 * Le chemin de sauvegarde des fichiers
	 */
	private String cheminSauvegarde;
	
	/**
	 * La Connection associée 
	 */
	private Connection maConnexion;
	
	/** 
	 * La FenetrePrincipale dont dépend la FenetreRequete
	 */
	private FenetrePrincipale fp;
	
	/**
	 * Le constructeur de la classe. Prend en paramètre une FenetreRequete, une Connection et une FenetrePrincipale et les associe à ses 
	 * attributs, puis ajoute l'écouteur à la FenetreRequete.
	 * @param fr la FenetreRequete à écouter
	 * @param maConnexion la Connection associée
	 * @param fp la FenetrePrincipale dont dépend la FenetreRequete
	 */
	public EcouteurJButtonRequete(FenetreRequete fr, Connection maConnexion,FenetrePrincipale fp){
		this.fr=fr;
		this.maConnexion=maConnexion;
		this.fp = fp;
		cheminSauvegarde="";
		addListener();
	}
	
	/**
	 * La méthode qui associe chaque bouton à son action.
	 * <P>Si le bouton est le bouton "Lancer", exécute la méthode lancer()
	 * <P>Si le bouton est le bouton "Enregistrer", exécute la méthode enregistrer()
	 * <P>Si le bouton est le bouton "Enregistrer Sous", exécute la méthode enregistrerSous()
	 * <P>Si le bouton est le bouton "Ouvrir", exécute la méthode ouvrir()
	 */
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();

		if(jb.getName().equals("lancer")){
			this.lancer();
		}
		else if(jb.getName().equals("enregistrer")){
			this.enregistrer();
		}
		else if(jb.getName().equals("enregistrerSous")){
			this.enregistrerSous();
		}
		else if(jb.getName().equals("ouvrir")){
			this.ouvrir();
		}

	}

	/**
	 * Créé un nouvel objet Requete prenant en paramètre l'attribut maConnexion de l'instance et une chaîne de caractère vide, puis
	 * exécute la requête écrite par l'utilisateur dans le champ prévu à cet effet dans la FenetreRequete.
	 * Récupère le résultat dans un tableau d'objet.
	 * <P>Si la requête a renvoyé un ResultSet, affiche ce résultat dans la console.
	 * <P>Sinon, affiche le nombre de lignes modifiées par la requête.
	 * <P> Si une erreur, SQL ou autre, est attrappée, affiche le message de cette erreur dans la console en précisant son type.
	 */
	public void lancer(){
		try{
			Requete maRequete = new Requete(maConnexion,"");
			ResultSet rs=null;
			int nb=0;
			Object[] res;
			res=maRequete.manuel(fr.getMonTextPane1().getText());

			rs=(ResultSet)res[1];
			nb=(Integer)res[2];
			System.out.println("ResultSet"+rs);

			if(rs!=null){
				String affichage="";
				affichage = maRequete.retournerResultSet(rs,true);
				fr.getMonTextPane2().setText("Requete : \n\n"+affichage);

			}
			else{
				fr.getMonTextPane2().setText("Nombre de lignes modifiées : "+nb);
			}
		}
		catch(SQLException se){
			System.out.println("sqlerreur");
			fr.getMonTextPane2().setText(se.getMessage());
		}
		catch(Exception e){
			System.out.println("erreur");
			e.printStackTrace();
			fr.getMonTextPane2().setText(e.getMessage());
		}

		fp.getPanneauGauche().constructionJTree();
		
	}

	/**
	 * Vérifie si un chemin de sauvegarde a déjà été initialisé.
	 * <P>Si c'est le cas, exécute la méthode sauvegarde() pour sauvegarder le contenu du champs de saisie dans un fichier sql
	 * <P>Sinon, exécute la méthode enregistrerSous() pour en définir un puis enregistrer le contenu dans un fichier sql
	 */
	public void enregistrer(){
		if(cheminSauvegarde.equals("")){
			enregistrerSous();
		}
		else{
			sauvegarde();
		}
	}
	
	/**
	 * Ouvre une fenêtre permettant à l'utilsiateur de choisir l'emplacement du fichier à créer, puis créé un nouveau fichier sql à l'emplacement indiqué contenant
	 * la saisie de l'utilisateur dans le champs de saisie
	 */
	public void enregistrerSous(){
		JFileChooser filechoose = new JFileChooser();
		filechoose.setCurrentDirectory(new File("."));  
		filechoose.setDialogTitle("Enregistrer sous"); 
		 
		//filechoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 
		String approve = new String("Enregistrer"); 
		int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){
		    cheminSauvegarde = filechoose.getSelectedFile().getAbsolutePath();
		    cheminSauvegarde = cheminSauvegarde +".sql";
		    sauvegarde();
		}
	}
	
	/**
	 * Ouvre une fenêtre permettant à l'utilsiateur de choisir l'emplacement du fichier à créer, puis créé un nouveau fichier sql à l'emplacement indiqué contenant
	 * la saisie de l'utilisateur dans le champs de saisie
	 */
	public void ouvrir(){
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
			RWFile lecture = new RWFile();
			String res = lecture.readFile(file);
			fr.getMonTextPane1().setText(res);
		}
	}
	
	/**
	 * Sauvegarde la saisie de l'utilisateur dans le fichier dont le chemin est contenu dans l'attribut cheminSauvegarde de l'instance.
	 */
	public void sauvegarde(){
		String res = fr.getMonTextPane1().getText();
		RWFile ecriture = new RWFile();
		ecriture.writeFile(res,cheminSauvegarde);
	}

	/**
	 * Ajoute l'écouteur aux boutons de la FenetreRequete associée.
	 */
	public void addListener(){
		fr.getMonPanneauButton2().getButtonLanceur().addActionListener(this);
		fr.getMonPanneauButton2().getButtonEnregistrer().addActionListener(this);
		fr.getMonPanneauButton2().getButtonEnregisterSous().addActionListener(this);
		fr.getMonPanneauButton2().getButtonOuvrir().addActionListener(this);
	}

}
