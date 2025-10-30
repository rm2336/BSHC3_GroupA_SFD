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
import java.util.Base64;


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
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); //create a AES key generator
            SecretKey myAESKey = keyGenerator.generateKey(); //create a key for AES encryption
            keyGenerator.init(128); //setting the key size to 128 bits using the AES encryption
            Cipher AESCipher = Cipher.getInstance("AES"); //create a cipher object for AES encryption
        
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
            
            AESCipher.init(Cipher.ENCRYPT_MODE, myAESKey); //initializing cipher object to generate encrypted key
            byte[] textEncrypted = AESCipher.doFinal(text); //encrypting the text in the byte array
            
            
            //printing encrypted text as base 64 encoded
            String textEncoded64 = java.util.Base64.getEncoder().encodeToString(textEncrypted);
            System.out.println("Encrypted record:");
            System.out.println(textEncoded64);
            
            //writing the ecrypted text to a file 
            try (FileWriter textFile = new FileWriter("PatientRecord_Encrypted_AES.txt")){
                textFile.write(textEncoded64);
            }
            
            
            //initializing cipher object in using the same generated key to decrypt data
            AESCipher.init(Cipher.DECRYPT_MODE, myAESKey);
            byte[] textDecrypted = AESCipher.doFinal(textEncrypted);
            
            //print decrypted patient record
             String textDecoded64 = new String(textDecrypted);
            System.out.println(textDecoded64);
            
            PatientRecordSQLCon.insertEncryptedRecord(record.ID, textEncoded64);
            
            
            
         //exception handler
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }

    public static String encryptRecord(PatientRecord record) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey myAESKey = keyGenerator.generateKey();
        Cipher AESCipher = Cipher.getInstance("AES");
        AESCipher.init(Cipher.ENCRYPT_MODE, myAESKey);
        
        byte[] text = record.toString().getBytes("UTF-8");
        byte[] textEncrypted = AESCipher.doFinal(text);
        return Base64.getEncoder().encodeToString(textEncrypted);
    }
}
    
    
    
   
