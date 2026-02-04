Student Course Management System

Welcome to my term 1 Advanced Programming project! This is a JavaFX-based desktop application that allows users to log in and manage student records.
The system supports adding, viewing, and deleting students, with data stored in JSON files.


-- Project Structure --
- Main.java: Handles GUI, login system, and student management
- Student.java: Student data model (name, ID, course)
- users.json: Stores login credentials
- students.json: Stores student records


-- Features --
- Login system using username and password
- Dashboard with simple navigation
- Add new students
- View all students
- Delete students
- Student data saved and loaded using JSON
- JavaFX graphical user interface


-- CRUD Functionality -- 
- Create: Add new students using the Add Student form
- Read: View all students in the View Students screen
- Delete: Remove students using the Delete button


-- Technologies Used --
- Java
- JavaFX
- Gson (JSON handling)
- Git & GitHub


-- How To Run The Program --  
 1. In order to make the code work, open the terminal.
 2. Paste the following in order one at a time:
       cd ~/Desktop/StudentCourseManagement/src
 
       javac --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp "../lib/gson-2.10.1.jar" Main.java Student.java

       java --module-path "/Users/10G3/Downloads/javafx-sdk-25.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-cp ".:../lib/gson-2.10.1.jar" Main


-- Login Details --
- Username: admin
- Password: 1234


-- Notes --
- Student data is saved in students.json
- User login data is stored in users.json
