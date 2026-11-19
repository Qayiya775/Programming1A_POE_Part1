/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.programming1a_poe_part1;

/**
 *
 * @author User 1
 */
import java.util.Scanner;
import java.util.ArrayList;

public class Programming1A_POE_Part1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // PART 3 - Manages message arrays
        MessageManager messageManager = new MessageManager();

        // Stores successfully sent messages
        ArrayList<Message> sentMessages = new ArrayList<>();

        // Tracks message numbers across the program
        int messageNumber = 0;

        // PART 1 - REGISTRATION
        System.out.println("REGISTRATION");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter your username: ");
        String username = input.nextLine();

        System.out.print("Enter your password: ");
        String password = input.nextLine();

        System.out.print("Enter your cell phone number: ");
        String cellPhoneNumber = input.nextLine();

        Login login = new Login(
                username, password, cellPhoneNumber,
                firstName, lastName
        );

        System.out.println(login.registerUser());

        // Only allow login after successful registration
        if (!login.checkUserName()
                || !login.checkPasswordComplexity()
                || !login.checkCellPhoneNumber()) {

            return;
        }

        System.out.println();
        System.out.println("LOGIN");

        System.out.print("Enter your username: ");
        String enteredUsername = input.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine();

        System.out.println(login.returnLoginStatus(
                enteredUsername, enteredPassword
        ));

        // Stop if login is unsuccessful
        if (!login.loginUser(enteredUsername, enteredPassword)) {
            return;
        }

        // PART 3 - Loads previously stored messages
        messageManager.loadStoredMessages();

        // PART 2 - QUICKCHAT MENU
        System.out.println();
        System.out.println("Welcome to QuickChat.");

        int menuOption = 0;

        while (menuOption != 3) {

            System.out.println();
            System.out.println("1) Send Messages");
            System.out.println("2) Show Recently Sent Messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");

            System.out.print("Enter your choice: ");
            String choice = input.nextLine();

            if (choice.equals("1")) {

                menuOption = 1;
                System.out.println("Send Messages selected.");

                System.out.print(
                        "How many messages would you like to send? "
                );

                String numberEntered = input.nextLine();
                int numberOfMessages;

                // Prevent the program from crashing on invalid input
                try {
                    numberOfMessages = Integer.parseInt(numberEntered);
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Please enter a valid whole number."
                    );
                    continue;
                }

                if (numberOfMessages <= 0) {
                    System.out.println(
                            "Please enter a number greater than zero."
                    );
                    continue;
                }

                // Repeat for the selected number of messages
                for (int i = 1; i <= numberOfMessages; i++) {

                    messageNumber++;

                    System.out.println();
                    System.out.println(
                            "Message " + i + " of " + numberOfMessages
                    );

                    System.out.print(
                            "Enter recipient cell phone number: "
                    );
                    String recipient = input.nextLine();

                    System.out.print("Enter your message: ");
                    String messageText = input.nextLine();

                    // Create the message object
                    Message message = new Message(
                            messageNumber, recipient, messageText
                    );

                    // PART 3 - Captures the sender's cellphone number
                    message.setSender(cellPhoneNumber);

                    // Display validation results
                    System.out.println(message.checkRecipientCell());
                    System.out.println(message.checkMessageLength());

                    System.out.println();
                    System.out.println(
                            "What would you like to do with this message?"
                    );
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message");

                    System.out.print("Enter your option: ");
                    String messageChoice = input.nextLine();

                    if (messageChoice.equals("1")) {

                        if (message.isMessageValid()) {

                            System.out.println(
                                    message.SentMessage(1)
                            );

                            sentMessages.add(message);

                            // Adds the sent message to the arrays
                            messageManager.addMessage(message, 1);

                            // Display the full message details
                            System.out.println(
                                    message.printMessages()
                            );

                        } else {

                            System.out.println(
                                    "Message not sent. Please correct "
                                    + "the recipient or message length."
                            );
                        }

                    } else if (messageChoice.equals("2")) {

                        System.out.println(
                                message.SentMessage(2)
                        );

                        // Adds the disregarded message to the arrays
                        messageManager.addMessage(message, 2);

                    } else if (messageChoice.equals("3")) {

                        if (message.isMessageValid()) {

                            String storageResult = message.storeMessage();
                            System.out.println(storageResult);

                            // Adds the message only if storage was successful
                            if (storageResult.equals("Message successfully stored.")) {
                                messageManager.addMessage(message, 3);
                            }

                        } else {

                            System.out.println(
                                    "Message not stored. Please correct "
                                    + "the recipient or message length."
                            );
                        }

                    } else {

                        System.out.println(
                                "Invalid option. Please try again."
                        );
                    }
                }

            } else if (choice.equals("2")) {

                menuOption = 2;

                // Part 2 requires this feature to be unavailable
                System.out.println("Coming Soon.");

            } else if (choice.equals("3")) {

                menuOption = 3;

                System.out.println(
                        "Total messages sent: "
                        + Message.returnTotalMessages()
                );

                messageManager.displayArrayCounts();

                System.out.println("Goodbye!");

            } else if (choice.equals("4")) {

                menuOption = 4;

                // PART 3 - Stored Messages menu
                System.out.println();
                System.out.println("STORED MESSAGES");
                System.out.println("1) Display sender and recipient");
                System.out.println("2) Display longest stored message");
                System.out.println("3) Search by message ID");
                System.out.println("4) Search by recipient");
                System.out.println("5) Delete message using message hash");
                System.out.println("6) Display message report");
                System.out.println("7) Return to main menu");

                // PART 3 - Handles the stored messages menu selection
                System.out.print("Enter your choice: ");
                String storedChoice = input.nextLine();

                if (storedChoice.equals("1")) {

                    messageManager.displaySenderAndRecipient();

                } else if (storedChoice.equals("2")) {

                    // PART 3 - Displays the longest stored message
                    String longestMessage = messageManager.findLongestMessage();

                    if (longestMessage.isEmpty()) {
                        System.out.println("No stored messages available.");
                    } else {
                        System.out.println("Longest stored message:");
                        System.out.println(longestMessage);
                    }

                } else if (storedChoice.equals("3")) {

                    // PART 3 - Searches by message ID
                    System.out.print("Enter message ID: ");
                    String searchID = input.nextLine();

                    System.out.println(
                            messageManager.searchByMessageID(searchID)
                    );

                } else if (storedChoice.equals("4")) {

                    // PART 3 - Searches by recipient
                    System.out.print("Enter recipient cell phone number: ");
                    String searchRecipient = input.nextLine();

                    System.out.println(
                            messageManager.searchByRecipient(searchRecipient)
                    );

                } else if (storedChoice.equals("7")) {

                    System.out.println("Returning to main menu.");

                } else if (storedChoice.equals("5")
                        || storedChoice.equals("6")) {

                    System.out.println("Coming Soon.");

                } else {

                    System.out.println("Invalid option. Please try again.");
                }

            } else {

                System.out.println(
                        "Invalid option. Please choose 1, 2, 3 or 4."
                );
            }
        }
    }
}
