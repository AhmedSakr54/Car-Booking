package Trips;

public class miniBus extends Vehicles{
    public miniBus(){
        this.numOfSeats = 7;
        this.type = "miniBus";
    }
    public miniBus(int numOfSeats , String type){
        this.numOfSeats = numOfSeats;
        this.type = type;
    }

}
