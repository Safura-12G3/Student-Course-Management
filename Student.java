// This part is going to be Student.java

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String id;
    private String course;

    public Student(String name, String id, String course) {
        private String name;
        private String id;
        private String course;

        public Student(String name, String id, String course) {
            this.name = name;
            this.id = id;
            this.course = course;
        }

        // Getters; a getter (also known as an accessor method) is a method used to retrieve the value of a class's variable (field).
        public String getName() { return name; }
        public String getId() { return id; }
        public String getCourse() { return course; }

        // Setters; a setter (also known as a mutator method) is a method used to set or update the value of a class's variable (field).
        public void setName(String name) { this.name = name; }
        public void setId(String id) { this.id = id; }
        public void setCourse(String course) { this.course = course; }
    }
}