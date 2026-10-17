/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;
import java.util.Random;
/**
 *
 * @author User 1
 */
public class Message {
    
    // Stores the information for each message
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    // Tracks the total number of messages successfully sent
private static int totalMessages = 0;
    
    
    // Constructor to create a new message
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();

    }

    // Generates a random 10-digit message ID
    public String generateMessageID() {
        Random random = new Random();

        long number = 1000000000L
                + random.nextLong(9000000000L);

        messageID = String.valueOf(number);

        return messageID;
    }

    // Checks that the message ID contains exactly 10 digits
    public boolean checkMessageID() {
        return messageID != null
                && messageID.matches("\\d{10}");
    }


    // Checks whether the recipient has a valid cellphone number
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("\\+27\\d{9}")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does "
                    + "not contain an international code. Please correct "
                    + "the number and try again.";
        }
    }

    // Creates the message hash using the ID, number and message words
    public String createMessageHash() {
        if (messageID == null || messageText == null
                || messageText.trim().isEmpty()) {
            return "";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = messageID.substring(0, 2)
                + ":" + messageNumber + ":"
                + firstWord + lastWord;

        return messageHash.toUpperCase();
    }

    // Checks that the message does not exceed 250 characters
    public String checkMessageLength() {
        if (messageText == null) {
            return "Please enter a message of less than 250 characters.";
        }

        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int extraCharacters = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + extraCharacters
                    + "; please reduce the size.";
        }
    }

    // Returns the response for the selected message option
    public String SentMessage(int option) {
        
if (option == 1) {
    totalMessages++;
    return "Message successfully sent.";


        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option. Please try again.";
        }
    }

    
    // Returns the total number of messages sent
    public int returnTotalMessages() {
        return totalMessages;
    }

    // Returns the details of a sent message
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

}
