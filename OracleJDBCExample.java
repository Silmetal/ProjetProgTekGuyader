package com.mkyong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleJDBCExample {

    public static void main(String[] argv) {

        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "root", "root");
        	
       /* 	connection = DriverManager.getConnection
        			("jdbc:oracle://localhost/?user=root&password=rootpassword"); */
        			

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            System.out.println(connection);
        } else {
            System.out.println("Failed to make connection!");
        }
        
        try{
        	
        	
        	Statement state=connection.createStatement();
        	
			int Result=state.executeUpdate("CREATE DATABASE databasename");
        	
    /*    //Création d'un objet Statement
        Statement state = connection.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM Client");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();
           
        System.out.println("\n**********************************");
        //On affiche le nom des colonnes
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
          System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
           
        System.out.println("\n**********************************");
           
        while(result.next()){         
          for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            System.out.print("\t" + result.getObject(i).toString() + "\t |");
              
          System.out.println("\n---------------------------------");

        }*/
        	
        	
        	
        	
        	
        	
        	
        }
        catch (Exception e) {

            e.printStackTrace();
        }
        
    }

}