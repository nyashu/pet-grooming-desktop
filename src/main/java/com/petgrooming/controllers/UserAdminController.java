package com.petgrooming.controllers;

import com.petgrooming.models.User;
import com.petgrooming.utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserAdminController {

    @FXML private TextField txtUsername, txtEmail, txtFullName, txtPhone, txtAddress;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<String> cmbRole;
    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn<User, Integer> colId;
    @FXML private TableColumn<User, String> colUsername, colEmail, colFullName, colPhone, colAddress, colRole;
    @FXML private Button btnAddUser, btnUpdateUser, btnDeleteUser;

    private UserManager userManager;
    private ObservableList<User> userList;

    public UserAdminController() {
        // Initialize the user manager and database connection
        try {
            userManager = new UserManager(DatabaseConnection.getConnection());
            userList = FXCollections.observableArrayList(userManager.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Set up the table columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Bind the table to the observable list
        tblUsers.setItems(userList);

        // Populate the combo box with roles
        cmbRole.setItems(FXCollections.observableArrayList("Admin", "Customer", "Staff", "Groomer"));
    }

    @FXML
    private void handleAddUser() {
        // Create a new user from form input
        User newUser = new User(
            0,
            txtUsername.getText(),
            txtPassword.getText(),
            txtEmail.getText(),
            txtFullName.getText(),
            txtPhone.getText(),
            txtAddress.getText(),
            cmbRole.getValue()
        );
        userManager.addUser(newUser);
        refreshTable();
        clearForm();
    }

    @FXML
    private void handleUpdateUser() {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setUsername(txtUsername.getText());
            selectedUser.setPassword(txtPassword.getText());
            selectedUser.setEmail(txtEmail.getText());
            selectedUser.setFullName(txtFullName.getText());
            selectedUser.setPhone(txtPhone.getText());
            selectedUser.setAddress(txtAddress.getText());
            selectedUser.setRole(cmbRole.getValue());

            userManager.updateUser(selectedUser);
            refreshTable();
            clearForm();
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userManager.deleteUser(selectedUser.getId());
            refreshTable();
            clearForm();
        }
    }

    @FXML
    private void handleClearForm() {
        clearForm();
    }

    private void refreshTable() {
        userList.setAll(userManager.getAllUsers());
    }

    private void clearForm() {
        txtUsername.clear();
        txtPassword.clear();
        txtEmail.clear();
        txtFullName.clear();
        txtPhone.clear();
        txtAddress.clear();
        cmbRole.setValue(null);
    }
}
