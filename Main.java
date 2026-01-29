// JAVA PROGRAMMING SEMESTER 1 - FINAL PROJECT
// Student Course Management System - By: Safura

// Here we are importing our JavaFX components and layouts.
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// GSON imports
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// Java utility and IO imports
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashMap;

// Main JavFX application class
public class Main extends Application {

    // Stores username-password pairs, stores students using ID as the key
    private HashMap<String, String> users = new HashMap<>();
    private HashMap<String, Student> students = new HashMap<>();

    // JSON file names (users and students)
    private final String FILE_NAME = "users.json";
    private final String STUDENTS_FILE = "students.json";

    // Gson object to read and write JSON
    private Gson gson = new Gson();

    // Runs the JavaFX application
    @Override
    public void start(Stage stage) {

        // Loads users and students from JSON files
        loadUsers();
        loadStudents();

        // Login screen components
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");
        Label message = new Label();


        // Handles the aftermath of clicking login button
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            // Checks whether the username and password match
            if (users.containsKey(username) && users.get(username).equals(password)) {
                message.setText("Login successful!");
            
            // Hides the login window
            stage.hide();

            // Create dashboard window
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("Dashboard");

            // Layout for dashboard buttons
            VBox dashboardLayout = new VBox(10);
            dashboardLayout.setPadding(new javafx.geometry.Insets(20));

            // Dashboard components
            Label welcomeLabel = new Label("Welcome, " + username + "!");
            Button addStudentButton = new Button("Add Student");
            Button viewStudentsButton = new Button("View Students");
            

            // Opens a new window to add a student
            addStudentButton.setOnAction(ev -> {
                System.out.println("Add Student button clicked");

                Stage addStage = new Stage();
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

                // Save button action
                saveButton.setOnAction(e2 -> {
                    String name = nameField.getText();
                    String id = idField.getText();
                    String course = courseField.getText();

                    // Ensures all fields are filled
                    if (!name.isEmpty() && !id.isEmpty() && !course.isEmpty()) {
                        Student newStudent = new Student(name, id, course);
                        // Store student using ID as key
                        students.put(id, newStudent); // add student
                        saveStudents();               // save student immediately
                        addStage.close();
                        System.out.println("Student added " + name + ", " + id + ", " + course);
                    }
                });

                Scene addScene = new Scene(addGrid, 300, 200);
                addStage.setScene(addScene);
                addStage.show();
            });

            // Opens a window displaying all students
            viewStudentsButton.setOnAction(ev -> {
                Stage viewStage = new Stage();
                viewStage.setTitle("View Students");

                VBox layout = new VBox(10);
                layout.setPadding(new javafx.geometry.Insets(20));
                
                // If no students exist
                if (students.isEmpty()) {
                    layout.getChildren().add(new Label("No students added yet."));
                } else {
                    // Display each student
                    for (Student s : students.values()) {
                        HBox studentRow = new HBox(10);
                        Label studentLabel = new Label(
                            "Name: " + s.getName() +
                            " | ID: " + s.getId() +
                            " | Course: " + s.getCourse()
                        );
                        
                        // The delete button
                        Button deleteButton = new Button("Delete");
                        deleteButton.setOnAction(ev2 -> {
                            students.remove(s.getId());    // remove from HashMap
                            saveStudents();                // save to the updated list
                            studentRow.setVisible(false);  // remove from GUI 
                            System.out.println("Delete student: " + s.getName());
                        });

                        studentRow.getChildren().addAll(studentLabel, deleteButton);
                        layout.getChildren().add(studentRow);
                    }
                }

                Scene scene = new Scene(layout, 400, 300);
                viewStage.setScene(scene);
                viewStage.show();
            });
        

            // Adding dashboard components
            dashboardLayout.getChildren().addAll(welcomeLabel, addStudentButton, viewStudentsButton);

            Scene dashboardScene = new Scene(dashboardLayout, 400, 300);
            dashboardStage.setScene(dashboardScene);
            dashboardStage.show();

            } else {
                message.setText("Invalid username or password.");
            }
        });


        // Login screen layout
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

    // Loading users from JSON
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

    // Saves and lists students added onto the JSON file.
    private void loadStudents() {
        try (FileReader reader = new FileReader(STUDENTS_FILE)) {
            Type type = new TypeToken<HashMap<String, Student>>() {}.getType();
            HashMap<String, Student> data = gson.fromJson(reader, type);
            if (data != null) {
                students = data;
            }
        } catch (Exception e) {
            System.out.println("No students.json found, starting empty.");
        }
    }

    // Saves students HashMap to students.json
    private void saveStudents() {
        try (FileWriter writer = new FileWriter(STUDENTS_FILE)) {
            gson.toJson(students, writer);
            System.out.println("Students saved successfully!!");
        } catch (Exception e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

        /* In order to make the code work, inside the terminal paste the following:
        First paste this:
        cd ~/Desktop/StudentCourseManagement/src

        Then paste this: 
       javac --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp "../lib/gson-2.10.1.jar" Main.java Student.java
        
        Finally paste this:
        java --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp ".:../lib/gson-2.10.1.jar" Main

        */

        // Note: "admin", "1234"