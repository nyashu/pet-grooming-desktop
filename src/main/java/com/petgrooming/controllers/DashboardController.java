package com.petgrooming.controllers;

import com.petgrooming.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DashboardController {
	
    @FXML
    private StackPane mainContent;
    
    @FXML
    private Button btnManageUsers, btnAppointments, btnReports, btnProfile, btnLogout;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Button viewHistoryButton;

    private User loggedInUser;

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;

        // Set the welcome message
        welcomeLabel.setText("Welcome, " + user.getFullName() + " (" + user.getRole() + ")");

        // Adjust dashboard options based on role
        switch (user.getRole()) {
            case "Admin":
            	btnManageUsers.setVisible(true);
            	btnAppointments.setVisible(true);
                break;
            case "Groomer":
            	btnAppointments.setVisible(true);
                break;
            case "Customer":
                viewHistoryButton.setVisible(true);
                break;
            case "Staff":
            	btnAppointments.setVisible(true);
                break;
        }
    }

    @FXML
    private void initialize() {
        // Add action listeners for sidebar buttons
        btnManageUsers.setOnAction(e -> loadManageUsers());
        btnAppointments.setOnAction(e -> loadAppointments());
        btnReports.setOnAction(e -> loadReports());
        btnProfile.setOnAction(e -> loadProfile());
        btnLogout.setOnAction(e -> handleLogout());
    }
    
    public void handleLogout() {
        // Close the current stage and return to the login screen
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        stage.close();
    }

    private void loadManageUsers() {
        // Dynamically load content for managing users
        mainContent.getChildren().clear();
        mainContent.getChildren().add(new Label("Manage Users Content Goes Here..."));
    }

    private void loadAppointments() {
        // Dynamically load content for appointments
        mainContent.getChildren().clear();
        mainContent.getChildren().add(new Label("Appointments Content Goes Here..."));
    }

    private void loadReports() {
        // Dynamically load content for reports
        mainContent.getChildren().clear();
        mainContent.getChildren().add(new Label("Reports Content Goes Here..."));
    }

    private void loadProfile() {
        // Dynamically load profile content
        mainContent.getChildren().clear();
        mainContent.getChildren().add(new Label("Profile Content Goes Here..."));
    }
    
    @FXML
    private void handleExit() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
    
    @FXML
    private void handleAbout() {
        // Create an "About" dialog box
        Alert aboutAlert = new Alert(AlertType.INFORMATION);
        aboutAlert.setTitle("About");
        aboutAlert.setHeaderText("Pet Grooming Service Application");
        aboutAlert.setContentText("Version 1.0\nDeveloped by: Your Name\nThis application manages pet grooming services, schedules, and payments.");
        
        // Show the dialog
        aboutAlert.showAndWait();
    }
}

