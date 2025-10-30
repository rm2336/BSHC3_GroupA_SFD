/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.security.sfd_groupa;

/**
 *
 * @author rokom
 * Security Fundamentals and Development, Group A
 * Owner of Repository: Roko Matanovic
 * Collaborators: Tom Brophy, Tom Byrne
 */
public class SFD_GroupA {

    public static void main(String[] args) throws ClassNotFoundException {
        // Initialise GUI components
        MainFrame mainMenu = new MainFrame();
        CreateAccountFrame createAccountFrame = new CreateAccountFrame();
        DatabaseSettingsFrame databaseSettingsFrame = new DatabaseSettingsFrame();
        
        // Initialise GUI manager and add the above frames
        GUIManager guiManager = new GUIManager();
        guiManager.addFrame(mainMenu);
        guiManager.addFrame(createAccountFrame);
        guiManager.addFrame(databaseSettingsFrame);
        guiManager.setCurrentFrame("mainFrame");
        
        // Pass references of the GUI manager to the frames
        mainMenu.setGUILiaison(guiManager);
        createAccountFrame.setGUILiaison(guiManager);
        databaseSettingsFrame.setGUILiaison(guiManager);
        
        // Initialise the password manager
        PasswordManager passwordManager = new PasswordManager();
        guiManager.setPasswordLiaison(passwordManager);
        createAccountFrame.setPasswordLiaison(passwordManager);
        mainMenu.setPasswordLiaison(passwordManager);
        databaseSettingsFrame.setPasswordLiaison(passwordManager);
        // Load stored SQL credentials if available
        passwordManager.readSQLCredentials();
        System.out.println("Host name: " + passwordManager.getSql_hostName());
        System.out.println("Port number: " + passwordManager.getSql_portNo());
        System.out.println("Database: " + passwordManager.getSql_databaseName());
        System.out.println("Username: " + passwordManager.getSql_userName());
        System.out.println("Password: " + passwordManager.getSql_password());
       
        // Initialise the EncryptRecord class
        EncryptRecord encryptor = new EncryptRecord();
        System.out.println(encryptor.toString());
    }
}
