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
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

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
public class RemotePasswordTest {
    
    @Test
    public void testConnection() throws SQLException {
        String result = "";
        try {
        MySQLConnection connection = new MySQLConnection("", "", "", "", "");
        String username = "test";
        result = connection.executeQuery("SELECT Hash FROM Credentials WHERE Username = '" + username + "';", "Hash");
        assertNotEquals(result, null);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        // Verify if the password matches
        String password = "Roko1234";

        Argon2PasswordEncoder testEncoder = new Argon2PasswordEncoder(16, 32, 1, 10000, 10);
        // Run the hash method
        try {
            System.out.println("Hash value is: " + result);
            System.out.println(result.length());
            
            assertTrue(testEncoder.matches(password, result));
            
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
