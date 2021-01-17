package Users;
import java.io.*;
import java.util.ArrayList;

import Trips.*;
public class Passenger extends User implements Logable {

    ArrayList<Trip> trips = new ArrayList<>();

    public Passenger(String firstName, String lastName, String password, String username,ArrayList<Trip> trips) {
        super(firstName, lastName, password, username);
        this.trips = trips;
    }
    public Passenger() {

    }


    public ArrayList<Trip> getTrips() {
        return trips;
    }
    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }
    @Override
    public User login(String username , String password) {
        LoginAuthentication loginAuthentication = new LoginAuthentication();
        User newPassenger = new Passenger();
        User some = new Passenger();
        newPassenger = loginAuthentication.isMember(username,password,some);
        return newPassenger;
    }




    @Override
    public void save(ArrayList<User> information) {
        File file = new File("Passengers");


        try {
            FileWriter filewriter = new FileWriter(file);
            for(int i = 0 ; i < information.size(); i++) {
                filewriter.write(information.get(i).getFirstName() + "," + information.get(i).getLastName() + "," + information.get(i).getPassword() + "," + information.get(i).getUsername());
                //System.out.println(information.get(i).getFirstName() + "," + information.get(i).getLastName() + "," + information.get(i).getPassword() + "," + information.get(i).getUsername());
                if(information.get(i).getTrips() != null) {
                    for (int j = 0; j < information.get(i).getTrips().size(); j++) {
                        filewriter.write("," + information.get(i).getTrips().get(j).getTripNumber());
                    }
                }
                    if (i == information.size() - 1)
                        break;
                    filewriter.write("\n");
            }
            filewriter.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Trip> reviewTrips(){
        ArrayList<Trip> trips = new ArrayList<>();
        LoginAuthentication la = new LoginAuthentication();
        trips = getTrips();
        return trips;
    }

    public void addTrips(Trip trip , LoginAuthentication la){
        this.trips.add(trip);
        for(int i = 0 ; i < la.getAllTrips().size() ; i++){
            if(la.getAllTrips().get(i).getTripNumber().equals(trip.getTripNumber())){
                int x = la.getAllTrips().get(i).getTheVehicle().getNumOfSeats();

                la.getAllTrips().get(i).getTheVehicle().setNumOfSeats(--x);

            }
        }


    }
    public void deleteTrips(Trip trip , LoginAuthentication la){
        for(int i = 0 ; i < getTrips().size() ; i++){
            if(trip.getTripNumber().equals(getTrips().get(i).getTripNumber())){
                getTrips().remove(i);

            }
        }
        for(int i = 0 ; i < la.getAllTrips().size() ; i++){
            if(la.getAllTrips().get(i).getTripNumber().equals(trip.getTripNumber())){
                int x = la.getAllTrips().get(i).getTheVehicle().getNumOfSeats();

                la.getAllTrips().get(i).getTheVehicle().setNumOfSeats(++x);

            }
        }
        la.saveTrips(la.getAllTrips());
    }


}