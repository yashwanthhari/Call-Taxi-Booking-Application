package com.calltaxibookingapp;

public class Ride {
    private int custID;
    private int taxiID;
    private String pickUpPoint;
    private String dropPoint;
    private int cost;
    private int pickUpTime;
    private int dropTime;

    public Ride(int custID, int taxiID, String pickUpPoint, String dropPoint, int cost, int pickUpTime, int dropTime) {
        this.custID = custID;
        this.taxiID = taxiID;
        this.pickUpPoint = pickUpPoint;
        this.dropPoint = dropPoint;
        this.cost = cost;
        this.pickUpTime = pickUpTime;
        this.dropTime = dropTime;
    }

    public int getTaxiID() {
        return taxiID;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public int getCost() {
        return cost;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public int getCustID() {
        return custID;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    public int getDropTime() {
        return dropTime;
    }
}
