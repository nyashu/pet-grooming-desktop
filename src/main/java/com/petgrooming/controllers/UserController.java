package com.petgrooming.controllers;

import com.petgrooming.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> users; // This will act as an in-memory database for simplicity

    public UserController() {
        this.users = new ArrayList<>();
    }
    
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/petgrooming", "root", "root");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users"; // Adjust the table name if necessary

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("UserID"),
                        rs.getString("Username").trim(),
                        rs.getString("Password").trim(),
                        rs.getString("Email").trim(),
                        rs.getString("FullName").trim(),
                        rs.getString("Phone").trim(),
                        rs.getString("Address").trim(),
                        rs.getString("Role").trim()
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Finds a user by email and password.
     *
     * @param email    User's email.
     * @param password User's password.
     * @return User if found, otherwise null.
     */
    public User authenticateUser(String email, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new user.
     *
     * @param user User to add.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Updates an existing user.
     *
     * @param userId User ID to update.
     * @param updatedUser Updated user object.
     */
    public void updateUser(int userId, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                users.set(i, updatedUser);
                return;
            }
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId ID of the user to delete.
     */
    public void deleteUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }
}
