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
                adminMode = true;
            }
            
            System.out.println("Type 'Exit' to leave the paystation machine");

            String choice = scan.nextLine();

            switch (choice.toUpperCase()) {
                case "DEPOSIT":
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
                    break;
                case "DISPLAY":
                    System.out.println("Current time purchased: " + station.readDisplay());
                    break;
                case "BUY":
                    station.buy();
                    break;
                case "CANCEL":
                    station.cancel();
                    break;
                case "CHANGE":
                    if(adminMode) {
                        System.out.println("Enter new town name:");
                        String newTown = scan.nextLine();
                        String townRet = station.setTownString(newTown);
                        if (townRet.equalsIgnoreCase("default")) {
                            System.out.println("Town not recognized, setting to default... ");
                        }
                        System.out.println("Town set to " + townRet + "...");
                    }
                    break;
                case "EXIT":
                    System.out.println("Exiting the PayStation interface.");
                    return;
                default:
                    break;
            }
            System.out.println();
        }
    }
}
