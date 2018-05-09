package com.calltaxibookingapp;

import java.util.Scanner;

public class Driver {


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BookingUtils bookingUtils = new BookingUtils();
        bookingUtils.initializeSetup();
        System.out.println("Call Taxi Booking Application");
        while (true) {
            System.out.println("1.Book A Taxi\n" +
                    "2.Display Details");
            System.out.println("Enter your choice :");
            int choice = sc.nextInt();
            int custID;
            String pickUpPoint;
            String droppoint;
            int pickUpTime;
            if (choice == 1) {
                //Book Taxi
                System.out.println("Enter number of bookings :");
                int numberOfBookings = sc.nextInt();
                while (numberOfBookings > 0) {
                    System.out.println("Customer ID : ");
                    custID = sc.nextInt();
                    System.out.println("PickUp point : ");
                    pickUpPoint = sc.next();
                    System.out.println("Drop Point : ");
                    droppoint = sc.next();
                    System.out.println("PickUp Time : ");
                    pickUpTime = sc.nextInt();
                    boolean isBooked = bookingUtils.bookARide(custID, pickUpPoint, droppoint, pickUpTime);
                    if (!isBooked) {
                        System.out.println("Booking Rejected");
                    }
                    numberOfBookings--;
                }
            } else if (choice == 2) {
                //Display details
                bookingUtils.displayAllDetails();
            } else if (choice == 3) {
                //Exit
                System.out.println("Exiting");
                break;
            } else {
                //Invalid Choice
                System.out.println("Please Enter Valid Selection");
            }
        }
    }


}
