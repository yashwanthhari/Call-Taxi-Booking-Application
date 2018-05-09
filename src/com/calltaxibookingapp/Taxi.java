package com.calltaxibookingapp;

import java.util.HashMap;
import java.util.Map;

public class Taxi {
    private int ID;
    private int earnings;
    private Map<Integer, Ride> rides;

    public Taxi(int ID) {
        this.ID = ID;
        this.earnings = 0;
        rides = new HashMap<>();
    }

    public int getID() {
        return ID;
    }

    public void updateEarnings(int earnings) {
        this.earnings += earnings;
    }

    public void addRide(int bookingID, Ride ride) {
        this.rides.put(bookingID, ride);
    }

    public Map<Integer, Ride> getRides() {
        return rides;
    }

    public int getEarnings() {
        return earnings;
    }
}
