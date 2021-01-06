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

    
    
    public MySQLConnectionFactory(String drive, String host, String port, String database, String user, String password) {
        
        this.DRIVER = drive;
        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.URL = "jdbc:mysql://"+this.HOST+":"+this.PORT+"/"+this.DATABASE;
        this.USER = user;
        this.PASSWORD = password;
        
    }
    
    public MySQLConnectionFactory(String host, String port, String database, String user, String password) {
        
        this.DRIVER = this.DEFAULT_DRIVER;
        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.URL = "jdbc:mysql://"+this.HOST+":"+this.PORT+"/"+this.DATABASE;
        this.USER = user;
        this.PASSWORD = password;
        
    }
    
    public MySQLConnectionFactory(String database) {
        
        this.DRIVER = this.DEFAULT_DRIVER;
        this.HOST = this.DEFAULT_HOST;
        this.PORT = this.DEFAULT_PORT;
        this.DATABASE = database;
        this.URL = "jdbc:mysql://"+this.HOST+":"+this.PORT+"/"+this.DATABASE;
        this.USER = this.DEFAULT_USER;
        this.PASSWORD = this.DEFAULT_PASSWORD;
        
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        
        Class.forName(DRIVER);
        
        connection = DriverManager.getConnection(URL);
   
        return connection;
    }
}
