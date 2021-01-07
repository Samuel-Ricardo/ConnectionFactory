/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        URL = getURL();
        USER = user;
        PASSWORD = password;
        
    }
    
    private static void loadClass(String host, String port, String database, String user, String password) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = host;
        PORT = port;
        DATABASE = database;
        URL = getURL();
        USER = user;
        PASSWORD = password;
        
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
        
        loadClass(database);
        
        Class.forName(DRIVER);
        
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
   
        return connection;
    }
    
    public static Connection getConnection(String host, String port, String database, String user, String password) throws ClassNotFoundException, SQLException {
        
        loadClass(host, port, database, user, password);
        
        Class.forName(DRIVER);
        
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
   
        return connection;
    }
}
