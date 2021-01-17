package gui;

import Trips.*;
import Users.Driver;
import Users.LoginAuthentication;
import Users.Manager;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class addTrip extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void display(Manager manager){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add new Trip");
        LoginAuthentication la = new LoginAuthentication();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        Label headerLabel = new Label("ADD NEW TRIP");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label tripnum = new Label("Trip Number:");
        Label dest = new Label("Destination:");
        gridPane.add(tripnum, 0,1);
        gridPane.add(dest,0,2);

        TextField tripNumber = new TextField();
        TextField destination = new TextField();
        tripNumber.setPrefHeight(40);
        destination.setPrefHeight(40);
        gridPane.add(tripNumber, 1,1);
        gridPane.add(destination,1,2);



        Label startPoint = new Label("Starting Point:");
        gridPane.add(startPoint, 0, 3);


        TextField startingPoint = new TextField();
        startingPoint.setPrefHeight(40);
        gridPane.add(startingPoint, 1, 3);


        Label date = new Label("Date:");
        gridPane.add(date, 0, 4);
        TextField dateOfTrip= new TextField();
        dateOfTrip.setPrefHeight(40);
        gridPane.add(dateOfTrip, 1, 4);
        TextField date2 = new TextField();
        date2.setPrefHeight(40);
        gridPane.add(date2, 2, 4);

        Label takeOff = new Label("Take off Time:");
        gridPane.add(takeOff, 0, 10);
        TextField takeOffTime= new TextField();
        takeOffTime.setPrefHeight(40);
        gridPane.add(takeOffTime, 1, 10);

        Label price = new Label("Price:");
        gridPane.add(price, 0, 5);
        TextField pricefield= new TextField();
        pricefield.setPrefHeight(40);
        gridPane.add(pricefield, 1, 5);

        Label type = new Label("Trip Type:");
        gridPane.add(type,0,6);
        ChoiceBox<String> tripType = new ChoiceBox<>();
        tripType.getItems().addAll("oneway" , "roundtrip");
        tripType.setValue("oneway");
        tripType.setPrefHeight(40);
        gridPane.add(tripType, 1, 6);


        Label vehc = new Label("Vehicles:");
        gridPane.add(vehc,0,7);
        ChoiceBox<String> vehicles = new ChoiceBox<>();
        vehicles.getItems().addAll("Bus" , "MiniBus" , "limousine");
        vehicles.setPrefHeight(40);
        gridPane.add(vehicles, 1, 7);

        Label numStops = new Label("no.Stops:");
        gridPane.add(numStops, 0, 8);
        TextField Stops= new TextField();
        Stops.setPrefHeight(40);
        gridPane.add(Stops, 1, 8);

        Label driver = new Label("Driver:");
        gridPane.add(driver,0,9);
        ChoiceBox<String> drivers = new ChoiceBox<>();
        ArrayList<Driver> AllDrivers = new ArrayList<>();

        AllDrivers =manager.getDrivers();
        for(int i = 0 ; i < AllDrivers.size() ; i ++){
            drivers.getItems().add(AllDrivers.get(i).getFirstName());
        }
        drivers.setPrefHeight(40);
        gridPane.add(drivers , 1 ,9);


        Button backButton = new Button("Back");
        Button submitButton = new Button("Add Trip");
        submitButton.setPrefHeight(40);
        submitButton.setPrefWidth(100);
        submitButton.setDefaultButton(true);
        backButton.setPrefHeight(40);
        backButton.setPrefWidth(100);
        backButton.setDefaultButton(true);
        gridPane.add(submitButton, 0, 11, 4, 1);
        gridPane.add(backButton,0,12,4,1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(backButton,HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        GridPane.setMargin(backButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(e->{
            int x = 0;
            AlertMessage am = new AlertMessage();
            String dateTrip = "";
            if(tripType.getValue().equals("roundtrip") && date2.getText().equals(""))
                    x=1;
            if(tripType.getValue().equals("oneway") && !date2.getText().equals(""))
                x=1;
            dateTrip = dateOfTrip.getText() + " " + date2.getText();
            LoginAuthentication lp = new LoginAuthentication();
            lp.loadTrips();
            if(manager.tripistaken(lp.getAllTrips() , tripNumber.getText()))
                x=1;

            if(x != 1) {
                try {
                    if (!tripNumber.getText().equals("") && !destination.getText().equals("") && !startingPoint.getText().equals("") && !dateTrip.equals("") && !pricefield.getText().equals("") && !Stops.getText().equals("") && !drivers.getValue().equals("")) {
                        ArrayList<Driver> allDrivers = new ArrayList<>();
                        allDrivers = manager.getDrivers();
                        Vehicles vehicles1 = new Bus();
                        if (vehicles.getValue().equals("Bus")) {
                            vehicles1 = new Bus();
                        }
                        if (vehicles.getValue().equals("MiniBus")) {
                            vehicles1 = new miniBus();
                        }
                        if (vehicles.getValue().equals("limousine")) {
                            vehicles1 = new limousine();
                        }
                        Trip trip = new Trip(tripNumber.getText(), destination.getText(), startingPoint.getText(), dateTrip, takeOffTime.getText(), Integer.parseInt(pricefield.getText()), tripType.getValue(), vehicles1, Integer.parseInt(Stops.getText()));
                        for (int i = 0; i < allDrivers.size(); i++) {
                            if (allDrivers.get(i).getFirstName().equals(drivers.getValue())) {
                                manager.addTrip(trip, allDrivers.get(i));

                                break;
                            }
                        }
                        ManagerScene ms = new ManagerScene();
                        primaryStage.close();
                        ms.display(manager);
                    } else {

                        am.display("Alert", "make sure you filled all the fields", 2);
                    }
                } catch (NullPointerException df) {

                    am.display("Alert", "make sure you filled all the fields", 2);
                }
            }else am.display("Alert" , "Error in trip type or Trip Number" , 2);
        });
        backButton.setOnAction(e->{
            ManagerScene ms = new ManagerScene();
            primaryStage.close();
            ms.display(manager);
        });


        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
