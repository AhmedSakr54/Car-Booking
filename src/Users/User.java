package Users;

import Trips.Trip;

import java.util.ArrayList;

public class User implements Logable{
    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private ArrayList<Trip> trips = new ArrayList<>();
    public User() {

    }
    public User(String firstName, String lastName, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void save(ArrayList<User> information) {

    }
}
