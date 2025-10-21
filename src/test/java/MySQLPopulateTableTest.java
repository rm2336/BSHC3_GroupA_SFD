/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.security.sfd_groupa.MySQLConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 *
 * @author rokom
 */
public class MySQLPopulateTableTest {
    
    @Test
    public void testConnection() throws SQLException {
        try {
        MySQLConnection connection = new MySQLConnection("", "", "", "", "");
        assertTrue(connection.executeUpdate("INSERT INTO Credentials (Username, Hash) VALUES ('test', '$argon2id$v=19$m=10000,t=10,p=1$QLpaCa8oA5iwwdKJC3cWWg$DCnq1kG2jM7hSFi1RcGG+UkLWQniL72+JW6dDEMPeE8')"));
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
