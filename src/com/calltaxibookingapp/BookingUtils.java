package com.calltaxibookingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;

public class BookingUtils {
    private static int NO_OF_POINTS;
    private static int NO_OF_TAXIS;
    private static int bookingID;
    private static int lastBookedRideNumber;
    private Map<Integer, Taxi> taxis;
    private Map<Integer, Ride> bookedRides;

    public void initializeSetup() {
        //Setup Resources
        NO_OF_POINTS = 6;
        NO_OF_TAXIS = 4;
        bookingID = 1;
        taxis = new HashMap<>();
        for (int i = 1; i <= NO_OF_TAXIS; i++) {
            Taxi taxi = new Taxi(i);
            taxis.put(i, taxi);
        }
        bookedRides = new HashMap<>();
        lastBookedRideNumber = 0;
    }

    public boolean bookARide(int custID, String pickUpPoint, String dropPoint, int pickUpTime) {
        int freeTaxiID = findTaxi(pickUpPoint, pickUpTime);
        if (freeTaxiID > 0) {
            System.out.println("Taxi Can be allotted");
            int cost = calculateCost(pickUpPoint, dropPoint);
            int dropTime = distanceOf(pickUpPoint, dropPoint) + pickUpTime;
            Ride ride = new Ride(custID, freeTaxiID, pickUpPoint, dropPoint, cost, pickUpTime, dropTime);
            bookedRides.put(++lastBookedRideNumber, ride);
            taxis.get(freeTaxiID).updateEarnings(cost);
            taxis.get(freeTaxiID).addRide(lastBookedRideNumber, ride);
            System.out.println("Earnings:" + taxis.get(freeTaxiID).getEarnings());
            System.out.println("Taxi - " + freeTaxiID + " is allotted");
            return true;
        }
        return false;
    }

    public void displayAllDetails() {
        System.out.println("Taxi Number \t" + "Total Earnings\n" + "Booking ID\t" + "CustomerID\t" + "From\t" + "To\t" +
                "PickUp Time\t" + "Drop Time\t" + "Amount");
        for (int i = 1; i <= NO_OF_TAXIS; i++) {
            Taxi taxi = taxis.get(i);
            System.out.println("Taxi - " + taxi.getID() + "\t" + "Total Earnings:" + taxi.getEarnings());
            Map<Integer, Ride> rides = taxi.getRides();
            Set<Integer> bookingIDs = rides.keySet();
            for (int bookingID : bookingIDs) {
                Ride ride = rides.get(bookingID);
                System.out.println(bookingID + "\t" + ride.getCustID() + "\t" + ride.getPickUpPoint() + "\t" +
                        ride.getDropPoint() + "\t" + ride.getPickUpTime() + "\t" + ride.getDropTime() + "\t"
                        + ride.getCost());
            }
        }
    }

    public int findTaxi(String pickUpPoint, int pickUpTime) {
        int min = -1;
        String location[] = new String[NO_OF_TAXIS + 1];
        int lastDropTime[] = new int[NO_OF_TAXIS + 1];
        for (int i = 1; i <= NO_OF_TAXIS; i++) {
            location[i] = "A";
            lastDropTime[i] = 0;
        }
        Map<Integer, Taxi> busyTaxis = new HashMap<>();
        for (int j = 1; j <= lastBookedRideNumber; j++) {
            Ride ride = bookedRides.get(j);
            if (lastDropTime[ride.getTaxiID()] < ride.getDropTime()) {
                //Change current location based on ride info
                lastDropTime[ride.getTaxiID()] = ride.getDropTime();
                location[ride.getTaxiID()] = ride.getDropPoint();
            }
            if (pickUpTime < ride.getDropTime() && pickUpTime >= ride.getPickUpTime()) {
                //Check whethe taxi busy
                System.out.println("Taxi-" + ride.getTaxiID() + " is busy");
                busyTaxis.put(ride.getTaxiID(), taxis.get(ride.getTaxiID()));
            }
        }
        for (int i = 1; i <= NO_OF_TAXIS; i++) {
            if (busyTaxis.containsKey(i))
                continue;
            else if (min == -1) {
                min = i;
            } else {
                if (distanceOf(location[i], pickUpPoint) < distanceOf(location[min], pickUpPoint)) {
                    min = i;
                } else if (distanceOf(location[min], pickUpPoint) == distanceOf(location[i], pickUpPoint)) {
                    min = (taxis.get(i).getEarnings() < taxis.get(min).getEarnings()) ? i : min;
                }
            }
        }
        return min;
    }

    public int calculateCost(String pickUp, String drop) {
        return 100 + (((distanceOf(pickUp, drop)) * 15) - 5) * 10;
    }

    public int distanceOf(String pickUp, String drop) {
        return abs(Integer.valueOf(pickUp.charAt(0)) - Integer.valueOf(drop.charAt(0)));
    }
}
