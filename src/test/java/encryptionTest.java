/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import com.security.sfd_groupa.CaesarUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tombr
 */
public class encryptionTest {
    
    public encryptionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    
    @Test
    public void passwordEncryptionAndDecryption() {
        String password = "H0!m-Z6u?";
        StringBuilder encrypted = new StringBuilder();
        
        CaesarUtils.encryptPassword(password, 0);
        String encryptedPasswordOutput = CaesarUtils.encryptPassword(password, 2);
        System.out.println(encryptedPasswordOutput);
        
        String encryptedPassword = encryptedPasswordOutput;
        StringBuilder decrypted = new StringBuilder();
        
        CaesarUtils.decryptPassword(encryptedPasswordOutput, 0);
        String decryptedPasswordOutput = CaesarUtils.decryptPassword(encryptedPasswordOutput, 2);
        System.out.println(decryptedPasswordOutput);
    }
    
    
}
