package model;

import java.sql.*;

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
	
	/**
	 * Constructeur de la classe. Utilise les paramètres pour créer l'objet connexion avec la méthode connexion, puis utilise la
	 * méthode créerRequete pour créer l'objet requete.
	 * @param adresse l'adresse de la base de données
	 * @param nomUtili le nom d'utilisateur utilisé pour se connecter
	 * @param motDePasse le mot de passe correspondant au nom d'utilisateur utilisé pour se connecter.
	 */
	public BaseDeDonnees(String adresse, String nomUtili, String motDePasse) throws ClassNotFoundException,SQLException,Exception{
		
		try{	
			if (verifPilote()) {
				
				try{
					boolean test = connexion(adresse, nomUtili, motDePasse);
					System.out.println("Connexion etablie");
				}
				catch(SQLException se){
					System.out.println("Connexion echouee");
					throw se;
				}
				catch(Exception e){
					System.out.println("Connexion echouee");
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
	 */
	private boolean verifPilote() throws ClassNotFoundException {
		
		boolean ret = false;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Pilote oracle trouvé");
			ret = true;
		}
		
		catch (ClassNotFoundException e) {
			
			System.out.println("Pilote non trouvé");
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
	 */
	private boolean connexion(String adresse, String nomUtili, String motDePasse) throws Exception,SQLException {
		
		boolean ret = false;
		
		try {
			
			connexion = DriverManager.getConnection(adresse, nomUtili, motDePasse);
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
		
		finally { 
		
			if(connexion!=null){
				try{
					connexion.close();
				}
				catch(Exception eC){
					eC.printStackTrace();
				}
			} 
		}
		
		return ret;
	}
	
	/**
	 * Vérifie si l'objet Connection passé en paramètre est valide ou non.
	 * Si la connexion est créée, envoie un ping à la base de données. Si le ping retourne un résultat, la connexion est valide. Sinon, la connexion est invalide.
	 * @param connexion la connexion à tester
	 * @return le résultat du test de connexion : true si la connexion est valide, false sinon.
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
	 * @param userType définit le type d'utilisateur créé. 0 pour un super utilisateur local, 1 pour un super utilisateur global, 2 pour un utilisateur local, 3 pour un utilisateur global
	 */
	public void ajouterNouvelUtilisateur(String nouvIdenti, String nouvMDP, int userType) throws SQLException{
		
		PreparedStatement creerGlobalSuperUser = null;
		PreparedStatement creerLocalSuperUser = null;
		PreparedStatement creerLocalUser = null;
		PreparedStatement creerGlobalUser = null;
		
		try {
			creerLocalSuperUser = connexion.prepareStatement("CREATE USER '?'@'localhost' IDENTIFIED BY '?'; GRANT ALL PRIVILEGES ON *.* TO '?'@'localhost' WITH GRANT OPTION;");
			creerLocalSuperUser.setString(1,nouvIdenti);
			creerLocalSuperUser.setString(2,nouvMDP);
			creerLocalSuperUser.setString(3,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try{
			creerGlobalSuperUser = connexion.prepareStatement("CREATE USER '?'@'%' IDENTIFIED BY '?'; GRANT ALL PRIVILEGES ON *.* TO '?'@'%' WITH GRANT OPTION;");
			creerGlobalSuperUser.setString(1,nouvIdenti);
			creerGlobalSuperUser.setString(2,nouvMDP);
			creerGlobalSuperUser.setString(3,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try{
			creerLocalUser = connexion.prepareStatement("CREATE USER '?'@'localhost' IDENTIFIED BY '?'; GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON ?.* TO '?'@'localhost';");
			creerLocalUser.setString(1,nouvIdenti);
			creerLocalUser.setString(2,nouvMDP);
			creerLocalUser.setString(3,connexion.getMetaData().getDatabaseProductName());
			creerLocalUser.setString(4,nouvIdenti);
		}
		catch(SQLException se) {
			throw se;
		}
		
		try {
			creerGlobalUser = connexion.prepareStatement("CREATE USER '?'@'?' IDENTIFIED BY '?'; GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON ?.* TO '?'@'?';");
			creerGlobalUser.setString(1,nouvIdenti);
			creerGlobalUser.setString(2,connexion.getMetaData().getURL());
			creerGlobalUser.setString(3,nouvMDP);
			creerGlobalUser.setString(4,connexion.getMetaData().getDatabaseProductName());
			creerGlobalUser.setString(5,nouvIdenti);
			creerGlobalUser.setString(6,connexion.getMetaData().getURL());
		}
		catch(SQLException se) {
			throw se;
		}
		
		if (userType == 0) {
			
			try {
				creerLocalSuperUser.execute();
			}
			catch(SQLException se) {
				throw se;
			}
		}
		
		else if (userType == 1) {
			
			try{
				creerGlobalSuperUser.execute();
			}
			catch(SQLException se) {
				throw se;
			}
		}
		
		else if (userType == 2) {
			
			try{
				creerLocalUser.execute();
			}
			catch(SQLException se) {
				throw se;
			}
		}
		
		else if (userType == 3) {
			
			try {
				creerGlobalUser.execute();
			}
			catch(SQLException se) {
				throw se;
			}
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
	
}