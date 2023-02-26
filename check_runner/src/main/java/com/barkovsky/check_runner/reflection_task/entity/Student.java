package com.barkovsky.check_runner.reflection_task.entity;

import com.barkovsky.check_runner.reflection_task.annotation.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Arrays;
import java.util.Objects;

public class Student {

    private long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    private String phoneNumber;
    @Password
    private char[] password;

    public Student(long id, String firstName, String lastName, String email, String phoneNumber, char[] password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
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
                && Objects.equals(phoneNumber, student.phoneNumber)
                && Arrays.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, email, phoneNumber);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    public static class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private char[] password;

        private Builder() {

        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(long id) {
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

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPassword(char[] password) {
            this.password = password;
            return this;
        }

        public Student build() {
            return new Student(this.id, this.firstName, this.lastName, this.email, this.phoneNumber, this.password);
        }
    }
}
