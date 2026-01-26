// JAVA PROGRAMMING SEMESTER 1 - FINAL PROJECT
// Student Course Management System - By: Safura

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Main extends Application {

    private HashMap<String, String> users = new HashMap<>();
    private HashMap<String, Student> students = new HashMap<>();

    private final String FILE_NAME = "users.json";
    private final String STUDENTS_FILE = "students.json";

    private Gson gson = new Gson();

    @Override
    public void start(Stage stage) {
        private HashMap<String, Student> students = new HashMap<>();
        private final String STUDENTS_FILE = "students.json";
        loadUsers();

        javafx.scene.control.Label userLabel = new javafx.scene.control.Label("Username:");
        TextField userField = new TextField();

        javafx.scene.control.Label passLabel = new javafx.scene.control.Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");

        javafx.scene.control.Label message = new javafx.scene.control.Label();

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                message.setText("Login successful!");
            
            // This is supposed to hide the login window
            stage.hide();

            // Open window
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");

            VBox dashboardLayout = new VBox(10);
            dashboardLayout.setPadding(new javafx.geometry.Insets(20));

            Label welcomeLabel = new Label("Welcome, " + username + "!");
            Button addStudentButton = new Button("Add Student");
            Button viewStudentsButton = new Button("View Students");
            
            Stage addStage = new Stage();
            addStage.setTtitle("Add Student");

            addStudentButton.setOnAction(ev -> {
                addStage.setTitle("Add Student:");

                GridPane addGrid = new GridPane();
                addGrid.setVgap(10);
                addGrid.setHgap(10);
                addGrid.setPadding(new javafx.geometry.Insets(20));

                TextField nameField = new TextField();
                TextField idField = new TextField();
                TextField courseField = new TextField();

                Button saveButton = new Button("Save");

                addGrid.add( new Label("Name:"), 0, 0);
                addGrid.add(nameField, 1, 0);
                addGrid.add(new Label("ID:"), 0, 1);
                addGrid.add(idField, 1, 1);
                addGrid.add(new Label("Course:"), 0, 2);
                addGrid.add(courseField, 1, 2);
                addGrid.add(saveButton, 1, 3);

                saveButton.setOnAction(e2 -> {
                    String name = nameField.getText();
                    String id = idField.getText();
                    String course = courseField.getText();

                    if (!name.isEmpty() && !id.isEmpty() && !course.isEmpty()) {
                        Student newStudent = new Student(name, id, course);
                        students.put(id, newStudent);
                        addStage.close();
                        System.out.println("Student added " + name + ", " + id + ", " + course);
                    }
                });

                Scene addScene = new Scene(addGrid, 300, 200);
                addStage.setScene(addScene);
                addStage.show();
            });
            
            dashboardLayout.getChildren().addAll(welcomeLabel, addStudentButton, viewStudentsButton);

            Scene dashboardScene = new Scene(dashboardLayout, 400, 300);
            dashboardStage.setScene(dashboardScene);
            dashboardStage.show();

            } else {
                message.setText("Invalid username or password.");
            }
        });

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
        stage.setTitle("Login System (JSON)");
        stage.setScene(scene);
        stage.show();
    }

    private void loadUsers() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            Type type = new TypeToken<HashMap<String, String>>() {}.getType();
            HashMap<String, String> data = gson.fromJson(reader, type);
            if (data != null) {
                users = data;
            }
        } catch (Exception e) {
            System.out.println("No users.json found, starting empty.");
        }
    }

    public static void main(String[] args) {
        launch(args);

        /* In order to make the code work, inside the terminal paste the following:
        First paste this:
        cd ~/Desktop/StudentCourseManagement/src

        Then paste this:
javac --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp "../lib/gson-2.10.1.jar" Main.java

        Finally paste this:
java --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp ".:../lib/gson-2.10.1.jar" Main
        */

        // Note: "admin", "1234"
        
    }
}
