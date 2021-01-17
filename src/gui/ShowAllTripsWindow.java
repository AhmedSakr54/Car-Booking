package gui;
import Trips.Trip;
import Users.LoginAuthentication;
import Users.Passenger;
import Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.*;

import java.util.ArrayList;

public class ShowAllTripsWindow extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public void display(Passenger passenger){
        TableView<Trip> table;
        LoginAuthentication la = new LoginAuthentication();
        User pass = new Passenger();
        la.load(pass);


        TableColumn<Trip , String> tripNum = new TableColumn<>("Trip Number");
        tripNum.setMinWidth(200);
        tripNum.setCellValueFactory(new PropertyValueFactory<>("tripNumber"));


        TableColumn<Trip , String> startingPointCol = new TableColumn<>("Starting Point");
        startingPointCol.setMinWidth(200);
        startingPointCol.setCellValueFactory(new PropertyValueFactory<>("startingPoint"));

        TableColumn<Trip , String> destination = new TableColumn<>("Destination");
        destination.setMinWidth(200);
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));


        TableColumn<Trip , String> date = new TableColumn<>("Date");
        date.setMinWidth(200);
        date.setCellValueFactory(new PropertyValueFactory<>("date"));


        TableColumn<Trip, String> takeofTime = new TableColumn<>("Time of TakeOff");
        takeofTime.setMinWidth(200);
        takeofTime.setCellValueFactory(new PropertyValueFactory<>("takeOffTime"));

        TableColumn<Trip, String> numOfStops = new TableColumn<>("Number of stops");
        numOfStops.setMinWidth(200);
        numOfStops.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));

        TableColumn<Trip, String> type = new TableColumn<>("Type");
        type.setMinWidth(200);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));


        table = new TableView<>();
        Button addTripbtn = new Button("Add Trip");
        Button backbtn = new Button("Back");
        table.setItems(getTrips(la.getAllTrips()));
        table.getColumns().addAll(tripNum,startingPointCol,destination,date,takeofTime,type,numOfStops);

        Stage primaryStage = new Stage();
        primaryStage.setTitle("All Trips");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(100);
        hbox.getChildren().addAll(addTripbtn,backbtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hbox);

        backbtn.setOnAction(e->{
            PassengerWindow pw = new PassengerWindow();
            pw.display(passenger);
            primaryStage.close();
        });

        addTripbtn.setOnAction(e->{
            ObservableList<Trip> tripsAvailable;
            AlertMessage am = new AlertMessage();

            tripsAvailable = table.getItems();
            ObservableList<Trip> tripSelected;
            tripSelected = table.getSelectionModel().getSelectedItems();

            if(tripSelected.get(0).getTheVehicle().getNumOfSeats() <= 0){
                am.display("Alert" ,"No Avaliable places" , 2);
            }
            else {
                boolean result = am.display("Caution", "Are you sure you want to buy this ticket?\nThe Price = " + tripSelected.get(0).getPrice() + "\nBy " + tripSelected.get(0).getTheVehicle().getType(), 1);

                if (result == true) {
                    passenger.addTrips(tripSelected.get(0), la);
                    for (int i = 0; i < la.getAllMembers().size(); i++) {
                        if (la.getAllMembers().get(i).getPassword().equals(passenger.getPassword())) {
                            la.getAllMembers().remove(i);
                            la.getAllMembers().add(passenger);
                        }
                    }
                    la.setAllMembers(la.getAllMembers());
                    passenger.save(la.getAllMembers());
                    la.saveTrips(la.getAllTrips());
                    PassengerWindow pw = new PassengerWindow();
                    pw.display(passenger);
                    primaryStage.close();
                }
            }

        });
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public ObservableList<Trip> getTrips(ArrayList<Trip> allTrips){
        ObservableList<Trip> trip = FXCollections.observableArrayList();
        for(int i = 0 ; i < allTrips.size() ; i++){
            trip.add(allTrips.get(i));
        }
        return trip;
    }
}
