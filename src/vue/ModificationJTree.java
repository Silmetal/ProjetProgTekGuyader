package vue;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class ModificationJTree implements TreeCellRenderer {
    private JLabel label;

    ModificationJTree() {
        label = new JLabel();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
                                                  boolean leaf, int row, boolean hasFocus) {


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


            label.setIcon(new ImageIcon(chemin));
            label.setText(nd.getName());
        }
        else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;


    }
}