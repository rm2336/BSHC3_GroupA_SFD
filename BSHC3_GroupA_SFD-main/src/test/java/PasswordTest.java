/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

/**
 *
 * @author rokom
 * This JUnit class tests the Argon2 hash method
 */
public class PasswordTest {
    
    public PasswordTest() {
    }

    @Test
    public void testHashMethod() {
        String password = "Roko1234";

        Argon2PasswordEncoder testEncoder = new Argon2PasswordEncoder(16, 32, 1, 10000, 10);
        // Run the hash method
        try {
            String hash = testEncoder.encode(password);
            System.out.println("The hash sequence for the given password is: " + hash);
            assertTrue(testEncoder.matches(password, hash));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
