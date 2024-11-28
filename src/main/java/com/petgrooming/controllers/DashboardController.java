package com.petgrooming.controllers;

import java.io.IOException;

import com.petgrooming.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
//                viewHistoryButton.setVisible(true);
            	btnAppointments.setVisible(true);
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
        try {
            // Close the current dashboard stage
            Stage currentStage = (Stage) btnLogout.getScene().getWindow();
            currentStage.close();

            // Load the login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login window
            Stage loginStage = new Stage();
            loginStage.setScene(new Scene(root));
            loginStage.setTitle("Login");
            loginStage.setMaximized(true);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadManageUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/usermanagement.fxml"));
            Parent manageUsersView = loader.load();
            
            // Set the loaded FXML in the main content area
            mainContent.getChildren().clear();
            mainContent.getChildren().add(manageUsersView);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    private void loadAppointments() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/appointmentmanagement.fxml"));
            Parent manageAppointmentsView = loader.load();
            
            // Set the loaded FXML in the main content area
            mainContent.getChildren().clear();
            mainContent.getChildren().add(manageAppointmentsView);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    private void loadReports() {
        // Dynamically load content for reports
        mainContent.getChildren().clear();
        mainContent.getChildren().add(new Label("Reports Content Goes Here..."));
    }

    @FXML
    private void loadProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/profileview.fxml"));
            Parent profilePage = loader.load();

            mainContent.getChildren().clear();
            mainContent.getChildren().add(profilePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        aboutAlert.setContentText("Version 1.0\nDeveloped by: Algoma Student Team\nThis application manages pet grooming services, schedules, and payments.");
        
        // Show the dialog
        aboutAlert.showAndWait();
    }
}

