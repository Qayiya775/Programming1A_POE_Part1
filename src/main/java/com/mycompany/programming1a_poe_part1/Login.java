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
    
    public Login(String username){
        this.username=username;
    }
    
    public boolean checkUserName() {
        return username.contains("_")&& username.length()<=5; 
    }
    
}
