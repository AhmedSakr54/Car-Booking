package Trips;

public abstract class Vehicles {

    protected int numOfSeats;
    protected String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumOfSeats(int numOfSeats){
        this.numOfSeats = numOfSeats;
    }

    public int getNumOfSeats(){
        return this.numOfSeats;
    }
}
