package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Cette classe établit une connexion à une base de données grâce à JDBC et les classes de java.sql.
 * La classe crée un objet Connnection sur lequel on peut effectuer des requêtes grâce à l'objet Statement également crée et à la classe Requête.
 * Exemple pour oracle :
 * <P>BaseDeDonnees bdd = new BaseDeDonnees("jdbc:oracle:thin:@localhsot:1521:xe", "root", "root")
 * "jdbc:oracle:thin:" indique que l'on se connect à une BDD oracle.
 * <P>"localhost" correspond au nom d'hôte
 * <P>"1521" correspond au port de la base
 * <P>"xe" correspond au SID.
 * <P>Les deux derniers champs sont, respectivement, le nom d'utilisateur et le mot de passe.
 */
public class BaseDeDonnees {
	
	/**
	 * L'objet Connection représente le lien entre l'application et la base de données. Il est défini lors de l'appel à la méthode connexion.
	 */
	private Connection connexion;
	private String nomDeLaBase;
	private String adresse;
	private String nomUtili;
	private String motDePasse;
	/**
	 * Constructeur de la classe. Utilise les paramètres pour créer l'objet connexion avec la méthode connexion, puis utilise la
	 * méthode créerRequete pour créer l'objet requete.
	 * @param adresse l'adresse de la base de données
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur utilisé pour se connecter.
	 * @param nomDeLaBase le nom de la base (Au choix)
	 * @throws ClassNotFoundException si le pilote correspondant n'est pas trouvé
	 * @throws SQLException si la connexion ne peut pas être établie à cause d'une erreur SQL
	 * @throws Exception si la connexion ne peut pas être établie à cause d'une autre erreur
	 */
	public BaseDeDonnees(String adresse, String nomUtili, String motDePasse,String nomDeLaBase) throws ClassNotFoundException,SQLException,Exception{
		this.adresse=adresse;
		this.nomUtili=nomUtili;
		this.motDePasse=motDePasse;
		try{	
			if (verifPilote()) {
				
				try{
					boolean test = connexion(adresse, nomUtili, motDePasse);
					this.nomDeLaBase = nomDeLaBase;
					System.out.println("Connexion etablie");
				}
				catch(SQLException se){
					throw se;
				}
				catch(Exception e){
					throw e;
				}
			}

		} catch(ClassNotFoundException ce){
			System.out.println("Verifiez votre pilote");
			throw ce;
		}
	}
	
	/**
	 * Vérifie la présence du pilote correspondant à la base à laquelle on essaye de se connecter.
	 * @return true si le pilote est présent, false sinon.
	 * @throws ClassNotFoundException si le pilote correspondant n'est pas trouvé
	 */
	private boolean verifPilote() throws ClassNotFoundException {
		
		boolean ret = false;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Pilote oracle trouvé");
			ret = true;
		}
		
		catch (ClassNotFoundException e) {
			
			System.out.println("Pilote non trouvé");
			e.printStackTrace();
			throw e;
		}
		
