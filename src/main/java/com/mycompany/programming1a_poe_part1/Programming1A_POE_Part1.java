/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programming1a_poe_part1;

import java.util.Scanner;

/**
 *
 * @author User 1
 */
public class Programming1A_POE_Part1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("REGISTRATION");
        
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter your username: ");
        String username= input.nextLine();
        
        System.out.print("Enter your password: ");
        String password = input.nextLine();
        
        System.out.print("Enter your cell phone number: ");
        String cellPhoneNumber = input.nextLine();
        
        Login login = new Login(username, password, cellPhoneNumber, firstName, 
                lastName );
        
        System.out.println(login.registerUser());
        
        System.out.println();
        System.out.println("LOGIN");
        
        System.out.print("Enter your username: ");
        String enteredUsername = input.nextLine();
        
        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine();
        
        System.out.println(login.returnLoginStatus(enteredUsername, enteredPassword));
    }
   
}
