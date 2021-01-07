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

/**
 *
 * @author Samuel
 */
public class MySQLConnectionFactoryTest {

    @Test
    public void testGetConnectionSuccess() throws Exception {
        
        Connection result = getConnection(DEFAULT_HOST, "3308", "dbbiblio", DEFAULT_USER, DEFAULT_PASSWORD);
        
        assertEquals(ConnectionImpl.class, result.getClass());
        
    }
    
}
