/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * <P> A Class to connect at <B> MySQL </B>
 * 
 * <P> For this, Is used the MySQL Connector java 8.0.22 
 * 
 * @author Samuel Ricardo Cabral de Barros
 * @version 1.0.0
 * @since Jan 2021
 */
public class MySQLConnectionFactory {
    
    /**
    *
    * <P>Driver location
    * 
    * <P>It receives by default: {@link #DEFAULT_DRIVER}, however, it can be <BR>
    * modified by calling the {@link #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)} <BR>
    * method by passing driver as a parameter
    * 
    * @serial  DRIVER
    * @see #DEFAULT_DRIVER
    */
    private static String DRIVER = MySQLConnectionFactory.DEFAULT_DRIVER; 
    
    /**
    *
    * <P>Database Host
    * 
    * <P>if you don't know or use a standard Host, try to use the {@link #DEFAULT_HOST}
    * 
    * @serial  HOST
    * @see #DEFAULT_HOST
    */
    private static String HOST;
    
    /**
    *
    * <P>Database Port
    * 
    * <P>if you don't know or use a standard Port, try to use the {@link #DEFAULT_PORT}
    * 
    * @serial  PORT
    * @see #DEFAULT_PORT
    */
    private static String PORT;
    
    /**
    *
    *<P> name of the database to be connected
    * 
    * @serial  DATABASE
    */
    private static String DATABASE;
    
    /**
    *
    *<P> URL With  Informations to Connect
    * 
    * @serial  URL
    */
    private static String URL; 
    
    /**
    *
    * <P>Database User Registred
    * 
    * <P>if you don't know or use a standard User, try to use the {@link #DEFAULT_USER}
    * 
    * @serial  USER
    * @see #DEFAULT_USER
    */
    private static String USER;
    
    /**
    *
    * <P>Database Password Registred
    * 
    * <P>if you don't know or use a standard User, try to use the {@link #DEFAULT_USER}
    * 
    * @serial  PASSWORD
    * @see #DEFAULT_PASSWORD
    */
    private static String PASSWORD;
    
    
    
    
    /**
    *
    *<P> Default Database User Registred
    * 
    * @serial  DEFAULT_USER {@value}
    * @see #USER
    */
    public static final String DEFAULT_USER = "root";
    
    /**
    *
    * <P>Default Database Password Registred
    * 
    * @serial  DEFAULT_PASSWORD {@value}
    * @see #PASSWORD
    */
    public static final String DEFAULT_PASSWORD = "";
    
    /**
    *
    * <P>Default Driver Location
    * 
    * <P>This driver refers to the latest JDBC (mysql-connector-java 8.0.22)
    * 
    * <P>if you use an older JDBC such as mysql-connector-java 5.1.48, then use {@link #OLD_DEFAULT_DRIVER}
    * 
    * @serial  DEFAULT_DRIVER  {@value}
    * @see #DRIVER
    * @see #OLD_DEFAULT_DRIVER 
    */
    public static final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    /**
    *
    * <P> Default Driver Location
    * 
    * <P> This driver refers to older JDBCs as mysql-connector-java 5.1.48
    *
    * <P> if you use a newer JDBC, like mysql-connector-java 8.0.22, use {@link #DEFAULT_DRIVER}
    *
    * @serial  OLD_DEFAULT_DRIVER {@value}
    * @see #DRIVER
    * @see #DEFAULT_DRIVER 
    */
    public static final String OLD_DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    
    /**
    *
    * <P>Default Database Host
    * 
    * @serial  DEFAULT_HOST {@value}
    * @see #HOST
    */
    public static final String DEFAULT_HOST = "localhost";
    
    /**
    *
    * <P>Default Database Port
    * 
    * @serial  DEFAULT_PORT  {@value}
    * @see #PORT
    */
    public static final String DEFAULT_PORT = "3306";

    /**
     *
     * method responsible for loading the class with the necessary data to make 
     * the connection to the database
     * 
     * @param drive
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     */
    private static void loadClass(String drive, String host, String port, String database, String user, String password) {
        
        DRIVER = drive;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL();
        
    }
    
