package utilitaire;


/** 
 * Cette classe permet de générer les templates des vues et des triggers.
 */
public class RequeteProgramee {
	
	public static String requeteCreationTriggerLigne(){
		String ret = "CREATE\n TRIGGER `event_name` BEFORE/AFTER INSERT/UPDATE/DELETE\n ON `database`.`table`\n FOR EACH ROW BEGIN\n 	-- corps du trigger\n END;\n";
		return ret;
	}

	public static String requeteCreationTriggerTable(){
		String ret = "CREATE\n TRIGGER `event_name` BEFORE/AFTER INSERT/UPDATE/DELETE\n ON `database`.`table`\n BEGIN\n     -- corps du trigger\n END;\n ";
		return ret;
	}


	public static String requeteCreationVue(){
		String ret = "CREATE VIEW nom_de_la_vue AS \nrequête_select";
		return ret;
	}
}
