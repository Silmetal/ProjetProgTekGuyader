package vue;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import model.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Le modèle de tableau utilisé par le JTable des FenetreNouvTable
 */
public class MyTableModel extends AbstractTableModel{
	
	/**
	 * Le nom des colonnes du tableau. Chaque colonne correspond à un attribut de la classe Attribut.
	 */
	private String[] titre={"Nom", "Type", "Valeur", "Non Nul ?",  "Unique ?", "Clé Primaire ?", "Clé étrangère ?", "Table référée", "Attribut référé"};

	/**
	 * Les données entrées dans le tableau
	 */
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	
	/**
	 * Le constructeur de la classe. Ajoute la première ligne du tableau au tableau. C'est une ligne vide.
	 */
	public MyTableModel() {
		
		data.add((new Object[]{"", "", "", false, false, false, false, "", ""}));
	}
	
	/**
	 * Retourne le nombre de colonnes du tableau
	 * @return le nombre de colonnes du tableau
	 */
	public int getColumnCount() {
		return titre.length;
	}
	
	/**
	 * Retourne le nombre de lignes du tableau
	 * @return le nombre de lignes du tableau
	 */
	public int getRowCount() {
		return data.size();
	}
	
	/**
	 * Retourne le nom de la colonne dont l'indice est précisé en paramètre
	 * @param col la colonne où est située la case
	 * @return le nom de la colonne dont l'indice est précisé en paramètre
	 */
	public String getColumnName(int col) {
		return titre[col];
	}

	/**
	 * Retourne la valeur de la case dont les coordonnées sont passées en paramètre
	 * @param row la ligne où est située la case
	 * @param col la colonne où est située la case
	 * @return la valeur de la case dont les coordonnées sont passées en paramètre
	 */
	public Object getValueAt(int row, int col) {
		return (data.get(row))[col];
	}
	
	/**
	 * Retourne la liste des données du tableau
	 * @return la liste des données du tableau
	 */
	public ArrayList<Object[]> getData() {
		return data;
	}

	/**
	 * Méthode utilisée par JTable pour déterminer le default renderer ou
	 * default editeur de chaque cellule. Sans cette méthode, les colonnes de booléans afficheraient
	 * true ou false, et non pas des JCheckBox.
	 * @param c l'indice de la colonne
	 * @return la class de la colonne
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	/**
	 * Cette méthode indique au JTable qu'il est éditable.
	 * @return true
	 */
	public boolean isCellEditable(int row, int col) {
		
		return true;
	}

	/**
	 * Permet de modifier la valeur de la case dont les coordonnées sont données en paramètre.
	 * @param value la nouvelle valeur de la case
	 * @param row la ligne où est située la case
	 * @param col la colonne où est située la case
	 */
	public void setValueAt(Object value, int row, int col) {
		
		(data.get(row))[col] = value;
		fireTableCellUpdated(row, col);
		
	}
	
	/**
	 * Ajoute une ligne au tableau contenant les données passées en paramètre
	 * @param rowData les données de la ligne ajoutée
	 */
    public void addRow(Object[] rowData) {
        
		data.add(rowData);
        this.fireTableDataChanged();
    }
	
	/**
	 * Retire la ligne dont l'indice est passé en paramètre
	 * @param row l'indice de la ligne à supprimer
	 */
	public void removeRow(int row) {
        data.remove(row);       
        this.fireTableDataChanged();
    }
}