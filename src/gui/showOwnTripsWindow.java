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
public class showOwnTripsWindow extends Application {
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


        TableColumn<Trip , String> takeofTime = new TableColumn<>("Time of TakeOff");
        takeofTime.setMinWidth(200);
        takeofTime.setCellValueFactory(new PropertyValueFactory<>("takeOffTime"));

        TableColumn<Trip, String> numOfStops = new TableColumn<>("Number of stops");
        numOfStops.setMinWidth(200);
        numOfStops.setCellValueFactory(new PropertyValueFactory<>("numOfStops"));

        TableColumn<Trip, String> type = new TableColumn<>("Type");
        type.setMinWidth(200);
        type.setCellValueFactory(new PropertyValueFactory<>("type"));


        table = new TableView<>();
        Button deleteTripBtn = new Button("Delete Trip");
        Button backbtn = new Button("Back");
        table.setItems(getTrips(passenger.reviewTrips()));
        table.getColumns().addAll(tripNum,startingPointCol,destination,date,takeofTime,numOfStops,type);


        Stage primaryStage = new Stage();
        primaryStage.setTitle("Booked Trips");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(100);
        hbox.getChildren().addAll(deleteTripBtn,backbtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hbox);

        backbtn.setOnAction(e->{
            PassengerWindow pw = new PassengerWindow();
            pw.display(passenger);
            primaryStage.close();
        });
        deleteTripBtn.setOnAction(e->{

            ObservableList<Trip> tripsAvailable;
            AlertMessage am = new AlertMessage();

            tripsAvailable = table.getItems();
            ObservableList<Trip> tripSelected;
            tripSelected = table.getSelectionModel().getSelectedItems();

            boolean result = am.display("Caution" , "Are you sure you want to delete" , 1);
            try {
                if (result == true) {
                    passenger.deleteTrips(tripSelected.get(0), la);
                    for (int i = 0; i < la.getAllMembers().size(); i++) {
                        if (la.getAllMembers().get(i).getPassword().equals(passenger.getPassword())) {
                            la.getAllMembers().remove(i);
                            la.getAllMembers().add(passenger);
                        }
                    }
                    passenger.save(la.getAllMembers());
                    tripSelected.forEach(tripsAvailable::remove);
                }
            }catch (NullPointerException e1){
                am.display("Caution" , "There is no trips to delete",2);
            }
        });

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public ObservableList<Trip> getTrips(ArrayList<Trip> bookedTrips){
        ObservableList<Trip> trip = FXCollections.observableArrayList();
        for(int i = 0 ; i < bookedTrips.size() ; i++){
            trip.add(bookedTrips.get(i));
        }
        return trip;
    }
}
