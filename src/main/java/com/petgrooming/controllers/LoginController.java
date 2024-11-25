package com.petgrooming.controllers;

import java.io.IOException;

import com.petgrooming.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    public void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter both email and password!");
            alert.show();
            return;
        }

        UserController userController = new UserController();
        User user = userController.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("Login Successful! Welcome, " + user.getFullName());
//            alert.show();

            // Redirect to the dashboard
            navigateToDashboard(user);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Email or Password!");
            alert.show();
        }
    }

    private void navigateToDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard.fxml"));
            Parent root = loader.load();

            // Pass the user to the dashboard controller
            DashboardController controller = loader.getController();
            controller.setLoggedInUser(user);

            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard - " + user.getRole());
            stage.setMaximized(true);
            stage.show();
            
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleSignUp(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Sign-up feature coming soon!");
        alert.show();

    }
}
