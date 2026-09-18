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
    private String firstName;
    private String lastName;
            
    public Login(String username,String password,String cellPhoneNumber,
            String firstName, String lastName){
        
        this.username=username;
        this.password=password;
        this.cellPhoneNumber=cellPhoneNumber;
        this.firstName=firstName;
        this.lastName=lastName;
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
// Cellphone number validation uses a regular expression.
// ICASA Numbering Plan was consulted for South African number length.
// Oracle Java documentation was consulted for the String.matches() method.
// Sources:
// ICASA: https://www.icasa.org.za/pages/numbering
// Oracle: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html

public boolean checkCellPhoneNumber(){
    return cellPhoneNumber.matches("\\+27\\d{9}");
}
public String registerUser() {
    if (!checkUserName()) {
    return "Username is not correctly formatted; please ensure that your "
            + "username contains an underscore and is no more than five "
            + "characters in length.";
    }
    
    if (!checkPasswordComplexity()) {
    return "Password is not correctly formatted; please ensure that the "
            + "password contains at least eight characters, a capital letter, "
            + "a number, and a special character.";
    }
    
    if (!checkCellPhoneNumber()) {
    return "Cellphone number incorrectly formatted or does not contain "
            + "international code.";
    }
    
    return "Username successfully captured.\n"
        + "Password successfully captured.\n"
        + "Cell number successfully captured.";
}

public boolean loginUser(String enteredUsername, String enteredPassword) {
    return username.equals(enteredUsername)
            && password.equals(enteredPassword);
}

public String returnLoginStatus(String enteredUserName,String enteredPassword){
    
    if (loginUser(enteredUserName, enteredPassword)){
    return "Welcome " + firstName + ", " + lastName 
            + " it is great to see you again.";
    
    }else{
        return "Username or password incorrect, please try again.";
    }
}

}

