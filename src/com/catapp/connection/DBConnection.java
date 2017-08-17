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

    public  Connection getConnection() {
    	
           try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
            try {
               // con = DriverManager.getConnection(urlstring, username, password);
            	con=DriverManager.getConnection(url);
            	
            	
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
         /*catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }*/
    	return con;
    }
    /*public static void main(String[] args){
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
    }*/

}


/*package com.catapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection{
	private static String url = "jdbc:mysql://localhost:3306/CATAPP?autoReconnect=true&useSSL=false";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "vibscatapp";
    private static Connection con;
	public static final Logger logger = Logger.getLogger(DBConnection.class.toString());
   // private static String urlstring;

    public  Connection getConnection() {
    	
           try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
            try {
               con = DriverManager.getConnection(url, username, password);
            	//con=DriverManager.getConnection(url);
               logger.error("Connection Successful");
            	
            } catch (SQLException ex) {
                // log an exception. fro example:
            	logger.error("Error Occured While creating connection---->", ex);
            }
         catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
    	return con;
    }
    public static void main(String[] args){
    	try {
          //  Class.forName(driverName);
            
            try {
               // con = DriverManager.getConnection(urlstring, username, password);
            	new DBConnection().getConnection();
            	
            } catch (Exception ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
    }
*/
//}