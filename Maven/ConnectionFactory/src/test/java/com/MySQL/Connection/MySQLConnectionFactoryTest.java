/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MySQL.Connection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.MySQL.Connection.MySQLConnectionFactory.*;
import java.sql.Connection;

/**
 *
 * @author Samuel
 */
public class MySQLConnectionFactoryTest {
    
     

    @Test
    public void testGetConnection_2() throws Exception {
         
        assertEquals(Connection.class, getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD));
   
    }
    
}
