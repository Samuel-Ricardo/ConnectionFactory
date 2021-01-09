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
    
    
    private static final NullPointerException NULL_POINTER_EXCEPTION = new NullPointerException("This field is empty or null, try to create a connection");
    
    
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
    private static String HOST = MySQLConnectionFactory.DEFAULT_HOST;
    
    /**
    *
    * <P>Database Port
    * 
    * <P>if you don't know or use a standard Port, try to use the {@link #DEFAULT_PORT}
    * 
    * @serial  PORT
    * @see #DEFAULT_PORT
    */
    private static String PORT = MySQLConnectionFactory.DEFAULT_PORT;
    
    /**
    *
    *<P> Name of the Database to be Connected
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
    private static String USER = MySQLConnectionFactory.DEFAULT_USER;
    
    /**
    *
    * <P>Database Password Registred
    * 
    * <P>if you don't know or use a standard Password, try to use the {@link #DEFAULT_PASSWORD}
    * 
    * @serial  PASSWORD
    * @see #DEFAULT_PASSWORD
    */
    private static String PASSWORD = MySQLConnectionFactory.DEFAULT_USER;
    
    
    
    
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
     * @param driver Driver location
     * @param host Database Host
     * @param port Database Port
     * @param database Name of the Database to be Connected
     * @param user Database User Registred
     * @param password Database Password Registred
     */
    private static void loadClass(String driver, String host, String port, String database, String user, String password) {
        
        DRIVER = driver;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL(HOST,PORT,DATABASE);
        
    }
    
    /**
     *
     * method responsible for loading the class with the necessary data to make 
     * the connection to the database
     * 
     * @param host Database Host
     * @param port Database Port
     * @param database Name of the Database to be Connected
     * @param user Database User Registred
     * @param password Database Password Registred
     */
    private static void loadClass(String host, String port, String database, String user, String password) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        URL = getURL(HOST,PORT,DATABASE);
        
    }

    /**
     *
     * method responsible for loading the class with the necessary data to make 
     * the connection to the database
     * 
     * @param database Name of the Database to be Connected
     */
    private static void loadClass(String database) {
        
        DRIVER = DEFAULT_DRIVER;
        HOST = DEFAULT_HOST;
        PORT = DEFAULT_PORT;
        DATABASE = database;
        USER = DEFAULT_USER;
        PASSWORD = DEFAULT_PASSWORD;
        URL = getURL(HOST,PORT,DATABASE);
        
    }
    
    /**
     * <P> Method responsible for making a connection
     *
     * <P>first It Calls The {@link #loadClass(java.lang.String)} function that loads the class with the data passed by parameter
     * 
     * <P>after he <code>try</code> to import the driver, if he <code>catch</code> an {@link ClassNotFoundException} he will <code>throw</code> a {@link RuntimeException}
     * 
     * <P>if he imported the driver with success, he will <code>try</code> to connect to the database, if he <code>catch</code> an {@link SQLException} he will <code>throw</code> {@link RuntimeException}
     *   
     * 
     * @param database Name of the Database to be Connected
     * 
     * @return
     *  <P> {@link Connection} - if everything is successful 
     * 
     *  <P> <CODE>null</CODE> - if <code>catch</code> a <code>Exception</code>
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException} or {@link ClassNotFoundException}
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
     * <P> Method responsible for making a connection
     * 
     * <P>first It Calls The {@link #loadClass(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) } function that loads the class with the data passed by parameter
     * 
     * <P>after he <code>try</code> to import the driver, if he <code>catch</code> an {@link ClassNotFoundException} he will <code>throw</code> a {@link RuntimeException}
     * 
     * <P>if he imported the driver with success, he will <code>try</code> to connect to the database, if he <code>catch</code> an {@link SQLException} he will <code>throw</code> {@link RuntimeException}
     *
     * @param host Database Host
     * @param port Database Port
     * @param database Name of the Database to be Connected
     * @param user Database User Registred
     * @param password Database Password Registred
     * 
     * @return
     *  <P> {@link Connection} - if everything is successful 
     * 
     *  <P> <CODE>null</CODE> - if <code>catch</code> a <code>Exception</code>
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException} or {@link ClassNotFoundException}
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
     * <P> Method responsible for making a connection
     * 
     * <P>first It Calls The {@link #loadClass(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) } function that loads the class with the data passed by parameter
     * 
     * <P>after he <code>try</code> to import the driver, if he <code>catch</code> an {@link ClassNotFoundException} he will <code>throw</code> a {@link RuntimeException}
     * 
     * <P>if he imported the driver with success, he will <code>try</code> to connect to the database, if he <code>catch</code> an {@link SQLException} he will <code>throw</code> {@link RuntimeException}
     *
     * @param driver Driver location
     * @param host Database Host
     * @param port Database Port
     * @param database Name of the Database to be Connected
     * @param user Database User Registred
     * @param password Database Password Registred
     * 
     * @return
     *  <P> {@link Connection} - if everything is successful 
     * 
     *  <P> <CODE>null</CODE> - if <code>catch</code> a <code>Exception</code>
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException} or {@link ClassNotFoundException}
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
     * <P> Method responsible for closing the connection
     * 
     * @param connection Connection to be closed
     * 
     * @return
     *  <P> <CODE>false</CODE> - if the Connection has not been closed, 
     * this is because the Connection is <CODE>null</CODE> or is already closed
     *  
     *  <P> <code>true</code> - if everything is successful
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException}
     */
    public static boolean closeConnection(Connection connection){
            
            if (connection != null ){
                
                try {
                    
                    if(connection.isClosed() == false){
                    
                        connection.close();

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
    
    /**
     *<P> Method responsible for closing the connection 
     * 
     *<P> It Calls The {@link #closeConnection(java.sql.Connection)}
     * 
     * @param connection Connection to be closed
     * @param statement PreparedStatement to be closed
     * 
     * @return
     *  <P> <CODE>false</CODE> - if the PreparedStatement has not been closed, 
     * this is because the PreparedStatement is <CODE>null</CODE> or is already closed
     *  
     *  <P> <code>true</code> - if everything is successful
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException}
     */
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
    
    /**
     *<P> Method responsible for closing the connection 
     * 
     *<P> It Calls The {@link #closeConnection(java.sql.Connection, java.sql.PreparedStatement)}
     * 
     * @param connection Connection to be closed
     * @param statement PreparedStatement to be closed
     * @param result ResultSet to be closed
     * 
     * @return
     *  <P> <CODE>false</CODE> - if the ResultSet has not been closed, 
     * this is because the ResultSet is <CODE>null</CODE> or is already closed
     *  
     *  <P> <code>true</code> - if everything is successful
     * 
     * @throws RuntimeException  if the method <CODE>catch</CODE> {@link SQLException}
     */
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
    
    /**
     *
     * Generate URL to connect whit Database
     * 
     * @param host Database Host
     * @param port Database Port
     * @param database Name of the Database to be Connected
     * 
     * @return URL with data to connect with database
     */
    public static String getURL(String host, String port, String database) {
        return "jdbc:mysql://"+host+":"+port+"/"+database+"?useTimezone=true&serverTimezone=UTC";
    }

    /**
    *  <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
    * 
    * <P>The Driver receives by default: {@link #DEFAULT_DRIVER}, however, it can be <BR>
    * modified by calling the {@link #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)} <BR>
    * method by passing <B>briver</B> as a parameter
    * 
    * @serial  DRIVER
    * @see #DEFAULT_DRIVER
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * 
    * @return <P> Driver location
    */
    public static String getDRIVER() {
        
        if(DRIVER != null){
            
        
            if(DRIVER.isEmpty() || DRIVER.equals("") || DRIVER.equals(" ")){

                throw  NULL_POINTER_EXCEPTION ;
                
            }else{
                
                return DRIVER;
            }
        }else{
           throw NULL_POINTER_EXCEPTION;
        }
    }

    /**
     *  <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
     * 
     * <P>It receives by default: {@link #DEFAULT_HOST}, however, it can be 
     * modified by calling the <CODE>getConnection()</CODE> 
     * method by passing <B>host</B> as a parameter
     * 
     * 
     * <P>if you don't know or use a standard Host, try to use the {@link #DEFAULT_HOST}
     * 
     * @see #DEFAULT_HOST
     * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     * 
     * @return <P> Database Host
     */
    public static String getHOST() {
        
        if(HOST != null){
            
        
            if(HOST.isEmpty() || HOST.equals("") || HOST.equals(" ")){

                throw  NULL_POINTER_EXCEPTION ;
                
            }else{
                
                return HOST;
            }
        }else{
           throw NULL_POINTER_EXCEPTION;
        }
    }

    /**
    * 
    *  <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
    * 
    * <P>It receives by default: {@link #DEFAULT_PORT}, however, it can be 
    * modified by calling the <CODE>getConnection()</CODE> 
    * method by passing <B>port</B> as a parameter 
    * 
    * <P>if you don't know or use a standard Port, try to use the {@link #DEFAULT_PORT}
    * 
    * @see #DEFAULT_PORT
    * 
    * @return<P>Database Port
    * 
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    */
    public static String getPORT() {
        
        if(PORT != null){
            
        
            if(PORT.isEmpty() || PORT.equals("") || PORT.equals(" ")){

                throw  NULL_POINTER_EXCEPTION ;
                
            }else{
                
                return PORT;
            }
        }else{
           throw NULL_POINTER_EXCEPTION;
        }
    }

    /**
     *
     *  <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
     * 
     * @return <P> Name of the Database Connected
     * 
     * @see #getConnection(java.lang.String) 
     * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
     */
    public static String getDATABASE() {
        
        if(DATABASE != null){
            
        
            if(DATABASE.isEmpty() || DATABASE.equals("") || DATABASE.equals(" ")){

                throw  NULL_POINTER_EXCEPTION ;
                
            }else{
                
                return DATABASE;
            }
        }else{
           throw NULL_POINTER_EXCEPTION;
        }
    }

    /**
    * <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
    * 
    * <P>It receives by default: {@link #DEFAULT_URL}, however, it can be 
    * modified by calling the <CODE>getConnection()</CODE> 
    * method by passing <B>ulr</B> as a parameter
    * 
    * <P>if you don't know or use a standard Url, try to use the {@link #DEFAULT_URL}
    * 
    * @see #DEFAULT_USER
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * 
    * @return <P>Database User Registred
    */
    public static String getURL() {
        
        if(URL != null){
            
        
            if(URL.isEmpty() || URL.equals("") || URL.equals(" ")){

                throw  NULL_POINTER_EXCEPTION ;
                
            }else{
                
                return URL;
            }
        }else{
           throw NULL_POINTER_EXCEPTION;
        }
    }

    /**
    * <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
    * 
    * <P>It receives by default: {@link #DEFAULT_USER}, however, it can be 
    * modified by calling the <CODE>getConnection()</CODE> 
    * method by passing <B>User</B> as a parameter
    * 
    * <P>if you don't know or use a standard User, try to use the {@link #DEFAULT_USER}
    * 
    * @see #DEFAULT_USER
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * 
    * @return 
    *       <P>Database User Registred
    * 
    *       <P> <CODE> null </CODE> - if the USER is null
    * 
    * @throws NullPointerException if the USER is null 
    */
    public static String getUSER() {
        
        if(USER != null){
            
            return USER;
        }else{
            throw  NULL_POINTER_EXCEPTION;
        }
    }

    /**
    * 
    * <P> <B>AVOID USING IF A CONNECTION HAS NOT BEEN CREATED OR CAN <CODE> throw</CODE> {@link NullPointerException} </B>
    * 
    * <P>It receives by default: {@link #DEFAULT_PASSWORD}, however, it can be 
    * modified by calling the <CODE>getConnection()</CODE> 
    * method by passing <B>password</B> as a parameter
    * 
    * <P>if you don't know or use a standard Password, try to use the {@link #DEFAULT_USER}
    * 
    * @see #DEFAULT_PASSWORD
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * @see #getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String) 
    * 
    * @return 
    *      <P> Database Password Registred - if not null
    * 
    *      <P> <CODE> null </CODE> - if the PASSWORD is null
    * 
    * @throws NullPointerException if the PASSWORD is null 
    */
    public static String getPASSWORD() {
        
        if(PASSWORD != null){
            
            return PASSWORD;
        }else{
            throw  NULL_POINTER_EXCEPTION;
        }
    }
}
