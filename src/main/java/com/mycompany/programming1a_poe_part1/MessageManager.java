/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

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
    
}
