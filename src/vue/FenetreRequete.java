package vue;

import javax.swing.*;
import java.awt.*;


public class FenetreRequete extends JPanel{

	private JTextArea monTextArea;
	private JTextPane monTextPane;
	private PanneauButton2 monPanneauButton2;


	public FenetreRequete(){
		this.setLayout(new GridBagLayout());
		super(new GridLayout(3,1,0,20));
		miseEnPlace();
		
	}
	

	private void miseEnPlace(){
		monTextPane = new JTextPane();
		monTextArea = new JTextArea();
		monPanneauButton2 = new PanneauButton2();

		monTextPane.setEditable(false);


		//Placement boutons




		//Placement TextArea



		//Placement TextPane






		this.add(monPanneauButton2);
		this.add(monTextArea);
		this.add(monTextPane);
	}



	public PanneauButton2 getMonPanneauButton2(){
		return this.monPanneauButton2;
	}

	public JTextArea getMonTextArea(){
		return this.monTextArea;
	}

	public JTextPane getMonTextPane(){
		return this.monTextPane;
	}
	
}