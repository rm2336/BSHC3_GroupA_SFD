/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.security.sfd_groupa;

//used imports
import java.io.Serializable;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import java.io.*;


/**
 *
 * @author tombyrne
 */
class PatientRecord implements Serializable { //This class is implementing serializable, patient data in the class can be converted to bytes to be stored in a file
    
    //patient data being declared
    public String ID;
    public String name;
    public String DOB;
    public int age;
    public String sex;
    public String gender;
    public String address;
    public String clinician;
    public String clinSrc;
    
    //getter methods being declared for each patient data variable
    public String getID() {return ID;}
    public String getName() {return name;}
    public String getDOB() {return DOB; }
    public int getAge() {return age;}
    public String getSex() {return sex;}
    public String getGender() {return gender;}
    public String getAddress() {return address;}
    public String getClinician() {return clinician; }
    public String getClinSrc() {return clinSrc; }
    
    
    //taking the patient record and converting it to a single string 
    @Override
    public String toString() {
        return "Patient Record(" + "ID:" + ID + 
                "name:" + name + 
                "DOB:" + DOB + 
                "age:" + age +
                "sex:" + sex +
                "gender:" + gender +
                "address:" + address +
                "clinician:" + clinician +
                "clinician source:" + clinSrc +
                ")";
    }
}
    
//main class that is performing DES encryption and decryption on the patient record   
public class EncryptRecord {
    public static void main(String[] args) {
        try{ //try clause
            
            //"Simplest way to encrypt a text file in Java" (https://stackoverflow.com/questions/27962116/simplest-way-to-encrypt-a-text-file-in-java)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES"); //create a DES key generator
            SecretKey myDESKey = keyGenerator.generateKey(); //create a key for DES encryption
        
            Cipher DESCipher = Cipher.getInstance("DES"); //create a cipher object for DES encryption
        
            //creating a patient record and initializing patient record variables
            PatientRecord record = new PatientRecord();
            record.ID = "001";
            record.name = "Tom Byrne";
            record.DOB = "2002-04-17";
            record.age = 23;
            record.sex = "Male";
            record.gender = "Male";
            record.address = "123, Bothar Bui";
            record.clinician = "Dr. Dre ";
            record.clinSrc = "Gorey Family Practice";
            byte[] text = record.toString().getBytes("UTF-8"); //converting the patient record to a byte array using UTF-8
            
            DESCipher.init(Cipher.ENCRYPT_MODE, myDESKey); //initializing cipher object to generate encrypted key
            byte[] textEncrypted = DESCipher.doFinal(text); //encrypting the text in the byte array
            
            //writing the ecrypted text to a file
            try (FileOutputStream fos = new FileOutputStream("PatientRecord_Encrypted.txt")){
                fos.write(textEncrypted);
            }
            
            //printing encrypted text
            String s = new String(textEncrypted);
            System.out.println(s);
            
            //initializing cipher object in using the same generated key to decrypt data
            DESCipher.init(Cipher.DECRYPT_MODE, myDESKey);
            byte[] textDecrypted = DESCipher.doFinal(textEncrypted);
            
            //print decrypted patient record
            s = new String(textDecrypted);
            System.out.println(s);
            
         //exception handler
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }
}
    
    
    
   
