/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.security.sfd_groupa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author tombyrne
 */
public class PatientRecordSQLCon {
    //url connection credentials
    private static final String connectionURL = "jdbc:mysql://avnadmin:AVNS_tUvfv--2cbySIKktzSk@mysql-cosantoir1-cosantoir1.e.aivencloud.com:24271/defaultdb?";
    private static final String username = "avnadmin";
    private static final String password ="AVNS_tUvfv--2cbySIKktzSk";
    public static void insertEncryptedRecord(String patientID, String textEncoded64) {//class taking encrypted data
        String insertSQL = "INSERT INTO Patient_Record (patientID, textEncrypted) VALUES (?, ?)";//sql query
        try{
            Connection conn = DriverManager.getConnection(connectionURL, username, password);//connecting to sql using credentials
            PreparedStatement statement = conn.prepareStatement(insertSQL);//prepares sql query
            conn.setAutoCommit(true);//committing query to sql
            
            //what data is sending 
            statement.setString(1, patientID);
            statement.setString(2, textEncoded64);
            
            int update = statement.executeUpdate();//update sqldb
            System.out.println("data inserted successfully");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
