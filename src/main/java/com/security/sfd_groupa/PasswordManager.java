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
import javax.swing.JOptionPane;
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
    private String sql_hostName = "";
    private String sql_portNo = "";
    private String sql_databaseName = "";
    private String sql_userName = "";
    private String sql_password = "";
    
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
    
    public boolean storeCredentials(String username, String hash) {
//        File f = new File("passwords.txt");
//        try {
//            FileWriter fw = new FileWriter(f, true);
//            fw.write(username);
//            fw.write("\n");
//            fw.write(hash);
//            fw.write("\n");
//            fw.close();
//            System.out.println("Writing credentials...");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        MySQLConnection sqlCon = new MySQLConnection(sql_hostName, sql_portNo, sql_databaseName, sql_userName, sql_password);
        try {
            if (sqlCon.executeUpdate("INSERT INTO Credentials (Username, Hash) VALUES ('" + username + "', '" + hash + "')"))
                return true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    
    public boolean assertCredentialsCorrect(String username, String password) {
//        File f = new File("passwords.txt");
//        String line = "";
//        String nextLine = "";
//        try {
//            Scanner scan = new Scanner(f);
//            while (scan.hasNextLine()) {
//                line = scan.nextLine();
//                if (line.equals(username)) {
//                    nextLine = scan.nextLine();
//                    if (encoder.matches(password, nextLine))
//                        return true;
//                }
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        MySQLConnection sqlCon = new MySQLConnection(sql_hostName, sql_portNo, sql_databaseName, sql_userName, sql_password);
        String hash = "";
        try {
            hash = sqlCon.executeQuery("SELECT Hash FROM Credentials WHERE Username = '" + username + "';", "Hash");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        if (encoder.matches(password, hash))
            return true;
        return false;
    }
    
    public boolean assertUsernameTaken(String username) {
//        File f = new File("passwords.txt");
//        String line = "";
//        try {
//            Scanner scan = new Scanner(f);
//            while (scan.hasNextLine()) {
//                line = scan.nextLine();
//                if (line.equals(username)) 
//                    return true;
//                else
//                    // skip the password line to avoid confusion
//                    line = scan.nextLine();
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
        MySQLConnection sqlCon = new MySQLConnection(sql_hostName, sql_portNo, sql_databaseName, sql_userName, sql_password);
        try {
        if (username.equals(sqlCon.executeQuery("SELECT Username FROM Credentials WHERE Username = " + "'" + username + "';", "Username")))
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return false;       
    }

    public void readSQLCredentials() {
        // check if SQL settings have been saved on the user's PC
        File f = new File("sqlsettings.txt");
        String line = "";
        int counter = 0;
        if (f.exists()) {
            try {
                System.out.println("Loading credentials...");
                Scanner scan = new Scanner(f);
                while (scan.hasNextLine()) {
                    line = scan.nextLine();
                    counter++;
                    switch (counter) {
                        case 1:
                            sql_hostName = line;
                            break;
                        case 2:
                            sql_portNo = line;
                            break;
                        case 3:
                            sql_databaseName = line;
                            break;
                        case 4:
                            sql_userName = line;
                            break;
                        case 5:
                            sql_password = line;
                            break;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());        
            }
        } else
            System.out.println("Failed to detect credentials...");
        MySQLConnection conn = new MySQLConnection(sql_hostName, sql_portNo, sql_databaseName, sql_userName, sql_password);
        if (!conn.testConnection()) {
            JOptionPane.showMessageDialog(null, "Warning: No valid database settings detected. You will be unable to avail"
                    + " of the application's functionality.");
        }
    }
    
    public String getSql_hostName() {
        return sql_hostName;
    }

    public void setSql_hostName(String sql_hostName) {
        this.sql_hostName = sql_hostName;
    }

    public String getSql_portNo() {
        return sql_portNo;
    }

    public void setSql_portNo(String sql_portNo) {
        this.sql_portNo = sql_portNo;
    }

    public String getSql_databaseName() {
        return sql_databaseName;
    }

    public void setSql_databaseName(String sql_databaseName) {
        this.sql_databaseName = sql_databaseName;
    }

    public String getSql_userName() {
        return sql_userName;
    }

    public void setSql_userName(String sql_userName) {
        this.sql_userName = sql_userName;
    }

    public String getSql_password() {
        return sql_password;
    }

    public void setSql_password(String sql_password) {
        this.sql_password = sql_password;
    }
    
    
}
