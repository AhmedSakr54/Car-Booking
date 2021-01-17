package Trips;

import java.util.ArrayList;

public class Trip {

    private String date;
    private String destination;
    private String startingPoint;
    private String tripNumber;
    private int price;
    private String takeOffTime;
    private Vehicles theVehicle;
    private int numOfStops;
    private String type;





    public Trip() {};
    public Trip(String tripNumber , String destination , String startingPoint , String date , String takeOffTime ,int price ,String type , Vehicles vehicle , int numOfStops) {
        this.date = date;
        this.destination = destination;
        this.startingPoint = startingPoint;
        this.tripNumber = tripNumber;
        this.takeOffTime = takeOffTime ;
        this.numOfStops = numOfStops;
        this.theVehicle = vehicle;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumOfStops() {
        return numOfStops;
    }

    public void setNumOfStops(int numOfStops) {
        this.numOfStops = numOfStops;
    }

    public Vehicles getTheVehicle() {
        return theVehicle;
    }

    public void setTheVehicle(Vehicles theVehicle) {
        this.theVehicle = theVehicle;
    }
    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getStartingPoint() {
        return startingPoint;
    }


    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }


    public String getTripNumber() {
        return tripNumber;
    }


    public void setTripNumber(String tripNumber) {
        this.tripNumber = tripNumber;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public String getTakeOffTime() {
        return takeOffTime;
    }


    public void setTakeOffTime(String takeOffTime) {
        this.takeOffTime = takeOffTime;
    }



}
