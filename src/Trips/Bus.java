package Trips;

public class Bus extends Vehicles {

    public Bus() {
        super.type = "Bus";
        super.numOfSeats = 30;
    }
    public Bus(int numOfSeats,String type){
        this.numOfSeats = numOfSeats;
        this.type = type;
    }

}
