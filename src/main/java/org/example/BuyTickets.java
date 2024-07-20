package org.example;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class BuyTickets {
    //////////////////////// DO NOT EDIT ///////////////////////////////////

    public static class IllegalAmountException extends Exception {

        private static final long serialVersionUID = -6962468679528648650L;

        public IllegalAmountException(String amount) {
            super("BuyTickets$IllegalAmountException: You entered " + amount + ". Please enter a valid Dollar amount (e.g $100)");
        }
    }

    public static class IllegalSectionException extends Exception {

        private static final long serialVersionUID = -1110633430750774293L;

        public IllegalSectionException(String section) {
            super("You entered '" + section + "'. Please enter a valid section (LB,UB1,UB2)");
        }
    }

    //////////////////////// DO NOT EDIT ///////////////////////////////////
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String amount = "";
        String section = "";
        double cashAmount = 0;
        boolean validAmount = false;

        // Validate amount input
        System.out.println("Welcome! Please input the Cash amount in Dollars (e.g $100)");
        // Validate amount input
        while (validAmount == false) {
            try {

                if (input.hasNext()) {
                    amount = input.next();
                    if (amount.equals("exit")) {
                        System.out.println("Thank you and Goodbye.");
                        return;
                    }
                    if (amount.charAt(0) != '$') {
                        throw new IllegalAmountException(amount);
                    }
                    cashAmount = Double.parseDouble(amount.substring(1));
                    validAmount = true;
                }
            } catch (IllegalAmountException e) {
                System.out.println(e.getMessage());
                input.nextLine(); // Clear the buffer
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid Dollar amount (e.g $100).");
                input.nextLine(); // Clear the buffer
            }
        }

        // Validate section input
        System.out.println("Please enter your sitting position.");
        System.out.println("For Lower Bowl enter LB");
        System.out.println("For Upper Bowl (Sections CC-II) enter UB1");
        System.out.println("For Upper Bowl (Sections AA-BB, JJ-LL) enter UB2");

        while (true) {
            try {
                if (input.hasNext()) {
                    section = input.next();
                    if (section.equals("exit")) {
                        System.out.println("Thank you and Goodbye.");
                        return;
                    }
                    if (!section.equals("LB") && !section.equals("UB1") && !section.equals("UB2")) {
                        throw new IllegalSectionException(section);
                    }
                    break;
                }
            } catch (IllegalSectionException e) {
                System.out.println(e.getMessage());
            }
        }

        // Calculate and print the number of tickets and change
        int numTickets = calculateNumTickets(cashAmount, section);
        printNumTickets(cashAmount, numTickets, section);
    }

    public static Integer calculateNumTickets(double amount, String section) {
        int numTickets = 0;
        switch (section) {
            case "LB":
                numTickets = (int) (amount / 130);
                break;
            case "UB1":
                numTickets = (int) (amount / 110);
                break;
            case "UB2":
                numTickets = (int) (amount / 95);
                break;
        }
        return numTickets;
    }
    //////////////////////// DO NOT EDIT. Check to gain understanding of the program.
    // /////////////////

    /**
     * Outputs the number of tickets the user will be able to buy for a given amount.
     *
     * @param amount     How much the user is spending on tickets.
     * @param numTickets The total number of tickets the user can buy.
     * @param section    String label for sitting section in stadium.
     */
    private static void printNumTickets(double amount, int numTickets, String section) {


        if (numTickets == 1) {
            System.out.println("You got " + numTickets + " Ticket!");
        } else {
            System.out.println("You got " + numTickets + " Tickets!");
        }

        if (section.toUpperCase().equals("LB")) {
            System.out.println("Your change is: $" + Math.round(amount - numTickets * 130));
        } else if (section.toUpperCase().equals("UB1")) {
            System.out.println("Your change is: $" + Math.round(amount - numTickets * 110));
        } else {
            System.out.println("Your change is: $" + Math.round(amount - numTickets * 95));
        }
    }
}
