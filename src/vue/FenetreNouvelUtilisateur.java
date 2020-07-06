package vue;
import controleur.*;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.*;

/**
 * Dans cette fenêtre, l'utilisateur définit les droits, le nom du nouvel l'utilisateur. 
 */
public class FenetreNouvelUtilisateur extends JFrame{
	
	private FenetrePrincipale fp;

	private Connection laConnexion;

	private JRadioButton jrb1;

	private JRadioButton jrb2;

	private JTextField jtf1;

	private JPasswordField jpf2;

	private JButton jb;

	
	public FenetreNouvelUtilisateur(String titre, BaseDeDonnees bd,String table,FenetrePrincipale fp){
		super(titre);
		this.miseEnPlace();
		this.setSize(300,200);
		this.setVisible(true);
		this.fp = fp;
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		EcouteurFenetreUtilisateur ecouteur = new EcouteurFenetreUtilisateur(this,bd,table);
	}


	/**
	 * Génère les éléments graphiques et les dispose dans la fenêtre.
	 */
	private void miseEnPlace(){
		JPanel panPrincipal = new JPanel();
		ButtonGroup bg = new ButtonGroup();

		jrb1 = new JRadioButton("Super Utilisateur",false);
		jrb2 = new JRadioButton("Utilisateur",false);

		bg.add(jrb1);
		bg.add(jrb2);

		jtf1 = new JTextField();

		jpf2 = new JPasswordField();

		jb = new JButton("Valider");

		this.setLayout(new GridLayout(4,0));

		this.add(jtf1);
		this.add(jpf2);

		JPanel panBut = new JPanel();
		panBut.setLayout(new GridLayout(0,2));
		JPanel panbut1 = new JPanel();
		panbut1.setLayout(new BorderLayout());
		panbut1.add(jrb1,BorderLayout.WEST);
		
		JPanel panbut2 = new JPanel();
		panbut2.setLayout(new BorderLayout());
		panbut2.add(jrb2,BorderLayout.EAST);
		panBut.add(panbut1);
		panBut.add(panbut2);
		this.add(panBut);
		this.add(jb);

	}


	public JRadioButton getJrb1() {
		return jrb1;
	}

	public JRadioButton getJrb2() {
		return jrb2;
	}

	public JTextField getJtf1() {
		return jtf1;
	}

	public JPasswordField getJpf2() {
		return jpf2;
	}

	public JButton getJb() {
		return jb;
	}

	


}