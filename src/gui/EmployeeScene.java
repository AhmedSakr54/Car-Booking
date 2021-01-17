package gui;

import Users.Driver;
import Users.Employee;
import Users.Manager;
import Users.Passenger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class EmployeeScene extends Application {
    ManagerScene managerScene=new ManagerScene();
    DriverScene driverScene=new DriverScene();
    private static int EmployeeType;

    public void setEmployeeType(int employeeType) {
        EmployeeType = employeeType;
    }
    public int getEmployeeType(){
        return this.EmployeeType;
    }

    @Override
    public void start(Stage primaryStage) {
        Label id=new Label("Employee ID ");
        id.setFont(new Font(15));
        TextField idField=new TextField();
        Label password =new Label("Password      ");
        password.setFont(new Font(15));
        PasswordField passwordField=new PasswordField();
        HBox idHbox =new HBox();
        idHbox.getChildren().addAll(id,idField);
        HBox passHBox=new HBox();
        passHBox.getChildren().addAll(password,passwordField);
        VBox loginVbox =new VBox();

        loginVbox.setAlignment(Pos.CENTER);
        loginVbox.getChildren().addAll(idHbox,passHBox);
        Button login = new Button("Login");
        login.setFont(new Font(15));
        Button logout =new Button("Back");
        logout.setFont(new Font(15));
        HBox EmployeeBox = new HBox();
        RadioButton driverCheck = new RadioButton("Driver");
        RadioButton managerCheck = new RadioButton("Manager");

        EmployeeBox.setAlignment(Pos.CENTER);
        EmployeeBox.getChildren().addAll(driverCheck,managerCheck);
        HBox logsHBox=new HBox();
        logsHBox.setAlignment(Pos.CENTER);
        logsHBox.getChildren().addAll(logout,login);


        BorderPane layout =new BorderPane();
        layout.setCenter(loginVbox);
        layout.setTop(EmployeeBox);
        layout.setBottom(logsHBox);


        login.setOnAction(e->{
            Employee emp = new Employee();
            AlertMessage am = new AlertMessage();

            if(managerCheck.isSelected() && driverCheck.isSelected()){

                am.display("Alert" , "You should only select one type of Employees" , 2);
            }
            else if(!managerCheck.isSelected() && !driverCheck.isSelected()){
                am.display("Alert" , "You should select one type of Employee" , 2);
            }

            else {
                int x = 0;
                if(driverCheck.isSelected()) {

                    Employee driver1 = new Driver();
                    setEmployeeType(1);
                    if(!idField.getText().equals("") || !passwordField.getText().equals(""))
                        driver1 = (Driver) driver1.login(idField.getText(), passwordField.getText());
                    else {
                        x = 1;
                    }
                    if (driver1 != null && x != 1) {
                            driverScene.display((Driver) driver1);
                            primaryStage.close();
                    } else am.display("Alert", "Wrong EmployeeID or Password", 2);
                }
                else if(managerCheck.isSelected()){
                    Employee manager1 = new Manager();
                    setEmployeeType(2);
                    if(!idField.getText().equals("") || !passwordField.getText().equals(""))
                        manager1 = (Manager) manager1.login(idField.getText(), passwordField.getText());
                    else {
                        x=1;
                    }

                    if (manager1 != null && x!=1) {
                        managerScene.display((Manager) manager1);
                        primaryStage.close();
                    } else am.display("Alert", "Wrong EmployeeID or Password", 2);

                }
            }
        });

        logout.setOnAction(e->{
            LoginWindow lw = new LoginWindow();
            try {
                lw.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Scene scene = new Scene(layout,300, 300);

        primaryStage.setTitle("Employee Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
