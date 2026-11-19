/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
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

    // PART 3 - Finds the longest stored message
    public String findLongestMessage() {

        String longestMessage = "";

        for (int i = 0; i < storedMessages.length; i++) {

            if (storedMessages[i].length() > longestMessage.length()) {

                longestMessage = storedMessages[i];
            }
        }

        return longestMessage;
    }

    // PART 3 - Searches for a message using its ID
    public String searchByMessageID(String searchID) {

        for (int i = 0; i < messageIDs.length; i++) {

            if (messageIDs[i].equals(searchID)) {

                return "Recipient: " + recipients[i]
                        + "\nMessage: " + allMessages[i];
            }
        }

        return "Message not found.";
    }

    // PART 3 - Searches for all messages sent or stored for a recipient
    public String searchByRecipient(String searchRecipient) {

        String results = "";

        for (int i = 0; i < recipients.length; i++) {

            if (recipients[i].equals(searchRecipient)
                    && !messageStatuses[i].equals("Disregard")) {

                results = results + allMessages[i] + "\n";
            }
        }

        if (results.isEmpty()) {
            return "No messages found for this recipient.";
        }

        return results.trim();
    }

    // PART 3 - Removes an element from an array
    private String[] removeFromArray(String[] array, int index) {

        String[] newArray = new String[array.length - 1];

        int newIndex = 0;

        for (int i = 0; i < array.length; i++) {

            if (i != index) {

                newArray[newIndex] = array[i];
                newIndex++;
            }
        }

        return newArray;
    }

    // PART 3 - Deletes a message using its message hash
    public String deleteMessageByHash(String searchHash) {

        for (int i = 0; i < messageHashes.length; i++) {

            if (messageHashes[i].equalsIgnoreCase(searchHash)) {

                String deletedMessage = allMessages[i];
                String status = messageStatuses[i];

                // Permanently deletes stored messages from JSON
                if (status.equals("Stored")) {

                    if (!deleteStoredMessage(messageIDs[i])) {

                        return "Unable to delete stored message.";
                    }
                }

                // Finds the matching index in the status-specific array
                int statusIndex = 0;

                for (int j = 0; j < i; j++) {

                    if (messageStatuses[j].equals(status)) {
                        statusIndex++;
                    }
                }

                // Removes the message from the appropriate array
                if (status.equals("Sent")) {

                    sentMessages = removeFromArray(
                            sentMessages, statusIndex);

                } else if (status.equals("Stored")) {

                    storedMessages = removeFromArray(
                            storedMessages, statusIndex);

                } else if (status.equals("Disregard")) {

                    disregardedMessages = removeFromArray(
                            disregardedMessages, statusIndex);
                }

                // Removes related details from the parallel arrays
                allMessages = removeFromArray(allMessages, i);
                messageHashes = removeFromArray(messageHashes, i);
                messageIDs = removeFromArray(messageIDs, i);
                recipients = removeFromArray(recipients, i);
                senders = removeFromArray(senders, i);
                messageStatuses = removeFromArray(messageStatuses, i);

                return "Message: \"" + deletedMessage
                        + "\" successfully deleted.";
            }
        }

        return "Message not found.";
    }

    // PART 3 - Permanently removes a stored message from JSON
    // Adapted from Google Gson User Guide:
    // https://google.github.io/gson/UserGuide.html
    private boolean deleteStoredMessage(String messageID) {

        Path filePath = Path.of("messages.json");

        if (!Files.exists(filePath)) {
            return false;
        }

        Gson gson = new Gson();
        Message[] loadedMessages;

        try (FileReader reader = new FileReader("messages.json")) {

            loadedMessages = gson.fromJson(
                    reader, Message[].class);

        } catch (IOException | com.google.gson.JsonParseException e) {
            return false;
        }

        if (loadedMessages == null) {
            return false;
        }

        for (int i = 0; i < loadedMessages.length; i++) {

            if (loadedMessages[i].getMessageID().equals(messageID)) {

                Message[] updatedMessages
                        = new Message[loadedMessages.length - 1];

                int newIndex = 0;

                for (int j = 0; j < loadedMessages.length; j++) {

                    if (j != i) {

                        updatedMessages[newIndex] = loadedMessages[j];
                        newIndex++;
                    }
                }

                try (FileWriter writer = new FileWriter("messages.json")) {

                    gson.toJson(updatedMessages, writer);
                    return true;

                } catch (IOException e) {
                    return false;
                }
            }
        }

        return false;
    }

    // PART 3 - Generates a report of sent and stored messages
    public String generateMessageReport() {

        String report = "\nQUICKCHAT MESSAGE REPORT\n";

        String[] reportStatuses = {"Sent", "Stored"};

        for (int j = 0; j < reportStatuses.length; j++) {

            report = report + "\n"
                    + reportStatuses[j].toUpperCase()
                    + " MESSAGES\n";

            boolean found = false;

            for (int i = 0; i < allMessages.length; i++) {

                if (messageStatuses[i].equals(reportStatuses[j])) {

                    report = report
                            + "Message ID: " + messageIDs[i] + "\n"
                            + "Message Hash: " + messageHashes[i] + "\n"
                            + "Sender: " + senders[i] + "\n"
                            + "Recipient: " + recipients[i] + "\n"
                            + "Message: " + allMessages[i] + "\n"
                            + "Status: " + messageStatuses[i] + "\n"
                            + "------------------------------\n";

                    found = true;
                }
            }

            if (!found) {
                report = report + "No messages available.\n";
            }
        }

        return report;
    }

}
