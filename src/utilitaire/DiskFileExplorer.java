package utilitaire;
import java.io.File;
 /**
 * Lister le contenu d'un répertoire
 * http://www.fobec.com/java/964/lister-fichiers-dossiers-repertoire.html
 * @author fobec 2010
 */
public class DiskFileExplorer {
 

    public static String listDirectory(String dir) {
        String ret="";
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory() == false) {     
                    ret = ret + "\n"+files[i].getName();
                }
            }
        }
        return ret;
    }    
}