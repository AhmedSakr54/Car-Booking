package Users;

import Trips.Trip;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import gui.AlertMessage;
import gui.EmployeeScene;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;


public class Manager extends Employee implements Logable {

    public Manager(String firstName, String lastName, String password, String username, int employeeid) {
        super(firstName, lastName, password, username, employeeid);
    }

    public Manager() {
    }

    public void removeTrips(ArrayList<Trip> allTrips, Trip removedTrip) {
        LoginAuthentication lap = new LoginAuthentication();
        User passenger = new Passenger();
        lap.load(passenger);
        for (int i = 0; i < lap.getAllMembers().size(); i++) {
            for (int j = 0; j < lap.getAllMembers().get(i).getTrips().size(); j++) {
                if (lap.getAllMembers().get(i).getTrips().get(j).getTripNumber().equals(removedTrip.getTripNumber())) {
                    lap.getAllMembers().get(i).getTrips().remove(j);

                }
            }

        }
        passenger.save(lap.getAllMembers());
        LoginAuthentication lad = new LoginAuthentication();
        User driver = new Driver();
        EmployeeScene es = new EmployeeScene();
        es.setEmployeeType(1);
        lad.load(driver);
        ArrayList<Employee> temp = new ArrayList<>();

        for (int i = 0; i < lad.getAllMembers().size(); i++) {
            for (int j = 0; j < lad.getAllMembers().get(i).getTrips().size(); j++) {
                if (lad.getAllMembers().get(i).getTrips().get(j).getTripNumber().equals(removedTrip.getTripNumber())) {
                    lad.getAllMembers().get(i).getTrips().remove(j);
                }
            }
            temp.add((Employee) lad.getAllMembers().get(i));
        }
        ((Driver) driver).saveDrivers(temp);
        for (int i = 0; i < allTrips.size(); i++) {
            if (allTrips.get(i).getTripNumber().equals(removedTrip.getTripNumber())) {
                allTrips.remove(i);
            }
        }
        LoginAuthentication la = new LoginAuthentication();
        la.saveTrips(allTrips);
    }

    public boolean isAvailabeDate(String assignedTrip, String assignedTime, Driver driver) {
        int x = 0;
        if (assignedTrip.length() > 15) {
           return isAvailabelDateRoundTrip(assignedTrip, assignedTime, driver);
        } else {
//            String[] assignedDate = assignedTrip.split("/");
            for (int i = 0; i < driver.getTrips().size(); i++) {
                if(assignedTrip.equals(driver.getTrips().get(i).getDate()))
                    return false;
                if(assignedTime.equals(driver.getTrips().get(i).getTakeOffTime()))
                    return false;
//                String[] driversDate = driver.getTrips().get(i).getDate().split("/");
//                if (driversDate[2].equals(assignedDate[2])) {
//                    if (driversDate[1].equals(assignedDate[1])) {
//                        if (driversDate[0].equals(assignedDate[0]) && driver.getTrips().get(i).getTakeOffTime().equals(assignedTime)) {
//                            x = 1;
//                        } else x = 2;
//                    } else continue;
//                } else continue;
            }
            return true;
        }
//        if (x == 1)
//            return false;
//        return true;
    }

    public boolean isAvailabelDateRoundTrip(String assignedTrip,String assignedTime, Driver driver) {
        String[] trips = assignedTrip.split(" ");
        String[] firstTrip = trips[0].split("/");
        String[] secondTrip = trips[1].split("/");
        for(int i = 0 ; i < driver.getTrips().size() ; i++){
            String [] driverDate = driver.getTrips().get(i).getDate().split("/");
            if(firstTrip[1].equals(driverDate[1])){
                if(firstTrip[0].equals(driverDate[0]) && assignedTime.equals(driver.getTrips().get(i).getTakeOffTime()))
                    return false;
            }
            if(secondTrip[1].equals(driverDate[1])){
                if(secondTrip[0].equals(driverDate[0]) && assignedTime.equals(driver.getTrips().get(i).getTakeOffTime()))
                    return false;
            }
        }
        return true;
    }

    public void addTrip(Trip trip,Driver driver) {
        LoginAuthentication la = new LoginAuthentication();
        if(isAvailabeDate(trip.getDate() , trip.getTakeOffTime() , driver)) {
            la.load(driver);
            la.getAllTrips().add(trip);
            la.saveTrips(la.getAllTrips());
            driver.getTrips().add(trip);
            ArrayList<Employee> temp = new ArrayList<>();
            for (int i = 0; i < la.getAllMembers().size(); i++) {
                if (la.getAllMembers().get(i).getPassword().equals(driver.getPassword())) {
                    la.getAllMembers().remove(i);
                    la.getAllMembers().add(driver);
                }
                temp.add((Employee) la.getAllMembers().get(i));
            }
            driver.saveDrivers(temp);
        }else {
            AlertMessage am = new AlertMessage();
            am.display("Alert" , "The driver is busy at this time" , 2);
        }

    }

    public ArrayList<Driver> getDrivers(){
        LoginAuthentication la = new LoginAuthentication();
        User driver = new Driver();
        EmployeeScene es = new EmployeeScene();
        es.setEmployeeType(1);
        la.load(driver);
        ArrayList<Driver> drivers = new ArrayList<>();
        for(int i = 0 ; i < la.getAllMembers().size() ; i++){
            drivers.add((Driver) la.getAllMembers().get(i));
        }
        return drivers;
    }

    public boolean tripistaken(ArrayList<Trip>information,String tripnum){
        for(int i=0;i<information.size();i++){
            if(information.get(i).getTripNumber().equals(tripnum))
                return true;
        }
        return false;
    }

}


