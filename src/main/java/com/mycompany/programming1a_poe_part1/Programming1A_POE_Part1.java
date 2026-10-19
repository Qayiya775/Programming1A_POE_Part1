/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.programming1a_poe_part1;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author User 1
 */


import java.util.Scanner;
import java.util.ArrayList;

public class Programming1A_POE_Part1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

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

        // PART 2 - QUICKCHAT MENU
        System.out.println();
        System.out.println("Welcome to QuickChat.");

        int menuOption = 0;

        while (menuOption != 3) {

            System.out.println();
            System.out.println("Please choose an option:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show Recently Sent Messages");
            System.out.println("3) Quit");

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

                    } else if (messageChoice.equals("3")) {

                        if (message.isMessageValid()) {

                            System.out.println(
                                    message.storeMessage()
                            );

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

                System.out.println("Goodbye!");

            } else {

                System.out.println(
                        "Invalid option. Please choose 1, 2 or 3."
                );
            }
        }
    }
}
