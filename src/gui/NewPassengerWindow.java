package gui;

import Trips.Trip;
import Users.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NewPassengerWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public void display(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Sign up");

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

        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));


        Label firstNameLabel = new Label("First Name : ");
        Label lastNameLabel = new Label("Last Name : ");
        gridPane.add(firstNameLabel, 0,1);
        gridPane.add(lastNameLabel,0,2);

        TextField firstName = new TextField();
        TextField lastName = new TextField();
        firstName.setPrefHeight(40);
        lastName.setPrefHeight(40);
        gridPane.add(firstName, 1,1);
        gridPane.add(lastName,1,2);



        Label userNameLabel = new Label("UserName : ");
        gridPane.add(userNameLabel, 0, 3);


        TextField userNameField = new TextField();
        userNameField.setPrefHeight(40);
        gridPane.add(userNameField, 1, 3);


        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0, 4);


        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 4);

        Button backButton = new Button("Back");
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setPrefWidth(100);
        submitButton.setDefaultButton(true);
        backButton.setPrefHeight(40);
        backButton.setPrefWidth(100);
        backButton.setDefaultButton(true);
        gridPane.add(submitButton, 0, 5, 4, 1);
        gridPane.add(backButton,0,6,4,1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(backButton,HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));
        GridPane.setMargin(backButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(e->{
            LoginAuthentication la = new LoginAuthentication();
            if(!firstName.getText().equals("") && !lastName.getText().equals("") && !passwordField.getText().equals("") && !userNameField.getText().equals("")) {
                Passenger passenger = new Passenger(firstName.getText(), lastName.getText(), passwordField.getText(), userNameField.getText(), null);
                la.load(passenger);

                la.getAllMembers().add(passenger);

                passenger.save(la.getAllMembers());

                PassengerLogin pl = new PassengerLogin();
                pl.display(primaryStage);
            }else{
                AlertMessage am = new AlertMessage();
                am.display("Alert" ,"Make sure to fill in all the information" , 2);
            }

        });

        backButton.setOnAction(e->{
            PassengerLogin pl = new PassengerLogin();
            pl.display(primaryStage);

        });
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
