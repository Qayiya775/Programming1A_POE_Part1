/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author User 1
 */
public class MessageManager {

    // PART 3 - Arrays for managing messages
    private String[] sentMessages = new String[0];
    private String[] disregardedMessages = new String[0];
    private String[] storedMessages = new String[0];
    private String[] messageHashes = new String[0];
    private String[] messageIDs = new String[0];

    // Supporting parallel arrays for searching and reporting
    private String[] allMessages = new String[0];
    private String[] recipients = new String[0];
    private String[] senders = new String[0];
    private String[] messageStatuses = new String[0];

    // Adds a new value to an existing array
    private String[] addToArray(String[] array, String value) {

        String[] newArray = new String[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        newArray[array.length] = value;

        return newArray;
    }

    // Adds message details to the appropriate arrays
    public void addMessage(Message message, int option) {

        if (option == 1) {
            sentMessages = addToArray(sentMessages,
                    message.getMessageText());

        } else if (option == 2) {
            disregardedMessages = addToArray(disregardedMessages,
                    message.getMessageText());

        } else if (option == 3) {
            storedMessages = addToArray(storedMessages,
                    message.getMessageText());

        } else {
            return;
        }

        messageHashes = addToArray(messageHashes,
                message.getMessageHash());

        messageIDs = addToArray(messageIDs,
                message.getMessageID());

        // Stores related message information at matching indexes
        allMessages = addToArray(allMessages,
                message.getMessageText());

        recipients = addToArray(recipients,
                message.getRecipient());

        // PART 3 - Populates the sender array
        senders = addToArray(senders, message.getSender());

        // Records the selected message status
        if (option == 1) {
            messageStatuses = addToArray(messageStatuses, "Sent");
        } else if (option == 2) {
            messageStatuses = addToArray(messageStatuses, "Disregard");
        } else if (option == 3) {
            messageStatuses = addToArray(messageStatuses, "Stored");
        }
    }

    // Temporarily checks that the arrays are populated
    public void displayArrayCounts() {

        System.out.println("Sent messages: " + sentMessages.length);
        System.out.println("Disregarded messages: " + disregardedMessages.length);
        System.out.println("Stored messages: " + storedMessages.length);
        System.out.println("Message hashes: " + messageHashes.length);
        System.out.println("Message IDs: " + messageIDs.length);
    }
    
    // PART 3 - Reads stored messages from the JSON file
    // Adapted from Google Gson User Guide:
    // https://google.github.io/gson/UserGuide.html

    public void loadStoredMessages() {

        Path filePath = Path.of("messages.json");

        if (!Files.exists(filePath)) {
            return;
        }

        try (FileReader reader = new FileReader("messages.json")) {

            Gson gson = new Gson();

            Message[] loadedMessages = gson.fromJson(
                    reader, Message[].class);

            if (loadedMessages != null) {

                for (int i = 0; i < loadedMessages.length; i++) {
                    addMessage(loadedMessages[i], 3);
                }
            }

        } catch (IOException | com.google.gson.JsonParseException e) {
            System.out.println("Error reading stored messages: "
                    + e.getMessage());
        }
    }
    // PART 3 - Displays the sender and recipient of stored messages

    public void displaySenderAndRecipient() {

        if (storedMessages.length == 0) {
            System.out.println("No stored messages available.");
            return;
        }

        System.out.println("\nSTORED MESSAGE DETAILS");

        for (int i = 0; i < allMessages.length; i++) {

            if (messageStatuses[i].equals("Stored")) {

                if (senders[i] == null || senders[i].isEmpty()) {
                    System.out.println("Sender: Not recorded");
                } else {
                    System.out.println("Sender: " + senders[i]);
                }

                System.out.println("Recipient: " + recipients[i]);
                System.out.println("------------------------------");
            }
        }
    }

}
