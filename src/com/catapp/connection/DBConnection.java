package com.catapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.catapp.entity.User;

public class DBConnection{
	private static String url = "jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true";    
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   
    ////private static String username = "root";   
   // private static String password = "root";
    private static Connection con;
   // private static String urlstring;

    /*public  Connection getConnection() {
        try {
            Class.forName(driverName);
            
            try {
               // con = DriverManager.getConnection(urlstring, username, password);
            	con=DriverManager.getConnection(url);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }*/
    public static void main(String[] args){
    	try {
            Class.forName(driverName);
            
            try {
               // con = DriverManager.getConnection(urlstring, username, password);
            	con=DriverManager.getConnection(url);
            	User lUser =new User();
            	lUser.setEntityId(1l);
            	lUser.find(con, lUser);
            	
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
    }

}
