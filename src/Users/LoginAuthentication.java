package Users;

import Trips.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import sun.rmi.server.InactiveGroupException;
import gui.*;
import java.util.ArrayList;
import java.io.*;
import java.util.Dictionary;

public class LoginAuthentication {



    private ArrayList<User> allMembers = new ArrayList<>();
    private ArrayList<Trip> allTrips = new ArrayList<>();



    public ArrayList<User> getAllMembers(){
        return this.allMembers;
    }
    public void setAllMembers(ArrayList<User> allMembers) {
        this.allMembers = allMembers;
    }




    public ArrayList<Trip> getAllTrips() {
        return allTrips;
    }
    public void setAllTrips(ArrayList<Trip> allTrips) {
        this.allTrips = allTrips;
    }

    public User isMember(String username , String password , User currentlyLogging) {

        load(currentlyLogging);

        if(currentlyLogging instanceof Passenger) {
            for (int i = 0; i < this.allMembers.size(); i++) {
                if (this.allMembers.get(i).getUsername().equals(username) && this.allMembers.get(i).getPassword().equals(password)) {
                    return this.allMembers.get(i);
                }
            }
        }
        else
            for (int i = 0; i < this.allMembers.size(); i++) {
                Employee emp = new Employee();
                emp = (Employee)this.allMembers.get(i);
                //username is the employeeID
                if (emp.getEmployeeId() == Integer.parseInt(username) && emp.getPassword().equals(password)) {
                    return this.allMembers.get(i);
                }
            }

        return null;
    }




    public void load(User currentlyLogging){
        File file = new File("f");
        EmployeeScene es = new EmployeeScene();
        int x = 0 ;
        if(currentlyLogging instanceof Passenger)
        {
            file = new File("Passengers");
            x=1;
        }
        else if(es.getEmployeeType() == 1) {
            file = new File("Drivers");
            x=2;
        }
        else if(es.getEmployeeType() == 2){
            file = new File("Managers");
            x = 3;
        }
        try {
            loadTrips();
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            while(bufferedreader.ready()) {
                String line = bufferedreader.readLine();
                String[] info = line.split(",");
                ArrayList<Trip> passengerTrips = new ArrayList<>();

                if(x == 1) {
                    for(int i = 4 ; i < info.length ; i++) {

                        passengerTrips.add(searchForTrips(info[i]));
                    }

                    User somebody = new Passenger(info[0] , info[1] , info[2] , info[3]  , passengerTrips);
                    this.allMembers.add(somebody);
                }
                else if(x == 2) {


                        ArrayList<Trip> driverTrips = new ArrayList<>();
                        for(int i = 5 ; i<info.length ; i++)
                            driverTrips.add(searchForTrips(info[i]));

                        User drive = new Driver(info[0],info[1],info[2],info[3],Integer.parseInt(info[4]),driverTrips);
                        this.allMembers.add(drive);



                }
                else if(x==3){
                    User manager = new Manager(info[0],info[1],info[2],info[3],Integer.parseInt(info[4]));
                    this.allMembers.add(manager);
                }
            }
            filereader.close();
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadTrips() {
        File file = new File("TripInfo");
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            while(bufferedreader.ready()) {
                String line = bufferedreader.readLine();
                String[] info = line.split(",");
                Vehicles vehic = new Bus();

                if(info[7].equals("Bus")){
                    vehic = new Bus(Integer.parseInt(info[6]) , "Bus");
                }
                else if(info[7].equals("MiniBus")){
                    vehic = new miniBus(Integer.parseInt(info[6]) , "MiniBus");
                }
                else if(info[7].equals("limousine")){
                    vehic = new limousine(Integer.parseInt(info[6]) , "limousine");
                }

                Trip trip = new Trip(info[0] , info[1] , info[2] , info[3] , info[4], Integer.parseInt(info[5]),info[9], vehic , Integer.parseInt(info[8]));
                this.allTrips.add(trip);
            }
            filereader.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Trip searchForTrips(String tripNumber){
        Trip passengerTrips = new Trip();
        for(int i = 0 ; i< this.allTrips.size() ; i++)
        {
            if(this.allTrips.get(i).getTripNumber().equals(tripNumber)) {
                passengerTrips = this.allTrips.get(i);
                break;
            }
        }
        return passengerTrips;
    }
    public void saveTrips (ArrayList<Trip> information){
        File file = new File("TripInfo");

        try {
            FileWriter filewriter = new FileWriter(file);
            for(int i = 0 ; i < information.size(); i++) {
                filewriter.write(information.get(i).getTripNumber() + "," + information.get(i).getDestination() + "," + information.get(i).getStartingPoint() + "," + information.get(i).getDate() + "," + information.get(i).getTakeOffTime() + "," + information.get(i).getPrice() + "," + information.get(i).getTheVehicle().getNumOfSeats()+","+ information.get(i).getTheVehicle().getType() + "," +information.get(i).getNumOfStops() + "," +information.get(i).getType());
                if(i == information.size() - 1)
                    break;
                filewriter.write("\n");
            }
            filewriter.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
