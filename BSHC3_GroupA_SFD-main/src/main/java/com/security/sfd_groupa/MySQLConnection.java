/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.security.sfd_groupa;

/**
 *
 * @author rokom
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
  
// Code obtained from Aiken API documentation

public class MySQLConnection {

    private String hostName;
    private String portNo;
    private String databaseName;
    private String userName;
    private String password;
    
    public MySQLConnection(String host, String port, String db, String user, String pw) {
        hostName = host;
        portNo = port;
        databaseName = db;
        userName = user;
        password = pw;

      }
    
    public boolean executeUpdate(String update) throws ClassNotFoundException {
        // JDBC allows to have nullable username and password
        if (hostName == null || portNo == null || databaseName == null) {
          System.out.println("Host, port, database information is required");
          return false;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        // run a test query
        try (final Connection connection =
                    DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNo + "/" + databaseName + "?sslmode=require", userName, password);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT version() AS version")) {

          while (resultSet.next()) {
            System.out.println("Version: " + resultSet.getString("version"));
          }
        } catch (SQLException e) {
          System.out.println("Connection failure.");
          e.printStackTrace();
        }
          
        // run the user-specified command
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (final Connection connection =
                    DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNo + "/" + databaseName + "?sslmode=require", userName, password);
             final Statement statement = connection.createStatement();
             ) {
          statement.executeUpdate(update);
          return true;
        } catch (SQLException e2) {
          System.out.println("Connection failure.");
          e2.printStackTrace();
            }
          return false;
        }

    public String executeQuery(String query, String searchValue) throws ClassNotFoundException {
        // JDBC allows to have nullable username and password
        if (hostName == null || portNo == null || databaseName == null) {
          System.out.println("Host, port, database information is required");
          return "";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        // run a test query
        try (final Connection connection =
                    DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNo + "/" + databaseName + "?sslmode=require", userName, password);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery("SELECT version() AS version")) {

          while (resultSet.next()) {
            System.out.println("Version: " + resultSet.getString("version"));
          }
        } catch (SQLException e) {
          System.out.println("Connection failure.");
          e.printStackTrace();
        }
          
        // run the user-specified command
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (final Connection connection =
                    DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNo + "/" + databaseName + "?sslmode=require", userName, password);
             final Statement statement = connection.createStatement();
             ) {
          System.out.println("Query: " + query);
          System.out.println("Search value: " + searchValue);
          ResultSet results =statement.executeQuery(query);
          System.out.println("Results: " + results);
          while (results.next()) {
              System.out.println(results.getString(searchValue));
              return results.getString(searchValue);
          }
          if (!results.next())
              return null;
        } catch (SQLException e2) {
          System.out.println("Connection failure.");
          e2.printStackTrace();
            }
          return "";
        }  
    
    public boolean testConnection() {
        try (final Connection connection = 
                DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + portNo + "/" + databaseName + "?sslmode=require", userName, password);
                 ) {
              return true;
            } catch (SQLException e2) {
              System.out.println("Connection failure.");
              e2.printStackTrace();
              return false;
                }    
            }   
    }
