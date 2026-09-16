/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

/**
 *
 * @author User 1
 */
public class Login {
    
    private String username;
    private String password;
    private String cellPhoneNumber;
            
    public Login(String username,String password){
        this.username=username;
        this.password=password;
        this.cellPhoneNumber=cellPhoneNumber;
    }
    
    public boolean checkUserName() {
        return username.contains("_")&& username.length()<=5; 
    }
public boolean checkPasswordComplexity() {
    boolean hasCapital= false;
    boolean hasNumber= false;
    boolean hasSpecialCharacter=false;
     
    if (password.length()<8) {
        return false;
    }
    for (int i = 0; i < password.length(); i++) {
        char currentCharacter= password.charAt(i);
        
        if (Character.isUpperCase(currentCharacter)){
            hasCapital= true;
        }
        
        if (Character.isDigit(currentCharacter)) {
            hasNumber = true;
        }
        
         if (!Character.isLetterOrDigit(currentCharacter)) {
        hasSpecialCharacter = true;
         }
    }
    
    return hasCapital && hasNumber && hasSpecialCharacter;
}
// Regex validation researched using:
// ICASA South African Numbering Plan - South Africa uses country code +27.
// Oracle Java documentation - String.matches() used for regex validation.
public boolean checkCellPhoneNumber(){
    return cellPhoneNumber.matches("\\+27\\\\d{9}");
}
}