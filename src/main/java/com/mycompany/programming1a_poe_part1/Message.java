/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.programming1a_poe_part1;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

       messageHash = messageHash.toUpperCase();
       return messageHash;

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
    
    // Checks whether a message is valid before sending
    public boolean isMessageValid() {
        
        boolean validRecipient = recipient != null
                && recipient.matches("\\+27\\d{9}");
        boolean validLength = messageText != null
                && !messageText.trim().isEmpty()
                && messageText.length() <= 250;
        
        return validRecipient && validLength;
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
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Returns the details of a sent message
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
    
    
    // Stores the message in a JSON file
    public String storeMessage() {

        if (!isMessageValid()) {
            return "Message cannot be stored. Invalid message details.";
        }

        String json = "{"
                + "\"messageID\":\"" + messageID + "\","
                + "\"messageNumber\":" + messageNumber + ","
                + "\"recipient\":\"" + recipient + "\","
                + "\"messageText\":\"" + escapeJson(messageText) + "\","
                + "\"messageHash\":\"" + messageHash + "\""
                + "}";

        try {
            Path filePath = Path.of("messages.json");

            String existingMessages = "";

            if (Files.exists(filePath)) {
                existingMessages = Files.readString(filePath).trim();
            }

            String updatedMessages;

            if (existingMessages.isEmpty()) {
                updatedMessages = "[\n" + json + "\n]";
            } else if (existingMessages.startsWith("[")
                    && existingMessages.endsWith("]")) {

                String contents = existingMessages.substring(
                        1, existingMessages.length() - 1
                ).trim();

                if (contents.isEmpty()) {
                    updatedMessages = "[\n" + json + "\n]";
                } else {
                    updatedMessages = "[\n" + contents
                            + ",\n" + json + "\n]";
                }

            } else {
                // Converts the existing single message into an array
                updatedMessages = "[\n" + existingMessages
                        + ",\n" + json + "\n]";
            }

            try (FileWriter writer = new FileWriter("messages.json")) {
                writer.write(updatedMessages);
            }

            return "Message successfully stored.";

        } catch (IOException e) {
            return "Error storing message: " + e.getMessage();
        }
    }


    // Handles special characters in JSON
    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }


}
