package controleur;
import utilitaire.*;
import vue.*;
import model.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.sql.*;



public class EcouteurJButtonRequete implements ActionListener {

	private FenetreRequete fr;
	private String cheminSauvegarde;
	private Connection maConnexion;

	public EcouteurJButtonRequete(FenetreRequete fr, Connection maConnexion){
		this.fr=fr;
		this.maConnexion=maConnexion;
		cheminSauvegarde="";
		addListener();

	}
	

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


	public void lancer(){
		try{
			Requete maRequete = new Requete(maConnexion);
			ResultSet rs=null;
			int nb=0;
			Object[] res;
			res=maRequete.manuel(fr.getMonTextPane1().getText());

			rs=(ResultSet)res[1];
			nb=(Integer)res[2];
			System.out.println("ResultSet"+rs);

			if(rs!=null){
				String affichage="";
				ResultSetMetaData rsmd = rs.getMetaData();
			   	int columnsNumber = rsmd.getColumnCount();
			   	while (rs.next()) {
			       for (int i = 1; i <= columnsNumber; i++) {
			           	if (i > 1) affichage=affichage+",  ";
			           	String columnValue = rs.getString(i);
			         	affichage=affichage+columnValue + " " + rsmd.getColumnName(i);
			       }

				}
				fr.getMonTextPane2().setText("Requete : \n"+affichage);

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
		
	}


	public void enregistrer(){
		if(cheminSauvegarde.equals("")){
			enregistrerSous();
		}
		else{
			sauvegarde();
		}
	}

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

	public void sauvegarde(){
		String res = fr.getMonTextPane1().getText();
		RWFile ecriture = new RWFile();
		ecriture.writeFile(res,cheminSauvegarde);
	}


	public void addListener(){
		fr.getMonPanneauButton2().getButtonLanceur().addActionListener(this);
		fr.getMonPanneauButton2().getButtonEnregistrer().addActionListener(this);
		fr.getMonPanneauButton2().getButtonEnregisterSous().addActionListener(this);
		fr.getMonPanneauButton2().getButtonOuvrir().addActionListener(this);
	}

}
