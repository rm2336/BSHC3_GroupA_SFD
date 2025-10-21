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
public class MySQLAssertDuplicateUsernameTest {
    
    @Test
    public void testConnection() throws SQLException {
        String result = "";
        try {
        MySQLConnection connection = new MySQLConnection("", "", "", "", "");
        String username = "test1234";
        result = connection.executeQuery("SELECT Username FROM Credentials WHERE Username = '" + username + "';", "Username");
        assertEquals(result, null);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
