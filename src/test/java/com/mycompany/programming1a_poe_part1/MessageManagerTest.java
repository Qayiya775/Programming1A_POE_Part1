/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programming1a_poe_part1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import com.google.gson.Gson;

/**
 *
 * @author User 1
 */
public class MessageManagerTest {

    private MessageManager messageManager;
    @TempDir
    Path tempDir;

    private Message message1;
    private Message message2;
    private Message message3;
    private Message message4;
    private Message message5;

    // Prepares the assessment test data before each test
    @BeforeEach
    public void setUp() throws IOException {

        Path testFile = tempDir.resolve("messages.json");

        messageManager = new MessageManager(testFile.toString());

        message1 = new Message(1, "+27834557896",
                "Did you get the cake?");

        message2 = new Message(2, "+27838884567",
                "Where are you? You are late! I have asked you to be on time.");

        message3 = new Message(3, "+27834484567",
                "Yohoooo, I am at your gate.");

        message4 = new Message(4, "0838884567",
                "It is dinner time !");

        message5 = new Message(5, "+27838884567",
                "Ok, I am leaving without you.");

        // Populates the arrays using the prescribed message statuses
        messageManager.addMessage(message1, 1);
        messageManager.addMessage(message2, 3);
        messageManager.addMessage(message3, 2);
        messageManager.addMessage(message4, 1);
        messageManager.addMessage(message5, 3);

        // Prepares stored messages in a temporary JSON file
        Gson gson = new Gson();

        Message[] storedTestMessages = {message2, message5};

        Files.writeString(testFile, gson.toJson(storedTestMessages));
    }

    // PART 3 - Tests the populated sent messages array
    @Test
    public void testSentMessagesArray() {

        String[] actualMessages = messageManager.getSentMessages();

        assertEquals(2, actualMessages.length);

        assertEquals("Did you get the cake?", actualMessages[0]);

        assertEquals("It is dinner time !", actualMessages[1]);
    }

    // PART 3 - Tests the longest stored message
    @Test
    public void testLongestMessage() {

        String expectedMessage
                = "Where are you? You are late! I have asked you to be on time.";

        String actualMessage = messageManager.findLongestMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    // PART 3 - Tests searching for a message by its ID
    @Test
    public void testSearchByMessageID() {

        String expectedResult
                = "Recipient: 0838884567\n"
                + "Message: It is dinner time !";

        String actualResult = messageManager.searchByMessageID(
                message4.getMessageID());

        assertEquals(expectedResult, actualResult);
    }

    // PART 3 - Tests searching for messages by recipient
    @Test
    public void testSearchByRecipient() {

        String expectedResult
                = "Where are you? You are late! I have asked you to be on time.\n"
                + "Ok, I am leaving without you.";

        String actualResult = messageManager.searchByRecipient(
                "+27838884567");

        assertEquals(expectedResult, actualResult);
    }

    // PART 3 - Tests deleting a message using its hash
    @Test
    public void testDeleteMessageByHash() {

        String expectedResult
                = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";

        String actualResult = messageManager.deleteMessageByHash(
                message2.getMessageHash());

        assertEquals(expectedResult, actualResult);

        // Checks that the deleted message is no longer in the arrays
        assertEquals("Ok, I am leaving without you.",
                messageManager.searchByRecipient("+27838884567"));

        // Checks that the message is permanently deleted from JSON
        MessageManager reloadedManager = new MessageManager(
                tempDir.resolve("messages.json").toString());

        reloadedManager.loadStoredMessages();

        assertEquals("Ok, I am leaving without you.",
                reloadedManager.searchByRecipient("+27838884567"));

        assertEquals("Message not found.",
                reloadedManager.searchByMessageID(message2.getMessageID()));
    }

    // PART 3 - Tests the generated message report
    @Test
    public void testMessageReport() {

        String report = messageManager.generateMessageReport();

        // Checks the sent messages
        assertEquals(true, report.contains("SENT MESSAGES"));

        assertEquals(true, report.contains(
                "Message Hash: " + message1.getMessageHash()));

        assertEquals(true, report.contains(
                "Recipient: +27834557896"));

        assertEquals(true, report.contains(
                "Message: Did you get the cake?"));

        assertEquals(true, report.contains(
                "Message Hash: " + message4.getMessageHash()));

        assertEquals(true, report.contains(
                "Recipient: 0838884567"));

        assertEquals(true, report.contains(
                "Message: It is dinner time !"));

        // Checks the stored messages
        assertEquals(true, report.contains("STORED MESSAGES"));

        assertEquals(true, report.contains(message2.getMessageText()));

        assertEquals(true, report.contains(message5.getMessageText()));

        // Checks that disregarded messages are excluded
        assertEquals(false, report.contains(message3.getMessageText()));
    }

}
