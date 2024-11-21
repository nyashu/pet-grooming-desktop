package com.petgrooming.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    // Setter method for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Optional: method to validate password (if needed)
    public boolean validatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    // Getters and Setters
}
