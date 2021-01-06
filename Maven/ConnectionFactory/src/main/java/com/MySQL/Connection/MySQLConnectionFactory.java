/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

/**
 *
 * <P>This class aims to make the connection to the <B>MySQL</B> database in a simple way
 * 
 * @author Samuel
 * @version 1.0.0
 * @since Jan 2021
 */
public class MySQLConnectionFactory {
    
    private static final String DRIVER; 
    private static final String HOST;
    private static final String PORT;
    private static final String DATABASE;
    private static final String URL; 
    private static final String USER;
    private static final String PASSWORD;
    
    
    public static final String DEFAULT_USER = "root";
    public static final String DEFAULT_PASSWORD = "";
    public static final String DEFAULT_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DEFAULT_HOST = "localhost";
    public static final String DEFAULT_PORT = "3306";
}