    /**
     *
     * method responsible for loading the class with the necessary data to make 
     * the connection to the database
     * 
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     */
    private static void loadClass(String host, String port, String database, String user, String password) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL();
        
    }

    /**
     *
     * method responsible for loading the class with the necessary data to make 
     * the connection to the database
     * 
     * @param database
     */
    private static void loadClass(String database) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = DEFAULT_HOST;
        PORT = DEFAULT_PORT;
        DATABASE = database;
        USER = DEFAULT_USER;
        PASSWORD = DEFAULT_PASSWORD;
        URL = getURL();
        
    }
    
    /**
     *
     * <P>first it calls the {@link #loadClass(java.lang.String)} function that loads the class with the data passed by parameter
     * 
     * <P>after he <code>try</code> to import the driver, if he <code>catch</code> an {@link ClassNotFoundException} he will <code>throw</code> a {@link RuntimeException}
     * 
     * <P>if he imported the driver with success, he will <code>try</code> to connect to the database, if he <code>catch</code> an {@link SQLException} he will <code>throw</code> {@link RuntimeException}
     *   
     * <P>if everything is successful, it will be <code>return</code> a {@link Connection}
     * 
     * @param database
     * @return
     * @throws RuntimeException
     */
    public static Connection getConnection(String database) {
        
        try {
            loadClass(database);

            Class.forName(DRIVER);

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

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
                                  + "\n Cause: "+ ex.getCause()
                                  + "\n"
                                  + "\n Recommendations:"
                                  + "\n"
                                  + "\n Check the internet connection;"
                                  + "\n"
                                  + "\n"
                                  + "\n Check if the database you passed as a parameter is correct;");
        }
    }
    
    /**
     *
     * <P>first it calls the {@link #loadClass(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) } function that loads the class with the data passed by parameter
     * 
     * <P>after he <code>try</code> to import the driver, if he <code>catch</code> an {@link ClassNotFoundException} he will <code>throw</code> a {@link RuntimeException}
     * 
     * <P>if he imported the driver with success, he will <code>try</code> to connect to the database, if he <code>catch</code> an {@link SQLException} he will <code>throw</code> {@link RuntimeException}
     *   
     * <P>if everything is successful, it will be <code>return</code> a {@link Connection}
     *
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     * @return
     * @throws RuntimeException
     */
    public static Connection getConnection(String host, String port, String database, String user, String password) {
        
        try {
            loadClass(host, port, database, user, password);
            
            Class.forName(DRIVER);
            
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
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
                                  + "\n Cause: "+ ex.getCause()
                                  + "\n"
                                  + "\n Recommendations:"
                                  + "\n"
                                  + "\n Check the internet connection;"
                                  + "\n"
                                  + "\n"
                                  + "\n Check if the parameters you passed are correct;");
        }
    }
    
    /**
     *
     * method responsible for creating a connection
     * 
     * if it <code>catch</code> a {@link ClassNotFoundException} or {@link SQLException} it will <code>throw</code> {@link RuntimeException}
     *   
     * if successful it will <code>return</code> a {@link Connection}
     *
     * @param driver
     * @param host
     * @param port
     * @param database
     * @param user
     * @param password
     * @return 
     * @throws RuntimeException
     */
    public static Connection getConnection(String driver, String host, String port, String database, String user, String password) {
        
        try {
            loadClass(driver, host, port, database, user, password);
            
            Class.forName(DRIVER);
            
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            return connection;
            
        } catch (ClassNotFoundException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to import DRIVER: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause()
                                  + "\n"
                                  + "\n Recommendations:"
                                  + "\n"
                                  + "\n Check if the Driver you passed as a parameter is correct;");
           
        } catch (SQLException ex) {
            
            Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("Error at trying to Connect: " + ex
                                  + "\n"
                                  + "\n"
                                  + "\n Cause: "+ ex.getCause()
                                  + "\n"
                                  + "\n Recommendations:"
                                  + "\n"
                                  + "\n Check the internet connection;"
                                  + "\n"
                                  + "\n"
                                  + "\n Check if the parameters you passed are correct;");
        }
        
    }  
        
    /**
     *
     * 
     * 
     * @param connection
     * @return
     */
    public static boolean closeConnection(Connection connection){
            
            if (connection != null ){
                
                try {
                    
                    connection.close();
                    
                    return true;
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            
                    throw new RuntimeException("Error at trying to Disconnect: " + ex
                                          + "\n"
                                          + "\n"
                                          + "\n Cause: "+ ex.getCause());
                    
                }
            }else{
                
                return false;
            }
    }
    
    public static boolean closeConnection(Connection connection, PreparedStatement statement ){ 

        closeConnection(connection);
        
        if(statement != null){
            
            try {
                if(statement.isClosed() == false){
                    
                       statement.close();
                       
                       return true;
                }else{
                    return false; 
                }
                
            } catch (SQLException ex) {
                
                Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                
                throw new RuntimeException("Error at trying to Disconnect: " + ex
                                          + "\n"
                                          + "\n"
                                          + "\n Cause: "+ ex.getCause());
                    
            }
            
        }else{
            
            return false;
        }
        
    }
    
    public static boolean closeConnection(Connection connection, PreparedStatement statement, ResultSet result){
        
        closeConnection(connection, statement);
        
        if(result != null){
            
            try {
                if(result.isClosed() == false){
                    
                    result.close();
                    
                    return true;
                }else{
                    
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MySQLConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                
                throw new RuntimeException("Error at trying to Disconnect: " + ex
                                          + "\n"
                                          + "\n"
                                          + "\n Cause: "+ ex.getCause());
            }
            
        }else{
            return false;
        }
    }
    
    private static String getURL() {
        return "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?useTimezone=true&serverTimezone=UTC";
    }
}
