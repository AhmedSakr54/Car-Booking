package gui;
import Users.LoginAuthentication;
import Users.Passenger;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.control.Label;

import javax.xml.soap.Text;

public class PassengerWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public void display(Passenger passenger){
        Stage newStage = new Stage();
        newStage.setTitle("PassengerWindow");
        newStage.setMinWidth(500);
        newStage.setMinHeight(500);
        Button showAllTripsbtn = new Button("Show All trips");
        Button reviewYourTrips = new Button("Review Your trips");
        Button backbtn = new Button ("Back");
        Label welcomelbl = new Label("Welcome");
        Label namelbl = new Label(passenger.getFirstName()+ " "+ passenger.getLastName());

        VBox window = new VBox();
        window.setPadding(new Insets(40,40,40,40));
        window.setSpacing(50);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(welcomelbl,namelbl);

        showAllTripsbtn.setOnAction(e->{
            ShowAllTripsWindow satw = new ShowAllTripsWindow();
            satw.display(passenger);
            newStage.close();
        });
        reviewYourTrips.setOnAction(e->{
            showOwnTripsWindow sotw = new showOwnTripsWindow();
            sotw.display(passenger);
            newStage.close();
        });
        backbtn.setOnAction(e->{
            PassengerLogin pl = new PassengerLogin();
            Stage newSt = new Stage();
            pl.display(newSt);
           newStage.close();
        });

        window.getChildren().addAll(showAllTripsbtn,reviewYourTrips,backbtn,hbox);

        Scene scene = new Scene(window);
        newStage.setScene(scene);
        newStage.show();
    }
}
