/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.security.sfd_groupa;



/**
 *
 * @author tombr
 */
public class CaesarUtils {
    public static String encryptPassword(String password, int shift) {
        StringBuilder encryptedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char shiftedChar = (char) (((c - base + shift) % 26) + base);
                encryptedPassword.append(shiftedChar);
            } else {
                encryptedPassword.append(c);
            }
        }
        return encryptedPassword.toString();
    }
    
    
    
    public static String decryptPassword(String encryptedPassword, int shift) {
        StringBuilder decryptedPassword = new StringBuilder();
        for (char c : encryptedPassword.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A'; // Determine base character
                char shiftedChar = (char) (((c - base - shift + 26) % 26) + base);  // Reverse the shift
                decryptedPassword.append(shiftedChar);
            } else {
                decryptedPassword.append(c);
            }
        }
        return decryptedPassword.toString();
    }
}
