package com.barkovsky.check_runner.reflection_task.entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> friends;
    private Map<String, Integer> marks;

    public Student(int id, String firstName, String lastName, String email, List<String> friends, Map<String, Integer> marks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.friends = friends;
        this.marks = marks;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<String, Integer> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id
                && Objects.equals(firstName, student.firstName)
                && Objects.equals(lastName, student.lastName)
                && Objects.equals(email, student.email)
                && Objects.equals(friends, student.friends)
                && Objects.equals(marks, student.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.email, this.friends, this.marks);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", friends=" + friends +
                ", marks=" + marks +
                '}';
    }

    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String email;
        private List<String> friends;
        private Map<String, Integer> marks;

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setFriends(List<String> friends) {
            this.friends = friends;
            return this;
        }

        public Builder setMarks(Map<String, Integer> marks) {
            this.marks = marks;
            return this;
        }

        public Student build() {
            return new Student(this.id, this.firstName, this.lastName, this.email, this.friends, this.marks);
        }
    }
}
