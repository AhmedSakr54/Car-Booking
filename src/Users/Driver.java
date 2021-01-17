package Users;

import Trips.Trip;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Driver extends Employee {
    private ArrayList<Trip> trips = new ArrayList<>();
    public Driver(String firstName, String lastName, String password, String username, int employeeid , ArrayList<Trip> trip) {
        super(firstName, lastName, password, username, employeeid);
        this.trips = trip;
    }
    public Driver(){}

    @Override
    public ArrayList<Trip> getTrips() {
        return trips;
    }

    @Override
    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public void saveDrivers(ArrayList<Employee> information) {
        File file = new File("Drivers");


        try {
            FileWriter filewriter = new FileWriter(file);
            for(int i = 0 ; i < information.size(); i++) {
                filewriter.write(information.get(i).getFirstName() + "," + information.get(i).getLastName() + "," + information.get(i).getPassword() + "," + information.get(i).getUsername() + "," + information.get(i).getEmployeeId());
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




}
