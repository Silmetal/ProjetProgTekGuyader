package vue;

/**
 * Permet d'associer les éléments du JTree à l'image qui correspond à leur type.
 */
public class Noeud{

		/**
		 * Le nom de l'élément du JTree
		 */
        private String name;
        
		/**
		 * Le type de l'élément du JTree (Base de données, table, attribut, vue, trigger...)
		 */
		private String type;
		
		/**
		 * Le constructeur de la classe. Initialise les attributs de la classe en fonction des paramètres.
		 * @param name le nom du Noeud
		 * @param type le type du Noeu
		 */
        Noeud(String name, String type) {
            this.name = name;
            this.type = type;
        }
		
		/**
		 * Retourne le nom du Noeud.
		 * @return le nom du Noeud.
		 */
        public String getName() {
            return name;
        }
		
		/**
		 * Change le nom du Noeud en fonction du paramètre
		 * @param name le nouveau nom du Noeud
		 */
        public void setName(String name) {
            this.name = name;
        }

		/**
		 * Retourne le type du Noeud
		 * @return le type du Noeud
		 */
        public String getType() {
            return type;
        }

		/**
		 * Change le type du Noeud en fonction du paramètre
		 * @param type le nouveau type du Noeud
		 */
        public void setType(String type) {
            this.type = type;
        }

}