		return ret;
	}
	
	/**
	 * Appelle la méthode verifPilote, puis, si le pilote est présent, essaye d'établir une connexion en créant un objet Connection avec les paramètres en appellant les méthodes de JDBC.
	 * Si la connexion est établit, place l'objet Connection crée dans l'attribut connexion
	 * @param adresse l'adresse de la base à laquelle on essaye de se connecter.
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter.
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur.
	 * @return true si la connexion est établie, false sinon.
	 * @throws SQLException si la connexion ne peut pas être établie à cause d'une erreur SQL
	 * @throws Exception si la connexion ne peut pas être établie à cause d'une autre erreur
	 */
	private boolean connexion(String adresse, String nomUtili, String motDePasse) throws Exception,SQLException {
		
		boolean ret = false;
		
		try {
			
			connexion = DriverManager.getConnection(adresse+"allowMultiQueries=true", nomUtili, motDePasse);
			ret = estValide(connexion);
		}
		
		catch(SQLException se) {
			
			System.out.println("Connexion échouée ! Vérifiez vos identifiants et l'adresse de connexion");
			throw se;
		}
		catch(Exception e){ 
			System.out.println("Autre erreur : "); 
			throw e;
		}
		
		
		return ret;
	}
	
	/**
	 * Vérifie si l'objet Connection passé en paramètre est valide ou non.
	 * Si la connexion est créée, envoie un ping à la base de données. Si le ping retourne un résultat, la connexion est valide. Sinon, la connexion est invalide.
	 * @param connexion la connexion à tester
	 * @return le résultat du test de connexion : true si la connexion est valide, false sinon.
	 * @throws SQLException si le ping n'est pas effectué à cause d'une erreur SQL
	 */
	private static boolean estValide(Connection connexion) throws SQLException{ 
		
		boolean ret = false;
		
		if(connexion==null){ 
			ret = false;
			System.out.println("connexion = null");
		} 
		ResultSet ping = null; 
		try{ 
			if(connexion.isClosed()){ret = false;} 
			ping = connexion.createStatement().executeQuery("SELECT 1 FROM DUAL"); 
			ret = ping.next();
			if (ret == false) System.out.println("Ping échoué");
		}
		catch(SQLException se){ 
			ret = false;
			throw se;
		} 
		finally{ 
			
			if(ping!=null){
				try{ping.close();}
				catch(Exception e){}
			} 
		} 
		return ret;
	}
	
	/**
	 * Prend en paramètre un nom d'utilisateur et un mot de passe pour créer un nouvel utilisateur et l'ajouter à la base. Le type de l'utilsiateur dépend du paramètre userType.
	 * L'utilsiateur ainsi crée pourra se connecter à la base de données avec ces identifiants.
	 * Un super utilisateur local ne peut se connecter que en localhost, et a tous les privilèges sur toutes les bases de données. Un super utilisateur global peut se connecter depuis n'importe quel hôte et a tous les
	 * privilèges sur toutes les bases de données. Un utilisateur local a des privilèges limités sur la base de données à laquelle le créateur est connecté. un utilisateur local ne peut se connecter qu'en localhost,
	 * un utilisateur global peut se connecter depuis n'importe quel hôte.
	 * @param nouvIdenti l'identifiant du nouvel utilisateur
	 * @param nouvMDP le mot de passe du nouvel utilisateur
	 * @param userType définit le type d'utilisateur créé. 0 pour un super utilisateur, 1 pour un utilisateur normal
	 * @param dbName le nom de la base de données à laquelle ajouter l'utilisateur
	 * @throws SQLException si l'utilisateur ne peut pas être créé à cause d'une erreur SQL
	 */
	public void ajouterNouvelUtilisateur(String nouvIdenti, String nouvMDP, String dbName, int userType) throws SQLException{
		
		Statement creerSuperUser = connexion.createStatement();
		Statement creerUser = connexion.createStatement();
		
		/* try {
			creerLocalSuperUser = connexion.execute("CREATE USER "+nouvIdenti+"@localhost IDENTIFIED BY "+nouvMDP+"; GRANT ALL PRIVILEGES ON *.* TO "+nouvIdenti+"@localhost WITH GRANT OPTION;");
			creerLocalSuperUser.setString(1,nouvIdenti);
			creerLocalSuperUser.setString(2,nouvMDP);
			creerLocalSuperUser.setString(3,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try{
			creerGlobalSuperUser = connexion.execute("CREATE USER "+nouvIdenti+"@% IDENTIFIED BY "+nouvMDP+"; GRANT ALL PRIVILEGES ON *.* TO "+nouvIdenti+"@% WITH GRANT OPTION;");
			creerGlobalSuperUser.setString(1,nouvIdenti);
			creerGlobalSuperUser.setString(2,nouvMDP);
			creerGlobalSuperUser.setString(3,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try{
			creerLocalUser = connexion.execute("CREATE USER "+nouvIdenti+"@localhost IDENTIFIED BY "+nouvMDP+"; GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON "+connexion.getMetaData().getDatabaseProductName()+".* TO "+nouvIdenti+"@localhost;");
			/* creerLocalUser.setString(1,nouvIdenti);
			creerLocalUser.setString(2,nouvMDP);
			creerLocalUser.setString(3,connexion.getMetaData().getDatabaseProductName());
			creerLocalUser.setString(4,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try {
			creerGlobalUser = connexion.execute("CREATE USER "+nouvIdenti+"@"+connexion.getMetaData().getURL()+" IDENTIFIED BY "+nouvMDP+"; GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON "+connexion.getMetaData().getDatabaseProductName()+".* TO "+nouvIdenti+"@"+connexion.getMetaData().getURL()+";");
			creerGlobalUser.setSt	ring(1,nouvIdenti);
			creerGlobalUser.setString(2,connexion.getMetaData().getURL());
			creerGlobalUser.setString(3,nouvMDP);
			creerGlobalUser.setString(4,connexion.getMetaData().getDatabaseProductName());
			creerGlobalUser.setString(5,nouvIdenti);
			creerGlobalUser.setString(6,connexion.getMetaData().getURL());
		}
		catch(SQLException se) {
			throw se;
		} */
		
		if (userType == 0) {
			
			try {
				creerSuperUser.executeUpdate("CREATE USER '"+nouvIdenti+"' IDENTIFIED BY '"+nouvMDP+"'; GRANT ALL ON projet.* TO '"+nouvIdenti+"' WITH GRANT OPTION;");;
			}
			catch(SQLException se) {
				throw se;
			}
		}
		
		else if (userType == 1) {
			try {
				creerUser.executeUpdate("use "+dbName);
				creerUser.executeUpdate("CREATE USER '"+nouvIdenti+"' IDENTIFIED BY '"+nouvMDP+"';");
				
			}
			catch(SQLException se){

			}

			try{
				creerUser.executeUpdate("GRANT ALL ON projet.* TO '"+nouvIdenti+"' IDENTIFIED BY '"+nouvMDP+"';");
				creerUser.executeUpdate("FLUSH PRIVILEGES;");
			}
			catch(SQLException se) {
				throw se;
			}
		}
	}
	
	//Recupere le nom des tables de la base
	public ArrayList<String> parcourirBase(){
		DatabaseMetaData dmd;
		ResultSet tables;
		int i=0;
		ArrayList<String> ret = new ArrayList<String>();
		try{
			dmd = this.connexion.getMetaData();
			tables = dmd.getTables(this.connexion.getCatalog(),null,"%",null);
			i=0;
			while(tables.next()){
				ret.add(tables.getString(3));
				i++;
			}
		} catch(SQLException e){
			System.out.println("Impossible de parcourir la base");
		}

		String listString = "";

		for (String s : ret)
		{
		    listString += s + "\t";
		}

		return ret;
	}


	//Recuperer le nom des attributs
	public ArrayList<String> parcourirTable(String table){
		ArrayList<String> ret = new ArrayList<String>();
		String affichage="";

		try{
			Requete nouvelleRequete = new Requete(connexion,"");
				Object[] res = nouvelleRequete.manuel("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+table+"'");

				ResultSet rs=(ResultSet)res[1];

				affichage = nouvelleRequete.retournerResultSet(rs);
				
				for(String str : affichage.split("[\n]")){
					ret.add(str);
				}
		} catch (SQLException se){

		} catch (Exception e){

		}

		return ret;

	}

	
	/**
	 * Supprime de la base de données l'utilisateur dont l'identifiant est passé en paramètre
	 * @param nomUtilis l'identifiant de l'utilisateur à supprimer
	 * @throws SQLException si l'utilisateur ne peut pas être supprimé à cause d'une erreur SQL
	 */
	public void supprimerUtilisateur(String nomUtilis) throws SQLException{
		
		PreparedStatement supprimerUtilisateur = null;
		
		try {
			supprimerUtilisateur = connexion.prepareStatement("DROP USER ?;");
			supprimerUtilisateur.setString(1,nomUtilis);
		}
		catch(SQLException se) {
			throw se;
		}
	}
	
	/**
	 * Enregistre la base de données dans un fichier.
	 * Le fichier contiendra toutes les requêtes SQL nécessaires pour créer une nouvelle base de données
	 * identique à celle à laquelle l'utilsiateur est connecté.
	 * @param fileName le nom du fichier crée
	 */
	public void ecrire(String fileName){
		
		
	}
	
	/**
	 * Exécute les requêtes SQL de création d'une base de données stockées dans un fichier.
	 * @param fileName le nom du fichier à lire
	 */	 
	public void lire(String fileName){
		
	}

	/**
	 * Retourne l'objet Connection créé par l'instance
	 * @return l'objet Connection créé par l'instance
	 */	
	public Connection getConnection(){
		return this.connexion;
	}

	public String getNomDeLaBase(){
		return this.nomDeLaBase;
	}

	public String getAdresse(){return this.adresse;}
	public String getNomUtili(){return this.nomUtili;}
	public String getMotDePasse(){return this.motDePasse;}





	public void deconnexion(){
		if(connexion!=null){
				try{
					connexion.close();
				}
				catch(Exception eC){
					eC.printStackTrace();
				}
		} 
	}
	
}