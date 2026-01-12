// JAVA PROGRAMMING SEMESTER 1 - FINAL PROJECT
// Student Course Management System - By: Safura

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Here we will create username and passwords
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        
        Button loginButton = new Button("Login");
        Label message = new Label();

        // Login Button
        loginButton.setOnSction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if(username.equals("admin) && password.equals("1234")) {
            message.setText("Login successful!!"");
            } else {
                message.setText("Invalid username or password :(");
            }
        });
       // Layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        
        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(message, 1, 3);
        
        Scene scene = new Scene(grid, 300, 200);
        stage.setTitle("Login Screen");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}