package gui;
import Trips.Trip;
import Users.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.*;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

public class removeTrips extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public void display(Manager manager){
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
        table.setItems(getTrips(la.getAllTrips()));
        table.getColumns().addAll(tripNum,startingPointCol,destination,date,takeofTime,type,numOfStops);


        Stage primaryStage = new Stage();
        primaryStage.setTitle("All Trips");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(100);
        hbox.getChildren().addAll(deleteTripBtn,backbtn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hbox);

        backbtn.setOnAction(e->{
            ManagerScene ms = new ManagerScene();
            ms.display(manager);
            primaryStage.close();
        });
        deleteTripBtn.setOnAction(e->{
            Passenger passenger = new Passenger();
            Driver driver = new Driver();
            LoginAuthentication lap = new LoginAuthentication();
            LoginAuthentication lad = new LoginAuthentication();
            lap.load(passenger);
            lad.load(driver);
            ObservableList<Trip> tripsAvailable;
            AlertMessage am = new AlertMessage();

            tripsAvailable = table.getItems();
            ObservableList<Trip> tripSelected;
            tripSelected = table.getSelectionModel().getSelectedItems();
            boolean result = am.display("Caution" , "Are you sure you want to delete" , 1);
            if(tripSelected.size() > 0) {
                if (result == true) {

                    manager.removeTrips(lap.getAllTrips(), tripSelected.get(0));
                }
                tripSelected.forEach(tripsAvailable::remove);
            }else
                am.display("Caution" , "There are no trips to delete" , 2);



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
