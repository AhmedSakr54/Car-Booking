package gui;

import Users.Employee;
import Users.Manager;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ManagerScene extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
    public void display(Manager manager){
        Stage primaryStage=new Stage();
        Button addTrip=new Button("Add Trips");
        addTrip.setFont(new Font(15));
        Button removeTrip=new Button("Remove Trips");
        removeTrip.setFont(new Font(15));
        Button assignTrip=new Button("Assign Trips");
        assignTrip.setFont(new Font(15));
        Button logOut=new Button("LogOut");
        logOut.setFont(new Font(15));

        StackPane assign=new StackPane();
        assign.setAlignment(Pos.CENTER);
        assign.getChildren().addAll(assignTrip);
        HBox addRemove =new HBox();
        addRemove.setAlignment(Pos.CENTER);
        addRemove.getChildren().addAll(addTrip,removeTrip);
        StackPane backPane =new StackPane();
        backPane.setAlignment(Pos.CENTER);
        backPane.getChildren().addAll(logOut);
        VBox layout=new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(addRemove,backPane);

        Scene scene=new Scene(layout,300,250);

        removeTrip.setOnAction(e->{
            removeTrips rt = new removeTrips();
            rt.display(manager);

            primaryStage.close();
        });
        logOut.setOnAction(e->{
            EmployeeScene es  = new EmployeeScene();
            es.start(primaryStage);
        });

        addTrip.setOnAction(e->{
            addTrip add = new addTrip();
            add.display(manager);
            primaryStage.close();
        });
        primaryStage.setTitle("Manager Window");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}