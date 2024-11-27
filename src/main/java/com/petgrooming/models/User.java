package com.petgrooming.models;

public class User {
    private int userId;              // User ID
    private String username;     // Username for login
    private String password;     // Password for login
    private String email;        // Email address
    private String fullName;     // Full name of the user
    private String phone;        // Phone number
    private String address;      // Address of the user
    private String role;         // Role: Admin, Groomer, Customer, or Staff

    // Default Constructor
    public User() {
    }
    
    public User(int userId, String fullName) {
        this.userId = userId;
        this.fullName = fullName;
    }

    // Constructor with all fields
    public User(int userId, String username, String password, String email, String fullName, String phone, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Override toString for easy debugging
    @Override
    public String toString() {
    	 return fullName;
    }
}
