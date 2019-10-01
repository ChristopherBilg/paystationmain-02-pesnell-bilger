/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Scanner;

/**
 *
 * @author Paul
 */
public class PayStationMain {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        PayStation station = new PayStationImpl();
        Boolean cont = true;
        boolean adminMode = false;

        System.out.println("Welcome to this paystation");

        System.out.println("Are you an administrator? (Y/N)");
        String admin = scan.nextLine();

        System.out.println("You are currently using the default rate strategy");
        while (cont) {
            System.out.println("Please choose an option below");

            System.out.println("Type 'Deposit' to deposit coins");
            System.out.println("Type 'Display' to view the total time currently purchased");
            System.out.println("Type 'Buy' to purchase your ticket");
            System.out.println("Type 'Cancel' to cancel the current purchase and retrieve your coins");

            if (admin.equalsIgnoreCase("Y")) {
                System.out.println("Type 'Change' to change the current town");
                System.out.println("Type 'Empty' to empty out the pay station");
                adminMode = true;
            }

            System.out.println("Type 'Exit' to leave the paystation machine");

            String choice = scan.nextLine();

            switch (choice.toUpperCase()) {
                case "DEPOSIT":
                    deposit(scan, station);
                    break;
                case "DISPLAY":
                    display(station);
                    break;
                case "BUY":
                    buy(station);
                    break;
                case "CANCEL":
                    cancel(station);
                    break;
                case "EMPTY":
                    if (adminMode) {
                        empty(station);
                    }
                    break;
                case "CHANGE":
                    if (adminMode) {
                        change(scan, station);
                    }
                    break;
                case "EXIT":
                    exit();
                    return;
                default:
                    break;
            }
            System.out.println();
        }
    }

    private static void deposit(Scanner scan, PayStation station) {
        System.out.println("Insert coin value (5, 10, 25)");
        int val = scan.nextInt();
        scan.nextLine();
        {
            try {
                station.addPayment(val);
            } catch (IllegalCoinException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void display(PayStation station) {
        System.out.println("Current time purchased: " + station.readDisplay());
    }

    private static void buy(PayStation station) {
        station.buy();
    }

    private static void cancel(PayStation station) {
        station.cancel();
    }

    private static void change(Scanner scan, PayStation station) {
        System.out.println("Enter new town name:");
        String newTown = scan.nextLine();
        String townRet = station.setTownString(newTown);
        if (townRet.equalsIgnoreCase("default")) {
            System.out.println("Town not recognized, setting to default... ");
        }
        System.out.println("Town set to " + townRet + "...");
    }

    private static void empty(PayStation station) {
        station.empty();
        System.out.println("The Pay Station has been emptied.");
    }

    private static void exit() {
        System.out.println("Exiting the PayStation interface.");
    }
}
