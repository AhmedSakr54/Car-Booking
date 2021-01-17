package Trips;

public class limousine extends Vehicles {
    public limousine(){
        this.numOfSeats = 4;
        this.type = "limousine";
    }
    public limousine(int numOfSeats , String type){
        this.type = type;
        this.numOfSeats = numOfSeats;
    }
}
