package gui;
import javafx.util.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.scene.control.Label;

public class LoginWindow extends Application {

	public static void main(String[] args) {
        launch(args);
        
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        PassengerLogin pl = new PassengerLogin();
        EmployeeScene es = new EmployeeScene();
        Button passengerBtn = new Button("Passenger");
        Button employeeBtn = new Button("Employee");
        Label welcomeMsg = new Label("Welcome to The Bus Station");

        passengerBtn.setOnAction(e->{
            pl.display(primaryStage);
        });
        employeeBtn.setOnAction(e->{
            es.start(primaryStage);
        });
        primaryStage.setTitle("Login window");
        StackPane root = new StackPane();
        root.getChildren().addAll(passengerBtn,employeeBtn,welcomeMsg);
        root.setAlignment(Pos.CENTER);
        StackPane.setMargin(passengerBtn, new Insets(0,0,100,0));
        StackPane.setMargin(employeeBtn, new Insets(0,0,0,0));
        StackPane.setMargin(welcomeMsg, new Insets(300 , 0 , 0 , 0));

        Scene scene = new Scene(root , 400 , 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
