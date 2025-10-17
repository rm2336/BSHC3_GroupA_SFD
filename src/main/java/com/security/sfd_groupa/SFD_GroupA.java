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

    public static void main(String[] args) {
        // Initialise GUI components
        MainFrame mainMenu = new MainFrame();
        CreateAccountFrame createAccountFrame = new CreateAccountFrame();
        
        // Initialise GUI manager and add the above frames
        GUIManager guiManager = new GUIManager();
        guiManager.addFrame(mainMenu);
        guiManager.addFrame(createAccountFrame);
        guiManager.setCurrentFrame("mainFrame");
        
        // Pass references of the GUI manager to the frames
        mainMenu.setGUILiaison(guiManager);
        createAccountFrame.setGUILiaison(guiManager);
        
        // Initialise the password manager
        PasswordManager passwordManager = new PasswordManager();
        createAccountFrame.setPasswordLiaison(passwordManager);
        mainMenu.setPasswordLiaison(passwordManager);
    }
}
