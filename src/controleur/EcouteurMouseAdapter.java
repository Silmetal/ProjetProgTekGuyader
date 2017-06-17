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




public class EcouteurMouseAdapter extends MouseAdapter {

	private FenetrePrincipale fp;
	private Utilisateur lUtilisateur;

	public EcouteurMouseAdapter(FenetrePrincipale fp, Utilisateur lUtilisateur){
		this.fp=fp;
		this.lUtilisateur=lUtilisateur;
		addListener();

	}
	

	public void mousePressed(MouseEvent e) {
			
		
			if(e.getComponent() instanceof JButton){
				System.out.println("jb");
				JButton jb = (JButton) e.getComponent();



				if(jb.getName().equals("connexion")){
					FenetreConnexion fenConnexion = new FenetreConnexion(lUtilisateur,fp);
				}
				else if(jb.getName().equals("nouvRequ")){
					if(lUtilisateur.getLesBasesDeDonnees().size()>0 && lUtilisateur.getSelection()!=-1){
						FenetreRequete fenRequete = new FenetreRequete("Requete",lUtilisateur.getLesBasesDeDonnees().get(lUtilisateur.getSelection()).getConnection());
					}else{

					}
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
			}
			else if(e.getComponent() instanceof JMenuItem){
				
				JMenuItem jmi = (JMenuItem) e.getComponent();

				if(jmi.getName().equals("nouvTable")){
					System.out.println("mi");
				}
				else if(jmi.getName().equals("supprTable")){
					
				}
				else if(jmi.getName().equals("nouvTuple")){
					
				}
				else if(jmi.getName().equals("supprTuple")){
					
				}
				else if(jmi.getName().equals("nouvTrigger")){
					
				}
				else if(jmi.getName().equals("supprTrigger")){
					
				}
				else if(jmi.getName().equals("nouvVue")){
					
				}
				else if(jmi.getName().equals("supprVue")){
					
				}
		
			}













	}


	

	public void addListener(){
		fp.getBouttonNouvRequ().addMouseListener(this);
		fp.getBouttonTable().addMouseListener(this);
		fp.getBouttonTrigger().addMouseListener(this);
		fp.getBouttonTuple().addMouseListener(this);
		fp.getBouttonVue().addMouseListener(this);

		fp.getPanneauGauche().getBouttonConnexion().addMouseListener(this);

		fp.getNouvTable().addMouseListener(this);
		fp.getSupprTable().addMouseListener(this);
		fp.getNouvTuple().addMouseListener(this);
		fp.getSupprTuple().addMouseListener(this);
		fp.getNouvTrigger().addMouseListener(this);
		fp.getSupprTrigger().addMouseListener(this);
		fp.getNouvVue().addMouseListener(this);
		fp.getSupprVue().addMouseListener(this);
	}

}
