package vue;
import javax.swing.tree.*;
import javax.swing.*;
import java.awt.*;

/**
 * Cette classe permet de modifier les icônes des noeuds du JTree.
 */ 
public class ModificationJTree extends DefaultTreeCellRenderer implements TreeCellRenderer {
    private JLabel label;
	
	/**
	 * Le cosntructeur de la classe. Créé le JLabel qui contient le nom et l'image du noeud.
	 */
    ModificationJTree() {
        label = new JLabel();
    }

	
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {
        label =(JLabel) super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof Noeud) {
            Noeud nd = (Noeud) o;
            String chemin="";
            if(nd.getType().equals("base")){
                chemin = "../fichier/bdd.jpg";
            }else if(nd.getType().equals("table")){
                chemin = "../fichier/table.png";
            }else if(nd.getType().equals("attribut")){
                chemin = "../fichier/attribut.png";
            }


            label.setIcon(new ImageIcon(new ImageIcon(chemin).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            label.setText(nd.getName());
        }
        else {
            label.setIcon(null);
            label.setText("" + value);
        }
        
        return label;


    }


}