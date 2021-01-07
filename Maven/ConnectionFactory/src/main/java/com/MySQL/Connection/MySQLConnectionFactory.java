/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * <P>This class aims to make the connection to the <B>MySQL</B> database in a simple way
 * 
 * @author Samuel
 * @version 1.0.0
 * @since Jan 2021
 */
public class MySQLConnectionFactory {
    
    private static String DRIVER; 
    private static String HOST;
    private static String PORT;
    private static String DATABASE;
    private static String URL; 
    private static String USER;
    private static String PASSWORD;
    private static Connection connection;
    
    public static final String DEFAULT_USER = "root";
    public static final String DEFAULT_PASSWORD = "";
    public static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_PORT = "3306";

    
    
    private static void loadClass(String drive, String host, String port, String database, String user, String password) {
        
        DRIVER = drive;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL();
        
    }
    
    private static void loadClass(String host, String port, String database, String user, String password) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL();
        
    }

    private static String getURL() {
        return "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?useTimezone=true&serverTimezone=UTC";
    }
    
    private static void loadClass(String database) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = DEFAULT_HOST;
        PORT = DEFAULT_PORT;
        DATABASE = database;
        USER = DEFAULT_USER;
        PASSWORD = DEFAULT_PASSWORD;
        URL = getURL();
        
    }
    
    public static Connection getConnection(String database) throws ClassNotFoundException, SQLException {
        
        try {
            loadClass(database);

            Class.forName(DRIVER);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            return connection;
        
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to import DRIVER: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause());
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to Connect: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause());
        }
    }
    
    public static Connection getConnection(String host, String port, String database, String user, String password) {
        
        try {
            loadClass(host, port, database, user, password);
            
            Class.forName(DRIVER);
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            return connection;
            
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to import DRIVER: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause());
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to Connect: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause());
        }
    }
    
    
}
