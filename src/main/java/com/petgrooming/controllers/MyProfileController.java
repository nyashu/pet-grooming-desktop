package com.petgrooming.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyProfileController {

    @FXML
    private Label lblName;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhone;

    public void initialize() {
        lblName.setText("Jane Doe");
        lblAddress.setText("456 Elm Street, Green Valley");
        lblEmail.setText("jane.doe@example.com");
        lblPhone.setText("+9876543210");
    }
}
