package controleur;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.tree.TreePath;

public class EcouteurJTree implements TreeSelectionListener {
     public void valueChanged(TreeSelectionEvent event) {
         for (int i = 0; i < event.getPaths().length; i++) {
             TreePath treePath = event.getPaths()[i];
             System.out.print(treePath + " has been ");
             System.out.println(event.isAddedPath(treePath) ?
                                "selected" : "deselected");
         }
     }
 }