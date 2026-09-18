/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author User 1
 */
public class LoginTest {
    
    @Test
    public void TestUsernameCorrectlyFormatted() {
        
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", 
        "Smith");
        assertTrue(login.checkUserName());
    }
    @Test
public void testUsernameIncorrectlyFormatted() {

    Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertFalse(login.checkUserName());
   
}

@Test
public void testPasswordCorrectlyFormatted() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertTrue(login.checkPasswordComplexity());
    
}

@Test
public void testPasswordIncorrectlyFormatted() {

    Login login = new Login("kyl_1", "password",
            "+27838968976", "Kyle", "Smith");

    assertFalse(login.checkPasswordComplexity());

}

@Test
public void testCellPhoneNumberCorrectlyFormatted() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertTrue(login.checkCellPhoneNumber());

}

@Test
public void testCellPhoneNumberIncorrectlyFormatted() {

    Login login = new Login("kyl_1", "password",
            "08966553", "Kyle", "Smith");

    assertFalse(login.checkCellPhoneNumber());
    
}
@Test
public void testLoginSuccessful() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
}

@Test
public void testLoginFailed() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertFalse(login.loginUser("kyle!!!!!!!", "password"));
}

@Test
public void testSuccesfulLoginMessage() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertEquals("Welcome Kyle, Smith it is great to see you again.", 
            login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));

}
@Test
public void testFailedLoginMessage() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertEquals("Username or password incorrect, please try again.",
            login.returnLoginStatus("kyle!!!!!!!", "password"));
}
@Test
public void testSuccessfulRegistrationMessage() {

    Login login = new Login("kyl_1", "Ch&&sec@ke99!",
            "+27838968976", "Kyle", "Smith");

    assertEquals("Username successfully captured.\n"
            + "Password successfully captured.\n"
            + "Cell number successfully captured.",
            login.registerUser());
}

}




