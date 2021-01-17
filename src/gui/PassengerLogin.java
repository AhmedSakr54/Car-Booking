package gui;
import Users.Passenger;
import javafx.scene.control.*;
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
import java.io.IOException;

public class PassengerLogin extends Application{
    @Override

    public void start(Stage primaryStage) throws Exception {

    }
    public void display(Stage primaryStage){
        primaryStage.setTitle("Passenger Login");
        primaryStage.setMaxHeight(500);
        primaryStage.setMaxWidth(500);
        Button loginbtn = new Button("Login");
        Button back = new Button("Back");
        Button signUP = new Button("Sign up");
        PasswordField passField = new PasswordField();
        TextField userName = new TextField();
        passField.setMinWidth(100);
        userName.setMinWidth(50);

        userName.setPromptText("Username");
        passField.setPromptText("Password");

        StackPane root = new StackPane();
        root.getChildren().addAll(loginbtn,back,passField,userName,signUP);
        root.setAlignment(Pos.CENTER);

        StackPane.setMargin(signUP, new Insets(400 , 0,0,0));
        StackPane.setMargin(loginbtn , new Insets(400,150,200,0));
        StackPane.setMargin(back , new Insets(400,0,200,150));
        StackPane.setMargin(passField , new Insets(100,0,0,0));
        StackPane.setMargin(userName, new Insets(0,0,100,0));



        signUP.setOnAction(e->{
            NewPassengerWindow npw = new NewPassengerWindow();
            npw.display();
            primaryStage.close();
        });
        loginbtn.setOnAction(e->{
            Passenger passenger = new Passenger();
            PassengerWindow pw = new PassengerWindow();
            passenger = (Passenger) passenger.login(userName.getText() , passField.getText());
            if(passenger != null){
                pw.display(passenger);
                primaryStage.close();
            }else
            {
                AlertMessage am = new AlertMessage();
                am.display("Alert" , "Wrong username of password" , 2);
            }
        });
        back.setOnAction(e->{
            LoginWindow lw = new LoginWindow();
            try{
                lw.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }


        });
        Scene scene = new Scene(root , 600 , 600);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
