package vue;

import javax.swing.*;
import java.awt.*;


public class FenetreRequete extends JPanel{

	private JTextArea monTextArea;
	private JTextPane monTextPane;
	private PanneauButton2 monPanneauButton2;


	public FenetreRequete(){
		this.setLayout(new GridBagLayout());
		miseEnPlace();
		
	}
	

	private void miseEnPlace(){
		monTextPane = new JTextPane();
		monTextArea = new JTextArea();
		monPanneauButton2 = new PanneauButton2();

		monTextPane.setEditable(false);


		//Placement boutons

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor= GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10,10,10,10);
		this.add(monPanneauButton2,gbc);

		//Placement TextArea

		gbc.gridx = 0;
		gbc.gridy = 1;

		gbc.weightx = 1.;
		gbc.weighty = 1.;

		this.add(monTextArea,gbc);

		//Placement TextPane
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(monTextPane,gbc);
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