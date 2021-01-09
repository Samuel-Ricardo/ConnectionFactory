/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.MySQL.Connection.MySQLConnectionFactory.*;
import com.mysql.cj.jdbc.ConnectionImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Samuel
 */
public class MySQLConnectionFactoryTest {

    @BeforeEach
    public void cleanFactory(){
        
        cleanFactory();
    }
    
    @Test
    public void SuccessTestConnection() throws Exception {
        
        Connection result = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        assertEquals(ConnectionImpl.class, result.getClass());
        
    }
    
    @Test
    public void SuccessTestGetURL() throws Exception {
        
        String url = getURL(DEFAULT_HOST, DEFAULT_PORT, "dbbiblio");
                
        assertEquals("jdbc:mysql://"+DEFAULT_HOST+":"+DEFAULT_PORT+"/dbbiblio?useTimezone=true&serverTimezone=UTC", url);
        
    }
    
    @Test
    public void InvalidDatabaseTest() throws Exception {
     
        try {

            Connection result = getConnection(DEFAULT_HOST, "3308", "INVALID_DATABASE", DEFAULT_USER, DEFAULT_PASSWORD);

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void InvalidHostTest() throws Exception {
     
        try {

            Connection result = getConnection("INVALID_HOST", "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void InvalidPortTest() throws Exception {
     
        try {

            Connection result = getConnection(DEFAULT_HOST, "INVALID_PORT", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void InvalidUserTest() throws Exception {
     
        try {

            Connection result = getConnection(DEFAULT_HOST, "3308", "dbbiblio", "INVALID_USER", DEFAULT_PASSWORD);

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void InvalidPasswordTest() throws Exception {
     
        try {

            Connection result = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, "INVALID_PASSWORD");

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void InvalidDriverTest() throws Exception {
     
        try {

            Connection result = getConnection("INVALID_DRIVER",DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, "INVALID_PASSWORD");

            assertEquals(null, result);
            
            fail("No Exception"); 

        } catch (RuntimeException ex) {

            assertEquals(RuntimeException.class, ex.getClass());
        }
    }
    
    @Test
    public void SuccessTestCloseConnection() throws Exception {
        
        Connection connection = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        closeConnection(connection);
        
        assertEquals(true, connection.isClosed());
    }
    
    @Test
    public void SuccessTestCloseConnectionAndPreparedStatement() throws Exception {
        
        Connection connection = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tb_book;");
        
        closeConnection(connection, statement);
        
        assertEquals(true, statement.isClosed() && connection.isClosed());
    }
    
    @Test
    public void SuccessTestCloseConnectionAndPreparedStatementAndResultSet() throws Exception {
        
        Connection connection = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM tb_book;");
        
        ResultSet result = statement.executeQuery();
        
        closeConnection(connection, statement, result);
        
        assertEquals(true, statement.isClosed() && connection.isClosed() && result.isClosed());
    }
    
    @Test
    public void SucssesTestGetDRIVER(){
        
        String driver = getDRIVER();
        
        assertEquals(driver, DEFAULT_DRIVER);
    }
    
    @Test
    public void SucssesTestGetDRIVER2(){
        
        Connection connection = getConnection(OLD_DEFAULT_DRIVER, DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        String driver = getDRIVER();
        
        assertEquals(driver, OLD_DEFAULT_DRIVER);
    }
    
    @Test
    public void FailTestGetDRIVERNull(){
        
        Connection connection = getConnection(null, DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        assertEquals(NullPointerException.class, getDRIVER());
        
    }
    
    @Test
    public void FailTestGetDRIVEREmpty(){
        
        Connection connection = getConnection("", DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        assertEquals(NullPointerException.class, getDRIVER());
        
    }
    
    @Test
    public void FailTestGetDRIVEREmpty2(){
        
        Connection connection = getConnection(" ", DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        assertEquals(NullPointerException.class, getDRIVER());
        
    }
   
}
