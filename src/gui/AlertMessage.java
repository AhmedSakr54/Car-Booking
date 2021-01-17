package gui;
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
import javafx.scene.*;

public class AlertMessage extends Application{
    static boolean answer;
    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub

    }

    public boolean display(String title , String message , int i) {
        Stage window = new Stage();


        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("OK");
        Button noButton = new Button("NO");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        noButton.setOnAction(e->{
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        if(i == 1) {
            layout.getChildren().addAll(label, yesButton, noButton);
        }
        else
            layout.getChildren().addAll(label,yesButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

}