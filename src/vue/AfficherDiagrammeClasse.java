package vue;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
 
  
 
public class AfficherDiagrammeClasse extends JFrame {
 	

	public AfficherDiagrammeClasse(){
		try {
			
			BufferedImage bi = ImageIO.read(new File("../UML/UML.png"));
			int width = bi.getWidth() +50;
			int height = bi.getHeight() +50;


			Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int heightEcran = (int)dimension.getHeight();
			int widthEcran  = (int)dimension.getWidth();

			if(width<widthEcran && height<heightEcran){
				this.setSize(width,height);
			}
			else{
				this.setSize(widthEcran,heightEcran-50);
			}

			JLabel lab =new JLabel(new ImageIcon(new ImageIcon("../UML/UML.png").getImage().getScaledInstance((int)bi.getWidth(),(int)bi.getHeight(), Image.SCALE_DEFAULT)));
			JScrollPane span1 = new JScrollPane(lab);
			this.add(span1);
			this.setVisible(true);
		}
		 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

 
}