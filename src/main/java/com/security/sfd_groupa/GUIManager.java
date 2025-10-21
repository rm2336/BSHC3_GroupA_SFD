/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.security.sfd_groupa;

import java.util.ArrayList;

/**
 *
 * @author rokom
 * This class manages the Java frames associated with the application's GUI.
 * To add your JFrame, follow the below steps:
 * 1) Design the frame and name it somethingsomethingFrame
 * 2) Instantiate it in the SFD_GroupA file
 * 3) Invoke this class's addFrame method in the group file to incorporate it into the array list
 * 4) Declare a GUIManager object in your JFrame class file
 * 5) Define a setGUILiaison method in the JFrame class file which will 
 *    pass the GUIManager object in the SFD_GroupA file as a reference
 *    to the one associated with your frame.
 * 6) Run the above method in the SFD_GroupA.java code. Done!
 */
public class GUIManager {
    private ArrayList<javax.swing.JFrame> frameList;
    private PasswordManager passwordLiaison;
    
    public GUIManager() {
        frameList = new ArrayList<>();
    }
    
    public void setPasswordLiaison(PasswordManager manager) {
        passwordLiaison = manager;
    }
    
    public void addFrame(javax.swing.JFrame frame) {
        frameList.add(frame);
        System.out.println("Adding frame " + frame.getName() + "...");
    }
    
    public javax.swing.JFrame getFrame(String frameName) {
        for (javax.swing.JFrame frame: frameList) {
            if (frame.getName().equals(frameName)) {
                return frame;
            } 
        }
        return null;
    }
    
    public void setCurrentFrame(String frameName) {
        for (javax.swing.JFrame frame: frameList) {
            // find the frame that bears the provided name
            if (frame.getName().equals(frameName)) {
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
            else
                frame.setVisible(false);
        }
        // load database settings
        if (frameName.equals("databaseSettingsFrame")) {
            ((DatabaseSettingsFrame)getFrame("databaseSettingsFrame")).getHostTF().setText(passwordLiaison.getSql_hostName());
            ((DatabaseSettingsFrame)getFrame("databaseSettingsFrame")).getPortTF().setText(passwordLiaison.getSql_portNo());
            ((DatabaseSettingsFrame)getFrame("databaseSettingsFrame")).getDatabaseTF().setText(passwordLiaison.getSql_databaseName());
            ((DatabaseSettingsFrame)getFrame("databaseSettingsFrame")).getUserTF().setText(passwordLiaison.getSql_userName());
            ((DatabaseSettingsFrame)getFrame("databaseSettingsFrame")).getPasswordTF().setText(passwordLiaison.getSql_password());
        }
    }
}
