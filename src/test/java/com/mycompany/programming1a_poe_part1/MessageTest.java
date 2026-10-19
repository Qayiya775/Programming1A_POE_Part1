/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User 1
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

  
    
    // Tests that the message ID contains exactly 10 digits
    @Test
    public void testMessageID() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertTrue(message.checkMessageID());
    }

    // Tests that a valid recipient number is accepted
    @Test
    public void testValidRecipientCell() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    // Tests that an invalid recipient number is rejected
    @Test
    public void testInvalidRecipientCell() {

        Message message = new Message(
                1, "0838968976", "Hi Mike"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does "
                + "not contain an international code. Please correct "
                + "the number and try again.",
                message.checkRecipientCell()
        );
    }

    // Tests that a valid message length is accepted
    @Test
    public void testValidMessageLength() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }
        
    // Tests that a message exceeding 250 characters is rejected
    @Test
    public void testInvalidMessageLength() {

        String longMessage = "A".repeat(260);

        Message message = new Message(
                1, "+27838968976", longMessage
        );

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                message.checkMessageLength()
        );
    }
        
    // Tests that the message hash uses the correct format
    @Test
    public void testMessageHash() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        String hash = message.createMessageHash();

        assertTrue(hash.matches("\\d{2}:1:HIMIKE"));
    }
        
    // Tests that a valid message can be sent
    @Test
    public void testValidMessage() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertTrue(message.isMessageValid());
    }
    
    // Tests that an invalid message cannot be sent
    @Test
    public void testInvalidMessage() {

        Message message = new Message(
                1, "0838968976", "Hi Mike"
        );

        assertFalse(message.isMessageValid());
    }
    
    // Tests that an empty message is rejected
    @Test
    public void testEmptyMessage() {

        Message message = new Message(
                1, "+27838968976", ""
        );

        assertFalse(message.isMessageValid());
    }


    // Tests the Send Message option
    @Test
    public void testSendMessage() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage(1)
        );
    }

    // Tests the Disregard Message option
    @Test
    public void testDisregardMessage() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage(2)
        );
    }

    // Tests the Store Message option response
    @Test
    public void testStoreMessageOption() {

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage(3)
        );
    }

    // Tests that the total number of sent messages increases
    @Test
    public void testReturnTotalMessages() {

        int beforeSending = Message.returnTotalMessages();

        Message message = new Message(
                1, "+27838968976", "Hi Mike"
        );

        message.SentMessage(1);

        assertEquals(
                beforeSending + 1,
                Message.returnTotalMessages()
        );
    }

}
