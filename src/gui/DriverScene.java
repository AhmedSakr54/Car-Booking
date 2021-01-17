package gui;

import Trips.Trip;
import Users.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class DriverScene extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
    public void display(Driver driver){

        Stage window = new Stage();

        Button backButton=new Button("Back");
        backButton.setFont(new Font(15));
        VBox layout =new VBox();




        TableView<Trip> table;
        LoginAuthentication la = new LoginAuthentication();
        User pass = new Employee();
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
        Button logoutBtn = new Button("LogOut");

        table.setItems(getTrips(driver.reviewTrips()));
        table.getColumns().addAll(tripNum,startingPointCol,destination,date,takeofTime,numOfStops,type);

        layout.getChildren().addAll(table,logoutBtn);
        Scene scene=new Scene(layout);

        logoutBtn.setOnAction(e->{
            EmployeeScene es = new EmployeeScene();
            es.start(window);
        });

        window.setTitle("Driver");
        window.setScene(scene);
        window.show();
    }
    public ObservableList<Trip> getTrips(ArrayList<Trip> bookedTrips){
        ObservableList<Trip> trip = FXCollections.observableArrayList();
        for(int i = 0 ; i < bookedTrips.size() ; i++){
            trip.add(bookedTrips.get(i));
        }
        return trip;
    }

}
