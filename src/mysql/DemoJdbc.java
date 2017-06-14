package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJdbc {

	public static void main(String[] args) {
		sauverEnBase("Jean Dupont");

	}
	
	
	public static void sauverEnBase(String personne){
		String url = "jdbc:mysql://raspberry.arthurguyader.fr:3306/";
		String login = "admin";
		String passwd="admin";
		Connection cn = null ; 
		Statement st = null;
		try{
		
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("test");
			cn = DriverManager.getConnection(url,login,passwd);
			System.out.println(cn);
			System.out.println("test2");
		/*	st = cn.createStatement();
			
			String sql = "INSERT INTO 'formation' ('personne') VALUES ('"+personne+"')";
			System.out.println("test");
			st.executeUpdate(sql);*/
		}catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		} finally{
			try{
				cn.close();
				st.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
