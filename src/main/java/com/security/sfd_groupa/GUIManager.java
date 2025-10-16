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
    
    public GUIManager() {
        frameList = new ArrayList<>();
    }
    
    public void addFrame(javax.swing.JFrame frame) {
        frameList.add(frame);
        System.out.println("Adding frame " + frame.getName() + "...");
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
    }
}
