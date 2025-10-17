/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.security.sfd_groupa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

/**
 *
 * @author rokom
 * This class is tasked with the secure storage and hashing of user-specified passwords.
 * Code inspired by: https://www.baeldung.com/java-argon2-hashing
 */
public class PasswordManager {
    private ArrayList<Credentials> passwords;
    private Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 10000, 10);
    
    public String computePassHash(String rawPass) {
        /**
         * 1st argument = time cost in iterations
         * 2nd argument = hash length in bytes
         * 3rd argument = number of threads
         * 4th argument = memory cost in KB
         * 5th argument = iterations
         */
        String hash = encoder.encode(rawPass);
        return hash;
    }
    
    public void storeCredentials(String username, String hash) {
        File f = new File("passwords.txt");
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(username);
            fw.write("\n");
            fw.write(hash);
            fw.write("\n");
            fw.close();
            System.out.println("Writing credentials...");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean assertCredentialsCorrect(String username, String password) {
        File f = new File("passwords.txt");
        String line = "";
        String nextLine = "";
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.equals(username)) {
                    nextLine = scan.nextLine();
                    if (encoder.matches(password, nextLine))
                        return true;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean assertUsernameTaken(String username) {
        File f = new File("passwords.txt");
        String line = "";
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                line = scan.nextLine();
                if (line.equals(username)) 
                    return true;
                else
                    // skip the password line to avoid confusion
                    line = scan.nextLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;       
    }
}
